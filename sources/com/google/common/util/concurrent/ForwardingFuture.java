package com.google.common.util.concurrent;

import com.google.common.collect.ForwardingObject;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingFuture extends ForwardingObject implements Future, ListenableFuture {
    protected ForwardingFuture() {
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        delegate().addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return delegate().cancel(z);
    }

    @Override // com.google.common.collect.ForwardingObject
    protected abstract ListenableFuture delegate();

    @Override // com.google.common.collect.ForwardingObject
    protected /* bridge */ /* synthetic */ Object delegate() {
        throw null;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return delegate().get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return delegate().isCancelled();
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return delegate().isDone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ForwardingFuture(byte[] bArr) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    public /* bridge */ /* synthetic */ Future delegate() {
        throw null;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        return delegate().get(j, timeUnit);
    }
}
