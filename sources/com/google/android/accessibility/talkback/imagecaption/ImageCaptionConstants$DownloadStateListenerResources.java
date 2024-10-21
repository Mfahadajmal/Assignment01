package com.google.android.accessibility.talkback.imagecaption;

import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum ImageCaptionConstants$DownloadStateListenerResources {
    ICON_DETECTION(R.string.download_icon_detection_successful_hint, R.string.download_icon_detection_failed_hint, R.string.downloading_icon_detection_hint),
    IMAGE_DESCRIPTION(R.string.download_image_description_successful_hint, R.string.download_image_description_failed_hint, R.string.downloading_image_description_hint);

    public final int downloadFailedHint;
    public final int downloadSuccessfulHint;
    public final int downloadingHint;

    ImageCaptionConstants$DownloadStateListenerResources(int i, int i2, int i3) {
        this.downloadSuccessfulHint = i;
        this.downloadFailedHint = i2;
        this.downloadingHint = i3;
    }
}
