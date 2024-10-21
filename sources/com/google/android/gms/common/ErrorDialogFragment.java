package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ErrorDialogFragment extends DialogFragment {
    public DialogInterface.OnCancelListener cancelListener;
    public Dialog dialog;
    private Dialog emptyDialog;

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.cancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // android.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.dialog;
        if (dialog == null) {
            setShowsDialog(false);
            if (this.emptyDialog == null) {
                Activity activity = getActivity();
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(activity);
                this.emptyDialog = new AlertDialog.Builder(activity).create();
            }
            return this.emptyDialog;
        }
        return dialog;
    }
}
