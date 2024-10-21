package com.google.android.libraries.performance.primes.initialization;

import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesInitializerImpl_Factory implements Factory {
    private final Provider crashMetricServiceProviderOptProvider;
    private final Provider deferPrimesCrashInitOptProvider;
    private final Provider memoryMetricServiceProviderOptProvider;

    public PrimesInitializerImpl_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.crashMetricServiceProviderOptProvider = provider;
        this.deferPrimesCrashInitOptProvider = provider2;
        this.memoryMetricServiceProviderOptProvider = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get$ar$class_merging$cf5517d3_0, reason: merged with bridge method [inline-methods] */
    public final ProcessStatsCapture get() {
        return new ProcessStatsCapture(((DaggerProdInternalComponent.PresentGuavaOptionalProviderProvider) this.crashMetricServiceProviderOptProvider).get(), (Optional) ((InstanceFactory) this.deferPrimesCrashInitOptProvider).instance, ((DaggerProdInternalComponent.PresentGuavaOptionalProviderProvider) this.memoryMetricServiceProviderOptProvider).get());
    }
}
