package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M3FeaturesFlagsImpl implements HatsV1M3FeaturesFlags {
    public static final ProcessStablePhenotypeFlag enableLandscapeImprovements;
    public static final ProcessStablePhenotypeFlag enableUserPromptedSurveys;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        enableLandscapeImprovements = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("13", false, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        enableUserPromptedSurveys = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("10", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M3FeaturesFlags
    public final boolean enableLandscapeImprovements(Context context) {
        return ((Boolean) enableLandscapeImprovements.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M3FeaturesFlags
    public final boolean enableUserPromptedSurveys(Context context) {
        return ((Boolean) enableUserPromptedSurveys.get(context)).booleanValue();
    }
}
