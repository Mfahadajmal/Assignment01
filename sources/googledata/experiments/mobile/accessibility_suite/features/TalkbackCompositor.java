package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkbackCompositor implements Supplier {
    private static final TalkbackCompositor INSTANCE = new TalkbackCompositor();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new TalkbackCompositorFlagsImpl());

    public static boolean enablePeriodAsSeparator(Context context) {
        return INSTANCE.get().enablePeriodAsSeparator(context);
    }

    @Override // com.google.common.base.Supplier
    public final TalkbackCompositorFlags get() {
        return (TalkbackCompositorFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
