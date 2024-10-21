package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.foreground.ForegroundListener;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetricMonitor {
    private static final Callback NOOP_CALLBACK = new Callback() { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor$$ExternalSyntheticLambda0
        @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor.Callback
        public final void onEvent$ar$edu(int i, String str) {
        }
    };
    public final Lazy configsProvider;
    public final Executor deferredExecutor;
    public ScheduledFuture futureMemoryBackgroundTask;
    public ScheduledFuture futureMemoryForegroundTask;
    public final AtomicBoolean started = new AtomicBoolean(false);
    public volatile Callback callback = NOOP_CALLBACK;

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements ForegroundListener {
        public final /* synthetic */ MemoryMetricMonitor this$0;
        final /* synthetic */ ListeningScheduledExecutorService val$executorService;

        public AnonymousClass1(MemoryMetricMonitor memoryMetricMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService) {
            this.val$executorService = listeningScheduledExecutorService;
            this.this$0 = memoryMetricMonitor;
        }

        @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
        public final void onAppToBackground(NoPiiString noPiiString) {
            this.this$0.callback.onEvent$ar$edu(MemoryMetric$MemoryUsageMetric.MemoryEventCode.APP_TO_BACKGROUND$ar$edu, noPiiString.value);
            this.this$0.cancelFutureTasksIfAny();
            this.this$0.futureMemoryBackgroundTask = this.val$executorService.schedule(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, noPiiString, 2, (byte[]) null), 10L, TimeUnit.SECONDS);
        }

        @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
        public final void onAppToForeground(NoPiiString noPiiString) {
            this.this$0.callback.onEvent$ar$edu(MemoryMetric$MemoryUsageMetric.MemoryEventCode.APP_TO_FOREGROUND$ar$edu, noPiiString.value);
            this.this$0.cancelFutureTasksIfAny();
            this.this$0.futureMemoryForegroundTask = this.val$executorService.schedule(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, noPiiString, 3, (byte[]) null), 10L, TimeUnit.SECONDS);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Callback {
        void onEvent$ar$edu(int i, String str);
    }

    public MemoryMetricMonitor(ForegroundTracker foregroundTracker, ListeningScheduledExecutorService listeningScheduledExecutorService, Executor executor, Lazy lazy) {
        this.configsProvider = lazy;
        this.deferredExecutor = executor;
        foregroundTracker.register(new AnonymousClass1(this, listeningScheduledExecutorService));
    }

    public final void cancelFutureTasksIfAny() {
        ScheduledFuture scheduledFuture = this.futureMemoryForegroundTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.futureMemoryForegroundTask = null;
        }
        ScheduledFuture scheduledFuture2 = this.futureMemoryBackgroundTask;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(true);
            this.futureMemoryBackgroundTask = null;
        }
    }
}
