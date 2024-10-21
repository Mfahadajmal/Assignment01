package com.google.android.libraries.surveys.internal.view;

import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.libraries.surveys.internal.view.MultipleSelectView;
import com.google.mlkit.common.model.RemoteModelManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PromptDialogDelegate$$ExternalSyntheticLambda3 implements MultipleSelectView.OnAnswerSelectClickListener {
    public final /* synthetic */ Object PromptDialogDelegate$$ExternalSyntheticLambda3$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PromptDialogDelegate$$ExternalSyntheticLambda3(Object obj, int i) {
        this.switching_field = i;
        this.PromptDialogDelegate$$ExternalSyntheticLambda3$ar$f$0 = obj;
    }

    @Override // com.google.android.libraries.surveys.internal.view.MultipleSelectView.OnAnswerSelectClickListener
    public final void onClickAnswerSelect$ar$class_merging$ar$class_merging$ar$class_merging(RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration) {
        if (this.switching_field != 0) {
            Object obj = this.PromptDialogDelegate$$ExternalSyntheticLambda3$ar$f$0;
            SurveyActivityInterface activityIfRunning = ((BaseFragment) obj).getActivityIfRunning();
            if (activityIfRunning == null) {
                Log.w("SurveyMultiSelectFrag", "Activity was null, finishing or destroyed while attempting to navigate to the next page. Likely the user rotated the device before the Runnable executed.");
                return;
            }
            if (!remoteModelManagerRegistration.isAnswerValid()) {
                activityIfRunning.setNextButtonEnabled(false);
                return;
            }
            MultipleSelectFragment multipleSelectFragment = (MultipleSelectFragment) obj;
            multipleSelectFragment.multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging = remoteModelManagerRegistration;
            multipleSelectFragment.questionMetrics.markAsAnswered();
            activityIfRunning.onQuestionProgressableChanged(multipleSelectFragment.isResponseSatisfactory(), (Fragment) obj);
            return;
        }
        boolean isAnswerValid = remoteModelManagerRegistration.isAnswerValid();
        Object obj2 = this.PromptDialogDelegate$$ExternalSyntheticLambda3$ar$f$0;
        if (!isAnswerValid) {
            ((PromptDialogDelegate) obj2).setNextButtonEnabled(false);
            return;
        }
        PromptDialogDelegate promptDialogDelegate = (PromptDialogDelegate) obj2;
        promptDialogDelegate.multiSelectAnswer$ar$class_merging$ar$class_merging$ar$class_merging = remoteModelManagerRegistration;
        promptDialogDelegate.questionMetrics.markAsAnswered();
        promptDialogDelegate.setNextButtonEnabled(true);
    }
}
