package com.google.android.accessibility.talkback.compositor.roledescription;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public interface RoleDescription {
    CharSequence nodeName(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables);

    CharSequence nodeRole(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables);

    CharSequence nodeState(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables);

    boolean shouldIgnoreDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);
}
