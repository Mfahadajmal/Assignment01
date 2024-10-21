package com.google.android.accessibility.brailleime;

import android.content.Context;
import android.os.Handler;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.RetryingNameResolver;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EscapeReminder {
    public final RetryingNameResolver.ResolutionResultListener callback$ar$class_merging$1705af2a_0$ar$class_merging$ar$class_merging;
    public final Context context;
    private int exitKeyboardCounter;
    public int optionsDialogCounter;
    public int reminderCount;
    public final AtomicBoolean finishSpeaking = new AtomicBoolean(true);
    public final SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = new SelectToSpeakJob.AnonymousClass1(this, 1);
    private final Runnable leaveReminder = new WorkerKt$$ExternalSyntheticLambda0(this, 17, null);
    private final Handler handler = new Handler();

    public EscapeReminder(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.context = context;
        this.callback$ar$class_merging$1705af2a_0$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.optionsDialogCounter = BrailleUserPreferences.getSharedPreferences$ar$ds(context).getInt(context.getString(R.string.pref_brailleime_show_option_dialog_count), 0);
        this.exitKeyboardCounter = BrailleUserPreferences.getSharedPreferences$ar$ds(context).getInt(context.getString(R.string.pref_brailleime_exit_keyboard_count), 0);
    }

    public final void cancelTimer() {
        this.handler.removeCallbacksAndMessages(null);
    }

    public final void increaseExitKeyboardCounter() {
        int i = this.exitKeyboardCounter + 1;
        this.exitKeyboardCounter = i;
        if (this.optionsDialogCounter <= 5) {
            Context context = this.context;
            BrailleUserPreferences.getSharedPreferences$ar$ds(context).edit().putInt(context.getString(R.string.pref_brailleime_exit_keyboard_count), i).apply();
        }
    }

    public final void restartTimer() {
        cancelTimer();
        startTimer();
    }

    public final void startTimer() {
        boolean hasCallbacks;
        hasCallbacks = this.handler.hasCallbacks(this.leaveReminder);
        if (!hasCallbacks && this.finishSpeaking.get()) {
            int i = this.reminderCount;
            if (i != 0) {
                if (i < 3) {
                    if (this.optionsDialogCounter >= 3 && this.exitKeyboardCounter >= 5) {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.handler.postDelayed(this.leaveReminder, i * 15000);
        }
    }
}
