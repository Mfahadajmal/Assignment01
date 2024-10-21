package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import dagger.Lazy;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupMetricRecordingService {
    public StartupMetricRecordingService(MetricRecorderFactory metricRecorderFactory, Executor executor, Lazy lazy, Provider provider) {
        metricRecorderFactory.create(executor, lazy, provider);
    }
}
