package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleDisplayConfigFlagsImpl implements BrailleDisplayConfigFlags {
    public static final ProcessStablePhenotypeFlag cutCopyPaste;
    public static final ProcessStablePhenotypeFlag playPauseMedia;
    public static final ProcessStablePhenotypeFlag selectAll;
    public static final ProcessStablePhenotypeFlag selectCurrentToStartOrEnd;
    public static final ProcessStablePhenotypeFlag selectPreviousNextCharacterWordLine;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        cutCopyPaste = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleDisplayConfig__cut_copy_paste", true, "com.google.android.marvin.talkback", of, true, false);
        playPauseMedia = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleDisplayConfig__play_pause_media", false, "com.google.android.marvin.talkback", of, true, false);
        selectAll = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleDisplayConfig__select_all", true, "com.google.android.marvin.talkback", of, true, false);
        selectCurrentToStartOrEnd = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleDisplayConfig__select_current_to_start_or_end", true, "com.google.android.marvin.talkback", of, true, false);
        selectPreviousNextCharacterWordLine = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleDisplayConfig__select_previous_next_character_word_line", true, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleDisplayConfigFlags
    public final boolean cutCopyPaste(Context context) {
        return ((Boolean) cutCopyPaste.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleDisplayConfigFlags
    public final boolean playPauseMedia(Context context) {
        return ((Boolean) playPauseMedia.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleDisplayConfigFlags
    public final boolean selectAll(Context context) {
        return ((Boolean) selectAll.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleDisplayConfigFlags
    public final boolean selectCurrentToStartOrEnd(Context context) {
        return ((Boolean) selectCurrentToStartOrEnd.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleDisplayConfigFlags
    public final boolean selectPreviousNextCharacterWordLine(Context context) {
        return ((Boolean) selectPreviousNextCharacterWordLine.get(context)).booleanValue();
    }
}
