package com.google.android.accessibility.talkback;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$DirectionNavigation extends SpannableUtils$NonCopyableTextSpan {
    private final AccessibilityNodeInfoCompat destination;
    private final int direction;

    public Interpretation$DirectionNavigation() {
        super((byte[]) null);
    }

    public final AccessibilityNodeInfoCompat destination() {
        return this.destination;
    }

    public final int direction() {
        return this.direction;
    }

    public final boolean equals(Object obj) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Interpretation$DirectionNavigation) {
            Interpretation$DirectionNavigation interpretation$DirectionNavigation = (Interpretation$DirectionNavigation) obj;
            if (this.direction == interpretation$DirectionNavigation.direction() && ((accessibilityNodeInfoCompat = this.destination) != null ? accessibilityNodeInfoCompat.equals(interpretation$DirectionNavigation.destination()) : interpretation$DirectionNavigation.destination() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.destination;
        if (accessibilityNodeInfoCompat == null) {
            hashCode = 0;
        } else {
            hashCode = accessibilityNodeInfoCompat.hashCode();
        }
        return hashCode ^ ((this.direction ^ 1000003) * 1000003);
    }

    public final String toString() {
        String concat;
        String optionalInt = StringBuilderUtils.optionalInt("direction", direction(), 0);
        if (destination() == null) {
            concat = null;
        } else {
            concat = "destination=".concat(String.valueOf(AccessibilityNodeInfoUtils.toStringShort(destination())));
        }
        return StringBuilderUtils.joinFields("DirectionNavigation{", optionalInt, concat, "}");
    }

    public Interpretation$DirectionNavigation(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this();
        this.direction = i;
        this.destination = accessibilityNodeInfoCompat;
    }
}
