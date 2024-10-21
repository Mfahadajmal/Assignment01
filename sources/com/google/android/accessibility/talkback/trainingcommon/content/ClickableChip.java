package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ClickableChip extends PageContentConfig {
    public final int[] firstPageCandidatesInSectionNameResIds;
    public transient HapticPatternParser$$ExternalSyntheticLambda1 linkHandler$ar$class_merging$ar$class_merging;
    private final int srcResId;
    private final int subtextResId;
    private final int textResId;

    public ClickableChip(int i, int i2, int i3) {
        this.textResId = i;
        this.subtextResId = i2;
        this.srcResId = i3;
    }

    protected View.OnClickListener createOnClickListener(Context context, TrainingIpcClient.ServiceData serviceData) {
        return new TrainingActivity$$ExternalSyntheticLambda0(this, 3);
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_link, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.training_chip_icon);
        TextView textView = (TextView) inflate.findViewById(R.id.training_chip_text);
        TextView textView2 = (TextView) inflate.findViewById(R.id.training_chip_subtext);
        imageView.setImageResource(this.srcResId);
        textView.setText(this.textResId);
        textView2.setText(this.subtextResId);
        ((LinearLayout) inflate.findViewById(R.id.training_chip)).setOnClickListener(createOnClickListener(context, serviceData));
        return inflate;
    }

    public ClickableChip(int i, int i2, int i3, int[] iArr) {
        this(i, i2, i3);
        this.firstPageCandidatesInSectionNameResIds = iArr;
    }
}
