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
public final class CpuprofilingFeatureFlagsImpl implements CpuprofilingFeatureFlags {
    public static final ProcessStablePhenotypeFlag cpuprofilingSamplingParameters;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        cpuprofilingSamplingParameters = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("15", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(7), "EAAYAg", "com.google.android.libraries.performance.primes", new SingletonImmutableSet("CLIENT_LOGGING_PROD"), true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.CpuprofilingFeatureFlags
    public final SystemHealthProto$SamplingParameters cpuprofilingSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) cpuprofilingSamplingParameters.get(context);
    }
}
