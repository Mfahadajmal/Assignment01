package com.google.android.accessibility.utils.labeling;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface LabelManager {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface State {
        long getLabelIdForNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

        boolean needsLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

        boolean supportsLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);
    }

    Label getLabelForViewIdFromCache(String str);

    State stateReader();
}
