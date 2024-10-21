package com.google.android.libraries.surveys.internal.view;

import android.support.v4.app.Fragment;
import com.google.scone.proto.Survey$Question;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultiSelectCard extends SurveyCard {
    public MultiSelectCard(Survey$Question survey$Question) {
        this.question = survey$Question;
        this.surveyCardType$ar$edu = 1;
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyCard
    public final Fragment buildFragment(Integer num, int i) {
        Survey$Question survey$Question = this.question;
        MultipleSelectFragment multipleSelectFragment = new MultipleSelectFragment();
        multipleSelectFragment.setArguments(MultipleSelectFragment.createArguments(survey$Question, num, i));
        return multipleSelectFragment;
    }
}
