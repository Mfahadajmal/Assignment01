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
public final class StorageFeatureFlagsImpl implements StorageFeatureFlags {
    public static final ProcessStablePhenotypeFlag storageSamplingParameters;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        storageSamplingParameters = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("17", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(16), "EOgHGAM", "com.google.android.libraries.performance.primes", new SingletonImmutableSet("CLIENT_LOGGING_PROD"), true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.StorageFeatureFlags
    public final SystemHealthProto$SamplingParameters storageSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) storageSamplingParameters.get(context);
    }
}
