package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppExitFeatureFlagsImpl implements AppExitFeatureFlags {
    public static final ProcessStablePhenotypeFlag appExitCollectionEnabled;
    public static final ProcessStablePhenotypeFlag appExitReasonsToReport;
    public static final ProcessStablePhenotypeFlag enableCollectingAnrDiagnostics;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        SingletonImmutableSet singletonImmutableSet = new SingletonImmutableSet("CLIENT_LOGGING_PROD");
        appExitCollectionEnabled = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45352228", true, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        appExitReasonsToReport = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("45352241", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(5), "CAYIBAgFCAM", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        enableCollectingAnrDiagnostics = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45633315", false, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.AppExitFeatureFlags
    public final boolean appExitCollectionEnabled(Context context) {
        return ((Boolean) appExitCollectionEnabled.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.AppExitFeatureFlags
    public final ApplicationExitReasons appExitReasonsToReport(Context context) {
        return (ApplicationExitReasons) appExitReasonsToReport.get(context);
    }

    @Override // googledata.experiments.mobile.primes_android.features.AppExitFeatureFlags
    public final boolean enableCollectingAnrDiagnostics(Context context) {
        return ((Boolean) enableCollectingAnrDiagnostics.get(context)).booleanValue();
    }
}
