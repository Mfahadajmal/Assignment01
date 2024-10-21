package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleDisplayConfig implements Supplier {
    private static final BrailleDisplayConfig INSTANCE = new BrailleDisplayConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new BrailleDisplayConfigFlagsImpl());

    public static boolean cutCopyPaste(Context context) {
        return INSTANCE.get().cutCopyPaste(context);
    }

    public static boolean playPauseMedia(Context context) {
        return INSTANCE.get().playPauseMedia(context);
    }

    public static boolean selectAll(Context context) {
        return INSTANCE.get().selectAll(context);
    }

    public static boolean selectCurrentToStartOrEnd(Context context) {
        return INSTANCE.get().selectCurrentToStartOrEnd(context);
    }

    public static boolean selectPreviousNextCharacterWordLine(Context context) {
        return INSTANCE.get().selectPreviousNextCharacterWordLine(context);
    }

    @Override // com.google.common.base.Supplier
    public final BrailleDisplayConfigFlags get() {
        return (BrailleDisplayConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
