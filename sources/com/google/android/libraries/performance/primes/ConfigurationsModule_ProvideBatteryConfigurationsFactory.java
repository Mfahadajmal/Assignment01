package com.google.android.libraries.performance.primes;

import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda7;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideBatteryConfigurationsFactory implements Factory {
    private final Provider optionalConfigurationsProvider;

    public ConfigurationsModule_ProvideBatteryConfigurationsFactory(Provider provider) {
        this.optionalConfigurationsProvider = provider;
    }

    @Override // javax.inject.Provider
    public final BatteryConfigurations get() {
        BatteryConfigurations batteryConfigurations = (BatteryConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(10));
        batteryConfigurations.getClass();
        return batteryConfigurations;
    }
}
