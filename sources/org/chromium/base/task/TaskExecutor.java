package org.chromium.base.task;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TaskExecutor {
    boolean canRunTaskImmediately$ar$ds();

    void postDelayedTask$ar$ds(int i, Runnable runnable);
}
