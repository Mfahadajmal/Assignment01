package com.google.android.accessibility.talkback.actor;

import android.content.Context;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.dialog.FirstTimeUseDialog;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PassThroughModeDialog extends FirstTimeUseDialog {
    public Pipeline.FeedbackReturner pipeline;

    public PassThroughModeDialog(Context context) {
        super(context, R.string.pref_show_pass_through_mode_dialog, R.string.dialog_title_pass_through_mode, R.string.dialog_message_pass_through_mode, R.string.always_show_this_message_label);
    }

    @Override // com.google.android.accessibility.talkback.dialog.FirstTimeUseDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogClick(int i) {
        super.handleDialogClick(i);
        if (i == -1) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            feedbackReturner.returnFeedback$ar$ds$1fdc2aa8_0(null, Feedback.passThroughMode$ar$edu(2));
        }
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void setPipeline(Pipeline.FeedbackReturner feedbackReturner) {
        this.pipeline = feedbackReturner;
    }
}
