package com.google.android.accessibility.talkback.actor;

import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.dialog.FirstTimeUseDialog;
import com.google.android.accessibility.talkback.eventprocessor.EventState;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FullScreenReadDialog extends FirstTimeUseDialog {
    public Pipeline.FeedbackReturner pipeline;
    public boolean waitingForContentFocus;

    public FullScreenReadDialog(TalkBackService talkBackService) {
        super(talkBackService, R.string.pref_show_continuous_reading_mode_dialog, R.string.dialog_title_continuous_reading_mode, R.string.dialog_message_continuous_reading_mode, R.string.always_show_this_message_label);
        this.waitingForContentFocus = false;
        setIncludeNegativeButton(false);
    }

    @Override // com.google.android.accessibility.talkback.dialog.FirstTimeUseDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogDismiss() {
        EventState.instance.setFlag(4);
        EventState.instance.setFlag(5);
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Logger logger = Performance.DEFAULT_LOGGER;
        feedbackReturner.returnFeedback$ar$ds(null, Feedback.focus(Feedback.Focus.Action.MUTE_NEXT_FOCUS));
        this.waitingForContentFocus = true;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void setPipeline(Pipeline.FeedbackReturner feedbackReturner) {
        this.pipeline = feedbackReturner;
    }

    public final void showDialogBeforeReading(Performance.EventId eventId) {
        this.pipeline.returnFeedback$ar$ds$1fdc2aa8_0(eventId, Feedback.continuousRead$ar$edu(4));
        showDialog();
    }
}
