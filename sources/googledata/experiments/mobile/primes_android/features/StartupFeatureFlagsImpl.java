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
public final class StartupFeatureFlagsImpl implements StartupFeatureFlags {
    public static final ProcessStablePhenotypeFlag enableStartupBaselineDiscarding;
    public static final ProcessStablePhenotypeFlag firstDrawType;
    public static final ProcessStablePhenotypeFlag startupSamplingParameters;
    public static final ProcessStablePhenotypeFlag warmStartType;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        SingletonImmutableSet singletonImmutableSet = new SingletonImmutableSet("CLIENT_LOGGING_PROD");
        enableStartupBaselineDiscarding = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("3", false, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        firstDrawType = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$64c97a1b_0("45357887", 1L, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        startupSamplingParameters = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("19", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(15), "EAAYAg", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        warmStartType = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$64c97a1b_0("45628530", 0L, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.StartupFeatureFlags
    public final boolean enableStartupBaselineDiscarding(Context context) {
        return ((Boolean) enableStartupBaselineDiscarding.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.StartupFeatureFlags
    public final long firstDrawType(Context context) {
        return ((Long) firstDrawType.get(context)).longValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.StartupFeatureFlags
    public final SystemHealthProto$SamplingParameters startupSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) startupSamplingParameters.get(context);
    }

    @Override // googledata.experiments.mobile.primes_android.features.StartupFeatureFlags
    public final long warmStartType(Context context) {
        return ((Long) warmStartType.get(context)).longValue();
    }
}
