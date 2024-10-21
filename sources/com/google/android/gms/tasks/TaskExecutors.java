package com.google.android.gms.tasks;

import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskExecutors {
    public static final Executor MAIN_THREAD = new HandlerExecutor(2, (byte[]) null);
    public static final Executor DIRECT = new Executor() { // from class: com.google.android.gms.tasks.TaskExecutors.1
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            runnable.run();
        }
    };
}
