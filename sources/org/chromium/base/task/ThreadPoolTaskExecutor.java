package org.chromium.base.task;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThreadPoolTaskExecutor implements TaskExecutor {
    private final TaskRunner[] mTraitsToRunnerMap = new TaskRunner[6];

    public ThreadPoolTaskExecutor() {
        for (int i = 0; i < 6; i++) {
            this.mTraitsToRunnerMap[i] = new TaskRunnerImpl(i);
        }
    }

    @Override // org.chromium.base.task.TaskExecutor
    public final boolean canRunTaskImmediately$ar$ds() {
        return false;
    }

    @Override // org.chromium.base.task.TaskExecutor
    public final void postDelayedTask$ar$ds(int i, Runnable runnable) {
        this.mTraitsToRunnerMap[i].postDelayedTask$ar$ds$ac3fd9c9_0(runnable);
    }
}
