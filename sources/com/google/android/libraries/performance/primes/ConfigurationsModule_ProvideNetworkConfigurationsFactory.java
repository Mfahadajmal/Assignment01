package com.google.android.libraries.performance.primes;

import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda7;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideNetworkConfigurationsFactory implements Factory {
    private final Provider optionalConfigurationsProvider;

    public ConfigurationsModule_ProvideNetworkConfigurationsFactory(Provider provider) {
        this.optionalConfigurationsProvider = provider;
    }

    @Override // javax.inject.Provider
    public final NetworkConfigurations get() {
        NetworkConfigurations networkConfigurations = (NetworkConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(8));
        networkConfigurations.getClass();
        return networkConfigurations;
    }
}
