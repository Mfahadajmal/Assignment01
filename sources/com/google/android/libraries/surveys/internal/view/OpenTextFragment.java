package com.google.android.libraries.surveys.internal.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.model.QuestionMetrics;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$OpenText;
import com.google.scone.proto.Survey$Question;
import googledata.experiments.mobile.surveys_android.features.HatsV1M17Bugfixes;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OpenTextFragment extends ScrollableAnswerFragment {
    public String openTextResponseAnswer;
    private QuestionMetrics questionMetrics;

    private final OpenTextView createOpenTextViewLayout(String str) {
        Survey$OpenText survey$OpenText;
        OpenTextView openTextView = new OpenTextView(getContext());
        ((EditText) openTextView.findViewById(R.id.survey_open_text)).setText(str);
        Survey$Question survey$Question = this.question;
        if (survey$Question.questionCase_ == 7) {
            survey$OpenText = (Survey$OpenText) survey$Question.question_;
        } else {
            survey$OpenText = Survey$OpenText.DEFAULT_INSTANCE;
        }
        openTextView.setUpOpenTextView(survey$OpenText);
        openTextView.onOpenTextResponseListener = new PromptDialogDelegate$$ExternalSyntheticLambda15(this, 1);
        return openTextView;
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final Survey$Event.QuestionAnswered computeQuestionResponse() {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.DEFAULT_INSTANCE.createBuilder();
        if (this.questionMetrics.isShown()) {
            this.questionMetrics.markAsAnswered();
            String nullToEmpty = ContextDataProvider.nullToEmpty(this.openTextResponseAnswer);
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.OpenTextAnswer.DEFAULT_INSTANCE.createBuilder();
            builder2.copyOnWrite();
            ((Survey$Event.QuestionAnswered.OpenTextAnswer) builder2.instance).answer_ = nullToEmpty;
            Survey$Event.QuestionAnswered.OpenTextAnswer openTextAnswer = (Survey$Event.QuestionAnswered.OpenTextAnswer) builder2.build();
            int i = this.question.questionOrdinal_;
            builder.copyOnWrite();
            ((Survey$Event.QuestionAnswered) builder.instance).questionOrdinal_ = i;
            builder.copyOnWrite();
            Survey$Event.QuestionAnswered questionAnswered = (Survey$Event.QuestionAnswered) builder.instance;
            openTextAnswer.getClass();
            questionAnswered.answer_ = openTextAnswer;
            questionAnswered.answerCase_ = 5;
        }
        return (Survey$Event.QuestionAnswered) builder.build();
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment
    public final View createScrollViewContents() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.survey_scrollable_answer_content_container, (ViewGroup) null).findViewById(R.id.survey_answers_container);
        linearLayout.addView(createOpenTextViewLayout(""));
        return linearLayout;
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment
    public final String getQuestionText() {
        if (this.question.questionHtml_.isEmpty()) {
            return this.question.questionText_;
        }
        return this.question.questionHtml_;
    }

    @Override // android.support.v4.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivityIfRunning().onQuestionProgressableChanged(true, this);
    }

    @Override // android.support.v4.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        View view;
        super.onConfigurationChanged(configuration);
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (HatsV1M17Bugfixes.INSTANCE.get().openTextLanscapeIssueFix(getContext()) && configuration.orientation == 2 && (view = getView()) != null) {
            EditText editText = (EditText) view.findViewById(R.id.survey_open_text);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.survey_answers_container);
            if (linearLayout != null && editText != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(createOpenTextViewLayout(editText.getText().toString()));
            }
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment, android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.questionMetrics = new QuestionMetrics();
        } else {
            this.questionMetrics = (QuestionMetrics) bundle.getParcelable("QuestionMetrics");
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment, com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void onPageScrolledIntoView() {
        super.onPageScrolledIntoView();
        this.questionMetrics.markAsShown();
        getActivityIfRunning().onQuestionProgressableChanged(true, this);
    }

    @Override // com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment, android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("QuestionMetrics", this.questionMetrics);
    }
}
