package com.google.android.libraries.surveys.internal.view;

import android.support.v4.app.Fragment;
import com.google.scone.proto.Survey$Question;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SurveyCard {
    protected Survey$Question question;
    protected int surveyCardType$ar$edu = 6;

    public abstract Fragment buildFragment(Integer num, int i);
}
