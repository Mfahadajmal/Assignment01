package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExitBanner extends PageContentConfig {
    public boolean firstTapPerformed;
    public WindowTrackerFactory metricStore$ar$class_merging$ar$class_merging;
    public HapticPatternParser$$ExternalSyntheticLambda1 requestDisableTalkBack$ar$class_merging$ar$class_merging;

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        this.firstTapPerformed = false;
        View inflate = layoutInflater.inflate(R.layout.training_exit_banner, viewGroup, false);
        Button button = (Button) inflate.findViewById(R.id.training_exit_talkback_button);
        button.setLongClickable(false);
        button.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(this, button, 2));
        return inflate;
    }
}
