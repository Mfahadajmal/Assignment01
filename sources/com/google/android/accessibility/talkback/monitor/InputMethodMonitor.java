package com.google.android.accessibility.talkback.monitor;

import android.accessibilityservice.AccessibilityService;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InputMethodMonitor {
    private volatile String activeInputMethod;
    public final ContentObserver contentObserver;
    private final FormFactorUtils formFactorUtils;
    private final Handler handler;
    public final AccessibilityService service;

    public InputMethodMonitor(AccessibilityService accessibilityService) {
        Handler handler = new Handler(Looper.myLooper());
        this.handler = handler;
        this.contentObserver = new ContentObserver(handler) { // from class: com.google.android.accessibility.talkback.monitor.InputMethodMonitor.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                InputMethodMonitor.this.updateActiveInputMethod();
            }
        };
        this.service = accessibilityService;
        this.formFactorUtils = FormFactorUtils.getInstance();
    }

    public final AccessibilityNodeInfo getRootInActiveInputWindow() {
        AccessibilityWindowInfo onscreenInputWindowInfo = SpannableUtils$IdentifierSpan.getOnscreenInputWindowInfo(this.service);
        if (onscreenInputWindowInfo == null) {
            return null;
        }
        return onscreenInputWindowInfo.getRoot();
    }

    public final void updateActiveInputMethod() {
        this.activeInputMethod = Settings.Secure.getString(this.service.getContentResolver(), "default_input_method");
    }

    public final boolean useInputWindowAsActiveWindow() {
        if (this.formFactorUtils.isAndroidTv && this.activeInputMethod != null) {
            String str = "com.google.android.inputmethod.latin";
            if (this.activeInputMethod.startsWith("com.google.android.inputmethod.latin")) {
                if (true == this.activeInputMethod.startsWith("com.google.android.inputmethod.latin.dev")) {
                    str = "com.google.android.inputmethod.latin.dev";
                }
                try {
                    if (this.service.getPackageManager().getPackageInfo(str, 0).versionCode < 107460889) {
                        return false;
                    }
                    return true;
                } catch (PackageManager.NameNotFoundException e) {
                    throw new AssertionError("Package not found despite being the default input method.", e);
                }
            }
        }
        return false;
    }
}
