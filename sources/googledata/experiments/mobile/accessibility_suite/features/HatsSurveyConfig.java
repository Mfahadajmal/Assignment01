package googledata.experiments.mobile.accessibility_suite.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsSurveyConfig implements Supplier {
    public static final HatsSurveyConfig INSTANCE = new HatsSurveyConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsSurveyConfigFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final HatsSurveyConfigFlags get() {
        return (HatsSurveyConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
