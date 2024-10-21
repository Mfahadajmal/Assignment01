package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M3Bugfixes implements Supplier {
    private static final HatsV1M3Bugfixes INSTANCE = new HatsV1M3Bugfixes();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M3BugfixesFlagsImpl());

    public static boolean fixLocaleLanguage(Context context) {
        return INSTANCE.get().fixLocaleLanguage(context);
    }

    public static boolean fixSplitWindowCrashes(Context context) {
        return INSTANCE.get().fixSplitWindowCrashes(context);
    }

    @Override // com.google.common.base.Supplier
    public final HatsV1M3BugfixesFlags get() {
        return (HatsV1M3BugfixesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
