package googledata.experiments.mobile.surveys_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Hats implements Supplier {
    public static final Hats INSTANCE = new Hats();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final HatsFlags get() {
        return (HatsFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
