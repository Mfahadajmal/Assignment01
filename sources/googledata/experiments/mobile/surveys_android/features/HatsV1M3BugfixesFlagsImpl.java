package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M3BugfixesFlagsImpl implements HatsV1M3BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixLocaleLanguage;
    public static final ProcessStablePhenotypeFlag fixSplitWindowCrashes;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        fixLocaleLanguage = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("12", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        fixSplitWindowCrashes = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("11", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M3BugfixesFlags
    public final boolean fixLocaleLanguage(Context context) {
        return ((Boolean) fixLocaleLanguage.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M3BugfixesFlags
    public final boolean fixSplitWindowCrashes(Context context) {
        return ((Boolean) fixSplitWindowCrashes.get(context)).booleanValue();
    }
}
