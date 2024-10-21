package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitorFlags;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.android.libraries.performance.primes.metrics.crash.CrashedTikTokTraceConfigs;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CrashFeatureFlags {
    CrashLoopMonitorFlags crashLoopMonitorFlags(Context context);

    CrashedTikTokTraceConfigs crashedTiktokTraceConfigs(Context context);

    boolean enableActiveTraceCollectionForCrashes(Context context);

    boolean enableSafeFormatArgsAsStrings(Context context);

    CrashRecordingTimeouts recordingTimeouts(Context context);
}
