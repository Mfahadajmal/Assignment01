package com.google.android.accessibility.talkback.focusmanagement.action;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TouchExplorationAction {
    public final AccessibilityNodeInfoCompat touchedFocusableNode;
    public final int type;

    public TouchExplorationAction(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.type = i;
        this.touchedFocusableNode = accessibilityNodeInfoCompat;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("TouchExplorationAction{actionType=");
        int i = this.type;
        if (i != 0) {
            if (i != 1) {
                str = "TOUCH_INTERACTION_END";
            } else {
                str = "HOVER_ENTER";
            }
        } else {
            str = "TOUCH_INTERACTION_START";
        }
        sb.append(str);
        sb.append(", touchedFocusableNode=");
        sb.append(this.touchedFocusableNode);
        sb.append('}');
        return sb.toString();
    }
}
