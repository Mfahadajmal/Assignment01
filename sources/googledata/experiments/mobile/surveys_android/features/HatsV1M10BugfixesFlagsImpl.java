package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M10BugfixesFlagsImpl implements HatsV1M10BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixAccessibilityFocus = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45351704", true, "com.google.android.libraries.surveys", RegularImmutableSet.EMPTY, true, false);

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M10BugfixesFlags
    public final boolean fixAccessibilityFocus(Context context) {
        return ((Boolean) fixAccessibilityFocus.get(context)).booleanValue();
    }
}
