package googledata.experiments.mobile.gmscore.collection_basis_verifier.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;
import com.google.mlkit.logging.schema.OnDeviceFaceDetectionLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifierRedactedFeatures implements Supplier {
    public static final CollectionBasisVerifierRedactedFeatures INSTANCE = new CollectionBasisVerifierRedactedFeatures();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new OnDeviceFaceDetectionLogEvent(null));

    @Override // com.google.common.base.Supplier
    /* renamed from: get$ar$class_merging$e2256824_0$ar$class_merging$ar$class_merging, reason: merged with bridge method [inline-methods] */
    public final OnDeviceFaceDetectionLogEvent get() {
        return (OnDeviceFaceDetectionLogEvent) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
