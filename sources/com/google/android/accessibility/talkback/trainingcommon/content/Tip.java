package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Tip extends PageContentConfig {
    private final int textResId;
    private final int textTtsSpanResId;

    public Tip(int i, int i2) {
        this.textResId = i;
        this.textTtsSpanResId = i2;
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_tip, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.training_tip_text);
        String string = context.getString(this.textResId);
        SpannableString spannableString = new SpannableString(string);
        int i = this.textTtsSpanResId;
        if (i != -1) {
            spannableString.setSpan(new TtsSpan.TextBuilder(context.getString(i)).build(), 0, string.length(), 0);
        }
        textView.setText(spannableString);
        return inflate;
    }
}
