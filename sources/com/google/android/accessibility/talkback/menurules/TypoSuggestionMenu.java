package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypoSuggestionMenu implements NodeMenu {
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public final Pipeline.FeedbackReturner pipeline;

    public TypoSuggestionMenu(Pipeline.FeedbackReturner feedbackReturner, AccessibilityFocusMonitor accessibilityFocusMonitor) {
        this.pipeline = feedbackReturner;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!SpannableUtils$IdentifierSpan.getSharedPreferences(context).getBoolean(context.getString(R.string.pref_show_context_menu_typo_suggestions_setting_key), context.getResources().getBoolean(R.bool.pref_show_context_menu_typo_suggestions_default))) {
            return false;
        }
        AccessibilityNodeInfoCompat editingNode = getEditingNode(accessibilityNodeInfoCompat);
        if (SpannableUtils$IdentifierSpan.getRole(editingNode) != 4 || AccessibilityNodeInfoUtils.getSpellingSuggestions(context, editingNode).isEmpty()) {
            return false;
        }
        return true;
    }

    public final AccessibilityNodeInfoCompat getEditingNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat editingNodeFromFocusedKeyboard;
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4 && (editingNodeFromFocusedKeyboard = this.accessibilityFocusMonitor.getEditingNodeFromFocusedKeyboard(accessibilityNodeInfoCompat)) != null) {
            return editingNodeFromFocusedKeyboard;
        }
        return accessibilityNodeInfoCompat;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final List getMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        throw null;
    }
}
