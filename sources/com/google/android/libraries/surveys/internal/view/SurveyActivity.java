package com.google.android.libraries.surveys.internal.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageButton;
import androidx.activity.OnBackPressedCallback;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.surveys_android.features.HatsV1M15Bugfixes;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Bugfixes;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SurveyActivity extends AppCompatActivity implements SurveyActivityInterface {
    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback() { // from class: com.google.android.libraries.surveys.internal.view.SurveyActivity.1
        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            SurveyActivityImpl surveyActivityImpl = SurveyActivity.this.surveyActivityImpl;
            surveyActivityImpl.setAnswerTypeAndTransmit$ar$edu(6);
            if (surveyActivityImpl.isSubmitting) {
                surveyActivityImpl.activity.setResult(-1, new Intent().putExtra("EXTRA_BACK_BUTTON_PRESSED", true));
            }
            surveyActivityImpl.activity.finish();
        }
    };
    public SurveyActivityImpl surveyActivityImpl;

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final void autoCloseActivityWithDelay() {
        this.surveyActivityImpl.autoCloseActivityWithDelay();
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final void hideCloseButton() {
        ImageButton imageButton = (ImageButton) this.surveyActivityImpl.findViewById(R.id.survey_close_button);
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final boolean isRunningEmbeddedFlow() {
        return false;
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityCallbacks
    public final boolean isSurveyControlRequired() {
        return this.surveyActivityImpl.isSurveyControlRequired();
    }

    @Override // com.google.android.libraries.surveys.internal.view.NextPageOrSubmitActionable
    public final void nextPageOrSubmit() {
        this.surveyActivityImpl.nextPageOrSubmit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0202  */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onCreate(android.os.Bundle r18) {
        /*
            Method dump skipped, instructions count: 905
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.view.SurveyActivity.onCreate(android.os.Bundle):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        SurveyActivityImpl surveyActivityImpl = this.surveyActivityImpl;
        if (FlagsUtil.phenotypeContext == null) {
            return;
        }
        if (FlagsUtil.useSurveyStore()) {
            SurveyInternalEvent surveyInternalEvent = surveyActivityImpl.getSurveyInternalEvent();
            if (surveyActivityImpl.activity.isFinishing() && surveyInternalEvent != null) {
                JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.onSurveyFinished(surveyInternalEvent);
            }
        } else if (surveyActivityImpl.activity.isFinishing()) {
            JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging.onSurveyFinished();
        }
        surveyActivityImpl.activityFinishHandler.removeCallbacks(surveyActivityImpl.delayedAutoClose);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SurveyActivityImpl surveyActivityImpl = this.surveyActivityImpl;
        if (intent.getBooleanExtra("IsDismissing", false)) {
            surveyActivityImpl.activity.finish();
        }
        if (intent.hasExtra("IsPausing")) {
            surveyActivityImpl.onPauseSurvey(intent.getBooleanExtra("IsPausing", false));
        }
    }

    @Override // com.google.android.libraries.surveys.internal.view.OnQuestionProgressableChangeListener
    public final void onQuestionProgressableChanged(boolean z, Fragment fragment) {
        SurveyActivityImpl surveyActivityImpl = this.surveyActivityImpl;
        if (!surveyActivityImpl.isSubmitting && SurveyViewPagerAdapter.getQuestionIndex(fragment) == surveyActivityImpl.surveyViewPager.mCurItem) {
            surveyActivityImpl.setNextButtonEnabled(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        int i;
        super.onSaveInstanceState(bundle);
        SurveyActivityImpl surveyActivityImpl = this.surveyActivityImpl;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixSplitWindowCrashes(FlagsUtil.phenotypeContext))) {
            SurveyViewPager surveyViewPager = surveyActivityImpl.surveyViewPager;
            if (surveyViewPager != null) {
                i = surveyViewPager.mCurItem;
            } else {
                i = 0;
            }
            bundle.putInt("CurrentQuestionIndexForViewPager", i);
        } else {
            bundle.putInt("CurrentQuestionIndexForViewPager", surveyActivityImpl.getCurrentQuestionIndexForSurveyPayload());
        }
        bundle.putBoolean("IsSubmitting", surveyActivityImpl.isSubmitting);
        bundle.putParcelable("Answer", surveyActivityImpl.answer);
        bundle.putBundle("SingleSelectOrdinalAnswerMappings", surveyActivityImpl.singleSelectOrdinalAnswerMappings);
    }

    @Override // android.app.Activity
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!HatsV1M15Bugfixes.fixTouchEventCrash(this)) {
            return this.surveyActivityImpl.onTouchEvent(motionEvent);
        }
        if (this.surveyActivityImpl.onTouchEvent(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.google.android.libraries.surveys.internal.view.NextPageOrSubmitActionable
    public final void setNextButtonEnabled(boolean z) {
        this.surveyActivityImpl.setNextButtonEnabled(z);
    }

    @Override // com.google.android.libraries.surveys.internal.view.NextPageOrSubmitActionable
    public final void showNextButton$ar$ds() {
        this.surveyActivityImpl.showNextButton(false);
    }

    @Override // com.google.android.libraries.surveys.internal.view.SurveyActivityInterface
    public final Activity getActivity() {
        return this;
    }
}
