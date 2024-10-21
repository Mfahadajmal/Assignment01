package com.google.android.libraries.surveys.internal.common.view;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.model.Answer;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.libraries.surveys.internal.view.EmbeddedSurveyFragment;
import com.google.android.libraries.surveys.internal.view.SurveyActivityInterface;
import com.google.android.libraries.surveys.internal.view.SurveyQuestionsContainerViewArgs;
import com.google.android.libraries.surveys.internal.view.SurveyViewPager;
import com.google.android.libraries.surveys.internal.view.SurveyViewPagerAdapter;
import com.google.android.marvin.talkback.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.protobuf.MapEntryLite$Metadata;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$AnswerChoices;
import com.google.scone.proto.Survey$BranchingDestination;
import com.google.scone.proto.Survey$Completion;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$Invitation;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$Question;
import com.google.scone.proto.Survey$SingleSelect;
import com.google.scone.proto.Survey$TextSubstitution;
import com.google.scone.proto.SurveyServiceGrpc;
import googledata.experiments.mobile.surveys_android.features.HatsQuestionnaireBranching;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Bugfixes;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Features;
import io.grpc.okhttp.OutboundFlowController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BaseView {
    public final Answer answer;
    private final MapEntryLite$Metadata answerTransmitter$ar$class_merging$ar$class_merging;
    public final SurveyQuestionsContainerViewArgs args;
    private ImmutableMap branchingMap;
    public final LayoutInflater inflater;
    public boolean isSubmitting;
    public final Set listeners;
    public final MaterialCardView overallContainer;
    public View rootView;
    public final Bundle singleSelectOrdinalAnswerMappings;
    public final FragmentManager supportFragmentManager;
    public final SurveyActivityInterface surveyActivityInterface;
    public final LinearLayout surveyContainer;
    public final Survey$Payload surveyPayload;
    public SurveyViewPager surveyViewPager;

    public BaseView() {
        this.listeners = new HashSet();
    }

    private final void proceedToNormalFlow() {
        if (!this.surveyViewPager.isLastQuestion() && CurrentProcess.shouldContinueSurveyDueToScreenIn(getCurrentQuestionIndexForSurveyPayload(), this.surveyPayload, this.answer)) {
            showQuestion(this.surveyViewPager.mCurItem + 1);
        } else {
            showThankYouMessage();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void showQuestion(int i) {
        String str;
        Survey$TextSubstitution.AnswerPipe answerPipe;
        Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext))) {
            showNextButton(isSurveyControlRequired());
        }
        int forNumber$ar$edu$f9ea4f52_0 = Survey$Event.QuestionAnswered.AnswerCase.forNumber$ar$edu$f9ea4f52_0(getSurveyResponse().answerCase_);
        int i2 = Survey$Event.QuestionAnswered.AnswerCase.SINGLE_SELECTION$ar$edu;
        if (forNumber$ar$edu$f9ea4f52_0 != 0) {
            if (forNumber$ar$edu$f9ea4f52_0 == i2) {
                Bundle bundle = this.singleSelectOrdinalAnswerMappings;
                String valueOf = String.valueOf(getSurveyResponse().questionOrdinal_);
                Survey$Event.QuestionAnswered surveyResponse = getSurveyResponse();
                if (surveyResponse.answerCase_ == 2) {
                    singleSelectAnswer = (Survey$Event.QuestionAnswered.SingleSelectAnswer) surveyResponse.answer_;
                } else {
                    singleSelectAnswer = Survey$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE;
                }
                Survey$Event.QuestionAnswered.Selection selection = singleSelectAnswer.answer_;
                if (selection == null) {
                    selection = Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE;
                }
                bundle.putString(valueOf, selection.text_);
            }
            setAnswerTypeAndTransmit$ar$edu(5);
            this.surveyViewPager.navigateToPage(i);
            SurveyViewPager surveyViewPager = this.surveyViewPager;
            if (surveyViewPager == null || !surveyViewPager.isThankYouDisplayed()) {
                Survey$Payload survey$Payload = this.surveyPayload;
                Survey$Question survey$Question = (Survey$Question) survey$Payload.question_.get(getCurrentQuestionIndexForSurveyPayload());
                if (survey$Question.questionHtml_.isEmpty()) {
                    str = survey$Question.questionText_;
                } else {
                    str = survey$Question.questionHtml_;
                }
                int size = survey$Question.textSubstitution_.size();
                String[] strArr = new String[size];
                String[] strArr2 = new String[size];
                for (int i3 = 0; i3 < size; i3++) {
                    Survey$TextSubstitution survey$TextSubstitution = (Survey$TextSubstitution) survey$Question.textSubstitution_.get(i3);
                    if (SurveyServiceGrpc.forNumber$ar$edu(survey$TextSubstitution.replacementOperationCase_) == 3) {
                        if (survey$TextSubstitution.replacementOperationCase_ == 2) {
                            answerPipe = (Survey$TextSubstitution.AnswerPipe) survey$TextSubstitution.replacementOperation_;
                        } else {
                            answerPipe = Survey$TextSubstitution.AnswerPipe.DEFAULT_INSTANCE;
                        }
                        Bundle bundle2 = this.singleSelectOrdinalAnswerMappings;
                        int i4 = answerPipe.fromQuestionOrdinal_;
                        String string = bundle2.getString(String.valueOf(i4));
                        if (string != null) {
                            strArr[i3] = survey$TextSubstitution.matchText_;
                            strArr2[i3] = string;
                        } else {
                            Log.e("SurveyContainer", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i4, "No single-select question with ordinal ", " was found."));
                        }
                    }
                }
                this.surveyViewPager.updateQuestionText(TextUtils.replace(str, strArr, strArr2).toString());
            }
            switchNextTextToSubmitIfNeeded();
            this.surveyViewPager.getCurrentItemFragment().getView().sendAccessibilityEvent(32);
            long j = SurveyUtils.TIMEOUT_MS;
            return;
        }
        throw null;
    }

    private final void showThankYouMessage() {
        long j = SurveyUtils.TIMEOUT_MS;
        setAnswerTypeAndTransmit$ar$edu(5);
        this.isSubmitting = true;
        setNextButtonEnabled(false);
        Iterator it = getListeners().iterator();
        while (it.hasNext()) {
            ((EmbeddedSurveyFragment) it.next()).onSurveyCompleted();
        }
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(HatsV1M3Features.enableUserPromptedSurveys(FlagsUtil.phenotypeContext))) {
            if (this.args.surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.CARD) {
                this.surveyViewPager.navigateToLastPage();
                return;
            }
            findViewById(R.id.survey_main_scroll_view).setVisibility(8);
            for (EmbeddedSurveyFragment embeddedSurveyFragment : getListeners()) {
                Survey$Completion survey$Completion = this.surveyPayload.completion_;
                if (survey$Completion == null) {
                    survey$Completion = Survey$Completion.DEFAULT_INSTANCE;
                }
                embeddedSurveyFragment.showSnackbarOnSurveyCompleted(survey$Completion.completionText_);
            }
            return;
        }
        this.surveyViewPager.navigateToLastPage();
    }

    public final View findViewById(int i) {
        return this.rootView.findViewById(i);
    }

    public final Context getContext() {
        return this.rootView.getContext();
    }

    public final int getCurrentQuestionIndexForSurveyPayload() {
        SurveyViewPager surveyViewPager = this.surveyViewPager;
        if (surveyViewPager != null) {
            int i = surveyViewPager.mCurItem;
            if (FlagsUtil.isBranchingEnabled()) {
                return i + this.args.startingQuestionIndex;
            }
            return i;
        }
        return 0;
    }

    public final Set getListeners() {
        return ImmutableSet.copyOf((Collection) this.listeners);
    }

    public final SurveyInternalEvent getSurveyInternalEvent() {
        AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent(null, null, null);
        aggregatedOnDeviceTextDetectionLogEvent.setSessionId$ar$ds(this.args.surveySession.sessionId_);
        aggregatedOnDeviceTextDetectionLogEvent.setTriggerId$ar$ds(this.args.triggerId);
        aggregatedOnDeviceTextDetectionLogEvent.setSurveyStyle$ar$ds(this.args.surveyStyle);
        return aggregatedOnDeviceTextDetectionLogEvent.m220build();
    }

    public final Survey$Event.QuestionAnswered getSurveyResponse() {
        return this.answer.response;
    }

    public final boolean isSurveyControlRequired() {
        return SurveyUtils.shouldDisplaySurveyControls(this.surveyPayload);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void nextPageOrSubmit() {
        Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer;
        Survey$SingleSelect survey$SingleSelect;
        Survey$SingleSelect survey$SingleSelect2;
        Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer2;
        Survey$Event.QuestionAnswered.RatingAnswer ratingAnswer;
        Survey$Event.QuestionAnswered.MultipleSelectAnswer multipleSelectAnswer;
        Survey$Event.QuestionAnswered.RatingAnswer ratingAnswer2;
        SurveyViewPager surveyViewPager = this.surveyViewPager;
        if (surveyViewPager != null && surveyViewPager.isFirstQuestion()) {
            Survey$Invitation survey$Invitation = this.surveyPayload.invitation_;
            if (survey$Invitation == null) {
                survey$Invitation = Survey$Invitation.DEFAULT_INSTANCE;
            }
            if (!survey$Invitation.showInvitation_) {
                setAnswerTypeAndTransmit$ar$edu(3);
            }
        }
        SurveyUtils.hideSoftKeyboard(this.surveyContainer);
        setLegalTextVisibility$ar$ds();
        SurveyInternalEvent surveyInternalEvent = getSurveyInternalEvent();
        int forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(((Survey$Question) this.surveyPayload.question_.get(getCurrentQuestionIndexForSurveyPayload())).questionType_);
        if (forNumber$ar$edu$83e82a14_0 == 0) {
            forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0;
        }
        if (forNumber$ar$edu$83e82a14_0 != 0) {
            int i = forNumber$ar$edu$83e82a14_0 - 2;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.didAnswerOpenTextQuestion(surveyInternalEvent);
                        }
                    } else {
                        Survey$Event.QuestionAnswered currentItemQuestionResponse = this.surveyViewPager.getCurrentItemQuestionResponse();
                        if (currentItemQuestionResponse.answerCase_ == 4) {
                            ratingAnswer2 = (Survey$Event.QuestionAnswered.RatingAnswer) currentItemQuestionResponse.answer_;
                        } else {
                            ratingAnswer2 = Survey$Event.QuestionAnswered.RatingAnswer.DEFAULT_INSTANCE;
                        }
                        Survey$Event.QuestionAnswered.Selection selection = ratingAnswer2.answer_;
                        if (selection == null) {
                            selection = Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE;
                        }
                        int i2 = selection.answerOrdinal_;
                        JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.didAnswerRatingQuestion$ar$ds(surveyInternalEvent);
                    }
                } else {
                    ArrayList arrayList = new ArrayList();
                    Survey$Event.QuestionAnswered currentItemQuestionResponse2 = this.surveyViewPager.getCurrentItemQuestionResponse();
                    if (currentItemQuestionResponse2.answerCase_ == 3) {
                        multipleSelectAnswer = (Survey$Event.QuestionAnswered.MultipleSelectAnswer) currentItemQuestionResponse2.answer_;
                    } else {
                        multipleSelectAnswer = Survey$Event.QuestionAnswered.MultipleSelectAnswer.DEFAULT_INSTANCE;
                    }
                    Iterator<E> it = multipleSelectAnswer.answer_.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Integer.valueOf(((Survey$Event.QuestionAnswered.Selection) it.next()).answerOrdinal_ - 1));
                    }
                    FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging;
                    ImmutableList.copyOf((Collection) arrayList);
                    shadowDelegateImpl.didAnswerMultiSelectQuestion$ar$ds(surveyInternalEvent);
                }
            } else {
                Survey$Event.QuestionAnswered currentItemQuestionResponse3 = this.surveyViewPager.getCurrentItemQuestionResponse();
                if (currentItemQuestionResponse3.answerCase_ == 2) {
                    singleSelectAnswer = (Survey$Event.QuestionAnswered.SingleSelectAnswer) currentItemQuestionResponse3.answer_;
                } else {
                    singleSelectAnswer = Survey$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE;
                }
                Survey$Event.QuestionAnswered.Selection selection2 = singleSelectAnswer.answer_;
                if (selection2 == null) {
                    selection2 = Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE;
                }
                int i3 = selection2.answerOrdinal_;
                JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.didAnswerSingleSelectQuestion$ar$ds(surveyInternalEvent);
            }
            CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (!FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext))) {
                Survey$Question survey$Question = (Survey$Question) this.surveyPayload.question_.get(getCurrentQuestionIndexForSurveyPayload());
                if (isSurveyControlRequired()) {
                    int forNumber$ar$edu$83e82a14_02 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(survey$Question.questionType_);
                    if (forNumber$ar$edu$83e82a14_02 == 0) {
                        forNumber$ar$edu$83e82a14_02 = Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0;
                    }
                    if (forNumber$ar$edu$83e82a14_02 == Survey$Question.QuestionType.QUESTION_TYPE_RATING$ar$edu) {
                        showNextButton(true);
                    }
                }
            }
            Survey$Event.QuestionAnswered currentItemQuestionResponse4 = this.surveyViewPager.getCurrentItemQuestionResponse();
            if (currentItemQuestionResponse4 != null) {
                this.answer.response = currentItemQuestionResponse4;
            }
            if (FlagsUtil.isBranchingEnabled()) {
                SurveyViewPager surveyViewPager2 = this.surveyViewPager;
                if (surveyViewPager2 == null) {
                    proceedToNormalFlow();
                    return;
                }
                Survey$Question survey$Question2 = surveyViewPager2.getCurrentItemFragment().question;
                Survey$Question.QuestionBranching questionBranching = survey$Question2.questionBranching_;
                if (questionBranching == null) {
                    questionBranching = Survey$Question.QuestionBranching.DEFAULT_INSTANCE;
                }
                if ((questionBranching.bitField0_ & 1) != 0) {
                    Survey$Question.QuestionBranching questionBranching2 = survey$Question2.questionBranching_;
                    if (questionBranching2 == null) {
                        questionBranching2 = Survey$Question.QuestionBranching.DEFAULT_INSTANCE;
                    }
                    Survey$BranchingDestination survey$BranchingDestination = questionBranching2.branchingDestination_;
                    if (survey$BranchingDestination == null) {
                        survey$BranchingDestination = Survey$BranchingDestination.DEFAULT_INSTANCE;
                    }
                    int forNumber$ar$edu$36e8454a_0 = Survey$BranchingDestination.DestinationType.forNumber$ar$edu$36e8454a_0(survey$BranchingDestination.destinationType_);
                    if (forNumber$ar$edu$36e8454a_0 == 0) {
                        forNumber$ar$edu$36e8454a_0 = Survey$BranchingDestination.DestinationType.UNRECOGNIZED$ar$edu$b8e6fbeb_0;
                    }
                    int i4 = Survey$BranchingDestination.DestinationType.DESTINATION_TYPE_COMPLETE_SURVEY$ar$edu;
                    if (forNumber$ar$edu$36e8454a_0 != 0) {
                        if (forNumber$ar$edu$36e8454a_0 == i4) {
                            showThankYouMessage();
                            return;
                        }
                    } else {
                        throw null;
                    }
                }
                CurrentProcess currentProcess2 = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                int i5 = 0;
                if (FlagsUtil.isFeatureEnabled(HatsQuestionnaireBranching.enableRatingQuestionnaireBranching(FlagsUtil.phenotypeContext))) {
                    int forNumber$ar$edu$83e82a14_03 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(survey$Question2.questionType_);
                    if (forNumber$ar$edu$83e82a14_03 == 0) {
                        forNumber$ar$edu$83e82a14_03 = Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0;
                    }
                    int i6 = Survey$Question.QuestionType.QUESTION_TYPE_RATING$ar$edu;
                    if (forNumber$ar$edu$83e82a14_03 != 0) {
                        if (forNumber$ar$edu$83e82a14_03 == i6) {
                            Survey$Event.QuestionAnswered currentItemQuestionResponse5 = this.surveyViewPager.getCurrentItemQuestionResponse();
                            if (currentItemQuestionResponse5.answerCase_ == 4) {
                                ratingAnswer = (Survey$Event.QuestionAnswered.RatingAnswer) currentItemQuestionResponse5.answer_;
                            } else {
                                ratingAnswer = Survey$Event.QuestionAnswered.RatingAnswer.DEFAULT_INSTANCE;
                            }
                            Survey$Event.QuestionAnswered.Selection selection3 = ratingAnswer.answer_;
                            if (selection3 == null) {
                                selection3 = Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE;
                            }
                            int nextQuestionOrdinal = new OutboundFlowController.WriteStatus(null).getNextQuestionOrdinal(this.branchingMap, this.surveyPayload.question_.size(), selection3.answerOrdinal_, survey$Question2);
                            if (nextQuestionOrdinal == -1) {
                                proceedToNormalFlow();
                                return;
                            }
                            if (nextQuestionOrdinal - 1 != this.surveyPayload.question_.size()) {
                                SurveyViewPagerAdapter surveyViewPagerAdapter = (SurveyViewPagerAdapter) this.surveyViewPager.mAdapter;
                                if (surveyViewPagerAdapter != null) {
                                    i5 = surveyViewPagerAdapter.getQuestionOrdinalCardIndex(nextQuestionOrdinal);
                                }
                                showQuestion(i5);
                                return;
                            }
                            showThankYouMessage();
                            return;
                        }
                    } else {
                        throw null;
                    }
                }
                CurrentProcess currentProcess3 = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                if (FlagsUtil.isFeatureEnabled(HatsQuestionnaireBranching.enableQuestionnaireBranching(FlagsUtil.phenotypeContext))) {
                    int forNumber$ar$edu$83e82a14_04 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(survey$Question2.questionType_);
                    if (forNumber$ar$edu$83e82a14_04 == 0) {
                        forNumber$ar$edu$83e82a14_04 = Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0;
                    }
                    int i7 = Survey$Question.QuestionType.QUESTION_TYPE_SINGLE_SELECT$ar$edu;
                    if (forNumber$ar$edu$83e82a14_04 != 0) {
                        if (forNumber$ar$edu$83e82a14_04 == i7) {
                            Survey$AnswerChoice survey$AnswerChoice = Survey$AnswerChoice.DEFAULT_INSTANCE;
                            if (survey$Question2.questionCase_ == 4) {
                                survey$SingleSelect = (Survey$SingleSelect) survey$Question2.question_;
                            } else {
                                survey$SingleSelect = Survey$SingleSelect.DEFAULT_INSTANCE;
                            }
                            Survey$AnswerChoices survey$AnswerChoices = survey$SingleSelect.answerChoices_;
                            if (survey$AnswerChoices == null) {
                                survey$AnswerChoices = Survey$AnswerChoices.DEFAULT_INSTANCE;
                            }
                            Iterator<E> it2 = survey$AnswerChoices.answerChoice_.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                Survey$AnswerChoice survey$AnswerChoice2 = (Survey$AnswerChoice) it2.next();
                                int i8 = survey$AnswerChoice2.answerOrdinal_;
                                Survey$Event.QuestionAnswered currentItemQuestionResponse6 = this.surveyViewPager.getCurrentItemQuestionResponse();
                                if (currentItemQuestionResponse6.answerCase_ == 2) {
                                    singleSelectAnswer2 = (Survey$Event.QuestionAnswered.SingleSelectAnswer) currentItemQuestionResponse6.answer_;
                                } else {
                                    singleSelectAnswer2 = Survey$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE;
                                }
                                Survey$Event.QuestionAnswered.Selection selection4 = singleSelectAnswer2.answer_;
                                if (selection4 == null) {
                                    selection4 = Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE;
                                }
                                if (i8 == selection4.answerOrdinal_) {
                                    survey$AnswerChoice = survey$AnswerChoice2;
                                    break;
                                }
                            }
                            if (survey$Question2.questionCase_ == 4) {
                                survey$SingleSelect2 = (Survey$SingleSelect) survey$Question2.question_;
                            } else {
                                survey$SingleSelect2 = Survey$SingleSelect.DEFAULT_INSTANCE;
                            }
                            if ((survey$SingleSelect2.bitField0_ & 1) != 0 && (survey$AnswerChoice.bitField0_ & 1) != 0) {
                                Survey$BranchingDestination survey$BranchingDestination2 = survey$AnswerChoice.branchingDestination_;
                                if (survey$BranchingDestination2 == null) {
                                    survey$BranchingDestination2 = Survey$BranchingDestination.DEFAULT_INSTANCE;
                                }
                                int forNumber$ar$edu$36e8454a_02 = Survey$BranchingDestination.DestinationType.forNumber$ar$edu$36e8454a_0(survey$BranchingDestination2.destinationType_);
                                if (forNumber$ar$edu$36e8454a_02 == 0) {
                                    forNumber$ar$edu$36e8454a_02 = Survey$BranchingDestination.DestinationType.UNRECOGNIZED$ar$edu$b8e6fbeb_0;
                                }
                                int i9 = forNumber$ar$edu$36e8454a_02 - 2;
                                if (forNumber$ar$edu$36e8454a_02 != 0) {
                                    if (i9 != 2) {
                                        if (i9 != 3) {
                                            proceedToNormalFlow();
                                            return;
                                        } else {
                                            showThankYouMessage();
                                            return;
                                        }
                                    }
                                    Survey$BranchingDestination survey$BranchingDestination3 = survey$AnswerChoice.branchingDestination_;
                                    if (survey$BranchingDestination3 == null) {
                                        survey$BranchingDestination3 = Survey$BranchingDestination.DEFAULT_INSTANCE;
                                    }
                                    String str = survey$BranchingDestination3.toBranchingGroup_;
                                    SurveyViewPagerAdapter surveyViewPagerAdapter2 = (SurveyViewPagerAdapter) this.surveyViewPager.mAdapter;
                                    if (surveyViewPagerAdapter2 != null && this.branchingMap.containsKey(str)) {
                                        i5 = surveyViewPagerAdapter2.getQuestionOrdinalCardIndex(((Integer) this.branchingMap.get(str)).intValue());
                                    }
                                    showQuestion(i5);
                                    return;
                                }
                                throw null;
                            }
                            proceedToNormalFlow();
                            return;
                        }
                    } else {
                        throw null;
                    }
                }
                proceedToNormalFlow();
                return;
            }
            proceedToNormalFlow();
            return;
        }
        throw null;
    }

    public final /* bridge */ /* synthetic */ void registerListener$ar$class_merging$599da675_0(EmbeddedSurveyFragment embeddedSurveyFragment) {
        this.listeners.add(embeddedSurveyFragment);
    }

    public final void setAnswerTypeAndTransmit$ar$edu(int i) {
        Answer answer = this.answer;
        answer.answerType$ar$edu = i;
        this.answerTransmitter$ar$class_merging$ar$class_merging.transmit(answer, SurveyUtils.isPiiCollectionEnabled(this.surveyPayload));
    }

    public final void setLegalTextVisibility$ar$ds() {
        findViewById(R.id.survey_controls_divider).setVisibility(8);
        findViewById(R.id.survey_controls_legal_text).setVisibility(8);
    }

    public final void setNextButtonEnabled(boolean z) {
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.survey_next);
        if (materialButton != null && materialButton.isEnabled() != z) {
            materialButton.setEnabled(z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setUpLegalText(java.lang.String r8) {
        /*
            r7 = this;
            com.google.android.libraries.surveys.internal.view.SurveyActivityImpl$$ExternalSyntheticLambda2 r6 = new com.google.android.libraries.surveys.internal.view.SurveyActivityImpl$$ExternalSyntheticLambda2
            r0 = 2
            r6.<init>(r7, r8, r0)
            com.google.scone.proto.Survey$Payload r1 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r1 = r1.legalSettings_
            if (r1 != 0) goto Le
            com.google.scone.proto.Survey$LegalSettings r1 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        Le:
            int r1 = r1.bitField0_
            r1 = r1 & 1
            r2 = 0
            if (r1 == 0) goto L31
            com.google.scone.proto.Survey$Payload r1 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r1 = r1.legalSettings_
            if (r1 != 0) goto L1d
            com.google.scone.proto.Survey$LegalSettings r1 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        L1d:
            java.lang.String r1 = r1.customEntityName_
            int r1 = r1.length()
            if (r1 <= 0) goto L31
            com.google.scone.proto.Survey$Payload r1 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r1 = r1.legalSettings_
            if (r1 != 0) goto L2d
            com.google.scone.proto.Survey$LegalSettings r1 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        L2d:
            java.lang.String r1 = r1.customEntityName_
            r3 = r1
            goto L32
        L31:
            r3 = r2
        L32:
            com.google.scone.proto.Survey$Payload r1 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r1 = r1.legalSettings_
            if (r1 != 0) goto L3a
            com.google.scone.proto.Survey$LegalSettings r1 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        L3a:
            int r1 = r1.bitField0_
            r0 = r0 & r1
            if (r0 == 0) goto L5b
            com.google.scone.proto.Survey$Payload r0 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r0 = r0.legalSettings_
            if (r0 != 0) goto L47
            com.google.scone.proto.Survey$LegalSettings r0 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        L47:
            java.lang.String r0 = r0.customPrivacyUrl_
            int r0 = r0.length()
            if (r0 <= 0) goto L5b
            com.google.scone.proto.Survey$Payload r0 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r0 = r0.legalSettings_
            if (r0 != 0) goto L57
            com.google.scone.proto.Survey$LegalSettings r0 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        L57:
            java.lang.String r0 = r0.customPrivacyUrl_
            r4 = r0
            goto L5c
        L5b:
            r4 = r2
        L5c:
            com.google.scone.proto.Survey$Payload r0 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r0 = r0.legalSettings_
            if (r0 != 0) goto L64
            com.google.scone.proto.Survey$LegalSettings r0 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        L64:
            int r0 = r0.bitField0_
            r0 = r0 & 4
            if (r0 == 0) goto L86
            com.google.scone.proto.Survey$Payload r0 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r0 = r0.legalSettings_
            if (r0 != 0) goto L72
            com.google.scone.proto.Survey$LegalSettings r0 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        L72:
            java.lang.String r0 = r0.customTermsUrl_
            int r0 = r0.length()
            if (r0 <= 0) goto L86
            com.google.scone.proto.Survey$Payload r0 = r7.surveyPayload
            com.google.scone.proto.Survey$LegalSettings r0 = r0.legalSettings_
            if (r0 != 0) goto L82
            com.google.scone.proto.Survey$LegalSettings r0 = com.google.scone.proto.Survey$LegalSettings.DEFAULT_INSTANCE
        L82:
            java.lang.String r0 = r0.customTermsUrl_
            r5 = r0
            goto L87
        L86:
            r5 = r2
        L87:
            android.content.Context r0 = r7.getContext()
            android.app.Activity r0 = (android.app.Activity) r0
            r1 = 2131428195(0x7f0b0363, float:1.8478028E38)
            android.view.View r1 = r7.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            r2 = r8
            com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator.configureLegalText(r0, r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.common.view.BaseView.setUpLegalText(java.lang.String):void");
    }

    public final void showNextButton(boolean z) {
        int i;
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.survey_next);
        if (materialButton != null) {
            if (true != z) {
                i = 8;
            } else {
                i = 0;
            }
            materialButton.setVisibility(i);
        }
    }

    public final void switchNextTextToSubmitIfNeeded() {
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.survey_next);
        if (materialButton != null && this.surveyViewPager.isLastQuestion()) {
            materialButton.setText(R.string.survey_submit);
        }
    }

    public BaseView(LayoutInflater layoutInflater, FragmentManager fragmentManager, SurveyActivityInterface surveyActivityInterface, SurveyQuestionsContainerViewArgs surveyQuestionsContainerViewArgs) {
        this();
        this.rootView = layoutInflater.inflate(R.layout.survey_container, (ViewGroup) null, false);
        this.inflater = layoutInflater;
        this.supportFragmentManager = fragmentManager;
        Survey$Payload survey$Payload = surveyQuestionsContainerViewArgs.surveyPayload;
        this.surveyPayload = survey$Payload;
        this.answer = surveyQuestionsContainerViewArgs.answer;
        this.isSubmitting = surveyQuestionsContainerViewArgs.isSubmitting;
        this.args = surveyQuestionsContainerViewArgs;
        this.surveyActivityInterface = surveyActivityInterface;
        this.singleSelectOrdinalAnswerMappings = surveyQuestionsContainerViewArgs.singleSelectOrdinalAnswerMappings;
        HashMap hashMap = new HashMap();
        for (Survey$Question survey$Question : survey$Payload.question_) {
            if ((survey$Question.bitField0_ & 1) != 0) {
                Survey$Question.QuestionBranching questionBranching = survey$Question.questionBranching_;
                if (!hashMap.containsKey((questionBranching == null ? Survey$Question.QuestionBranching.DEFAULT_INSTANCE : questionBranching).branchingGroupName_)) {
                    Survey$Question.QuestionBranching questionBranching2 = survey$Question.questionBranching_;
                    hashMap.put((questionBranching2 == null ? Survey$Question.QuestionBranching.DEFAULT_INSTANCE : questionBranching2).branchingGroupName_, Integer.valueOf(survey$Question.questionOrdinal_ - 1));
                }
            }
        }
        this.branchingMap = ImmutableMap.copyOf((Map) hashMap);
        this.answerTransmitter$ar$class_merging$ar$class_merging = new MapEntryLite$Metadata(getContext(), surveyQuestionsContainerViewArgs.triggerId, surveyQuestionsContainerViewArgs.surveySession);
        this.surveyContainer = (LinearLayout) findViewById(R.id.survey_container);
        this.overallContainer = (MaterialCardView) findViewById(R.id.survey_overall_container);
    }
}
