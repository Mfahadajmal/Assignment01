package com.google.android.accessibility.talkback.actor;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Interpreters$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.performance.AccessibilityActionDetails;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodeActionPerformer {
    public NodeActionRecord actionRecord = null;
    public final FloatingActionButton.ShadowDelegateImpl stateReader$ar$class_merging$1a9fae11_0$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NodeActionRecord {
        public final Object NodeActionPerformer$NodeActionRecord$ar$targetNode;
        public final long actionTime;

        public NodeActionRecord(Object obj, long j) {
            this.NodeActionPerformer$NodeActionRecord$ar$targetNode = obj;
            this.actionTime = j;
        }

        public final boolean actionedNodeMatches(AccessibilityNodeInfo accessibilityNodeInfo) {
            return ((AccessibilityNode) this.NodeActionPerformer$NodeActionRecord$ar$targetNode).getBare().equals(accessibilityNodeInfo);
        }
    }

    public final boolean performAction(Feedback.NodeAction nodeAction, Performance.EventId eventId) {
        Performance.EventData recentEvent;
        LogUtils.d("NodeActionPerformer", "perform Action, nodeAction = %s", nodeAction);
        AccessibilityNode accessibilityNode = nodeAction.target;
        if (accessibilityNode == null) {
            return true;
        }
        int i = nodeAction.actionId;
        Bundle bundle = nodeAction.args;
        long uptimeMillis = SystemClock.uptimeMillis();
        boolean performAction = SpannableUtils$IdentifierSpan.performAction(accessibilityNode.getCompat(), i, bundle, eventId);
        int i2 = nodeAction.actionId;
        Performance performance = Performance.instance;
        if (performance.trackEvents() && (recentEvent = performance.getRecentEvent(eventId)) != null && recentEvent.actionDetails == null) {
            long uptimeMillis2 = SystemClock.uptimeMillis();
            recentEvent.actionDetails = new AccessibilityActionDetails(i2, uptimeMillis2 - uptimeMillis, uptimeMillis2, performAction);
            performance.notifyLatencyTracker(new Interpreters$$ExternalSyntheticLambda1(recentEvent, 3));
            if (performance.computeStatsEnabled) {
                long j = uptimeMillis2 - recentEvent.uptimeReceivedAtTalkback;
                String[] strArr = recentEvent.labels;
                for (int i3 = 0; i3 <= 0; i3++) {
                    performance.getOrCreateStatistics(strArr[i3], 6).increment(j);
                }
            }
        }
        if (!performAction) {
            return performAction;
        }
        this.actionRecord = new NodeActionRecord(accessibilityNode, uptimeMillis);
        return true;
    }
}
