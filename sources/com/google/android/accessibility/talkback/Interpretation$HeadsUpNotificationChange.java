package com.google.android.accessibility.talkback;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$HeadsUpNotificationChange extends SpannableUtils$NonCopyableTextSpan {
    public AccessibilityNodeInfoCompat headsUpNotification;

    public Interpretation$HeadsUpNotificationChange(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super((byte[]) null);
        if (accessibilityNodeInfoCompat != null) {
            this.headsUpNotification = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
        }
    }

    public final boolean equals(Object obj) {
        Interpretation$HeadsUpNotificationChange interpretation$HeadsUpNotificationChange = (Interpretation$HeadsUpNotificationChange) SpannableUtils$NonCopyableTextSpan.castOrNull(obj, Interpretation$HeadsUpNotificationChange.class);
        if (interpretation$HeadsUpNotificationChange == null) {
            return false;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.headsUpNotification;
        if (accessibilityNodeInfoCompat == null && interpretation$HeadsUpNotificationChange.headsUpNotification == null) {
            return true;
        }
        if (accessibilityNodeInfoCompat == null || !accessibilityNodeInfoCompat.equals(interpretation$HeadsUpNotificationChange.headsUpNotification)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(this.headsUpNotification);
    }

    public final String toString() {
        return StringBuilderUtils.joinFields("HeadsUpNotificationChange{", StringBuilderUtils.optionalSubObj("node", this.headsUpNotification), "}");
    }
}
