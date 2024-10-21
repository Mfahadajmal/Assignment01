package org.chromium.base;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.AccessibilityService;
import android.app.ApplicationExitInfo;
import android.view.WindowInsetsAnimation;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class PathUtils$$ExternalSyntheticApiModelOutline2 {
    public static /* bridge */ /* synthetic */ AccessibilityGestureEvent m(Object obj) {
        return (AccessibilityGestureEvent) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ApplicationExitInfo m295m(Object obj) {
        return (ApplicationExitInfo) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ WindowInsetsAnimation m300m(Object obj) {
        return (WindowInsetsAnimation) obj;
    }

    public static /* bridge */ /* synthetic */ boolean m(AccessibilityService.SoftKeyboardController softKeyboardController, String str) {
        return softKeyboardController.switchToInputMethod(str);
    }
}
