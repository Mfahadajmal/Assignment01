package com.google.android.accessibility.talkback;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$InputFocus extends SpannableUtils$NonCopyableTextSpan {
    public final AccessibilityNodeInfoCompat node;

    public Interpretation$InputFocus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super((byte[]) null);
        this.node = accessibilityNodeInfoCompat;
    }

    public final boolean equals(Object obj) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2;
        if (obj == null || !(obj instanceof Interpretation$InputFocus) || ((accessibilityNodeInfoCompat = this.node) != (accessibilityNodeInfoCompat2 = ((Interpretation$InputFocus) obj).node) && !accessibilityNodeInfoCompat.equals(accessibilityNodeInfoCompat2))) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(this.node);
    }

    public final String toString() {
        return StringBuilderUtils.joinFields("InputFocus{", StringBuilderUtils.optionalSubObj("node", this.node), "}");
    }
}
