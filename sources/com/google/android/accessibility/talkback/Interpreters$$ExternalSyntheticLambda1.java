package com.google.android.accessibility.talkback;

import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.gemini.GeminiActor;
import com.google.android.accessibility.talkback.logging.EventLatencyLogger;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Consumer;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.TreeDebug;
import com.google.android.accessibility.utils.input.HeadsUpNotificationEventInterpreter;
import com.google.android.accessibility.utils.input.SelectionEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.traversal.OrderedTraversalController;
import com.google.android.accessibility.utils.traversal.OrderedTraversalStrategy;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.common.util.concurrent.ExecutionList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class Interpreters$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ Object Interpreters$$ExternalSyntheticLambda1$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ Interpreters$$ExternalSyntheticLambda1(Object obj, int i) {
        this.switching_field = i;
        this.Interpreters$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v24, types: [java.lang.Object, com.google.android.accessibility.utils.Logger] */
    @Override // com.google.android.accessibility.utils.Consumer
    public final void accept(Object obj) {
        ExecutionList.RunnableExecutorPair runnableExecutorPair;
        Object obj2;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                List<AccessibilityWindowInfo> list = (List) obj;
                                ?? r0 = this.Interpreters$$ExternalSyntheticLambda1$ar$f$0;
                                TreeDebug.logNodeTrees(list, r0);
                                if (list != null && !list.isEmpty()) {
                                    r0.log("------------Node tree traversal order---------- display %d", Integer.valueOf(SpannableUtils$IdentifierSpan.getDisplayId((AccessibilityWindowInfo) list.get(0))));
                                    for (AccessibilityWindowInfo accessibilityWindowInfo : list) {
                                        if (accessibilityWindowInfo != null) {
                                            r0.log("Window: %s", accessibilityWindowInfo);
                                            AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(SpannableUtils$IdentifierSpan.getRoot(accessibilityWindowInfo));
                                            if (compat != null) {
                                                OrderedTraversalController orderedTraversalController = new OrderedTraversalStrategy(compat).controller;
                                                Object obj3 = orderedTraversalController.tree$ar$class_merging;
                                                if (obj3 == null) {
                                                    obj2 = null;
                                                } else {
                                                    while (true) {
                                                        runnableExecutorPair = (ExecutionList.RunnableExecutorPair) obj3;
                                                        Object obj4 = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next;
                                                        if (obj4 == null) {
                                                            break;
                                                        } else {
                                                            obj3 = obj4;
                                                        }
                                                    }
                                                    obj2 = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable;
                                                }
                                                while (obj2 != null) {
                                                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj2;
                                                    Integer valueOf = Integer.valueOf(accessibilityNodeInfoCompat.hashCode());
                                                    CharSequence nodeDebugDescription = TreeDebug.nodeDebugDescription(accessibilityNodeInfoCompat);
                                                    StringBuilder sb = new StringBuilder();
                                                    AccessibilityNodeInfoCompat traversalBefore = accessibilityNodeInfoCompat.getTraversalBefore();
                                                    AccessibilityNodeInfoCompat traversalAfter = accessibilityNodeInfoCompat.getTraversalAfter();
                                                    if (traversalBefore != null) {
                                                        sb.append(" before:");
                                                        sb.append(traversalBefore.hashCode());
                                                    }
                                                    if (traversalAfter != null) {
                                                        sb.append(" after:");
                                                        sb.append(traversalAfter.hashCode());
                                                    }
                                                    r0.log(" (%d)%s%s", valueOf, nodeDebugDescription, sb.toString());
                                                    obj2 = orderedTraversalController.findNext(accessibilityNodeInfoCompat);
                                                }
                                            }
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            Logger logger = Performance.DEFAULT_LOGGER;
                            ((EventLatencyLogger) obj).onGestureRecognized((Performance.GestureEventData) this.Interpreters$$ExternalSyntheticLambda1$ar$f$0);
                            return;
                        }
                        Logger logger2 = Performance.DEFAULT_LOGGER;
                        ((EventLatencyLogger) obj).onFeedbackOutput((Performance.EventData) this.Interpreters$$ExternalSyntheticLambda1$ar$f$0);
                        return;
                    }
                    Logger logger3 = Performance.DEFAULT_LOGGER;
                    ((EventLatencyLogger) obj).onAccessibilityActionPerformed((Performance.EventData) this.Interpreters$$ExternalSyntheticLambda1$ar$f$0);
                    return;
                }
                Pipeline.FeedbackReturner feedbackReturner = ((GeminiActor) this.Interpreters$$ExternalSyntheticLambda1$ar$f$0).pipeline;
                Logger logger4 = Performance.DEFAULT_LOGGER;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, (Feedback.Part.Builder) ((ApplicationModule) obj).ApplicationModule$ar$application);
                return;
            }
            SelectionEventInterpreter.Interpretation interpretation = (SelectionEventInterpreter.Interpretation) obj;
            if (!interpretation.isTransitional) {
                ((HapticPatternParser$$ExternalSyntheticLambda1) ((Interpreters) this.Interpreters$$ExternalSyntheticLambda1$ar$f$0).pipelineInterpretations.get()).input$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(interpretation.eventId, interpretation.event, new Interpretation$CompositorID(4));
                return;
            }
            return;
        }
        HeadsUpNotificationEventInterpreter.Interpretation interpretation2 = (HeadsUpNotificationEventInterpreter.Interpretation) obj;
        Interpreters interpreters = (Interpreters) this.Interpreters$$ExternalSyntheticLambda1$ar$f$0;
        ((HapticPatternParser$$ExternalSyntheticLambda1) interpreters.pipelineInterpretations.get()).input$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(interpretation2.eventId, interpretation2.event, new Interpretation$HeadsUpNotificationChange(interpretation2.notificationGuess));
        if (interpretation2.isHeadsUpAppearance) {
            ((HapticPatternParser$$ExternalSyntheticLambda1) interpreters.pipelineInterpretations.get()).input$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(interpretation2.eventId, interpretation2.event, new Interpretation$CompositorID(1073741941));
        }
    }
}
