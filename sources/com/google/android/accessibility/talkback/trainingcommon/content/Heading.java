package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Heading extends PageContentConfig {
    private final int textResId;

    public Heading(int i) {
        this.textResId = i;
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_heading, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.training_heading);
        textView.setText(this.textResId);
        ViewCompat.setAccessibilityHeading(textView, true);
        return inflate;
    }
}
