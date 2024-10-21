package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppExitFeature implements Supplier {
    public static final AppExitFeature INSTANCE = new AppExitFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new AppExitFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final AppExitFeatureFlags get() {
        return (AppExitFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
