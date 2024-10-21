package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MoreExecutors$ScheduledListeningDecorator extends AbstractListeningExecutorService implements ListeningScheduledExecutorService {
    final ScheduledExecutorService delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ListenableScheduledTask extends ForwardingListenableFuture$SimpleForwardingListenableFuture implements ScheduledFuture, ListenableFuture {
        private final ScheduledFuture scheduledDelegate;

        public ListenableScheduledTask(final ListenableFuture listenableFuture, ScheduledFuture scheduledFuture) {
            new ForwardingFuture(listenableFuture) { // from class: com.google.common.util.concurrent.ForwardingListenableFuture$SimpleForwardingListenableFuture
                private final ListenableFuture delegate;

                /* JADX INFO: Access modifiers changed from: protected */
                {
                    super(null);
                    this.delegate = listenableFuture;
                }

                @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
                protected final ListenableFuture delegate() {
                    return this.delegate;
                }

                @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
                protected final /* synthetic */ Object delegate() {
                    return this.delegate;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
                public final /* synthetic */ Future delegate() {
                    return this.delegate;
                }
            };
            this.scheduledDelegate = scheduledFuture;
        }

        @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
        public final boolean cancel(boolean z) {
            boolean cancel = delegate().cancel(z);
            if (cancel) {
                this.scheduledDelegate.cancel(z);
            }
            return cancel;
        }

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Delayed delayed) {
            return this.scheduledDelegate.compareTo(delayed);
        }

        @Override // java.util.concurrent.Delayed
        public final long getDelay(TimeUnit timeUnit) {
            return this.scheduledDelegate.getDelay(timeUnit);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class NeverSuccessfulListenableFutureTask extends AbstractFuture.TrustedFuture implements Runnable {
        private final Runnable delegate;

        public NeverSuccessfulListenableFutureTask(Runnable runnable) {
            runnable.getClass();
            this.delegate = runnable;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        protected final String pendingToString() {
            return "task=[" + this.delegate.toString() + "]";
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                this.delegate.run();
            } catch (Throwable th) {
                setException(th);
                throw th;
            }
        }
    }

    public MoreExecutors$ScheduledListeningDecorator(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        scheduledExecutorService.getClass();
        this.delegate = scheduledExecutorService;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    /* renamed from: schedule$ar$class_merging$49e426f9_0, reason: merged with bridge method [inline-methods] */
    public final ListenableScheduledTask schedule(Callable callable, long j, TimeUnit timeUnit) {
        TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(callable);
        return new ListenableScheduledTask(trustedListenableFutureTask, this.delegate.schedule(trustedListenableFutureTask, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    /* renamed from: schedule$ar$class_merging$7c20ace8_0, reason: merged with bridge method [inline-methods] */
    public final ListenableScheduledTask schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledExecutorService scheduledExecutorService = this.delegate;
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(runnable, null);
        return new ListenableScheduledTask(create, scheduledExecutorService.schedule(create, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        NeverSuccessfulListenableFutureTask neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(runnable);
        return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleAtFixedRate(neverSuccessfulListenableFutureTask, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        NeverSuccessfulListenableFutureTask neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(runnable);
        return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleWithFixedDelay(neverSuccessfulListenableFutureTask, j, j2, timeUnit));
    }
}
