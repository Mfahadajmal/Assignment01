package com.google.android.accessibility.talkback.interpreters;

import android.content.Context;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$InputFocus;
import com.google.android.accessibility.talkback.Interpretation$Touch;
import com.google.android.accessibility.talkback.Interpretation$TouchInteraction;
import com.google.android.accessibility.talkback.Interpretation$WindowChange;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForScreenStateChange;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForTapAndTouchExploration;
import com.google.android.accessibility.talkback.focusmanagement.action.TouchExplorationAction;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenStateMonitor;
import com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityFocusInterpreter implements ScreenStateMonitor.ScreenStateChangeListener {
    public final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public ActorState actorState;
    public final Context context;
    public final FocusProcessorForScreenStateChange focusProcessorForScreenStateChange;
    public final FocusProcessorForTapAndTouchExploration focusProcessorForTapAndTouchExploration;
    public final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
    public HapticPatternParser$$ExternalSyntheticLambda1 pipelineInterpretations$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final HapticPatternParser$$ExternalSyntheticLambda1 screenState$ar$class_merging$ar$class_merging$ar$class_merging;

    public AccessibilityFocusInterpreter(Context context, AccessibilityFocusMonitor accessibilityFocusMonitor, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, TalkBackAnalytics talkBackAnalytics) {
        this.context = context;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.screenState$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.focusProcessorForTapAndTouchExploration = new FocusProcessorForTapAndTouchExploration(talkBackAnalytics);
        this.focusProcessorForScreenStateChange = new FocusProcessorForScreenStateChange(accessibilityFocusMonitor);
    }

    @Override // com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenStateMonitor.ScreenStateChangeListener
    public final void onScreenStateChanged$ar$ds(ScreenState screenState, Performance.EventId eventId) {
        FocusProcessorForScreenStateChange.FocusResult focusResult;
        boolean z;
        int i;
        FocusProcessorForScreenStateChange focusProcessorForScreenStateChange = this.focusProcessorForScreenStateChange;
        try {
            AccessibilityWindowInfo accessibilityWindowInfo = screenState.activeWindow;
            if (accessibilityWindowInfo == null) {
                focusResult = FocusProcessorForScreenStateChange.FocusResult.FAIL_NO_ACTIVE_WINDOW;
            } else {
                FocusActionRecord lastFocusActionRecordInWindow = focusProcessorForScreenStateChange.actorState.getFocusHistory$ar$class_merging$ar$class_merging().getLastFocusActionRecordInWindow(AccessibilityFocusActionHistory.WindowIdentifier.create(accessibilityWindowInfo.getId(), screenState));
                if (lastFocusActionRecordInWindow != null && lastFocusActionRecordInWindow.actionTime > screenState.screenTransitionStartTime && ((i = lastFocusActionRecordInWindow.extraInfo.sourceAction) == 2 || i == 4)) {
                    focusResult = FocusProcessorForScreenStateChange.FocusResult.FAIL_HAS_INTERACTION;
                } else {
                    if (!accessibilityWindowInfo.isAccessibilityFocused()) {
                        LogUtils.w("FocusProcessor-ScreenStateChange", "hasValidAccessibilityFocusInWindow: window %d not accessibilityFocused", Integer.valueOf(accessibilityWindowInfo.getId()));
                    } else {
                        AccessibilityNodeInfoCompat accessibilityFocus = focusProcessorForScreenStateChange.accessibilityFocusMonitor.getAccessibilityFocus(false);
                        if (accessibilityFocus != null && AccessibilityNodeInfoUtils.isVisible(accessibilityFocus) && accessibilityFocus.getWindowId() == accessibilityWindowInfo.getId()) {
                            focusResult = FocusProcessorForScreenStateChange.FocusResult.FAIL_HAS_VALID_FOCUS;
                        }
                    }
                    if (focusProcessorForScreenStateChange.actorState.getOverrideFocusRestoreUptimeMs() > focusProcessorForScreenStateChange.handledOverrideFocusRestoreUptimeMs) {
                        z = true;
                    } else {
                        z = false;
                    }
                    focusProcessorForScreenStateChange.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$WindowChange(z, screenState), null);
                    focusResult = FocusProcessorForScreenStateChange.FocusResult.SUCCESS;
                }
            }
            focusProcessorForScreenStateChange.handledOverrideFocusRestoreUptimeMs = focusProcessorForScreenStateChange.actorState.getOverrideFocusRestoreUptimeMs();
            LogUtils.d("FocusProcessor-ScreenStateChange", "Screen state changed with result=%s : \nDuration=%s\nFrom: %s", focusResult, Long.valueOf(SystemClock.uptimeMillis() - screenState.screenTransitionStartTime), screenState);
            FocusProcessorForScreenStateChange.FocusResult focusResult2 = FocusProcessorForScreenStateChange.FocusResult.SUCCESS;
        } catch (Throwable th) {
            focusProcessorForScreenStateChange.handledOverrideFocusRestoreUptimeMs = focusProcessorForScreenStateChange.actorState.getOverrideFocusRestoreUptimeMs();
            throw th;
        }
    }

    public final boolean onTouchExplorationAction(TouchExplorationAction touchExplorationAction, Performance.EventId eventId) {
        LogUtils.d("A11yFocusInterp", "User action: %s", touchExplorationAction);
        int i = touchExplorationAction.type;
        FocusProcessorForTapAndTouchExploration focusProcessorForTapAndTouchExploration = this.focusProcessorForTapAndTouchExploration;
        if (i != 0) {
            if (i != 1) {
                return focusProcessorForTapAndTouchExploration.handleTouchInteractionEnd(eventId);
            }
            return focusProcessorForTapAndTouchExploration.handleHoverEnterNode(touchExplorationAction.touchedFocusableNode, eventId);
        }
        focusProcessorForTapAndTouchExploration.reset();
        focusProcessorForTapAndTouchExploration.touchInteractionStartTime = SystemClock.uptimeMillis();
        focusProcessorForTapAndTouchExploration.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$TouchInteraction(true), null);
        if (!focusProcessorForTapAndTouchExploration.actorState.getSpeechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().isSpeaking()) {
            return false;
        }
        focusProcessorForTapAndTouchExploration.mayBeRefocusAction = true;
        focusProcessorForTapAndTouchExploration.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$Touch(Interpretation$Touch.Action.TOUCH_START, null), null);
        return false;
    }

    public final void onViewTargeted(Performance.EventId eventId, AccessibilityEvent accessibilityEvent, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (this.screenState$ar$class_merging$ar$class_merging$ar$class_merging.areMainWindowsStable()) {
            this.pipelineInterpretations$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$InputFocus(accessibilityNodeInfoCompat), null);
        } else {
            LogUtils.w("A11yFocusInterp", "onViewTargeted return due to windows are not stable and the focus will be handled by onScreenStateChanged after main windows are stable.", new Object[0]);
        }
    }
}
