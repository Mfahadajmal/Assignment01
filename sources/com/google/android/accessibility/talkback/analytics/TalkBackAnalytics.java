package com.google.android.accessibility.talkback.analytics;

import android.content.SharedPreferences;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.input.CursorGranularity;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackAnalytics implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ImageCaptionLogKeys {
        ICON_DETECTION(17, 18, 15, 16, 19, 20),
        IMAGE_DESCRIPTION(28, 29, 26, 27, 30, 31);

        public final int installDeny;
        public final int installFail;
        public final int installRequest;
        public final int installSuccess;
        public final int uninstallDeny;
        public final int uninstallRequest;

        ImageCaptionLogKeys(int i, int i2, int i3, int i4, int i5, int i6) {
            this.installSuccess = i;
            this.installFail = i2;
            this.installRequest = i3;
            this.installDeny = i4;
            this.uninstallRequest = i5;
            this.uninstallDeny = i6;
        }
    }

    public void logPendingChanges() {
    }

    public void onGesture(int i) {
    }

    public void onImageCaptionEvent(int i) {
    }

    public void onManuallyChangeSetting$ar$ds(String str) {
    }

    public void onMoveWithGranularity(CursorGranularity cursorGranularity) {
    }

    public void onSelectorActionEvent(SelectorController.Setting setting) {
    }

    public void onShortcutActionEvent(GestureShortcutMapping.TalkbackAction talkbackAction) {
    }

    public void onVoiceCommandEvent(int i) {
    }

    public void onGeminiFailEvent(int i, boolean z) {
    }

    public void onGeminiOptInEvent(int i, boolean z) {
    }

    public void onLocalContextMenuAction(int i, int i2) {
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
    }

    public void onGeminiEvent(int i, boolean z, boolean z2) {
    }
}
