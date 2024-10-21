package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GestureConfigFlagsImpl implements GestureConfigFlags {
    public static final ProcessStablePhenotypeFlag enableQuickNavigationToHunGesture;
    public static final ProcessStablePhenotypeFlag handleGestureBrailleDisplayOnOff;
    public static final ProcessStablePhenotypeFlag handleGestureDetectionInTalkback;
    public static final ProcessStablePhenotypeFlag handleStateChangeInMainThread;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        enableQuickNavigationToHunGesture = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GestureConfig__enable_quick_navigation_to_hun_gesture", true, "com.google.android.marvin.talkback", of, true, false);
        handleGestureBrailleDisplayOnOff = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GestureConfig__handle_gesture_braille_display_on_off", false, "com.google.android.marvin.talkback", of, true, false);
        handleGestureDetectionInTalkback = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GestureConfig__handle_gesture_detection_in_talkback", true, "com.google.android.marvin.talkback", of, true, false);
        handleStateChangeInMainThread = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GestureConfig__handle_state_change_in_main_thread", false, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GestureConfigFlags
    public final boolean enableQuickNavigationToHunGesture(Context context) {
        return ((Boolean) enableQuickNavigationToHunGesture.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GestureConfigFlags
    public final boolean handleGestureBrailleDisplayOnOff(Context context) {
        return ((Boolean) handleGestureBrailleDisplayOnOff.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GestureConfigFlags
    public final boolean handleGestureDetectionInTalkback(Context context) {
        return ((Boolean) handleGestureDetectionInTalkback.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GestureConfigFlags
    public final boolean handleStateChangeInMainThread(Context context) {
        return ((Boolean) handleStateChangeInMainThread.get(context)).booleanValue();
    }
}
