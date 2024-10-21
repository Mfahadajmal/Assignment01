package com.google.android.accessibility.talkback.actor.search;

import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.RingerModeAndScreenMonitor;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UniversalSearchManager implements WindowEventInterpreter.WindowEventHandler {
    private final Pipeline.FeedbackReturner pipeline;

    public UniversalSearchManager(final Pipeline.FeedbackReturner feedbackReturner, RingerModeAndScreenMonitor ringerModeAndScreenMonitor, WindowEventInterpreter windowEventInterpreter) {
        this.pipeline = feedbackReturner;
        if (ringerModeAndScreenMonitor != null) {
            ringerModeAndScreenMonitor.addScreenChangedListener(new RingerModeAndScreenMonitor.ScreenChangedListener() { // from class: com.google.android.accessibility.talkback.actor.search.UniversalSearchManager$$ExternalSyntheticLambda0
                @Override // com.google.android.accessibility.talkback.RingerModeAndScreenMonitor.ScreenChangedListener
                public final void onScreenChanged$ar$ds(boolean z) {
                    if (!z) {
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(Pipeline.FeedbackReturner.this, (Performance.EventId) null, Feedback.universalSearch$ar$edu(2));
                    }
                }
            });
        }
        if (windowEventInterpreter != null) {
            windowEventInterpreter.addListener(this);
        }
    }

    @Override // com.google.android.accessibility.utils.input.WindowEventInterpreter.WindowEventHandler
    public final void handle(WindowEventInterpreter.EventInterpretation eventInterpretation, Performance.EventId eventId) {
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.universalSearch$ar$edu(3));
    }
}
