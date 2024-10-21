package googledata.experiments.mobile.gmscore.collection_basis_verifier.features;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifierFeaturesFlagsImpl implements CollectionBasisVerifierFeaturesFlags {
    public static final ProcessStablePhenotypeFlag enableCbvV2;
    public static final ProcessStablePhenotypeFlag enableGoogleSignatureCheck;
    public static final ProcessStablePhenotypeFlag enableUsingLogVerifierResult;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        enableCbvV2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("CollectionBasisVerifierFeatures__enable_cbv_v2", false, "com.google.android.libraries.consentverifier", regularImmutableSet, true, false);
        enableGoogleSignatureCheck = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("CollectionBasisVerifierFeatures__enable_google_signature_check", false, "com.google.android.libraries.consentverifier", regularImmutableSet, true, false);
        enableUsingLogVerifierResult = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("CollectionBasisVerifierFeatures__enable_using_log_verifier_result", false, "com.google.android.libraries.consentverifier", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public final boolean enableCbvV2() {
        return ((Boolean) enableCbvV2.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public final boolean enableGoogleSignatureCheck() {
        return ((Boolean) enableGoogleSignatureCheck.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public final boolean enableUsingLogVerifierResult() {
        return ((Boolean) enableUsingLogVerifierResult.get()).booleanValue();
    }
}
