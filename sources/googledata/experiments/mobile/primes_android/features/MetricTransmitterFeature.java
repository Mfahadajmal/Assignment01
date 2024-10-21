package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricTransmitterFeature implements Supplier {
    public static final MetricTransmitterFeature INSTANCE = new MetricTransmitterFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new MetricTransmitterFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final MetricTransmitterFeatureFlags get() {
        return (MetricTransmitterFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
