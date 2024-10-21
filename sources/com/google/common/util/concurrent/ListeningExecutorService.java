package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ListeningExecutorService extends ExecutorService {
    ListenableFuture submit(Runnable runnable);

    ListenableFuture submit(Callable callable);
}
