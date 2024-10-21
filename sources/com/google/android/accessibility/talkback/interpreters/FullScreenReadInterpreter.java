package com.google.android.accessibility.talkback.interpreters;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$ID;
import com.google.android.accessibility.talkback.actor.FullScreenReadActor;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FullScreenReadInterpreter implements AccessibilityEventListener {
    public ActorState actorState;
    private long continuousReadStartTime = 0;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 49671;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        if (accessibilityEvent.getEventType() == 32768) {
            if (!((FullScreenReadActor) this.actorState.getContinuousRead$ar$class_merging$ar$class_merging().HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).fullScreenReadDialog.waitingForContentFocus) {
                this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$ID(Interpretation$ID.Value.CONTINUOUS_READ_IGNORE), null);
            } else {
                this.continuousReadStartTime = accessibilityEvent.getEventTime();
                this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$ID(Interpretation$ID.Value.CONTINUOUS_READ_CONTENT_FOCUSED), null);
            }
        }
        if (this.actorState.getContinuousRead$ar$class_merging$ar$class_merging().isActive()) {
            if (accessibilityEvent.getEventType() == 1 && accessibilityEvent.getEventTime() - this.continuousReadStartTime < 200) {
                LogUtils.i("FullScreenReadInterpreter", "Skip event because continuous read focused is working recently: %s", accessibilityEvent);
                return;
            }
            if (accessibilityEvent.getEventType() == 4) {
                AccessibilityNodeInfo source = accessibilityEvent.getSource();
                if (SpannableUtils$IdentifierSpan.getRole(source) != 18 && SpannableUtils$IdentifierSpan.getRole(source) != 10) {
                    this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$ID(Interpretation$ID.Value.CONTINUOUS_READ_INTERRUPT), null);
                    return;
                }
                return;
            }
            if (SpannableUtils$IdentifierSpan.eventMatchesAnyType(accessibilityEvent, 16899)) {
                this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$ID(Interpretation$ID.Value.CONTINUOUS_READ_INTERRUPT), null);
            }
        }
    }
}
