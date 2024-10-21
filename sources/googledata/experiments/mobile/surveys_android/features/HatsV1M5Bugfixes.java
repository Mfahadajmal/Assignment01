package googledata.experiments.mobile.surveys_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M5Bugfixes implements Supplier {
    public static final HatsV1M5Bugfixes INSTANCE = new HatsV1M5Bugfixes();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M5BugfixesFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final HatsV1M5BugfixesFlags get() {
        return (HatsV1M5BugfixesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
