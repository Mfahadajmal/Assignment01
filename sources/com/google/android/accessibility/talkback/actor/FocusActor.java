package com.google.android.accessibility.talkback.actor;

import android.accessibilityservice.AccessibilityService;
import android.os.SystemClock;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.ActorStateWritable;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusActor {
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public ActorStateWritable actorState;
    public final FocusManagerInternal focusManagerInternal;
    private final FloatingActionButton.ShadowDelegateImpl gestureDetectionState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final AccessibilityFocusActionHistory history;
    public Pipeline.FeedbackReturner pipeline;
    private final AccessibilityService service;
    public final NumberAdjustor webActor$ar$class_merging;

    public FocusActor(AccessibilityService accessibilityService, AppLifecycleMonitor appLifecycleMonitor, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, AccessibilityFocusActionHistory accessibilityFocusActionHistory, AccessibilityFocusMonitor accessibilityFocusMonitor, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.service = accessibilityService;
        this.history = accessibilityFocusActionHistory;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.gestureDetectionState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.focusManagerInternal = new FocusManagerInternal(accessibilityService, appLifecycleMonitor, hapticPatternParser$$ExternalSyntheticLambda1, accessibilityFocusActionHistory, accessibilityFocusMonitor);
        this.webActor$ar$class_merging = new NumberAdjustor(accessibilityService, new HapticPatternParser$$ExternalSyntheticLambda1(this));
    }

    public final boolean cacheNodeToRestoreFocus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityWindowInfoCompat window;
        if (accessibilityNodeInfoCompat != null) {
            this.history.cachedNodeToRestoreFocus = accessibilityNodeInfoCompat;
            return true;
        }
        AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
        if (accessibilityFocus == null || (window = AccessibilityNodeInfoUtils.getWindow(accessibilityFocus)) == null || (window.isActive() && window.getType() != 3)) {
            return false;
        }
        this.history.cachedNodeToRestoreFocus = accessibilityFocus;
        return true;
    }

    public final void clearAccessibilityFocus(Performance.EventId eventId) {
        FocusManagerInternal focusManagerInternal = this.focusManagerInternal;
        AccessibilityNodeInfoCompat accessibilityFocus = focusManagerInternal.accessibilityFocusMonitor.getAccessibilityFocus(false);
        if (accessibilityFocus != null) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(focusManagerInternal.pipeline, eventId, Feedback.nodeAction(accessibilityFocus, BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE));
        }
    }

    public final boolean clickCurrentFocus(Performance.EventId eventId) {
        return clickNode(this.accessibilityFocusMonitor.getAccessibilityFocus(false), eventId);
    }

    public final boolean clickCurrentHierarchical(Performance.EventId eventId) {
        AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
        if (accessibilityFocus == null) {
            return false;
        }
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityFocus, AccessibilityNodeInfoUtils.FILTER_CLICKABLE), AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.getId()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004c, code lost:
    
        r13 = r11.service.getTouchInteractionController(0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean clickNode(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r12, com.google.android.accessibility.utils.Performance.EventId r13) {
        /*
            r11 = this;
            r0 = 0
            if (r12 == 0) goto La3
            com.google.android.accessibility.talkback.Pipeline$FeedbackReturner r1 = r11.pipeline
            if (r1 != 0) goto L9
            goto La3
        L9:
            java.util.List r1 = r12.getActionList()
            java.util.Iterator r1 = r1.iterator()
        L11:
            boolean r2 = r1.hasNext()
            r3 = 1
            if (r2 == 0) goto L3a
            java.lang.Object r2 = r1.next()
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r2 = (androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) r2
            int r2 = r2.getId()
            r4 = 16
            if (r2 != r4) goto L11
            com.google.android.accessibility.talkback.Pipeline$FeedbackReturner r1 = r11.pipeline
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r2 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK
            int r2 = r2.getId()
            com.google.android.accessibility.talkback.Feedback$Part$Builder r2 = com.google.android.accessibility.talkback.Feedback.nodeAction(r12, r2)
            boolean r13 = com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r1, r13, r2)
            if (r13 != 0) goto L39
            goto L3a
        L39:
            return r3
        L3a:
            boolean r13 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.isAtLeastT()
            if (r13 == 0) goto L58
            com.google.android.material.floatingactionbutton.FloatingActionButton$ShadowDelegateImpl r13 = r11.gestureDetectionState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
            java.lang.Object r13 = r13.FloatingActionButton$ShadowDelegateImpl$ar$this$0
            com.google.android.accessibility.talkback.TalkBackService r13 = (com.google.android.accessibility.talkback.TalkBackService) r13
            boolean r13 = r13.shouldUseTalkbackGestureDetection()
            if (r13 == 0) goto L58
            android.accessibilityservice.AccessibilityService r13 = r11.service
            android.accessibilityservice.TouchInteractionController r13 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m(r13, r0)
            if (r13 == 0) goto L58
            org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m$2(r13)
            return r3
        L58:
            android.accessibilityservice.AccessibilityService r13 = r11.service
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r12.getBoundsInScreen(r0)
            android.graphics.Path r12 = new android.graphics.Path
            r12.<init>()
            int r1 = r0.centerX()
            float r1 = (float) r1
            int r0 = r0.centerY()
            float r0 = (float) r0
            r12.moveTo(r1, r0)
            int r0 = android.view.ViewConfiguration.getTapTimeout()
            android.accessibilityservice.GestureDescription$Builder r7 = new android.accessibilityservice.GestureDescription$Builder
            r7.<init>()
            long r8 = (long) r0
            android.accessibilityservice.GestureDescription$StrokeDescription r10 = new android.accessibilityservice.GestureDescription$StrokeDescription
            r3 = 0
            r1 = r10
            r2 = r12
            r5 = r8
            r1.<init>(r2, r3, r5)
            android.accessibilityservice.GestureDescription$Builder r7 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m(r7, r10)
            int r0 = r0 + 40
            long r3 = (long) r0
            android.accessibilityservice.GestureDescription$StrokeDescription r0 = new android.accessibilityservice.GestureDescription$StrokeDescription
            r1 = r0
            r1.<init>(r2, r3, r5)
            android.accessibilityservice.GestureDescription$Builder r12 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m(r7, r0)
            android.accessibilityservice.GestureDescription r12 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m(r12)
            r0 = 0
            boolean r12 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m(r13, r12, r0, r0)
            return r12
        La3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.FocusActor.clickNode(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, com.google.android.accessibility.utils.Performance$EventId):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x012e, code lost:
    
        if (r1 != false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean ensureAccessibilityFocusOnScreen(com.google.android.accessibility.utils.Performance.EventId r12) {
        /*
            Method dump skipped, instructions count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.FocusActor.ensureAccessibilityFocusOnScreen(com.google.android.accessibility.utils.Performance$EventId):boolean");
    }

    public final boolean longClickCurrentFocus(Performance.EventId eventId) {
        return longClickNode(this.accessibilityFocusMonitor.getAccessibilityFocus(false), eventId);
    }

    public final boolean longClickNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        Pipeline.FeedbackReturner feedbackReturner;
        if (accessibilityNodeInfoCompat != null && (feedbackReturner = this.pipeline) != null) {
            return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_LONG_CLICK.getId()));
        }
        return false;
    }

    public final void overrideNextFocusRestorationForWindowTransition() {
        this.actorState.overrideFocusRestoreUptimeMs = SystemClock.uptimeMillis();
    }

    public final boolean popCachedNodeToRestoreFocus() {
        if (this.history.popCachedNodeToRestoreFocus() != null) {
            return true;
        }
        return false;
    }

    public final void renewEnsureFocus() {
        this.focusManagerInternal.skipRefocus = false;
    }

    public final boolean restoreFocus(Performance.EventId eventId) {
        AccessibilityNodeInfoCompat popCachedNodeToRestoreFocus = this.history.popCachedNodeToRestoreFocus();
        if (popCachedNodeToRestoreFocus != null && popCachedNodeToRestoreFocus.refresh() && popCachedNodeToRestoreFocus.isVisibleToUser() && AccessibilityNodeInfoUtils.isInWindow(popCachedNodeToRestoreFocus, AccessibilityNodeInfoUtils.getWindow(popCachedNodeToRestoreFocus))) {
            FocusManagerInternal focusManagerInternal = this.focusManagerInternal;
            FocusActionInfo.Builder builder = new FocusActionInfo.Builder();
            builder.sourceAction = 5;
            builder.initialFocusType = 2;
            if (focusManagerInternal.setAccessibilityFocus(popCachedNodeToRestoreFocus, false, new FocusActionInfo(builder), eventId)) {
                return true;
            }
        }
        return false;
    }

    public final boolean setAccessibilityFocus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, FocusActionInfo focusActionInfo, Performance.EventId eventId) {
        return this.focusManagerInternal.setAccessibilityFocus(accessibilityNodeInfoCompat, z, focusActionInfo, eventId);
    }

    public final void setMuteNextFocus() {
        this.focusManagerInternal.muteNextFocus = true;
    }

    public final void updateFocusHistory(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, FocusActionInfo focusActionInfo) {
        FocusManagerInternal focusManagerInternal = this.focusManagerInternal;
        AppLifecycleMonitor appLifecycleMonitor = focusManagerInternal.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        long uptimeMillis = SystemClock.uptimeMillis();
        AccessibilityNodeInfoCompat findFocusCompat = appLifecycleMonitor.findFocusCompat(2);
        LogUtils.d("FocusManagerInternal", "Navigate in web:result=%s\nNode:%s\nFocusActionInfo:%s", findFocusCompat, accessibilityNodeInfoCompat, focusActionInfo);
        FocusActionInfo updateFocusActionInfoIfNecessary = focusManagerInternal.updateFocusActionInfoIfNecessary(focusActionInfo, findFocusCompat);
        if (findFocusCompat != null && !accessibilityNodeInfoCompat.equals(findFocusCompat)) {
            focusManagerInternal.history.onAccessibilityFocusAction(findFocusCompat, updateFocusActionInfoIfNecessary, uptimeMillis, focusManagerInternal.screenState$ar$class_merging$ar$class_merging$ar$class_merging.getStableScreenState());
            return;
        }
        AccessibilityFocusActionHistory accessibilityFocusActionHistory = focusManagerInternal.history;
        ScreenState stableScreenState = focusManagerInternal.screenState$ar$class_merging$ar$class_merging$ar$class_merging.getStableScreenState();
        accessibilityFocusActionHistory.pendingWebFocusActionInfo = updateFocusActionInfoIfNecessary;
        accessibilityFocusActionHistory.pendingScreenState = stableScreenState;
        accessibilityFocusActionHistory.pendingWebFocusActionTime = uptimeMillis;
    }
}
