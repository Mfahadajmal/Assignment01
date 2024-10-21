package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.core.GlobalConfigurations;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideGlobalConfigurationsFactory implements Factory {
    private final Provider globalConfigurationsProvider;

    public ConfigurationsModule_ProvideGlobalConfigurationsFactory(Provider provider) {
        this.globalConfigurationsProvider = provider;
    }

    @Override // javax.inject.Provider
    public final Optional get() {
        Optional optional = (Optional) ((InstanceFactory) this.globalConfigurationsProvider).instance;
        return optional.isPresent() ? Optional.fromNullable((GlobalConfigurations) ((Provider) optional.get()).get()) : Absent.INSTANCE;
    }
}
