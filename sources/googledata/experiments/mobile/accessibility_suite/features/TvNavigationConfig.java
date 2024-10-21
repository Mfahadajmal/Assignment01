package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TvNavigationConfig implements Supplier {
    public static final TvNavigationConfig INSTANCE = new TvNavigationConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new TvNavigationConfigFlagsImpl());

    public static long keyEventTimeoutMillis(Context context) {
        return INSTANCE.get().keyEventTimeoutMillis(context);
    }

    public static boolean letSystemHandleDpadCenterWhenFocusNotInSyncNew(Context context) {
        return INSTANCE.get().letSystemHandleDpadCenterWhenFocusNotInSyncNew(context);
    }

    public static boolean useHandlerThread(Context context) {
        return INSTANCE.get().useHandlerThread(context);
    }

    @Override // com.google.common.base.Supplier
    public final TvNavigationConfigFlags get() {
        return (TvNavigationConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
