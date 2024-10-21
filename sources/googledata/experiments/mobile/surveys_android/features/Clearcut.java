package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Clearcut implements Supplier {
    public static final Clearcut INSTANCE = new Clearcut();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new ClearcutFlagsImpl());

    public static boolean enableLoggingViaClearcut(Context context) {
        return INSTANCE.get().enableLoggingViaClearcut(context);
    }

    @Override // com.google.common.base.Supplier
    public final ClearcutFlags get() {
        return (ClearcutFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
