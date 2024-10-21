package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkFeature implements Supplier {
    public static final NetworkFeature INSTANCE = new NetworkFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new NetworkFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final NetworkFeatureFlags get() {
        return (NetworkFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
