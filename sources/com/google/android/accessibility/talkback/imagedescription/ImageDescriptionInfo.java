package com.google.android.accessibility.talkback.imagedescription;

import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionInfo {
    public final float captionQualityScore;
    public final String captionText;
    public final ImmutableList labels;

    public ImageDescriptionInfo() {
    }

    public final float captionQualityScore() {
        return this.captionQualityScore;
    }

    public final String captionText() {
        return this.captionText;
    }

    public final boolean equals(Object obj) {
        String str;
        ImmutableList immutableList;
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImageDescriptionInfo) {
            ImageDescriptionInfo imageDescriptionInfo = (ImageDescriptionInfo) obj;
            if (Float.floatToIntBits(this.captionQualityScore) == Float.floatToIntBits(imageDescriptionInfo.captionQualityScore()) && ((str = this.captionText) != null ? str.equals(imageDescriptionInfo.captionText()) : imageDescriptionInfo.captionText() == null) && ((immutableList = this.labels) != null ? ContextDataProvider.equalsImpl(immutableList, imageDescriptionInfo.labels()) : imageDescriptionInfo.labels() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int floatToIntBits = Float.floatToIntBits(this.captionQualityScore) ^ 375623332;
        String str = this.captionText;
        int i = 0;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        int i2 = floatToIntBits * 1000003;
        ImmutableList immutableList = this.labels;
        if (immutableList != null) {
            i = immutableList.hashCode();
        }
        return ((i2 ^ hashCode) * 1000003) ^ i;
    }

    public final ImmutableList labels() {
        return this.labels;
    }

    public final String toString() {
        String format;
        String optionalTag = StringBuilderUtils.optionalTag("hasCaption", true);
        float captionQualityScore = captionQualityScore();
        if (captionQualityScore == -1.0f) {
            format = "";
        } else {
            format = String.format("%s=%s", "captionQualityScore", Float.valueOf(captionQualityScore));
        }
        return "ImageDescriptionInfo=".concat(String.valueOf(StringBuilderUtils.joinFields(optionalTag, format, StringBuilderUtils.optionalSubObj("captionText", captionText()), StringBuilderUtils.optionalSubObj("labels", labels()))));
    }

    public ImageDescriptionInfo(float f, String str, ImmutableList immutableList) {
        this();
        this.captionQualityScore = f;
        this.captionText = str;
        this.labels = immutableList;
    }
}
