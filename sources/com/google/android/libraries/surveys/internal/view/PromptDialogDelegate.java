package com.google.android.libraries.surveys.internal.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.model.Answer;
import com.google.android.libraries.surveys.internal.model.QuestionMetrics;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.collect.ImmutableMap;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.protobuf.MapEntryLite$Metadata;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$AnswerChoices;
import com.google.scone.proto.Survey$BranchingDestination;
import com.google.scone.proto.Survey$Completion;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$Question;
import com.google.scone.proto.Survey$Session;
import com.google.scone.proto.Survey$SingleSelect;
import googledata.experiments.mobile.surveys_android.features.HatsQuestionnaireBranching;
import googledata.experiments.mobile.surveys_android.features.HatsV1M10Bugfixes;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Features;
import googledata.experiments.mobile.surveys_android.features.HatsV1M6Bugfixes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import org.chromium.net.impl.CronetEngineBuilderImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PromptDialogDelegate {
    public static ImmutableMap branchingMap;
    public Activity activity;
    public Answer answer;
    private View contentView;
    public final DialogFragmentInterface dialogFragment;
    public boolean ignoreFirstQuestion;
    public InvitationView invitationView;
    private Integer logoResId;
    public RemoteModelManager.RemoteModelManagerRegistration multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging;
    public String openTextResponseAnswer;
    private ViewGroup promptBannerContainer;
    public QuestionMetrics questionMetrics;
    private int requestCode;
    public Survey$Session session;
    public CronetEngineBuilderImpl.QuicHint singleSelectAnswer$ar$class_merging;
    private String surveyActivityClassName;
    private PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle;
    public Survey$Payload surveyPayload;
    public Context themeContext;
    public String triggerId;
    private boolean isStartingSurvey = false;
    private boolean keepNextButtonForLastQuestion = false;
    public int nextQuestionIndex = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DialogFragmentInterface {
        void dismissAllowingStateLoss();

        Activity getActivity();

        Bundle getArguments();

        Dialog getDialog();

        boolean getShowsDialog();
    }

    public PromptDialogDelegate(DialogFragmentInterface dialogFragmentInterface) {
        this.dialogFragment = dialogFragmentInterface;
    }

    private final void setNextButtonOnClickListener(View.OnClickListener onClickListener, String str) {
        ((MaterialButton) this.promptBannerContainer.findViewById(R.id.survey_next)).setOnClickListener(new PromptDialogDelegate$$ExternalSyntheticLambda1(this, onClickListener, str, 2));
    }

    private final void setUpSurveyControls() {
        ((LayoutInflater) this.themeContext.getSystemService("layout_inflater")).inflate(R.layout.survey_controls, this.promptBannerContainer);
        if (!SurveyUtils.shouldDisplaySurveyControls(this.surveyPayload)) {
            this.promptBannerContainer.findViewById(R.id.survey_next).setVisibility(8);
        } else {
            setNextButtonEnabled(false);
            MaterialButton materialButton = (MaterialButton) this.promptBannerContainer.findViewById(R.id.survey_next);
            if (materialButton != null && this.surveyPayload.question_.size() == 1 && !this.keepNextButtonForLastQuestion) {
                materialButton.setText(R.string.survey_submit);
            }
            r3.post(new Runnable() { // from class: com.google.android.libraries.surveys.internal.resourceutils.LayoutUtils$$ExternalSyntheticLambda0
                public final /* synthetic */ int f$1 = R.dimen.survey_button_accessibility_padding;

                @Override // java.lang.Runnable
                public final void run() {
                    Rect rect = new Rect();
                    View view = r1;
                    view.getHitRect(rect);
                    rect.top -= 2131166621;
                    rect.left = rect.left;
                    rect.right += r2;
                    rect.bottom += r3;
                    r4.setTouchDelegate(new TouchDelegate(rect, view));
                }
            });
        }
        this.promptBannerContainer.findViewById(R.id.survey_controls_divider).setVisibility(8);
        this.promptBannerContainer.findViewById(R.id.survey_controls_legal_text).setVisibility(8);
    }

    private final boolean shouldDismissSurvey() {
        Activity activity;
        if (this.isStartingSurvey) {
            return false;
        }
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isBugfixEnabled(HatsV1M6Bugfixes.INSTANCE.get().fixOrientationChangeDismissal(FlagsUtil.phenotypeContext)) && (activity = this.dialogFragment.getActivity()) != null && activity.isChangingConfigurations()) {
            return false;
        }
        return true;
    }

    private final void transmitSurveyAccepted(Context context, String str, Survey$Session survey$Session, boolean z) {
        this.answer.answerType$ar$edu = 3;
        new MapEntryLite$Metadata(context, str, survey$Session).transmit(this.answer, z);
    }

    private static final void updatePromptBannerText$ar$ds(View view, String str) {
        Spanned fromHtml;
        TextView textView = (TextView) view.findViewById(R.id.survey_prompt_title_text);
        fromHtml = HtmlCompat.Api24Impl.fromHtml(str, 0);
        textView.setText(fromHtml);
        textView.announceForAccessibility(fromHtml.toString());
    }

    public final void checkQuestionBranching(Survey$Question survey$Question) {
        if (FlagsUtil.isBranchingEnabled()) {
            Survey$Question.QuestionBranching questionBranching = survey$Question.questionBranching_;
            if (questionBranching == null) {
                questionBranching = Survey$Question.QuestionBranching.DEFAULT_INSTANCE;
            }
            if ((questionBranching.bitField0_ & 1) != 0) {
                Survey$Question.QuestionBranching questionBranching2 = survey$Question.questionBranching_;
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
                if (forNumber$ar$edu$36e8454a_0 != 0) {
                    if (forNumber$ar$edu$36e8454a_0 - 2 != 3) {
                        this.nextQuestionIndex = 1;
                        return;
                    } else {
                        this.nextQuestionIndex = this.surveyPayload.question_.size();
                        return;
                    }
                }
                throw null;
            }
            this.nextQuestionIndex = 1;
            return;
        }
        this.nextQuestionIndex = 1;
    }

    public final SurveyInternalEvent getSurveyInternalEvent() {
        Survey$Session survey$Session = this.session;
        if (survey$Session != null && this.triggerId != null) {
            AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent(null, null, null);
            aggregatedOnDeviceTextDetectionLogEvent.setSessionId$ar$ds(survey$Session.sessionId_);
            aggregatedOnDeviceTextDetectionLogEvent.setTriggerId$ar$ds(this.triggerId);
            aggregatedOnDeviceTextDetectionLogEvent.setSurveyStyle$ar$ds(SurveyStyle.POPUP);
            return aggregatedOnDeviceTextDetectionLogEvent.m220build();
        }
        long j = SurveyUtils.TIMEOUT_MS;
        return null;
    }

    public final void handleQuestionAnswered() {
        this.questionMetrics.markAsAnswered();
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(HatsV1M3Features.enableUserPromptedSurveys(FlagsUtil.phenotypeContext)) && ((this.surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.TOAST || this.surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.SILENT) && (this.surveyPayload.question_.size() == 1 || CurrentProcess.shouldIgnoreScreenInFollowUpQuestions$ar$ds(this.ignoreFirstQuestion, this.surveyPayload, this.answer) || this.nextQuestionIndex == this.surveyPayload.question_.size()))) {
            if (this.surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.TOAST) {
                View view = this.contentView;
                Survey$Completion survey$Completion = this.surveyPayload.completion_;
                if (survey$Completion == null) {
                    survey$Completion = Survey$Completion.DEFAULT_INSTANCE;
                }
                Snackbar.make$ar$ds(view, survey$Completion.completionText_).show();
            }
            Context context = this.themeContext;
            String str = this.triggerId;
            Survey$Session survey$Session = this.session;
            boolean isPiiCollectionEnabled = SurveyUtils.isPiiCollectionEnabled(this.surveyPayload);
            this.answer.answerType$ar$edu = 5;
            new MapEntryLite$Metadata(context, str, survey$Session).transmit(this.answer, isPiiCollectionEnabled);
            transmitSurveyAccepted(this.themeContext, this.triggerId, this.session, SurveyUtils.isPiiCollectionEnabled(this.surveyPayload));
            this.dialogFragment.dismissAllowingStateLoss();
            return;
        }
        startSurveyActivity();
    }

    public final void onCreate$ar$ds$aa6d3cfc_0() {
        if (FlagsUtil.phenotypeContext == null) {
            this.dialogFragment.dismissAllowingStateLoss();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0459  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View onCreateView$ar$ds(android.view.ViewGroup r20) {
        /*
            Method dump skipped, instructions count: 1233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.view.PromptDialogDelegate.onCreateView$ar$ds(android.view.ViewGroup):android.view.View");
    }

    public final void onDestroy() {
        if (FlagsUtil.phenotypeContext != null) {
            if (FlagsUtil.useSurveyStore()) {
                SurveyInternalEvent surveyInternalEvent = getSurveyInternalEvent();
                if (shouldDismissSurvey() && surveyInternalEvent != null) {
                    JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.onSurveyFinished(surveyInternalEvent);
                    return;
                }
                return;
            }
            if (shouldDismissSurvey()) {
                JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.onSurveyFinished();
            }
        }
    }

    public final void onResume(View view) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.themeContext.getSystemService("accessibility");
        View findViewById = view.findViewById(R.id.survey_prompt_title_text);
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isBugfixEnabled(HatsV1M10Bugfixes.INSTANCE.get().fixAccessibilityFocus(FlagsUtil.phenotypeContext)) && accessibilityManager.isTouchExplorationEnabled() && findViewById != null && !findViewById.isAccessibilityFocused()) {
            findViewById.performAccessibilityAction(64, null);
        }
    }

    public final void setNextButtonEnabled(boolean z) {
        MaterialButton materialButton = (MaterialButton) this.promptBannerContainer.findViewById(R.id.survey_next);
        if (materialButton != null && materialButton.isEnabled() != z) {
            materialButton.setEnabled(z);
        }
    }

    public final void singleSelectAnswerComplete(Survey$Question survey$Question) {
        Survey$SingleSelect survey$SingleSelect;
        int i;
        CronetEngineBuilderImpl.QuicHint quicHint = this.singleSelectAnswer$ar$class_merging;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.DEFAULT_INSTANCE.createBuilder();
        if (this.questionMetrics.isShown() && quicHint.CronetEngineBuilderImpl$QuicHint$ar$mHost != null) {
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE.createBuilder();
            int i2 = quicHint.mPort;
            builder2.copyOnWrite();
            ((Survey$Event.QuestionAnswered.Selection) builder2.instance).answerOrdinal_ = i2;
            int i3 = quicHint.mAlternatePort;
            builder2.copyOnWrite();
            ((Survey$Event.QuestionAnswered.Selection) builder2.instance).answerType_ = Survey$Event.QuestionAnswered.Selection.AnswerType.getNumber$ar$edu$def17366_0(i3);
            Object obj = quicHint.CronetEngineBuilderImpl$QuicHint$ar$mHost;
            builder2.copyOnWrite();
            Survey$Event.QuestionAnswered.Selection selection = (Survey$Event.QuestionAnswered.Selection) builder2.instance;
            obj.getClass();
            selection.text_ = (String) obj;
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
            int i4 = survey$Question.questionOrdinal_;
            builder.copyOnWrite();
            ((Survey$Event.QuestionAnswered) builder.instance).questionOrdinal_ = i4;
        }
        Survey$Event.QuestionAnswered questionAnswered2 = (Survey$Event.QuestionAnswered) builder.build();
        if (questionAnswered2 != null) {
            this.answer.response = questionAnswered2;
        }
        checkQuestionBranching(survey$Question);
        CronetEngineBuilderImpl.QuicHint quicHint2 = this.singleSelectAnswer$ar$class_merging;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isFeatureEnabled(HatsQuestionnaireBranching.enableQuestionnaireBranching(FlagsUtil.phenotypeContext))) {
            this.nextQuestionIndex = 1;
        } else {
            Survey$AnswerChoice survey$AnswerChoice = Survey$AnswerChoice.DEFAULT_INSTANCE;
            if (survey$Question.questionCase_ == 4) {
                survey$SingleSelect = (Survey$SingleSelect) survey$Question.question_;
            } else {
                survey$SingleSelect = Survey$SingleSelect.DEFAULT_INSTANCE;
            }
            Survey$AnswerChoices survey$AnswerChoices = survey$SingleSelect.answerChoices_;
            if (survey$AnswerChoices == null) {
                survey$AnswerChoices = Survey$AnswerChoices.DEFAULT_INSTANCE;
            }
            Iterator<E> it = survey$AnswerChoices.answerChoice_.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Survey$AnswerChoice survey$AnswerChoice2 = (Survey$AnswerChoice) it.next();
                if (survey$AnswerChoice2.answerOrdinal_ == quicHint2.mPort) {
                    survey$AnswerChoice = survey$AnswerChoice2;
                    break;
                }
            }
            if ((survey$AnswerChoice.bitField0_ & 1) != 0) {
                Survey$BranchingDestination survey$BranchingDestination = survey$AnswerChoice.branchingDestination_;
                if (survey$BranchingDestination == null) {
                    survey$BranchingDestination = Survey$BranchingDestination.DEFAULT_INSTANCE;
                }
                int forNumber$ar$edu$36e8454a_0 = Survey$BranchingDestination.DestinationType.forNumber$ar$edu$36e8454a_0(survey$BranchingDestination.destinationType_);
                if (forNumber$ar$edu$36e8454a_0 == 0) {
                    forNumber$ar$edu$36e8454a_0 = Survey$BranchingDestination.DestinationType.UNRECOGNIZED$ar$edu$b8e6fbeb_0;
                }
                if (forNumber$ar$edu$36e8454a_0 != 0) {
                    int i5 = forNumber$ar$edu$36e8454a_0 - 2;
                    if (i5 != 2) {
                        if (i5 != 3) {
                            this.nextQuestionIndex = 1;
                        } else {
                            this.nextQuestionIndex = this.surveyPayload.question_.size();
                        }
                    } else {
                        Survey$BranchingDestination survey$BranchingDestination2 = survey$AnswerChoice.branchingDestination_;
                        if (survey$BranchingDestination2 == null) {
                            survey$BranchingDestination2 = Survey$BranchingDestination.DEFAULT_INSTANCE;
                        }
                        String str = survey$BranchingDestination2.toBranchingGroup_;
                        if (branchingMap.containsKey(str)) {
                            i = ((Integer) branchingMap.get(str)).intValue();
                        } else {
                            i = 0;
                        }
                        this.nextQuestionIndex = i;
                    }
                } else {
                    throw null;
                }
            }
        }
        handleQuestionAnswered();
    }

    public final void startSurveyActivity() {
        Activity activity = this.dialogFragment.getActivity();
        String str = this.triggerId;
        Survey$Payload survey$Payload = this.surveyPayload;
        Survey$Session survey$Session = this.session;
        Answer answer = this.answer;
        int i = this.requestCode;
        Integer valueOf = Integer.valueOf(i);
        boolean z = this.ignoreFirstQuestion;
        Integer num = this.logoResId;
        PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle = this.surveyCompletionStyle;
        String str2 = this.surveyActivityClassName;
        int i2 = this.nextQuestionIndex;
        boolean z2 = this.keepNextButtonForLastQuestion;
        HashMap hashMap = new HashMap();
        Iterator it = survey$Payload.question_.iterator();
        while (it.hasNext()) {
            Iterator it2 = it;
            Survey$Question survey$Question = (Survey$Question) it.next();
            int i3 = i;
            if ((1 & survey$Question.bitField0_) != 0) {
                Survey$Question.QuestionBranching questionBranching = survey$Question.questionBranching_;
                if (questionBranching == null) {
                    questionBranching = Survey$Question.QuestionBranching.DEFAULT_INSTANCE;
                }
                if (!hashMap.containsKey(questionBranching.branchingGroupName_)) {
                    Survey$Question.QuestionBranching questionBranching2 = survey$Question.questionBranching_;
                    if (questionBranching2 == null) {
                        questionBranching2 = Survey$Question.QuestionBranching.DEFAULT_INSTANCE;
                    }
                    hashMap.put(questionBranching2.branchingGroupName_, Integer.valueOf(survey$Question.questionOrdinal_ - 1));
                }
            }
            i = i3;
            it = it2;
        }
        int i4 = i;
        SurveyActivityImpl.branchingMap = ImmutableMap.copyOf((Map) hashMap);
        Intent intent = new Intent(activity, (Class<?>) SurveyActivityImpl.class);
        intent.setClassName(activity, str2);
        intent.putExtra("TriggerId", str);
        intent.putExtra("SurveyPayload", survey$Payload.toByteArray());
        intent.putExtra("SurveySession", survey$Session.toByteArray());
        intent.putExtra("Answer", answer);
        intent.putExtra("IsFullWidth", false);
        intent.putExtra("IgnoreFirstQuestion", z);
        if (num != null) {
            intent.putExtra("LogoResId", num);
        }
        intent.putExtra("IsSubmitting", false);
        intent.putExtra("SurveyCompletionStyle", surveyCompletionStyle);
        intent.putExtra("StartingQuestionIndex", i2);
        intent.putExtra("keepNextButtonForLastQuestion", z2);
        long j = SurveyUtils.TIMEOUT_MS;
        valueOf.getClass();
        activity.startActivityForResult(intent, i4);
        this.isStartingSurvey = true;
        transmitSurveyAccepted(this.themeContext, this.triggerId, this.session, SurveyUtils.isPiiCollectionEnabled(this.surveyPayload));
        this.dialogFragment.dismissAllowingStateLoss();
    }

    public final void transmitInvitationAnswered(Context context, String str, Survey$Session survey$Session, boolean z) {
        this.answer.answerType$ar$edu = 4;
        new MapEntryLite$Metadata(context, str, survey$Session).transmit(this.answer, z);
    }

    public final void transmitOtherAccess(Context context, String str, Survey$Session survey$Session, boolean z) {
        this.answer.answerType$ar$edu = 6;
        new MapEntryLite$Metadata(context, str, survey$Session).transmit(this.answer, z);
    }
}
