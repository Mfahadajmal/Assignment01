package com.google.android.accessibility.talkback.selector;

import android.content.Context;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AdjustableWidgetSetting implements SelectorController.ContextualSetting {
    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final SelectorController.Setting getSetting() {
        return SelectorController.Setting.ADJUSTABLE_WIDGET;
    }

    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final boolean isNodeSupportSetting(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        AccessibilityNodeInfoCompat matchingAncestor = AccessibilityNodeInfoUtils.getMatchingAncestor(accessibilityNodeInfoCompat, AccessibilityFocusMonitor.NUMBER_PICKER_FILTER_FOR_ADJUST);
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 10 && matchingAncestor == null) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final /* synthetic */ boolean shouldActivateSetting(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return isNodeSupportSetting(context, accessibilityNodeInfoCompat);
    }
}
