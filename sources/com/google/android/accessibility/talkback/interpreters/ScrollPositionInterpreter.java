package com.google.android.accessibility.talkback.interpreters;

import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.talkback.Interpretation$CompositorID;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.DelayHandler;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.input.ScrollEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScrollPositionInterpreter implements ScrollEventInterpreter.ScrollEventHandler {
    private final DelayHandler delayHandler = new DelayHandler() { // from class: com.google.android.accessibility.talkback.interpreters.ScrollPositionInterpreter.1
        @Override // com.google.android.accessibility.utils.DelayHandler
        public final void handle(Object obj) {
            ScrollPositionInterpreter scrollPositionInterpreter = ScrollPositionInterpreter.this;
            AccessibilityEvent accessibilityEvent = scrollPositionInterpreter.eventDeduplicated;
            if (accessibilityEvent != null) {
                scrollPositionInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(scrollPositionInterpreter.eventId, accessibilityEvent, new Interpretation$CompositorID(1073741938), null);
                scrollPositionInterpreter.clearEventDeduplicated();
            }
        }
    };
    public AccessibilityEvent eventDeduplicated;
    public Performance.EventId eventId;
    public boolean isVerbose;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public final void clearEventDeduplicated() {
        this.eventDeduplicated = null;
        this.eventId = null;
    }

    @Override // com.google.android.accessibility.utils.input.ScrollEventInterpreter.ScrollEventHandler
    public final void onScrollEvent(AccessibilityEvent accessibilityEvent, ScrollEventInterpreter.ScrollEventInterpretation scrollEventInterpretation, Performance.EventId eventId) {
        long j;
        if (scrollEventInterpretation.hasValidIndex && !scrollEventInterpretation.isDuplicateEvent) {
            if (this.isVerbose || scrollEventInterpretation.userAction == 2 || (SpannableUtils$IdentifierSpan.getSourceRole(accessibilityEvent) == 16 && scrollEventInterpretation.userAction == 3)) {
                if (SpannableUtils$IdentifierSpan.getSourceRole(this.eventDeduplicated) == 16) {
                    AccessibilityNode takeOwnership = AccessibilityNode.takeOwnership(accessibilityEvent.getSource());
                    AccessibilityNode takeOwnership2 = AccessibilityNode.takeOwnership(this.eventDeduplicated.getSource());
                    if (takeOwnership2 != null && !takeOwnership2.equals(takeOwnership) && takeOwnership.getCompat().getCollectionInfo$ar$class_merging() != null && takeOwnership != null) {
                        AccessibilityNode parent = takeOwnership.getParent();
                        AccessibilityNode parent2 = takeOwnership2.getParent();
                        if (parent != null && parent.equals(parent2)) {
                            return;
                        }
                    }
                }
                clearEventDeduplicated();
                this.eventDeduplicated = AccessibilityEvent.obtain(accessibilityEvent);
                this.eventId = eventId;
                this.delayHandler.removeMessages();
                DelayHandler delayHandler = this.delayHandler;
                if (SpannableUtils$IdentifierSpan.getSourceRole(accessibilityEvent) == 16) {
                    j = 500;
                } else {
                    j = 1000;
                }
                delayHandler.delay(j, null);
            }
        }
    }
}
