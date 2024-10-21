package com.google.android.accessibility.utils.screencapture;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityService$TakeScreenshotCallback;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.hardware.HardwareBuffer;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ScreenshotCapture$WindowOrFullScreenScreenshotCallback implements AccessibilityService$TakeScreenshotCallback {
    public Bitmap bitmap;
    public boolean captureByWindow = false;

    public void onFailure(int i) {
        LogUtils.e("ScreenshotCapture", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Taking screenshot but failed [error:", "]"), new Object[0]);
    }

    public void onSuccess(AccessibilityService.ScreenshotResult screenshotResult) {
        HardwareBuffer hardwareBuffer;
        ColorSpace colorSpace;
        Bitmap wrapHardwareBuffer;
        try {
            hardwareBuffer = screenshotResult.getHardwareBuffer();
            try {
                colorSpace = screenshotResult.getColorSpace();
                wrapHardwareBuffer = Bitmap.wrapHardwareBuffer(hardwareBuffer, colorSpace);
                this.bitmap = wrapHardwareBuffer;
                if (hardwareBuffer != null) {
                    hardwareBuffer.close();
                }
                Bitmap bitmap = this.bitmap;
                if (bitmap != null) {
                    Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, this.bitmap.isMutable());
                    this.bitmap.recycle();
                    this.bitmap = copy;
                }
            } finally {
            }
        } catch (IllegalArgumentException e) {
            LogUtils.e("ScreenshotCapture", "Taken screenshot could not be converted to Bitmap, %s", e);
        }
    }
}
