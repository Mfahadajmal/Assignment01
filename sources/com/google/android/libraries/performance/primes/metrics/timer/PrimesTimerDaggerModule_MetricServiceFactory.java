package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule_TimerMetricServiceSupportFactory;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTimerDaggerModule_MetricServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider traceMetricServiceProvider;
    private final Provider userConfigProvider;
    private final Provider withTracingImplProvider;

    public PrimesTimerDaggerModule_MetricServiceFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.userConfigProvider = provider;
        this.traceMetricServiceProvider = provider2;
        this.withTracingImplProvider = provider3;
        this.implProvider = provider4;
    }

    @Override // javax.inject.Provider
    public final TimerMetricService get() {
        TimerMetricService noopTimerMetricService;
        boolean isPresent = ((Optional) ((InstanceFactory) this.userConfigProvider).instance).isPresent();
        Optional optional = ((PrimesTraceDaggerModule_TimerMetricServiceSupportFactory) this.traceMetricServiceProvider).get();
        if (isPresent) {
            noopTimerMetricService = (TimerMetricService) (optional.isPresent() ? this.withTracingImplProvider : this.implProvider).get();
        } else {
            noopTimerMetricService = new NoopTimerMetricService();
        }
        noopTimerMetricService.getClass();
        return noopTimerMetricService;
    }
}
