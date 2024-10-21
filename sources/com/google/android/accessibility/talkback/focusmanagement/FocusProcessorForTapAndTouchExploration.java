package com.google.android.accessibility.talkback.focusmanagement;

import android.os.Message;
import android.os.SystemClock;
import android.view.ViewConfiguration;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$CompositorID;
import com.google.android.accessibility.talkback.Interpretation$Touch;
import com.google.android.accessibility.talkback.Interpretation$TouchInteraction;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusProcessorForTapAndTouchExploration {
    public static final long TAP_TIMEOUT_MS = ViewConfiguration.getJumpTapTimeout();
    public ActorState actorState;
    private final TalkBackAnalytics analytics;
    public HapticPatternParser$$ExternalSyntheticLambda1 interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public AccessibilityNodeInfoCompat lastFocusableNodeBeingTouched;
    public long touchInteractionStartTime;
    public long longPressDuration = 3000;
    public boolean isSingleTapEnabled = false;
    public int typingMethod = 1;
    public boolean mayBeRefocusAction = true;
    private boolean mayBeSingleTap = true;
    private boolean hasHoveredEnterNode = false;
    public boolean mayBeLiftToType = true;
    public boolean isSplitTap = false;
    public final PostDelayHandler postDelayHandler = new PostDelayHandler(this, 3000);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PostDelayHandler extends WeakReferenceHandler {
        public long longPressDelayMs;

        public PostDelayHandler(FocusProcessorForTapAndTouchExploration focusProcessorForTapAndTouchExploration, long j) {
            super(focusProcessorForTapAndTouchExploration);
            this.longPressDelayMs = 3000L;
        }

        public final void cancelLongPress() {
            removeMessages(2);
        }

        public final void cancelRefocusTimeout() {
            removeMessages(1);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            FocusProcessorForTapAndTouchExploration focusProcessorForTapAndTouchExploration = (FocusProcessorForTapAndTouchExploration) obj;
            int i = message.what;
            if (i != 1) {
                if (i == 2 && focusProcessorForTapAndTouchExploration.supportsLiftToType(focusProcessorForTapAndTouchExploration.lastFocusableNodeBeingTouched)) {
                    focusProcessorForTapAndTouchExploration.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(null, null, new Interpretation$Touch(Interpretation$Touch.Action.LONG_PRESS, focusProcessorForTapAndTouchExploration.lastFocusableNodeBeingTouched), null);
                    focusProcessorForTapAndTouchExploration.mayBeLiftToType = false;
                    return;
                }
                return;
            }
            focusProcessorForTapAndTouchExploration.touchFocusedNode(focusProcessorForTapAndTouchExploration.lastFocusableNodeBeingTouched, null);
        }

        public final void longPressAfterTimeout() {
            removeMessages(2);
            sendEmptyMessageDelayed(2, this.longPressDelayMs);
        }
    }

    public FocusProcessorForTapAndTouchExploration(TalkBackAnalytics talkBackAnalytics) {
        this.analytics = talkBackAnalytics;
    }

    private final boolean onHoverEnterGeneralNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        boolean input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        this.mayBeSingleTap = false;
        this.mayBeRefocusAction = false;
        if (accessibilityNodeInfoCompat == null) {
            this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$Touch(Interpretation$Touch.Action.TOUCH_NOTHING, null), null);
            return false;
        }
        input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$Touch(Interpretation$Touch.Action.TOUCH_UNFOCUSED_NODE, accessibilityNodeInfoCompat), null);
        if (input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging && isEnableLiftToType() && supportsLiftToType(accessibilityNodeInfoCompat) && AccessibilityNodeInfoUtils.isLongClickable(accessibilityNodeInfoCompat)) {
            this.postDelayHandler.longPressAfterTimeout();
            return true;
        }
        return input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final boolean handleHoverEnterNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        boolean onHoverEnterGeneralNode;
        this.postDelayHandler.cancelLongPress();
        this.postDelayHandler.cancelRefocusTimeout();
        if (accessibilityNodeInfoCompat != null && !accessibilityNodeInfoCompat.isAccessibilityFocused()) {
            this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$Touch(Interpretation$Touch.Action.TOUCH_ENTERED_UNFOCUSED_NODE, null), null);
        }
        if (!this.hasHoveredEnterNode) {
            this.hasHoveredEnterNode = true;
            if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.isAccessibilityFocused()) {
                onHoverEnterGeneralNode = false;
                if (isEnableLiftToType() && supportsLiftToType(accessibilityNodeInfoCompat)) {
                    if (accessibilityNodeInfoCompat.isAccessibilityFocused()) {
                        this.mayBeRefocusAction = false;
                        if (AccessibilityNodeInfoUtils.isLongClickable(accessibilityNodeInfoCompat)) {
                            this.postDelayHandler.longPressAfterTimeout();
                        }
                        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                        Logger logger = Performance.DEFAULT_LOGGER;
                        hapticPatternParser$$ExternalSyntheticLambda1.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(null, null, new Interpretation$CompositorID(1073741939), accessibilityNodeInfoCompat);
                    } else {
                        this.mayBeRefocusAction = true;
                        if (AccessibilityNodeInfoUtils.isLongClickable(accessibilityNodeInfoCompat)) {
                            this.postDelayHandler.longPressAfterTimeout();
                        }
                    }
                } else if (this.isSingleTapEnabled) {
                    PostDelayHandler postDelayHandler = this.postDelayHandler;
                    postDelayHandler.removeMessages(1);
                    postDelayHandler.sendMessageDelayed(postDelayHandler.obtainMessage(1), TAP_TIMEOUT_MS);
                } else {
                    onHoverEnterGeneralNode = touchFocusedNode(accessibilityNodeInfoCompat, eventId);
                }
            } else {
                onHoverEnterGeneralNode = onHoverEnterGeneralNode(accessibilityNodeInfoCompat, eventId);
            }
        } else {
            onHoverEnterGeneralNode = onHoverEnterGeneralNode(accessibilityNodeInfoCompat, eventId);
        }
        this.mayBeLiftToType = true;
        this.lastFocusableNodeBeingTouched = accessibilityNodeInfoCompat;
        return onHoverEnterGeneralNode;
    }

    public final boolean handleTouchInteractionEnd(Performance.EventId eventId) {
        boolean input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        this.postDelayHandler.cancelRefocusTimeout();
        this.postDelayHandler.cancelLongPress();
        long uptimeMillis = SystemClock.uptimeMillis();
        if (isEnableLiftToType() && supportsLiftToType(this.lastFocusableNodeBeingTouched) && this.mayBeLiftToType && !this.isSplitTap) {
            this.analytics.onGesture(62);
            input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$Touch(Interpretation$Touch.Action.LIFT, this.lastFocusableNodeBeingTouched), null);
        } else {
            input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = (this.isSingleTapEnabled && this.mayBeSingleTap && uptimeMillis - this.touchInteractionStartTime < TAP_TIMEOUT_MS) ? this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$Touch(Interpretation$Touch.Action.TAP, this.lastFocusableNodeBeingTouched), null) : false;
        }
        this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$TouchInteraction(false), null);
        reset();
        return input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final boolean isEnableLiftToType() {
        if (this.typingMethod != 0) {
            return true;
        }
        return false;
    }

    public final void reset() {
        this.lastFocusableNodeBeingTouched = null;
        this.hasHoveredEnterNode = false;
        this.mayBeRefocusAction = true;
        this.mayBeSingleTap = true;
        this.mayBeLiftToType = true;
        this.isSplitTap = false;
    }

    public final boolean supportsLiftToType(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 34) {
            return true;
        }
        if (this.typingMethod == 2 && SpannableUtils$IdentifierSpan.getType(AccessibilityNodeInfoUtils.getWindow(accessibilityNodeInfoCompat)) == 2) {
            return true;
        }
        return false;
    }

    public final boolean touchFocusedNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        boolean input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (this.mayBeRefocusAction && accessibilityNodeInfoCompat != null) {
            input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$Touch(Interpretation$Touch.Action.TOUCH_FOCUSED_NODE, accessibilityNodeInfoCompat), null);
            if (input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging) {
                return true;
            }
            return false;
        }
        return false;
    }
}
