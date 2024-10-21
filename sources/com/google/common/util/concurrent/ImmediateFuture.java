package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImmediateFuture implements ListenableFuture {
    public static final ListenableFuture NULL = new ImmediateFuture(null);
    private static final LazyLogger log = new LazyLogger(ImmediateFuture.class);
    private final Object value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImmediateCancelledFuture extends AbstractFuture.TrustedFuture {
        public static final ImmediateCancelledFuture INSTANCE;

        static {
            ImmediateCancelledFuture immediateCancelledFuture;
            if (AbstractFuture.GENERATE_CANCELLATION_CAUSES) {
                immediateCancelledFuture = null;
            } else {
                immediateCancelledFuture = new ImmediateCancelledFuture();
            }
            INSTANCE = immediateCancelledFuture;
        }

        public ImmediateCancelledFuture() {
            cancel(false);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImmediateFailedFuture extends AbstractFuture.TrustedFuture {
        public ImmediateFailedFuture(Throwable th) {
            setException(th);
        }
    }

    public ImmediateFuture(Object obj) {
        this.value = obj;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        executor.getClass();
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            log.get().logp(Level.SEVERE, "com.google.common.util.concurrent.ImmediateFuture", "addListener", "RuntimeException while executing runnable " + runnable.toString() + " with executor " + executor.toString(), (Throwable) e);
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.value;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        Object obj = this.value;
        return super.toString() + "[status=SUCCESS, result=[" + String.valueOf(obj) + "]]";
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        timeUnit.getClass();
        return this.value;
    }
}
