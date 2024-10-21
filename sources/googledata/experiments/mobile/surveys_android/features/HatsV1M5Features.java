package googledata.experiments.mobile.surveys_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M5Features implements Supplier {
    public static final HatsV1M5Features INSTANCE = new HatsV1M5Features();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M5FeaturesFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final HatsV1M5FeaturesFlags get() {
        return (HatsV1M5FeaturesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
