package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M5BugfixesFlagsImpl implements HatsV1M5BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixSuccessionModeControlMeasurement = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("19", true, "com.google.android.libraries.surveys", RegularImmutableSet.EMPTY, true, false);

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M5BugfixesFlags
    public final boolean fixSuccessionModeControlMeasurement(Context context) {
        return ((Boolean) fixSuccessionModeControlMeasurement.get(context)).booleanValue();
    }
}
