package com.google.android.accessibility.brailleime.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackSuspendDialog extends ViewAttachedDialog {
    public final HapticPatternParser$$ExternalSyntheticLambda1 callback$ar$class_merging$6557f8d2_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context context;
    private Dialog dialog;

    public TalkBackSuspendDialog(Context context, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.context = context;
        this.callback$ar$class_merging$6557f8d2_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    @Override // com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog
    public final Dialog makeDialog() {
        int i;
        AlertDialog.Builder message = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(this.context).setTitle(R.string.talkback_suspend_dialog_title).setMessage(R.string.talkback_off_or_suspend_dialog_message);
        boolean areMultipleImesEnabled = SpannableUtils$IdentifierSpan.areMultipleImesEnabled(this.context);
        BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0 brailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0 = new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 12);
        if (true != areMultipleImesEnabled) {
            i = android.R.string.ok;
        } else {
            i = R.string.next_keyboard;
        }
        AlertDialog create = message.setPositiveButton(i, brailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0).setOnCancelListener(new TalkBackOffDialog$$ExternalSyntheticLambda1(this, 2)).create();
        this.dialog = create;
        create.setCanceledOnTouchOutside(false);
        return this.dialog;
    }
}
