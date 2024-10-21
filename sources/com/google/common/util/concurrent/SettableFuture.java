package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SettableFuture extends AbstractFuture.TrustedFuture {
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final boolean set(Object obj) {
        return super.set(obj);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final boolean setException(Throwable th) {
        return super.setException(th);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final boolean setFuture(ListenableFuture listenableFuture) {
        return super.setFuture(listenableFuture);
    }
}
