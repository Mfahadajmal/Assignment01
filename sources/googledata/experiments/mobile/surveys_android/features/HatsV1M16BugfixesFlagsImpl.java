package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M16BugfixesFlagsImpl implements HatsV1M16BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixThankYouUrlMarginIssue = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45381533", true, "com.google.android.libraries.surveys", RegularImmutableSet.EMPTY, true, false);

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M16BugfixesFlags
    public final boolean fixThankYouUrlMarginIssue(Context context) {
        return ((Boolean) fixThankYouUrlMarginIssue.get(context)).booleanValue();
    }
}
