package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricTransmitterFeatureFlagsImpl implements MetricTransmitterFeatureFlags {
    public static final ProcessStablePhenotypeFlag enableDelphiCollectionBasisLogVerifier;
    public static final ProcessStablePhenotypeFlag usePackedHistogramEncodingInClearcut;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        SingletonImmutableSet singletonImmutableSet = new SingletonImmutableSet("CLIENT_LOGGING_PROD");
        enableDelphiCollectionBasisLogVerifier = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45359255", false, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
        usePackedHistogramEncodingInClearcut = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("36", true, "com.google.android.libraries.performance.primes", singletonImmutableSet, true, true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.MetricTransmitterFeatureFlags
    public final boolean enableDelphiCollectionBasisLogVerifier(Context context) {
        return ((Boolean) enableDelphiCollectionBasisLogVerifier.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.MetricTransmitterFeatureFlags
    public final boolean usePackedHistogramEncodingInClearcut(Context context) {
        return ((Boolean) usePackedHistogramEncodingInClearcut.get(context)).booleanValue();
    }
}
