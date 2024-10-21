package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sCommonConfig implements Supplier {
    public static final S2sCommonConfig INSTANCE = new S2sCommonConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new S2sCommonConfigFlagsImpl());

    public static boolean enableImproveAccuracy(Context context) {
        return INSTANCE.get().enableImproveAccuracy(context);
    }

    @Override // com.google.common.base.Supplier
    public final S2sCommonConfigFlags get() {
        return (S2sCommonConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
