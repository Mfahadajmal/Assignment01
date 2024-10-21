package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceFeature implements Supplier {
    public static final TraceFeature INSTANCE = new TraceFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new TraceFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final TraceFeatureFlags get() {
        return (TraceFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
