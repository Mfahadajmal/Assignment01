package googledata.experiments.mobile.surveys_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M10Bugfixes implements Supplier {
    public static final HatsV1M10Bugfixes INSTANCE = new HatsV1M10Bugfixes();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M10BugfixesFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final HatsV1M10BugfixesFlags get() {
        return (HatsV1M10BugfixesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
