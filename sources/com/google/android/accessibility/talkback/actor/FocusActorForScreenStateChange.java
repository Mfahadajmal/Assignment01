package com.google.android.accessibility.talkback.actor;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.talkback.monitor.InputMethodMonitor;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.traversal.TraversalStrategy;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.timer.TimerEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusActorForScreenStateChange {
    protected static final FocusActionInfo.Builder FOCUS_ACTION_INFO_FIRST_FOCUSABLE_NODE_BUILDER;
    protected static final FocusActionInfo.Builder FOCUS_ACTION_INFO_REQUEST_INITIAL_NODE_BUILDER;
    protected static final FocusActionInfo.Builder FOCUS_ACTION_INFO_RESTORED_BUILDER;
    protected static final FocusActionInfo.Builder FOCUS_ACTION_INFO_SYNCED_INPUT_FOCUS_BUILDER;
    public ActorState actorState;
    public final AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
    private final InputMethodMonitor inputMethodMonitor;
    public Pipeline.FeedbackReturner pipeline;
    private final PrimesController primesController;
    public final AccessibilityService service;

    static {
        FocusActionInfo.Builder builder = new FocusActionInfo.Builder();
        builder.sourceAction = 5;
        builder.initialFocusType = 2;
        FOCUS_ACTION_INFO_RESTORED_BUILDER = builder;
        FocusActionInfo.Builder builder2 = new FocusActionInfo.Builder();
        builder2.sourceAction = 5;
        builder2.initialFocusType = 3;
        FOCUS_ACTION_INFO_SYNCED_INPUT_FOCUS_BUILDER = builder2;
        FocusActionInfo.Builder builder3 = new FocusActionInfo.Builder();
        builder3.sourceAction = 5;
        builder3.initialFocusType = 1;
        FOCUS_ACTION_INFO_FIRST_FOCUSABLE_NODE_BUILDER = builder3;
        FocusActionInfo.Builder builder4 = new FocusActionInfo.Builder();
        builder4.sourceAction = 5;
        builder4.initialFocusType = 4;
        FOCUS_ACTION_INFO_REQUEST_INITIAL_NODE_BUILDER = builder4;
    }

    public FocusActorForScreenStateChange(AccessibilityService accessibilityService, InputMethodMonitor inputMethodMonitor, AppLifecycleMonitor appLifecycleMonitor, PrimesController primesController) {
        this.primesController = primesController;
        this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.service = accessibilityService;
        this.inputMethodMonitor = inputMethodMonitor;
    }

    public final boolean focusOnRequestInitialNodeOrFirstFocusableNonTitleNode(ScreenState screenState, Performance.EventId eventId) {
        AccessibilityNodeInfoCompat rootCompat;
        AccessibilityWindowInfo accessibilityWindowInfo = screenState.activeWindow;
        boolean z = false;
        if (accessibilityWindowInfo == null || (rootCompat = SpannableUtils$IdentifierSpan.getRootCompat(accessibilityWindowInfo)) == null) {
            return false;
        }
        long time = TimerEvent.getTime();
        CharSequence windowTitle = screenState.getWindowTitle(accessibilityWindowInfo.getId());
        TraversalStrategy traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = TraversalStrategyUtils.getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(rootCompat, this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, 1);
        Filter createNodeFilter = SpannableUtils$IdentifierSpan.createNodeFilter(0, traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getSpeakingNodesCache());
        if (this.formFactorUtils.isAndroidWear) {
            createNodeFilter = AccessibilityNodeInfoUtils.getFilterExcludingSmallTopAndBottomBorderNode(this.service).and(createNodeFilter);
        }
        if (!TextUtils.isEmpty(windowTitle)) {
            final String charSequence = windowTitle.toString();
            createNodeFilter = new Filter(this) { // from class: com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange.1
                final /* synthetic */ FocusActorForScreenStateChange this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.android.accessibility.utils.Filter
                public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                    CharSequence nodeText = AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat);
                    if (TextUtils.isEmpty(nodeText)) {
                        nodeText = AccessibilityNodeInfoUtils.getNodeText(TraversalStrategyUtils.searchFocus(TraversalStrategyUtils.getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, this.this$0.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, 1), accessibilityNodeInfoCompat, 1, new Filter() { // from class: com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange.2
                            @Override // com.google.android.accessibility.utils.Filter
                            public final /* bridge */ /* synthetic */ boolean accept(Object obj2) {
                                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) obj2;
                                if (AccessibilityNodeInfoUtils.FILTER_NON_FOCUSABLE_VISIBLE_NODE.accept(accessibilityNodeInfoCompat2) && !TextUtils.isEmpty(AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat2))) {
                                    return true;
                                }
                                return false;
                            }
                        }));
                    }
                    if (nodeText != null) {
                        if (nodeText.toString().equalsIgnoreCase(charSequence) && !AccessibilityNodeInfoUtils.isOrHasMatchingAncestor(accessibilityNodeInfoCompat, new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.16
                            final /* synthetic */ Context val$context;

                            public AnonymousClass16(Context context) {
                                r1 = context;
                            }

                            @Override // com.google.android.accessibility.utils.Filter
                            public final /* bridge */ /* synthetic */ boolean accept(Object obj2) {
                                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) obj2;
                                if (AccessibilityNodeInfoUtils.isClickable(accessibilityNodeInfoCompat2) || AccessibilityNodeInfoUtils.isLongClickable(accessibilityNodeInfoCompat2)) {
                                    return true;
                                }
                                if (FeatureSupport.isWatch(r1)) {
                                    return false;
                                }
                                int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat2);
                                if (role == 8 || role == 5) {
                                    return true;
                                }
                                return false;
                            }
                        })) {
                            return false;
                        }
                    }
                    return true;
                }
            }.and(createNodeFilter);
        }
        AccessibilityNodeInfoCompat focusInitial = traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.focusInitial(rootCompat);
        boolean z2 = screenState.interpretFirstTimeWhenWakeUp;
        boolean shouldMuteFeedbackForFocusedNode$ar$ds = SpannableUtils$NonCopyableTextSpan.shouldMuteFeedbackForFocusedNode$ar$ds(screenState);
        FocusActionInfo.Builder builder = FOCUS_ACTION_INFO_REQUEST_INITIAL_NODE_BUILDER;
        builder.forceMuteFeedback = shouldMuteFeedbackForFocusedNode$ar$ds;
        FocusActionInfo focusActionInfo = new FocusActionInfo(builder);
        if (focusInitial == null || !AccessibilityNodeInfoUtils.shouldFocusNode(focusInitial)) {
            focusInitial = TraversalStrategyUtils.findFirstFocusInNodeTree(traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, rootCompat, 1, createNodeFilter);
            FocusActionInfo.Builder builder2 = FOCUS_ACTION_INFO_FIRST_FOCUSABLE_NODE_BUILDER;
            builder2.forceMuteFeedback = shouldMuteFeedbackForFocusedNode$ar$ds;
            focusActionInfo = new FocusActionInfo(builder2);
        }
        if (focusInitial != null && SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.focus(focusInitial, focusActionInfo))) {
            z = true;
        }
        if (z2 && z) {
            screenState.consumeInterpretFirstTimeWhenWakeUp();
        }
        if (z) {
            this.primesController.recordDuration(PrimesController.TimerAction.INITIAL_FOCUS_FIRST_CONTENT, time, TimerEvent.getTime());
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x005f, code lost:
    
        if (r3.intersect(r4) != false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean restoreLastFocusedNode(com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState r14, com.google.android.accessibility.utils.Performance.EventId r15) {
        /*
            r13 = this;
            com.google.android.accessibility.talkback.ActorState r0 = r13.actorState
            com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1 r0 = r0.getFocusHistory$ar$class_merging$ar$class_merging()
            android.accessibilityservice.AccessibilityService r1 = r13.service
            java.util.List r1 = r1.getWindows()
            java.util.Iterator r1 = r1.iterator()
            r2 = 0
            r3 = 0
            r5 = r2
            r6 = r5
        L15:
            android.view.accessibility.AccessibilityWindowInfo r7 = r14.activeWindow
            boolean r8 = r1.hasNext()
            if (r8 == 0) goto L68
            java.lang.Object r8 = r1.next()
            android.view.accessibility.AccessibilityWindowInfo r8 = (android.view.accessibility.AccessibilityWindowInfo) r8
            int r9 = r8.getId()
            com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory$WindowIdentifier r9 = com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory.WindowIdentifier.create(r9, r14)
            com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord r9 = r0.getLastFocusActionRecordInWindow(r9)
            if (r9 == 0) goto L15
            long r10 = r9.actionTime
            int r12 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r12 <= 0) goto L15
            int r3 = r8.getType()
            r4 = 2
            if (r3 != r4) goto L65
            if (r7 != 0) goto L41
            goto L62
        L41:
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r7.getBoundsInScreen(r3)
            r8.getBoundsInScreen(r4)
            int r5 = r7.getLayer()
            int r7 = r8.getLayer()
            if (r5 <= r7) goto L62
            boolean r3 = r3.intersect(r4)
            if (r3 == 0) goto L62
            goto L65
        L62:
            r5 = r8
            r6 = r9
            goto L66
        L65:
            r5 = r2
        L66:
            r3 = r10
            goto L15
        L68:
            if (r5 != 0) goto L6b
            r5 = r7
        L6b:
            r1 = 0
            if (r5 != 0) goto L6f
            return r1
        L6f:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r2 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getRootCompat(r5)
            if (r2 != 0) goto L76
            return r1
        L76:
            int r3 = r5.getId()
            int r4 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getType(r5)
            com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory$WindowIdentifier r3 = com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory.WindowIdentifier.create(r3, r14)
            r5 = 3
            if (r4 != r5) goto L8f
            java.lang.Object[] r14 = new java.lang.Object[r1]
            java.lang.String r15 = "FocusActorForScreen"
            java.lang.String r0 = "Do not restore focus in system ui window."
            com.google.android.libraries.accessibility.utils.log.LogUtils.d(r15, r0, r14)
            return r1
        L8f:
            if (r6 != 0) goto L95
            com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord r6 = r0.getLastFocusActionRecordInWindow(r3)
        L95:
            if (r6 != 0) goto L98
            return r1
        L98:
            com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor r0 = r13.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
            long r9 = com.google.android.libraries.performance.primes.metrics.timer.TimerEvent.getTime()
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r0 = com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord.getFocusableNodeFromFocusRecord$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(r2, r0, r6)
            boolean r3 = r14.interpretFirstTimeWhenWakeUp
            boolean r4 = com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.shouldMuteFeedbackForFocusedNode$ar$ds(r14)
            com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo$Builder r5 = com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange.FOCUS_ACTION_INFO_RESTORED_BUILDER
            r5.forceMuteFeedback = r4
            com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo r4 = new com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo
            r4.<init>(r5)
            if (r0 == 0) goto Ld4
            boolean r5 = r0.isVisibleToUser()
            if (r5 == 0) goto Ld4
            androidx.core.view.accessibility.AccessibilityWindowInfoCompat r2 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getWindow(r2)
            boolean r2 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isInWindow(r0, r2)
            if (r2 == 0) goto Ld4
            com.google.android.accessibility.talkback.Pipeline$FeedbackReturner r2 = r13.pipeline
            com.google.android.accessibility.talkback.Feedback$Focus$Builder r0 = com.google.android.accessibility.talkback.Feedback.focus(r0, r4)
            r4 = 1
            r0.setForceRefocus$ar$ds(r4)
            boolean r15 = com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r2, r15, r0)
            if (r15 == 0) goto Ld4
            r1 = r4
        Ld4:
            if (r3 == 0) goto Ldb
            if (r1 == 0) goto Ldb
            r14.consumeInterpretFirstTimeWhenWakeUp()
        Ldb:
            if (r1 == 0) goto Le8
            com.google.android.accessibility.talkback.PrimesController r7 = r13.primesController
            com.google.android.accessibility.talkback.PrimesController$TimerAction r8 = com.google.android.accessibility.talkback.PrimesController.TimerAction.INITIAL_FOCUS_RESTORE
            long r11 = com.google.android.libraries.performance.primes.metrics.timer.TimerEvent.getTime()
            r7.recordDuration(r8, r9, r11)
        Le8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange.restoreLastFocusedNode(com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState, com.google.android.accessibility.utils.Performance$EventId):boolean");
    }

    public final boolean syncAccessibilityFocusAndInputFocus(ScreenState screenState, Performance.EventId eventId) {
        String str;
        int i = 0;
        if (screenState.activeWindow == null) {
            return false;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = null;
        if (this.inputMethodMonitor.useInputWindowAsActiveWindow() && this.inputMethodMonitor.getRootInActiveInputWindow() != null) {
            AccessibilityNodeInfo rootInActiveInputWindow = this.inputMethodMonitor.getRootInActiveInputWindow();
            if (rootInActiveInputWindow != null) {
                accessibilityNodeInfoCompat = AccessibilityNodeInfoUtils.toCompat(rootInActiveInputWindow.findFocus(1));
            }
        } else {
            AccessibilityNodeInfoCompat findFocusCompat = this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.findFocusCompat(1);
            AccessibilityWindowInfo accessibilityWindowInfo = screenState.activeWindow;
            if (findFocusCompat != null && findFocusCompat.getWindowId() == accessibilityWindowInfo.getId() && (this.formFactorUtils.isAndroidTv || findFocusCompat.isEditable() || SpannableUtils$IdentifierSpan.getRole(findFocusCompat) == 4)) {
                if (!AccessibilityNodeInfoUtils.shouldFocusNode(findFocusCompat)) {
                    if (findFocusCompat.getChildCount() != 0) {
                        AccessibilityNodeInfoCompat matchingDescendant = AccessibilityNodeInfoUtils.getMatchingDescendant(findFocusCompat, new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(i)));
                        if (AccessibilityNodeInfoUtils.shouldFocusNode(matchingDescendant)) {
                            accessibilityNodeInfoCompat = matchingDescendant;
                        } else {
                            accessibilityNodeInfoCompat = AccessibilityNodeInfoUtils.getMatchingDescendant(findFocusCompat, AccessibilityNodeInfoUtils.FILTER_SHOULD_FOCUS);
                        }
                    }
                    Object[] objArr = new Object[0];
                    if (accessibilityNodeInfoCompat != null) {
                        str = "no suitable child has been found";
                    } else {
                        str = "has been replaced by a suitable child";
                    }
                    LogUtils.v("FocusActorForScreen", "Input-focused node is not suitable for accessibility-focus and ".concat(str), objArr);
                } else {
                    accessibilityNodeInfoCompat = findFocusCompat;
                }
            }
        }
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        long time = TimerEvent.getTime();
        boolean z = screenState.interpretFirstTimeWhenWakeUp;
        boolean shouldMuteFeedbackForFocusedNode$ar$ds = SpannableUtils$NonCopyableTextSpan.shouldMuteFeedbackForFocusedNode$ar$ds(screenState);
        FocusActionInfo.Builder builder = FOCUS_ACTION_INFO_SYNCED_INPUT_FOCUS_BUILDER;
        builder.forceMuteFeedback = shouldMuteFeedbackForFocusedNode$ar$ds;
        boolean $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.focus(accessibilityNodeInfoCompat, new FocusActionInfo(builder)));
        if (z && $default$returnFeedback) {
            screenState.consumeInterpretFirstTimeWhenWakeUp();
        }
        if ($default$returnFeedback) {
            this.primesController.recordDuration(PrimesController.TimerAction.INITIAL_FOCUS_FOLLOW_INPUT, time, TimerEvent.getTime());
        }
        return $default$returnFeedback;
    }
}
