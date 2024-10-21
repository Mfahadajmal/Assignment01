package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.android.libraries.performance.primes.persistent.PersistentStorage_Factory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StatsStorage_Factory implements Factory {
    private final Provider storageProvider;

    public StatsStorage_Factory(Provider provider) {
        this.storageProvider = provider;
    }

    @Override // javax.inject.Provider
    public final StatsStorage get() {
        return new StatsStorage(((PersistentStorage_Factory) this.storageProvider).get());
    }
}
