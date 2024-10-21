package com.google.android.accessibility.talkback.actor;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.imagecaption.CaptionRequest;
import com.google.android.accessibility.talkback.imagecaption.Request;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.accessibility.utils.caption.Result;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ImageCaptioner$$ExternalSyntheticLambda6 implements CaptionRequest.OnErrorListener {
    public final /* synthetic */ ImageCaptioner f$0;
    public final /* synthetic */ AccessibilityNodeInfoCompat f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ImageCaptioner$$ExternalSyntheticLambda6(ImageCaptioner imageCaptioner, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        this.switching_field = i;
        this.f$0 = imageCaptioner;
        this.f$1 = accessibilityNodeInfoCompat;
    }

    @Override // com.google.android.accessibility.talkback.imagecaption.CaptionRequest.OnErrorListener
    public final void onError$ar$ds(CaptionRequest captionRequest, int i, boolean z) {
        int i2 = this.switching_field;
        if (i2 != 0) {
            if (i2 != 1) {
                long durationMillis = captionRequest.getDurationMillis();
                ImageCaptioner imageCaptioner = this.f$0;
                if (durationMillis != -1) {
                    imageCaptioner.primeLogImageCaptionEvent(PrimesController.TimerAction.IMAGE_CAPTION_IMAGE_DESCRIPTION_FAILED, captionRequest.getDurationMillis());
                }
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.f$1;
                imageCaptioner.analytics.onImageCaptionEvent(24);
                LogUtils.v("ImageCaptioner", "onError(), error=%s", Request.errorName(i));
                imageCaptioner.imageDescriptionRequests.performNextRequest();
                imageCaptioner.handleResult(captionRequest.requestId, AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat), new Result(ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION, null, 1.0f), z);
                return;
            }
            long durationMillis2 = captionRequest.getDurationMillis();
            ImageCaptioner imageCaptioner2 = this.f$0;
            if (durationMillis2 != -1) {
                imageCaptioner2.primeLogImageCaptionEvent(PrimesController.TimerAction.IMAGE_CAPTION_OCR_FAILED, captionRequest.getDurationMillis());
            }
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = this.f$1;
            imageCaptioner2.analytics.onImageCaptionEvent(12);
            LogUtils.v("ImageCaptioner", "onError(), error= %s", Request.errorName(i));
            imageCaptioner2.characterCaptionRequests.performNextRequest();
            imageCaptioner2.handleResult(captionRequest.requestId, AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat2), new Result(ImageCaptionUtils$CaptionType.OCR, null, 1.0f), z);
            return;
        }
        long durationMillis3 = captionRequest.getDurationMillis();
        ImageCaptioner imageCaptioner3 = this.f$0;
        if (durationMillis3 != -1) {
            imageCaptioner3.primeLogImageCaptionEvent(PrimesController.TimerAction.IMAGE_CAPTION_ICON_LABEL_FAILED, captionRequest.getDurationMillis());
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = this.f$1;
        imageCaptioner3.analytics.onImageCaptionEvent(8);
        LogUtils.v("ImageCaptioner", "onError(), error=%s", Request.errorName(i));
        imageCaptioner3.iconDetectionRequests.performNextRequest();
        imageCaptioner3.handleResult(captionRequest.requestId, AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat3), new Result(ImageCaptionUtils$CaptionType.ICON_LABEL, null, 1.0f), z);
    }
}
