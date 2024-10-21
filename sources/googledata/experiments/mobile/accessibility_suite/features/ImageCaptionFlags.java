package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ImageCaptionFlags {
    boolean enableAutomaticCaptioningForAllImages(Context context);

    boolean enableImageDescriptionV2(Context context);

    double getImageDescriptionHighQualityThreshold(Context context);

    double getImageDescriptionLowQualityThreshold(Context context);
}
