package com.google.android.accessibility.talkback;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.controller.TelevisionNavigationController;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfig;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TelevisionDPadManager implements AccessibilityEventListener {
    private final Set dpadAllowlist;
    private String previousActivePackageName = "";
    private final AccessibilityService service;
    private final TelevisionNavigationController tvNavigationController;

    public TelevisionDPadManager(TelevisionNavigationController televisionNavigationController, AccessibilityService accessibilityService) {
        if (televisionNavigationController != null) {
            this.tvNavigationController = televisionNavigationController;
            this.service = accessibilityService;
            HashSet hashSet = new HashSet();
            hashSet.addAll(TvNavigationConfig.INSTANCE.get().packagesDpadAllowlist(accessibilityService).element_);
            hashSet.removeAll(TvNavigationConfig.INSTANCE.get().packagesDpadBlocklist(accessibilityService).element_);
            this.dpadAllowlist = hashSet;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 4194304;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        if (accessibilityEvent.getEventType() == 4194304) {
            AccessibilityNodeInfoCompat rootInActiveWindow = SpannableUtils$IdentifierSpan.getRootInActiveWindow(this.service);
            String str = null;
            if (rootInActiveWindow != null && rootInActiveWindow.getPackageName() != null) {
                str = rootInActiveWindow.getPackageName().toString();
            }
            TelevisionNavigationController televisionNavigationController = this.tvNavigationController;
            if (televisionNavigationController.listMenuManager.isMenuShowing() && (accessibilityNodeInfoCompat = televisionNavigationController.listMenuTriggerNode) != null && !accessibilityNodeInfoCompat.refresh()) {
                televisionNavigationController.listMenuManager.dismissAll();
            }
            if (!TextUtils.equals(this.previousActivePackageName, str)) {
                this.previousActivePackageName = str;
                if (!TextUtils.isEmpty(str) && this.dpadAllowlist.contains(str)) {
                    this.tvNavigationController.shouldProcessDPadKeyEvent = false;
                    this.service.sendBroadcast(new Intent("com.google.android.accessibility.talkback.DPAD_PASSTHROUGH"));
                } else {
                    this.service.sendBroadcast(new Intent("com.google.android.accessibility.talkback.DPAD_NO_PASSTHROUGH"));
                    this.tvNavigationController.shouldProcessDPadKeyEvent = true;
                }
            }
        }
    }
}
