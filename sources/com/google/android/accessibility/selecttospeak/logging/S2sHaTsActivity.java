package com.google.android.accessibility.selecttospeak.logging;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.util.Pair;
import com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda2;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SEntryPoint;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.SurveyData;
import com.google.android.libraries.surveys.SurveyMetadata;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sHaTsActivity extends AppCompatActivity {
    public static final SpannableUtils$NonCopyableTextSpan Companion$ar$class_merging$3cdfa3c6_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    private SurveyData surveyData;

    public final void cleanUpAndFinish() {
        S2sHatsSurveyRequester s2sHatsSurveyRequester = S2sHatsSurveyRequester.INSTANCE;
        S2sHatsSurveyRequester.cleanUp$ar$ds();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        SurveyData surveyData;
        Object parcelableExtra;
        super.onCreate(bundle);
        if (SpannableUtils$IdentifierSpan.isAtLeastT()) {
            parcelableExtra = getIntent().getParcelableExtra("KEY_SURVEY_DATA", SurveyData.class);
            surveyData = (SurveyData) parcelableExtra;
        } else {
            surveyData = (SurveyData) getIntent().getParcelableExtra("KEY_SURVEY_DATA");
        }
        this.surveyData = surveyData;
        setContentView(R.layout.layout_hats_s2s_service);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.s2s_transparent_bg);
        if (frameLayout != null) {
            frameLayout.setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        cleanUpAndFinish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onStart() {
        super.onStart();
        if (this.surveyData != null) {
            S2sHatsSurveyRequester s2sHatsSurveyRequester = S2sHatsSurveyRequester.INSTANCE;
            SurveyData surveyData = this.surveyData;
            surveyData.getClass();
            int i = A11yS2SProtoEnums$A11yS2SEntryPoint.ENTRY_A11Y_SERVICE$ar$edu;
            final OnBackPressedDispatcher.AnonymousClass1 anonymousClass1 = new OnBackPressedDispatcher.AnonymousClass1(this, 17);
            if (i != 0) {
                PresentSurveyRequest.SurveyEventListener surveyEventListener = new PresentSurveyRequest.SurveyEventListener() { // from class: com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$presentSurvey$surveyEventListener$1
                    @Override // com.google.android.libraries.surveys.PresentSurveyRequest.SurveyEventListener
                    public final void onPresentSurveyFailed$ar$ds(PresentSurveyRequest.ErrorType errorType) {
                        LogUtils.e("HatsSurveyRequester", "Survey onPresentSurveyFailed : %s", errorType);
                        if (errorType != PresentSurveyRequest.ErrorType.SURVEY_ALREADY_RUNNING) {
                            anonymousClass1.invoke(false);
                            S2sHatsSurveyRequester s2sHatsSurveyRequester2 = S2sHatsSurveyRequester.INSTANCE;
                            S2sHatsSurveyRequester.cleanUp$ar$ds();
                        }
                    }

                    @Override // com.google.android.libraries.surveys.PresentSurveyRequest.SurveyEventListener
                    public final void onSurveyClosed$ar$ds() {
                        LogUtils.v("HatsSurveyRequester", "onSurveyClosed", new Object[0]);
                        Context applicationContext = this.getApplicationContext();
                        applicationContext.getClass();
                        Toast.makeText(applicationContext, R.string.survey_closed_thank_you, 0).show();
                        anonymousClass1.invoke(true);
                        S2sHatsSurveyRequester s2sHatsSurveyRequester2 = S2sHatsSurveyRequester.INSTANCE;
                        S2sHatsSurveyRequester.cleanUp$ar$ds();
                    }

                    @Override // com.google.android.libraries.surveys.PresentSurveyRequest.SurveyEventListener
                    public final void onSurveyPrompted(SurveyMetadata surveyMetadata) {
                        LogUtils.v("HatsSurveyRequester", "onSurveyPrompted", new Object[0]);
                    }
                };
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Pair("entry_point", A11yS2SProtoEnums$A11yS2SEntryPoint.toStringGeneratedd7c9859b585c301d(i)));
                PresentSurveyRequest.Builder builder = new PresentSurveyRequest.Builder(this, surveyData);
                builder.surveyEventListener = surveyEventListener;
                builder.insertIntoParent$ar$ds(R.id.s2s_survey_parent, 340);
                builder.psd = arrayList;
                Context applicationContext = getApplicationContext();
                applicationContext.getClass();
                S2sHatsSurveyRequester.getSurveysClient$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(applicationContext);
                JankMetricService.presentSurvey$ar$ds(builder.build());
                return;
            }
            throw null;
        }
        cleanUpAndFinish();
    }
}
