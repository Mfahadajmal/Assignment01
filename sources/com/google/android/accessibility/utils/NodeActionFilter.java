package com.google.android.accessibility.utils;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodeActionFilter extends Filter {
    private final int action;

    public NodeActionFilter(int i) {
        this.action = i;
    }

    @Override // com.google.android.accessibility.utils.Filter
    public final boolean accept(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, this.action);
    }
}
