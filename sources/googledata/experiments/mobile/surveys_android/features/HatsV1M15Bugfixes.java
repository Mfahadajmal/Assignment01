package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M15Bugfixes implements Supplier {
    private static final HatsV1M15Bugfixes INSTANCE = new HatsV1M15Bugfixes();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M15BugfixesFlagsImpl());

    public static boolean fixTouchEventCrash(Context context) {
        return INSTANCE.get().fixTouchEventCrash(context);
    }

    @Override // com.google.common.base.Supplier
    public final HatsV1M15BugfixesFlags get() {
        return (HatsV1M15BugfixesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
