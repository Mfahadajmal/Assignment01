package com.google.android.accessibility.brailleime.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TooFewTouchPointsDialog extends ViewAttachedDialog {
    public final FloatingActionButton.ShadowDelegateImpl callback$ar$class_merging$aff09e5a_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context context;
    private Dialog dialog;

    public TooFewTouchPointsDialog(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.context = context;
        this.callback$ar$class_merging$aff09e5a_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
    }

    @Override // com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog
    protected final Dialog makeDialog() {
        Context context = this.context;
        AlertDialog.Builder alertDialogBuilder = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(context);
        alertDialogBuilder.setTitle(context.getString(R.string.not_enough_touch_points_dialog_title)).setMessage(this.context.getString(R.string.not_enough_touch_points_dialog_message));
        if (SpannableUtils$IdentifierSpan.areMultipleImesEnabled(this.context)) {
            alertDialogBuilder.setPositiveButton(this.context.getString(R.string.next_keyboard), new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 13));
        } else {
            alertDialogBuilder.setPositiveButton(this.context.getString(android.R.string.ok), new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 14));
        }
        alertDialogBuilder.setOnCancelListener(new TalkBackOffDialog$$ExternalSyntheticLambda1(this, 3));
        AlertDialog create = alertDialogBuilder.create();
        this.dialog = create;
        create.setCanceledOnTouchOutside(false);
        return this.dialog;
    }
}
