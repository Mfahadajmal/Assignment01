package com.google.android.libraries.performance.primes.metrics.battery;

import android.content.Context;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetricServiceImpl_Factory implements Factory {
    private final Provider appLifecycleMonitorProvider;
    private final Provider applicationContextProvider;
    private final Provider batteryCaptureProvider;
    private final Provider configsProvider;
    private final Provider deferredExecutorProvider;
    private final Provider eagerExecutorProvider;
    private final Provider foregroundTrackerProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider samplingParametersProvider;
    private final Provider storageProvider;

    public BatteryMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10) {
        this.metricRecorderFactoryProvider = provider;
        this.applicationContextProvider = provider2;
        this.appLifecycleMonitorProvider = provider3;
        this.foregroundTrackerProvider = provider4;
        this.eagerExecutorProvider = provider5;
        this.configsProvider = provider6;
        this.storageProvider = provider7;
        this.batteryCaptureProvider = provider8;
        this.samplingParametersProvider = provider9;
        this.deferredExecutorProvider = provider10;
    }

    @Override // javax.inject.Provider
    public final /* bridge */ /* synthetic */ Object get() {
        Object obj = ((InstanceFactory) this.applicationContextProvider).instance;
        return new BatteryMetricServiceImpl(((MetricRecorderFactory_Factory) this.metricRecorderFactoryProvider).get(), (Context) obj, (AppLifecycleMonitor) this.appLifecycleMonitorProvider.get(), (ForegroundTracker) this.foregroundTrackerProvider.get(), (ListeningScheduledExecutorService) this.eagerExecutorProvider.get(), DoubleCheck.lazy(this.configsProvider), ((StatsStorage_Factory) this.storageProvider).get(), this.batteryCaptureProvider, this.samplingParametersProvider, (Executor) this.deferredExecutorProvider.get());
    }
}
