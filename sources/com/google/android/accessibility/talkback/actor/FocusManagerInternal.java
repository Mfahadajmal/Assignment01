package com.google.android.accessibility.talkback.actor;

import android.accessibilityservice.AccessibilityService;
import android.os.SystemClock;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.ActorStateWritable;
import com.google.android.accessibility.talkback.CursorGranularityManager;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.NodeActionPerformer;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import java.util.ArrayList;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusManagerInternal {
    public final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public ActorStateWritable actorState;
    private final boolean controlInputFocus;
    public final AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FormFactorUtils formFactorUtils;
    public final AccessibilityFocusActionHistory history;
    public Pipeline.FeedbackReturner pipeline;
    public final HapticPatternParser$$ExternalSyntheticLambda1 screenState$ar$class_merging$ar$class_merging$ar$class_merging;
    public final AccessibilityService service;
    protected boolean muteNextFocus = false;
    public boolean skipRefocus = false;

    public FocusManagerInternal(AccessibilityService accessibilityService, AppLifecycleMonitor appLifecycleMonitor, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, AccessibilityFocusActionHistory accessibilityFocusActionHistory, AccessibilityFocusMonitor accessibilityFocusMonitor) {
        FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
        this.formFactorUtils = formFactorUtils;
        this.service = accessibilityService;
        this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.screenState$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.history = accessibilityFocusActionHistory;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.controlInputFocus = formFactorUtils.isAndroidTv;
    }

    public static final Feedback.Part toFeedbackPart$ar$ds(Feedback.Focus.Action action, ScreenState screenState) {
        Feedback.Part.Builder builder = Feedback.Part.builder();
        Feedback.Focus.Builder focus = Feedback.focus(action);
        focus.screenState = screenState;
        builder.focus = focus.build();
        return builder.build();
    }

    public final boolean setAccessibilityFocus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, FocusActionInfo focusActionInfo, Performance.EventId eventId) {
        FocusActionInfo focusActionInfo2;
        if (accessibilityNodeInfoCompat.isAccessibilityFocused()) {
            if (!z) {
                return true;
            }
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE));
        }
        if (this.controlInputFocus && accessibilityNodeInfoCompat.isFocusable() && !accessibilityNodeInfoCompat.isFocused()) {
            long uptimeMillis = SystemClock.uptimeMillis();
            boolean $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, 1));
            LogUtils.d("FocusManagerInternal", "Perform input focus action:result=%s\n eventId=%s, Node=%s", Boolean.valueOf($default$returnFeedback), eventId, accessibilityNodeInfoCompat);
            if ($default$returnFeedback) {
                ActorStateWritable actorStateWritable = this.actorState;
                actorStateWritable.lastWindowId = accessibilityNodeInfoCompat.getWindowId();
                actorStateWritable.lastWindowIdUptimeMs = uptimeMillis;
                actorStateWritable.inputFocusActionRecord$ar$class_merging = new NodeActionPerformer.NodeActionRecord(accessibilityNodeInfoCompat, uptimeMillis);
            }
        }
        long uptimeMillis2 = SystemClock.uptimeMillis();
        boolean $default$returnFeedback2 = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, 64));
        if ($default$returnFeedback2) {
            focusActionInfo2 = updateFocusActionInfoIfNecessary(focusActionInfo, accessibilityNodeInfoCompat);
            this.history.onAccessibilityFocusAction(accessibilityNodeInfoCompat, focusActionInfo2, uptimeMillis2, this.screenState$ar$class_merging$ar$class_merging$ar$class_merging.getStableScreenState());
        } else {
            focusActionInfo2 = focusActionInfo;
        }
        LogUtils.d("FocusManagerInternal", "Set accessibility focus:result=%s\nNode:%s\nFocusActionInfo:%s", Boolean.valueOf($default$returnFeedback2), accessibilityNodeInfoCompat, focusActionInfo2);
        return $default$returnFeedback2;
    }

    public final FocusActionInfo updateFocusActionInfoIfNecessary(FocusActionInfo focusActionInfo, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        CursorGranularity cursorGranularity;
        NavigationAction navigationAction = focusActionInfo.navigationAction;
        if (navigationAction != null && (cursorGranularity = navigationAction.originalNavigationGranularity) != null && cursorGranularity.isMicroGranularity()) {
            ArrayList arrayList = new ArrayList();
            CursorGranularity.extractFromMask(CursorGranularityManager.extractNavigableNodes(accessibilityNodeInfoCompat, null, new HashSet()), WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat), WebInterfaceUtils.getSupportedHtmlElements(accessibilityNodeInfoCompat), arrayList);
            if (arrayList.contains(cursorGranularity)) {
                LogUtils.d("FocusManagerInternal", "Mute node feedback for micro granularity navigation.", new Object[0]);
                FocusActionInfo.Builder builder = new FocusActionInfo.Builder(focusActionInfo);
                builder.forceMuteFeedback = true;
                focusActionInfo = new FocusActionInfo(builder);
            }
        }
        if (this.muteNextFocus) {
            this.muteNextFocus = false;
            LogUtils.d("FocusManagerInternal", "FocusActionInfo modified.", new Object[0]);
            FocusActionInfo.Builder builder2 = new FocusActionInfo.Builder(focusActionInfo);
            builder2.forceMuteFeedback = true;
            return new FocusActionInfo(builder2);
        }
        return focusActionInfo;
    }
}
