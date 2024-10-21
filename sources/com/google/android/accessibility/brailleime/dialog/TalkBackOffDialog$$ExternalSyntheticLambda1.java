package com.google.android.accessibility.brailleime.dialog;

import android.content.DialogInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TalkBackOffDialog$$ExternalSyntheticLambda1 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ ViewAttachedDialog TalkBackOffDialog$$ExternalSyntheticLambda1$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TalkBackOffDialog$$ExternalSyntheticLambda1(ViewAttachedDialog viewAttachedDialog, int i) {
        this.switching_field = i;
        this.TalkBackOffDialog$$ExternalSyntheticLambda1$ar$f$0 = viewAttachedDialog;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    ((TooFewTouchPointsDialog) this.TalkBackOffDialog$$ExternalSyntheticLambda1$ar$f$0).callback$ar$class_merging$aff09e5a_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSwitchToNextIme();
                    return;
                } else {
                    ((TalkBackSuspendDialog) this.TalkBackOffDialog$$ExternalSyntheticLambda1$ar$f$0).callback$ar$class_merging$6557f8d2_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSwitchToNextIme();
                    return;
                }
            }
            ((ContextMenuDialog) this.TalkBackOffDialog$$ExternalSyntheticLambda1$ar$f$0).callback.onDialogHidden();
            return;
        }
        ((TalkBackOffDialog) this.TalkBackOffDialog$$ExternalSyntheticLambda1$ar$f$0).callback$ar$class_merging$4b8c0d33_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSwitchToNextIme();
    }
}
