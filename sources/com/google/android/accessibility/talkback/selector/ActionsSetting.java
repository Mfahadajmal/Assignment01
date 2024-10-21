package com.google.android.accessibility.talkback.selector;

import android.content.Context;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActionsSetting implements SelectorController.ContextualSetting {
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    private final NodeMenuRuleCreator nodeMenuCreator;

    public ActionsSetting(NodeMenuRuleCreator nodeMenuRuleCreator, AccessibilityFocusMonitor accessibilityFocusMonitor) {
        this.nodeMenuCreator = nodeMenuRuleCreator;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
    }

    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final SelectorController.Setting getSetting() {
        return SelectorController.Setting.ACTIONS;
    }

    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final boolean isNodeSupportSetting(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null || !SpannableUtils$IdentifierSpan.getSharedPreferences(context).getBoolean(context.getString(SelectorController.Setting.ACTIONS.prefKeyResId), context.getResources().getBoolean(SelectorController.Setting.ACTIONS.defaultValueResId)) || this.nodeMenuCreator.getNodeMenuByRule$ar$ds(NodeMenuRuleCreator.MenuRules.RULE_CUSTOM_ACTION, context, accessibilityNodeInfoCompat).isEmpty()) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.selector.SelectorController.ContextualSetting
    public final boolean shouldActivateSetting(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4 && this.accessibilityFocusMonitor.getEditingNodeFromFocusedKeyboard(accessibilityNodeInfoCompat) == null) {
            return isNodeSupportSetting(context, accessibilityNodeInfoCompat);
        }
        return false;
    }
}
