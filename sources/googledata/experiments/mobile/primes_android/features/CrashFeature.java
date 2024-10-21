package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashFeature implements Supplier {
    public static final CrashFeature INSTANCE = new CrashFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new CrashFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final CrashFeatureFlags get() {
        return (CrashFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
