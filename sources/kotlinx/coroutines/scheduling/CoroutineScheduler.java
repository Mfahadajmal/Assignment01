package kotlinx.coroutines.scheduling;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.io.Closeable;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.atomicfu.TraceBase$None;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    public static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    private final AtomicBoolean _isTerminated;
    public final AtomicLong controlState;
    public final int corePoolSize;
    public final ExecutorSelector globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ExecutorSelector globalCpuQueue$ar$class_merging$ar$class_merging$ar$class_merging;
    public final long idleWorkerKeepAliveNs;
    public final int maxPoolSize;
    public final AtomicLong parkedWorkersStack;
    public final String schedulerName;
    public final ResizableAtomicArray workers;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Worker extends Thread {
        public volatile int indexInArray;
        public final WorkQueue localQueue;
        public boolean mayHaveLocalTasks;
        private long minDelayUntilStealableTaskNs;
        public volatile Object nextParkedWorker;
        private int rngState;
        public int state$ar$edu$82fb9dde_0;
        private final Ref$ObjectRef stolenTask;
        private long terminationDeadline;
        public final AtomicInt workerCtl;

        public Worker(int i) {
            setDaemon(true);
            setContextClassLoader(CoroutineScheduler.this.getClass().getClassLoader());
            this.localQueue = new WorkQueue();
            this.stolenTask = new Ref$ObjectRef();
            this.state$ar$edu$82fb9dde_0 = WorkerState.DORMANT$ar$edu;
            this.workerCtl = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            int nanoTime = (int) System.nanoTime();
            this.rngState = nanoTime == 0 ? 42 : nanoTime;
            setIndexInArray(i);
        }

        private final boolean inStack() {
            if (this.nextParkedWorker != CoroutineScheduler.NOT_IN_STACK) {
                return true;
            }
            return false;
        }

        private final Task pollGlobalQueues() {
            if (nextInt(2) == 0) {
                Task task = (Task) CoroutineScheduler.this.globalCpuQueue$ar$class_merging$ar$class_merging$ar$class_merging.removeFirstOrNull();
                if (task != null) {
                    return task;
                }
                return (Task) CoroutineScheduler.this.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging.removeFirstOrNull();
            }
            Task task2 = (Task) CoroutineScheduler.this.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging.removeFirstOrNull();
            if (task2 != null) {
                return task2;
            }
            return (Task) CoroutineScheduler.this.globalCpuQueue$ar$class_merging$ar$class_merging$ar$class_merging.removeFirstOrNull();
        }

        private final Task trySteal(int i) {
            Task task;
            boolean z;
            long j;
            long j2;
            long j3;
            int i2;
            int i3 = (int) (CoroutineScheduler.this.controlState.value & 2097151);
            if (i3 < 2) {
                return null;
            }
            int nextInt = nextInt(i3);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            long j4 = Long.MAX_VALUE;
            for (int i4 = 0; i4 < i3; i4++) {
                nextInt++;
                if (nextInt > i3) {
                    nextInt = 1;
                }
                Worker worker = (Worker) coroutineScheduler.workers.get(nextInt);
                if (worker != null && worker != this) {
                    WorkQueue workQueue = worker.localQueue;
                    Ref$ObjectRef ref$ObjectRef = this.stolenTask;
                    if (i == 3) {
                        task = workQueue.pollBuffer();
                    } else {
                        int i5 = ((AtomicInt) workQueue.WorkQueue$ar$consumerIndex).value;
                        int i6 = ((AtomicInt) workQueue.WorkQueue$ar$producerIndex).value;
                        while (i5 != i6) {
                            if (i == 1) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z && ((AtomicInt) workQueue.WorkQueue$ar$blockingTasksInBuffer).value == 0) {
                                break;
                            }
                            int i7 = i5 + 1;
                            task = workQueue.tryExtractFromTheMiddle(i5, z);
                            if (task != null) {
                                break;
                            }
                            i5 = i7;
                        }
                        task = null;
                    }
                    if (task != null) {
                        ref$ObjectRef.element = task;
                        j3 = -1;
                        j2 = -1;
                    } else {
                        while (true) {
                            Task task2 = (Task) ((AtomicRef) workQueue.WorkQueue$ar$lastScheduledTask).value;
                            j = -2;
                            if (task2 == null) {
                                break;
                            }
                            if (1 != task2.taskContext$ar$class_merging.getTaskMode()) {
                                i2 = 2;
                            } else {
                                i2 = 1;
                            }
                            if ((i2 & i) == 0) {
                                break;
                            }
                            long nanoTime = TasksKt.schedulerTimeSource.nanoTime() - task2.submissionTime;
                            long j5 = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
                            if (nanoTime < j5) {
                                j = j5 - nanoTime;
                                break;
                            }
                            if (((AtomicRef) workQueue.WorkQueue$ar$lastScheduledTask).compareAndSet(task2, null)) {
                                ref$ObjectRef.element = task2;
                                j = -1;
                                break;
                            }
                        }
                        j2 = j;
                        j3 = -1;
                    }
                    if (j2 == j3) {
                        Ref$ObjectRef ref$ObjectRef2 = this.stolenTask;
                        Task task3 = (Task) ref$ObjectRef2.element;
                        ref$ObjectRef2.element = null;
                        return task3;
                    }
                    if (j2 > 0) {
                        j4 = Math.min(j4, j2);
                    }
                }
            }
            if (j4 == Long.MAX_VALUE) {
                j4 = 0;
            }
            this.minDelayUntilStealableTaskNs = j4;
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00c3 A[ORIG_RETURN, RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final kotlinx.coroutines.scheduling.Task findTask(boolean r9) {
            /*
                r8 = this;
                int r0 = r8.state$ar$edu$82fb9dde_0
                int r1 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.CPU_ACQUIRED$ar$edu
                r2 = 0
                r3 = 1
                if (r0 != r1) goto La
                goto L83
            La:
                kotlinx.coroutines.scheduling.CoroutineScheduler r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
            Lc:
                kotlinx.atomicfu.AtomicLong r1 = r0.controlState
                long r4 = r1.value
                r6 = 9223367638808264704(0x7ffffc0000000000, double:NaN)
                long r6 = r6 & r4
                r1 = 42
                long r6 = r6 >> r1
                int r1 = (int) r6
                if (r1 != 0) goto L71
                kotlinx.coroutines.scheduling.WorkQueue r9 = r8.localQueue
            L1e:
                java.lang.Object r0 = r9.WorkQueue$ar$lastScheduledTask
                kotlinx.atomicfu.AtomicRef r0 = (kotlinx.atomicfu.AtomicRef) r0
                java.lang.Object r0 = r0.value
                kotlinx.coroutines.scheduling.Task r0 = (kotlinx.coroutines.scheduling.Task) r0
                if (r0 != 0) goto L29
                goto L3d
            L29:
                kotlinx.coroutines.scheduling.TaskContextImpl r1 = r0.taskContext$ar$class_merging
                int r1 = r1.getTaskMode()
                if (r1 != r3) goto L3d
                java.lang.Object r1 = r9.WorkQueue$ar$lastScheduledTask
                kotlinx.atomicfu.AtomicRef r1 = (kotlinx.atomicfu.AtomicRef) r1
                boolean r1 = r1.compareAndSet(r0, r2)
                if (r1 == 0) goto L1e
                r2 = r0
                goto L5d
            L3d:
                java.lang.Object r0 = r9.WorkQueue$ar$consumerIndex
                kotlinx.atomicfu.AtomicInt r0 = (kotlinx.atomicfu.AtomicInt) r0
                int r0 = r0.value
                java.lang.Object r1 = r9.WorkQueue$ar$producerIndex
                kotlinx.atomicfu.AtomicInt r1 = (kotlinx.atomicfu.AtomicInt) r1
                int r1 = r1.value
            L49:
                if (r0 == r1) goto L5d
                java.lang.Object r4 = r9.WorkQueue$ar$blockingTasksInBuffer
                kotlinx.atomicfu.AtomicInt r4 = (kotlinx.atomicfu.AtomicInt) r4
                int r4 = r4.value
                if (r4 != 0) goto L54
                goto L5d
            L54:
                int r1 = r1 + (-1)
                kotlinx.coroutines.scheduling.Task r4 = r9.tryExtractFromTheMiddle(r1, r3)
                if (r4 == 0) goto L49
                r2 = r4
            L5d:
                if (r2 != 0) goto Lc3
                kotlinx.coroutines.scheduling.CoroutineScheduler r9 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                com.google.mlkit.common.sdkinternal.ExecutorSelector r9 = r9.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging
                java.lang.Object r9 = r9.removeFirstOrNull()
                r2 = r9
                kotlinx.coroutines.scheduling.Task r2 = (kotlinx.coroutines.scheduling.Task) r2
                if (r2 != 0) goto Lc3
                kotlinx.coroutines.scheduling.Task r2 = r8.trySteal(r3)
                goto Lc3
            L71:
                r6 = -4398046511104(0xfffffc0000000000, double:NaN)
                long r6 = r6 + r4
                kotlinx.atomicfu.AtomicLong r1 = r0.controlState
                boolean r1 = r1.compareAndSet(r4, r6)
                if (r1 == 0) goto Lc
                int r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.CPU_ACQUIRED$ar$edu
                r8.state$ar$edu$82fb9dde_0 = r0
            L83:
                if (r9 == 0) goto Lbd
                kotlinx.coroutines.scheduling.CoroutineScheduler r9 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                int r9 = r9.corePoolSize
                int r9 = r9 + r9
                int r9 = r8.nextInt(r9)
                if (r9 != 0) goto L91
                goto L92
            L91:
                r3 = 0
            L92:
                if (r3 == 0) goto L9c
                kotlinx.coroutines.scheduling.Task r9 = r8.pollGlobalQueues()
                if (r9 == 0) goto L9c
                r2 = r9
                goto Lc3
            L9c:
                kotlinx.coroutines.scheduling.WorkQueue r9 = r8.localQueue
                java.lang.Object r0 = r9.WorkQueue$ar$lastScheduledTask
                kotlinx.atomicfu.AtomicRef r0 = (kotlinx.atomicfu.AtomicRef) r0
                java.lang.Object r0 = r0.getAndSet(r2)
                kotlinx.coroutines.scheduling.Task r0 = (kotlinx.coroutines.scheduling.Task) r0
                if (r0 != 0) goto Lb0
                kotlinx.coroutines.scheduling.Task r9 = r9.pollBuffer()
                r2 = r9
                goto Lb1
            Lb0:
                r2 = r0
            Lb1:
                if (r2 == 0) goto Lb4
                goto Lc3
            Lb4:
                if (r3 != 0) goto Lc4
                kotlinx.coroutines.scheduling.Task r2 = r8.pollGlobalQueues()
                if (r2 == 0) goto Lc4
                goto Lc3
            Lbd:
                kotlinx.coroutines.scheduling.Task r2 = r8.pollGlobalQueues()
                if (r2 == 0) goto Lc4
            Lc3:
                return r2
            Lc4:
                r9 = 3
                kotlinx.coroutines.scheduling.Task r9 = r8.trySteal(r9)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.findTask(boolean):kotlinx.coroutines.scheduling.Task");
        }

        public final int nextInt(int i) {
            int i2 = this.rngState;
            int i3 = i2 ^ (i2 << 13);
            int i4 = i3 ^ (i3 >> 17);
            int i5 = i4 ^ (i4 << 5);
            this.rngState = i5;
            int i6 = i - 1;
            if ((i6 & i) == 0) {
                return i5 & i6;
            }
            return (i5 & Preference.DEFAULT_ORDER) % i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            boolean z;
            long j;
            int i;
            boolean z2 = false;
            loop0: while (true) {
                boolean z3 = z2;
                while (!CoroutineScheduler.this.isTerminated() && this.state$ar$edu$82fb9dde_0 != WorkerState.TERMINATED$ar$edu) {
                    Task findTask = findTask(this.mayHaveLocalTasks);
                    if (findTask != null) {
                        this.minDelayUntilStealableTaskNs = 0L;
                        int taskMode = findTask.taskContext$ar$class_merging.getTaskMode();
                        this.terminationDeadline = 0L;
                        if (this.state$ar$edu$82fb9dde_0 == WorkerState.PARKING$ar$edu) {
                            boolean z4 = DebugKt.ASSERTIONS_ENABLED;
                            this.state$ar$edu$82fb9dde_0 = WorkerState.BLOCKING$ar$edu;
                        }
                        if (taskMode != 0 && tryReleaseCpu$ar$edu(WorkerState.BLOCKING$ar$edu)) {
                            CoroutineScheduler.this.signalCpuWork();
                        }
                        CoroutineScheduler.runSafely$ar$ds(findTask);
                        if (taskMode != 0) {
                            CoroutineScheduler.this.controlState.addAndGet(-2097152L);
                            if (this.state$ar$edu$82fb9dde_0 != WorkerState.TERMINATED$ar$edu) {
                                boolean z5 = DebugKt.ASSERTIONS_ENABLED;
                                this.state$ar$edu$82fb9dde_0 = WorkerState.DORMANT$ar$edu;
                            }
                        }
                    } else {
                        this.mayHaveLocalTasks = z2;
                        if (this.minDelayUntilStealableTaskNs != 0) {
                            if (!z3) {
                                z3 = true;
                            } else {
                                tryReleaseCpu$ar$edu(WorkerState.PARKING$ar$edu);
                                Thread.interrupted();
                                LockSupport.parkNanos(this.minDelayUntilStealableTaskNs);
                                this.minDelayUntilStealableTaskNs = 0L;
                            }
                        } else {
                            if (!inStack()) {
                                CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
                                if (this.nextParkedWorker == CoroutineScheduler.NOT_IN_STACK) {
                                    AtomicLong atomicLong = coroutineScheduler.parkedWorkersStack;
                                    do {
                                        j = atomicLong.value;
                                        i = this.indexInArray;
                                        boolean z6 = DebugKt.ASSERTIONS_ENABLED;
                                        this.nextParkedWorker = coroutineScheduler.workers.get((int) (j & 2097151));
                                    } while (!coroutineScheduler.parkedWorkersStack.compareAndSet(j, ((2097152 + j) & (-2097152)) | i));
                                } else {
                                    z = z2;
                                    z2 = z;
                                }
                            } else {
                                this.workerCtl.setValue(-1);
                                while (inStack() && this.workerCtl.value == -1 && !CoroutineScheduler.this.isTerminated() && this.state$ar$edu$82fb9dde_0 != WorkerState.TERMINATED$ar$edu) {
                                    tryReleaseCpu$ar$edu(WorkerState.PARKING$ar$edu);
                                    Thread.interrupted();
                                    if (this.terminationDeadline == 0) {
                                        this.terminationDeadline = System.nanoTime() + CoroutineScheduler.this.idleWorkerKeepAliveNs;
                                    }
                                    LockSupport.parkNanos(CoroutineScheduler.this.idleWorkerKeepAliveNs);
                                    if (System.nanoTime() - this.terminationDeadline >= 0) {
                                        this.terminationDeadline = 0L;
                                        CoroutineScheduler coroutineScheduler2 = CoroutineScheduler.this;
                                        synchronized (coroutineScheduler2.workers) {
                                            if (!coroutineScheduler2.isTerminated()) {
                                                if (((int) (coroutineScheduler2.controlState.value & 2097151)) > coroutineScheduler2.corePoolSize) {
                                                    if (this.workerCtl.compareAndSet(-1, 1)) {
                                                        int i2 = this.indexInArray;
                                                        setIndexInArray(0);
                                                        coroutineScheduler2.parkedWorkersStackTopUpdate(this, i2, 0);
                                                        AtomicLong atomicLong2 = coroutineScheduler2.controlState;
                                                        long andDecrement = AtomicLong.FU.getAndDecrement(atomicLong2);
                                                        OnDeviceSubjectSegmentationInferenceLogEvent onDeviceSubjectSegmentationInferenceLogEvent = atomicLong2.trace$ar$class_merging$ar$class_merging;
                                                        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
                                                        int i3 = (int) (andDecrement & 2097151);
                                                        if (i3 != i2) {
                                                            Object obj = coroutineScheduler2.workers.get(i3);
                                                            obj.getClass();
                                                            Worker worker = (Worker) obj;
                                                            coroutineScheduler2.workers.setSynchronized(i2, worker);
                                                            worker.setIndexInArray(i2);
                                                            coroutineScheduler2.parkedWorkersStackTopUpdate(worker, i3, i2);
                                                        }
                                                        coroutineScheduler2.workers.setSynchronized(i3, null);
                                                        this.state$ar$edu$82fb9dde_0 = WorkerState.TERMINATED$ar$edu;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            z = false;
                            z2 = z;
                        }
                    }
                }
            }
            tryReleaseCpu$ar$edu(WorkerState.TERMINATED$ar$edu);
        }

        public final void setIndexInArray(int i) {
            String valueOf;
            if (i == 0) {
                valueOf = "TERMINATED";
            } else {
                valueOf = String.valueOf(i);
            }
            setName(CoroutineScheduler.this.schedulerName + "-worker-" + valueOf);
            this.indexInArray = i;
        }

        public final boolean tryReleaseCpu$ar$edu(int i) {
            boolean z;
            int i2 = this.state$ar$edu$82fb9dde_0;
            if (i2 == WorkerState.CPU_ACQUIRED$ar$edu) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                CoroutineScheduler.this.controlState.addAndGet(4398046511104L);
            }
            if (i2 != i) {
                this.state$ar$edu$82fb9dde_0 = i;
            }
            return z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WorkerState {
        public static final int CPU_ACQUIRED$ar$edu = 1;
        public static final int BLOCKING$ar$edu = 2;
        public static final int PARKING$ar$edu = 3;
        public static final int DORMANT$ar$edu = 4;
        public static final int TERMINATED$ar$edu = 5;
        private static final /* synthetic */ int[] $VALUES$ar$edu$3e4b07d9_0 = {CPU_ACQUIRED$ar$edu, BLOCKING$ar$edu, PARKING$ar$edu, DORMANT$ar$edu, TERMINATED$ar$edu};

        public static int[] values$ar$edu$229c30f3_0() {
            return new int[]{CPU_ACQUIRED$ar$edu, BLOCKING$ar$edu, PARKING$ar$edu, DORMANT$ar$edu, TERMINATED$ar$edu};
        }
    }

    public CoroutineScheduler(int i, int i2, long j, String str) {
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        if (i > 0) {
            if (i2 >= i) {
                if (i2 <= 2097150) {
                    if (j > 0) {
                        this.globalCpuQueue$ar$class_merging$ar$class_merging$ar$class_merging = new ExecutorSelector();
                        this.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging = new ExecutorSelector();
                        this.parkedWorkersStack = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0L);
                        int i3 = i + 1;
                        this.workers = new ResizableAtomicArray(i3 + i3);
                        this.controlState = OnDeviceSubjectSegmentationCreateLogEvent.atomic(i << 42);
                        this._isTerminated = OnDeviceSubjectSegmentationCreateLogEvent.atomic(false);
                        return;
                    }
                    throw new IllegalArgumentException("Idle worker keep alive time " + j + " must be positive");
                }
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i2, "Max pool size ", " should not exceed maximal supported number of threads 2097150"));
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i, i2, "Max pool size ", " should be greater than or equals to core pool size "));
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Core pool size ", " should be at least 1"));
    }

    private final int createNewWorker() {
        synchronized (this.workers) {
            if (isTerminated()) {
                return -1;
            }
            long j = this.controlState.value;
            int i = (int) (j & 2097151);
            int coerceAtLeast = OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(i - ((int) ((j & 4398044413952L) >> 21)), 0);
            if (coerceAtLeast >= this.corePoolSize) {
                return 0;
            }
            if (i >= this.maxPoolSize) {
                return 0;
            }
            int i2 = ((int) (this.controlState.value & 2097151)) + 1;
            if (this.workers.get(i2) == null) {
                Worker worker = new Worker(i2);
                this.workers.setSynchronized(i2, worker);
                AtomicLong atomicLong = this.controlState;
                long incrementAndGet = AtomicLong.FU.incrementAndGet(atomicLong);
                OnDeviceSubjectSegmentationInferenceLogEvent onDeviceSubjectSegmentationInferenceLogEvent = atomicLong.trace$ar$class_merging$ar$class_merging;
                TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
                if (i2 == ((int) (2097151 & incrementAndGet))) {
                    int i3 = coerceAtLeast + 1;
                    worker.start();
                    return i3;
                }
                throw new IllegalArgumentException("Failed requirement.");
            }
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    private final Worker currentWorker() {
        Worker worker;
        Thread currentThread = Thread.currentThread();
        if (currentThread instanceof Worker) {
            worker = (Worker) currentThread;
        } else {
            worker = null;
        }
        if (worker == null || !Intrinsics.areEqual(CoroutineScheduler.this, this)) {
            return null;
        }
        return worker;
    }

    public static /* synthetic */ void dispatch$default$ar$class_merging$ar$ds(CoroutineScheduler coroutineScheduler, Runnable runnable, boolean z, int i) {
        TaskContextImpl taskContextImpl;
        boolean z2;
        if ((i & 2) != 0) {
            taskContextImpl = TasksKt.NonBlockingContext$ar$class_merging;
        } else {
            taskContextImpl = null;
        }
        if ((i & 4) != 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        coroutineScheduler.dispatch$ar$class_merging(runnable, taskContextImpl, z & z2);
    }

    private static final int parkedWorkersStackNextIndex$ar$ds(Worker worker) {
        int i;
        do {
            Object obj = worker.nextParkedWorker;
            if (obj == NOT_IN_STACK) {
                return -1;
            }
            if (obj == null) {
                return 0;
            }
            worker = (Worker) obj;
            i = worker.indexInArray;
        } while (i == 0);
        return i;
    }

    public static final void runSafely$ar$ds(Task task) {
        try {
            task.run();
        } catch (Throwable th) {
            Thread currentThread = Thread.currentThread();
            currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
        }
    }

    private final boolean tryCreateWorker(long j) {
        if (OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(((int) (j & 2097151)) - ((int) ((4398044413952L & j) >> 21)), 0) < this.corePoolSize) {
            int createNewWorker = createNewWorker();
            if (createNewWorker == 1) {
                if (this.corePoolSize > 1) {
                    createNewWorker();
                }
            } else if (createNewWorker <= 0) {
            }
            return true;
        }
        return false;
    }

    private final boolean tryUnpark() {
        Worker worker;
        do {
            AtomicLong atomicLong = this.parkedWorkersStack;
            while (true) {
                long j = atomicLong.value;
                worker = (Worker) this.workers.get((int) (2097151 & j));
                if (worker == null) {
                    worker = null;
                    break;
                }
                long j2 = 2097152 + j;
                int parkedWorkersStackNextIndex$ar$ds = parkedWorkersStackNextIndex$ar$ds(worker);
                if (parkedWorkersStackNextIndex$ar$ds >= 0 && this.parkedWorkersStack.compareAndSet(j, (j2 & (-2097152)) | parkedWorkersStackNextIndex$ar$ds)) {
                    worker.nextParkedWorker = NOT_IN_STACK;
                    break;
                }
            }
            if (worker == null) {
                return false;
            }
        } while (!worker.workerCtl.compareAndSet(-1, 0));
        LockSupport.unpark(worker);
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        long j;
        Task task;
        if (!this._isTerminated.compareAndSet(false, true)) {
            return;
        }
        Worker currentWorker = currentWorker();
        synchronized (this.workers) {
            j = this.controlState.value & 2097151;
        }
        int i = (int) j;
        if (i > 0) {
            int i2 = 1;
            while (true) {
                Object obj = this.workers.get(i2);
                obj.getClass();
                Worker worker = (Worker) obj;
                if (worker != currentWorker) {
                    while (worker.getState() != Thread.State.TERMINATED) {
                        LockSupport.unpark(worker);
                        worker.join(10000L);
                    }
                    boolean z = DebugKt.ASSERTIONS_ENABLED;
                    WorkQueue workQueue = worker.localQueue;
                    ExecutorSelector executorSelector = this.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging;
                    Task task2 = (Task) ((AtomicRef) workQueue.WorkQueue$ar$lastScheduledTask).getAndSet(null);
                    if (task2 != null) {
                        executorSelector.addLast(task2);
                    }
                    while (true) {
                        Task pollBuffer = workQueue.pollBuffer();
                        if (pollBuffer == null) {
                            break;
                        } else {
                            executorSelector.addLast(pollBuffer);
                        }
                    }
                }
                if (i2 == i) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        this.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging.close();
        this.globalCpuQueue$ar$class_merging$ar$class_merging$ar$class_merging.close();
        while (true) {
            if (currentWorker != null) {
                task = currentWorker.findTask(true);
                if (task != null) {
                    continue;
                    runSafely$ar$ds(task);
                }
            }
            task = (Task) this.globalCpuQueue$ar$class_merging$ar$class_merging$ar$class_merging.removeFirstOrNull();
            if (task == null && (task = (Task) this.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging.removeFirstOrNull()) == null) {
                break;
            }
            runSafely$ar$ds(task);
        }
        if (currentWorker != null) {
            currentWorker.tryReleaseCpu$ar$edu(WorkerState.TERMINATED$ar$edu);
        }
        boolean z2 = DebugKt.ASSERTIONS_ENABLED;
        this.parkedWorkersStack.setValue(0L);
        this.controlState.setValue(0L);
    }

    public final void dispatch$ar$class_merging(Runnable runnable, TaskContextImpl taskContextImpl, boolean z) {
        Task taskImpl;
        long j;
        boolean addLast;
        long nanoTime = TasksKt.schedulerTimeSource.nanoTime();
        if (runnable instanceof Task) {
            taskImpl = (Task) runnable;
            taskImpl.submissionTime = nanoTime;
            taskImpl.taskContext$ar$class_merging = taskContextImpl;
        } else {
            taskImpl = new TaskImpl(runnable, nanoTime, taskContextImpl);
        }
        int taskMode = taskImpl.taskContext$ar$class_merging.getTaskMode();
        if (taskMode != 0) {
            j = this.controlState.addAndGet(2097152L);
        } else {
            j = 0;
        }
        Worker currentWorker = currentWorker();
        boolean z2 = true;
        if (currentWorker != null && currentWorker.state$ar$edu$82fb9dde_0 != WorkerState.TERMINATED$ar$edu && (taskImpl.taskContext$ar$class_merging.getTaskMode() != 0 || currentWorker.state$ar$edu$82fb9dde_0 != WorkerState.BLOCKING$ar$edu)) {
            currentWorker.mayHaveLocalTasks = true;
            WorkQueue workQueue = currentWorker.localQueue;
            if (z) {
                taskImpl = workQueue.addLast(taskImpl);
            } else {
                Task task = (Task) ((AtomicRef) workQueue.WorkQueue$ar$lastScheduledTask).getAndSet(taskImpl);
                if (task == null) {
                    taskImpl = null;
                } else {
                    taskImpl = workQueue.addLast(task);
                }
            }
        }
        if (taskImpl != null) {
            if (taskImpl.taskContext$ar$class_merging.getTaskMode() == 1) {
                addLast = this.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging.addLast(taskImpl);
            } else {
                addLast = this.globalCpuQueue$ar$class_merging$ar$class_merging$ar$class_merging.addLast(taskImpl);
            }
            if (!addLast) {
                throw new RejectedExecutionException(String.valueOf(this.schedulerName).concat(" was terminated"));
            }
        }
        if (!z || currentWorker == null) {
            z2 = false;
        }
        if (taskMode != 0) {
            if (!z2 && !tryUnpark() && !tryCreateWorker(j)) {
                tryUnpark();
                return;
            }
            return;
        }
        if (!z2) {
            signalCpuWork();
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        dispatch$default$ar$class_merging$ar$ds(this, runnable, false, 6);
    }

    public final boolean isTerminated() {
        return this._isTerminated.getValue();
    }

    public final void parkedWorkersStackTopUpdate(Worker worker, int i, int i2) {
        while (true) {
            long j = this.parkedWorkersStack.value;
            long j2 = 2097152 + j;
            int i3 = (int) (2097151 & j);
            if (i3 == i) {
                if (i2 == 0) {
                    i3 = parkedWorkersStackNextIndex$ar$ds(worker);
                } else {
                    i3 = i2;
                }
            }
            if (i3 >= 0 && this.parkedWorkersStack.compareAndSet(j, (j2 & (-2097152)) | i3)) {
                return;
            }
        }
    }

    public final void signalCpuWork() {
        if (!tryUnpark() && !tryCreateWorker(this.controlState.value)) {
            tryUnpark();
        }
    }

    public final String toString() {
        int bufferSize;
        ArrayList arrayList = new ArrayList();
        int length = this.workers.array.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 1; i6 < length; i6++) {
            Worker worker = (Worker) this.workers.get(i6);
            if (worker != null) {
                WorkQueue workQueue = worker.localQueue;
                if (((AtomicRef) workQueue.WorkQueue$ar$lastScheduledTask).value != null) {
                    bufferSize = workQueue.getBufferSize() + 1;
                } else {
                    bufferSize = workQueue.getBufferSize();
                }
                int i7 = worker.state$ar$edu$82fb9dde_0;
                int i8 = WorkerState.CPU_ACQUIRED$ar$edu;
                int i9 = i7 - 1;
                if (i7 != 0) {
                    if (i9 != 0) {
                        if (i9 != 1) {
                            if (i9 != 2) {
                                if (i9 != 3) {
                                    if (i9 == 4) {
                                        i5++;
                                    }
                                } else {
                                    i4++;
                                    if (bufferSize > 0) {
                                        arrayList.add(bufferSize + "d");
                                    }
                                }
                            } else {
                                i3++;
                            }
                        } else {
                            arrayList.add(bufferSize + "b");
                            i2++;
                        }
                    } else {
                        arrayList.add(bufferSize + "c");
                        i++;
                    }
                } else {
                    throw null;
                }
            }
        }
        AtomicLong atomicLong = this.controlState;
        String str = this.schedulerName;
        long j = atomicLong.value;
        String hexAddress = DebugStringsKt.getHexAddress(this);
        int i10 = this.corePoolSize;
        int i11 = this.maxPoolSize;
        ExecutorSelector executorSelector = this.globalCpuQueue$ar$class_merging$ar$class_merging$ar$class_merging;
        ExecutorSelector executorSelector2 = this.globalBlockingQueue$ar$class_merging$ar$class_merging$ar$class_merging;
        long j2 = j & 2097151;
        long j3 = 4398044413952L & j;
        long j4 = j & 9223367638808264704L;
        return str + "@" + hexAddress + "[Pool Size {core = " + i10 + ", max = " + i11 + "}, Worker States {CPU = " + i + ", blocking = " + i2 + ", parked = " + i3 + ", dormant = " + i4 + ", terminated = " + i5 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + executorSelector.getSize() + ", global blocking queue size = " + executorSelector2.getSize() + ", Control State {created workers= " + ((int) j2) + ", blocking tasks = " + ((int) (j3 >> 21)) + ", CPUs acquired = " + (i10 - ((int) (j4 >> 42))) + "}]";
    }
}
