package com.google.mlkit.logging.schema;

import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatcherExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcherImpl;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RemoteConfigLogEvent {
    public static /* synthetic */ void cancel$default$ar$ds(Job job) {
        job.cancel(null);
    }

    public static final CoroutineDispatcher from(Executor executor) {
        DispatcherExecutor dispatcherExecutor;
        if (executor instanceof DispatcherExecutor) {
            dispatcherExecutor = (DispatcherExecutor) executor;
        } else {
            dispatcherExecutor = null;
        }
        if (dispatcherExecutor == null) {
            return new ExecutorCoroutineDispatcherImpl(executor);
        }
        throw null;
    }
}
