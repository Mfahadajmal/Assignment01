package com.google.android.accessibility.talkback.labeling;

import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class LabelDialogManager$BaseEditLabelDialog$$ExternalSyntheticLambda0 implements TextView.OnEditorActionListener {
    public final /* synthetic */ Object LabelDialogManager$BaseEditLabelDialog$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ LabelDialogManager$BaseEditLabelDialog$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.LabelDialogManager$BaseEditLabelDialog$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (this.switching_field != 0) {
            if (i != 3) {
                return false;
            }
            ((SearchScreenOverlay) this.LabelDialogManager$BaseEditLabelDialog$$ExternalSyntheticLambda0$ar$f$0).performSearch();
            return true;
        }
        if (i != 6) {
            return false;
        }
        LabelDialogManager$BaseEditLabelDialog labelDialogManager$BaseEditLabelDialog = (LabelDialogManager$BaseEditLabelDialog) this.LabelDialogManager$BaseEditLabelDialog$$ExternalSyntheticLambda0$ar$f$0;
        ((InputMethodManager) labelDialogManager$BaseEditLabelDialog.context.getSystemService("input_method")).hideSoftInputFromWindow(labelDialogManager$BaseEditLabelDialog.editField.getWindowToken(), 0);
        return true;
    }
}
