package com.google.android.libraries.surveys.internal.view;

import android.support.v4.app.Fragment;
import com.google.scone.proto.Survey$Question;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RatingCard extends SurveyCard {
    public RatingCard(Survey$Question survey$Question) {
        this.question = survey$Question;
        this.surveyCardType$ar$edu = 3;
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyCard
    public final Fragment buildFragment(Integer num, int i) {
        Survey$Question survey$Question = this.question;
        RatingFragment ratingFragment = new RatingFragment();
        ratingFragment.setArguments(RatingFragment.createArguments(survey$Question, num, i));
        return ratingFragment;
    }
}
