package com.google.android.libraries.surveys.internal.view;

import android.support.v4.app.Fragment;
import com.google.scone.proto.Survey$Question;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingleSelectCard extends SurveyCard {
    public SingleSelectCard(Survey$Question survey$Question) {
        this.question = survey$Question;
        this.surveyCardType$ar$edu = 4;
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyCard
    public final Fragment buildFragment(Integer num, int i) {
        Survey$Question survey$Question = this.question;
        SingleSelectFragment singleSelectFragment = new SingleSelectFragment();
        singleSelectFragment.setArguments(SingleSelectFragment.createArguments(survey$Question, num, i));
        return singleSelectFragment;
    }
}
