package com.google.android.accessibility.talkback.labeling;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.libraries.surveys.internal.view.OpenTextView;
import com.google.android.marvin.talkback.R;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LabelDialogManager$BaseEditLabelDialog extends BaseDialog {
    protected EditText editField;
    protected final CustomLabelManager labelManager;
    private final TextWatcher textValidator;

    public LabelDialogManager$BaseEditLabelDialog(Context context, int i, CustomLabelManager customLabelManager, Pipeline.FeedbackReturner feedbackReturner) {
        super(context, i, feedbackReturner);
        this.textValidator = new OpenTextView.AnonymousClass1(this, 1);
        this.labelManager = customLabelManager;
    }

    protected abstract String getCustomizedMessage();

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final View getCustomizedView() {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.label_addedit_dialog, (ViewGroup) null);
        String customizedMessage = getCustomizedMessage();
        if (!TextUtils.isEmpty(customizedMessage)) {
            ((TextView) inflate.findViewById(R.id.label_dialog_text)).setText(customizedMessage);
        }
        EditText editText = (EditText) inflate.findViewById(R.id.label_dialog_edit_text);
        this.editField = editText;
        editText.setOnEditorActionListener(new LabelDialogManager$BaseEditLabelDialog$$ExternalSyntheticLambda0(this, 0));
        this.editField.addTextChangedListener(this.textValidator);
        this.editField.requestFocus();
        setupCustomizedView();
        return inflate;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public void handleDialogClick(int i) {
        if (i == -1) {
            onPositiveAction();
        } else if (i == -2) {
            dismissDialog();
        }
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogDismiss() {
        this.labelManager.shutdown();
        this.editField = null;
    }

    protected abstract void onPositiveAction();

    protected void setupCustomizedView() {
    }
}
