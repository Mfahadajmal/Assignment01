package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DevopsFlagsImpl implements DevopsFlags {
    public static final ProcessStablePhenotypeFlag forceDisableAllFlags = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("1", false, "com.google.android.libraries.surveys", RegularImmutableSet.EMPTY, true, false);

    @Override // googledata.experiments.mobile.surveys_android.features.DevopsFlags
    public final boolean forceDisableAllFlags(Context context) {
        return ((Boolean) forceDisableAllFlags.get(context)).booleanValue();
    }
}
