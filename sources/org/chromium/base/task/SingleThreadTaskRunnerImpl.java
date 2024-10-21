package org.chromium.base.task;

import J.N;
import android.os.Handler;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingleThreadTaskRunnerImpl extends TaskRunnerImpl implements SingleThreadTaskRunner {
    private final Handler mHandler;

    public SingleThreadTaskRunnerImpl(Handler handler, int i) {
        super(i, "SingleThreadTaskRunnerImpl", 2);
        this.mHandler = handler;
    }

    @Override // org.chromium.base.task.SingleThreadTaskRunner
    public final boolean belongsToCurrentThread() {
        Boolean valueOf;
        synchronized (this.mPreNativeTaskLock) {
            super.oneTimeInitialization();
        }
        if (this.mNativeTaskRunnerAndroid == 0) {
            valueOf = null;
        } else {
            valueOf = Boolean.valueOf(N.MdFi6sVQ(this.mNativeTaskRunnerAndroid));
        }
        if (valueOf != null) {
            return valueOf.booleanValue();
        }
        if (this.mHandler.getLooper().getThread() == Thread.currentThread()) {
            return true;
        }
        return false;
    }

    @Override // org.chromium.base.task.TaskRunnerImpl
    protected final void schedulePreNativeTask() {
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.post(this.mRunPreNativeTaskClosure);
    }
}
