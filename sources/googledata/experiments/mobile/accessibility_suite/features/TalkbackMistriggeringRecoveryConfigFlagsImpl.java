package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkbackMistriggeringRecoveryConfigFlagsImpl implements TalkbackMistriggeringRecoveryConfigFlags {
    public static final ProcessStablePhenotypeFlag automaticTurnOff;
    public static final ProcessStablePhenotypeFlag showExitBanner;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        automaticTurnOff = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("TalkbackMistriggeringRecoveryConfig__automatic_turn_off", true, "com.google.android.marvin.talkback", of, true, false);
        showExitBanner = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("TalkbackMistriggeringRecoveryConfig__show_exit_banner", true, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TalkbackMistriggeringRecoveryConfigFlags
    public final boolean automaticTurnOff(Context context) {
        return ((Boolean) automaticTurnOff.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TalkbackMistriggeringRecoveryConfigFlags
    public final boolean showExitBanner(Context context) {
        return ((Boolean) showExitBanner.get(context)).booleanValue();
    }
}
