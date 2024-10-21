package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesConfigFlagsImpl implements PrimesConfigFlags {
    public static final ProcessStablePhenotypeFlag getThresholdMessageQueueDetails;
    public static final ProcessStablePhenotypeFlag getTimerSamplingProbability;
    public static final ProcessStablePhenotypeFlag isBatteryMetricEnabled;
    public static final ProcessStablePhenotypeFlag isCrashMetricEnabled;
    public static final ProcessStablePhenotypeFlag isEventBasedLatencyMetricEnabled;
    public static final ProcessStablePhenotypeFlag isMemoryMetricEnabled;
    public static final ProcessStablePhenotypeFlag isNetworkMetricEnabled;
    public static final ProcessStablePhenotypeFlag isPackageMetricEnabled;
    public static final ProcessStablePhenotypeFlag isTimerMetricEnabled;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        getThresholdMessageQueueDetails = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$64c97a1b_0("PrimesConfig__get_threshold_message_queue_details", 2000L, "com.google.android.marvin.talkback", of, true, false);
        getTimerSamplingProbability = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing("PrimesConfig__get_timer_sampling_probability", 0.09d, "com.google.android.marvin.talkback", of, true, false);
        isBatteryMetricEnabled = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("PrimesConfig__is_battery_metric_enabled", true, "com.google.android.marvin.talkback", of, true, false);
        isCrashMetricEnabled = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("PrimesConfig__is_crash_metric_enabled", true, "com.google.android.marvin.talkback", of, true, false);
        isEventBasedLatencyMetricEnabled = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("PrimesConfig__is_event_based_latency_metric_enabled", true, "com.google.android.marvin.talkback", of, true, false);
        isMemoryMetricEnabled = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("PrimesConfig__is_memory_metric_enabled", true, "com.google.android.marvin.talkback", of, true, false);
        isNetworkMetricEnabled = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("PrimesConfig__is_network_metric_enabled", true, "com.google.android.marvin.talkback", of, true, false);
        isPackageMetricEnabled = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("PrimesConfig__is_package_metric_enabled", true, "com.google.android.marvin.talkback", of, true, false);
        isTimerMetricEnabled = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("PrimesConfig__is_timer_metric_enabled", true, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final long getThresholdMessageQueueDetails(Context context) {
        return ((Long) getThresholdMessageQueueDetails.get(context)).longValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final double getTimerSamplingProbability(Context context) {
        return ((Double) getTimerSamplingProbability.get(context)).doubleValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final boolean isBatteryMetricEnabled(Context context) {
        return ((Boolean) isBatteryMetricEnabled.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final boolean isCrashMetricEnabled(Context context) {
        return ((Boolean) isCrashMetricEnabled.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final boolean isEventBasedLatencyMetricEnabled(Context context) {
        return ((Boolean) isEventBasedLatencyMetricEnabled.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final boolean isMemoryMetricEnabled(Context context) {
        return ((Boolean) isMemoryMetricEnabled.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final boolean isNetworkMetricEnabled(Context context) {
        return ((Boolean) isNetworkMetricEnabled.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final boolean isPackageMetricEnabled(Context context) {
        return ((Boolean) isPackageMetricEnabled.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags
    public final boolean isTimerMetricEnabled(Context context) {
        return ((Boolean) isTimerMetricEnabled.get(context)).booleanValue();
    }
}
