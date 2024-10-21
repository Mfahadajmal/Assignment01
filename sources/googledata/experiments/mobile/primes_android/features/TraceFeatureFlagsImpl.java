package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceFeatureFlagsImpl implements TraceFeatureFlags {
    public static final ProcessStablePhenotypeFlag defaultToUnifiedFormatForTiktokTraces;
    public static final ProcessStablePhenotypeFlag traceSamplingParameters;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        SingletonImmutableSet singletonImmutableSet = new SingletonImmutableSet("CLIENT_LOGGING_PROD");
        defaultToUnifiedFormatForTiktokTraces = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45625440", false, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        traceSamplingParameters = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("10", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(18), "EOgHGAQ", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.TraceFeatureFlags
    public final boolean defaultToUnifiedFormatForTiktokTraces(Context context) {
        return ((Boolean) defaultToUnifiedFormatForTiktokTraces.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.TraceFeatureFlags
    public final SystemHealthProto$SamplingParameters traceSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) traceSamplingParameters.get(context);
    }
}
