package org.chromium.base.task;

import J.N;
import android.util.Pair;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TaskRunnerImpl implements TaskRunner {
    private boolean mDidOneTimeInitialization;
    public volatile long mNativeTaskRunnerAndroid;
    private List mPreNativeDelayedTasks;
    public final Object mPreNativeTaskLock;
    public LinkedList mPreNativeTasks;
    protected final Runnable mRunPreNativeTaskClosure;
    private final int mTaskRunnerType;
    public final int mTaskTraits;
    public final String mTraceEvent;
    public static final ReferenceQueue sQueue = new ReferenceQueue();
    private static final Set sCleaners = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TaskRunnerCleaner extends WeakReference {
        final long mNativePtr;

        public TaskRunnerCleaner(TaskRunnerImpl taskRunnerImpl) {
            super(taskRunnerImpl, TaskRunnerImpl.sQueue);
            this.mNativePtr = taskRunnerImpl.mNativeTaskRunnerAndroid;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TaskRunnerImpl(int i, String str, int i2) {
        this.mRunPreNativeTaskClosure = new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 9, null);
        this.mPreNativeTaskLock = new Object();
        this.mTaskTraits = i;
        this.mTraceEvent = str.concat(".PreNativeTask.run");
        this.mTaskRunnerType = i2;
    }

    private static void destroyGarbageCollectedTaskRunners() {
        while (true) {
            TaskRunnerCleaner taskRunnerCleaner = (TaskRunnerCleaner) sQueue.poll();
            if (taskRunnerCleaner == null) {
                return;
            }
            N.MERCiIV8(taskRunnerCleaner.mNativePtr);
            Set set = sCleaners;
            synchronized (set) {
                set.remove(taskRunnerCleaner);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void initNativeTaskRunner() {
        long M5_IQXaH = N.M5_IQXaH(this.mTaskRunnerType, this.mTaskTraits);
        synchronized (this.mPreNativeTaskLock) {
            LinkedList linkedList = this.mPreNativeTasks;
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    Runnable runnable = (Runnable) it.next();
                    N.MGnQU$47(M5_IQXaH, runnable, 0L, runnable.getClass().getName());
                }
                this.mPreNativeTasks = null;
            }
            List<Pair> list = this.mPreNativeDelayedTasks;
            if (list != null) {
                for (Pair pair : list) {
                    N.MGnQU$47(M5_IQXaH, (Runnable) pair.first, ((Long) pair.second).longValue(), pair.getClass().getName());
                }
                this.mPreNativeDelayedTasks = null;
            }
            this.mNativeTaskRunnerAndroid = M5_IQXaH;
        }
        Set set = sCleaners;
        synchronized (set) {
            set.add(new TaskRunnerCleaner(this));
        }
        destroyGarbageCollectedTaskRunners();
    }

    public final void oneTimeInitialization() {
        if (this.mDidOneTimeInitialization) {
            return;
        }
        this.mDidOneTimeInitialization = true;
        synchronized (PostTask.sPreNativeTaskRunnerLock) {
            List list = PostTask.sPreNativeTaskRunners;
            if (list == null) {
                initNativeTaskRunner();
                return;
            }
            list.add(this);
            this.mPreNativeTasks = new LinkedList();
            this.mPreNativeDelayedTasks = new ArrayList();
        }
    }

    @Override // org.chromium.base.task.TaskRunner
    public final void postDelayedTask$ar$ds$ac3fd9c9_0(Runnable runnable) {
        if (this.mNativeTaskRunnerAndroid != 0) {
            N.MGnQU$47(this.mNativeTaskRunnerAndroid, runnable, 0L, runnable.getClass().getName());
            return;
        }
        synchronized (this.mPreNativeTaskLock) {
            oneTimeInitialization();
            if (this.mNativeTaskRunnerAndroid != 0) {
                N.MGnQU$47(this.mNativeTaskRunnerAndroid, runnable, 0L, runnable.getClass().getName());
            } else {
                this.mPreNativeTasks.add(runnable);
                schedulePreNativeTask();
            }
        }
    }

    protected void schedulePreNativeTask() {
        Executor executor = PostTask.sPrenativeThreadPoolExecutorForTesting;
        PostTask.sPrenativeThreadPoolExecutor.execute(this.mRunPreNativeTaskClosure);
    }

    public TaskRunnerImpl(int i) {
        this(i, "TaskRunnerImpl", 0);
        destroyGarbageCollectedTaskRunners();
    }
}
