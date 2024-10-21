package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.foreground.ForegroundStateCapture_Factory;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitor_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory_Factory;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactory;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetricServiceImpl_Factory implements Factory {
    private final Provider captureProvider;
    private final Provider clockProvider;
    private final Provider configsProvider;
    private final Provider contextProvider;
    private final Provider deferredExecutorProvider;
    private final Provider enableUnifiedInitProvider;
    private final Provider executorServiceProvider;
    private final Provider foregroundStateCaptureProvider;
    private final Provider metricMonitorProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider samplingParametersProvider;
    private final Provider shutdownProvider;
    private final /* synthetic */ int switching_field;

    public MemoryMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, int i) {
        this.switching_field = i;
        this.metricRecorderFactoryProvider = provider;
        this.clockProvider = provider2;
        this.contextProvider = provider3;
        this.metricMonitorProvider = provider4;
        this.executorServiceProvider = provider5;
        this.configsProvider = provider6;
        this.captureProvider = provider7;
        this.shutdownProvider = provider8;
        this.samplingParametersProvider = provider9;
        this.deferredExecutorProvider = provider10;
        this.enableUnifiedInitProvider = provider11;
        this.foregroundStateCaptureProvider = provider12;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        if (this.switching_field != 0) {
            Provider provider = this.executorServiceProvider;
            MetricRecorderFactory metricRecorderFactory = ((MetricRecorderFactory_Factory) this.metricMonitorProvider).get();
            Executor executor = (Executor) provider.get();
            Object obj = ((InstanceFactory) this.metricRecorderFactoryProvider).instance;
            Lazy lazy = DoubleCheck.lazy(this.clockProvider);
            Optional optional = (Optional) obj;
            AppLifecycleMonitor appLifecycleMonitor = (AppLifecycleMonitor) this.captureProvider.get();
            ForegroundTracker foregroundTracker = (ForegroundTracker) this.foregroundStateCaptureProvider.get();
            Provider provider2 = this.configsProvider;
            Provider provider3 = this.deferredExecutorProvider;
            AppLifecycleMonitor appLifecycleMonitor2 = ((ProbabilitySamplerFactory_Factory) this.samplingParametersProvider).get();
            ProtoDataStoreFactory protoDataStoreFactory = ((CrashLoopMonitor_Factory) provider3).get();
            PersistentStorage persistentStorage = ((CrashMetricFactory_Factory) provider2).get();
            Provider provider4 = this.contextProvider;
            return new CrashMetricServiceImpl(metricRecorderFactory, executor, lazy, optional, appLifecycleMonitor, foregroundTracker, appLifecycleMonitor2, this.shutdownProvider, this.enableUnifiedInitProvider, provider4, protoDataStoreFactory, persistentStorage);
        }
        Provider provider5 = this.clockProvider;
        MetricRecorderFactory metricRecorderFactory2 = ((MetricRecorderFactory_Factory) this.metricRecorderFactoryProvider).get();
        Provider provider6 = this.executorServiceProvider;
        Object obj2 = this.metricMonitorProvider.get();
        ListeningScheduledExecutorService listeningScheduledExecutorService = (ListeningScheduledExecutorService) provider6.get();
        Provider provider7 = this.shutdownProvider;
        Provider provider8 = this.captureProvider;
        Lazy lazy2 = DoubleCheck.lazy(this.configsProvider);
        Object obj3 = provider8.get();
        Shutdown shutdown = (Shutdown) provider7.get();
        Executor executor2 = (Executor) this.deferredExecutorProvider.get();
        Optional optional2 = (Optional) ((InstanceFactory) this.enableUnifiedInitProvider).instance;
        ((ForegroundStateCapture_Factory) this.foregroundStateCaptureProvider).get();
        return new MemoryMetricServiceImpl(metricRecorderFactory2, (MemoryMetricMonitor) obj2, listeningScheduledExecutorService, lazy2, (MemoryUsageCapture) obj3, shutdown, this.samplingParametersProvider, executor2, optional2);
    }

    public MemoryMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, int i, byte[] bArr) {
        this.switching_field = i;
        this.metricMonitorProvider = provider;
        this.executorServiceProvider = provider2;
        this.clockProvider = provider3;
        this.metricRecorderFactoryProvider = provider4;
        this.captureProvider = provider5;
        this.foregroundStateCaptureProvider = provider6;
        this.samplingParametersProvider = provider7;
        this.shutdownProvider = provider8;
        this.enableUnifiedInitProvider = provider9;
        this.contextProvider = provider10;
        this.deferredExecutorProvider = provider11;
        this.configsProvider = provider12;
    }
}
