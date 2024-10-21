package googledata.experiments.mobile.accessibility_suite.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sHatsSurveyConfig implements Supplier {
    public static final S2sHatsSurveyConfig INSTANCE = new S2sHatsSurveyConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new S2sHatsSurveyConfigFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final S2sHatsSurveyConfigFlags get() {
        return (S2sHatsSurveyConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
