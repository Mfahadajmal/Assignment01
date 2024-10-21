package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirectionalNavigationConfigFlagsImpl implements DirectionalNavigationConfigFlags {
    public static final ProcessStablePhenotypeFlag allowFocusResting;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        allowFocusResting = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("DirectionalNavigationConfig__allow_focus_resting", true, "com.google.android.marvin.talkback", ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK"), true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.DirectionalNavigationConfigFlags
    public final boolean allowFocusResting(Context context) {
        return ((Boolean) allowFocusResting.get(context)).booleanValue();
    }
}
