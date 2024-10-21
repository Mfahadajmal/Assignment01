package googledata.experiments.mobile.surveys_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M14Bugfixes implements Supplier {
    public static final HatsV1M14Bugfixes INSTANCE = new HatsV1M14Bugfixes();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M14BugfixesFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final HatsV1M14BugfixesFlags get() {
        return (HatsV1M14BugfixesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
