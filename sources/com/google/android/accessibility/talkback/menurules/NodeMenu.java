package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface NodeMenu {
    boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    List getMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z);
}
