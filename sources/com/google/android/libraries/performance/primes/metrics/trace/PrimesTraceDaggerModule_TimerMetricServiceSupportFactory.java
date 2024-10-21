package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceDaggerModule_TimerMetricServiceSupportFactory implements Factory {
    private final Provider implProvider;
    private final Provider userTikTokTraceConfigProvider;
    private final Provider userTraceConfigProvider;

    public PrimesTraceDaggerModule_TimerMetricServiceSupportFactory(Provider provider, Provider provider2, Provider provider3) {
        this.userTraceConfigProvider = provider;
        this.userTikTokTraceConfigProvider = provider2;
        this.implProvider = provider3;
    }

    @Override // javax.inject.Provider
    public final Optional get() {
        Optional optional = (Optional) ((InstanceFactory) this.userTraceConfigProvider).instance;
        Optional optional2 = (Optional) ((InstanceFactory) this.userTikTokTraceConfigProvider).instance;
        if (!optional.isPresent() && !optional2.isPresent()) {
            return Absent.INSTANCE;
        }
        Provider provider = this.implProvider;
        provider.getClass();
        return Optional.of(new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(provider, 4));
    }
}
