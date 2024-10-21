package com.google.android.accessibility.selecttospeak;

import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import com.google.android.libraries.performance.primes.metrics.core.GlobalConfigurations;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricService;
import com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations;
import com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableSet;
import com.google.frameworks.client.data.android.metrics.NetworkBandwidthInterceptor;
import com.google.frameworks.client.data.android.metrics.NetworkLatencyInterceptor;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PrimesController$$ExternalSyntheticLambda7 implements Provider {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PrimesController$$ExternalSyntheticLambda7(int i) {
        this.switching_field = i;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        int i;
        int i2;
        int i3;
        ApplicationExitMetricService applicationExitMetricService;
        int i4 = 2;
        int i5 = 0;
        int i6 = 1;
        switch (this.switching_field) {
            case 0:
                GlobalConfigurations.Builder newBuilder = GlobalConfigurations.newBuilder();
                newBuilder.componentNameSupplier = new PrimesController$$ExternalSyntheticLambda9(i5);
                return newBuilder.build();
            case 1:
                GlobalConfigurations.Builder newBuilder2 = GlobalConfigurations.newBuilder();
                newBuilder2.componentNameSupplier = new PrimesController$$ExternalSyntheticLambda9(i6);
                return newBuilder2.build();
            case 2:
                GlobalConfigurations.Builder newBuilder3 = GlobalConfigurations.newBuilder();
                newBuilder3.componentNameSupplier = new PrimesController$$ExternalSyntheticLambda9(i4);
                return newBuilder3.build();
            case 3:
                CpuProfilingConfigurations.Builder builder = new CpuProfilingConfigurations.Builder(null);
                builder.maxBufferSizeBytes = 2097152;
                builder.sampleDurationMs = 30000;
                builder.sampleDurationSkewMs = 5000;
                builder.sampleFrequencyMicro = 1000;
                builder.samplesPerEpoch = 5.0d;
                builder.set$0 = (byte) 31;
                builder.enablement$ar$edu = 1;
                if (builder.set$0 != 31) {
                    StringBuilder sb = new StringBuilder();
                    if (builder.enablement$ar$edu == 0) {
                        sb.append(" enablement");
                    }
                    if ((builder.set$0 & 1) == 0) {
                        sb.append(" maxBufferSizeBytes");
                    }
                    if ((builder.set$0 & 2) == 0) {
                        sb.append(" sampleDurationMs");
                    }
                    if ((builder.set$0 & 4) == 0) {
                        sb.append(" sampleDurationSkewMs");
                    }
                    if ((builder.set$0 & 8) == 0) {
                        sb.append(" sampleFrequencyMicro");
                    }
                    if ((builder.set$0 & 16) == 0) {
                        sb.append(" samplesPerEpoch");
                    }
                    throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
                }
                return new CpuProfilingConfigurations(1, builder.maxBufferSizeBytes, builder.sampleDurationMs, builder.sampleDurationSkewMs, builder.sampleFrequencyMicro, builder.samplesPerEpoch);
            case 4:
                ApplicationExitConfigurations.Builder builder2 = new ApplicationExitConfigurations.Builder(null);
                builder2.enablement$ar$edu = 1;
                builder2.ApplicationExitConfigurations$Builder$ar$reportingProcessShortName = "";
                Object obj = builder2.ApplicationExitConfigurations$Builder$ar$reportingProcessShortName;
                if (obj == null) {
                    StringBuilder sb2 = new StringBuilder();
                    if (builder2.enablement$ar$edu == 0) {
                        sb2.append(" enablement");
                    }
                    if (builder2.ApplicationExitConfigurations$Builder$ar$reportingProcessShortName == null) {
                        sb2.append(" reportingProcessShortName");
                    }
                    throw new IllegalStateException("Missing required properties:".concat(sb2.toString()));
                }
                return new ApplicationExitConfigurations(1, (String) obj);
            case 5:
                JankConfigurations.Builder builder3 = new JankConfigurations.Builder(null);
                builder3.rateLimitPerSecond = 10;
                builder3.set$0 = (byte) 1;
                builder3.setEnablement$ar$edu$b45733ce_0(1);
                JankConfigurations.Builder enablement$ar$edu$b45733ce_0 = builder3.setEnablement$ar$edu$b45733ce_0(2);
                if (enablement$ar$edu$b45733ce_0.set$0 == 1 && (i = enablement$ar$edu$b45733ce_0.enablement$ar$edu) != 0) {
                    return new JankConfigurations(i, enablement$ar$edu$b45733ce_0.rateLimitPerSecond);
                }
                StringBuilder sb3 = new StringBuilder();
                if (enablement$ar$edu$b45733ce_0.enablement$ar$edu == 0) {
                    sb3.append(" enablement");
                }
                if (enablement$ar$edu$b45733ce_0.set$0 == 0) {
                    sb3.append(" rateLimitPerSecond");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb3.toString()));
            case 6:
                DebugMemoryConfigurations.Builder builder4 = new DebugMemoryConfigurations.Builder(null);
                builder4.setEnablement$ar$edu$dea24210_0(1);
                builder4.debugMemoryServiceThrottleMs = TimeUnit.MINUTES.toMillis(5L) + TimeUnit.SECONDS.toMillis(20L);
                builder4.set$0 = (byte) 1;
                DebugMemoryConfigurations.Builder enablement$ar$edu$dea24210_0 = builder4.setEnablement$ar$edu$dea24210_0(2);
                if (enablement$ar$edu$dea24210_0.debugMemoryEventsToSample == null) {
                    enablement$ar$edu$dea24210_0.debugMemoryEventsToSample = RegularImmutableSet.EMPTY;
                }
                if (enablement$ar$edu$dea24210_0.set$0 == 1 && (i2 = enablement$ar$edu$dea24210_0.enablement$ar$edu) != 0) {
                    return new DebugMemoryConfigurations(i2, enablement$ar$edu$dea24210_0.debugMemoryServiceThrottleMs, enablement$ar$edu$dea24210_0.debugMemoryEventsToSample);
                }
                StringBuilder sb4 = new StringBuilder();
                if (enablement$ar$edu$dea24210_0.enablement$ar$edu == 0) {
                    sb4.append(" enablement");
                }
                if (enablement$ar$edu$dea24210_0.set$0 == 0) {
                    sb4.append(" debugMemoryServiceThrottleMs");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb4.toString()));
            case 7:
                return StorageConfigurations.newBuilder().m201build();
            case 8:
                return NetworkConfigurations.newBuilder().setEnabled(false).build();
            case 9:
                return TimerConfigurations.newBuilder().setEnabled(false).m203build();
            case 10:
                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(false).build();
            case 11:
                return TraceConfigurations.newBuilder().setEnabled(false).build();
            case 12:
                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(false).build();
            case 13:
                TikTokTraceConfigurations.Builder builder5 = new TikTokTraceConfigurations.Builder(null);
                builder5.rateLimitPerSecond = 10;
                byte b = builder5.set$0;
                builder5.recordTimerDuration = true;
                builder5.set$0 = (byte) (b | 3);
                builder5.dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging = new ApplicationExitMetricService((byte[]) null);
                builder5.setEnablement$ar$edu$9b3aba90_0(1);
                builder5.set$0 = (byte) (builder5.set$0 | 4);
                builder5.traceFormat$ar$edu = 1;
                TikTokTraceConfigurations.Builder enablement$ar$edu$9b3aba90_0 = builder5.setEnablement$ar$edu$9b3aba90_0(2);
                if (enablement$ar$edu$9b3aba90_0.set$0 == 7 && (i3 = enablement$ar$edu$9b3aba90_0.enablement$ar$edu) != 0 && (applicationExitMetricService = enablement$ar$edu$9b3aba90_0.dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging) != null && enablement$ar$edu$9b3aba90_0.traceFormat$ar$edu != 0) {
                    return new TikTokTraceConfigurations(i3, enablement$ar$edu$9b3aba90_0.rateLimitPerSecond, applicationExitMetricService, enablement$ar$edu$9b3aba90_0.traceMetricExtensionProvider, enablement$ar$edu$9b3aba90_0.recordTimerDuration, 1);
                }
                StringBuilder sb5 = new StringBuilder();
                if (enablement$ar$edu$9b3aba90_0.enablement$ar$edu == 0) {
                    sb5.append(" enablement");
                }
                if ((enablement$ar$edu$9b3aba90_0.set$0 & 1) == 0) {
                    sb5.append(" rateLimitPerSecond");
                }
                if (enablement$ar$edu$9b3aba90_0.dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging == null) {
                    sb5.append(" dynamicSampler");
                }
                if ((enablement$ar$edu$9b3aba90_0.set$0 & 2) == 0) {
                    sb5.append(" recordTimerDuration");
                }
                if ((enablement$ar$edu$9b3aba90_0.set$0 & 4) == 0) {
                    sb5.append(" sendEmptyTraces");
                }
                if (enablement$ar$edu$9b3aba90_0.traceFormat$ar$edu == 0) {
                    sb5.append(" traceFormat");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb5.toString()));
            case 14:
                return MemoryConfigurations.newBuilder().build();
            default:
                return ImmutableList.of((Object) new NetworkLatencyInterceptor(), (Object) new NetworkBandwidthInterceptor());
        }
    }
}
