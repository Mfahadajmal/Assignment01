package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirectionalNavigationConfig implements Supplier {
    private static final DirectionalNavigationConfig INSTANCE = new DirectionalNavigationConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new DirectionalNavigationConfigFlagsImpl());

    public static boolean allowFocusResting(Context context) {
        return INSTANCE.get().allowFocusResting(context);
    }

    @Override // com.google.common.base.Supplier
    public final DirectionalNavigationConfigFlags get() {
        return (DirectionalNavigationConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
