package com.google.android.accessibility.talkback.actor;

import android.os.SystemClock;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.DelayHandler;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.Supplier;
import com.google.android.accessibility.utils.output.ScrollActionRecord;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoScrollActor {
    public Pipeline.FeedbackReturner feedbackReturner;
    public FloatingActionButton.ShadowDelegateImpl pipelineReceiver$ar$class_merging$ar$class_merging$ar$class_merging;
    public final StateReader stateReader = new StateReader();
    private int nextScrollInstanceId = 0;
    public ScrollActionRecord scrollActionRecord = null;
    public ScrollActionRecord failedScrollActionRecord = null;
    private final DelayHandler postDelayHandler = new DelayHandler() { // from class: com.google.android.accessibility.talkback.actor.AutoScrollActor.1
        @Override // com.google.android.accessibility.utils.DelayHandler
        public final /* bridge */ /* synthetic */ void handle(Object obj) {
            AutoScrollActor autoScrollActor = AutoScrollActor.this;
            ScrollActionRecord scrollActionRecord = autoScrollActor.scrollActionRecord;
            if (scrollActionRecord == null) {
                return;
            }
            autoScrollActor.failedScrollActionRecord = scrollActionRecord;
            autoScrollActor.scrollActionRecord = null;
            Object obj2 = autoScrollActor.pipelineReceiver$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            Logger logger = Performance.DEFAULT_LOGGER;
            Pipeline.m94$$Nest$minputEvent$ar$ds((Pipeline) obj2, new Pipeline.SyntheticEvent(1));
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StateReader implements Supplier {
        public StateReader() {
        }

        @Override // com.google.android.accessibility.utils.Supplier
        public final ScrollActionRecord get() {
            return AutoScrollActor.this.scrollActionRecord;
        }

        @Override // com.google.android.accessibility.utils.Supplier
        public final /* bridge */ /* synthetic */ Object get() {
            throw null;
        }
    }

    private final void setScrollRecord$ar$edu(int i, AccessibilityNode accessibilityNode, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str, long j, int i2, int i3) {
        int i4;
        ScrollActionRecord scrollActionRecord;
        if (i3 > 0 && (scrollActionRecord = this.scrollActionRecord) != null) {
            Integer valueOf = Integer.valueOf(i3);
            i4 = scrollActionRecord.scrollInstanceId;
            LogUtils.i("AutoScrollActor", "autoScrollAttempt=%d > 0 so keep scrollActionRecord=%d the same.", valueOf, Integer.valueOf(i4));
        } else {
            i4 = this.nextScrollInstanceId;
            int i5 = i4 + 1;
            this.nextScrollInstanceId = i5;
            if (i5 < 0) {
                this.nextScrollInstanceId = 0;
            }
            LogUtils.i("AutoScrollActor", "new AutoScrollRecord with scrollActionRecord=%d", Integer.valueOf(i4));
        }
        ScrollActionRecord scrollActionRecord2 = new ScrollActionRecord(i4, accessibilityNode, accessibilityNodeInfoCompat, i, j, str);
        this.failedScrollActionRecord = null;
        this.scrollActionRecord = scrollActionRecord2;
        this.postDelayHandler.removeMessages();
        this.postDelayHandler.delay(i2, new WindowTrackerFactory((Object) false, (Performance.EventId) null));
    }

    public final void cancelTimeout() {
        this.postDelayHandler.removeMessages();
    }

    public final boolean ensureOnScreen$ar$edu(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2, String str, int i2, Performance.EventId eventId) {
        if (accessibilityNodeInfoCompat2 == null || accessibilityNodeInfoCompat == null) {
            return false;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        boolean $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.feedbackReturner, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat2, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN.getId()));
        if ($default$returnFeedback) {
            setScrollRecord$ar$edu(i, null, accessibilityNodeInfoCompat, str, uptimeMillis, i2, 0);
        }
        LogUtils.d("AutoScrollActor", "Perform ACTION_SHOW_ON_SCREEN:result=%s\nnodeCompat=%s\nactionNodeCompat=%s\nUserAction=%s", Boolean.valueOf($default$returnFeedback), accessibilityNodeInfoCompat, accessibilityNodeInfoCompat2, ScrollActionRecord.userActionToString(i));
        return $default$returnFeedback;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0031, code lost:
    
        if (com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r1, r23, r2) == false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean scroll$ar$edu(int r16, com.google.android.accessibility.utils.AccessibilityNode r17, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r18, int r19, java.lang.String r20, int r21, int r22, com.google.android.accessibility.utils.Performance.EventId r23) {
        /*
            r15 = this;
            r9 = r15
            r10 = r17
            r0 = r23
            r11 = 0
            if (r10 != 0) goto Lc
            if (r18 == 0) goto Lb
            goto Lc
        Lb:
            return r11
        Lc:
            long r5 = android.os.SystemClock.uptimeMillis()
            r12 = 1
            if (r10 == 0) goto L36
            com.google.android.accessibility.talkback.Pipeline$FeedbackReturner r1 = r9.feedbackReturner
            com.google.android.accessibility.talkback.Feedback$Part$Builder r2 = com.google.android.accessibility.talkback.Feedback.Part.builder()
            com.google.android.accessibility.talkback.Interpretation$ManualScroll$Builder r3 = new com.google.android.accessibility.talkback.Interpretation$ManualScroll$Builder
            r4 = 0
            r3.<init>(r4, r4)
            r3.setTarget$ar$ds(r10)
            r13 = r19
            r3.setActionId$ar$ds(r13)
            com.google.android.accessibility.talkback.Feedback$NodeAction r3 = r3.build()
            r2.nodeAction = r3
            boolean r1 = com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r1, r0, r2)
            if (r1 != 0) goto L34
            goto L38
        L34:
            r14 = r12
            goto L48
        L36:
            r13 = r19
        L38:
            if (r18 == 0) goto L47
            com.google.android.accessibility.talkback.Pipeline$FeedbackReturner r1 = r9.feedbackReturner
            com.google.android.accessibility.talkback.Feedback$Part$Builder r2 = com.google.android.accessibility.talkback.Feedback.nodeAction(r18, r19)
            boolean r0 = com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r1, r0, r2)
            if (r0 == 0) goto L47
            goto L34
        L47:
            r14 = r11
        L48:
            if (r14 == 0) goto L5a
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r20
            r7 = r21
            r8 = r22
            r0.setScrollRecord$ar$edu(r1, r2, r3, r4, r5, r7, r8)
        L5a:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r14)
            java.lang.String r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.actionToString(r19)
            java.lang.String r2 = com.google.android.accessibility.utils.output.ScrollActionRecord.userActionToString(r16)
            r3 = 5
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r11] = r0
            r3[r12] = r10
            r0 = 2
            r3[r0] = r18
            r0 = 3
            r3[r0] = r1
            r0 = 4
            r3[r0] = r2
            java.lang.String r0 = "AutoScrollActor"
            java.lang.String r1 = "Perform scroll action:result=%s\nnode=%s\nnodeCompat=%s\nScrollAction=%s\nUserAction=%s"
            com.google.android.libraries.accessibility.utils.log.LogUtils.d(r0, r1, r3)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.AutoScrollActor.scroll$ar$edu(int, com.google.android.accessibility.utils.AccessibilityNode, androidx.core.view.accessibility.AccessibilityNodeInfoCompat, int, java.lang.String, int, int, com.google.android.accessibility.utils.Performance$EventId):boolean");
    }
}
