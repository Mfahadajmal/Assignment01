package com.google.android.libraries.surveys.internal.view;

import android.support.v4.app.Fragment;
import com.google.scone.proto.Survey$Question;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OpenTextCard extends SurveyCard {
    public OpenTextCard(Survey$Question survey$Question) {
        this.question = survey$Question;
        this.surveyCardType$ar$edu = 2;
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyCard
    public final Fragment buildFragment(Integer num, int i) {
        Survey$Question survey$Question = this.question;
        OpenTextFragment openTextFragment = new OpenTextFragment();
        openTextFragment.setArguments(OpenTextFragment.createArguments(survey$Question, num, i));
        return openTextFragment;
    }
}
