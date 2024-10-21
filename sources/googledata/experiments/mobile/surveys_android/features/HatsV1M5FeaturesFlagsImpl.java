package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M5FeaturesFlagsImpl implements HatsV1M5FeaturesFlags {
    public static final ProcessStablePhenotypeFlag accessibilityHelperAllowlist;
    public static final ProcessStablePhenotypeFlag enableLandscapeImprovementsNonmodal;
    public static final ProcessStablePhenotypeFlag enablePreferredSurveyLanguages;
    public static final ProcessStablePhenotypeFlag enableTvAccessibilityHelper;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        accessibilityHelperAllowlist = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("20", "com.google.android.surveys.testapp,com.google.android.tvrecommendations,com.google.android.apps.tv.launcherx", "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        enableLandscapeImprovementsNonmodal = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("18", false, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        enablePreferredSurveyLanguages = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("22", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        enableTvAccessibilityHelper = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("21", false, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M5FeaturesFlags
    public final String accessibilityHelperAllowlist(Context context) {
        return (String) accessibilityHelperAllowlist.get(context);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M5FeaturesFlags
    public final boolean enableLandscapeImprovementsNonmodal(Context context) {
        return ((Boolean) enableLandscapeImprovementsNonmodal.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M5FeaturesFlags
    public final boolean enablePreferredSurveyLanguages(Context context) {
        return ((Boolean) enablePreferredSurveyLanguages.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M5FeaturesFlags
    public final boolean enableTvAccessibilityHelper(Context context) {
        return ((Boolean) enableTvAccessibilityHelper.get(context)).booleanValue();
    }
}
