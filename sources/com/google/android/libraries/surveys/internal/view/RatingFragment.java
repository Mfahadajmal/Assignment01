package com.google.android.libraries.surveys.internal.view;

import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.model.QuestionMetrics;
import com.google.android.libraries.surveys.internal.resourceutils.LayoutUtils;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.libraries.surveys.internal.view.RatingView;
import com.google.android.marvin.talkback.R;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$Question;
import com.google.scone.proto.Survey$Rating;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Bugfixes;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RatingFragment extends BaseFragment {
    public QuestionMetrics questionMetrics;
    private TextView questionTextView;
    public int selectedIndex;
    public String selectedResponse;

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final Survey$Event.QuestionAnswered computeQuestionResponse() {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.DEFAULT_INSTANCE.createBuilder();
        if (this.questionMetrics.isShown() && this.selectedResponse != null) {
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE.createBuilder();
            int i = this.selectedIndex;
            builder2.copyOnWrite();
            ((Survey$Event.QuestionAnswered.Selection) builder2.instance).answerOrdinal_ = i;
            int i2 = Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_TEXT$ar$edu$def17366_0;
            builder2.copyOnWrite();
            ((Survey$Event.QuestionAnswered.Selection) builder2.instance).answerType_ = Survey$Event.QuestionAnswered.Selection.AnswerType.getNumber$ar$edu$def17366_0(i2);
            String str = this.selectedResponse;
            builder2.copyOnWrite();
            Survey$Event.QuestionAnswered.Selection selection = (Survey$Event.QuestionAnswered.Selection) builder2.instance;
            str.getClass();
            selection.text_ = str;
            Survey$Event.QuestionAnswered.Selection selection2 = (Survey$Event.QuestionAnswered.Selection) builder2.build();
            SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.RatingAnswer.DEFAULT_INSTANCE.createBuilder();
            builder3.copyOnWrite();
            Survey$Event.QuestionAnswered.RatingAnswer ratingAnswer = (Survey$Event.QuestionAnswered.RatingAnswer) builder3.instance;
            selection2.getClass();
            ratingAnswer.answer_ = selection2;
            ratingAnswer.bitField0_ |= 1;
            Survey$Event.QuestionAnswered.RatingAnswer ratingAnswer2 = (Survey$Event.QuestionAnswered.RatingAnswer) builder3.build();
            int i3 = this.question.questionOrdinal_;
            builder.copyOnWrite();
            ((Survey$Event.QuestionAnswered) builder.instance).questionOrdinal_ = i3;
            builder.copyOnWrite();
            Survey$Event.QuestionAnswered questionAnswered = (Survey$Event.QuestionAnswered) builder.instance;
            ratingAnswer2.getClass();
            questionAnswered.answer_ = ratingAnswer2;
            questionAnswered.answerCase_ = 4;
            long j = SurveyUtils.TIMEOUT_MS;
        }
        return (Survey$Event.QuestionAnswered) builder.build();
    }

    public final boolean isResponseSatisfactory() {
        if (this.selectedResponse != null) {
            return true;
        }
        return false;
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

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Integer num;
        Survey$Rating survey$Rating;
        String str;
        View inflate = layoutInflater.inflate(R.layout.survey_question_rating_fragment, viewGroup, false);
        Bundle arguments = getArguments();
        CharSequence charSequence = null;
        if (arguments.containsKey("DisplayLogoResId")) {
            num = Integer.valueOf(arguments.getInt("DisplayLogoResId", 0));
        } else {
            num = null;
        }
        LayoutUtils.updateImageViewWithLogo((ImageView) inflate.findViewById(R.id.survey_prompt_banner_logo), num);
        if (bundle != null) {
            charSequence = bundle.getCharSequence("QuestionTextFromHtml");
        }
        if (charSequence == null) {
            if (this.question.questionHtml_.isEmpty()) {
                str = this.question.questionText_;
            } else {
                str = this.question.questionHtml_;
            }
            charSequence = HtmlCompat.Api24Impl.fromHtml(str, 0);
        }
        TextView textView = (TextView) inflate.findViewById(R.id.survey_question_text);
        this.questionTextView = textView;
        textView.setText(charSequence);
        this.questionTextView.setContentDescription(charSequence.toString());
        RatingView ratingView = new RatingView(getContext());
        Survey$Question survey$Question = this.question;
        if (survey$Question.questionCase_ == 6) {
            survey$Rating = (Survey$Rating) survey$Question.question_;
        } else {
            survey$Rating = Survey$Rating.DEFAULT_INSTANCE;
        }
        ratingView.setUpRatingView(survey$Rating);
        ratingView.onRatingClickListener = new RatingView.OnRatingClickListener() { // from class: com.google.android.libraries.surveys.internal.view.RatingFragment$$ExternalSyntheticLambda0
            @Override // com.google.android.libraries.surveys.internal.view.RatingView.OnRatingClickListener
            public final void onClickRating(int i) {
                RatingFragment ratingFragment = RatingFragment.this;
                ratingFragment.selectedResponse = Integer.toString(i);
                ratingFragment.selectedIndex = i;
                ratingFragment.questionMetrics.markAsAnswered();
                int forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(ratingFragment.question.questionType_);
                if (forNumber$ar$edu$83e82a14_0 == 0) {
                    forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0;
                }
                int i2 = Survey$Question.QuestionType.QUESTION_TYPE_RATING$ar$edu;
                SurveyActivityInterface activityIfRunning = ratingFragment.getActivityIfRunning();
                if (activityIfRunning == null) {
                    Log.w("SurveyRatingFragment", "Activity was null, finishing or destroyed while attempting to navigate to the next page. Likely the user rotated the device before the Runnable executed.");
                } else if (forNumber$ar$edu$83e82a14_0 == i2) {
                    activityIfRunning.nextPageOrSubmit();
                } else {
                    activityIfRunning.onQuestionProgressableChanged(ratingFragment.isResponseSatisfactory(), ratingFragment);
                }
            }
        };
        ((ViewGroup) inflate.findViewById(R.id.survey_rating_container)).addView(ratingView);
        return inflate;
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void onPageScrolledIntoView() {
        TextView textView;
        this.questionMetrics.markAsShown();
        if (getActivityIfRunning() != null) {
            getActivityIfRunning().showNextButton$ar$ds();
        }
        getActivityIfRunning().onQuestionProgressableChanged(isResponseSatisfactory(), this);
        if (SurveyUtils.isInTalkBackMode(getContext()) && (textView = this.questionTextView) != null) {
            textView.requestFocus();
            this.questionTextView.sendAccessibilityEvent(8);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putString("SelectedResponse", this.selectedResponse);
        bundle.putParcelable("QuestionMetrics", this.questionMetrics);
        TextView textView = this.questionTextView;
        if (textView != null) {
            bundle.putCharSequence("QuestionTextFromHtml", textView.getText());
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void updateQuestionText(String str) {
        Spanned fromHtml;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext)) || (getContext() != null && this.questionTextView != null)) {
            fromHtml = HtmlCompat.Api24Impl.fromHtml(str, 0);
            this.questionTextView.setText(fromHtml);
            this.questionTextView.setContentDescription(fromHtml.toString());
        }
    }
}
