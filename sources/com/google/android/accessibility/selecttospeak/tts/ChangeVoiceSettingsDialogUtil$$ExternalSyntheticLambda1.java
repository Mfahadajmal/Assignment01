package com.google.android.accessibility.selecttospeak.tts;

import android.content.DialogInterface;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda1 implements DialogInterface.OnCancelListener {
    private final /* synthetic */ int switching_field;

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        if (this.switching_field != 0) {
            Job job = ChangeVoiceSettingsDialogUtil.dialogJob;
            if (job != null) {
                job.cancel(null);
            }
            ChangeVoiceSettingsDialogUtil.dialogJob = null;
            return;
        }
        Job job2 = ChangeVoiceSettingsDialogUtil.dialogJob;
        if (job2 != null) {
            job2.cancel(null);
        }
        ChangeVoiceSettingsDialogUtil.dialogJob = null;
    }
}
