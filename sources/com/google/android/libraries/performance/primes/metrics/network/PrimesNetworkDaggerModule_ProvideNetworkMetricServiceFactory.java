package com.google.android.libraries.performance.primes.metrics.network;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesNetworkDaggerModule_ProvideNetworkMetricServiceFactory implements Factory {
    private final Provider networkMetricServiceProvider;
    private final Provider userConfigProvider;

    public PrimesNetworkDaggerModule_ProvideNetworkMetricServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.networkMetricServiceProvider = provider2;
    }

    @Override // javax.inject.Provider
    public final NetworkMetricService get() {
        NetworkMetricService noopNetworkMetricService;
        if (((Optional) ((InstanceFactory) this.userConfigProvider).instance).isPresent()) {
            noopNetworkMetricService = (NetworkMetricService) this.networkMetricServiceProvider.get();
        } else {
            noopNetworkMetricService = new NoopNetworkMetricService();
        }
        noopNetworkMetricService.getClass();
        return noopNetworkMetricService;
    }
}
