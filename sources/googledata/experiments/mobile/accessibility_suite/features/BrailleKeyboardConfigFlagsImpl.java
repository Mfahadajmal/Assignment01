package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleKeyboardConfigFlagsImpl implements BrailleKeyboardConfigFlags {
    public static final ProcessStablePhenotypeFlag holdAndSwipeGesture;
    public static final ProcessStablePhenotypeFlag selectCurrentToStartOrEnd;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        holdAndSwipeGesture = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleKeyboardConfig__hold_and_swipe_gesture", true, "com.google.android.marvin.talkback", of, true, false);
        selectCurrentToStartOrEnd = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleKeyboardConfig__select_current_to_start_or_end", true, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleKeyboardConfigFlags
    public final boolean holdAndSwipeGesture(Context context) {
        return ((Boolean) holdAndSwipeGesture.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleKeyboardConfigFlags
    public final boolean selectCurrentToStartOrEnd(Context context) {
        return ((Boolean) selectCurrentToStartOrEnd.get(context)).booleanValue();
    }
}
