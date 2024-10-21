package com.google.android.accessibility.talkback.actor;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.imagecaption.CaptionRequest;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.caption.Result;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ImageCaptioner$$ExternalSyntheticLambda5 implements CaptionRequest.OnFinishListener {
    public final /* synthetic */ ImageCaptioner f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ImageCaptioner$$ExternalSyntheticLambda5(ImageCaptioner imageCaptioner, int i) {
        this.switching_field = i;
        this.f$0 = imageCaptioner;
    }

    @Override // com.google.android.accessibility.talkback.imagecaption.CaptionRequest.OnFinishListener
    public final void onCaptionFinish(CaptionRequest captionRequest, AccessibilityNode accessibilityNode, Result result, boolean z) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                long durationMillis = captionRequest.getDurationMillis();
                ImageCaptioner imageCaptioner = this.f$0;
                if (durationMillis != -1) {
                    imageCaptioner.primeLogImageCaptionEvent(PrimesController.TimerAction.IMAGE_CAPTION_IMAGE_DESCRIPTION_SUCCEED, captionRequest.getDurationMillis());
                }
                imageCaptioner.analytics.onImageCaptionEvent(22);
                LogUtils.v("ImageCaptioner", "onImageDescriptionFinish() ".concat(String.valueOf(StringBuilderUtils.joinFields(StringBuilderUtils.optionalSubObj("result", result.text), StringBuilderUtils.optionalSubObj("node", accessibilityNode)))), new Object[0]);
                imageCaptioner.imageDescriptionRequests.performNextRequest();
                imageCaptioner.handleResult(captionRequest.requestId, accessibilityNode, result, z);
                LogUtils.v("ImageCaptionStorage", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(result, "Image Description result (", ") should not be stored."), new Object[0]);
                return;
            }
            long durationMillis2 = captionRequest.getDurationMillis();
            ImageCaptioner imageCaptioner2 = this.f$0;
            if (durationMillis2 != -1) {
                imageCaptioner2.primeLogImageCaptionEvent(PrimesController.TimerAction.IMAGE_CAPTION_OCR_SUCCEED, captionRequest.getDurationMillis());
            }
            imageCaptioner2.analytics.onImageCaptionEvent(10);
            LogUtils.v("ImageCaptioner", "onCharacterCaptionFinish() ".concat(String.valueOf(StringBuilderUtils.joinFields(StringBuilderUtils.optionalSubObj("result", result.text), StringBuilderUtils.optionalSubObj("node", accessibilityNode)))), new Object[0]);
            imageCaptioner2.characterCaptionRequests.performNextRequest();
            imageCaptioner2.handleResult(captionRequest.requestId, accessibilityNode, result, z);
            LogUtils.v("ImageCaptionStorage", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(result, "Character caption result (", ") should not be stored."), new Object[0]);
            return;
        }
        long durationMillis3 = captionRequest.getDurationMillis();
        ImageCaptioner imageCaptioner3 = this.f$0;
        if (durationMillis3 != -1) {
            imageCaptioner3.primeLogImageCaptionEvent(PrimesController.TimerAction.IMAGE_CAPTION_ICON_LABEL_SUCCEED, captionRequest.getDurationMillis());
        }
        imageCaptioner3.analytics.onImageCaptionEvent(6);
        LogUtils.v("ImageCaptioner", "onIconDetectionFinish() result=%s node=%s", result.text, accessibilityNode);
        imageCaptioner3.iconDetectionRequests.performNextRequest();
        imageCaptioner3.handleResult(captionRequest.requestId, accessibilityNode, result, z);
        LogUtils.v("ImageCaptionStorage", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(result, "DetectedIconLabel (", ") should not be stored."), new Object[0]);
    }
}
