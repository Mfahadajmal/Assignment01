package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.talkback.trainingcommon.content.Text;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WholeScreenText extends Text {
    public WholeScreenText(Text.Paragraph... paragraphArr) {
        super(paragraphArr);
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.Text, com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        int height;
        View createView = super.createView(layoutInflater, viewGroup, context, serviceData);
        Rect rect = new Rect();
        viewGroup.getGlobalVisibleRect(rect);
        int height2 = rect.height();
        View findViewById = viewGroup.findViewById(R.id.nav_container);
        if (findViewById == null) {
            height = 0;
        } else {
            height = findViewById.getHeight();
        }
        createView.setMinimumHeight(height2 - height);
        return createView;
    }
}
