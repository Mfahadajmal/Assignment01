package com.google.android.accessibility.talkback.imagecaption;

import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum ImageCaptionConstants$ImageCaptionPreferenceKeys {
    ICON_DETECTION(R.string.pref_icon_detection_download_dialog_shown_times, R.string.pref_icon_detection_download_dialog_do_no_show, R.string.pref_icon_detection_installed, R.string.pref_icon_detection_uninstalled, R.string.pref_auto_icon_detection_key, R.string.pref_auto_icon_detection_unlabelled_only_key),
    IMAGE_DESCRIPTION(R.string.pref_image_description_download_dialog_shown_times, R.string.pref_image_description_download_dialog_do_no_show, R.string.pref_image_description_installed, R.string.pref_image_description_uninstalled, R.string.pref_auto_image_description_key, R.string.pref_auto_image_description_unlabelled_only_key);

    public final int doNotShowKey;
    public final int downloadShownTimesKey;
    public final int installedKey;
    public final int switchKey;
    public final int switchOnUnlabelledOnlyKey;
    public final int uninstalledKey;

    ImageCaptionConstants$ImageCaptionPreferenceKeys(int i, int i2, int i3, int i4, int i5, int i6) {
        this.downloadShownTimesKey = i;
        this.doNotShowKey = i2;
        this.installedKey = i3;
        this.uninstalledKey = i4;
        this.switchKey = i5;
        this.switchOnUnlabelledOnlyKey = i6;
    }
}
