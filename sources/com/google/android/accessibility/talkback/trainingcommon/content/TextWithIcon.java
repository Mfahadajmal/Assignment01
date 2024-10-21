package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextWithIcon extends PageContentConfig {
    private final int srcResId;
    private final int subtextResId;
    private final int textResId;

    public TextWithIcon() {
        this.textResId = R.string.new_shortcut_gesture_pause_or_play_media_text;
        this.subtextResId = R.string.new_shortcut_gesture_pause_or_play_media_subtext;
        this.srcResId = R.drawable.ic_gesture_2fingerdoubletap;
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_text_with_icon, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.training_icon);
        TextView textView = (TextView) inflate.findViewById(R.id.training_text);
        TextView textView2 = (TextView) inflate.findViewById(R.id.training_subtext);
        imageView.setImageResource(this.srcResId);
        textView.setText(this.textResId);
        int i = this.subtextResId;
        if (i != -1) {
            textView2.setText(i);
        } else {
            textView2.setVisibility(8);
        }
        return inflate;
    }

    public TextWithIcon(int i, int i2) {
        this.textResId = i;
        this.subtextResId = -1;
        this.srcResId = i2;
    }
}
