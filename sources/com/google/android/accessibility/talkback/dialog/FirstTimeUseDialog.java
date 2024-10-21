package com.google.android.accessibility.talkback.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FirstTimeUseDialog extends BaseDialog {
    private CheckBox checkBox;
    private final int checkboxTextResId;
    public String dialogMainMessage;
    private final int dialogMainMessageResId;
    private final int dialogSecondMessageResId;
    private final int dialogThirdMessageResId;
    private final SharedPreferences prefs;
    private final int showDialogPreference;

    public FirstTimeUseDialog(Context context, int i, int i2, int i3, int i4) {
        super(context, i2, null);
        this.showDialogPreference = i;
        this.dialogMainMessageResId = i3;
        this.dialogSecondMessageResId = -1;
        this.dialogThirdMessageResId = -1;
        this.checkboxTextResId = i4;
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final View getCustomizedView() {
        ScrollView scrollView = (ScrollView) LayoutInflater.from(this.context).inflate(R.layout.first_time_use_dialog, (ViewGroup) null);
        this.checkBox = (CheckBox) scrollView.findViewById(R.id.show_message_checkbox);
        TextView textView = (TextView) scrollView.findViewById(R.id.dialog_content);
        this.checkBox.setText(this.checkboxTextResId);
        String str = this.dialogMainMessage;
        if (str != null) {
            textView.setText(str);
        } else {
            textView.setText(this.dialogMainMessageResId);
        }
        return scrollView;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final String getMessageString() {
        return null;
    }

    public final boolean getShouldShowDialogPref() {
        return this.prefs.getBoolean(this.context.getString(this.showDialogPreference), true);
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public void handleDialogClick(int i) {
        CheckBox checkBox;
        if (i == -1 && (checkBox = this.checkBox) != null && !checkBox.isChecked()) {
            SpannableUtils$IdentifierSpan.putBooleanPref(this.prefs, this.context.getResources(), this.showDialogPreference, false);
        }
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public void handleDialogDismiss() {
        this.checkBox = null;
    }

    public final void setSharedPreferencesByKey$ar$ds() {
        SpannableUtils$IdentifierSpan.putBooleanPref(this.prefs, this.context.getResources(), R.string.pref_dim_when_talkback_enabled_key, true);
    }
}
