package com.google.android.libraries.phenotype.client.stable;

import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitorFlags;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.android.libraries.performance.primes.metrics.crash.CrashedTikTokTraceConfigs;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protos.experiments.proto.TypedFeatures$StringListParam;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17 implements ProcessStablePhenotypeFlagFactory.Converter {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(int i) {
        this.switching_field = i;
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        switch (this.switching_field) {
            case 0:
                return Double.valueOf(Double.parseDouble((String) obj));
            case 1:
                return Long.valueOf(Long.parseLong((String) obj));
            case 2:
                return Boolean.valueOf(Boolean.parseBoolean((String) obj));
            case 3:
                return (String) obj;
            case 4:
                return (TypedFeatures$StringListParam) GeneratedMessageLite.parseFrom(TypedFeatures$StringListParam.DEFAULT_INSTANCE, (byte[]) obj);
            case 5:
                return (ApplicationExitReasons) GeneratedMessageLite.parseFrom(ApplicationExitReasons.DEFAULT_INSTANCE, (byte[]) obj);
            case 6:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
            case 7:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
            case 8:
                return (CrashLoopMonitorFlags) GeneratedMessageLite.parseFrom(CrashLoopMonitorFlags.DEFAULT_INSTANCE, (byte[]) obj);
            case 9:
                return (CrashedTikTokTraceConfigs) GeneratedMessageLite.parseFrom(CrashedTikTokTraceConfigs.DEFAULT_INSTANCE, (byte[]) obj);
            case 10:
                return (CrashRecordingTimeouts) GeneratedMessageLite.parseFrom(CrashRecordingTimeouts.DEFAULT_INSTANCE, (byte[]) obj);
            case 11:
                return (PerfettoTraceConfigurations$JankPerfettoConfigurations) GeneratedMessageLite.parseFrom(PerfettoTraceConfigurations$JankPerfettoConfigurations.DEFAULT_INSTANCE, (byte[]) obj);
            case 12:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
            case 13:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
            case 14:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
            case 15:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
            case 16:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
            case 17:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
            default:
                return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, (byte[]) obj);
        }
    }
}
