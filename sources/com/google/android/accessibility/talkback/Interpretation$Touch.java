package com.google.android.accessibility.talkback;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$Touch extends SpannableUtils$NonCopyableTextSpan {
    private final Action action;
    private final AccessibilityNodeInfoCompat target;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Action {
        TAP,
        LIFT,
        LONG_PRESS,
        TOUCH_START,
        TOUCH_NOTHING,
        TOUCH_FOCUSED_NODE,
        TOUCH_UNFOCUSED_NODE,
        TOUCH_ENTERED_UNFOCUSED_NODE
    }

    public Interpretation$Touch() {
        super((byte[]) null);
    }

    public final Action action() {
        return this.action;
    }

    public final boolean equals(Object obj) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Interpretation$Touch) {
            Interpretation$Touch interpretation$Touch = (Interpretation$Touch) obj;
            if (this.action.equals(interpretation$Touch.action()) && ((accessibilityNodeInfoCompat = this.target) != null ? accessibilityNodeInfoCompat.equals(interpretation$Touch.target()) : interpretation$Touch.target() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2 = this.action.hashCode() ^ 1000003;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.target;
        if (accessibilityNodeInfoCompat == null) {
            hashCode = 0;
        } else {
            hashCode = accessibilityNodeInfoCompat.hashCode();
        }
        return (hashCode2 * 1000003) ^ hashCode;
    }

    public final AccessibilityNodeInfoCompat target() {
        return this.target;
    }

    public final String toString() {
        String concat;
        String optionalField = StringBuilderUtils.optionalField("action", action());
        if (target() == null) {
            concat = null;
        } else {
            concat = "target=".concat(String.valueOf(AccessibilityNodeInfoUtils.toStringShort(target())));
        }
        return StringBuilderUtils.joinFields("Touch{", optionalField, concat, "}");
    }

    public Interpretation$Touch(Action action, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this();
        if (action == null) {
            throw new NullPointerException("Null action");
        }
        this.action = action;
        this.target = accessibilityNodeInfoCompat;
    }
}
