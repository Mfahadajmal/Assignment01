package com.google.android.accessibility.talkback.imagecaption;

import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum ImageCaptionConstants$UninstallDialogResources {
    ICON_DETECTION(R.string.delete_icon_detection_dialog_title, R.string.delete_icon_description_hint),
    IMAGE_DESCRIPTION(R.string.delete_image_description_dialog_title, R.string.delete_image_description_hint);

    public final int deletedHintRes;
    public final int uninstallTitleRes;

    ImageCaptionConstants$UninstallDialogResources(int i, int i2) {
        this.uninstallTitleRes = i;
        this.deletedHintRes = i2;
    }
}
