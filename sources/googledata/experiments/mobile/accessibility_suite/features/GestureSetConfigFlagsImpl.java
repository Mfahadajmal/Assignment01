package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GestureSetConfigFlagsImpl implements GestureSetConfigFlags {
    public static final ProcessStablePhenotypeFlag activateMultipleGestureSet;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        activateMultipleGestureSet = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GestureSetConfig__activate_multiple_gesture_set", false, "com.google.android.marvin.talkback", ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK"), true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GestureSetConfigFlags
    public final boolean activateMultipleGestureSet(Context context) {
        return ((Boolean) activateMultipleGestureSet.get(context)).booleanValue();
    }
}
