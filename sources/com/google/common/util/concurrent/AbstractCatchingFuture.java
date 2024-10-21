package com.google.common.util.concurrent;

import _COROUTINE._BOUNDARY;
import com.google.common.base.Function;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractCatchingFuture extends FluentFuture$TrustedFuture implements Runnable {
    Class exceptionType;
    Object fallback;
    ListenableFuture inputFuture;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AsyncCatchingFuture extends AbstractCatchingFuture {
        public AsyncCatchingFuture(ListenableFuture listenableFuture, Class cls, AsyncFunction asyncFunction) {
            super(listenableFuture, cls, asyncFunction);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public final /* bridge */ /* synthetic */ Object doFallback(Object obj, Throwable th) {
            ListenableFuture apply = ((AsyncFunction) obj).apply(th);
            apply.getClass();
            return apply;
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public final /* synthetic */ void setResult(Object obj) {
            setFuture((ListenableFuture) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CatchingFuture extends AbstractCatchingFuture {
        public CatchingFuture(ListenableFuture listenableFuture, Class cls, Function function) {
            super(listenableFuture, cls, function);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public final /* bridge */ /* synthetic */ Object doFallback(Object obj, Throwable th) {
            return ((Function) obj).apply(th);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public final void setResult(Object obj) {
            set(obj);
        }
    }

    public AbstractCatchingFuture(ListenableFuture listenableFuture, Class cls, Object obj) {
        listenableFuture.getClass();
        this.inputFuture = listenableFuture;
        this.exceptionType = cls;
        this.fallback = obj;
    }

    public static ListenableFuture create(ListenableFuture listenableFuture, Class cls, Function function, Executor executor) {
        CatchingFuture catchingFuture = new CatchingFuture(listenableFuture, cls, function);
        listenableFuture.addListener(catchingFuture, ContextDataProvider.rejectionPropagatingExecutor(executor, catchingFuture));
        return catchingFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    public abstract Object doFallback(Object obj, Throwable th);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        String str;
        ListenableFuture listenableFuture = this.inputFuture;
        Class cls = this.exceptionType;
        Object obj = this.fallback;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            str = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(listenableFuture, "inputFuture=[", "], ");
        } else {
            str = "";
        }
        if (cls != null && obj != null) {
            return str + "exceptionType=[" + cls.toString() + "], fallback=[" + obj.toString() + "]";
        }
        if (pendingToString != null) {
            return String.valueOf(str).concat(pendingToString);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007b  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r9 = this;
            com.google.common.util.concurrent.ListenableFuture r0 = r9.inputFuture
            java.lang.Class r1 = r9.exceptionType
            java.lang.Object r2 = r9.fallback
            r3 = 1
            r4 = 0
            if (r0 != 0) goto Lc
            r5 = r3
            goto Ld
        Lc:
            r5 = r4
        Ld:
            if (r1 != 0) goto L11
            r6 = r3
            goto L12
        L11:
            r6 = r4
        L12:
            r5 = r5 | r6
            if (r2 != 0) goto L16
            goto L17
        L16:
            r3 = r4
        L17:
            r3 = r3 | r5
            if (r3 != 0) goto La2
            boolean r3 = r9.isCancelled()
            if (r3 == 0) goto L22
            goto La2
        L22:
            r3 = 0
            r9.inputFuture = r3
            boolean r4 = r0 instanceof com.google.common.util.concurrent.internal.InternalFutureFailureAccess     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            if (r4 == 0) goto L31
            r4 = r0
            com.google.common.util.concurrent.internal.InternalFutureFailureAccess r4 = (com.google.common.util.concurrent.internal.InternalFutureFailureAccess) r4     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            java.lang.Throwable r4 = r4.tryInternalFastPathGetFailure()     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            goto L32
        L31:
            r4 = r3
        L32:
            if (r4 != 0) goto L3a
            java.lang.Object r5 = com.google.common.flogger.context.ContextDataProvider.getDone(r0)     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            goto L75
        L39:
            r4 = move-exception
        L3a:
            r5 = r3
            goto L75
        L3c:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L73
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Future type "
            r7.<init>(r8)
            r7.append(r6)
            java.lang.String r6 = " threw "
            r7.append(r6)
            r7.append(r4)
            java.lang.String r4 = " without a cause"
            r7.append(r4)
            java.lang.String r4 = r7.toString()
            r5.<init>(r4)
        L73:
            r4 = r5
            goto L3a
        L75:
            if (r4 != 0) goto L7b
            r9.set(r5)
            return
        L7b:
            boolean r1 = r1.isInstance(r4)
            if (r1 == 0) goto L9f
            java.lang.Object r0 = r9.doFallback(r2, r4)     // Catch: java.lang.Throwable -> L8d
            r9.exceptionType = r3
            r9.fallback = r3
            r9.setResult(r0)
            return
        L8d:
            r0 = move-exception
            com.google.common.flogger.context.ContextDataProvider.restoreInterruptIfIsInterruptedException(r0)     // Catch: java.lang.Throwable -> L99
            r9.setException(r0)     // Catch: java.lang.Throwable -> L99
            r9.exceptionType = r3
            r9.fallback = r3
            return
        L99:
            r0 = move-exception
            r9.exceptionType = r3
            r9.fallback = r3
            throw r0
        L9f:
            r9.setFuture(r0)
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractCatchingFuture.run():void");
    }

    public abstract void setResult(Object obj);

    public static ListenableFuture create(ListenableFuture listenableFuture, Class cls, AsyncFunction asyncFunction, Executor executor) {
        AsyncCatchingFuture asyncCatchingFuture = new AsyncCatchingFuture(listenableFuture, cls, asyncFunction);
        listenableFuture.addListener(asyncCatchingFuture, ContextDataProvider.rejectionPropagatingExecutor(executor, asyncCatchingFuture));
        return asyncCatchingFuture;
    }
}
