package googledata.experiments.mobile.accessibility_suite.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleCommonConfig implements Supplier {
    public static final BrailleCommonConfig INSTANCE = new BrailleCommonConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new BrailleCommonConfigFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final BrailleCommonConfigFlags get() {
        return (BrailleCommonConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
