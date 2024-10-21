package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableCollection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CombinedFuture extends AggregateFuture {
    private CombinedFutureInterruptibleTask task;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AsyncCallableInterruptibleTask extends CombinedFutureInterruptibleTask {
        private final AsyncCallable callable;

        public AsyncCallableInterruptibleTask(AsyncCallable asyncCallable, Executor executor) {
            super(executor);
            this.callable = asyncCallable;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final /* bridge */ /* synthetic */ Object runInterruptibly() {
            ListenableFuture call = this.callable.call();
            call.getClass();
            return call;
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        public final /* bridge */ /* synthetic */ void setValue(Object obj) {
            CombinedFuture.this.setFuture((ListenableFuture) obj);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final String toPendingString() {
            return this.callable.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CallableInterruptibleTask extends CombinedFutureInterruptibleTask {
        private final Callable callable;

        public CallableInterruptibleTask(Callable callable, Executor executor) {
            super(executor);
            this.callable = callable;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final Object runInterruptibly() {
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        public final void setValue(Object obj) {
            CombinedFuture.this.set(obj);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final String toPendingString() {
            return this.callable.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class CombinedFutureInterruptibleTask extends InterruptibleTask {
        private final Executor listenerExecutor;

        public CombinedFutureInterruptibleTask(Executor executor) {
            executor.getClass();
            this.listenerExecutor = executor;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblyFailure(Throwable th) {
            CombinedFuture.this.task = null;
            if (th instanceof ExecutionException) {
                CombinedFuture.this.setException(((ExecutionException) th).getCause());
            } else if (th instanceof CancellationException) {
                CombinedFuture.this.cancel(false);
            } else {
                CombinedFuture.this.setException(th);
            }
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblySuccess(Object obj) {
            CombinedFuture.this.task = null;
            setValue(obj);
        }

        final void execute() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e) {
                CombinedFuture.this.setException(e);
            }
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final boolean isDone() {
            return CombinedFuture.this.isDone();
        }

        public abstract void setValue(Object obj);
    }

    public CombinedFuture(ImmutableCollection immutableCollection, boolean z, Executor executor, AsyncCallable asyncCallable) {
        super(immutableCollection, z, false);
        this.task = new AsyncCallableInterruptibleTask(asyncCallable, executor);
        init();
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void handleAllCompleted() {
        CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
        if (combinedFutureInterruptibleTask != null) {
            combinedFutureInterruptibleTask.execute();
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected final void interruptTask() {
        CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
        if (combinedFutureInterruptibleTask != null) {
            combinedFutureInterruptibleTask.interruptTask();
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void releaseResources$ar$edu(int i) {
        super.releaseResources$ar$edu(i);
        if (i == 1) {
            this.task = null;
        }
    }

    public CombinedFuture(ImmutableCollection immutableCollection, boolean z, Executor executor, Callable callable) {
        super(immutableCollection, z, false);
        this.task = new CallableInterruptibleTask(callable, executor);
        init();
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void collectOneValue(int i, Object obj) {
    }
}
