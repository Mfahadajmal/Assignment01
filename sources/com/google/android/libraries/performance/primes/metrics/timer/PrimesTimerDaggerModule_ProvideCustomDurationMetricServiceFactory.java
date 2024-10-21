package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule_TimerMetricServiceSupportFactory;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider traceMetricServiceProvider;
    private final Provider userConfigProvider;
    private final Provider withTracingImplProvider;

    public PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.userConfigProvider = provider;
        this.traceMetricServiceProvider = provider2;
        this.withTracingImplProvider = provider3;
        this.implProvider = provider4;
    }

    @Override // javax.inject.Provider
    public final CustomDurationMetricService get() {
        CustomDurationMetricService customDurationMetricService;
        boolean isPresent = ((Optional) ((InstanceFactory) this.userConfigProvider).instance).isPresent();
        Optional optional = ((PrimesTraceDaggerModule_TimerMetricServiceSupportFactory) this.traceMetricServiceProvider).get();
        if (isPresent) {
            customDurationMetricService = (CustomDurationMetricService) (optional.isPresent() ? this.withTracingImplProvider : this.implProvider).get();
        } else {
            customDurationMetricService = new CustomDurationMetricService() { // from class: com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule$1
                @Override // com.google.android.libraries.performance.primes.metrics.timer.CustomDurationMetricService
                public final ListenableFuture record(NoPiiString noPiiString, long j, long j2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
                    return ImmediateFuture.NULL;
                }
            };
        }
        customDurationMetricService.getClass();
        return customDurationMetricService;
    }
}
