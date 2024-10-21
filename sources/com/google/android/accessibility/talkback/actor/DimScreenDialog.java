package com.google.android.accessibility.talkback.actor;

import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.dialog.FirstTimeUseDialog;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DimScreenDialog extends FirstTimeUseDialog {
    public final DimScreenActor dimScreenController;

    public DimScreenDialog(TalkBackService talkBackService, DimScreenActor dimScreenActor) {
        super(talkBackService, R.string.pref_show_dim_screen_confirmation_dialog, R.string.dialog_title_dim_screen, R.string.dialog_message_dim_screen, R.string.always_show_warning_checkbox);
        this.dimScreenController = dimScreenActor;
    }

    @Override // com.google.android.accessibility.talkback.dialog.FirstTimeUseDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogClick(int i) {
        super.handleDialogClick(i);
        if (i == -1 && TalkBackService.isServiceActive()) {
            this.dimScreenController.makeScreenDim();
            setSharedPreferencesByKey$ar$ds();
        }
    }
}
