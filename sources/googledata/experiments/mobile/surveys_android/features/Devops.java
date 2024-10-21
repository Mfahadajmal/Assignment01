package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Devops implements Supplier {
    private static final Devops INSTANCE = new Devops();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new DevopsFlagsImpl());

    public static boolean forceDisableAllFlags(Context context) {
        return INSTANCE.get().forceDisableAllFlags(context);
    }

    @Override // com.google.common.base.Supplier
    public final DevopsFlags get() {
        return (DevopsFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
