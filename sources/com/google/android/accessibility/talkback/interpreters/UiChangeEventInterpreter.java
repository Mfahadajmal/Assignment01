package com.google.android.accessibility.talkback.interpreters;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.talkback.Interpretation$UiChange;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UiChangeEventInterpreter implements WindowEventInterpreter.WindowEventHandler, AccessibilityEventListener {
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 1;
    }

    @Override // com.google.android.accessibility.utils.input.WindowEventInterpreter.WindowEventHandler
    public final void handle(WindowEventInterpreter.EventInterpretation eventInterpretation, Performance.EventId eventId) {
        Rect rect;
        Interpretation$UiChange.UiChangeType uiChangeType;
        int i = eventInterpretation.eventType;
        if (eventInterpretation.mainWindowsChanged || i == 4194304 || i == 32) {
            this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, Interpretation$UiChange.WHOLE_SCREEN_UI_CHANGE, null);
            return;
        }
        if ((i & 6144) != 0) {
            Rect rect2 = eventInterpretation.sourceBoundsInScreen;
            if (rect2 == null) {
                rect = null;
            } else {
                rect = new Rect(rect2);
            }
            if (rect != null) {
                HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                if (i == 4096) {
                    uiChangeType = Interpretation$UiChange.UiChangeType.VIEW_SCROLLED;
                } else {
                    uiChangeType = Interpretation$UiChange.UiChangeType.WINDOW_CONTENT_CHANGED;
                }
                hapticPatternParser$$ExternalSyntheticLambda1.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$UiChange(rect, uiChangeType), null);
            }
        }
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        if (source != null) {
            Rect rect = new Rect();
            source.getBoundsInScreen(rect);
            this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$UiChange(rect, Interpretation$UiChange.UiChangeType.VIEW_CLICKED), null);
        }
    }
}
