package googledata.experiments.mobile.gmscore.collection_basis_verifier.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifierFeatures implements Supplier {
    public static final CollectionBasisVerifierFeatures INSTANCE = new CollectionBasisVerifierFeatures();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new CollectionBasisVerifierFeaturesFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final CollectionBasisVerifierFeaturesFlags get() {
        return (CollectionBasisVerifierFeaturesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
