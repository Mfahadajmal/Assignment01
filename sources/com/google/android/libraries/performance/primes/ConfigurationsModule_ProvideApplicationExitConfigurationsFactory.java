package com.google.android.libraries.performance.primes;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda7;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.core.MetricDispatcher;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations;
import com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Ticker;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import googledata.experiments.mobile.primes_android.features.AppExitFeature;
import googledata.experiments.mobile.primes_android.features.BatteryFeature;
import googledata.experiments.mobile.primes_android.features.JankFeature;
import io.grpc.internal.RetryingNameResolver;
import java.util.Random;
import java.util.Set;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideApplicationExitConfigurationsFactory implements Factory {
    private final Provider optionalConfigurationsProvider;
    private final /* synthetic */ int switching_field;

    public ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(Provider provider, int i) {
        this.switching_field = i;
        this.optionalConfigurationsProvider = provider;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        long elapsedRealtime;
        switch (this.switching_field) {
            case 0:
                ApplicationExitConfigurations applicationExitConfigurations = (ApplicationExitConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(4));
                applicationExitConfigurations.getClass();
                return applicationExitConfigurations;
            case 1:
                return new RetryingNameResolver.ResolutionResultListener(this.optionalConfigurationsProvider, null);
            case 2:
                CpuProfilingConfigurations cpuProfilingConfigurations = (CpuProfilingConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(3));
                cpuProfilingConfigurations.getClass();
                return cpuProfilingConfigurations;
            case 3:
                CrashConfigurations crashConfigurations = (CrashConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(12));
                crashConfigurations.getClass();
                return crashConfigurations;
            case 4:
                DebugMemoryConfigurations debugMemoryConfigurations = (DebugMemoryConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(6));
                debugMemoryConfigurations.getClass();
                return debugMemoryConfigurations;
            case 5:
                JankConfigurations jankConfigurations = (JankConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(5));
                jankConfigurations.getClass();
                return jankConfigurations;
            case 6:
                StorageConfigurations storageConfigurations = (StorageConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(7));
                storageConfigurations.getClass();
                return storageConfigurations;
            case 7:
                return (PrimesThreadsConfigurations) ((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance).or(PrimesThreadsConfigurations.newBuilder().build());
            case 8:
                TikTokTraceConfigurations tikTokTraceConfigurations = (TikTokTraceConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(13));
                tikTokTraceConfigurations.getClass();
                return tikTokTraceConfigurations;
            case 9:
                TimerConfigurations timerConfigurations = (TimerConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(9));
                timerConfigurations.getClass();
                return timerConfigurations;
            case 10:
                TraceConfigurations traceConfigurations = (TraceConfigurations) BatteryMetricService.provideMetricConfigurations((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance, new PrimesController$$ExternalSyntheticLambda7(11));
                traceConfigurations.getClass();
                return traceConfigurations;
            case 11:
                return new MetricDispatcher((Context) ((InstanceFactory) this.optionalConfigurationsProvider).instance);
            case 12:
                elapsedRealtime = SystemClock.elapsedRealtime();
                return new Random(elapsedRealtime);
            case 13:
                return (SpannableUtils$NonCopyableTextSpan) ((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance).or(new SpannableUtils$NonCopyableTextSpan());
            case 14:
                final SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = (SpannableUtils$NonCopyableTextSpan) this.optionalConfigurationsProvider.get();
                return new Ticker() { // from class: com.google.android.libraries.performance.primes.PrimesClockModule$1
                    @Override // com.google.common.base.Ticker
                    public final long read() {
                        return SpannableUtils$NonCopyableTextSpan.elapsedRealtimeNanos$ar$ds();
                    }
                };
            case 15:
                Set set = (Set) ((Supplier) ((InstanceFactory) this.optionalConfigurationsProvider).instance).get();
                set.getClass();
                return set;
            case 16:
                MemoryConfigurations memoryConfigurations = (MemoryConfigurations) ((Provider) ((Optional) ((InstanceFactory) this.optionalConfigurationsProvider).instance).or(new PrimesController$$ExternalSyntheticLambda7(14))).get();
                memoryConfigurations.getClass();
                return memoryConfigurations;
            case 17:
                return Boolean.valueOf(AppExitFeature.INSTANCE.get().appExitCollectionEnabled((Context) ((InstanceFactory) this.optionalConfigurationsProvider).instance));
            case 18:
                ApplicationExitReasons appExitReasonsToReport = AppExitFeature.INSTANCE.get().appExitReasonsToReport((Context) ((InstanceFactory) this.optionalConfigurationsProvider).instance);
                appExitReasonsToReport.getClass();
                return appExitReasonsToReport;
            case 19:
                SystemHealthProto$SamplingParameters batterySamplingParameters = BatteryFeature.INSTANCE.get().batterySamplingParameters((Context) ((InstanceFactory) this.optionalConfigurationsProvider).instance);
                batterySamplingParameters.getClass();
                return batterySamplingParameters;
            default:
                return Boolean.valueOf(JankFeature.INSTANCE.get().computeMaxAcceptedFrameTimeFromWindow((Context) ((InstanceFactory) this.optionalConfigurationsProvider).instance));
        }
    }
}
