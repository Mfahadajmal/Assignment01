package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryFeature implements Supplier {
    public static final BatteryFeature INSTANCE = new BatteryFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new BatteryFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final BatteryFeatureFlags get() {
        return (BatteryFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
