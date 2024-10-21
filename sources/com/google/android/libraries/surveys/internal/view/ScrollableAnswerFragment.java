package com.google.android.libraries.surveys.internal.view;

import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import androidx.core.view.ViewCompat;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.resourceutils.LayoutUtils;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Bugfixes;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ScrollableAnswerFragment extends BaseFragment {
    private TextView questionTextView;
    public ScrollViewWithSizeCallback scrollView;
    public View scrollViewContents;
    public View surveyControlsContainer;
    public View surveyQuestionHeader;
    private final ScrollShadowHandler scrollShadowHandler = new ScrollShadowHandler();
    private boolean isOnScrollChangedListenerAttached = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ScrollShadowHandler implements ViewTreeObserver.OnScrollChangedListener {
        public ScrollShadowHandler() {
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public final void onScrollChanged() {
            CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext)) && !ScrollableAnswerFragment.this.areImportantReferencesValid()) {
                return;
            }
            updateShadowVisibility(ScrollableAnswerFragment.this.scrollView.getHeight());
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x0050, code lost:
        
            if (r6 != false) goto L16;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void updateShadowVisibility(int r6) {
            /*
                r5 = this;
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r0 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                boolean r0 = r0.getUserVisibleHint()
                if (r0 != 0) goto L9
                return
            L9:
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r0 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                com.google.android.libraries.surveys.internal.view.ScrollViewWithSizeCallback r0 = r0.scrollView
                int r0 = r0.getScrollY()
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r1 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                android.view.View r1 = r1.scrollViewContents
                int r1 = r1.getBottom()
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r2 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                com.google.android.libraries.surveys.internal.view.ScrollViewWithSizeCallback r2 = r2.scrollView
                int r2 = r2.getScrollY()
                int r2 = r2 + r6
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r3 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                android.view.View r3 = r3.scrollViewContents
                int r3 = r3.getBottom()
                if (r3 <= r6) goto L2e
                r6 = 1
                goto L2f
            L2e:
                r6 = 0
            L2f:
                r3 = 0
                if (r6 == 0) goto L49
                if (r0 != 0) goto L35
                goto L49
            L35:
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r6 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                android.view.View r0 = r6.surveyQuestionHeader
                android.content.res.Resources r6 = r6.getResources()
                r4 = 2131166646(0x7f0705b6, float:1.7947543E38)
                int r6 = r6.getDimensionPixelSize(r4)
                float r6 = (float) r6
                r0.setElevation(r6)
                goto L52
            L49:
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r0 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                android.view.View r0 = r0.surveyQuestionHeader
                r0.setElevation(r3)
                if (r6 == 0) goto L69
            L52:
                if (r1 != r2) goto L55
                goto L69
            L55:
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r6 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                android.view.View r0 = r6.surveyControlsContainer
                android.content.res.Resources r6 = r6.getResources()
                r1 = 2131166624(0x7f0705a0, float:1.7947499E38)
                int r6 = r6.getDimensionPixelSize(r1)
                float r6 = (float) r6
                r0.setElevation(r6)
                return
            L69:
                com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment r6 = com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.this
                android.view.View r6 = r6.surveyControlsContainer
                r6.setElevation(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment.ScrollShadowHandler.updateShadowVisibility(int):void");
        }
    }

    public final boolean areImportantReferencesValid() {
        if (getContext() != null && this.questionTextView != null && this.surveyQuestionHeader != null && this.surveyControlsContainer != null && this.scrollViewContents != null && this.scrollView != null) {
            return true;
        }
        return false;
    }

    public abstract View createScrollViewContents();

    public abstract String getQuestionText();

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CharSequence charSequence;
        View inflate = layoutInflater.inflate(R.layout.survey_question_with_scrollable_content, viewGroup, false);
        this.surveyQuestionHeader = inflate.findViewById(R.id.survey_question_header_logo_text);
        this.questionTextView = (TextView) inflate.findViewById(R.id.survey_question_text);
        if (bundle != null) {
            charSequence = bundle.getCharSequence("QuestionTextFromHtml");
        } else {
            charSequence = null;
        }
        if (charSequence == null) {
            charSequence = HtmlCompat.Api24Impl.fromHtml(getQuestionText(), 0);
        }
        this.questionTextView.setText(charSequence);
        this.questionTextView.setContentDescription(charSequence.toString());
        this.scrollViewContents = createScrollViewContents();
        ScrollViewWithSizeCallback scrollViewWithSizeCallback = (ScrollViewWithSizeCallback) inflate.findViewById(R.id.survey_question_scroll_view);
        this.scrollView = scrollViewWithSizeCallback;
        scrollViewWithSizeCallback.addView(this.scrollViewContents);
        ScrollViewWithSizeCallback scrollViewWithSizeCallback2 = this.scrollView;
        scrollViewWithSizeCallback2.onHeightChangedListener$ar$class_merging = this.scrollShadowHandler;
        if (!this.isOnScrollChangedListenerAttached && scrollViewWithSizeCallback2 != null) {
            scrollViewWithSizeCallback2.getViewTreeObserver().addOnScrollChangedListener(this.scrollShadowHandler);
            this.isOnScrollChangedListenerAttached = true;
        }
        LayoutUtils.updateImageViewWithLogo((ImageView) inflate.findViewById(R.id.survey_prompt_banner_logo), this.logoResId);
        SurveyActivityInterface activityIfRunning = getActivityIfRunning();
        if (activityIfRunning != null) {
            this.surveyControlsContainer = activityIfRunning.getActivity().findViewById(R.id.survey_controls_container);
        }
        ViewCompat.setAccessibilityPaneTitle(inflate, null);
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public final void onDestroyView() {
        ScrollViewWithSizeCallback scrollViewWithSizeCallback;
        if (this.isOnScrollChangedListenerAttached && (scrollViewWithSizeCallback = this.scrollView) != null) {
            scrollViewWithSizeCallback.getViewTreeObserver().removeOnScrollChangedListener(this.scrollShadowHandler);
            this.isOnScrollChangedListenerAttached = false;
        }
        super.onDestroyView();
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public void onPageScrolledIntoView() {
        View view;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if ((!FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext)) || areImportantReferencesValid()) && SurveyUtils.isInTalkBackMode(getContext()) && (view = this.surveyQuestionHeader) != null) {
            view.requestFocus();
            this.surveyQuestionHeader.sendAccessibilityEvent(8);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        TextView textView = this.questionTextView;
        if (textView != null) {
            bundle.putCharSequence("QuestionTextFromHtml", textView.getText());
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void updateQuestionText(String str) {
        Spanned fromHtml;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext)) || areImportantReferencesValid()) {
            fromHtml = HtmlCompat.Api24Impl.fromHtml(str, 0);
            this.questionTextView.setText(fromHtml);
            this.questionTextView.setContentDescription(fromHtml.toString());
        }
    }
}
