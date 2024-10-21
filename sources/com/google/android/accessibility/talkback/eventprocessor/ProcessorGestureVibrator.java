package com.google.android.accessibility.talkback.eventprocessor;

import android.view.accessibility.AccessibilityEvent;
import androidx.core.provider.CallbackWithHandler$2;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessorGestureVibrator implements AccessibilityEventListener {
    private final Object ProcessorGestureVibrator$ar$feedbackReturner;
    private final /* synthetic */ int switching_field;

    public ProcessorGestureVibrator(Object obj, int i) {
        this.switching_field = i;
        this.ProcessorGestureVibrator$ar$feedbackReturner = obj;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        if (this.switching_field != 0) {
            return 32769;
        }
        return 786432;
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v3, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        int i;
        if (this.switching_field != 0) {
            int eventType = accessibilityEvent.getEventType();
            if (eventType == 1 || eventType == 32768) {
                Object obj = this.ProcessorGestureVibrator$ar$feedbackReturner;
                int eventType2 = accessibilityEvent.getEventType();
                TalkBackAnalyticsImpl talkBackAnalyticsImpl = (TalkBackAnalyticsImpl) obj;
                if (talkBackAnalyticsImpl.initialized) {
                    TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = talkBackAnalyticsImpl.talkBackAnalyticsLogger;
                    if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                        if (eventType2 != 1) {
                            if (eventType2 == 32768) {
                                i = 4;
                            } else {
                                return;
                            }
                        } else {
                            i = 3;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i, 14)).execute(new Void[0]);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        int eventType3 = accessibilityEvent.getEventType();
        if (eventType3 != 262144) {
            if (eventType3 != 524288) {
                return;
            }
            ?? r4 = this.ProcessorGestureVibrator$ar$feedbackReturner;
            Feedback.Part.Builder interrupt = Feedback.interrupt(1, 1);
            interrupt.setInterruptSoundAndVibration$ar$ds(true);
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r4, eventId, interrupt);
            return;
        }
        ?? r42 = this.ProcessorGestureVibrator$ar$feedbackReturner;
        Feedback.Part.Builder interrupt2 = Feedback.interrupt(1, 1);
        interrupt2.setDelayMs$ar$ds(70);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r42, eventId, interrupt2.vibration(R.array.gesture_detection_repeated_pattern).sound(R.raw.gesture_begin));
    }
}
