package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sHatsSurveyConfigFlagsImpl implements S2sHatsSurveyConfigFlags {
    public static final ProcessStablePhenotypeFlag apiKey;
    public static final ProcessStablePhenotypeFlag enableProofMode;
    public static final ProcessStablePhenotypeFlag triggerId;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        apiKey = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("S2sHatsSurveyConfig__api_key", "AIzaSyBDoIyixYxKxvRIyUhVdpMSuDRHKZsnQ9A", "com.google.android.marvin.talkback", of, true, false);
        enableProofMode = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("S2sHatsSurveyConfig__enable_Proof_Mode", false, "com.google.android.marvin.talkback", of, true, false);
        triggerId = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("S2sHatsSurveyConfig__trigger_id", "gcqwyDWU20n55VUFU4f0QwEoxvAj", "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sHatsSurveyConfigFlags
    public final String apiKey(Context context) {
        return (String) apiKey.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sHatsSurveyConfigFlags
    public final boolean enableProofMode(Context context) {
        return ((Boolean) enableProofMode.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sHatsSurveyConfigFlags
    public final String triggerId(Context context) {
        return (String) triggerId.get(context);
    }
}
