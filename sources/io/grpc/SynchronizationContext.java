package io.grpc;

import _COROUTINE._BOUNDARY;
import com.google.apps.tiktok.tracing.Trace;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.apps.tiktok.tracing.WeakTraceReference;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.util.MultiChildLoadBalancer;
import j$.util.concurrent.ConcurrentLinkedQueue;
import java.lang.Thread;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SynchronizationContext implements Executor {
    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final Queue queue = new ConcurrentLinkedQueue();
    private final AtomicReference drainingThread = new AtomicReference();

    /* compiled from: PG */
    /* renamed from: io.grpc.SynchronizationContext$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Runnable {
        final /* synthetic */ Object SynchronizationContext$1$ar$this$0;
        final /* synthetic */ Object SynchronizationContext$1$ar$val$runnable;
        final /* synthetic */ Object SynchronizationContext$1$ar$val$task;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(SynchronizationContext synchronizationContext, ManagedRunnable managedRunnable, Runnable runnable, int i) {
            this.switching_field = i;
            this.SynchronizationContext$1$ar$val$runnable = managedRunnable;
            this.SynchronizationContext$1$ar$val$task = runnable;
            this.SynchronizationContext$1$ar$this$0 = synchronizationContext;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object, com.google.apps.tiktok.tracing.Trace] */
        /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.lang.Runnable] */
        @Override // java.lang.Runnable
        public final void run() {
            if (this.switching_field != 0) {
                if (((WeakTraceReference) ((Ref$ObjectRef) this.SynchronizationContext$1$ar$val$task).element) == null) {
                    ?? r0 = this.SynchronizationContext$1$ar$val$runnable;
                    ?? r1 = this.SynchronizationContext$1$ar$this$0;
                    Trace trace = Tracer.set(Tracer.getCurrentThreadState(), r0);
                    try {
                        r1.run();
                    } finally {
                    }
                } else {
                    throw null;
                }
            } else {
                ((SynchronizationContext) this.SynchronizationContext$1$ar$this$0).execute(this.SynchronizationContext$1$ar$val$runnable);
            }
        }

        public final String toString() {
            if (this.switching_field != 0) {
                return "propagating=[" + this.SynchronizationContext$1$ar$this$0 + "]";
            }
            return String.valueOf(this.SynchronizationContext$1$ar$val$task.toString()).concat("(scheduled in SynchronizationContext)");
        }

        public AnonymousClass1(Ref$ObjectRef ref$ObjectRef, Trace trace, Runnable runnable, int i) {
            this.switching_field = i;
            this.SynchronizationContext$1$ar$val$task = ref$ObjectRef;
            this.SynchronizationContext$1$ar$val$runnable = trace;
            this.SynchronizationContext$1$ar$this$0 = runnable;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ManagedRunnable implements Runnable {
        public boolean hasStarted;
        public boolean isCancelled;
        final Runnable task;

        public ManagedRunnable(Runnable runnable) {
            this.task = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (!this.isCancelled) {
                this.hasStarted = true;
                this.task.run();
            }
        }
    }

    public SynchronizationContext(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public final void drain() {
        while (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(this.drainingThread, Thread.currentThread())) {
            while (true) {
                try {
                    Runnable runnable = (Runnable) this.queue.poll();
                    if (runnable == null) {
                        break;
                    }
                    try {
                        runnable.run();
                    } catch (Throwable th) {
                        this.uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
                    }
                } catch (Throwable th2) {
                    this.drainingThread.set(null);
                    throw th2;
                }
            }
            this.drainingThread.set(null);
            if (this.queue.isEmpty()) {
                return;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        executeLater(runnable);
        drain();
    }

    public final void executeLater(Runnable runnable) {
        runnable.getClass();
        this.queue.add(runnable);
    }

    public final MultiChildLoadBalancer.AcceptResolvedAddrRetVal schedule$ar$class_merging$ar$class_merging(Runnable runnable, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        ManagedRunnable managedRunnable = new ManagedRunnable(runnable);
        return new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(managedRunnable, (ScheduledFuture) scheduledExecutorService.schedule(new AnonymousClass1(this, managedRunnable, runnable, 0), j, timeUnit));
    }

    public final void throwIfNotInThisSynchronizationContext() {
        boolean z;
        if (Thread.currentThread() == this.drainingThread.get()) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Not called from the SynchronizationContext");
    }
}
