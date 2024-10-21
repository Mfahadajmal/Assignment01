package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Image extends PageContentConfig {
    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_image, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.onboarding_sample_image);
        imageView.setContentDescription(context.getString(R.string.onboarding_detailed_image_descriptions_image_content_description));
        return inflate;
    }
}
