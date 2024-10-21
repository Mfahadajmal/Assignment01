package googledata.experiments.mobile.primes_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerFeature implements Supplier {
    public static final TimerFeature INSTANCE = new TimerFeature();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new TimerFeatureFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final TimerFeatureFlags get() {
        return (TimerFeatureFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
