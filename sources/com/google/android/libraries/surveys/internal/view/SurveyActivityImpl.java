package com.google.android.libraries.surveys.internal.view;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.model.Answer;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
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
import com.google.scone.proto.Survey$Session;
import com.google.scone.proto.Survey$SingleSelect;
import com.google.scone.proto.Survey$TextSubstitution;
import com.google.scone.proto.SurveyServiceGrpc;
import googledata.experiments.mobile.surveys_android.features.HatsQuestionnaireBranching;
import googledata.experiments.mobile.surveys_android.features.HatsV1M15Bugfixes;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Bugfixes;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Features;
import io.grpc.okhttp.OutboundFlowController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyActivityImpl {
    public static ImmutableMap branchingMap;
    public final Activity activity;
    public Answer answer;
    public MapEntryLite$Metadata answerTransmitter$ar$class_merging$ar$class_merging;
    public boolean ignoreFirstQuestion;
    public boolean isSubmitting;
    public boolean keepNextButtonForLastQuestion;
    public Integer logoResId;
    public boolean nextButtonIsEnabled;
    public MaterialCardView overallContainer;
    public int startingQuestionIndex;
    public final FragmentManager supportFragmentManager;
    public final SurveyActivityInterface surveyActivityInterface;
    public PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle;
    public LinearLayout surveyContainer;
    public Survey$Payload surveyPayload;
    public Survey$Session surveySession;
    public SurveyViewPager surveyViewPager;
    public Bundle singleSelectOrdinalAnswerMappings = new Bundle();
    public final Handler activityFinishHandler = new Handler();
    public final Runnable delayedAutoClose = new FlagStore$$ExternalSyntheticLambda3(this, 9);

    public SurveyActivityImpl(Activity activity, FragmentManager fragmentManager, SurveyActivityInterface surveyActivityInterface) {
        this.activity = activity;
        this.supportFragmentManager = fragmentManager;
        this.surveyActivityInterface = surveyActivityInterface;
    }

    private final void proceedToNormalFlow() {
        if (!this.surveyViewPager.isLastQuestion() && CurrentProcess.shouldContinueSurveyDueToScreenIn(getCurrentQuestionIndexForSurveyPayload(), this.surveyPayload, this.answer)) {
            showQuestion(this.surveyViewPager.mCurItem + 1);
        } else {
            showThankYouMessage();
        }
    }

    private final void setViewGroupEnabled(ViewGroup viewGroup, boolean z) {
        viewGroup.setEnabled(z);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                setViewGroupEnabled((ViewGroup) childAt, z);
            } else if (childAt.getId() == R.id.survey_next && z) {
                findViewById(R.id.survey_next).setEnabled(this.nextButtonIsEnabled);
            } else {
                childAt.setEnabled(z);
            }
        }
    }

    private final void showQuestion(int i) {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext))) {
            showNextButton(isSurveyControlRequired());
        }
        saveOrdinalAnswerMappingIfNeeded();
        setAnswerTypeAndTransmit$ar$edu(5);
        this.surveyViewPager.navigateToPage(i);
        updateQuestionTextForAnswerPiping();
        switchNextTextToSubmitIfNeeded();
        this.surveyViewPager.getCurrentItemFragment().getView().sendAccessibilityEvent(32);
        long j = SurveyUtils.TIMEOUT_MS;
    }

    private final void showThankYouMessage() {
        long j = SurveyUtils.TIMEOUT_MS;
        setAnswerTypeAndTransmit$ar$edu(5);
        this.isSubmitting = true;
        setNextButtonEnabled(false);
        this.activity.setResult(-1, new Intent());
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(HatsV1M3Features.enableUserPromptedSurveys(FlagsUtil.phenotypeContext))) {
            if (this.surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.CARD) {
                this.surveyViewPager.navigateToLastPage();
                return;
            }
            this.overallContainer.setVisibility(8);
            PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle = this.surveyCompletionStyle;
            if (surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.TOAST) {
                View findViewById = this.activity.getWindow().findViewById(android.R.id.content);
                Survey$Completion survey$Completion = this.surveyPayload.completion_;
                if (survey$Completion == null) {
                    survey$Completion = Survey$Completion.DEFAULT_INSTANCE;
                }
                Snackbar.make$ar$ds(findViewById, survey$Completion.completionText_).show();
                autoCloseActivityWithDelay();
                return;
            }
            if (surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.SILENT) {
                this.activity.finish();
                return;
            }
            return;
        }
        this.surveyViewPager.navigateToLastPage();
    }

    public final void autoCloseActivityWithDelay() {
        this.activity.setResult(-1, new Intent());
        this.activityFinishHandler.postDelayed(this.delayedAutoClose, 2400L);
    }

    public final View findViewById(int i) {
        return this.activity.findViewById(i);
    }

    public final int getCurrentQuestionIndexForSurveyPayload() {
        SurveyViewPager surveyViewPager = this.surveyViewPager;
        if (surveyViewPager != null) {
            int i = surveyViewPager.mCurItem;
            if (FlagsUtil.isBranchingEnabled()) {
                return i + this.startingQuestionIndex;
            }
            if (this.ignoreFirstQuestion) {
                return i + 1;
            }
            return i;
        }
        return 0;
    }

    public final SurveyInternalEvent getSurveyInternalEvent() {
        String stringExtra = this.activity.getIntent().getStringExtra("TriggerId");
        Survey$Session survey$Session = this.surveySession;
        if (survey$Session != null && stringExtra != null) {
            AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent(null, null, null);
            aggregatedOnDeviceTextDetectionLogEvent.setSessionId$ar$ds(survey$Session.sessionId_);
            aggregatedOnDeviceTextDetectionLogEvent.setTriggerId$ar$ds(stringExtra);
            aggregatedOnDeviceTextDetectionLogEvent.setSurveyStyle$ar$ds(SurveyStyle.POPUP);
            return aggregatedOnDeviceTextDetectionLogEvent.m220build();
        }
        long j = SurveyUtils.TIMEOUT_MS;
        return null;
    }

    public final Survey$Event.QuestionAnswered getSurveyResponse() {
        return this.answer.response;
    }

    public final boolean isSurveyControlRequired() {
        return SurveyUtils.shouldDisplaySurveyControls(this.surveyPayload);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void nextPageOrSubmit() {
        Survey$SingleSelect survey$SingleSelect;
        Survey$SingleSelect survey$SingleSelect2;
        Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer;
        Survey$Event.QuestionAnswered.RatingAnswer ratingAnswer;
        Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer2;
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
        setLegalTextVisibility$ar$ds$735c7a73_0();
        SurveyInternalEvent surveyInternalEvent = getSurveyInternalEvent();
        if (surveyInternalEvent != null) {
            int forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(((Survey$Question) this.surveyPayload.question_.get(getCurrentQuestionIndexForSurveyPayload())).questionType_);
            if (forNumber$ar$edu$83e82a14_0 == 0) {
                forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0;
            }
            int i = forNumber$ar$edu$83e82a14_0 - 2;
            if (forNumber$ar$edu$83e82a14_0 != 0) {
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
                        singleSelectAnswer2 = (Survey$Event.QuestionAnswered.SingleSelectAnswer) currentItemQuestionResponse3.answer_;
                    } else {
                        singleSelectAnswer2 = Survey$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE;
                    }
                    Survey$Event.QuestionAnswered.Selection selection2 = singleSelectAnswer2.answer_;
                    if (selection2 == null) {
                        selection2 = Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE;
                    }
                    int i3 = selection2.answerOrdinal_;
                    JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.didAnswerSingleSelectQuestion$ar$ds(surveyInternalEvent);
                }
            } else {
                throw null;
            }
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
                        int nextQuestionOrdinal = new OutboundFlowController.WriteStatus(null).getNextQuestionOrdinal(branchingMap, this.surveyPayload.question_.size(), selection3.answerOrdinal_, survey$Question2);
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
                                singleSelectAnswer = (Survey$Event.QuestionAnswered.SingleSelectAnswer) currentItemQuestionResponse6.answer_;
                            } else {
                                singleSelectAnswer = Survey$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE;
                            }
                            Survey$Event.QuestionAnswered.Selection selection4 = singleSelectAnswer.answer_;
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
                                if (surveyViewPagerAdapter2 != null && branchingMap.containsKey(str)) {
                                    i5 = surveyViewPagerAdapter2.getQuestionOrdinalCardIndex(((Integer) branchingMap.get(str)).intValue());
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
    }

    public final void onPauseSurvey(boolean z) {
        int i;
        float f;
        LinearLayout linearLayout = this.surveyContainer;
        if (true != z) {
            i = 262144;
        } else {
            i = 393216;
        }
        linearLayout.setDescendantFocusability(i);
        LinearLayout linearLayout2 = this.surveyContainer;
        if (true != z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        linearLayout2.setAlpha(f);
        if (z) {
            this.nextButtonIsEnabled = findViewById(R.id.survey_next).isEnabled();
        }
        setViewGroupEnabled(this.surveyContainer, !z);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            Rect rect = new Rect();
            this.overallContainer.getGlobalVisibleRect(rect);
            if (!rect.contains((int) motionEvent.getX(), (int) motionEvent.getY()) && this.isSubmitting) {
                long j = SurveyUtils.TIMEOUT_MS;
                this.activity.finish();
                return true;
            }
        }
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!HatsV1M15Bugfixes.fixTouchEventCrash(this.activity)) {
            return this.activity.onTouchEvent(motionEvent);
        }
        return false;
    }

    public final void saveOrdinalAnswerMappingIfNeeded() {
        Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer;
        int forNumber$ar$edu$f9ea4f52_0 = Survey$Event.QuestionAnswered.AnswerCase.forNumber$ar$edu$f9ea4f52_0(getSurveyResponse().answerCase_);
        int i = Survey$Event.QuestionAnswered.AnswerCase.SINGLE_SELECTION$ar$edu;
        if (forNumber$ar$edu$f9ea4f52_0 != 0) {
            if (forNumber$ar$edu$f9ea4f52_0 == i) {
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
                return;
            }
            return;
        }
        throw null;
    }

    public final void setAnswerTypeAndTransmit$ar$edu(int i) {
        Answer answer = this.answer;
        answer.answerType$ar$edu = i;
        this.answerTransmitter$ar$class_merging$ar$class_merging.transmit(answer, SurveyUtils.isPiiCollectionEnabled(this.surveyPayload));
    }

    public final void setLegalTextVisibility$ar$ds$735c7a73_0() {
        findViewById(R.id.survey_controls_divider).setVisibility(8);
        findViewById(R.id.survey_controls_legal_text).setVisibility(8);
    }

    public final void setNextButtonEnabled(boolean z) {
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.survey_next);
        if (materialButton != null && materialButton.isEnabled() != z) {
            materialButton.setEnabled(z);
        }
        this.nextButtonIsEnabled = z;
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
        if (materialButton != null && this.surveyViewPager.isLastQuestion() && !this.keepNextButtonForLastQuestion) {
            materialButton.setText(R.string.survey_submit);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateQuestionTextForAnswerPiping() {
        String str;
        Survey$TextSubstitution.AnswerPipe answerPipe;
        SurveyViewPager surveyViewPager = this.surveyViewPager;
        if (surveyViewPager != null && surveyViewPager.isThankYouDisplayed()) {
            return;
        }
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
        for (int i = 0; i < size; i++) {
            Survey$TextSubstitution survey$TextSubstitution = (Survey$TextSubstitution) survey$Question.textSubstitution_.get(i);
            if (SurveyServiceGrpc.forNumber$ar$edu(survey$TextSubstitution.replacementOperationCase_) == 3) {
                if (survey$TextSubstitution.replacementOperationCase_ == 2) {
                    answerPipe = (Survey$TextSubstitution.AnswerPipe) survey$TextSubstitution.replacementOperation_;
                } else {
                    answerPipe = Survey$TextSubstitution.AnswerPipe.DEFAULT_INSTANCE;
                }
                int i2 = answerPipe.fromQuestionOrdinal_;
                String string = this.singleSelectOrdinalAnswerMappings.getString(String.valueOf(i2));
                if (string != null) {
                    strArr[i] = survey$TextSubstitution.matchText_;
                    strArr2[i] = string;
                } else {
                    Log.e("SurveyActivityImpl", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i2, "No single-select question with ordinal ", " was found."));
                }
            }
        }
        this.surveyViewPager.updateQuestionText(TextUtils.replace(str, strArr, strArr2).toString());
    }
}
