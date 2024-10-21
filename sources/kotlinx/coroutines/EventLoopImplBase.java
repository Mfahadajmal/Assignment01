package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import java.util.Arrays;
import java.util.Objects;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.CoroutineContext;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class EventLoopImplBase extends EventLoop implements Delay {
    public final AtomicRef _delayed;
    private final AtomicBoolean _isCompleted;
    public final AtomicRef _queue;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DelayedResumeTask extends DelayedTask {
        private final CancellableContinuationImpl cont$ar$class_merging;

        public DelayedResumeTask(long j, CancellableContinuationImpl cancellableContinuationImpl) {
            super(j);
            this.cont$ar$class_merging = cancellableContinuationImpl;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.cont$ar$class_merging.resumeUndispatched(EventLoopImplBase.this, Unit.INSTANCE);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public final String toString() {
            String delayedTask = super.toString();
            CancellableContinuationImpl cancellableContinuationImpl = this.cont$ar$class_merging;
            Objects.toString(cancellableContinuationImpl);
            return String.valueOf(delayedTask).concat(cancellableContinuationImpl.toString());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DelayedRunnableTask extends DelayedTask {
        private final Runnable block;

        public DelayedRunnableTask(long j, Runnable runnable) {
            super(j);
            this.block = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.block.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public final String toString() {
            String delayedTask = super.toString();
            Runnable runnable = this.block;
            Objects.toString(runnable);
            return String.valueOf(delayedTask).concat(runnable.toString());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class DelayedTask implements Runnable, Comparable, DisposableHandle, ThreadSafeHeapNode {
        private volatile Object _heap;
        private int index = -1;
        public long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = j;
        }

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            long j = this.nanoTime - ((DelayedTask) obj).nanoTime;
            if (j > 0) {
                return 1;
            }
            if (j >= 0) {
                return 0;
            }
            return -1;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            DelayedTaskQueue delayedTaskQueue;
            synchronized (this) {
                Object obj = this._heap;
                if (obj == EventLoop_commonKt.DISPOSED_TASK) {
                    return;
                }
                if (obj instanceof DelayedTaskQueue) {
                    delayedTaskQueue = (DelayedTaskQueue) obj;
                } else {
                    delayedTaskQueue = null;
                }
                if (delayedTaskQueue != null) {
                    synchronized (delayedTaskQueue) {
                        if (getHeap() != null) {
                            int index = getIndex();
                            boolean z = DebugKt.ASSERTIONS_ENABLED;
                            delayedTaskQueue.removeAtImpl(index);
                        }
                    }
                }
                this._heap = EventLoop_commonKt.DISPOSED_TASK;
            }
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public final ThreadSafeHeap getHeap() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public final int getIndex() {
            return this.index;
        }

        public final int scheduleTask(long j, DelayedTaskQueue delayedTaskQueue, EventLoopImplBase eventLoopImplBase) {
            synchronized (this) {
                if (this._heap != EventLoop_commonKt.DISPOSED_TASK) {
                    synchronized (delayedTaskQueue) {
                        DelayedTask delayedTask = (DelayedTask) delayedTaskQueue.firstImpl();
                        if (eventLoopImplBase.isCompleted()) {
                            return 1;
                        }
                        if (delayedTask == null) {
                            delayedTaskQueue.timeNow = j;
                        } else {
                            long j2 = delayedTask.nanoTime;
                            if (j2 - j < 0) {
                                j = j2;
                            }
                            long j3 = delayedTaskQueue.timeNow;
                            if (j - j3 > 0) {
                                delayedTaskQueue.timeNow = j;
                            } else {
                                j = j3;
                            }
                        }
                        if (this.nanoTime - j < 0) {
                            this.nanoTime = j;
                        }
                        boolean z = DebugKt.ASSERTIONS_ENABLED;
                        setHeap(delayedTaskQueue);
                        ThreadSafeHeapNode[] threadSafeHeapNodeArr = delayedTaskQueue.a;
                        if (threadSafeHeapNodeArr == null) {
                            threadSafeHeapNodeArr = new ThreadSafeHeapNode[4];
                            delayedTaskQueue.a = threadSafeHeapNodeArr;
                        } else if (delayedTaskQueue.getSize() >= threadSafeHeapNodeArr.length) {
                            int size = delayedTaskQueue.getSize();
                            Object[] copyOf = Arrays.copyOf(threadSafeHeapNodeArr, size + size);
                            copyOf.getClass();
                            threadSafeHeapNodeArr = (ThreadSafeHeapNode[]) copyOf;
                            delayedTaskQueue.a = threadSafeHeapNodeArr;
                        }
                        int size2 = delayedTaskQueue.getSize();
                        delayedTaskQueue.setSize(size2 + 1);
                        threadSafeHeapNodeArr[size2] = this;
                        setIndex(size2);
                        delayedTaskQueue.siftUpFrom(size2);
                        return 0;
                    }
                }
                return 2;
            }
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public final void setHeap(ThreadSafeHeap threadSafeHeap) {
            if (this._heap != EventLoop_commonKt.DISPOSED_TASK) {
                this._heap = threadSafeHeap;
                return;
            }
            throw new IllegalArgumentException("Failed requirement.");
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public final void setIndex(int i) {
            this.index = i;
        }

        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + "]";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DelayedTaskQueue extends ThreadSafeHeap {
        public long timeNow;

        public DelayedTaskQueue(long j) {
            this.timeNow = j;
        }
    }

    public EventLoopImplBase() {
        super(null);
        this._queue = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
        this._delayed = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
        this._isCompleted = OnDeviceSubjectSegmentationCreateLogEvent.atomic(false);
    }

    private final boolean enqueueImpl(Runnable runnable) {
        while (true) {
            Object obj = this._queue.value;
            if (isCompleted()) {
                return false;
            }
            if (obj == null) {
                if (this._queue.compareAndSet(null, runnable)) {
                    return true;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                int addLast = lockFreeTaskQueueCore.addLast(runnable);
                if (addLast == 0) {
                    return true;
                }
                if (addLast != 1) {
                    return false;
                }
                this._queue.compareAndSet(obj, lockFreeTaskQueueCore.next());
            } else {
                if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore2.addLast((Runnable) obj);
                lockFreeTaskQueueCore2.addLast(runnable);
                if (this._queue.compareAndSet(obj, lockFreeTaskQueueCore2)) {
                    return true;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        enqueue(runnable);
    }

    public void enqueue(Runnable runnable) {
        if (enqueueImpl(runnable)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.enqueue(runnable);
        }
    }

    public DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        return DefaultExecutorKt.DefaultDelay.invokeOnTimeout(j, runnable, coroutineContext);
    }

    public final boolean isCompleted() {
        return this._isCompleted.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isEmpty() {
        if (!isUnconfinedQueueEmpty()) {
            return false;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.value;
        if (delayedTaskQueue != null && !delayedTaskQueue.isEmpty()) {
            return false;
        }
        Object obj = this._queue.value;
        if (obj == null) {
            return true;
        }
        if (obj instanceof LockFreeTaskQueueCore) {
            return ((LockFreeTaskQueueCore) obj).isEmpty();
        }
        if (obj != EventLoop_commonKt.CLOSED_EMPTY) {
            return false;
        }
        return true;
    }

    @Override // kotlinx.coroutines.EventLoop
    public final long processNextEvent() {
        long j;
        DelayedTask delayedTask;
        ThreadSafeHeapNode threadSafeHeapNode;
        if (processUnconfinedEvent()) {
            return 0L;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.value;
        Runnable runnable = null;
        if (delayedTaskQueue != null && !delayedTaskQueue.isEmpty()) {
            long nanoTime = System.nanoTime();
            do {
                synchronized (delayedTaskQueue) {
                    ThreadSafeHeapNode firstImpl = delayedTaskQueue.firstImpl();
                    if (firstImpl == null) {
                        threadSafeHeapNode = null;
                    } else {
                        DelayedTask delayedTask2 = (DelayedTask) firstImpl;
                        if (nanoTime - delayedTask2.nanoTime >= 0 && enqueueImpl(delayedTask2)) {
                            threadSafeHeapNode = delayedTaskQueue.removeAtImpl(0);
                        } else {
                            threadSafeHeapNode = null;
                        }
                    }
                }
            } while (((DelayedTask) threadSafeHeapNode) != null);
        }
        AtomicRef atomicRef = this._queue;
        while (true) {
            Object obj = atomicRef.value;
            if (obj == null) {
                break;
            }
            if (obj instanceof LockFreeTaskQueueCore) {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
                if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                    runnable = (Runnable) removeFirstOrNull;
                    break;
                }
                this._queue.compareAndSet(obj, lockFreeTaskQueueCore.next());
            } else {
                if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                    break;
                }
                if (this._queue.compareAndSet(obj, null)) {
                    runnable = (Runnable) obj;
                    break;
                }
            }
        }
        if (runnable == null) {
            ArrayDeque arrayDeque = this.unconfinedQueue;
            if (arrayDeque == null || arrayDeque.isEmpty()) {
                j = Long.MAX_VALUE;
            } else {
                j = 0;
            }
            if (j == 0) {
                return 0L;
            }
            Object obj2 = this._queue.value;
            if (obj2 != null) {
                if (obj2 instanceof LockFreeTaskQueueCore) {
                    if (!((LockFreeTaskQueueCore) obj2).isEmpty()) {
                        return 0L;
                    }
                } else {
                    if (obj2 != EventLoop_commonKt.CLOSED_EMPTY) {
                        return 0L;
                    }
                    return Long.MAX_VALUE;
                }
            }
            DelayedTaskQueue delayedTaskQueue2 = (DelayedTaskQueue) this._delayed.value;
            if (delayedTaskQueue2 != null && (delayedTask = (DelayedTask) delayedTaskQueue2.peek()) != null) {
                return OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(delayedTask.nanoTime - System.nanoTime(), 0L);
            }
            return Long.MAX_VALUE;
        }
        runnable.run();
        return 0L;
    }

    public final void schedule(long j, DelayedTask delayedTask) {
        if (!isCompleted()) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.value;
            DelayedTask delayedTask2 = null;
            if (delayedTaskQueue == null) {
                this._delayed.compareAndSet(null, new DelayedTaskQueue(j));
                Object obj = this._delayed.value;
                obj.getClass();
                delayedTaskQueue = (DelayedTaskQueue) obj;
            }
            int scheduleTask = delayedTask.scheduleTask(j, delayedTaskQueue, this);
            if (scheduleTask != 0) {
                if (scheduleTask != 1) {
                    return;
                }
            } else {
                DelayedTaskQueue delayedTaskQueue2 = (DelayedTaskQueue) this._delayed.value;
                if (delayedTaskQueue2 != null) {
                    delayedTask2 = (DelayedTask) delayedTaskQueue2.peek();
                }
                if (delayedTask2 == delayedTask) {
                    unpark();
                    return;
                }
                return;
            }
        }
        reschedule(j, delayedTask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DisposableHandle scheduleInvokeOnTimeout(long j, Runnable runnable) {
        long delayToNanos = EventLoop_commonKt.delayToNanos(j);
        if (delayToNanos < 4611686018427387903L) {
            long nanoTime = System.nanoTime();
            DelayedRunnableTask delayedRunnableTask = new DelayedRunnableTask(delayToNanos + nanoTime, runnable);
            schedule(nanoTime, delayedRunnableTask);
            return delayedRunnableTask;
        }
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.Delay
    public final void scheduleResumeAfterDelay$ar$class_merging$3a912d96_0(long j, CancellableContinuationImpl cancellableContinuationImpl) {
        long delayToNanos = EventLoop_commonKt.delayToNanos(j);
        if (delayToNanos < 4611686018427387903L) {
            long nanoTime = System.nanoTime();
            DelayedResumeTask delayedResumeTask = new DelayedResumeTask(delayToNanos + nanoTime, cancellableContinuationImpl);
            schedule(nanoTime, delayedResumeTask);
            OnDeviceTranslationLogEvent.disposeOnCancellation$ar$class_merging(cancellableContinuationImpl, delayedResumeTask);
        }
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadSafeHeapNode threadSafeHeapNode;
        ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
        ThreadLocalEventLoop.ref.set(null);
        this._isCompleted.setValue$ar$ds();
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        while (true) {
            Object obj = this._queue.value;
            if (obj == null) {
                if (this._queue.compareAndSet(null, EventLoop_commonKt.CLOSED_EMPTY)) {
                    break;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                ((LockFreeTaskQueueCore) obj).close();
                break;
            } else {
                if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                    break;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) obj);
                if (this._queue.compareAndSet(obj, lockFreeTaskQueueCore)) {
                    break;
                }
            }
        }
        do {
        } while (processNextEvent() <= 0);
        long nanoTime = System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.value;
            if (delayedTaskQueue != null) {
                synchronized (delayedTaskQueue) {
                    if (delayedTaskQueue.getSize() > 0) {
                        threadSafeHeapNode = delayedTaskQueue.removeAtImpl(0);
                    } else {
                        threadSafeHeapNode = null;
                    }
                }
                DelayedTask delayedTask = (DelayedTask) threadSafeHeapNode;
                if (delayedTask != null) {
                    reschedule(nanoTime, delayedTask);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}
