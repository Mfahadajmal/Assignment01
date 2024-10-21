package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sCommonConfigFlagsImpl implements S2sCommonConfigFlags {
    public static final ProcessStablePhenotypeFlag enableChangeVoice;
    public static final ProcessStablePhenotypeFlag enableImproveAccuracy;
    public static final ProcessStablePhenotypeFlag enableSnapshot;
    public static final ProcessStablePhenotypeFlag enableSnapshotDetail;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        enableChangeVoice = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("S2sCommonConfig__enable_change_voice", true, "com.google.android.marvin.talkback", of, true, false);
        enableImproveAccuracy = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("S2sCommonConfig__enable_improve_accuracy", false, "com.google.android.marvin.talkback", of, true, false);
        enableSnapshot = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("S2sCommonConfig__enable_snapshot", false, "com.google.android.marvin.talkback", of, true, false);
        enableSnapshotDetail = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("S2sCommonConfig__enable_snapshot_detail", false, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sCommonConfigFlags
    public final boolean enableChangeVoice(Context context) {
        return ((Boolean) enableChangeVoice.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sCommonConfigFlags
    public final boolean enableImproveAccuracy(Context context) {
        return ((Boolean) enableImproveAccuracy.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sCommonConfigFlags
    public final boolean enableSnapshot(Context context) {
        return ((Boolean) enableSnapshot.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sCommonConfigFlags
    public final boolean enableSnapshotDetail(Context context) {
        return ((Boolean) enableSnapshotDetail.get(context)).booleanValue();
    }
}
