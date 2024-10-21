package com.google.android.accessibility.talkback.interpreters;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$ID;
import com.google.android.accessibility.talkback.actor.NodeActionPerformer;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StateChangeEventInterpreter implements AccessibilityEventListener {
    public ActorState actorState;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 2048;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        AccessibilityNodeInfo source;
        NodeActionPerformer.NodeActionRecord nodeActionRecord;
        if (accessibilityEvent.getEventType() == 2048 && (accessibilityEvent.getContentChangeTypes() & 64) != 0 && (source = accessibilityEvent.getSource()) != null && (nodeActionRecord = ((NodeActionPerformer) this.actorState.writable.nodeActionState$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).actionRecord) != null) {
            long eventTime = accessibilityEvent.getEventTime() - nodeActionRecord.actionTime;
            if (eventTime >= 0 && eventTime <= 500 && !AccessibilityNodeInfoUtils.isSelfOrAncestorFocused(AccessibilityNodeInfoUtils.toCompat(source)) && nodeActionRecord.actionedNodeMatches(source)) {
                this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$ID(Interpretation$ID.Value.STATE_CHANGE), null);
            }
        }
    }
}
