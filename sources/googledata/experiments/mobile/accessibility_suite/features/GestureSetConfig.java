package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GestureSetConfig implements Supplier {
    private static final GestureSetConfig INSTANCE = new GestureSetConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new GestureSetConfigFlagsImpl());

    public static boolean activateMultipleGestureSet(Context context) {
        return INSTANCE.get().activateMultipleGestureSet(context);
    }

    @Override // com.google.common.base.Supplier
    public final GestureSetConfigFlags get() {
        return (GestureSetConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
