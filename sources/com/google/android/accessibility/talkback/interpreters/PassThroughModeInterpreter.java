package com.google.android.accessibility.talkback.interpreters;

import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$ID;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PassThroughModeInterpreter implements AccessibilityEventListener {
    public ActorState actorState;
    private boolean isInteractionPassThrough;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 3145728;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            int eventType = accessibilityEvent.getEventType();
            if (eventType != 1048576) {
                if (eventType == 2097152 && this.isInteractionPassThrough) {
                    this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$ID(Interpretation$ID.Value.PASS_THROUGH_INTERACTION_END), null);
                    return;
                }
                return;
            }
            if (this.actorState.writable.passThroughModeState$ar$class_merging$ar$class_merging$ar$class_merging.isPassThroughModeActive()) {
                this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$ID(Interpretation$ID.Value.PASS_THROUGH_INTERACTION_START), null);
                this.isInteractionPassThrough = true;
            } else {
                this.isInteractionPassThrough = false;
            }
        }
    }
}
