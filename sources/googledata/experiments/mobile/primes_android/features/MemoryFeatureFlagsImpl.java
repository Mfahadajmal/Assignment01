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
public final class MemoryFeatureFlagsImpl implements MemoryFeatureFlags {
    public static final ProcessStablePhenotypeFlag memoizeConfigsProvider;
    public static final ProcessStablePhenotypeFlag memorySamplingParameters;
    public static final ProcessStablePhenotypeFlag periodicMemoryCollectionPeriodMs;
    public static final ProcessStablePhenotypeFlag readCorrectProcStatus;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        SingletonImmutableSet singletonImmutableSet = new SingletonImmutableSet("CLIENT_LOGGING_PROD");
        memoizeConfigsProvider = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45415027", true, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        memorySamplingParameters = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("8", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(13), "EOgHGAQ", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        periodicMemoryCollectionPeriodMs = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$64c97a1b_0("45401381", 3600000L, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        readCorrectProcStatus = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45420903", false, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.MemoryFeatureFlags
    public final boolean memoizeConfigsProvider(Context context) {
        return ((Boolean) memoizeConfigsProvider.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.MemoryFeatureFlags
    public final SystemHealthProto$SamplingParameters memorySamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) memorySamplingParameters.get(context);
    }

    @Override // googledata.experiments.mobile.primes_android.features.MemoryFeatureFlags
    public final long periodicMemoryCollectionPeriodMs(Context context) {
        return ((Long) periodicMemoryCollectionPeriodMs.get(context)).longValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.MemoryFeatureFlags
    public final boolean readCorrectProcStatus(Context context) {
        return ((Boolean) readCorrectProcStatus.get(context)).booleanValue();
    }
}
