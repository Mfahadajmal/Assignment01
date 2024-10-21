package com.google.android.accessibility.talkback.actor.voicecommands;

import android.content.Context;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.dialog.FirstTimeUseDialog;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeechRecognitionDialog extends FirstTimeUseDialog {
    private final Pipeline.FeedbackReturner pipeline;

    public SpeechRecognitionDialog(Context context, Pipeline.FeedbackReturner feedbackReturner) {
        super(context, R.string.pref_show_voice_command_dialog, R.string.dialog_title_voice_commands, R.string.dialog_message_voice_commands, R.string.always_show_this_message_label);
        this.pipeline = feedbackReturner;
        this.dialogMainMessage = context.getString(R.string.dialog_message_voice_commands, context.getString(R.string.title_pref_help));
        setPositiveButtonStringRes(R.string.start_voice_command);
    }

    @Override // com.google.android.accessibility.talkback.dialog.FirstTimeUseDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogClick(int i) {
        super.handleDialogClick(i);
        if (i == -1) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.voiceRecognition$ar$edu$6decc7d7_0(1, false));
        }
    }
}
