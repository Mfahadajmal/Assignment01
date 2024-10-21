package com.google.android.accessibility.talkback.actor.gemini;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GeminiOptInDialog extends BaseDialog {
    private final int dialogMessageResId;
    private final boolean showSummary;

    public GeminiOptInDialog(Context context, int i, boolean z, int i2, int i3, int i4) {
        super(context, i, null);
        setPositiveButtonStringRes(i3);
        if (i4 != -1) {
            setNegativeButtonStringRes(i4);
        }
        this.dialogMessageResId = i2;
        this.showSummary = z;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final View getCustomizedView() {
        ScrollView scrollView = (ScrollView) LayoutInflater.from(this.context).inflate(R.layout.detail_image_description_promotion_dialog, (ViewGroup) null);
        if (!this.showSummary) {
            ((TextView) scrollView.findViewById(R.id.ai_descriptiog_promotion_dialog_summary)).setVisibility(8);
        }
        TextView textView = (TextView) scrollView.findViewById(R.id.ai_descriptiog_promotion_dialog_message);
        if (this.dialogMessageResId != -1 && textView != null) {
            Context context = this.context;
            Context context2 = this.context;
            int i = this.dialogMessageResId;
            String string = context.getString(R.string.dialog_message_gen_ai_tos_link);
            String string2 = context2.getString(i, string);
            SpannableString spannableString = new SpannableString(string2);
            int indexOf = string2.indexOf(string);
            if (indexOf >= 0) {
                Context context3 = this.context;
                GeminiFunctionUtils.DescribeImageCandidate describeImageCandidate = GeminiFunctionUtils.confirmDownloadOrPerformImageCaptioning;
                spannableString.setSpan(new GeminiFunctionUtils.AnonymousClass8(context3, this), indexOf, string.length() + indexOf, 0);
            }
            textView.setText(spannableString);
        }
        return scrollView;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final String getMessageString() {
        return null;
    }
}
