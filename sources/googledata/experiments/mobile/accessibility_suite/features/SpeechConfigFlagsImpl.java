package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeechConfigFlagsImpl implements SpeechConfigFlags {
    public static final ProcessStablePhenotypeFlag enableAggressiveChunking;
    public static final ProcessStablePhenotypeFlag enableCacheTtsLocale;
    public static final ProcessStablePhenotypeFlag enableMediaControlHintForCall;
    public static final ProcessStablePhenotypeFlag enableShortAndLongDurationsForSpecificApps;
    public static final ProcessStablePhenotypeFlag removeUnnecessarySpans;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        enableAggressiveChunking = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("SpeechConfig__enable_aggressive_chunking", false, "com.google.android.marvin.talkback", of, true, false);
        enableCacheTtsLocale = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("SpeechConfig__enable_cache_tts_locale", false, "com.google.android.marvin.talkback", of, true, false);
        enableMediaControlHintForCall = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("SpeechConfig__enable_media_control_hint_for_call", true, "com.google.android.marvin.talkback", of, true, false);
        enableShortAndLongDurationsForSpecificApps = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("SpeechConfig__enable_short_and_long_durations_for_specific_apps", false, "com.google.android.marvin.talkback", of, true, false);
        removeUnnecessarySpans = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("SpeechConfig__remove_unnecessary_spans", false, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.SpeechConfigFlags
    public final boolean enableAggressiveChunking(Context context) {
        return ((Boolean) enableAggressiveChunking.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.SpeechConfigFlags
    public final boolean enableCacheTtsLocale(Context context) {
        return ((Boolean) enableCacheTtsLocale.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.SpeechConfigFlags
    public final boolean enableMediaControlHintForCall(Context context) {
        return ((Boolean) enableMediaControlHintForCall.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.SpeechConfigFlags
    public final boolean enableShortAndLongDurationsForSpecificApps(Context context) {
        return ((Boolean) enableShortAndLongDurationsForSpecificApps.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.SpeechConfigFlags
    public final boolean removeUnnecessarySpans(Context context) {
        return ((Boolean) removeUnnecessarySpans.get(context)).booleanValue();
    }
}
