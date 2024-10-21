package com.google.android.accessibility.talkback.actor.gemini;

import android.content.Context;
import android.view.View;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeminiResultDialog extends BaseDialog {
    private final String message;

    public GeminiResultDialog(Context context, String str) {
        super(context, R.string.title_gemini_result_dialog, null);
        this.message = str;
        setIncludeNegativeButton(false);
        setPositiveButtonStringRes(R.string.positive_button_gemini_result_dialog);
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final View getCustomizedView() {
        return null;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final String getMessageString() {
        return this.message;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogDismiss() {
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogClick(int i) {
    }
}
