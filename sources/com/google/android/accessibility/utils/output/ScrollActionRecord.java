package com.google.android.accessibility.utils.output;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.StringBuilderUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScrollActionRecord {
    public final long autoScrolledTime;
    public final int scrollInstanceId;
    public final String scrollSource;
    public final AccessibilityNode scrolledNode;
    public final AccessibilityNodeInfoCompat scrolledNodeCompat;
    public final int userAction;

    public ScrollActionRecord(int i, AccessibilityNode accessibilityNode, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i2, long j, String str) {
        this.scrollInstanceId = i;
        this.userAction = i2;
        this.scrolledNode = accessibilityNode;
        this.scrolledNodeCompat = accessibilityNodeInfoCompat;
        this.autoScrolledTime = j;
        this.scrollSource = str;
    }

    public static String userActionToString(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return "ACTION_UNKNOWN";
                }
                return "ACTION_MANUAL_SCROLL";
            }
            return "ACTION_SCROLL_SHORTCUT";
        }
        return "ACTION_AUTO_SCROLL";
    }

    public final void refresh() {
        AccessibilityNode accessibilityNode = this.scrolledNode;
        if (accessibilityNode != null) {
            accessibilityNode.refresh();
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.scrolledNodeCompat;
        if (accessibilityNodeInfoCompat != null) {
            accessibilityNodeInfoCompat.refresh();
        }
    }

    public final String toString() {
        return StringBuilderUtils.joinFields(StringBuilderUtils.optionalSubObj("scrolledNode", this.scrolledNode), StringBuilderUtils.optionalSubObj("scrolledNodeCompat", this.scrolledNodeCompat), StringBuilderUtils.optionalInt("scrollInstanceId", this.scrollInstanceId, 0), StringBuilderUtils.optionalInt("userAction", this.userAction, 0), StringBuilderUtils.optionalInt$ar$ds("autoScrolledTime", this.autoScrolledTime), StringBuilderUtils.optionalText("scrollSource", this.scrollSource));
    }
}
