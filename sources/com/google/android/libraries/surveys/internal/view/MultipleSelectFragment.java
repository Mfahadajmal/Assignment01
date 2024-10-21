package com.google.android.libraries.surveys.internal.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.android.libraries.surveys.internal.model.QuestionMetrics;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.protobuf.Internal;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$AnswerChoices;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$MultiSelect;
import com.google.scone.proto.Survey$Question;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultipleSelectFragment extends ScrollableAnswerFragment {
    private ViewGroup answersContainer;
    public RemoteModelManager.RemoteModelManagerRegistration multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging;
    public QuestionMetrics questionMetrics;
    private boolean[] responses;

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void animateFadeIn() {
        if (this.answersContainer != null) {
            int i = 0;
            while (i < this.answersContainer.getChildCount()) {
                View childAt = this.answersContainer.getChildAt(i);
                childAt.setAlpha(0.0f);
                i++;
                childAt.animate().alpha(1.0f).setDuration(150L).setStartDelay(i * 80);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.lang.CharSequence, java.lang.Object] */
    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final Survey$Event.QuestionAnswered computeQuestionResponse() {
        Survey$MultiSelect survey$MultiSelect;
        int i;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.DEFAULT_INSTANCE.createBuilder();
        if (this.questionMetrics.isShown()) {
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.MultipleSelectAnswer.DEFAULT_INSTANCE.createBuilder();
            Survey$Question survey$Question = this.question;
            if (survey$Question.questionCase_ == 5) {
                survey$MultiSelect = (Survey$MultiSelect) survey$Question.question_;
            } else {
                survey$MultiSelect = Survey$MultiSelect.DEFAULT_INSTANCE;
            }
            Survey$AnswerChoices survey$AnswerChoices = survey$MultiSelect.answerChoices_;
            if (survey$AnswerChoices == null) {
                survey$AnswerChoices = Survey$AnswerChoices.DEFAULT_INSTANCE;
            }
            Internal.ProtobufList protobufList = survey$AnswerChoices.answerChoice_;
            int i2 = 0;
            while (true) {
                boolean[] zArr = (boolean[]) this.multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass;
                if (i2 >= zArr.length) {
                    break;
                }
                if (zArr[i2]) {
                    Object obj = ((Survey$AnswerChoice) protobufList.get(i2)).text_;
                    int forNumber$ar$edu$ac62307f_0 = Survey$AnswerChoice.AnswerType.forNumber$ar$edu$ac62307f_0(((Survey$AnswerChoice) protobufList.get(i2)).answerType_);
                    if (forNumber$ar$edu$ac62307f_0 == 0) {
                        forNumber$ar$edu$ac62307f_0 = Survey$AnswerChoice.AnswerType.UNRECOGNIZED$ar$edu$57c31185_0;
                    }
                    if (forNumber$ar$edu$ac62307f_0 == Survey$AnswerChoice.AnswerType.ANSWER_TYPE_WRITE_IN$ar$edu && !TextUtils.isEmpty(this.multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider)) {
                        obj = this.multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider;
                    }
                    SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE.createBuilder();
                    int i3 = ((Survey$AnswerChoice) protobufList.get(i2)).answerOrdinal_;
                    builder3.copyOnWrite();
                    ((Survey$Event.QuestionAnswered.Selection) builder3.instance).answerOrdinal_ = i3;
                    builder3.copyOnWrite();
                    Survey$Event.QuestionAnswered.Selection selection = (Survey$Event.QuestionAnswered.Selection) builder3.instance;
                    obj.getClass();
                    selection.text_ = (String) obj;
                    int forNumber$ar$edu$ac62307f_02 = Survey$AnswerChoice.AnswerType.forNumber$ar$edu$ac62307f_0(((Survey$AnswerChoice) protobufList.get(i2)).answerType_);
                    if (forNumber$ar$edu$ac62307f_02 == 0) {
                        forNumber$ar$edu$ac62307f_02 = Survey$AnswerChoice.AnswerType.UNRECOGNIZED$ar$edu$57c31185_0;
                    }
                    if (forNumber$ar$edu$ac62307f_02 != 0) {
                        int i4 = forNumber$ar$edu$ac62307f_02 - 2;
                        if (i4 != 1) {
                            if (i4 != 2) {
                                if (i4 != 3) {
                                    i = Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_UNSPECIFIED$ar$edu$def17366_0;
                                } else {
                                    i = Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu$def17366_0;
                                }
                            } else {
                                i = Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_WRITE_IN$ar$edu$def17366_0;
                            }
                        } else {
                            i = Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_TEXT$ar$edu$def17366_0;
                        }
                        builder3.copyOnWrite();
                        ((Survey$Event.QuestionAnswered.Selection) builder3.instance).answerType_ = Survey$Event.QuestionAnswered.Selection.AnswerType.getNumber$ar$edu$def17366_0(i);
                        builder2.addAnswer$ar$ds((Survey$Event.QuestionAnswered.Selection) builder3.build());
                        this.questionMetrics.markAsAnswered();
                    } else {
                        throw null;
                    }
                }
                int i5 = this.question.questionOrdinal_;
                builder.copyOnWrite();
                ((Survey$Event.QuestionAnswered) builder.instance).questionOrdinal_ = i5;
                Survey$Event.QuestionAnswered.MultipleSelectAnswer multipleSelectAnswer = (Survey$Event.QuestionAnswered.MultipleSelectAnswer) builder2.build();
                builder.copyOnWrite();
                Survey$Event.QuestionAnswered questionAnswered = (Survey$Event.QuestionAnswered) builder.instance;
                multipleSelectAnswer.getClass();
                questionAnswered.answer_ = multipleSelectAnswer;
                questionAnswered.answerCase_ = 3;
                i2++;
            }
        }
        return (Survey$Event.QuestionAnswered) builder.build();
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment
    public final View createScrollViewContents() {
        Survey$MultiSelect survey$MultiSelect;
        this.answersContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.survey_scrollable_answer_content_container, (ViewGroup) null).findViewById(R.id.survey_answers_container);
        MultipleSelectView multipleSelectView = new MultipleSelectView(getContext());
        multipleSelectView.onAnswerSelectClickListener = new PromptDialogDelegate$$ExternalSyntheticLambda3(this, 1);
        Survey$Question survey$Question = this.question;
        if (survey$Question.questionCase_ == 5) {
            survey$MultiSelect = (Survey$MultiSelect) survey$Question.question_;
        } else {
            survey$MultiSelect = Survey$MultiSelect.DEFAULT_INSTANCE;
        }
        multipleSelectView.setUpMultipleSelectView(survey$MultiSelect, this.responses);
        this.answersContainer.addView(multipleSelectView);
        return this.answersContainer;
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment
    public final String getQuestionText() {
        if (this.question.questionHtml_.isEmpty()) {
            return this.question.questionText_;
        }
        return this.question.questionHtml_;
    }

    public final boolean isResponseSatisfactory() {
        RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = this.multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging;
        if (remoteModelManagerRegistration == null) {
            return false;
        }
        return remoteModelManagerRegistration.isAnswerValid();
    }

    @Override // android.support.v4.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivityIfRunning().onQuestionProgressableChanged(isResponseSatisfactory(), this);
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment, android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        Survey$MultiSelect survey$MultiSelect;
        Survey$MultiSelect survey$MultiSelect2;
        Survey$MultiSelect survey$MultiSelect3;
        super.onCreate(bundle);
        if (bundle != null) {
            this.questionMetrics = (QuestionMetrics) bundle.getParcelable("QuestionMetrics");
            this.responses = bundle.getBooleanArray("ResponsesAsArray");
        }
        if (this.questionMetrics == null) {
            this.questionMetrics = new QuestionMetrics();
        }
        boolean[] zArr = this.responses;
        if (zArr == null) {
            Survey$Question survey$Question = this.question;
            if (survey$Question.questionCase_ == 5) {
                survey$MultiSelect3 = (Survey$MultiSelect) survey$Question.question_;
            } else {
                survey$MultiSelect3 = Survey$MultiSelect.DEFAULT_INSTANCE;
            }
            Survey$AnswerChoices survey$AnswerChoices = survey$MultiSelect3.answerChoices_;
            if (survey$AnswerChoices == null) {
                survey$AnswerChoices = Survey$AnswerChoices.DEFAULT_INSTANCE;
            }
            this.responses = new boolean[survey$AnswerChoices.answerChoice_.size()];
            return;
        }
        Survey$Question survey$Question2 = this.question;
        if (survey$Question2.questionCase_ == 5) {
            survey$MultiSelect = (Survey$MultiSelect) survey$Question2.question_;
        } else {
            survey$MultiSelect = Survey$MultiSelect.DEFAULT_INSTANCE;
        }
        Survey$AnswerChoices survey$AnswerChoices2 = survey$MultiSelect.answerChoices_;
        if (survey$AnswerChoices2 == null) {
            survey$AnswerChoices2 = Survey$AnswerChoices.DEFAULT_INSTANCE;
        }
        if (zArr.length != survey$AnswerChoices2.answerChoice_.size()) {
            Log.e("SurveyMultiSelectFrag", "Saved instance state responses had incorrect length: " + this.responses.length);
            Survey$Question survey$Question3 = this.question;
            if (survey$Question3.questionCase_ == 5) {
                survey$MultiSelect2 = (Survey$MultiSelect) survey$Question3.question_;
            } else {
                survey$MultiSelect2 = Survey$MultiSelect.DEFAULT_INSTANCE;
            }
            Survey$AnswerChoices survey$AnswerChoices3 = survey$MultiSelect2.answerChoices_;
            if (survey$AnswerChoices3 == null) {
                survey$AnswerChoices3 = Survey$AnswerChoices.DEFAULT_INSTANCE;
            }
            this.responses = new boolean[survey$AnswerChoices3.answerChoice_.size()];
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment, com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void onPageScrolledIntoView() {
        super.onPageScrolledIntoView();
        this.questionMetrics.markAsShown();
        getActivityIfRunning().onQuestionProgressableChanged(isResponseSatisfactory(), this);
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment, android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("QuestionMetrics", this.questionMetrics);
        bundle.putBooleanArray("ResponsesAsArray", this.responses);
    }
}
