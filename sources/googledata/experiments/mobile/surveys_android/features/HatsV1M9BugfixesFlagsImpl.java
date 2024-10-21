package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M9BugfixesFlagsImpl implements HatsV1M9BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixScreenInFlow = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45350840", true, "com.google.android.libraries.surveys", RegularImmutableSet.EMPTY, true, false);

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M9BugfixesFlags
    public final boolean fixScreenInFlow(Context context) {
        return ((Boolean) fixScreenInFlow.get(context)).booleanValue();
    }
}
