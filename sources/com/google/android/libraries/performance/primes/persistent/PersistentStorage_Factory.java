package com.google.android.libraries.performance.primes.persistent;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentStorage_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider sharedPreferencesProvider;

    public PersistentStorage_Factory(Provider provider, Provider provider2) {
        this.applicationProvider = provider;
        this.sharedPreferencesProvider = provider2;
    }

    @Override // javax.inject.Provider
    public final PersistentStorage get() {
        return new PersistentStorage((Context) ((InstanceFactory) this.applicationProvider).instance, this.sharedPreferencesProvider);
    }
}
