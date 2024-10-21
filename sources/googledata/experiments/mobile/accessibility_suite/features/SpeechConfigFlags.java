package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SpeechConfigFlags {
    boolean enableAggressiveChunking(Context context);

    boolean enableCacheTtsLocale(Context context);

    boolean enableMediaControlHintForCall(Context context);

    boolean enableShortAndLongDurationsForSpecificApps(Context context);

    boolean removeUnnecessarySpans(Context context);
}
