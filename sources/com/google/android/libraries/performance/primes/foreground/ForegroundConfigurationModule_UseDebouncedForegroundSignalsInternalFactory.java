package com.google.android.libraries.performance.primes.foreground;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ForegroundConfigurationModule_UseDebouncedForegroundSignalsInternalFactory implements Factory {
    private final Provider userConfigProvider;

    public ForegroundConfigurationModule_UseDebouncedForegroundSignalsInternalFactory(Provider provider) {
        this.userConfigProvider = provider;
    }

    @Override // javax.inject.Provider
    public final Boolean get() {
        Boolean bool = (Boolean) ((Optional) ((InstanceFactory) this.userConfigProvider).instance).or((Object) false);
        bool.booleanValue();
        return bool;
    }
}
