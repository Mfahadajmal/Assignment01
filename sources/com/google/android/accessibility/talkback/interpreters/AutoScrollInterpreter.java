package com.google.android.accessibility.talkback.interpreters;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.accessibility.AccessibilityEvent;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$ID;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay;
import com.google.android.accessibility.talkback.actor.search.UniversalSearchActor;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.input.ScrollEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.ScrollActionRecord;
import com.google.mlkit.common.sdkinternal.TaskQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoScrollInterpreter implements ScrollEventInterpreter.ScrollEventHandler {
    public ActorState actorState;
    public DirectionNavigationActor directionNavigationActor;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public UniversalSearchActor universalSearchActor;
    public long handledAutoScrollUptimeMs = 0;
    public final AutoScrollHandler autoScrollHandler = new AutoScrollHandler(this);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AutoScrollHandler extends WeakReferenceHandler {
        public int scrollDeltaSumX;
        public int scrollDeltaSumY;

        public AutoScrollHandler(AutoScrollInterpreter autoScrollInterpreter) {
            super(autoScrollInterpreter, Looper.myLooper());
            this.scrollDeltaSumX = 0;
            this.scrollDeltaSumY = 0;
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            AccessibilityNode accessibilityNode;
            SearchScreenOverlay searchScreenOverlay;
            SearchScreenOverlay.AnonymousClass5 anonymousClass5;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
            AutoScrollInterpreter autoScrollInterpreter = (AutoScrollInterpreter) obj;
            if (message.what == 0) {
                Performance.EventId eventId = (Performance.EventId) message.obj;
                int i = message.arg1;
                int i2 = message.arg2;
                ScrollActionRecord unhandledAutoScrollRecord = autoScrollInterpreter.getUnhandledAutoScrollRecord();
                if (unhandledAutoScrollRecord != null) {
                    autoScrollInterpreter.autoScrollHandler.removeHandleAutoScrollSuccessMessages();
                    autoScrollInterpreter.handledAutoScrollUptimeMs = unhandledAutoScrollRecord.autoScrolledTime;
                    unhandledAutoScrollRecord.refresh();
                    String str = unhandledAutoScrollRecord.scrollSource;
                    if (str == "FOCUS" && (accessibilityNodeInfoCompat = unhandledAutoScrollRecord.scrolledNodeCompat) != null) {
                        FocusProcessorForLogicalNavigation focusProcessorForLogicalNavigation = (FocusProcessorForLogicalNavigation) autoScrollInterpreter.directionNavigationActor.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation;
                        if (focusProcessorForLogicalNavigation.scrollCallback$ar$class_merging$f9bb3c40_0 != null) {
                            AccessibilityNodeInfoCompat accessibilityFocus = focusProcessorForLogicalNavigation.accessibilityFocusMonitor.getAccessibilityFocus(false);
                            TaskQueue taskQueue = focusProcessorForLogicalNavigation.scrollCallback$ar$class_merging$f9bb3c40_0;
                            focusProcessorForLogicalNavigation.scrollCallback$ar$class_merging$f9bb3c40_0 = null;
                            if (accessibilityFocus == null || accessibilityFocus.equals(taskQueue.lock)) {
                                taskQueue.onAutoScrolled(accessibilityNodeInfoCompat, eventId, i, i2);
                            }
                        }
                    } else if (str == "SEARCH" && (accessibilityNode = unhandledAutoScrollRecord.scrolledNode) != null && (anonymousClass5 = (searchScreenOverlay = autoScrollInterpreter.universalSearchActor.searchScreenOverlay).scrollCallback$ar$class_merging) != null) {
                        if (anonymousClass5.val$needToUpdateFocus) {
                            new Handler().postDelayed(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(anonymousClass5.this$0, accessibilityNode, anonymousClass5.val$action, 4), 400L);
                        } else {
                            new Handler().postDelayed(new VoiceActionMonitor$$ExternalSyntheticLambda0(anonymousClass5.this$0, 10), 400L);
                        }
                        searchScreenOverlay.scrollCallback$ar$class_merging = null;
                    }
                }
                this.scrollDeltaSumX = 0;
                this.scrollDeltaSumY = 0;
            }
        }

        public final void removeHandleAutoScrollSuccessMessages() {
            removeMessages(0);
        }
    }

    public final ScrollActionRecord getUnhandledAutoScrollRecord() {
        ScrollActionRecord scrollActionRecord = this.actorState.getScrollerState().get();
        if (scrollActionRecord != null) {
            if (scrollActionRecord.autoScrolledTime > this.handledAutoScrollUptimeMs) {
                return scrollActionRecord;
            }
            return null;
        }
        return null;
    }

    @Override // com.google.android.accessibility.utils.input.ScrollEventInterpreter.ScrollEventHandler
    public final void onScrollEvent(AccessibilityEvent accessibilityEvent, ScrollEventInterpreter.ScrollEventInterpretation scrollEventInterpretation, Performance.EventId eventId) {
        int i = -1;
        if (scrollEventInterpretation.scrollInstanceId != -1) {
            ScrollActionRecord unhandledAutoScrollRecord = getUnhandledAutoScrollRecord();
            if (unhandledAutoScrollRecord != null) {
                i = unhandledAutoScrollRecord.scrollInstanceId;
            }
            if (i == scrollEventInterpretation.scrollInstanceId && accessibilityEvent.getEventType() == 4096) {
                this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$ID(Interpretation$ID.Value.SCROLL_CANCEL_TIMEOUT), null);
                this.autoScrollHandler.removeHandleAutoScrollSuccessMessages();
                AutoScrollHandler autoScrollHandler = this.autoScrollHandler;
                int scrollDeltaX = SpannableUtils$IdentifierSpan.getScrollDeltaX(accessibilityEvent);
                int scrollDeltaY = SpannableUtils$IdentifierSpan.getScrollDeltaY(accessibilityEvent);
                int i2 = autoScrollHandler.scrollDeltaSumX + scrollDeltaX;
                autoScrollHandler.scrollDeltaSumX = i2;
                int i3 = autoScrollHandler.scrollDeltaSumY + scrollDeltaY;
                autoScrollHandler.scrollDeltaSumY = i3;
                autoScrollHandler.sendMessageDelayed(autoScrollHandler.obtainMessage(0, i2, i3, eventId), 110L);
            }
        }
    }
}
