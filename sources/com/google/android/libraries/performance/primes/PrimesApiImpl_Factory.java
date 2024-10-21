package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.initialization.PrimesInitializerImpl_Factory;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesApiImpl_Factory implements Factory {
    private final Provider batteryMetricServiceProvider;
    private final Provider crashMetricServiceProvider;
    private final Provider customDurationMetricServiceProvider;
    private final Provider debugMemoryMetricServiceProvider;
    private final Provider executorServiceProvider;
    private final Provider initializerProvider;
    private final Provider jankMetricServiceProvider;
    private final Provider memoryDiffMetricServiceProvider;
    private final Provider memoryMetricServiceProvider;
    private final Provider memoryMetricServiceUnsafeStringProvider;
    private final Provider metricServicesProvider;
    private final Provider metricTransmittersProvider;
    private final Provider networkConfigurationsProvider;
    private final Provider networkMetricServiceProvider;
    private final Provider shutdownProvider;
    private final Provider startupMetricServiceProvider;
    private final Provider storageMetricServiceProvider;
    private final Provider timerMetricServiceProvider;
    private final Provider traceMetricServiceProvider;
    private final Provider unifiedInitProvider;

    public PrimesApiImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, Provider provider14, Provider provider15, Provider provider16, Provider provider17, Provider provider18, Provider provider19, Provider provider20) {
        this.executorServiceProvider = provider;
        this.shutdownProvider = provider2;
        this.metricServicesProvider = provider3;
        this.metricTransmittersProvider = provider4;
        this.networkConfigurationsProvider = provider5;
        this.batteryMetricServiceProvider = provider6;
        this.crashMetricServiceProvider = provider7;
        this.jankMetricServiceProvider = provider8;
        this.memoryMetricServiceProvider = provider9;
        this.memoryMetricServiceUnsafeStringProvider = provider10;
        this.debugMemoryMetricServiceProvider = provider11;
        this.memoryDiffMetricServiceProvider = provider12;
        this.networkMetricServiceProvider = provider13;
        this.storageMetricServiceProvider = provider14;
        this.timerMetricServiceProvider = provider15;
        this.traceMetricServiceProvider = provider16;
        this.customDurationMetricServiceProvider = provider17;
        this.startupMetricServiceProvider = provider18;
        this.unifiedInitProvider = provider19;
        this.initializerProvider = provider20;
    }

    @Override // javax.inject.Provider
    public final PrimesApiImpl get() {
        return new PrimesApiImpl((Shutdown) this.shutdownProvider.get(), this.metricServicesProvider, this.crashMetricServiceProvider, this.memoryMetricServiceProvider, this.networkMetricServiceProvider, this.timerMetricServiceProvider, this.customDurationMetricServiceProvider, (Optional) ((InstanceFactory) this.unifiedInitProvider).instance, ((PrimesInitializerImpl_Factory) this.initializerProvider).get());
    }
}
