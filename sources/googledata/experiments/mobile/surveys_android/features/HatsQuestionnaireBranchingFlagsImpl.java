package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsQuestionnaireBranchingFlagsImpl implements HatsQuestionnaireBranchingFlags {
    public static final ProcessStablePhenotypeFlag enableQuestionnaireBranching;
    public static final ProcessStablePhenotypeFlag enableRatingQuestionnaireBranching;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        enableQuestionnaireBranching = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45367388", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        enableRatingQuestionnaireBranching = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45373269", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsQuestionnaireBranchingFlags
    public final boolean enableQuestionnaireBranching(Context context) {
        return ((Boolean) enableQuestionnaireBranching.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsQuestionnaireBranchingFlags
    public final boolean enableRatingQuestionnaireBranching(Context context) {
        return ((Boolean) enableRatingQuestionnaireBranching.get(context)).booleanValue();
    }
}
