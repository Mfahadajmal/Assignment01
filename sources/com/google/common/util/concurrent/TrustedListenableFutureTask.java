package com.google.common.util.concurrent;

import _COROUTINE._BOUNDARY;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrustedListenableFutureTask extends FluentFuture$TrustedFuture implements RunnableFuture {
    private volatile InterruptibleTask task;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TrustedFutureInterruptibleAsyncTask extends InterruptibleTask {
        private final AsyncCallable callable;

        public TrustedFutureInterruptibleAsyncTask(AsyncCallable asyncCallable) {
            this.callable = asyncCallable;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblyFailure(Throwable th) {
            TrustedListenableFutureTask.this.setException(th);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final /* bridge */ /* synthetic */ void afterRanInterruptiblySuccess(Object obj) {
            TrustedListenableFutureTask.this.setFuture((ListenableFuture) obj);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final /* bridge */ /* synthetic */ Object runInterruptibly() {
            ListenableFuture call = this.callable.call();
            call.getClass();
            return call;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final String toPendingString() {
            return this.callable.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TrustedFutureInterruptibleTask extends InterruptibleTask {
        private final Callable callable;

        public TrustedFutureInterruptibleTask(Callable callable) {
            callable.getClass();
            this.callable = callable;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblyFailure(Throwable th) {
            TrustedListenableFutureTask.this.setException(th);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblySuccess(Object obj) {
            TrustedListenableFutureTask.this.set(obj);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final Object runInterruptibly() {
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final String toPendingString() {
            return this.callable.toString();
        }
    }

    public TrustedListenableFutureTask(AsyncCallable asyncCallable) {
        this.task = new TrustedFutureInterruptibleAsyncTask(asyncCallable);
    }

    public static TrustedListenableFutureTask create(Runnable runnable, Object obj) {
        return new TrustedListenableFutureTask(Executors.callable(runnable, obj));
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected final void afterDone() {
        InterruptibleTask interruptibleTask;
        if (wasInterrupted() && (interruptibleTask = this.task) != null) {
            interruptibleTask.interruptTask();
        }
        this.task = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask != null) {
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(interruptibleTask, "task=[", "]");
        }
        return super.pendingToString();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask != null) {
            interruptibleTask.run();
        }
        this.task = null;
    }

    public TrustedListenableFutureTask(Callable callable) {
        this.task = new TrustedFutureInterruptibleTask(callable);
    }
}
