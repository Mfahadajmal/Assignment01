package com.google.android.libraries.surveys.internal.view;

import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.view.OpenTextView;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PromptDialogDelegate$$ExternalSyntheticLambda15 implements OpenTextView.OnOpenTextResponseListener {
    public final /* synthetic */ Object PromptDialogDelegate$$ExternalSyntheticLambda15$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PromptDialogDelegate$$ExternalSyntheticLambda15(Object obj, int i) {
        this.switching_field = i;
        this.PromptDialogDelegate$$ExternalSyntheticLambda15$ar$f$0 = obj;
    }

    @Override // com.google.android.libraries.surveys.internal.view.OpenTextView.OnOpenTextResponseListener
    public final void onOpenTextResponse(String str) {
        if (this.switching_field != 0) {
            ((OpenTextFragment) this.PromptDialogDelegate$$ExternalSyntheticLambda15$ar$f$0).openTextResponseAnswer = str;
            return;
        }
        PromptDialogDelegate promptDialogDelegate = (PromptDialogDelegate) this.PromptDialogDelegate$$ExternalSyntheticLambda15$ar$f$0;
        promptDialogDelegate.openTextResponseAnswer = str;
        SurveyInternalEvent surveyInternalEvent = promptDialogDelegate.getSurveyInternalEvent();
        if (surveyInternalEvent != null) {
            JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.didAnswerOpenTextQuestion(surveyInternalEvent);
        }
    }
}
