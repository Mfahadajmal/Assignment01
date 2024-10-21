package com.google.android.accessibility.brailleime.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackOffDialog extends ViewAttachedDialog {
    public final RetryingNameResolver.ResolutionResultListener callback$ar$class_merging$4b8c0d33_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context context;
    private Dialog dialog;

    public TalkBackOffDialog(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.context = context;
        this.callback$ar$class_merging$4b8c0d33_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
    }

    @Override // com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog
    protected final Dialog makeDialog() {
        AlertDialog.Builder onCancelListener = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(this.context).setTitle(R.string.talkback_off_dialog_title).setMessage(R.string.talkback_off_or_suspend_dialog_message).setPositiveButton(R.string.talkback_off_dialog_positive_button, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 10)).setOnCancelListener(new TalkBackOffDialog$$ExternalSyntheticLambda1(this, 0));
        if (SpannableUtils$IdentifierSpan.areMultipleImesEnabled(this.context)) {
            onCancelListener.setNegativeButton(R.string.next_keyboard, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 11));
        }
        AlertDialog create = onCancelListener.create();
        this.dialog = create;
        create.setCanceledOnTouchOutside(false);
        return this.dialog;
    }
}
