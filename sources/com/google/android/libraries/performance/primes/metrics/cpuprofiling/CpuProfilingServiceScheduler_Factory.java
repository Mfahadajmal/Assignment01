package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfilingServiceScheduler_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider clockProvider;
    private final Provider configsProvider;

    public CpuProfilingServiceScheduler_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.clockProvider = provider;
        this.configsProvider = provider2;
        this.applicationProvider = provider3;
    }

    @Override // javax.inject.Provider
    public final CpuProfilingServiceScheduler get() {
        return new CpuProfilingServiceScheduler((SpannableUtils$NonCopyableTextSpan) this.clockProvider.get(), DoubleCheck.lazy(this.configsProvider), (Context) ((InstanceFactory) this.applicationProvider).instance);
    }
}
