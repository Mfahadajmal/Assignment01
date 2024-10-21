package com.google.android.accessibility.talkback.labeling;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.accessibility.talkback.labeling.LabelManagerPackageActivity;
import com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.trainingcommon.content.ExitBanner;
import com.google.android.accessibility.talkback.trainingcommon.content.PageButton;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentActionProviderImpl;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentHelper;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.libraries.surveys.internal.common.view.BaseView;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.libraries.surveys.internal.view.EmbeddedSurveyFragment;
import com.google.android.libraries.surveys.internal.view.PromptDialogDelegate;
import com.google.android.libraries.surveys.internal.view.RatingView;
import com.google.android.libraries.surveys.internal.view.SurveyActivityImpl;
import com.google.android.marvin.talkback.R;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.MonthsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.base.VerifyException;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.protobuf.Internal;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$AnswerChoices;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$MultiSelect;
import com.google.scone.proto.Survey$Question;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ Object LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
    private final /* synthetic */ int switching_field;

    public LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(MaterialCalendar materialCalendar, MonthsPagerAdapter monthsPagerAdapter, int i) {
        this.switching_field = i;
        this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0 = monthsPagerAdapter;
        this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1 = materialCalendar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v12, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v7, types: [android.os.Parcelable, java.lang.Object] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1;
        Survey$MultiSelect survey$MultiSelect;
        int i;
        int i2 = 0;
        switch (this.switching_field) {
            case 0:
                ((LabelManagerSummaryActivity.PackageLabelInfoAdapter.PackageLabelViewHolder) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0).context.startActivity((Intent) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1);
                return;
            case 1:
                long j = ((Label) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0).mId;
                Object obj = this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                WindowTrackerFactory.editLabel$ar$class_merging$ar$class_merging(((LabelManagerPackageActivity.LabelAdapter) obj).this$0, j, false, null, new HapticPatternParser$$ExternalSyntheticLambda1(obj));
                return;
            case 2:
                ExitBanner exitBanner = (ExitBanner) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0;
                WindowTrackerFactory windowTrackerFactory = exitBanner.metricStore$ar$class_merging$ar$class_merging;
                if (windowTrackerFactory != null) {
                    windowTrackerFactory.onTutorialEvent(12);
                }
                if (exitBanner.firstTapPerformed && (hapticPatternParser$$ExternalSyntheticLambda1 = exitBanner.requestDisableTalkBack$ar$class_merging$ar$class_merging) != null) {
                    exitBanner.firstTapPerformed = false;
                    try {
                        TrainingActivity trainingActivity = (TrainingActivity) ((Fragment) hapticPatternParser$$ExternalSyntheticLambda1.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getActivity();
                        trainingActivity.sendMessageToService(Message.obtain((Handler) null, 6));
                        new Handler().postDelayed(new TrainingActivity$$ExternalSyntheticLambda1(trainingActivity, 0), 1000L);
                        return;
                    } catch (InterruptedException e) {
                        throw new VerifyException(e);
                    }
                }
                Object obj2 = this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                exitBanner.firstTapPerformed = true;
                ((Button) obj2).setText(R.string.tap_again_to_turn_off);
                return;
            case 3:
                Context context = (Context) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                Toast.makeText(context, context.getString(R.string.activated_view, context.getString(((PageButton) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0).textResId)), 1).show();
                return;
            case 4:
                ((PageButton) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0).buttonOnClickListener.accept(this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1);
                return;
            case 5:
                MddDebugMainFragmentActionProviderImpl mddDebugMainFragmentActionProviderImpl = (MddDebugMainFragmentActionProviderImpl) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                OptionalMethod optionalMethod = mddDebugMainFragmentActionProviderImpl.futureRegistry$ar$class_merging$ar$class_merging;
                ?? r1 = this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0;
                optionalMethod.listen$ar$class_merging$cca55420_0$ar$class_merging(OptionalMethod.ignoringValueFuture$ar$class_merging$ar$class_merging(mddDebugMainFragmentActionProviderImpl.performAction((MddDebugMainFragmentHelper.ActionInfo) r1)), mddDebugMainFragmentActionProviderImpl.mainFragmentActionCallback, r1);
                return;
            case 6:
                Stopwatch stopwatch = new Stopwatch();
                PromptDialogDelegate promptDialogDelegate = (PromptDialogDelegate) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                promptDialogDelegate.transmitOtherAccess(promptDialogDelegate.themeContext, promptDialogDelegate.triggerId, promptDialogDelegate.session, SurveyUtils.isPiiCollectionEnabled(promptDialogDelegate.surveyPayload));
                promptDialogDelegate.dialogFragment.dismissAllowingStateLoss();
                DefaultExperimentTokenDecorator.reportUserEventForCloseButtonClicked(stopwatch, promptDialogDelegate.themeContext, (String) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0);
                return;
            case 7:
                ((PromptDialogDelegate) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1).singleSelectAnswerComplete((Survey$Question) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0);
                return;
            case 8:
                PromptDialogDelegate promptDialogDelegate2 = (PromptDialogDelegate) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                String str = promptDialogDelegate2.openTextResponseAnswer;
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.DEFAULT_INSTANCE.createBuilder();
                boolean isShown = promptDialogDelegate2.questionMetrics.isShown();
                Object obj3 = this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0;
                if (isShown) {
                    String nullToEmpty = ContextDataProvider.nullToEmpty(str);
                    SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.OpenTextAnswer.DEFAULT_INSTANCE.createBuilder();
                    builder2.copyOnWrite();
                    ((Survey$Event.QuestionAnswered.OpenTextAnswer) builder2.instance).answer_ = nullToEmpty;
                    Survey$Event.QuestionAnswered.OpenTextAnswer openTextAnswer = (Survey$Event.QuestionAnswered.OpenTextAnswer) builder2.build();
                    int i3 = ((Survey$Question) obj3).questionOrdinal_;
                    builder.copyOnWrite();
                    ((Survey$Event.QuestionAnswered) builder.instance).questionOrdinal_ = i3;
                    builder.copyOnWrite();
                    Survey$Event.QuestionAnswered questionAnswered = (Survey$Event.QuestionAnswered) builder.instance;
                    openTextAnswer.getClass();
                    questionAnswered.answer_ = openTextAnswer;
                    questionAnswered.answerCase_ = 5;
                }
                Survey$Event.QuestionAnswered questionAnswered2 = (Survey$Event.QuestionAnswered) builder.build();
                if (questionAnswered2 != null) {
                    promptDialogDelegate2.answer.response = questionAnswered2;
                }
                promptDialogDelegate2.checkQuestionBranching((Survey$Question) obj3);
                promptDialogDelegate2.handleQuestionAnswered();
                return;
            case 9:
                Stopwatch stopwatch2 = new Stopwatch();
                PromptDialogDelegate promptDialogDelegate3 = (PromptDialogDelegate) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                promptDialogDelegate3.transmitOtherAccess(promptDialogDelegate3.themeContext, promptDialogDelegate3.triggerId, promptDialogDelegate3.session, SurveyUtils.isPiiCollectionEnabled(promptDialogDelegate3.surveyPayload));
                promptDialogDelegate3.dialogFragment.dismissAllowingStateLoss();
                DefaultExperimentTokenDecorator.reportUserEventForCloseButtonClicked(stopwatch2, promptDialogDelegate3.themeContext, (String) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0);
                return;
            case 10:
                PromptDialogDelegate promptDialogDelegate4 = (PromptDialogDelegate) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                SurveyInternalEvent surveyInternalEvent = promptDialogDelegate4.getSurveyInternalEvent();
                if (surveyInternalEvent != null) {
                    ArrayList arrayList = new ArrayList();
                    int i4 = 0;
                    while (true) {
                        boolean[] zArr = (boolean[]) promptDialogDelegate4.multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass;
                        if (i4 < zArr.length) {
                            if (zArr[i4]) {
                                arrayList.add(Integer.valueOf(i4));
                            }
                            i4++;
                        } else {
                            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging;
                            ImmutableList.copyOf((Collection) arrayList);
                            shadowDelegateImpl.didAnswerMultiSelectQuestion$ar$ds(surveyInternalEvent);
                        }
                    }
                }
                Object obj4 = this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0;
                RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = promptDialogDelegate4.multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging;
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.DEFAULT_INSTANCE.createBuilder();
                if (promptDialogDelegate4.questionMetrics.isShown()) {
                    SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.MultipleSelectAnswer.DEFAULT_INSTANCE.createBuilder();
                    Survey$Question survey$Question = (Survey$Question) obj4;
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
                    while (true) {
                        boolean[] zArr2 = (boolean[]) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass;
                        if (i2 < zArr2.length) {
                            if (zArr2[i2]) {
                                Object obj5 = ((Survey$AnswerChoice) protobufList.get(i2)).text_;
                                int forNumber$ar$edu$ac62307f_0 = Survey$AnswerChoice.AnswerType.forNumber$ar$edu$ac62307f_0(((Survey$AnswerChoice) protobufList.get(i2)).answerType_);
                                if (forNumber$ar$edu$ac62307f_0 == 0) {
                                    forNumber$ar$edu$ac62307f_0 = Survey$AnswerChoice.AnswerType.UNRECOGNIZED$ar$edu$57c31185_0;
                                }
                                if (forNumber$ar$edu$ac62307f_0 == Survey$AnswerChoice.AnswerType.ANSWER_TYPE_WRITE_IN$ar$edu && !TextUtils.isEmpty(remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider)) {
                                    obj5 = remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider;
                                }
                                SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE.createBuilder();
                                int i5 = ((Survey$AnswerChoice) protobufList.get(i2)).answerOrdinal_;
                                builder5.copyOnWrite();
                                ((Survey$Event.QuestionAnswered.Selection) builder5.instance).answerOrdinal_ = i5;
                                builder5.copyOnWrite();
                                Survey$Event.QuestionAnswered.Selection selection = (Survey$Event.QuestionAnswered.Selection) builder5.instance;
                                obj5.getClass();
                                selection.text_ = (String) obj5;
                                int forNumber$ar$edu$ac62307f_02 = Survey$AnswerChoice.AnswerType.forNumber$ar$edu$ac62307f_0(((Survey$AnswerChoice) protobufList.get(i2)).answerType_);
                                if (forNumber$ar$edu$ac62307f_02 == 0) {
                                    forNumber$ar$edu$ac62307f_02 = Survey$AnswerChoice.AnswerType.UNRECOGNIZED$ar$edu$57c31185_0;
                                }
                                if (forNumber$ar$edu$ac62307f_02 != 0) {
                                    int i6 = forNumber$ar$edu$ac62307f_02 - 2;
                                    if (i6 != 1) {
                                        if (i6 != 2) {
                                            if (i6 != 3) {
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
                                    builder5.copyOnWrite();
                                    ((Survey$Event.QuestionAnswered.Selection) builder5.instance).answerType_ = Survey$Event.QuestionAnswered.Selection.AnswerType.getNumber$ar$edu$def17366_0(i);
                                    builder4.addAnswer$ar$ds((Survey$Event.QuestionAnswered.Selection) builder5.build());
                                    promptDialogDelegate4.questionMetrics.markAsAnswered();
                                } else {
                                    throw null;
                                }
                            }
                            int i7 = survey$Question.questionOrdinal_;
                            builder3.copyOnWrite();
                            ((Survey$Event.QuestionAnswered) builder3.instance).questionOrdinal_ = i7;
                            Survey$Event.QuestionAnswered.MultipleSelectAnswer multipleSelectAnswer = (Survey$Event.QuestionAnswered.MultipleSelectAnswer) builder4.build();
                            builder3.copyOnWrite();
                            Survey$Event.QuestionAnswered questionAnswered3 = (Survey$Event.QuestionAnswered) builder3.instance;
                            multipleSelectAnswer.getClass();
                            questionAnswered3.answer_ = multipleSelectAnswer;
                            questionAnswered3.answerCase_ = 3;
                            i2++;
                        }
                    }
                }
                Survey$Event.QuestionAnswered questionAnswered4 = (Survey$Event.QuestionAnswered) builder3.build();
                if (questionAnswered4 != null) {
                    promptDialogDelegate4.answer.response = questionAnswered4;
                }
                promptDialogDelegate4.checkQuestionBranching((Survey$Question) obj4);
                promptDialogDelegate4.handleQuestionAnswered();
                return;
            case 11:
                RatingView.removeOnClickListenersAndDisableClickEvents((ViewGroup) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1);
                new Handler().postDelayed(new FlagStore$$ExternalSyntheticLambda3(this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0, 7), 250L);
                return;
            case 12:
                RatingView.removeOnClickListenersAndDisableClickEvents((ViewGroup) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1);
                new Handler().postDelayed(new FlagStore$$ExternalSyntheticLambda3(this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0, 8), 250L);
                return;
            case 13:
                Stopwatch stopwatch3 = new Stopwatch();
                SurveyActivityImpl surveyActivityImpl = (SurveyActivityImpl) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                surveyActivityImpl.setAnswerTypeAndTransmit$ar$edu(6);
                SurveyUtils.hideSoftKeyboard(surveyActivityImpl.surveyContainer);
                surveyActivityImpl.activity.finish();
                DefaultExperimentTokenDecorator.reportUserEventForCloseButtonClicked(stopwatch3, surveyActivityImpl.activity, (String) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0);
                return;
            case 14:
                Stopwatch stopwatch4 = new Stopwatch();
                SurveyActivityImpl surveyActivityImpl2 = (SurveyActivityImpl) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                surveyActivityImpl2.nextPageOrSubmit();
                DefaultExperimentTokenDecorator.reportUserEventForNextButtonClicked(stopwatch4, surveyActivityImpl2.activity, (String) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0);
                return;
            case 15:
                Stopwatch stopwatch5 = new Stopwatch();
                BaseView baseView = (BaseView) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                baseView.setAnswerTypeAndTransmit$ar$edu(6);
                SurveyUtils.hideSoftKeyboard(baseView.surveyContainer);
                Iterator it = baseView.getListeners().iterator();
                while (it.hasNext()) {
                    ((EmbeddedSurveyFragment) it.next()).onSurveyClosed();
                }
                DefaultExperimentTokenDecorator.reportUserEventForCloseButtonClicked(stopwatch5, baseView.getContext(), (String) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0);
                return;
            case 16:
                Stopwatch stopwatch6 = new Stopwatch();
                BaseView baseView2 = (BaseView) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1;
                baseView2.nextPageOrSubmit();
                DefaultExperimentTokenDecorator.reportUserEventForNextButtonClicked(stopwatch6, baseView2.getContext(), (String) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0);
                return;
            case 17:
                int findLastVisibleItemPosition = ((MaterialCalendar) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1).getLayoutManager().findLastVisibleItemPosition() - 1;
                if (findLastVisibleItemPosition >= 0) {
                    ((MaterialCalendar) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1).setCurrentMonth(((MonthsPagerAdapter) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0).getPageMonth(findLastVisibleItemPosition));
                    return;
                }
                return;
            default:
                int findFirstVisibleItemPosition = ((MaterialCalendar) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1).getLayoutManager().findFirstVisibleItemPosition() + 1;
                if (findFirstVisibleItemPosition < ((MaterialCalendar) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1).recyclerView.mAdapter.getItemCount()) {
                    ((MaterialCalendar) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1).setCurrentMonth(((MonthsPagerAdapter) this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0).getPageMonth(findFirstVisibleItemPosition));
                    return;
                }
                return;
        }
    }

    public /* synthetic */ LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0 = obj;
        this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1 = obj2;
    }

    public /* synthetic */ LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(Object obj, Object obj2, int i, byte[] bArr) {
        this.switching_field = i;
        this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$1 = obj;
        this.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0$ar$f$0 = obj2;
    }
}
