package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageCaption implements Supplier {
    public static final ImageCaption INSTANCE = new ImageCaption();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new ImageCaptionFlagsImpl());

    public static boolean enableAutomaticCaptioningForAllImages(Context context) {
        return INSTANCE.get().enableAutomaticCaptioningForAllImages(context);
    }

    public static double getImageDescriptionHighQualityThreshold(Context context) {
        return INSTANCE.get().getImageDescriptionHighQualityThreshold(context);
    }

    public static double getImageDescriptionLowQualityThreshold(Context context) {
        return INSTANCE.get().getImageDescriptionLowQualityThreshold(context);
    }

    @Override // com.google.common.base.Supplier
    public final ImageCaptionFlags get() {
        return (ImageCaptionFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
