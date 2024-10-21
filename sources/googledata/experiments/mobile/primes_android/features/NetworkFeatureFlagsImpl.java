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
public final class NetworkFeatureFlagsImpl implements NetworkFeatureFlags {
    public static final ProcessStablePhenotypeFlag networkSamplingParameters;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        networkSamplingParameters = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("12", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(14), "EAAYAg", "com.google.android.libraries.performance.primes", new SingletonImmutableSet("CLIENT_LOGGING_PROD"), true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.NetworkFeatureFlags
    public final SystemHealthProto$SamplingParameters networkSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) networkSamplingParameters.get(context);
    }
}
