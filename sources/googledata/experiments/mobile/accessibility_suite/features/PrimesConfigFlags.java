package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface PrimesConfigFlags {
    long getThresholdMessageQueueDetails(Context context);

    double getTimerSamplingProbability(Context context);

    boolean isBatteryMetricEnabled(Context context);

    boolean isCrashMetricEnabled(Context context);

    boolean isEventBasedLatencyMetricEnabled(Context context);

    boolean isMemoryMetricEnabled(Context context);

    boolean isNetworkMetricEnabled(Context context);

    boolean isPackageMetricEnabled(Context context);

    boolean isTimerMetricEnabled(Context context);
}
