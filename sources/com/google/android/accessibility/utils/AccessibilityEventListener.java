package com.google.android.accessibility.utils;

import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.utils.Performance;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AccessibilityEventListener {
    int getEventTypes();

    void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId);
}
