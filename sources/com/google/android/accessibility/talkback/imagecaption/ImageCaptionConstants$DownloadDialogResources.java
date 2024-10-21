package com.google.android.accessibility.talkback.imagecaption;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum ImageCaptionConstants$DownloadDialogResources {
    ICON_DETECTION(R.string.confirm_download_icon_detection_title, R.string.confirm_download_icon_detection_message, 50),
    IMAGE_DESCRIPTION(R.string.confirm_download_image_description_title, R.string.confirm_download_image_description_message, BrailleInputEvent.CMD_HEADING_NEXT);

    public final int downloadMessageRes;
    public final int downloadTitleRes;
    public final int moduleSizeInMb;

    ImageCaptionConstants$DownloadDialogResources(int i, int i2, int i3) {
        this.downloadTitleRes = i;
        this.downloadMessageRes = i2;
        this.moduleSizeInMb = i3;
    }
}
