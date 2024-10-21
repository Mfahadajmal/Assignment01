package com.google.android.libraries.performance.primes.sampling;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentRateLimiting_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider clockProvider;
    private final Provider sharedPrefsProvider;

    public PersistentRateLimiting_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.applicationProvider = provider;
        this.clockProvider = provider2;
        this.sharedPrefsProvider = provider3;
    }

    @Override // javax.inject.Provider
    public final PersistentRateLimiting get() {
        return new PersistentRateLimiting((Context) ((InstanceFactory) this.applicationProvider).instance, (SpannableUtils$NonCopyableTextSpan) this.clockProvider.get(), this.sharedPrefsProvider);
    }
}
