package com.google.android.libraries.performance.primes.metrics.jank;

import dagger.internal.Factory;
import dagger.internal.ProviderOfLazy;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowTrackerFactory_Factory implements Factory {
    private final Provider executorProvider;
    private final Provider handlerProvider;

    public WindowTrackerFactory_Factory(Provider provider, Provider provider2) {
        this.handlerProvider = provider;
        this.executorProvider = provider2;
    }

    @Override // javax.inject.Provider
    public final WindowTrackerFactory get() {
        return new WindowTrackerFactory((Provider) ProviderOfLazy.create(this.handlerProvider), this.executorProvider);
    }
}
