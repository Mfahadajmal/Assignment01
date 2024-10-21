package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeechConfig implements Supplier {
    public static final SpeechConfig INSTANCE = new SpeechConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new SpeechConfigFlagsImpl());

    public static boolean enableCacheTtsLocale(Context context) {
        return INSTANCE.get().enableCacheTtsLocale(context);
    }

    public static boolean removeUnnecessarySpans(Context context) {
        return INSTANCE.get().removeUnnecessarySpans(context);
    }

    @Override // com.google.common.base.Supplier
    public final SpeechConfigFlags get() {
        return (SpeechConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
