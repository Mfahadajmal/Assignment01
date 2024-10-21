package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashMetricFactory_Factory implements Factory {
    private final Provider exceptionMessageMappingFunctionsProvider;
    private final Provider processStatsCaptureProvider;

    public CrashMetricFactory_Factory(Provider provider, Provider provider2) {
        this.processStatsCaptureProvider = provider;
        this.exceptionMessageMappingFunctionsProvider = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get$ar$class_merging$e5685de6_0, reason: merged with bridge method [inline-methods] */
    public final PersistentStorage get() {
        return new PersistentStorage((ProcessStatsCapture) this.processStatsCaptureProvider.get(), DoubleCheck.lazy(this.exceptionMessageMappingFunctionsProvider));
    }
}
