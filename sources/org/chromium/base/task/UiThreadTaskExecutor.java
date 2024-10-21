package org.chromium.base.task;

import android.os.Handler;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UiThreadTaskExecutor implements TaskExecutor {
    private final SingleThreadTaskRunner mBestEffortTaskRunner;
    private final SingleThreadTaskRunner mUserVisibleTaskRunner;

    public UiThreadTaskExecutor(Handler handler) {
        this.mBestEffortTaskRunner = new SingleThreadTaskRunnerImpl(handler, 6);
        this.mUserVisibleTaskRunner = new SingleThreadTaskRunnerImpl(handler, 7);
    }

    @Override // org.chromium.base.task.TaskExecutor
    public final boolean canRunTaskImmediately$ar$ds() {
        return createSingleThreadTaskRunner(7).belongsToCurrentThread();
    }

    public final SingleThreadTaskRunner createSingleThreadTaskRunner(int i) {
        if (i == 6) {
            return this.mBestEffortTaskRunner;
        }
        if (i == 7) {
            return this.mUserVisibleTaskRunner;
        }
        throw new RuntimeException();
    }

    @Override // org.chromium.base.task.TaskExecutor
    public final void postDelayedTask$ar$ds(int i, Runnable runnable) {
        createSingleThreadTaskRunner(i).postDelayedTask$ar$ds$ac3fd9c9_0(runnable);
    }
}
