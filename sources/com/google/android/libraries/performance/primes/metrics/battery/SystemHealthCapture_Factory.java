package com.google.android.libraries.performance.primes.metrics.battery;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthCapture_Factory implements Factory {
    private final Provider applicationContextProvider;

    public SystemHealthCapture_Factory(Provider provider) {
        this.applicationContextProvider = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get$ar$class_merging$57cf6181_0, reason: merged with bridge method [inline-methods] */
    public final WindowTrackerFactory get() {
        return new WindowTrackerFactory((Context) ((InstanceFactory) this.applicationContextProvider).instance);
    }
}
