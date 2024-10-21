package com.google.android.accessibility.talkback.compositor.hint.tv;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.hint.LongClickableHint;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LongClickableHintForTV extends LongClickableHint {
    public LongClickableHintForTV(Context context, GlobalVariables globalVariables) {
        super(context, globalVariables);
    }

    @Override // com.google.android.accessibility.talkback.compositor.hint.LongClickableHint
    public final CharSequence getHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String string;
        String string2;
        CharSequence actionLabelById = AccessibilityNodeInfoUtils.getActionLabelById(accessibilityNodeInfoCompat, 32);
        if (TextUtils.isEmpty(actionLabelById)) {
            string = "";
        } else if (this.globalVariables.getGlobalInputMode() == 1 && this.globalVariables.getKeyComboCodeForKey(R.string.keycombo_shortcut_perform_long_click) != 0) {
            string = this.context.getString(R.string.template_custom_hint_for_actions_keyboard, this.globalVariables.getKeyComboStringRepresentation(R.string.keycombo_shortcut_perform_long_click), actionLabelById);
        } else {
            Context context = this.context;
            string = context.getString(R.string.template_custom_hint_for_long_clickable_actions, context.getString(R.string.value_press_select), actionLabelById);
        }
        if (TextUtils.isEmpty(string)) {
            if (!AccessibilityNodeInfoUtils.isLongClickable(accessibilityNodeInfoCompat)) {
                string2 = "";
            } else if (this.globalVariables.getGlobalInputMode() == 1 && this.globalVariables.getKeyComboCodeForKey(R.string.keycombo_shortcut_perform_long_click) != 0) {
                string2 = this.context.getString(R.string.template_hint_long_clickable_keyboard, this.globalVariables.getKeyComboStringRepresentation(R.string.keycombo_shortcut_perform_long_click));
            } else {
                Context context2 = this.context;
                string2 = context2.getString(R.string.template_hint_long_clickable, context2.getString(R.string.value_press_select));
            }
            if (TextUtils.isEmpty(string2)) {
                return "";
            }
            LogUtils.v("LongClickableHintForTV", " longClickableHint={%s}", string2);
            return string2;
        }
        LogUtils.v("LongClickableHintForTV", " actionLongClickHint={%s}", string);
        return string;
    }
}
