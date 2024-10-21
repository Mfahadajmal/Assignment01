package com.google.android.libraries.performance.primes.sampling;

import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProbabilitySamplerFactory_Factory implements Factory {
    private final Provider randomProvider;

    public ProbabilitySamplerFactory_Factory(Provider provider) {
        this.randomProvider = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get$ar$class_merging$944026d8_0$ar$class_merging, reason: merged with bridge method [inline-methods] */
    public final AppLifecycleMonitor get() {
        return new AppLifecycleMonitor(this.randomProvider);
    }
}
