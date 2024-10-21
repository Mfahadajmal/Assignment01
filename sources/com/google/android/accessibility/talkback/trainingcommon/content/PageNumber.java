package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PageNumber extends PageContentConfig {
    private final int pageNumber;
    private final int totalNumber;

    public PageNumber(int i, int i2) {
        this.pageNumber = i;
        this.totalNumber = i2;
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_page_number, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.training_page_number)).setText(context.getString(R.string.training_page_number, Integer.valueOf(this.pageNumber), Integer.valueOf(this.totalNumber)));
        return inflate;
    }
}
