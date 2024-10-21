package com.google.android.accessibility.braille.brailledisplay.controller;

import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
interface EventConsumer {
    void onAccessibilityEvent(AccessibilityEvent accessibilityEvent);

    void onActivate();

    void onDeactivate();

    boolean onMappedInputEvent(BrailleInputEvent brailleInputEvent);
}
