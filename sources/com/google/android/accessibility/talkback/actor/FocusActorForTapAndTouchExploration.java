package com.google.android.accessibility.talkback.actor;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusActorForTapAndTouchExploration {
    protected static final FocusActionInfo NON_REFOCUS_ACTION_INFO;
    protected static final FocusActionInfo REFOCUS_ACTION_INFO;
    public ActorState actorState;
    public Pipeline.FeedbackReturner pipeline;

    static {
        FocusActionInfo.Builder builder = new FocusActionInfo.Builder();
        builder.sourceAction = 2;
        builder.isFromRefocusAction = true;
        REFOCUS_ACTION_INFO = new FocusActionInfo(builder);
        FocusActionInfo.Builder builder2 = new FocusActionInfo.Builder();
        builder2.sourceAction = 2;
        builder2.isFromRefocusAction = false;
        NON_REFOCUS_ACTION_INFO = new FocusActionInfo(builder2);
    }

    public final boolean setAccessibilityFocus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, Performance.EventId eventId) {
        FocusActionInfo focusActionInfo;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2;
        boolean z2 = false;
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        FocusActionRecord lastFocusActionRecord = ((AccessibilityFocusActionHistory) this.actorState.getFocusHistory$ar$class_merging$ar$class_merging().HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getLastFocusActionRecord();
        if (lastFocusActionRecord != null) {
            if (Objects.equals(lastFocusActionRecord.uniqueId, FocusActionRecord.compoundPackageNameAndUniqueId(accessibilityNodeInfoCompat)) && (lastFocusActionRecord.uniqueId != null || (accessibilityNodeInfoCompat2 = lastFocusActionRecord.focusedNode) == accessibilityNodeInfoCompat || accessibilityNodeInfoCompat2.equals(accessibilityNodeInfoCompat))) {
                z2 = true;
            }
        }
        boolean z3 = z | (!z2);
        if (z3) {
            focusActionInfo = REFOCUS_ACTION_INFO;
        } else {
            focusActionInfo = NON_REFOCUS_ACTION_INFO;
        }
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Feedback.Focus.Builder focus = Feedback.focus(accessibilityNodeInfoCompat, focusActionInfo);
        focus.setForceRefocus$ar$ds(z3);
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, focus);
    }
}
