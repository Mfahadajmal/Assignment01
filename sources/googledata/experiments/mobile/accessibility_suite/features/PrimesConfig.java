package googledata.experiments.mobile.accessibility_suite.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesConfig implements Supplier {
    public static final PrimesConfig INSTANCE = new PrimesConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new PrimesConfigFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final PrimesConfigFlags get() {
        return (PrimesConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
