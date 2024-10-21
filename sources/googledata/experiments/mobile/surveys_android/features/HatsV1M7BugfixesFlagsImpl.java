package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M7BugfixesFlagsImpl implements HatsV1M7BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixThankyouMissingUrlQuery = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45350103", true, "com.google.android.libraries.surveys", RegularImmutableSet.EMPTY, true, false);

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M7BugfixesFlags
    public final boolean fixThankyouMissingUrlQuery(Context context) {
        return ((Boolean) fixThankyouMissingUrlQuery.get(context)).booleanValue();
    }
}
