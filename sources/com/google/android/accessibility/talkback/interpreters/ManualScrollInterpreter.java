package com.google.android.accessibility.talkback.interpreters;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$ManualScroll;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord;
import com.google.android.accessibility.talkback.focusmanagement.record.NodeDescription;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.compat.CompatUtils;
import com.google.android.accessibility.utils.input.ScrollEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ManualScrollInterpreter implements ScrollEventInterpreter.ScrollEventHandler {
    public ActorState actorState;
    public AccessibilityFocusInterpreter listener$ar$class_merging$c6a3d175_0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ManualScrollInterpretation {
        public final int direction;
        public final AccessibilityEvent event;
        public final Performance.EventId eventId;

        public ManualScrollInterpretation() {
        }

        public final int direction() {
            return this.direction;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ManualScrollInterpretation) {
                ManualScrollInterpretation manualScrollInterpretation = (ManualScrollInterpretation) obj;
                Performance.EventId eventId = this.eventId;
                if (eventId != null ? eventId.equals(manualScrollInterpretation.eventId()) : manualScrollInterpretation.eventId() == null) {
                    if (this.event.equals(manualScrollInterpretation.event()) && this.direction == manualScrollInterpretation.direction()) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final AccessibilityEvent event() {
            return this.event;
        }

        public final Performance.EventId eventId() {
            return this.eventId;
        }

        public final int hashCode() {
            int hashCode;
            Performance.EventId eventId = this.eventId;
            if (eventId == null) {
                hashCode = 0;
            } else {
                hashCode = eventId.hashCode();
            }
            return ((((hashCode ^ 1000003) * 1000003) ^ this.event.hashCode()) * 1000003) ^ this.direction;
        }

        public final String toString() {
            AccessibilityEvent accessibilityEvent = this.event;
            return "ManualScrollInterpretation{eventId=" + String.valueOf(this.eventId) + ", event=" + accessibilityEvent.toString() + ", direction=" + this.direction + "}";
        }

        public ManualScrollInterpretation(Performance.EventId eventId, AccessibilityEvent accessibilityEvent, int i) {
            this();
            this.eventId = eventId;
            if (accessibilityEvent == null) {
                throw new NullPointerException("Null event");
            }
            this.event = accessibilityEvent;
            this.direction = i;
        }
    }

    @Override // com.google.android.accessibility.utils.input.ScrollEventInterpreter.ScrollEventHandler
    public final void onScrollEvent(AccessibilityEvent accessibilityEvent, ScrollEventInterpreter.ScrollEventInterpretation scrollEventInterpretation, Performance.EventId eventId) {
        AccessibilityNodeInfoCompat compat;
        NodePathDescription nodePathDescription;
        String str;
        if (scrollEventInterpretation.userAction == 3 && scrollEventInterpretation.scrollDirection != 0 && (compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource())) != null) {
            FocusActionRecord lastFocusActionRecord = ((AccessibilityFocusActionHistory) this.actorState.getFocusHistory$ar$class_merging$ar$class_merging().HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getLastFocusActionRecord();
            if (lastFocusActionRecord == null) {
                nodePathDescription = null;
            } else {
                nodePathDescription = lastFocusActionRecord.nodePathDescription;
            }
            if (nodePathDescription != null) {
                if (!SpannableUtils$IdentifierSpan.isAtLeastOMR1()) {
                    compat.refresh();
                }
                int hashCode = compat.hashCode();
                String str2 = (String) CompatUtils.invoke(compat.mInfo, null, CompatUtils.getMethod(AccessibilityNodeInfo.class, "getUniqueId", new Class[0]), new Object[0]);
                ArrayList arrayList = nodePathDescription.nodeDescriptions;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    NodeDescription nodeDescription = (NodeDescription) arrayList.get(i);
                    AccessibilityNode accessibilityNode = nodeDescription.savedNode;
                    if ((accessibilityNode != null && str2 != null && (str = (String) CompatUtils.invoke(accessibilityNode.obtainCopyCompat().mInfo, null, CompatUtils.getMethod(AccessibilityNodeInfo.class, "getUniqueId", new Class[0]), new Object[0])) != null && str.equals(str2)) || (nodeDescription.nodeInfoHashCode == hashCode && TextUtils.equals(compat.getClassName(), nodeDescription.className) && TextUtils.equals(compat.getViewIdResourceName(), nodeDescription.viewIdResourceName))) {
                        AccessibilityFocusInterpreter accessibilityFocusInterpreter = this.listener$ar$class_merging$c6a3d175_0;
                        ManualScrollInterpretation manualScrollInterpretation = new ManualScrollInterpretation(eventId, accessibilityEvent, scrollEventInterpretation.scrollDirection);
                        if (!accessibilityFocusInterpreter.screenState$ar$class_merging$ar$class_merging$ar$class_merging.areMainWindowsStable()) {
                            LogUtils.w("A11yFocusInterp", "onScrollEvent return due to windows are not stable and the focus will be handled by onScreenStateChanged after main windows are stable.", new Object[0]);
                            return;
                        }
                        AccessibilityNodeInfoCompat accessibilityFocus = accessibilityFocusInterpreter.accessibilityFocusMonitor.getAccessibilityFocus(false);
                        int i2 = manualScrollInterpretation.direction;
                        if (accessibilityFocusInterpreter.formFactorUtils.isAndroidWear) {
                            Context context = accessibilityFocusInterpreter.context;
                            if (accessibilityFocus != null) {
                                accessibilityFocus.getBoundsInScreen(new Rect());
                                Point screenPixelSizeWithoutWindowDecor = SpannableUtils$IdentifierSpan.getScreenPixelSizeWithoutWindowDecor(context);
                                if (i2 == 1) {
                                    if (r8.top >= screenPixelSizeWithoutWindowDecor.y * 0.15f) {
                                        return;
                                    }
                                } else if (i2 != 2 || r8.bottom <= screenPixelSizeWithoutWindowDecor.y * 0.85f) {
                                    return;
                                }
                                if (i2 == 1) {
                                    if (r8.bottom >= screenPixelSizeWithoutWindowDecor.y * 0.5f) {
                                        return;
                                    }
                                } else if (r8.top <= screenPixelSizeWithoutWindowDecor.y * 0.5f) {
                                    return;
                                }
                            }
                        } else if (AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityFocus)) {
                            return;
                        }
                        Interpretation$ManualScroll.Builder builder = new Interpretation$ManualScroll.Builder(null);
                        builder.direction = manualScrollInterpretation.direction;
                        builder.set$0 = (byte) 1;
                        builder.Interpretation$ManualScroll$Builder$ar$screenState = accessibilityFocusInterpreter.screenState$ar$class_merging$ar$class_merging$ar$class_merging.getStableScreenState();
                        if (accessibilityFocusInterpreter.formFactorUtils.isAndroidWear) {
                            builder.Interpretation$ManualScroll$Builder$ar$currentFocusedNode = accessibilityFocus;
                        }
                        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = accessibilityFocusInterpreter.pipelineInterpretations$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                        Performance.EventId eventId2 = manualScrollInterpretation.eventId;
                        AccessibilityEvent accessibilityEvent2 = manualScrollInterpretation.event;
                        if (builder.set$0 == 1) {
                            hapticPatternParser$$ExternalSyntheticLambda1.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId2, accessibilityEvent2, new Interpretation$ManualScroll((AccessibilityNodeInfoCompat) builder.Interpretation$ManualScroll$Builder$ar$currentFocusedNode, builder.direction, (ScreenState) builder.Interpretation$ManualScroll$Builder$ar$screenState), null);
                            return;
                        }
                        throw new IllegalStateException("Missing required properties: direction");
                    }
                }
            }
        }
    }
}
