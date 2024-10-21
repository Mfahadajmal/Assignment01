package com.google.android.accessibility.talkback.imagecaption;

import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum ImageCaptionConstants$FeatureSwitchDialogResources {
    ICON_DETECTION(R.string.switch_icon_detection_dialog_title, R.string.switch_icon_detection_dialog_message, R.string.pref_auto_icon_detection_key, R.bool.pref_auto_icon_detection_default, R.string.pref_auto_icon_detection_unlabelled_only_key, R.bool.pref_auto_icon_detection_unlabelled_only_default),
    IMAGE_DESCRIPTION(R.string.title_pref_image_description, R.string.switch_image_description_dialog_message, R.string.pref_auto_image_description_key, R.bool.pref_auto_image_description_default, R.string.pref_auto_image_description_unlabelled_only_key, R.bool.pref_auto_image_description_unlabelled_only_default),
    TEXT_RECOGNITION(R.string.title_pref_text_recognition, R.string.switch_text_recognition_dialog_message, R.string.pref_auto_text_recognition_key, R.bool.pref_auto_text_recognition_default, R.string.pref_auto_text_recognition_unlabelled_only_key, R.bool.pref_auto_text_recognition_unlabelled_only_default),
    IMAGE_DESCRIPTION_AICORE_OPT_IN(R.string.title_pref_image_description, R.string.dialog_message_on_device_ai_description, R.string.pref_auto_on_devices_image_description_key, R.bool.pref_auto_on_device_image_description_default, R.string.pref_auto_on_device_image_description_unlabelled_only_key, R.bool.pref_auto_on_device_image_description_unlabelled_only_default, 1),
    IMAGE_DESCRIPTION_AICORE_SCOPE(R.string.title_pref_image_description, R.string.switch_image_description_dialog_message, R.string.pref_auto_on_devices_image_description_key, R.bool.pref_auto_on_device_image_description_default, R.string.pref_auto_on_device_image_description_unlabelled_only_key, R.bool.pref_auto_on_device_image_description_unlabelled_only_default),
    DETAILED_IMAGE_DESCRIPTION(R.string.title_pref_detailed_image_description, R.string.dialog_message_detailed_ai_description, R.string.pref_detailed_image_description_key, R.bool.pref_detailed_image_description_default, -1, -1, 1);

    public final int descriptionType;
    public final int messageRes;
    public final int switchDefaultValue;
    public final int switchKey;
    public final int switchOnUnlabelledOnlyDefaultValue;
    public final int switchOnUnlabelledOnlyKey;
    public final int titleRes;

    ImageCaptionConstants$FeatureSwitchDialogResources(int i, int i2, int i3, int i4, int i5, int i6) {
        this(i, i2, i3, i4, i5, i6, 0);
    }

    ImageCaptionConstants$FeatureSwitchDialogResources(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.titleRes = i;
        this.messageRes = i2;
        this.switchKey = i3;
        this.switchDefaultValue = i4;
        this.switchOnUnlabelledOnlyKey = i5;
        this.switchOnUnlabelledOnlyDefaultValue = i6;
        this.descriptionType = i7;
    }
}
