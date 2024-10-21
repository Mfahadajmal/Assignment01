package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M3Features implements Supplier {
    public static final HatsV1M3Features INSTANCE = new HatsV1M3Features();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsV1M3FeaturesFlagsImpl());

    public static boolean enableUserPromptedSurveys(Context context) {
        return INSTANCE.get().enableUserPromptedSurveys(context);
    }

    @Override // com.google.common.base.Supplier
    public final HatsV1M3FeaturesFlags get() {
        return (HatsV1M3FeaturesFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
