package com.google.common.util.concurrent;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Futures$CancellationPropagater implements Runnable {
    public Object Futures$CancellationPropagater$ar$from;
    public Object Futures$CancellationPropagater$ar$to;
    private final /* synthetic */ int switching_field;

    public Futures$CancellationPropagater(ListenableFuture listenableFuture, Future future, int i) {
        this.switching_field = i;
        this.Futures$CancellationPropagater$ar$from = listenableFuture;
        this.Futures$CancellationPropagater$ar$to = future;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.concurrent.Future, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        if (this.switching_field == 0) {
            ContextDataProvider.maybePropagateCancellation(this.Futures$CancellationPropagater$ar$from, this.Futures$CancellationPropagater$ar$to);
        }
        this.Futures$CancellationPropagater$ar$from = null;
        this.Futures$CancellationPropagater$ar$to = null;
    }

    public Futures$CancellationPropagater(AsyncCallable asyncCallable, Executor executor, int i) {
        this.switching_field = i;
        this.Futures$CancellationPropagater$ar$from = asyncCallable;
        executor.getClass();
        this.Futures$CancellationPropagater$ar$to = executor;
    }
}
