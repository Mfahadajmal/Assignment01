package com.google.android.libraries.surveys.internal.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.scone.proto.Survey$Completion;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThankYouCard extends SurveyCard {
    private final Survey$Completion completion;

    public ThankYouCard(Survey$Completion survey$Completion) {
        this.completion = survey$Completion;
        this.surveyCardType$ar$edu = 5;
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyCard
    public final Fragment buildFragment(Integer num, int i) {
        ThankYouFragment thankYouFragment = new ThankYouFragment();
        Bundle bundle = new Bundle();
        if (num != null) {
            bundle.putInt("DisplayLogoResId", num.intValue());
        }
        bundle.putByteArray("Completion", this.completion.toByteArray());
        thankYouFragment.setArguments(bundle);
        return thankYouFragment;
    }
}
