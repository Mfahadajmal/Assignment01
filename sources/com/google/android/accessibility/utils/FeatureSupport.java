package com.google.android.accessibility.utils;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Vibrator;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeatureSupport {
    private static Boolean brailleDisplaySettingsActivityPresent;
    private static Boolean brailleKeyboardSettingsActivityPresent;
    private static Boolean isWatch;

    public static boolean hasAccessibilityAudioStream(Context context) {
        if (!isTv(context)) {
            return true;
        }
        return false;
    }

    public static boolean isFingerprintGestureSupported(Context context) {
        boolean z;
        int identifier;
        if (context != null && !isWatch(context)) {
            z = context.getPackageManager().hasSystemFeature("android.hardware.fingerprint");
        } else {
            z = false;
        }
        if (context == null || !z || (identifier = context.getResources().getIdentifier("config_fingerprintSupportsGestures", "bool", "android")) == 0 || !context.getResources().getBoolean(identifier)) {
            return false;
        }
        return true;
    }

    public static boolean isGestureNavigateEnabled(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_navBarInteractionMode", "integer", "android");
        if (identifier <= 0 || resources.getInteger(identifier) != 2) {
            return false;
        }
        return true;
    }

    public static boolean isMultiFingerGestureSupported() {
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && AccessibilityServiceInfo.flagToString(8192) != null) {
            return true;
        }
        return false;
    }

    public static boolean isTv(Context context) {
        UiModeManager uiModeManager;
        if (context == null || (uiModeManager = (UiModeManager) context.getSystemService("uimode")) == null || uiModeManager.getCurrentModeType() != 4) {
            return false;
        }
        return true;
    }

    public static boolean isVibratorSupported(Context context) {
        Vibrator vibrator;
        if (context == null) {
            vibrator = null;
        } else {
            vibrator = (Vibrator) context.getSystemService("vibrator");
        }
        if (vibrator != null && vibrator.hasVibrator()) {
            return true;
        }
        return false;
    }

    public static boolean isWatch(Context context) {
        if (isWatch == null) {
            isWatch = Boolean.valueOf(context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return isWatch.booleanValue();
    }

    public static boolean logcatIncludePsi() {
        if (LogUtils.minLogLevel < 6) {
            return true;
        }
        return false;
    }

    public static boolean screenshotRequiresForeground() {
        if (Build.VERSION.SDK_INT == 29) {
            return true;
        }
        return false;
    }

    public static boolean supportBrailleDisplay(Context context) {
        if (brailleDisplaySettingsActivityPresent == null) {
            boolean z = false;
            if (new Intent().setComponent(AccessibilityServiceCompatUtils$Constants.BRAILLE_DISPLAY_SETTINGS).resolveActivityInfo(context.getPackageManager(), 0) != null) {
                z = true;
            }
            brailleDisplaySettingsActivityPresent = Boolean.valueOf(z);
        }
        return brailleDisplaySettingsActivityPresent.booleanValue();
    }

    public static boolean supportBrailleKeyboard(Context context) {
        if (brailleKeyboardSettingsActivityPresent == null) {
            boolean z = false;
            if (new Intent().setComponent(AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD_SETTINGS).resolveActivityInfo(context.getPackageManager(), 0) != null) {
                z = true;
            }
            brailleKeyboardSettingsActivityPresent = Boolean.valueOf(z);
        }
        return brailleKeyboardSettingsActivityPresent.booleanValue();
    }

    public static boolean supportGestureMotionEvents() {
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && AccessibilityServiceInfo.flagToString(16384) != null) {
            return true;
        }
        return false;
    }

    public static boolean supportGetSystemActions(Context context) {
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && !isWatch(context)) {
            return true;
        }
        return false;
    }
}
