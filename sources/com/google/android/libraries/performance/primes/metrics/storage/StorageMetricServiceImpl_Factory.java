package com.google.android.libraries.performance.primes.metrics.storage;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingService;
import com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl;
import com.google.android.libraries.performance.primes.sampling.PersistentRateLimiting_Factory;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory_Factory;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageMetricServiceImpl_Factory implements Factory {
    private final Provider configurationsProvider;
    private final Provider contextProvider;
    private final Provider executorProvider;
    private final Provider foregroundTrackerProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider persistentRateLimitingProvider;
    private final Provider samplingParametersProvider;
    private final /* synthetic */ int switching_field;

    public StorageMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, int i) {
        this.switching_field = i;
        this.metricRecorderFactoryProvider = provider;
        this.contextProvider = provider2;
        this.foregroundTrackerProvider = provider3;
        this.executorProvider = provider4;
        this.configurationsProvider = provider5;
        this.persistentRateLimitingProvider = provider6;
        this.samplingParametersProvider = provider7;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                Provider provider = this.contextProvider;
                MetricRecorderFactory metricRecorderFactory = ((MetricRecorderFactory_Factory) this.executorProvider).get();
                ListeningScheduledExecutorService listeningScheduledExecutorService = (ListeningScheduledExecutorService) provider.get();
                Provider provider2 = this.foregroundTrackerProvider;
                Provider provider3 = this.samplingParametersProvider;
                return new TraceMetricServiceImpl(metricRecorderFactory, listeningScheduledExecutorService, DoubleCheck.lazy(this.persistentRateLimitingProvider), DoubleCheck.lazy(provider3), this.metricRecorderFactoryProvider, ((ProbabilitySamplerFactory_Factory) provider2).get());
            }
            Object obj = ((InstanceFactory) this.configurationsProvider).instance;
            MetricRecorderFactory metricRecorderFactory2 = ((MetricRecorderFactory_Factory) this.metricRecorderFactoryProvider).get();
            Context context = (Context) obj;
            ListeningScheduledExecutorService listeningScheduledExecutorService2 = (ListeningScheduledExecutorService) this.foregroundTrackerProvider.get();
            Provider provider4 = this.contextProvider;
            return new CpuProfilingService(metricRecorderFactory2, context, listeningScheduledExecutorService2, DoubleCheck.lazy(this.executorProvider), this.persistentRateLimitingProvider, (SpannableUtils$NonCopyableTextSpan) provider4.get(), this.samplingParametersProvider);
        }
        Object obj2 = ((InstanceFactory) this.contextProvider).instance;
        return new StorageMetricServiceImpl(((MetricRecorderFactory_Factory) this.metricRecorderFactoryProvider).get(), (Context) obj2, (ForegroundTracker) this.foregroundTrackerProvider.get(), (Executor) this.executorProvider.get(), DoubleCheck.lazy(this.configurationsProvider), ((PersistentRateLimiting_Factory) this.persistentRateLimitingProvider).get(), this.samplingParametersProvider);
    }

    public StorageMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, int i, byte[] bArr) {
        this.switching_field = i;
        this.metricRecorderFactoryProvider = provider;
        this.configurationsProvider = provider2;
        this.foregroundTrackerProvider = provider3;
        this.executorProvider = provider4;
        this.persistentRateLimitingProvider = provider5;
        this.contextProvider = provider6;
        this.samplingParametersProvider = provider7;
    }

    public StorageMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, int i, char[] cArr) {
        this.switching_field = i;
        this.executorProvider = provider;
        this.contextProvider = provider2;
        this.persistentRateLimitingProvider = provider3;
        this.samplingParametersProvider = provider4;
        this.metricRecorderFactoryProvider = provider5;
        this.configurationsProvider = provider6;
        this.foregroundTrackerProvider = provider7;
    }
}
