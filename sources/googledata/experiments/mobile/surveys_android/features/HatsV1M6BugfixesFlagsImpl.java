package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M6BugfixesFlagsImpl implements HatsV1M6BugfixesFlags {
    public static final ProcessStablePhenotypeFlag fixCrashJavaCronetEngine;
    public static final ProcessStablePhenotypeFlag fixOrientationChangeDismissal;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        fixCrashJavaCronetEngine = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("28", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        fixOrientationChangeDismissal = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("29", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M6BugfixesFlags
    public final boolean fixCrashJavaCronetEngine(Context context) {
        return ((Boolean) fixCrashJavaCronetEngine.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M6BugfixesFlags
    public final boolean fixOrientationChangeDismissal(Context context) {
        return ((Boolean) fixOrientationChangeDismissal.get(context)).booleanValue();
    }
}
