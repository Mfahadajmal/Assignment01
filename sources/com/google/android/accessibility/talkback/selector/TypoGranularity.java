package com.google.android.accessibility.talkback.selector;

import android.content.Context;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.selector.SelectorController;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypoGranularity implements SelectorController.ContextualSetting {
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;

    public TypoGranularity(AccessibilityFocusMonitor accessibilityFocusMonitor) {
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
    }

    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final SelectorController.Setting getSetting() {
        return SelectorController.Setting.GRANULARITY_TYPO;
    }

    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final boolean isNodeSupportSetting(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            accessibilityNodeInfoCompat = this.accessibilityFocusMonitor.getAccessibilityFocus(true);
        }
        if (accessibilityNodeInfoCompat != null && this.accessibilityFocusMonitor.getNodeForEditingActions(accessibilityNodeInfoCompat) != null) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final boolean shouldActivateSetting(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return false;
    }
}
