package com.google.android.libraries.surveys.internal.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.resourceutils.LayoutUtils;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.marvin.talkback.R;
import com.google.scone.proto.Survey$Event;
import googledata.experiments.mobile.surveys_android.features.HatsV1M11Bugfixes;
import googledata.experiments.mobile.surveys_android.features.HatsV1M16Bugfixes;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThankYouFragment extends BaseFragment {
    private LinearLayout thankYouFragmentContainer;

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void animateFadeIn() {
        if (this.thankYouFragmentContainer != null) {
            int i = 0;
            while (i < this.thankYouFragmentContainer.getChildCount()) {
                View childAt = this.thankYouFragmentContainer.getChildAt(i);
                childAt.setAlpha(0.0f);
                i++;
                childAt.animate().alpha(1.0f).setDuration(150L).setStartDelay(i * 80);
            }
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final Survey$Event.QuestionAnswered computeQuestionResponse() {
        return null;
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Spanned fromHtml;
        this.thankYouFragmentContainer = (LinearLayout) layoutInflater.inflate(R.layout.survey_thank_you_fragment, viewGroup, false);
        int i = getArguments().getInt("DisplayLogoResId", 0);
        LayoutUtils.updateImageViewWithLogo((ImageView) this.thankYouFragmentContainer.findViewById(R.id.survey_prompt_banner_logo), Integer.valueOf(i));
        TextView textView = (TextView) this.thankYouFragmentContainer.findViewById(R.id.survey_question_text);
        fromHtml = HtmlCompat.Api24Impl.fromHtml(this.completion.completionText_, 0);
        textView.setText(fromHtml);
        textView.setContentDescription(fromHtml.toString());
        textView.announceForAccessibility(textView.getContentDescription());
        final String followUpUrl = SurveyUtils.getFollowUpUrl(this.completion.followUpUrl_);
        if (!TextUtils.isEmpty(followUpUrl)) {
            TextView textView2 = (TextView) this.thankYouFragmentContainer.findViewById(R.id.survey_follow_up_url);
            CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (HatsV1M16Bugfixes.INSTANCE.get().fixThankYouUrlMarginIssue(requireContext()) && i == 0 && (textView2.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                ((ViewGroup.MarginLayoutParams) textView2.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(R.dimen.survey_thank_you_url_start_margin_state_no_icon));
            }
            String str = this.completion.followUpText_;
            if (TextUtils.isEmpty(str)) {
                str = getResources().getString(R.string.survey_thank_you_followup_message);
            }
            textView2.setContentDescription(str);
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ClickableSpan(this) { // from class: com.google.android.libraries.surveys.internal.view.ThankYouFragment.1
                final /* synthetic */ ThankYouFragment this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.text.style.ClickableSpan
                public final void onClick(View view) {
                    this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(followUpUrl)));
                }
            }, 0, str.length(), 0);
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setText(spannableString);
            if (SurveyUtils.isInTalkBackMode(getContext())) {
                textView2.setClickable(false);
                textView2.setLongClickable(false);
            }
            textView2.setOnTouchListener(new ThankYouFragment$$ExternalSyntheticLambda0(0));
            textView2.setVisibility(0);
        }
        return this.thankYouFragmentContainer;
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void onPageScrolledIntoView() {
        getActivityIfRunning().showNextButton$ar$ds();
        getActivityIfRunning().onQuestionProgressableChanged(true, this);
        if (TextUtils.isEmpty(SurveyUtils.getFollowUpUrl(this.completion.followUpUrl_)) && getActivityIfRunning() != null) {
            CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (FlagsUtil.isBugfixEnabled(HatsV1M11Bugfixes.INSTANCE.get().fixHideThankyouCloseButton(FlagsUtil.phenotypeContext))) {
                getActivityIfRunning().hideCloseButton();
            }
            getActivityIfRunning().autoCloseActivityWithDelay();
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.BaseFragment
    public final void updateQuestionText(String str) {
    }
}
