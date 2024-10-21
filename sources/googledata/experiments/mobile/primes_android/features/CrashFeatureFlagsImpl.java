package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitorFlags;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.android.libraries.performance.primes.metrics.crash.CrashedTikTokTraceConfigs;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashFeatureFlagsImpl implements CrashFeatureFlags {
    public static final ProcessStablePhenotypeFlag crashLoopMonitorFlags;
    public static final ProcessStablePhenotypeFlag crashedTiktokTraceConfigs;
    public static final ProcessStablePhenotypeFlag enableActiveTraceCollectionForCrashes;
    public static final ProcessStablePhenotypeFlag enableSafeFormatArgsAsStrings;
    public static final ProcessStablePhenotypeFlag recordingTimeouts;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        SingletonImmutableSet singletonImmutableSet = new SingletonImmutableSet("CLIENT_LOGGING_PROD");
        crashLoopMonitorFlags = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("45390627", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(8), "CAAQAxgGIJBOLQrXIzw", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        crashedTiktokTraceConfigs = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("45376983", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(9), "CAEQZBj0AyDoBw", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        enableActiveTraceCollectionForCrashes = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45625683", false, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        enableSafeFormatArgsAsStrings = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45404981", true, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        recordingTimeouts = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("45371370", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(10), "CJYBEMgB", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.CrashFeatureFlags
    public final CrashLoopMonitorFlags crashLoopMonitorFlags(Context context) {
        return (CrashLoopMonitorFlags) crashLoopMonitorFlags.get(context);
    }

    @Override // googledata.experiments.mobile.primes_android.features.CrashFeatureFlags
    public final CrashedTikTokTraceConfigs crashedTiktokTraceConfigs(Context context) {
        return (CrashedTikTokTraceConfigs) crashedTiktokTraceConfigs.get(context);
    }

    @Override // googledata.experiments.mobile.primes_android.features.CrashFeatureFlags
    public final boolean enableActiveTraceCollectionForCrashes(Context context) {
        return ((Boolean) enableActiveTraceCollectionForCrashes.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.CrashFeatureFlags
    public final boolean enableSafeFormatArgsAsStrings(Context context) {
        return ((Boolean) enableSafeFormatArgsAsStrings.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.CrashFeatureFlags
    public final CrashRecordingTimeouts recordingTimeouts(Context context) {
        return (CrashRecordingTimeouts) recordingTimeouts.get(context);
    }
}
