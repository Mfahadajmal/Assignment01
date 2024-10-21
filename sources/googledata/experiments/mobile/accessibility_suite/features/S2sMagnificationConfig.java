package googledata.experiments.mobile.accessibility_suite.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sMagnificationConfig implements Supplier {
    public static final S2sMagnificationConfig INSTANCE = new S2sMagnificationConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new S2sMagnificationConfigFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final S2sMagnificationConfigFlags get() {
        return (S2sMagnificationConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
