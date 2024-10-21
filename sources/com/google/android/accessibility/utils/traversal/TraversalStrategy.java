package com.google.android.accessibility.utils.traversal;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TraversalStrategy {
    AccessibilityNodeInfoCompat findFocus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i);

    AccessibilityNodeInfoCompat focusFirst(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i);

    AccessibilityNodeInfoCompat focusInitial(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    Map getSpeakingNodesCache();
}
