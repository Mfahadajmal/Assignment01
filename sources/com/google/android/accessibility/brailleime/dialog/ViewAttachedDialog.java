package com.google.android.accessibility.brailleime.dialog;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ViewAttachedDialog {
    private Dialog dialog;
    public View viewToAttach;

    public void dismiss() {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public final boolean isShowing() {
        Dialog dialog = this.dialog;
        if (dialog != null && dialog.isShowing()) {
            return true;
        }
        return false;
    }

    protected abstract Dialog makeDialog();

    public final void show(View view) {
        this.viewToAttach = view;
        Dialog makeDialog = makeDialog();
        this.dialog = makeDialog;
        WindowManager.LayoutParams attributes = makeDialog.getWindow().getAttributes();
        attributes.type = TrainingProto$TrainingPageId.PAGE_ID_ANSWER_CALLS$ar$edu;
        attributes.token = view.getWindowToken();
        makeDialog.getWindow().setAttributes(attributes);
        makeDialog.show();
    }
}
