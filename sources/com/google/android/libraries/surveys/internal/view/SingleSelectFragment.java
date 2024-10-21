package com.google.android.libraries.surveys.internal.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.android.libraries.surveys.internal.model.QuestionMetrics;
import com.google.android.libraries.surveys.internal.view.SingleSelectView;
import com.google.android.marvin.talkback.R;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$Question;
import com.google.scone.proto.Survey$SingleSelect;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import org.chromium.net.impl.CronetEngineBuilderImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingleSelectFragment extends ScrollableAnswerFragment {
    public int answerType$ar$edu$def17366_0;
    private LinearLayout answersContainer;
    private QuestionMetrics questionMetrics;
    public int selectedAnswerOrdinal = -1;
    public String selectedResponse;

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

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final Survey$Event.QuestionAnswered computeQuestionResponse() {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.DEFAULT_INSTANCE.createBuilder();
        if (this.questionMetrics.isShown() && this.selectedResponse != null) {
            this.questionMetrics.markAsAnswered();
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE.createBuilder();
            int i = this.selectedAnswerOrdinal;
            builder2.copyOnWrite();
            ((Survey$Event.QuestionAnswered.Selection) builder2.instance).answerOrdinal_ = i;
            int i2 = this.answerType$ar$edu$def17366_0;
            builder2.copyOnWrite();
            ((Survey$Event.QuestionAnswered.Selection) builder2.instance).answerType_ = Survey$Event.QuestionAnswered.Selection.AnswerType.getNumber$ar$edu$def17366_0(i2);
            String str = this.selectedResponse;
            builder2.copyOnWrite();
            Survey$Event.QuestionAnswered.Selection selection = (Survey$Event.QuestionAnswered.Selection) builder2.instance;
            str.getClass();
            selection.text_ = str;
            Survey$Event.QuestionAnswered.Selection selection2 = (Survey$Event.QuestionAnswered.Selection) builder2.build();
            SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE.createBuilder();
            builder3.copyOnWrite();
            Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer = (Survey$Event.QuestionAnswered.SingleSelectAnswer) builder3.instance;
            selection2.getClass();
            singleSelectAnswer.answer_ = selection2;
            singleSelectAnswer.bitField0_ |= 1;
            Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer2 = (Survey$Event.QuestionAnswered.SingleSelectAnswer) builder3.build();
            builder.copyOnWrite();
            Survey$Event.QuestionAnswered questionAnswered = (Survey$Event.QuestionAnswered) builder.instance;
            singleSelectAnswer2.getClass();
            questionAnswered.answer_ = singleSelectAnswer2;
            questionAnswered.answerCase_ = 2;
            int i3 = this.question.questionOrdinal_;
            builder.copyOnWrite();
            ((Survey$Event.QuestionAnswered) builder.instance).questionOrdinal_ = i3;
        }
        return (Survey$Event.QuestionAnswered) builder.build();
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment
    public final View createScrollViewContents() {
        Survey$SingleSelect survey$SingleSelect;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.survey_scrollable_answer_content_container, (ViewGroup) null);
        this.answersContainer = (LinearLayout) inflate.findViewById(R.id.survey_answers_container);
        SingleSelectView singleSelectView = new SingleSelectView(getContext());
        singleSelectView.onAnswerSelectClickListener = new SingleSelectView.OnAnswerSelectClickListener() { // from class: com.google.android.libraries.surveys.internal.view.SingleSelectFragment$$ExternalSyntheticLambda0
            @Override // com.google.android.libraries.surveys.internal.view.SingleSelectView.OnAnswerSelectClickListener
            public final void onClickAnswerSelect$ar$class_merging$a1759ccb_0(CronetEngineBuilderImpl.QuicHint quicHint) {
                SingleSelectFragment singleSelectFragment = SingleSelectFragment.this;
                SurveyActivityInterface activityIfRunning = singleSelectFragment.getActivityIfRunning();
                if (activityIfRunning == null) {
                    Log.w("SurveyMultiChoiceFrag", "Activity was null, finishing or destroyed while attempting to navigate to the next page. Likely the user rotated the device before the Runnable executed.");
                    return;
                }
                singleSelectFragment.answerType$ar$edu$def17366_0 = quicHint.mAlternatePort;
                singleSelectFragment.selectedResponse = (String) quicHint.CronetEngineBuilderImpl$QuicHint$ar$mHost;
                singleSelectFragment.selectedAnswerOrdinal = quicHint.mPort;
                if (quicHint.mAlternatePort == Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_WRITE_IN$ar$edu$def17366_0) {
                    activityIfRunning.setNextButtonEnabled(true);
                } else {
                    activityIfRunning.nextPageOrSubmit();
                }
            }
        };
        Survey$Question survey$Question = this.question;
        if (survey$Question.questionCase_ == 4) {
            survey$SingleSelect = (Survey$SingleSelect) survey$Question.question_;
        } else {
            survey$SingleSelect = Survey$SingleSelect.DEFAULT_INSTANCE;
        }
        singleSelectView.setUpSingleSelectView(survey$SingleSelect);
        this.answersContainer.addView(singleSelectView);
        if (!getActivityIfRunning().isSurveyControlRequired()) {
            inflate.setPadding(inflate.getPaddingLeft(), inflate.getPaddingTop(), inflate.getPaddingRight(), getResources().getDimensionPixelSize(R.dimen.survey_bottom_padding));
        }
        return inflate;
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment
    public final String getQuestionText() {
        if (this.question.questionHtml_.isEmpty()) {
            return this.question.questionText_;
        }
        return this.question.questionHtml_;
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment, android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.selectedResponse = bundle.getString("SelectedResponse", null);
            this.questionMetrics = (QuestionMetrics) bundle.getParcelable("QuestionMetrics");
        }
        if (this.questionMetrics == null) {
            this.questionMetrics = new QuestionMetrics();
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment, com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void onPageScrolledIntoView() {
        EditText editText;
        super.onPageScrolledIntoView();
        this.questionMetrics.markAsShown();
        SurveyActivityInterface activityIfRunning = getActivityIfRunning();
        LinearLayout linearLayout = this.answersContainer;
        boolean z = false;
        if (linearLayout != null && (editText = (EditText) linearLayout.findViewById(R.id.survey_other_option)) != null && editText.hasFocus()) {
            z = true;
        }
        activityIfRunning.onQuestionProgressableChanged(z, this);
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment, android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("SelectedResponse", this.selectedResponse);
        bundle.putParcelable("QuestionMetrics", this.questionMetrics);
    }
}
