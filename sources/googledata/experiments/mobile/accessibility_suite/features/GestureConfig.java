package googledata.experiments.mobile.accessibility_suite.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GestureConfig implements Supplier {
    public static final GestureConfig INSTANCE = new GestureConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new GestureConfigFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final GestureConfigFlags get() {
        return (GestureConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
