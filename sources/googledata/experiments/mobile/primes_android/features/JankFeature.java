package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JankFeature implements Supplier {
    public static final JankFeature INSTANCE = new JankFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new JankFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final JankFeatureFlags get() {
        return (JankFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
