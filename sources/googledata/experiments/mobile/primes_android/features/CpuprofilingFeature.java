package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuprofilingFeature implements Supplier {
    public static final CpuprofilingFeature INSTANCE = new CpuprofilingFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new CpuprofilingFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final CpuprofilingFeatureFlags get() {
        return (CpuprofilingFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
