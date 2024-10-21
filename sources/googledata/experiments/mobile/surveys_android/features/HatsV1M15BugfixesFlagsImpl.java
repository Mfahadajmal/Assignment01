package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M15BugfixesFlagsImpl implements HatsV1M15BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixTouchEventCrash = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45381176", true, "com.google.android.libraries.surveys", RegularImmutableSet.EMPTY, true, false);

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M15BugfixesFlags
    public final boolean fixTouchEventCrash(Context context) {
        return ((Boolean) fixTouchEventCrash.get(context)).booleanValue();
    }
}
