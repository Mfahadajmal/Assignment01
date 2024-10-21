package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsSurveyConfigFlagsImpl implements HatsSurveyConfigFlags {
    public static final ProcessStablePhenotypeFlag apiKey;
    public static final ProcessStablePhenotypeFlag enableProofMode;
    public static final ProcessStablePhenotypeFlag triggerId;
    public static final ProcessStablePhenotypeFlag tvTriggerId;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        apiKey = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("HatsSurveyConfig__api_key", "AIzaSyBDoIyixYxKxvRIyUhVdpMSuDRHKZsnQ9A", "com.google.android.marvin.talkback", of, true, false);
        enableProofMode = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("HatsSurveyConfig__enable_Proof_Mode", false, "com.google.android.marvin.talkback", of, true, false);
        triggerId = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("HatsSurveyConfig__trigger_id", "2LeECWxJB0n55VUFU4f0YncRpiqc", "com.google.android.marvin.talkback", of, true, false);
        tvTriggerId = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("HatsSurveyConfig__tv_trigger_id", "1XQMnVdi80n55VUFU4f0SbFvLXeo", "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.HatsSurveyConfigFlags
    public final String apiKey(Context context) {
        return (String) apiKey.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.HatsSurveyConfigFlags
    public final boolean enableProofMode(Context context) {
        return ((Boolean) enableProofMode.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.HatsSurveyConfigFlags
    public final String triggerId(Context context) {
        return (String) triggerId.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.HatsSurveyConfigFlags
    public final String tvTriggerId(Context context) {
        return (String) tvTriggerId.get(context);
    }
}
