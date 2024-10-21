package googledata.experiments.mobile.accessibility_suite.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextEditingConfig implements Supplier {
    public static final TextEditingConfig INSTANCE = new TextEditingConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new TextEditingConfigFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final TextEditingConfigFlags get() {
        return (TextEditingConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
