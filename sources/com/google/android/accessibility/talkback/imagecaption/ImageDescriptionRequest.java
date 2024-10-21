package com.google.android.accessibility.talkback.imagecaption;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.imagecaption.CaptionRequest;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor$$ExternalSyntheticLambda0;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.search.mdi.aratea.proto.FeatureName;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionRequest extends CaptionRequest {
    public final Context context;
    private final ImageDescriptionProcessor imageDescriptionProcessor;
    private final Bitmap screenCapture;

    public ImageDescriptionRequest(int i, Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, ImageDescriptionProcessor imageDescriptionProcessor, CaptionRequest.OnFinishListener onFinishListener, CaptionRequest.OnErrorListener onErrorListener, boolean z) {
        super(i, accessibilityNodeInfoCompat, onFinishListener, onErrorListener, z);
        this.context = context;
        this.imageDescriptionProcessor = imageDescriptionProcessor;
        this.screenCapture = bitmap;
    }

    public final void onFailure(int i) {
        stopTimeoutRunnable();
        onError(i);
    }

    @Override // com.google.android.accessibility.talkback.imagecaption.Request
    public final void perform() {
        onCaptionStart();
        ImageDescriptionProcessor imageDescriptionProcessor = this.imageDescriptionProcessor;
        if (imageDescriptionProcessor.pipeline == null) {
            onFailure(FeatureName.STYLUS_SKETCH_TO_IMAGE$ar$edu);
        } else {
            ContextDataProvider.addCallback(ContextDataProvider.submit(new ImageDescriptionProcessor$$ExternalSyntheticLambda0(imageDescriptionProcessor, this.screenCapture, 0), imageDescriptionProcessor.scheduler), new ImageDescriptionProcessor.AnonymousClass1(imageDescriptionProcessor, this, 0), imageDescriptionProcessor.scheduler);
        }
        runTimeoutRunnable();
    }
}
