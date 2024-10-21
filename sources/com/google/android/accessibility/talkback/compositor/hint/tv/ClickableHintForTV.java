package com.google.android.accessibility.talkback.compositor.hint.tv;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.hint.ClickableHint;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClickableHintForTV extends ClickableHint {
    public ClickableHintForTV(Context context, GlobalVariables globalVariables) {
        super(context, globalVariables);
    }

    public final CharSequence getClickHint(int i, int i2) {
        if (this.globalVariables.getGlobalInputMode() == 1 && this.globalVariables.getKeyComboCodeForKey(R.string.keycombo_shortcut_perform_click) != 0) {
            return this.context.getString(i, this.globalVariables.getKeyComboStringRepresentation(R.string.keycombo_shortcut_perform_click));
        }
        Context context = this.context;
        return context.getString(i2, context.getString(R.string.value_press_select));
    }

    @Override // com.google.android.accessibility.talkback.compositor.hint.ClickableHint
    public final CharSequence getEditTextClickableHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return getClickHint(R.string.template_hint_edit_text_keyboard, R.string.template_hint_edit_text);
    }

    @Override // com.google.android.accessibility.talkback.compositor.hint.ClickableHint
    public final CharSequence getHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String string;
        CharSequence clickHint;
        CharSequence clickHint2;
        CharSequence clickHint3;
        CharSequence actionLabelById = AccessibilityNodeInfoUtils.getActionLabelById(accessibilityNodeInfoCompat, 16);
        if (TextUtils.isEmpty(actionLabelById)) {
            string = "";
        } else if (this.globalVariables.getGlobalInputMode() == 1 && this.globalVariables.getKeyComboCodeForKey(R.string.keycombo_shortcut_perform_click) != 0) {
            string = this.context.getString(R.string.template_custom_hint_for_actions_keyboard, this.globalVariables.getKeyComboStringRepresentation(R.string.keycombo_shortcut_perform_click), actionLabelById);
        } else {
            Context context = this.context;
            string = context.getString(R.string.template_custom_hint_for_actions, context.getString(R.string.value_press_select), actionLabelById);
        }
        if (TextUtils.isEmpty(string)) {
            if (this.globalVariables.getCollectionSelectionMode() != 1) {
                clickHint = "";
            } else {
                clickHint = getClickHint(R.string.template_hint_single_selection_item_keyboard, R.string.template_hint_single_selection_item);
            }
            if (TextUtils.isEmpty(clickHint)) {
                if (!accessibilityNodeInfoCompat.isCheckable()) {
                    clickHint2 = "";
                } else {
                    clickHint2 = getClickHint(R.string.template_hint_checkable_keyboard, R.string.template_hint_checkable);
                }
                if (TextUtils.isEmpty(clickHint2)) {
                    if (!AccessibilityNodeInfoUtils.isClickable(accessibilityNodeInfoCompat)) {
                        clickHint3 = "";
                    } else {
                        clickHint3 = getClickHint(R.string.template_hint_clickable_keyboard, R.string.template_hint_clickable);
                    }
                    if (TextUtils.isEmpty(clickHint3)) {
                        return "";
                    }
                    LogUtils.v("ClickableHintForTV", " clickableHint={%s}", clickHint3);
                    return clickHint3;
                }
                LogUtils.v("ClickableHintForTV", " checkableHint={%s}", clickHint2);
                return clickHint2;
            }
            LogUtils.v("ClickableHintForTV", " singleSelectionClickHint={%s}", clickHint);
            return clickHint;
        }
        LogUtils.v("ClickableHintForTV", " actionClickHint={%s}", string);
        return string;
    }
}
