package com.google.android.libraries.performance.primes.metrics.network;

import android.content.Context;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetricServiceImpl_Factory implements Factory {
    private final Provider configsProvider;
    private final Provider contextProvider;
    private final Provider deferredExecutorProvider;
    private final Provider executorServiceProvider;
    private final Provider foregroundTrackerProvider;
    private final Provider metricCollectorProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider processStatsCaptureProvider;
    private final Provider samplingParametersProvider;

    public NetworkMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9) {
        this.metricRecorderFactoryProvider = provider;
        this.contextProvider = provider2;
        this.foregroundTrackerProvider = provider3;
        this.executorServiceProvider = provider4;
        this.configsProvider = provider5;
        this.metricCollectorProvider = provider6;
        this.samplingParametersProvider = provider7;
        this.deferredExecutorProvider = provider8;
        this.processStatsCaptureProvider = provider9;
    }

    @Override // javax.inject.Provider
    public final /* bridge */ /* synthetic */ Object get() {
        Object obj = ((InstanceFactory) this.contextProvider).instance;
        return new NetworkMetricServiceImpl(((MetricRecorderFactory_Factory) this.metricRecorderFactoryProvider).get(), (Context) obj, (ForegroundTracker) this.foregroundTrackerProvider.get(), (ListeningScheduledExecutorService) this.executorServiceProvider.get(), DoubleCheck.lazy(this.configsProvider), DoubleCheck.lazy(this.metricCollectorProvider), this.samplingParametersProvider, (Executor) this.deferredExecutorProvider.get(), (ProcessStatsCapture) this.processStatsCaptureProvider.get());
    }
}
