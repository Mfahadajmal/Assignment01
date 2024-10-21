package com.google.android.accessibility.talkback.interpreters;

import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenStateMonitor;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InputFocusInterpreter implements AccessibilityEventListener, ScreenStateMonitor.ScreenStateChangeListener {
    public ActorState actorState;
    private final AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final GlobalVariables globalVariables;
    private long lastFocusActionHandleUptimeMs = 0;
    private final AccessibilityFocusInterpreter targetViewChangeListener$ar$class_merging;

    public InputFocusInterpreter(AccessibilityFocusInterpreter accessibilityFocusInterpreter, AppLifecycleMonitor appLifecycleMonitor, GlobalVariables globalVariables) {
        this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.globalVariables = globalVariables;
        this.targetViewChangeListener$ar$class_merging = accessibilityFocusInterpreter;
    }

    private final boolean isLastFocusActionHandled() {
        if (this.actorState.getInputFocusActionRecord$ar$class_merging() != null && this.actorState.getInputFocusActionRecord$ar$class_merging().actionTime > this.lastFocusActionHandleUptimeMs) {
            return false;
        }
        return true;
    }

    private final void updateInputFocusedNodeInGlobalVariables(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat != null) {
            if (accessibilityNodeInfoCompat.isEditable() || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4) {
                this.globalVariables.mLastTextEditIsPassword = accessibilityNodeInfoCompat.isPassword();
            }
        }
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 12;
    }

    public final void initLastEditableFocusForGlobalVariables() {
        AccessibilityNodeInfoCompat findFocusCompat = this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.findFocusCompat(1);
        LogUtils.v("InputFocusInterpreter", "initLastEditableFocusForGlobalVariables() : currentInputFocus: %s", findFocusCompat);
        updateInputFocusedNodeInGlobalVariables(findFocusCompat);
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        int currentItemIndex;
        AccessibilityNodeInfoCompat child;
        AccessibilityNodeInfoCompat compat;
        int eventType = accessibilityEvent.getEventType();
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = null;
        if (eventType != 4) {
            if (eventType != 8) {
                if (eventType == 32768) {
                    initLastEditableFocusForGlobalVariables();
                    return;
                }
                return;
            }
            AccessibilityNodeInfoCompat compat2 = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
            if (compat2 != null) {
                updateInputFocusedNodeInGlobalVariables(compat2);
                if (!isLastFocusActionHandled() && (compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource())) != null) {
                    long eventTime = accessibilityEvent.getEventTime() - this.actorState.getInputFocusActionRecord$ar$class_merging().actionTime;
                    if (eventTime >= 0 && eventTime < 1000 && compat.equals(this.actorState.getInputFocusActionRecord$ar$class_merging().NodeActionPerformer$NodeActionRecord$ar$targetNode)) {
                        if (this.actorState.getInputFocusActionRecord$ar$class_merging() != null) {
                            this.lastFocusActionHandleUptimeMs = this.actorState.getInputFocusActionRecord$ar$class_merging().actionTime;
                            return;
                        }
                        return;
                    }
                }
                if (isLastFocusActionHandled() || accessibilityEvent.getEventTime() - this.actorState.getInputFocusActionRecord$ar$class_merging().actionTime >= 1000) {
                    AppLifecycleMonitor appLifecycleMonitor = this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    int role = SpannableUtils$IdentifierSpan.getRole(compat2);
                    if (role != 8 && role != 5) {
                        if (!AccessibilityNodeInfoUtils.shouldFocusNode(compat2)) {
                            if (appLifecycleMonitor.findFocusCompat(2) == null) {
                                accessibilityNodeInfoCompat = AccessibilityNodeInfoUtils.searchFromBfs(compat2, AccessibilityNodeInfoUtils.FILTER_SHOULD_FOCUS);
                            }
                        } else {
                            accessibilityNodeInfoCompat = compat2;
                        }
                    } else {
                        LogUtils.d("InputFocusInterpreter", "Ignore TYPE_VIEW_FOCUSED event from a collection.", new Object[0]);
                    }
                    if (accessibilityNodeInfoCompat != null) {
                        this.targetViewChangeListener$ar$class_merging.onViewTargeted(eventId, accessibilityEvent, accessibilityNodeInfoCompat);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        AccessibilityNodeInfoCompat compat3 = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
        if (compat3 != null && accessibilityEvent.getItemCount() > 0 && accessibilityEvent.getFromIndex() >= 0 && accessibilityEvent.getCurrentItemIndex() >= 0 && (currentItemIndex = accessibilityEvent.getCurrentItemIndex() - accessibilityEvent.getFromIndex()) >= 0 && currentItemIndex < compat3.getChildCount() && (child = compat3.getChild(currentItemIndex)) != null && AccessibilityNodeInfoUtils.isTopLevelScrollItem(child)) {
            accessibilityNodeInfoCompat = child;
        }
        if (accessibilityNodeInfoCompat != null && AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat)) {
            this.targetViewChangeListener$ar$class_merging.onViewTargeted(eventId, accessibilityEvent, accessibilityNodeInfoCompat);
        }
    }

    @Override // com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenStateMonitor.ScreenStateChangeListener
    public final void onScreenStateChanged$ar$ds(ScreenState screenState, Performance.EventId eventId) {
        initLastEditableFocusForGlobalVariables();
    }
}
