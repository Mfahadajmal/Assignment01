package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.resourceutils.LayoutUtils;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.marvin.talkback.R;
import com.google.scone.proto.Survey$BranchingDestination;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$Question;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Features;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SurveyViewPager extends ViewPager {
    public SurveyActivityInterface surveyActivityInterface;

    public SurveyViewPager(Context context) {
        super(context);
        setUpSurveyViewPager();
    }

    private final View getCurrentView() {
        BaseFragment currentItemFragment;
        if (this.mAdapter != null && (currentItemFragment = getCurrentItemFragment()) != null) {
            return currentItemFragment.getView();
        }
        return null;
    }

    private final void setUpSurveyViewPager() {
        ViewPager.SimpleOnPageChangeListener simpleOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() { // from class: com.google.android.libraries.surveys.internal.view.SurveyViewPager.1
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public final void onPageSelected$ar$ds() {
                SurveyViewPager.this.invalidate();
                BaseFragment currentItemFragment = SurveyViewPager.this.getCurrentItemFragment();
                if (currentItemFragment != null) {
                    currentItemFragment.onPageScrolledIntoView();
                    currentItemFragment.animateFadeIn();
                    View view = currentItemFragment.getView();
                    if (view != null) {
                        view.sendAccessibilityEvent(32);
                    }
                }
                SurveyViewPager.this.requestLayout();
            }
        };
        addOnPageChangeListener(simpleOnPageChangeListener);
        post(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, simpleOnPageChangeListener, 13, (byte[]) null));
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {
            case 19:
            case 20:
            case 21:
            case 22:
                return false;
            default:
                switch (keyCode) {
                    case 268:
                    case 269:
                    case 270:
                    case 271:
                        return false;
                    default:
                        return super.dispatchKeyEvent(keyEvent);
                }
        }
    }

    public final BaseFragment getCurrentItemFragment() {
        SurveyActivityInterface surveyActivityInterface = this.surveyActivityInterface;
        if (surveyActivityInterface != null) {
            int i = this.mCurItem;
            for (Fragment fragment : surveyActivityInterface.getSupportFragmentManager().getFragments()) {
                if (SurveyViewPagerAdapter.getQuestionIndex(fragment) == i && (fragment instanceof BaseFragment)) {
                    return (BaseFragment) fragment;
                }
            }
            return null;
        }
        return null;
    }

    public final Survey$Event.QuestionAnswered getCurrentItemQuestionResponse() {
        BaseFragment currentItemFragment = getCurrentItemFragment();
        if (currentItemFragment == null) {
            return null;
        }
        return currentItemFragment.computeQuestionResponse();
    }

    public final boolean isFirstQuestion() {
        if (this.mCurItem == 0) {
            return true;
        }
        return false;
    }

    public final boolean isLastQuestion() {
        int i;
        SurveyViewPagerAdapter surveyViewPagerAdapter = (SurveyViewPagerAdapter) this.mAdapter;
        boolean z = false;
        if (surveyViewPagerAdapter == null) {
            Log.e("SurveyViewPager", "Error, survey view pager adapter is null!");
            return false;
        }
        if (FlagsUtil.isBranchingEnabled() && getCurrentItemFragment() != null && surveyViewPagerAdapter.getQuestion(this.mCurItem) != null && (surveyViewPagerAdapter.getQuestion(this.mCurItem).bitField0_ & 1) != 0) {
            Survey$Question.QuestionBranching questionBranching = ((SurveyViewPagerAdapter) this.mAdapter).getQuestion(this.mCurItem).questionBranching_;
            if (questionBranching == null) {
                questionBranching = Survey$Question.QuestionBranching.DEFAULT_INSTANCE;
            }
            Survey$BranchingDestination survey$BranchingDestination = questionBranching.branchingDestination_;
            if (survey$BranchingDestination == null) {
                survey$BranchingDestination = Survey$BranchingDestination.DEFAULT_INSTANCE;
            }
            int forNumber$ar$edu$36e8454a_0 = Survey$BranchingDestination.DestinationType.forNumber$ar$edu$36e8454a_0(survey$BranchingDestination.destinationType_);
            if (forNumber$ar$edu$36e8454a_0 == 0) {
                forNumber$ar$edu$36e8454a_0 = Survey$BranchingDestination.DestinationType.UNRECOGNIZED$ar$edu$b8e6fbeb_0;
            }
            if (forNumber$ar$edu$36e8454a_0 == Survey$BranchingDestination.DestinationType.DESTINATION_TYPE_COMPLETE_SURVEY$ar$edu) {
                z = true;
            }
            if (forNumber$ar$edu$36e8454a_0 != 0) {
                return z;
            }
            throw null;
        }
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(HatsV1M3Features.enableUserPromptedSurveys(FlagsUtil.phenotypeContext))) {
            if (surveyViewPagerAdapter.surveyCompletionStyle == PresentSurveyRequest.SurveyCompletionStyle.CARD) {
                i = 2;
            } else {
                i = 1;
            }
            if (this.mCurItem != surveyViewPagerAdapter.getCount() - i) {
                return false;
            }
            return true;
        }
        if (this.mCurItem != surveyViewPagerAdapter.getCount() - 2) {
            return false;
        }
        return true;
    }

    public final boolean isThankYouDisplayed() {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(HatsV1M3Features.enableUserPromptedSurveys(FlagsUtil.phenotypeContext))) {
            SurveyViewPagerAdapter surveyViewPagerAdapter = (SurveyViewPagerAdapter) this.mAdapter;
            if (surveyViewPagerAdapter == null) {
                Log.e("SurveyViewPager", "Error, survey view pager adapter is null!");
                return false;
            }
            if (((SurveyCard) surveyViewPagerAdapter.surveyCards.get(this.mCurItem)).surveyCardType$ar$edu == 5) {
                return true;
            }
            return false;
        }
        if (this.mCurItem == this.mAdapter.getCount() - 1) {
            return true;
        }
        return false;
    }

    public final void navigateToLastPage() {
        setCurrentItem$ar$ds(this.mAdapter.getCount() - 1);
        getCurrentItemFragment().animateFadeIn();
    }

    public final void navigateToPage(int i) {
        setCurrentItem$ar$ds(i);
        getCurrentItemFragment().animateFadeIn();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public final void onMeasure(int i, int i2) {
        View view;
        boolean z;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isFeatureEnabled(HatsV1M3Features.INSTANCE.get().enableLandscapeImprovements(FlagsUtil.phenotypeContext))) {
            if (getChildCount() == 0) {
                super.onMeasure(i, i2);
            }
            View currentView = getCurrentView();
            if (currentView == null) {
                super.onMeasure(i, i2);
                return;
            }
            currentView.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = currentView.getMeasuredHeight();
            Rect rect = new Rect();
            currentView.getWindowVisibleDisplayFrame(rect);
            int height = rect.height() - currentView.findViewById(R.id.survey_question_header_logo_text).getHeight();
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.survey_card_vertical_margin);
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(Math.min(measuredHeight, height - (dimensionPixelSize + dimensionPixelSize)), 1073741824));
            return;
        }
        View currentView2 = getCurrentView();
        if (currentView2 == null) {
            super.onMeasure(i, i2);
            return;
        }
        SurveyActivityInterface surveyActivityInterface = this.surveyActivityInterface;
        if (surveyActivityInterface != null) {
            view = surveyActivityInterface.getActivity().findViewById(R.id.survey_controls_container);
        } else {
            view = null;
        }
        View view2 = view;
        SurveyActivityInterface surveyActivityInterface2 = this.surveyActivityInterface;
        if (surveyActivityInterface2 != null && !surveyActivityInterface2.isRunningEmbeddedFlow()) {
            z = false;
        } else {
            z = true;
        }
        super.onMeasure(i, LayoutUtils.measureCardHeightMeasureSpec(this, currentView2, i, i2, currentView2.findViewById(R.id.survey_question_header_logo_text), view2, z));
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public final void updateQuestionText(String str) {
        BaseFragment currentItemFragment = getCurrentItemFragment();
        if (currentItemFragment != null) {
            currentItemFragment.updateQuestionText(str);
        } else {
            post(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, str, 12));
        }
    }

    public SurveyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setUpSurveyViewPager();
    }
}
