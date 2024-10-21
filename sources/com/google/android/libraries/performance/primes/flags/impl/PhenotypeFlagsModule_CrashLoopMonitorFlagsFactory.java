package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitorFlags;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.android.libraries.performance.primes.metrics.crash.CrashedTikTokTraceConfigs;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import googledata.experiments.mobile.primes_android.features.AppExitFeature;
import googledata.experiments.mobile.primes_android.features.CpuprofilingFeature;
import googledata.experiments.mobile.primes_android.features.CrashFeature;
import googledata.experiments.mobile.primes_android.features.JankFeature;
import googledata.experiments.mobile.primes_android.features.MemoryFeature;
import googledata.experiments.mobile.primes_android.features.NetworkFeature;
import googledata.experiments.mobile.primes_android.features.StartupFeature;
import googledata.experiments.mobile.primes_android.features.StorageFeature;
import googledata.experiments.mobile.primes_android.features.TimerFeature;
import googledata.experiments.mobile.primes_android.features.TraceFeature;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory implements Factory {
    private final Provider contextProvider;
    private final /* synthetic */ int switching_field;

    public PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(Provider provider, int i) {
        this.switching_field = i;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        switch (this.switching_field) {
            case 0:
                CrashLoopMonitorFlags crashLoopMonitorFlags = CrashFeature.INSTANCE.get().crashLoopMonitorFlags((Context) ((InstanceFactory) this.contextProvider).instance);
                crashLoopMonitorFlags.getClass();
                return crashLoopMonitorFlags;
            case 1:
                SystemHealthProto$SamplingParameters cpuprofilingSamplingParameters = CpuprofilingFeature.INSTANCE.get().cpuprofilingSamplingParameters((Context) ((InstanceFactory) this.contextProvider).instance);
                cpuprofilingSamplingParameters.getClass();
                return cpuprofilingSamplingParameters;
            case 2:
                CrashedTikTokTraceConfigs crashedTiktokTraceConfigs = CrashFeature.INSTANCE.get().crashedTiktokTraceConfigs((Context) ((InstanceFactory) this.contextProvider).instance);
                crashedTiktokTraceConfigs.getClass();
                return crashedTiktokTraceConfigs;
            case 3:
                return Boolean.valueOf(TraceFeature.INSTANCE.get().defaultToUnifiedFormatForTiktokTraces((Context) ((InstanceFactory) this.contextProvider).instance));
            case 4:
                return Boolean.valueOf(CrashFeature.INSTANCE.get().enableActiveTraceCollectionForCrashes((Context) ((InstanceFactory) this.contextProvider).instance));
            case 5:
                return Boolean.valueOf(AppExitFeature.INSTANCE.get().enableCollectingAnrDiagnostics((Context) ((InstanceFactory) this.contextProvider).instance));
            case 6:
                return Boolean.valueOf(CrashFeature.INSTANCE.get().enableSafeFormatArgsAsStrings((Context) ((InstanceFactory) this.contextProvider).instance));
            case 7:
                return Boolean.valueOf(StartupFeature.INSTANCE.get().enableStartupBaselineDiscarding((Context) ((InstanceFactory) this.contextProvider).instance));
            case 8:
                return Long.valueOf(StartupFeature.INSTANCE.get().firstDrawType((Context) ((InstanceFactory) this.contextProvider).instance));
            case 9:
                PerfettoTraceConfigurations$JankPerfettoConfigurations jankPerfettoConfigurations = JankFeature.INSTANCE.get().jankPerfettoConfigurations((Context) ((InstanceFactory) this.contextProvider).instance);
                jankPerfettoConfigurations.getClass();
                return jankPerfettoConfigurations;
            case 10:
                SystemHealthProto$SamplingParameters jankSamplingParameters = JankFeature.INSTANCE.get().jankSamplingParameters((Context) ((InstanceFactory) this.contextProvider).instance);
                jankSamplingParameters.getClass();
                return jankSamplingParameters;
            case 11:
                return Boolean.valueOf(MemoryFeature.INSTANCE.get().memoizeConfigsProvider((Context) ((InstanceFactory) this.contextProvider).instance));
            case 12:
                SystemHealthProto$SamplingParameters memorySamplingParameters = MemoryFeature.INSTANCE.get().memorySamplingParameters((Context) ((InstanceFactory) this.contextProvider).instance);
                memorySamplingParameters.getClass();
                return memorySamplingParameters;
            case 13:
                SystemHealthProto$SamplingParameters networkSamplingParameters = NetworkFeature.INSTANCE.get().networkSamplingParameters((Context) ((InstanceFactory) this.contextProvider).instance);
                networkSamplingParameters.getClass();
                return networkSamplingParameters;
            case 14:
                return Long.valueOf(MemoryFeature.INSTANCE.get().periodicMemoryCollectionPeriodMs((Context) ((InstanceFactory) this.contextProvider).instance));
            case 15:
                return Boolean.valueOf(MemoryFeature.INSTANCE.get().readCorrectProcStatus((Context) ((InstanceFactory) this.contextProvider).instance));
            case 16:
                CrashRecordingTimeouts recordingTimeouts = CrashFeature.INSTANCE.get().recordingTimeouts((Context) ((InstanceFactory) this.contextProvider).instance);
                recordingTimeouts.getClass();
                return recordingTimeouts;
            case 17:
                SystemHealthProto$SamplingParameters startupSamplingParameters = StartupFeature.INSTANCE.get().startupSamplingParameters((Context) ((InstanceFactory) this.contextProvider).instance);
                startupSamplingParameters.getClass();
                return startupSamplingParameters;
            case 18:
                SystemHealthProto$SamplingParameters storageSamplingParameters = StorageFeature.INSTANCE.get().storageSamplingParameters((Context) ((InstanceFactory) this.contextProvider).instance);
                storageSamplingParameters.getClass();
                return storageSamplingParameters;
            case 19:
                SystemHealthProto$SamplingParameters timerSamplingParameters = TimerFeature.INSTANCE.get().timerSamplingParameters((Context) ((InstanceFactory) this.contextProvider).instance);
                timerSamplingParameters.getClass();
                return timerSamplingParameters;
            default:
                SystemHealthProto$SamplingParameters traceSamplingParameters = TraceFeature.INSTANCE.get().traceSamplingParameters((Context) ((InstanceFactory) this.contextProvider).instance);
                traceSamplingParameters.getClass();
                return traceSamplingParameters;
        }
    }
}
