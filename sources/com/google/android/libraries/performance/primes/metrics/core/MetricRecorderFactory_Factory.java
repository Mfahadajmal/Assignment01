package com.google.android.libraries.performance.primes.metrics.core;

import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricRecorderFactory_Factory implements Factory {
    private final Provider activeTraceProvider;
    private final Provider enableActiveTraceCollectionForCrashProvider;
    private final Provider enableSafeFormatArgsAsStringsProvider;
    private final Provider globalConfigurationsProvider;
    private final Provider interactionContextProvider;
    private final Provider metricDispatcherProvider;
    private final Provider metricStamperProvider;
    private final Provider recentLogsProvider;
    private final Provider samplerFactoryProvider;
    private final Provider shutdownProvider;

    public MetricRecorderFactory_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10) {
        this.metricDispatcherProvider = provider;
        this.metricStamperProvider = provider2;
        this.shutdownProvider = provider3;
        this.samplerFactoryProvider = provider4;
        this.globalConfigurationsProvider = provider5;
        this.recentLogsProvider = provider6;
        this.interactionContextProvider = provider7;
        this.enableSafeFormatArgsAsStringsProvider = provider8;
        this.activeTraceProvider = provider9;
        this.enableActiveTraceCollectionForCrashProvider = provider10;
    }

    @Override // javax.inject.Provider
    public final MetricRecorderFactory get() {
        return new MetricRecorderFactory(this.metricDispatcherProvider, this.metricStamperProvider, this.shutdownProvider, this.samplerFactoryProvider, this.globalConfigurationsProvider, this.recentLogsProvider, this.interactionContextProvider, this.enableSafeFormatArgsAsStringsProvider, this.activeTraceProvider, this.enableActiveTraceCollectionForCrashProvider);
    }
}
