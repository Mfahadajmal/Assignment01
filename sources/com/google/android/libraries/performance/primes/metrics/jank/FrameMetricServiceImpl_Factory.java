package com.google.android.libraries.performance.primes.metrics.jank;

import android.content.Context;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameMetricServiceImpl_Factory implements Factory {
    private final Provider activityLevelJankMonitorProvider;
    private final Provider appLifecycleMonitorProvider;
    private final Provider computeMaxAcceptedFrameTimeFromWindowProvider;
    private final Provider configsProvider;
    private final Provider contextProvider;
    private final Provider deferredExecutorProvider;
    private final Provider frameTimeHistogramProvider;
    private final Provider jankObserverFactoryProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider perfettoConfigurationsProvider;
    private final Provider perfettoTriggerProvider;
    private final Provider samplingParametersProvider;
    private final Provider windowTrackerFactoryProvider;

    public FrameMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13) {
        this.metricRecorderFactoryProvider = provider;
        this.contextProvider = provider2;
        this.appLifecycleMonitorProvider = provider3;
        this.configsProvider = provider4;
        this.activityLevelJankMonitorProvider = provider5;
        this.frameTimeHistogramProvider = provider6;
        this.samplingParametersProvider = provider7;
        this.deferredExecutorProvider = provider8;
        this.perfettoTriggerProvider = provider9;
        this.windowTrackerFactoryProvider = provider10;
        this.computeMaxAcceptedFrameTimeFromWindowProvider = provider11;
        this.perfettoConfigurationsProvider = provider12;
        this.jankObserverFactoryProvider = provider13;
    }

    @Override // javax.inject.Provider
    public final /* bridge */ /* synthetic */ Object get() {
        Object obj = ((InstanceFactory) this.contextProvider).instance;
        MetricRecorderFactory metricRecorderFactory = ((MetricRecorderFactory_Factory) this.metricRecorderFactoryProvider).get();
        Context context = (Context) obj;
        AppLifecycleMonitor appLifecycleMonitor = (AppLifecycleMonitor) this.appLifecycleMonitorProvider.get();
        Lazy lazy = DoubleCheck.lazy(this.configsProvider);
        Object obj2 = this.activityLevelJankMonitorProvider.get();
        Executor executor = (Executor) this.deferredExecutorProvider.get();
        Lazy lazy2 = DoubleCheck.lazy(this.perfettoTriggerProvider);
        WindowTrackerFactory windowTrackerFactory = ((WindowTrackerFactory_Factory) this.windowTrackerFactoryProvider).get();
        JankObserverFactory jankObserverFactory = ((JankObserverFactory_Factory) this.jankObserverFactoryProvider).get();
        return new FrameMetricServiceImpl(metricRecorderFactory, context, appLifecycleMonitor, lazy, (ActivityLevelJankMonitor) obj2, this.frameTimeHistogramProvider, this.samplingParametersProvider, executor, lazy2, windowTrackerFactory, this.computeMaxAcceptedFrameTimeFromWindowProvider, this.perfettoConfigurationsProvider, jankObserverFactory);
    }
}
