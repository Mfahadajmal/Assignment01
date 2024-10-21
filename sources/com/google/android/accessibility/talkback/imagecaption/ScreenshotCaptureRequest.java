package com.google.android.accessibility.talkback.imagecaption;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Bitmap;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.screencapture.ScreenshotCapture$WindowOrFullScreenScreenshotCallback;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import j$.time.Duration;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenshotCaptureRequest extends Request {
    public static final Duration SCREENSHOT_CAPTURE_TIMEOUT_MS = Duration.ofMillis(3000);
    private final boolean isUserRequested;
    private final AccessibilityNodeInfoCompat node;
    private final OnFinishListener onFinishListener;
    private final AccessibilityService service;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnFinishListener {
        void onFinish(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, boolean z, boolean z2);
    }

    public ScreenshotCaptureRequest(AccessibilityService accessibilityService, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, OnFinishListener onFinishListener, boolean z) {
        super(hapticPatternParser$$ExternalSyntheticLambda1, SCREENSHOT_CAPTURE_TIMEOUT_MS);
        this.service = accessibilityService;
        this.node = accessibilityNodeInfoCompat;
        this.onFinishListener = onFinishListener;
        this.isUserRequested = z;
    }

    @Override // com.google.android.accessibility.talkback.imagecaption.Request
    public final void onError(int i) {
        onFinished(null, false);
    }

    public final void onFinished(Bitmap bitmap, boolean z) {
        stopTimeoutRunnable();
        setEndTimestamp();
        LogUtils.v("ScreenshotRequestForCaption", "onFinish() ".concat(String.valueOf(StringBuilderUtils.joinFields(StringBuilderUtils.optionalText("name", getClass().getSimpleName()), StringBuilderUtils.optionalInt$ar$ds("time", getDurationMillis()), StringBuilderUtils.optionalSubObj("screenCapture", bitmap), StringBuilderUtils.optionalTag("capturedByWindow", z), StringBuilderUtils.optionalSubObj("node", this.node)))), new Object[0]);
        this.onFinishListener.onFinish(this.node, bitmap, this.isUserRequested, z);
    }

    @Override // com.google.android.accessibility.talkback.imagecaption.Request
    public final void perform() {
        int windowId;
        Executor mainExecutor;
        setStartTimestamp();
        ScreenshotCapture$WindowOrFullScreenScreenshotCallback screenshotCapture$WindowOrFullScreenScreenshotCallback = new ScreenshotCapture$WindowOrFullScreenScreenshotCallback() { // from class: com.google.android.accessibility.talkback.imagecaption.ScreenshotCaptureRequest.1
            @Override // com.google.android.accessibility.utils.screencapture.ScreenshotCapture$WindowOrFullScreenScreenshotCallback
            public final void onFailure(int i) {
                super.onFailure(i);
                ScreenshotCaptureRequest.this.onFinished(null, false);
            }

            @Override // com.google.android.accessibility.utils.screencapture.ScreenshotCapture$WindowOrFullScreenScreenshotCallback
            public final void onSuccess(AccessibilityService.ScreenshotResult screenshotResult) {
                super.onSuccess(screenshotResult);
                ScreenshotCaptureRequest.this.onFinished(this.bitmap, this.captureByWindow);
            }
        };
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.node;
        if (accessibilityNodeInfoCompat == null) {
            windowId = -1;
        } else {
            windowId = accessibilityNodeInfoCompat.getWindowId();
        }
        AccessibilityService accessibilityService = this.service;
        mainExecutor = accessibilityService.getMainExecutor();
        if (!SpannableUtils$IdentifierSpan.isAtLeastR()) {
            LogUtils.e("ScreenshotCapture", "Taking screenshot but platform's not support", new Object[0]);
            screenshotCapture$WindowOrFullScreenScreenshotCallback.onFailure(2);
        } else if (windowId == -1 || !SpannableUtils$IdentifierSpan.isAtLeastU()) {
            accessibilityService.takeScreenshot(0, mainExecutor, screenshotCapture$WindowOrFullScreenScreenshotCallback);
        } else {
            screenshotCapture$WindowOrFullScreenScreenshotCallback.captureByWindow = true;
            accessibilityService.takeScreenshotOfWindow(windowId, mainExecutor, screenshotCapture$WindowOrFullScreenScreenshotCallback);
        }
        runTimeoutRunnable();
    }

    public final String toString() {
        return getClass().getSimpleName() + "= " + StringBuilderUtils.joinFields(StringBuilderUtils.optionalSubObj("node", this.node));
    }
}
