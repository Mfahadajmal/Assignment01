package com.google.android.accessibility.talkback.actor;

import android.graphics.Bitmap;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.imagecaption.ScreenshotCaptureRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ImageCaptioner$$ExternalSyntheticLambda11 implements ScreenshotCaptureRequest.OnFinishListener {
    public final /* synthetic */ ImageCaptioner f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ImageCaptioner$$ExternalSyntheticLambda11(ImageCaptioner imageCaptioner, int i) {
        this.switching_field = i;
        this.f$0 = imageCaptioner;
    }

    @Override // com.google.android.accessibility.talkback.imagecaption.ScreenshotCaptureRequest.OnFinishListener
    public final void onFinish(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, boolean z, boolean z2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                this.f$0.handleScreenshotCaptureResponse(accessibilityNodeInfoCompat, bitmap, z, z2);
                return;
            } else {
                this.f$0.processImageCaptioningWithGemini(accessibilityNodeInfoCompat, bitmap, z, z2, true);
                return;
            }
        }
        this.f$0.processImageCaptioningWithGemini(accessibilityNodeInfoCompat, bitmap, z, z2, false);
    }
}
