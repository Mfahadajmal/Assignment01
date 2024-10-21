package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupFeature implements Supplier {
    public static final StartupFeature INSTANCE = new StartupFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new StartupFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final StartupFeatureFlags get() {
        return (StartupFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
