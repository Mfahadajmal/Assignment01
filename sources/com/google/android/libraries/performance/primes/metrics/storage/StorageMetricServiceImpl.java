package com.google.android.libraries.performance.primes.metrics.storage;

import android.content.Context;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.foreground.ForegroundListener;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.sampling.PersistentRateLimiting;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncCallable;
import dagger.Lazy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageMetricServiceImpl extends BatteryMetricService implements ForegroundListener, MetricService {
    public static final long CONSIDER_RECENT_DURATION_MS = TimeUnit.HOURS.toMillis(12);
    public final Lazy configurationsProvider;
    public final Context context;
    private final Executor executor;
    private final ForegroundTracker foregroundTracker;
    public final MetricRecorder metricRecorder;
    public final PersistentRateLimiting persistentRateLimiting;

    public StorageMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, ForegroundTracker foregroundTracker, Executor executor, Lazy lazy, PersistentRateLimiting persistentRateLimiting, Provider provider) {
        super((byte[]) null);
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider);
        this.executor = executor;
        this.context = context;
        this.configurationsProvider = lazy;
        this.persistentRateLimiting = persistentRateLimiting;
        this.foregroundTracker = foregroundTracker;
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public final void onAppToBackground(NoPiiString noPiiString) {
        this.foregroundTracker.unregister(this);
        ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.storage.StorageMetricServiceImpl$$ExternalSyntheticLambda0
            /* JADX WARN: Code restructure failed: missing block: B:35:0x01ce, code lost:
            
                if (r2.putLong("primes.packageMetric.lastSendTime", r7).commit() == false) goto L33;
             */
            @Override // com.google.common.util.concurrent.AsyncCallable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final com.google.common.util.concurrent.ListenableFuture call() {
                /*
                    Method dump skipped, instructions count: 509
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.storage.StorageMetricServiceImpl$$ExternalSyntheticLambda0.call():com.google.common.util.concurrent.ListenableFuture");
            }
        }, this.executor);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final void onApplicationStartup() {
        this.foregroundTracker.register(this);
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public final /* synthetic */ void onAppToForeground(NoPiiString noPiiString) {
    }
}
