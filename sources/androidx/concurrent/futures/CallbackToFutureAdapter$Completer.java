package androidx.concurrent.futures;

import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallbackToFutureAdapter$Completer {
    public boolean attemptedSetting;
    public ResolvableFuture cancellationFuture = new ResolvableFuture();
    public CallbackToFutureAdapter$SafeFuture future;
    public Object tag;

    public final void addCancellationListener(Runnable runnable, Executor executor) {
        ResolvableFuture resolvableFuture = this.cancellationFuture;
        if (resolvableFuture != null) {
            resolvableFuture.addListener(runnable, executor);
        }
    }

    protected final void finalize() {
        ResolvableFuture resolvableFuture;
        CallbackToFutureAdapter$SafeFuture callbackToFutureAdapter$SafeFuture = this.future;
        if (callbackToFutureAdapter$SafeFuture != null && !callbackToFutureAdapter$SafeFuture.isDone()) {
            Object obj = this.tag;
            Objects.toString(obj);
            final String concat = "The completer object was garbage collected - this future would otherwise never complete. The tag was: ".concat(String.valueOf(obj));
            callbackToFutureAdapter$SafeFuture.setException(new Throwable(concat) { // from class: androidx.concurrent.futures.CallbackToFutureAdapter$FutureGarbageCollectedException
                @Override // java.lang.Throwable
                public final synchronized Throwable fillInStackTrace() {
                    return this;
                }
            });
        }
        if (!this.attemptedSetting && (resolvableFuture = this.cancellationFuture) != null) {
            resolvableFuture.set(null);
        }
    }

    public final void set$ar$ds(Object obj) {
        this.attemptedSetting = true;
        CallbackToFutureAdapter$SafeFuture callbackToFutureAdapter$SafeFuture = this.future;
        if (callbackToFutureAdapter$SafeFuture != null && callbackToFutureAdapter$SafeFuture.delegate.set(obj)) {
            setCompletedNormally();
        }
    }

    public final void setCompletedNormally() {
        this.tag = null;
        this.future = null;
        this.cancellationFuture = null;
    }

    public final void setException$ar$ds(Throwable th) {
        this.attemptedSetting = true;
        CallbackToFutureAdapter$SafeFuture callbackToFutureAdapter$SafeFuture = this.future;
        if (callbackToFutureAdapter$SafeFuture != null && callbackToFutureAdapter$SafeFuture.setException(th)) {
            setCompletedNormally();
        }
    }
}
