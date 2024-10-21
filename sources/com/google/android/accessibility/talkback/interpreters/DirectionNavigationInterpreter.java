package com.google.android.accessibility.talkback.interpreters;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$DirectionNavigation;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirectionNavigationInterpreter implements AccessibilityEventListener {
    public ActorState actorState;
    private final Context context;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public DirectionNavigationInterpreter(Context context) {
        this.context = context;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 32768;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        NavigationAction navigationAction;
        CursorGranularity cursorGranularity;
        ActorState actorState = this.actorState;
        if (actorState != null) {
            FocusActionInfo focusActionInfoFromEvent = actorState.getFocusHistory$ar$class_merging$ar$class_merging().getFocusActionInfoFromEvent(accessibilityEvent);
            if (focusActionInfoFromEvent == null) {
                LogUtils.w("DirectionNavigationInterpreter", "Accessibility focus is not assigned by TalkBack. Unable to find source action info for event: %s", accessibilityEvent);
                return;
            }
            AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
            if (focusActionInfoFromEvent.sourceAction == 4 && (navigationAction = focusActionInfoFromEvent.navigationAction) != null && (cursorGranularity = navigationAction.originalNavigationGranularity) != null && cursorGranularity.isMicroGranularity()) {
                if (true == AccessibilityNodeInfoUtils.isKeyboard(compat)) {
                    compat = null;
                }
                this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$DirectionNavigation(TraversalStrategyUtils.getLogicalDirection(navigationAction.searchDirection, SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(this.context)), compat), null);
            }
        }
    }
}
