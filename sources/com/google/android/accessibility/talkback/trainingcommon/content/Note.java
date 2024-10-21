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
public final class Note extends PageContentConfig {
    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_note, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.training_note)).setText(R.string.new_shortcut_gesture_note);
        return inflate;
    }
}
