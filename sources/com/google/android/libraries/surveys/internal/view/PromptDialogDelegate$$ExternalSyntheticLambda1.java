package com.google.android.libraries.surveys.internal.view;

import android.view.View;
import android.widget.LinearLayout;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PromptDialogDelegate$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ Object PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$0;
    public final /* synthetic */ Object PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$1;
    public final /* synthetic */ String f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PromptDialogDelegate$$ExternalSyntheticLambda1(PromptDialogDelegate promptDialogDelegate, LinearLayout linearLayout, String str, int i) {
        this.switching_field = i;
        this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$0 = promptDialogDelegate;
        this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$1 = linearLayout;
        this.f$2 = str;
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [android.view.View$OnClickListener, java.lang.Object] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    Stopwatch stopwatch = new Stopwatch();
                    ((MultipleSelectView) this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$0).onAnswerSelectClickListener = null;
                    PromptDialogDelegate promptDialogDelegate = (PromptDialogDelegate) this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$1;
                    promptDialogDelegate.transmitOtherAccess(promptDialogDelegate.themeContext, promptDialogDelegate.triggerId, promptDialogDelegate.session, SurveyUtils.isPiiCollectionEnabled(promptDialogDelegate.surveyPayload));
                    promptDialogDelegate.dialogFragment.dismissAllowingStateLoss();
                    DefaultExperimentTokenDecorator.reportUserEventForCloseButtonClicked(stopwatch, promptDialogDelegate.themeContext, this.f$2);
                    return;
                }
                Stopwatch stopwatch2 = new Stopwatch();
                this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$0.onClick(view);
                DefaultExperimentTokenDecorator.reportUserEventForNextButtonClicked(stopwatch2, ((PromptDialogDelegate) this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$1).themeContext, this.f$2);
                return;
            }
            Stopwatch stopwatch3 = new Stopwatch();
            ((SingleSelectView) this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$1).onAnswerSelectClickListener = null;
            PromptDialogDelegate promptDialogDelegate2 = (PromptDialogDelegate) this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$0;
            promptDialogDelegate2.transmitOtherAccess(promptDialogDelegate2.themeContext, promptDialogDelegate2.triggerId, promptDialogDelegate2.session, SurveyUtils.isPiiCollectionEnabled(promptDialogDelegate2.surveyPayload));
            promptDialogDelegate2.dialogFragment.dismissAllowingStateLoss();
            DefaultExperimentTokenDecorator.reportUserEventForCloseButtonClicked(stopwatch3, promptDialogDelegate2.themeContext, this.f$2);
            return;
        }
        Stopwatch stopwatch4 = new Stopwatch();
        ((RatingView) this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$1).onRatingClickListener = null;
        PromptDialogDelegate promptDialogDelegate3 = (PromptDialogDelegate) this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$0;
        promptDialogDelegate3.transmitOtherAccess(promptDialogDelegate3.themeContext, promptDialogDelegate3.triggerId, promptDialogDelegate3.session, SurveyUtils.isPiiCollectionEnabled(promptDialogDelegate3.surveyPayload));
        promptDialogDelegate3.dialogFragment.dismissAllowingStateLoss();
        DefaultExperimentTokenDecorator.reportUserEventForCloseButtonClicked(stopwatch4, promptDialogDelegate3.themeContext, this.f$2);
    }

    public /* synthetic */ PromptDialogDelegate$$ExternalSyntheticLambda1(PromptDialogDelegate promptDialogDelegate, Object obj, String str, int i) {
        this.switching_field = i;
        this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$1 = promptDialogDelegate;
        this.PromptDialogDelegate$$ExternalSyntheticLambda1$ar$f$0 = obj;
        this.f$2 = str;
    }
}
