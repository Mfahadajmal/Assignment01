package com.google.android.gms.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SupportErrorDialogFragment extends DialogFragment {
    public DialogInterface.OnCancelListener cancelListener;
    public Dialog dialog;
    private Dialog emptyDialog;

    @Override // android.support.v4.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.cancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // android.support.v4.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.dialog;
        if (dialog == null) {
            setShowsDialog(false);
            if (this.emptyDialog == null) {
                Context context = getContext();
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(context);
                this.emptyDialog = new AlertDialog.Builder(context).create();
            }
            return this.emptyDialog;
        }
        return dialog;
    }
}
