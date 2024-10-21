package com.google.android.accessibility.utils.material;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.Window;
import android.widget.Button;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.apps.common.inject.ApplicationModule;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11yAlertDialogWrapper implements DialogInterface {
    public final ApplicationModule dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging;

    public A11yAlertDialogWrapper(AlertDialog alertDialog) {
        this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging = new ApplicationModule(alertDialog);
    }

    public static void focusCancelButton(A11yAlertDialogWrapper a11yAlertDialogWrapper) {
        Button button = a11yAlertDialogWrapper.getButton(-2);
        button.setFocusableInTouchMode(true);
        button.requestFocus();
    }

    public static ApplicationModule materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging(Context context) {
        return SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(context, 2);
    }

    @Override // android.content.DialogInterface
    public final void cancel() {
        ((AlertDialog) this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).cancel();
    }

    @Override // android.content.DialogInterface
    public final void dismiss() {
        ((AppCompatDialog) this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).dismiss();
    }

    public final Button getButton(int i) {
        return ((AlertDialog) this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).getButton(i);
    }

    public final Dialog getDialog() {
        return (Dialog) this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application;
    }

    public final Window getWindow() {
        return ((AlertDialog) this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).getWindow();
    }

    public final boolean isShowing() {
        return ((AlertDialog) this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).isShowing();
    }

    public final void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        ((AlertDialog) this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).setOnDismissListener(onDismissListener);
    }

    public final void show() {
        ((AlertDialog) this.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).show();
    }
}
