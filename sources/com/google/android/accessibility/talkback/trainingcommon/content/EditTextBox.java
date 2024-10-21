package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditTextBox extends PageContentConfig {
    private final int hintResId;
    private final int textResId;

    public EditTextBox(int i, int i2) {
        this.textResId = i;
        this.hintResId = i2;
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_edit_text, viewGroup, false);
        EditText editText = (EditText) inflate.findViewById(R.id.training_edit_text);
        int i = this.textResId;
        if (i != -1) {
            editText.setText(i);
        } else {
            int i2 = this.hintResId;
            if (i2 != -1) {
                editText.setHint(i2);
            }
        }
        return inflate;
    }
}
