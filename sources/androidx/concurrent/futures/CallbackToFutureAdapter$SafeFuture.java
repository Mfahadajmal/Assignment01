package androidx.concurrent.futures;

import androidx.concurrent.futures.AbstractResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallbackToFutureAdapter$SafeFuture implements ListenableFuture {
    final WeakReference completerWeakReference;
    public final AbstractResolvableFuture delegate = new AbstractResolvableFuture() { // from class: androidx.concurrent.futures.CallbackToFutureAdapter$SafeFuture.1
        @Override // androidx.concurrent.futures.AbstractResolvableFuture
        protected final String pendingToString() {
            CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer = (CallbackToFutureAdapter$Completer) CallbackToFutureAdapter$SafeFuture.this.completerWeakReference.get();
            if (callbackToFutureAdapter$Completer == null) {
                return "Completer object has been garbage collected, future will fail soon";
            }
            return "tag=[" + callbackToFutureAdapter$Completer.tag + "]";
        }
    };

    public CallbackToFutureAdapter$SafeFuture(CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer) {
        this.completerWeakReference = new WeakReference(callbackToFutureAdapter$Completer);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        this.delegate.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer = (CallbackToFutureAdapter$Completer) this.completerWeakReference.get();
        boolean cancel = this.delegate.cancel(z);
        if (cancel && callbackToFutureAdapter$Completer != null) {
            callbackToFutureAdapter$Completer.tag = null;
            callbackToFutureAdapter$Completer.future = null;
            callbackToFutureAdapter$Completer.cancellationFuture.set(null);
            return true;
        }
        return cancel;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.delegate.get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.delegate.isDone();
    }

    public final boolean setException(Throwable th) {
        th.getClass();
        AbstractResolvableFuture.Failure failure = new AbstractResolvableFuture.Failure(th);
        AbstractResolvableFuture.AtomicHelper atomicHelper = AbstractResolvableFuture.ATOMIC_HELPER;
        AbstractResolvableFuture abstractResolvableFuture = this.delegate;
        if (atomicHelper.casValue(abstractResolvableFuture, null, failure)) {
            AbstractResolvableFuture.complete(abstractResolvableFuture);
            return true;
        }
        return false;
    }

    public final String toString() {
        return this.delegate.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        return this.delegate.get(j, timeUnit);
    }
}
