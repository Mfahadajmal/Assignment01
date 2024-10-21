package googledata.experiments.mobile.surveys_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M4Features implements Supplier {
    public static final HatsV1M4Features INSTANCE = new HatsV1M4Features();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M4FeaturesFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final HatsV1M4FeaturesFlags get() {
        return (HatsV1M4FeaturesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
