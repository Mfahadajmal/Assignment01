package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Title extends PageContentConfig {
    private final int titleResId;
    private final String titleString;

    public Title(PageConfig pageConfig) {
        this.titleResId = pageConfig.getPageNameResId();
        this.titleString = pageConfig.getPageNameString();
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_title, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.training_title);
        String str = this.titleString;
        if (str != null) {
            textView.setText(str);
        } else {
            textView.setText(this.titleResId);
        }
        textView.setLayoutParams((ViewGroup.MarginLayoutParams) textView.getLayoutParams());
        return inflate;
    }
}
