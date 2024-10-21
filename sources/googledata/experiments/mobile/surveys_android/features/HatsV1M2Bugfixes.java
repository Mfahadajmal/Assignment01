package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M2Bugfixes implements Supplier {
    public static final HatsV1M2Bugfixes INSTANCE = new HatsV1M2Bugfixes();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M2BugfixesFlagsImpl());

    public static boolean fixProtoliteMergefromNpe(Context context) {
        return INSTANCE.get().fixProtoliteMergefromNpe(context);
    }

    @Override // com.google.common.base.Supplier
    public final HatsV1M2BugfixesFlags get() {
        return (HatsV1M2BugfixesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
