package com.google.android.libraries.performance.primes.metrics.jank;

import dagger.internal.Factory;
import dagger.internal.ProviderOfLazy;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JankObserverFactory_Factory implements Factory {
    private final Provider handlerProvider;
    private final Provider jankServiceProvider;

    public JankObserverFactory_Factory(Provider provider, Provider provider2) {
        this.jankServiceProvider = provider;
        this.handlerProvider = provider2;
    }

    @Override // javax.inject.Provider
    public final JankObserverFactory get() {
        ProviderOfLazy.create(this.handlerProvider);
        return new JankObserverFactory(this.jankServiceProvider);
    }
}
