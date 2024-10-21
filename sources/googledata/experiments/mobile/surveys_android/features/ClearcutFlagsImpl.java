package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutFlagsImpl implements ClearcutFlags {
    public static final ProcessStablePhenotypeFlag clearcutLogSourceName;
    public static final ProcessStablePhenotypeFlag disableLoggingForLoggedInUsers;
    public static final ProcessStablePhenotypeFlag enableLoggingViaClearcut;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        clearcutLogSourceName = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("7", "SURVEYS", "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        disableLoggingForLoggedInUsers = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("9", false, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        enableLoggingViaClearcut = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("6", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.ClearcutFlags
    public final String clearcutLogSourceName(Context context) {
        return (String) clearcutLogSourceName.get(context);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.ClearcutFlags
    public final boolean disableLoggingForLoggedInUsers(Context context) {
        return ((Boolean) disableLoggingForLoggedInUsers.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.ClearcutFlags
    public final boolean enableLoggingViaClearcut(Context context) {
        return ((Boolean) enableLoggingViaClearcut.get(context)).booleanValue();
    }
}
