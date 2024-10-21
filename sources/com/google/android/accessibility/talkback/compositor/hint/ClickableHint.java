package com.google.android.accessibility.talkback.compositor.hint;

import android.content.Context;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ClickableHint {
    protected final Context context;
    protected final GlobalVariables globalVariables;

    public ClickableHint(Context context, GlobalVariables globalVariables) {
        this.context = context;
        this.globalVariables = globalVariables;
    }

    public final CharSequence getClickHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, int i2) {
        String string;
        int globalInputMode = this.globalVariables.getGlobalInputMode();
        if (globalInputMode == 1) {
            if (this.globalVariables.getKeyComboCodeForKey(R.string.keycombo_shortcut_perform_click) != 0) {
                return this.context.getString(i, this.globalVariables.getKeyComboStringRepresentation(R.string.keycombo_shortcut_perform_click));
            }
        } else if (globalInputMode == 3) {
            Context context = this.context;
            return context.getString(i2, context.getString(R.string.value_press_select));
        }
        if (AccessibilityNodeInfoUtils.isClickable(accessibilityNodeInfoCompat) && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 34) {
            if (!this.globalVariables.isInterpretAsEntryKey || !AccessibilityNodeInfoUtils.isKeyboard(accessibilityNodeInfoCompat)) {
                if (this.globalVariables.mUseSingleTap) {
                    string = this.context.getString(R.string.value_single_tap);
                } else {
                    string = this.context.getString(R.string.value_double_tap);
                }
                return this.context.getString(i2, string);
            }
            return "";
        }
        return "";
    }

    public CharSequence getEditTextClickableHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return getClickHint(accessibilityNodeInfoCompat, R.string.template_hint_edit_text_keyboard, R.string.template_hint_edit_text);
    }

    public CharSequence getHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        throw null;
    }
}
