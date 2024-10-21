package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageCaptionFlagsImpl implements ImageCaptionFlags {
    public static final ProcessStablePhenotypeFlag enableAutomaticCaptioningForAllImages;
    public static final ProcessStablePhenotypeFlag enableImageDescriptionV2;
    public static final ProcessStablePhenotypeFlag getImageDescriptionHighQualityThreshold;
    public static final ProcessStablePhenotypeFlag getImageDescriptionLowQualityThreshold;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        enableAutomaticCaptioningForAllImages = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("ImageCaption__enable_automatic_captioning_for_all_images", true, "com.google.android.marvin.talkback", of, true, false);
        enableImageDescriptionV2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("ImageCaption__enable_image_description_v2", true, "com.google.android.marvin.talkback", of, true, false);
        getImageDescriptionHighQualityThreshold = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing("ImageCaption__get_image_description_high_quality_threshold", 0.65d, "com.google.android.marvin.talkback", of, true, false);
        getImageDescriptionLowQualityThreshold = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing("ImageCaption__get_image_description_low_quality_threshold", 0.55d, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.ImageCaptionFlags
    public final boolean enableAutomaticCaptioningForAllImages(Context context) {
        return ((Boolean) enableAutomaticCaptioningForAllImages.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.ImageCaptionFlags
    public final boolean enableImageDescriptionV2(Context context) {
        return ((Boolean) enableImageDescriptionV2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.ImageCaptionFlags
    public final double getImageDescriptionHighQualityThreshold(Context context) {
        return ((Double) getImageDescriptionHighQualityThreshold.get(context)).doubleValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.ImageCaptionFlags
    public final double getImageDescriptionLowQualityThreshold(Context context) {
        return ((Double) getImageDescriptionLowQualityThreshold.get(context)).doubleValue();
    }
}
