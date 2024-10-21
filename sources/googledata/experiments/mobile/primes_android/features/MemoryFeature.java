package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryFeature implements Supplier {
    public static final MemoryFeature INSTANCE = new MemoryFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new MemoryFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final MemoryFeatureFlags get() {
        return (MemoryFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
