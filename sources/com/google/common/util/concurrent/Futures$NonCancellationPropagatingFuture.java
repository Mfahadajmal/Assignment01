package com.google.common.util.concurrent;

import _COROUTINE._BOUNDARY;
import com.google.common.util.concurrent.AbstractFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Futures$NonCancellationPropagatingFuture extends AbstractFuture.TrustedFuture implements Runnable {
    private ListenableFuture delegate;

    public Futures$NonCancellationPropagatingFuture(ListenableFuture listenableFuture) {
        this.delegate = listenableFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected final void afterDone() {
        this.delegate = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        ListenableFuture listenableFuture = this.delegate;
        if (listenableFuture != null) {
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(listenableFuture, "delegate=[", "]");
        }
        return null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture listenableFuture = this.delegate;
        if (listenableFuture != null) {
            setFuture(listenableFuture);
        }
    }
}
