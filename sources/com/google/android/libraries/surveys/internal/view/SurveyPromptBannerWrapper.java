package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.resourceutils.LayoutUtils;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.surveys_android.features.HatsV1M5Features;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SurveyPromptBannerWrapper extends FrameLayout {
    public SurveyPromptBannerWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(HatsV1M5Features.INSTANCE.get().enableLandscapeImprovementsNonmodal(FlagsUtil.phenotypeContext))) {
            i2 = LayoutUtils.measureCardHeightMeasureSpec(this, findViewById(R.id.survey_prompt_banner), i, i2, findViewById(R.id.survey_prompt_header), null, false);
        }
        super.onMeasure(i, i2);
    }
}
