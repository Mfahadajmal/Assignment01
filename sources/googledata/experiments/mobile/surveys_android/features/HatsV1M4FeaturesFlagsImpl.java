package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M4FeaturesFlagsImpl implements HatsV1M4FeaturesFlags {
    public static final ProcessStablePhenotypeFlag enableMinValidTriggerTimeBypass = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("14", true, "com.google.android.libraries.surveys", RegularImmutableSet.EMPTY, true, false);

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M4FeaturesFlags
    public final boolean enableMinValidTriggerTimeBypass(Context context) {
        return ((Boolean) enableMinValidTriggerTimeBypass.get(context)).booleanValue();
    }
}
