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
public final class TimerFeatureFlagsImpl implements TimerFeatureFlags {
    public static final ProcessStablePhenotypeFlag timerSamplingParameters;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        timerSamplingParameters = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("9", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(17), "EOgHGAQ", "com.google.android.libraries.performance.primes", new SingletonImmutableSet("CLIENT_LOGGING_PROD"), true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.TimerFeatureFlags
    public final SystemHealthProto$SamplingParameters timerSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) timerSamplingParameters.get(context);
    }
}
