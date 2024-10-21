package com.google.android.accessibility.utils;

import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityWindow {
    private static final SpannableUtils$IdentifierSpan FACTORY$ar$class_merging$516956c2_0$ar$class_merging$ar$class_merging = new SpannableUtils$IdentifierSpan();
    public AccessibilityWindowInfoCompat windowCompat;

    public final AccessibilityNode getRoot() {
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = this.windowCompat;
        if (accessibilityWindowInfoCompat != null) {
            return AccessibilityNode.takeOwnership(accessibilityWindowInfoCompat.getRoot());
        }
        return null;
    }
}
