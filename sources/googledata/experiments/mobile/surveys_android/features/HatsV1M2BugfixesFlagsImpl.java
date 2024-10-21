package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M2BugfixesFlagsImpl implements HatsV1M2BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixProtoliteMergefromNpe;
    public static final ProcessStablePhenotypeFlag onlySendZwiebackWhenNoGaiaIsPresent;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        fixProtoliteMergefromNpe = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("8", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        onlySendZwiebackWhenNoGaiaIsPresent = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("5", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M2BugfixesFlags
    public final boolean fixProtoliteMergefromNpe(Context context) {
        return ((Boolean) fixProtoliteMergefromNpe.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M2BugfixesFlags
    public final boolean onlySendZwiebackWhenNoGaiaIsPresent(Context context) {
        return ((Boolean) onlySendZwiebackWhenNoGaiaIsPresent.get(context)).booleanValue();
    }
}
