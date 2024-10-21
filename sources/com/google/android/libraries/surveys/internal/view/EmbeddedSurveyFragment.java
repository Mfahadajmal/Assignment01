package com.google.android.libraries.surveys.internal.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.core.content.ContextCompat$Api21Impl;
import androidx.core.content.ContextCompat$Api23Impl;
import com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.common.view.BaseView;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.model.Answer;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import com.google.android.libraries.surveys.internal.resourceutils.LayoutUtils;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.libraries.surveys.internal.view.SurveyQuestionsContainerViewArgs;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.scone.proto.Survey$Invitation;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$Session;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Bugfixes;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EmbeddedSurveyFragment extends Fragment implements SurveyActivityInterface {
    private FileUtils argsMapper$ar$class_merging$ar$class_merging$ar$class_merging;
    final CurrentProcess provider$ar$class_merging$1a27c458_0$ar$class_merging$ar$class_merging = new CurrentProcess();
    private BaseView questionsContainerView$ar$class_merging$ar$class_merging;

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityInterface
    public final /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final FragmentManager getSupportFragmentManager() {
        return getChildFragmentManager();
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final void hideCloseButton() {
        ImageButton imageButton = (ImageButton) this.questionsContainerView$ar$class_merging$ar$class_merging.findViewById(R.id.survey_close_button);
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final boolean isRunningEmbeddedFlow() {
        return true;
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final boolean isSurveyControlRequired() {
        return this.questionsContainerView$ar$class_merging$ar$class_merging.isSurveyControlRequired();
    }

    @Override // com.google.android.libraries.surveys.internal.view.NextPageOrSubmitActionable
    public final void nextPageOrSubmit() {
        this.questionsContainerView$ar$class_merging$ar$class_merging.nextPageOrSubmit();
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.argsMapper$ar$class_merging$ar$class_merging$ar$class_merging = new FileUtils();
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Drawable recoloredDrawable;
        boolean z;
        Survey$Payload survey$Payload;
        Answer answer;
        String str;
        Survey$Session survey$Session;
        PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle;
        SurveyStyle surveyStyle;
        Bundle bundle2;
        Bundle arguments = getArguments();
        Answer answer2 = bundle != null ? (Answer) bundle.getParcelable("Answer") : (Answer) arguments.getParcelable("Answer");
        String string = arguments.getString("TriggerId");
        byte[] byteArray = arguments.getByteArray("SurveyPayload");
        SurveyQuestionsContainerViewArgs surveyQuestionsContainerViewArgs = null;
        Survey$Payload survey$Payload2 = byteArray != null ? (Survey$Payload) SurveyUtils.getMessage(Survey$Payload.DEFAULT_INSTANCE, byteArray) : null;
        byte[] byteArray2 = arguments.getByteArray("SurveySession");
        Survey$Session survey$Session2 = byteArray2 != null ? (Survey$Session) SurveyUtils.getMessage(Survey$Session.DEFAULT_INSTANCE, byteArray2) : null;
        if (string == null || survey$Payload2 == null || survey$Payload2.question_.size() == 0 || answer2 == null) {
            surveyQuestionsContainerViewArgs = null;
        } else if (survey$Session2 != null) {
            SurveyQuestionsContainerViewArgs.Builder builder = new SurveyQuestionsContainerViewArgs.Builder(null);
            builder.set$0 = (byte) (builder.set$0 | 2);
            builder.setHideCloseButton$ar$ds(false);
            builder.setIsSubmitting$ar$ds(false);
            builder.setStartingQuestionIndex$ar$ds(0);
            builder.setKeepNextButtonForLastQuestion$ar$ds(false);
            builder.singleSelectOrdinalAnswerMappings = new Bundle();
            builder.surveyPayload = survey$Payload2;
            builder.answer = answer2;
            builder.surveySession = survey$Session2;
            builder.triggerId = string;
            if (bundle != null) {
                z = bundle.getBoolean("IsSubmitting");
            } else {
                z = arguments.getBoolean("IsSubmitting", false);
            }
            builder.setIsSubmitting$ar$ds(z);
            if (arguments.containsKey("LogoResId")) {
                builder.logoResId = Integer.valueOf(arguments.getInt("LogoResId", 0));
            }
            if (arguments.containsKey("keepNextButtonForLastQuestion")) {
                builder.setKeepNextButtonForLastQuestion$ar$ds(arguments.getBoolean("keepNextButtonForLastQuestion", false));
            }
            builder.currentItem = bundle != null ? Integer.valueOf(bundle.getInt("CurrentQuestionIndexForViewPager")) : null;
            if (bundle != null) {
                Bundle bundle3 = bundle.getBundle("SingleSelectOrdinalAnswerMappings");
                if (bundle3 == null) {
                    bundle3 = new Bundle();
                }
                builder.singleSelectOrdinalAnswerMappings = bundle3;
            }
            PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle2 = (PresentSurveyRequest.SurveyCompletionStyle) arguments.getSerializable("SurveyCompletionCode");
            if (surveyCompletionStyle2 != null) {
                builder.surveyCompletionStyle = surveyCompletionStyle2;
                builder.setHideCloseButton$ar$ds(true);
                SurveyStyle surveyStyle2 = SurveyStyle.EMBEDDED;
                if (surveyStyle2 != null) {
                    builder.surveyStyle = surveyStyle2;
                    builder.setStartingQuestionIndex$ar$ds(arguments.getInt("StartingQuestionIndex"));
                    if (builder.set$0 == 31 && (survey$Payload = builder.surveyPayload) != null && (answer = builder.answer) != null && (str = builder.triggerId) != null && (survey$Session = builder.surveySession) != null && (surveyCompletionStyle = builder.surveyCompletionStyle) != null && (surveyStyle = builder.surveyStyle) != null && (bundle2 = builder.singleSelectOrdinalAnswerMappings) != null) {
                        surveyQuestionsContainerViewArgs = new SurveyQuestionsContainerViewArgs(survey$Payload, answer, builder.isSubmitting, builder.logoResId, str, survey$Session, builder.startingQuestionIndex, builder.currentItem, surveyCompletionStyle, builder.hideCloseButton, builder.keepNextButtonForLastQuestion, surveyStyle, bundle2);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        if (builder.surveyPayload == null) {
                            sb.append(" surveyPayload");
                        }
                        if (builder.answer == null) {
                            sb.append(" answer");
                        }
                        if ((builder.set$0 & 1) == 0) {
                            sb.append(" isSubmitting");
                        }
                        if ((builder.set$0 & 2) == 0) {
                            sb.append(" ignoreFirstQuestion");
                        }
                        if (builder.triggerId == null) {
                            sb.append(" triggerId");
                        }
                        if (builder.surveySession == null) {
                            sb.append(" surveySession");
                        }
                        if ((builder.set$0 & 4) == 0) {
                            sb.append(" startingQuestionIndex");
                        }
                        if (builder.surveyCompletionStyle == null) {
                            sb.append(" surveyCompletionStyle");
                        }
                        if ((builder.set$0 & 8) == 0) {
                            sb.append(" hideCloseButton");
                        }
                        if ((builder.set$0 & 16) == 0) {
                            sb.append(" keepNextButtonForLastQuestion");
                        }
                        if (builder.surveyStyle == null) {
                            sb.append(" surveyStyle");
                        }
                        if (builder.singleSelectOrdinalAnswerMappings == null) {
                            sb.append(" singleSelectOrdinalAnswerMappings");
                        }
                        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
                    }
                } else {
                    throw new NullPointerException("Null surveyStyle");
                }
            } else {
                throw new NullPointerException("Null surveyCompletionStyle");
            }
        }
        if (surveyQuestionsContainerViewArgs == null) {
            Log.e("EmbeddedSurveyFragment", "Required EXTRAS not found in the intent, bailing out.");
            return null;
        }
        BaseView baseView = new BaseView(layoutInflater, getChildFragmentManager(), this, surveyQuestionsContainerViewArgs);
        this.questionsContainerView$ar$class_merging$ar$class_merging = baseView;
        baseView.registerListener$ar$class_merging$599da675_0(this);
        BaseView baseView2 = this.questionsContainerView$ar$class_merging$ar$class_merging;
        if (baseView2.isSubmitting && baseView2.args.surveyStyle == SurveyStyle.EMBEDDED && (baseView2.args.surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.TOAST || baseView2.args.surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.SILENT)) {
            baseView2.findViewById(R.id.survey_main_scroll_view).setVisibility(8);
        } else {
            boolean z2 = baseView2.args.surveyStyle == SurveyStyle.EMBEDDED && baseView2.args.currentItem == null;
            Survey$Invitation survey$Invitation = baseView2.surveyPayload.invitation_;
            if (survey$Invitation == null) {
                survey$Invitation = Survey$Invitation.DEFAULT_INSTANCE;
            }
            boolean z3 = survey$Invitation.showInvitation_;
            SurveyInternalEvent surveyInternalEvent = baseView2.getSurveyInternalEvent();
            if (!z3 || z2) {
                JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.onSurveyRunning(surveyInternalEvent);
            }
            if (baseView2.args.surveyStyle == SurveyStyle.EMBEDDED) {
                FrameLayout frameLayout = (FrameLayout) baseView2.findViewById(R.id.fl_card_container);
                frameLayout.setClipToPadding(false);
                frameLayout.setClipChildren(false);
                frameLayout.setPadding(0, baseView2.rootView.getResources().getDimensionPixelOffset(R.dimen.card_container_top_padding), 0, 0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) baseView2.overallContainer.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.gravity = 8388659;
                baseView2.overallContainer.setLayoutParams(layoutParams);
            }
            if (baseView2.args.surveyStyle != SurveyStyle.EMBEDDED) {
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) baseView2.overallContainer.getLayoutParams();
                if (LayoutUtils.isInCarMode(baseView2.overallContainer.getContext())) {
                    layoutParams2.width = -2;
                } else {
                    layoutParams2.width = LayoutUtils.getSurveyContainerMaxWidth(baseView2.overallContainer.getContext());
                }
                baseView2.overallContainer.setLayoutParams(layoutParams2);
            }
            String str2 = TextUtils.isEmpty(baseView2.answer.accountName) ? null : baseView2.answer.accountName;
            ImageButton imageButton = (ImageButton) baseView2.findViewById(R.id.survey_close_button);
            recoloredDrawable = DefaultExperimentTokenDecorator.getRecoloredDrawable(ContextCompat$Api21Impl.getDrawable(r7, R.drawable.survey_close_button_icon), r7, ContextCompat$Api23Impl.getColor(baseView2.getContext(), R.color.survey_close_icon_color));
            imageButton.setImageDrawable(recoloredDrawable);
            imageButton.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(baseView2, str2, 15, null));
            baseView2.findViewById(R.id.survey_main_scroll_view).setFocusable(false);
            boolean isSurveyControlRequired = baseView2.isSurveyControlRequired();
            baseView2.inflater.inflate(R.layout.survey_controls, baseView2.surveyContainer);
            CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext))) {
                baseView2.showNextButton(isSurveyControlRequired);
            } else if (!isSurveyControlRequired) {
                baseView2.showNextButton(false);
            }
            if (baseView2.args.surveyStyle == SurveyStyle.EMBEDDED) {
                Integer num = baseView2.args.currentItem;
                if (num != null && num.intValue() != 0) {
                    baseView2.setLegalTextVisibility$ar$ds();
                } else {
                    baseView2.setUpLegalText(str2);
                }
            } else {
                Survey$Invitation survey$Invitation2 = baseView2.surveyPayload.invitation_;
                if (survey$Invitation2 == null) {
                    survey$Invitation2 = Survey$Invitation.DEFAULT_INSTANCE;
                }
                if (!survey$Invitation2.showInvitation_) {
                    baseView2.setUpLegalText(str2);
                } else {
                    baseView2.setLegalTextVisibility$ar$ds();
                }
            }
            SurveyQuestionsContainerViewArgs surveyQuestionsContainerViewArgs2 = baseView2.args;
            Integer num2 = surveyQuestionsContainerViewArgs2.currentItem;
            PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle3 = surveyQuestionsContainerViewArgs2.surveyCompletionStyle;
            FragmentManager fragmentManager = baseView2.supportFragmentManager;
            Survey$Payload survey$Payload3 = baseView2.surveyPayload;
            SurveyViewPagerAdapter surveyViewPagerAdapter = new SurveyViewPagerAdapter(fragmentManager, survey$Payload3, surveyQuestionsContainerViewArgs2.logoResId, false, CurrentProcess.shouldIgnoreScreenInFollowUpQuestions$ar$ds(false, survey$Payload3, baseView2.answer), surveyCompletionStyle3, baseView2.args.startingQuestionIndex);
            baseView2.surveyViewPager = (SurveyViewPager) baseView2.findViewById(R.id.survey_viewpager);
            SurveyViewPager surveyViewPager = baseView2.surveyViewPager;
            surveyViewPager.surveyActivityInterface = baseView2.surveyActivityInterface;
            surveyViewPager.setAdapter(surveyViewPagerAdapter);
            baseView2.surveyViewPager.setImportantForAccessibility(2);
            if (num2 != null) {
                baseView2.surveyViewPager.setCurrentItem(num2.intValue());
            }
            if (isSurveyControlRequired) {
                baseView2.switchNextTextToSubmitIfNeeded();
            }
            baseView2.surveyContainer.setVisibility(0);
            baseView2.surveyContainer.forceLayout();
            if (isSurveyControlRequired) {
                ((MaterialButton) baseView2.findViewById(R.id.survey_next)).setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(baseView2, str2, 16, null));
            }
            Iterator it = baseView2.getListeners().iterator();
            while (it.hasNext()) {
                ((EmbeddedSurveyFragment) it.next()).showWindowScrim();
            }
            baseView2.findViewById(R.id.survey_close_button).setVisibility(true != baseView2.args.hideCloseButton ? 0 : 8);
            SurveyViewPager surveyViewPager2 = baseView2.surveyViewPager;
            if (surveyViewPager2 != null && surveyViewPager2.isFirstQuestion()) {
                Survey$Invitation survey$Invitation3 = baseView2.surveyPayload.invitation_;
                if (survey$Invitation3 == null) {
                    survey$Invitation3 = Survey$Invitation.DEFAULT_INSTANCE;
                }
                if (!survey$Invitation3.showInvitation_) {
                    baseView2.setAnswerTypeAndTransmit$ar$edu(2);
                }
            }
        }
        return this.questionsContainerView$ar$class_merging$ar$class_merging.rootView;
    }

    @Override // com.google.android.libraries.surveys.internal.view.OnQuestionProgressableChangeListener
    public final void onQuestionProgressableChanged(boolean z, Fragment fragment) {
        BaseView baseView = this.questionsContainerView$ar$class_merging$ar$class_merging;
        if (!baseView.isSubmitting && SurveyViewPagerAdapter.getQuestionIndex(fragment) == baseView.surveyViewPager.mCurItem && !baseView.args.keepNextButtonForLastQuestion) {
            baseView.setNextButtonEnabled(z);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        int i;
        SurveyViewPager surveyViewPager = this.questionsContainerView$ar$class_merging$ar$class_merging.surveyViewPager;
        if (surveyViewPager != null) {
            i = surveyViewPager.mCurItem;
        } else {
            i = 0;
        }
        bundle.putInt("CurrentQuestionIndexForViewPager", i);
        bundle.putBoolean("IsSubmitting", this.questionsContainerView$ar$class_merging$ar$class_merging.isSubmitting);
        bundle.putParcelable("Answer", this.questionsContainerView$ar$class_merging$ar$class_merging.answer);
        bundle.putBundle("SingleSelectOrdinalAnswerMappings", this.questionsContainerView$ar$class_merging$ar$class_merging.singleSelectOrdinalAnswerMappings);
    }

    @Override // com.google.android.libraries.surveys.internal.view.NextPageOrSubmitActionable
    public final void setNextButtonEnabled(boolean z) {
        this.questionsContainerView$ar$class_merging$ar$class_merging.setNextButtonEnabled(z);
    }

    @Override // com.google.android.libraries.surveys.internal.view.NextPageOrSubmitActionable
    public final void showNextButton$ar$ds() {
        this.questionsContainerView$ar$class_merging$ar$class_merging.showNextButton(false);
    }

    public final void showSnackbarOnSurveyCompleted(String str) {
        Snackbar.make$ar$ds(requireActivity().getWindow().findViewById(android.R.id.content), str).show();
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final void autoCloseActivityWithDelay() {
    }

    public final void onSurveyClosed() {
    }

    public final void onSurveyCompleted() {
    }

    public final void showWindowScrim() {
    }
}
