package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageFeature implements Supplier {
    public static final StorageFeature INSTANCE = new StorageFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new StorageFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final StorageFeatureFlags get() {
        return (StorageFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
