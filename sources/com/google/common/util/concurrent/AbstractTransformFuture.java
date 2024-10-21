package com.google.common.util.concurrent;

import _COROUTINE._BOUNDARY;
import com.google.common.base.Function;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractTransformFuture extends FluentFuture$TrustedFuture implements Runnable {
    Object function;
    ListenableFuture inputFuture;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AsyncTransformFuture extends AbstractTransformFuture {
        public AsyncTransformFuture(ListenableFuture listenableFuture, AsyncFunction asyncFunction) {
            super(listenableFuture, asyncFunction);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public final /* bridge */ /* synthetic */ Object doTransform(Object obj, Object obj2) {
            ListenableFuture apply = ((AsyncFunction) obj).apply(obj2);
            apply.getClass();
            return apply;
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public final /* synthetic */ void setResult(Object obj) {
            setFuture((ListenableFuture) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TransformFuture extends AbstractTransformFuture {
        public TransformFuture(ListenableFuture listenableFuture, Function function) {
            super(listenableFuture, function);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public final /* bridge */ /* synthetic */ Object doTransform(Object obj, Object obj2) {
            return ((Function) obj).apply(obj2);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public final void setResult(Object obj) {
            set(obj);
        }
    }

    public AbstractTransformFuture(ListenableFuture listenableFuture, Object obj) {
        listenableFuture.getClass();
        this.inputFuture = listenableFuture;
        this.function = obj;
    }

    public static ListenableFuture create(ListenableFuture listenableFuture, Function function, Executor executor) {
        TransformFuture transformFuture = new TransformFuture(listenableFuture, function);
        listenableFuture.addListener(transformFuture, ContextDataProvider.rejectionPropagatingExecutor(executor, transformFuture));
        return transformFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    public abstract Object doTransform(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        String str;
        ListenableFuture listenableFuture = this.inputFuture;
        Object obj = this.function;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            str = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(listenableFuture, "inputFuture=[", "], ");
        } else {
            str = "";
        }
        if (obj != null) {
            return str + "function=[" + obj.toString() + "]";
        }
        if (pendingToString != null) {
            return String.valueOf(str).concat(pendingToString);
        }
        return null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        ListenableFuture listenableFuture = this.inputFuture;
        Object obj = this.function;
        boolean isCancelled = isCancelled();
        boolean z2 = true;
        if (listenableFuture == null) {
            z = true;
        } else {
            z = false;
        }
        boolean z3 = isCancelled | z;
        if (obj != null) {
            z2 = false;
        }
        if (z3 | z2) {
            return;
        }
        this.inputFuture = null;
        if (listenableFuture.isCancelled()) {
            setFuture(listenableFuture);
            return;
        }
        try {
            try {
                Object doTransform = doTransform(obj, ContextDataProvider.getDone(listenableFuture));
                this.function = null;
                setResult(doTransform);
            } catch (Throwable th) {
                try {
                    ContextDataProvider.restoreInterruptIfIsInterruptedException(th);
                    setException(th);
                } finally {
                    this.function = null;
                }
            }
        } catch (Error e) {
            setException(e);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (ExecutionException e2) {
            setException(e2.getCause());
        } catch (Exception e3) {
            setException(e3);
        }
    }

    public abstract void setResult(Object obj);

    public static ListenableFuture create(ListenableFuture listenableFuture, AsyncFunction asyncFunction, Executor executor) {
        executor.getClass();
        AsyncTransformFuture asyncTransformFuture = new AsyncTransformFuture(listenableFuture, asyncFunction);
        listenableFuture.addListener(asyncTransformFuture, ContextDataProvider.rejectionPropagatingExecutor(executor, asyncTransformFuture));
        return asyncTransformFuture;
    }
}
