package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeminiConfig implements Supplier {
    public static final GeminiConfig INSTANCE = new GeminiConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new GeminiConfigFlagsImpl());

    public static boolean enableOnDeviceGeminiImageCaptioning(Context context) {
        return INSTANCE.get().enableOnDeviceGeminiImageCaptioning(context);
    }

    public static boolean enableServerSideGeminiImageCaptioning(Context context) {
        return INSTANCE.get().enableServerSideGeminiImageCaptioning(context);
    }

    public static String geminiModel(Context context) {
        return INSTANCE.get().geminiModel(context);
    }

    @Override // com.google.common.base.Supplier
    public final GeminiConfigFlags get() {
        return (GeminiConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
