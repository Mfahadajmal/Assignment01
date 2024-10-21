package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JankFeatureFlagsImpl implements JankFeatureFlags {
    public static final ProcessStablePhenotypeFlag computeMaxAcceptedFrameTimeFromWindow;
    public static final ProcessStablePhenotypeFlag jankPerfettoConfigurations;
    public static final ProcessStablePhenotypeFlag jankSamplingParameters;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        SingletonImmutableSet singletonImmutableSet = new SingletonImmutableSet("CLIENT_LOGGING_PROD");
        computeMaxAcceptedFrameTimeFromWindow = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45408304", false, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        jankPerfettoConfigurations = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("40", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(11), "Ci1jb20uZ29vZ2xlLmFuZHJvaWQucHJpbWVzLWphbmstJVBBQ0tBR0VfTkFNRSUSIwgCEh9KPCVFVkVOVF9OQU1FJT4jbWlzc2VkQXBwRnJhbWVzEh8IAxIbSjwlRVZFTlRfTkFNRSU+I3RvdGFsRnJhbWVzEiYIBRIiSjwlRVZFTlRfTkFNRSU+I21heEZyYW1lVGltZU1pbGxpcw", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        jankSamplingParameters = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("13", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(12), "EAAYAg", "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.JankFeatureFlags
    public final boolean computeMaxAcceptedFrameTimeFromWindow(Context context) {
        return ((Boolean) computeMaxAcceptedFrameTimeFromWindow.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.JankFeatureFlags
    public final PerfettoTraceConfigurations$JankPerfettoConfigurations jankPerfettoConfigurations(Context context) {
        return (PerfettoTraceConfigurations$JankPerfettoConfigurations) jankPerfettoConfigurations.get(context);
    }

    @Override // googledata.experiments.mobile.primes_android.features.JankFeatureFlags
    public final SystemHealthProto$SamplingParameters jankSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) jankSamplingParameters.get(context);
    }
}
