package com.google.common.util.concurrent;

import com.google.apps.tiktok.tracing.ExceptionTracer;
import com.google.apps.tiktok.tracing.Trace;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExecutionSequencer {
    private final AtomicReference ref = new AtomicReference(ImmediateFuture.NULL);
    public AggregatedOnDeviceTextDetectionLogEvent latestTaskQueue$ar$class_merging$ar$class_merging$ar$class_merging = new AggregatedOnDeviceTextDetectionLogEvent();

    /* compiled from: PG */
    /* renamed from: com.google.common.util.concurrent.ExecutionSequencer$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements AsyncCallable {
        final /* synthetic */ Object ExecutionSequencer$2$ar$val$callable;
        final /* synthetic */ Object ExecutionSequencer$2$ar$val$taskExecutor;
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(Trace trace, AsyncCallable asyncCallable, int i) {
            this.switching_field = i;
            this.ExecutionSequencer$2$ar$val$callable = trace;
            this.ExecutionSequencer$2$ar$val$taskExecutor = asyncCallable;
        }

        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, com.google.common.util.concurrent.AsyncCallable] */
        /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object, com.google.apps.tiktok.tracing.Trace] */
        /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, com.google.common.util.concurrent.AsyncCallable] */
        @Override // com.google.common.util.concurrent.AsyncCallable
        public final ListenableFuture call() {
            if (this.switching_field != 0) {
                ?? r0 = this.ExecutionSequencer$2$ar$val$callable;
                Tracer.ThreadState currentThreadState = Tracer.getCurrentThreadState();
                Trace trace = Tracer.set(currentThreadState, r0);
                try {
                    ListenableFuture call = this.ExecutionSequencer$2$ar$val$taskExecutor.call();
                    Tracer.set(currentThreadState, trace);
                    call.getClass();
                    return call;
                } catch (Throwable th) {
                    try {
                        ExceptionTracer.reportException(th);
                        throw th;
                    } catch (Throwable th2) {
                        Tracer.set(currentThreadState, trace);
                        throw th2;
                    }
                }
            }
            if (!((TaskNonReentrantExecutor) this.ExecutionSequencer$2$ar$val$taskExecutor).compareAndSet(RunningState.NOT_RUN, RunningState.STARTED)) {
                return ContextDataProvider.immediateCancelledFuture();
            }
            return this.ExecutionSequencer$2$ar$val$callable.call();
        }

        public final String toString() {
            if (this.switching_field != 0) {
                return "propagating=[" + this.ExecutionSequencer$2$ar$val$taskExecutor + "]";
            }
            return this.ExecutionSequencer$2$ar$val$callable.toString();
        }

        public AnonymousClass2(TaskNonReentrantExecutor taskNonReentrantExecutor, AsyncCallable asyncCallable, int i) {
            this.switching_field = i;
            this.ExecutionSequencer$2$ar$val$taskExecutor = taskNonReentrantExecutor;
            this.ExecutionSequencer$2$ar$val$callable = asyncCallable;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum RunningState {
        NOT_RUN,
        CANCELLED,
        STARTED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TaskNonReentrantExecutor extends AtomicReference implements Executor, Runnable {
        Executor delegate;
        ExecutionSequencer sequencer;
        Thread submitting;
        Runnable task;

        public TaskNonReentrantExecutor(Executor executor, ExecutionSequencer executionSequencer) {
            super(RunningState.NOT_RUN);
            this.delegate = executor;
            this.sequencer = executionSequencer;
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            boolean z;
            if (get() == RunningState.CANCELLED) {
                this.delegate = null;
                this.sequencer = null;
                return;
            }
            this.submitting = Thread.currentThread();
            try {
                ExecutionSequencer executionSequencer = this.sequencer;
                executionSequencer.getClass();
                AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = executionSequencer.latestTaskQueue$ar$class_merging$ar$class_merging$ar$class_merging;
                if (aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey == this.submitting) {
                    this.sequencer = null;
                    if (aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats == null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ContextDataProvider.checkState(z);
                    aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = runnable;
                    Executor executor = this.delegate;
                    executor.getClass();
                    aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = executor;
                    this.delegate = null;
                } else {
                    Executor executor2 = this.delegate;
                    executor2.getClass();
                    this.delegate = null;
                    this.task = runnable;
                    executor2.execute(this);
                }
            } finally {
                this.submitting = null;
            }
        }

        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r3v0, types: [java.util.concurrent.Executor, java.lang.Object] */
        @Override // java.lang.Runnable
        public final void run() {
            ?? r3;
            Thread currentThread = Thread.currentThread();
            if (currentThread != this.submitting) {
                Runnable runnable = this.task;
                runnable.getClass();
                this.task = null;
                runnable.run();
                return;
            }
            AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent();
            aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = currentThread;
            ExecutionSequencer executionSequencer = this.sequencer;
            executionSequencer.getClass();
            executionSequencer.latestTaskQueue$ar$class_merging$ar$class_merging$ar$class_merging = aggregatedOnDeviceTextDetectionLogEvent;
            this.sequencer = null;
            try {
                Runnable runnable2 = this.task;
                runnable2.getClass();
                this.task = null;
                runnable2.run();
                while (true) {
                    ?? r0 = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats;
                    if (r0 == 0 || (r3 = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount) == 0) {
                        break;
                    }
                    aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = null;
                    aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = null;
                    r3.execute(r0);
                }
            } finally {
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$submitAsync$0(TrustedListenableFutureTask trustedListenableFutureTask, SettableFuture settableFuture, ListenableFuture listenableFuture, ListenableFuture listenableFuture2, TaskNonReentrantExecutor taskNonReentrantExecutor) {
        if (trustedListenableFutureTask.isDone()) {
            settableFuture.setFuture(listenableFuture);
        } else if (listenableFuture2.isCancelled() && taskNonReentrantExecutor.compareAndSet(RunningState.NOT_RUN, RunningState.CANCELLED)) {
            trustedListenableFutureTask.cancel(false);
        }
    }

    public final ListenableFuture submitAsync(AsyncCallable asyncCallable, Executor executor) {
        executor.getClass();
        final TaskNonReentrantExecutor taskNonReentrantExecutor = new TaskNonReentrantExecutor(executor, this);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(taskNonReentrantExecutor, asyncCallable, 0);
        final SettableFuture settableFuture = new SettableFuture();
        final ListenableFuture listenableFuture = (ListenableFuture) this.ref.getAndSet(settableFuture);
        final TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(anonymousClass2);
        listenableFuture.addListener(trustedListenableFutureTask, taskNonReentrantExecutor);
        final ListenableFuture nonCancellationPropagating = ContextDataProvider.nonCancellationPropagating(trustedListenableFutureTask);
        Runnable runnable = new Runnable() { // from class: com.google.common.util.concurrent.ExecutionSequencer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ExecutionSequencer.lambda$submitAsync$0(TrustedListenableFutureTask.this, settableFuture, listenableFuture, nonCancellationPropagating, taskNonReentrantExecutor);
            }
        };
        nonCancellationPropagating.addListener(runnable, DirectExecutor.INSTANCE);
        trustedListenableFutureTask.addListener(runnable, DirectExecutor.INSTANCE);
        return nonCancellationPropagating;
    }
}
