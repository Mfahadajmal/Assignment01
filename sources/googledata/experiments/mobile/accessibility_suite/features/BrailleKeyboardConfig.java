package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleKeyboardConfig implements Supplier {
    public static final BrailleKeyboardConfig INSTANCE = new BrailleKeyboardConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new BrailleKeyboardConfigFlagsImpl());

    public static boolean holdAndSwipeGesture(Context context) {
        return INSTANCE.get().holdAndSwipeGesture(context);
    }

    @Override // com.google.common.base.Supplier
    public final BrailleKeyboardConfigFlags get() {
        return (BrailleKeyboardConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
