package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsFlagsImpl implements HatsFlags {
    public static final ProcessStablePhenotypeFlag libraryVersion;
    public static final ProcessStablePhenotypeFlag libraryVersionDev;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        libraryVersion = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("3", "1", "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        libraryVersionDev = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("45403852", "1.13.531108431", "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsFlags
    public final String libraryVersion(Context context) {
        return (String) libraryVersion.get(context);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsFlags
    public final String libraryVersionDev(Context context) {
        return (String) libraryVersionDev.get(context);
    }
}
