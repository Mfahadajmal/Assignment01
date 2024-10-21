package com.google.android.libraries.surveys.internal.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.common.collect.ImmutableMap;
import com.google.scone.proto.Survey$Completion;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$Question;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Features;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyViewPagerAdapter extends FragmentPagerAdapter {
    private final Integer logoResId;
    private ImmutableMap questionOrdinalCardIndexMap;
    public final List surveyCards;
    public final PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle;

    public SurveyViewPagerAdapter(FragmentManager fragmentManager, Survey$Payload survey$Payload, Integer num, boolean z, boolean z2, PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle, int i) {
        super(fragmentManager);
        ArrayList arrayList = new ArrayList(survey$Payload.question_.size());
        for (Survey$Question survey$Question : survey$Payload.question_) {
            int forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(survey$Question.questionType_);
            forNumber$ar$edu$83e82a14_0 = forNumber$ar$edu$83e82a14_0 == 0 ? Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0 : forNumber$ar$edu$83e82a14_0;
            if (forNumber$ar$edu$83e82a14_0 != 0) {
                int i2 = forNumber$ar$edu$83e82a14_0 - 2;
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 == 4) {
                                arrayList.add(new OpenTextCard(survey$Question));
                            }
                        } else {
                            arrayList.add(new RatingCard(survey$Question));
                        }
                    } else {
                        arrayList.add(new MultiSelectCard(survey$Question));
                    }
                } else {
                    arrayList.add(new SingleSelectCard(survey$Question));
                }
            } else {
                throw null;
            }
        }
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(HatsV1M3Features.enableUserPromptedSurveys(FlagsUtil.phenotypeContext))) {
            if (surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.CARD) {
                Survey$Completion survey$Completion = survey$Payload.completion_;
                arrayList.add(new ThankYouCard(survey$Completion == null ? Survey$Completion.DEFAULT_INSTANCE : survey$Completion));
            }
        } else {
            Survey$Completion survey$Completion2 = survey$Payload.completion_;
            arrayList.add(new ThankYouCard(survey$Completion2 == null ? Survey$Completion.DEFAULT_INSTANCE : survey$Completion2));
        }
        this.surveyCards = arrayList;
        int i3 = 0;
        if (z && !arrayList.isEmpty()) {
            arrayList.remove(0);
            i--;
        }
        if (FlagsUtil.isBranchingEnabled() && i > 0) {
            arrayList.subList(0, i).clear();
        }
        if (z2 && !arrayList.isEmpty()) {
            arrayList.subList(0, arrayList.size() - 1).clear();
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int i4 = ((SurveyCard) it.next()).surveyCardType$ar$edu;
            if (i4 != 0) {
                if (i4 != 5) {
                    builder.put$ar$ds$de9b9d28_0(Integer.valueOf(r9.question.questionOrdinal_ - 1), Integer.valueOf(i3));
                    i3++;
                }
            } else {
                throw null;
            }
        }
        this.questionOrdinalCardIndexMap = builder.buildOrThrow();
        if (!this.surveyCards.isEmpty()) {
            this.logoResId = num;
            this.surveyCompletionStyle = surveyCompletionStyle;
            return;
        }
        throw new NullPointerException("Questions were missing!");
    }

    public static int getQuestionIndex(Fragment fragment) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            return -1;
        }
        return arguments.getInt("QuestionIndex", -1);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getCount() {
        return this.surveyCards.size();
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public final Fragment getItem(int i) {
        Fragment buildFragment = ((SurveyCard) this.surveyCards.get(i)).buildFragment(this.logoResId, i);
        buildFragment.getArguments().putInt("QuestionIndex", i);
        return buildFragment;
    }

    public final Survey$Question getQuestion(int i) {
        return ((SurveyCard) this.surveyCards.get(i)).question;
    }

    public final int getQuestionOrdinalCardIndex(int i) {
        return ((Integer) this.questionOrdinalCardIndexMap.get(Integer.valueOf(i))).intValue();
    }
}
