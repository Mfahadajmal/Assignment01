package com.google.android.libraries.performance.primes.metrics.jank;

import android.os.Bundle;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.model.Answer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$Session;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JankObserverFactory {
    public static FloatingActionButton.ShadowDelegateImpl eventListener$ar$class_merging$ar$class_merging$ar$class_merging;

    public JankObserverFactory() {
    }

    public static Bundle createPromptDialogFragmentArgs$ar$ds(String str, Survey$Payload survey$Payload, Survey$Session survey$Session, Answer answer, PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle, PresentSurveyRequest.SurveyPromptStyle surveyPromptStyle, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("TriggerId", str);
        bundle.putByteArray("SurveyPayload", survey$Payload.toByteArray());
        bundle.putByteArray("SurveySession", survey$Session.toByteArray());
        bundle.putParcelable("Answer", answer);
        bundle.putInt("LogoResId", 0);
        bundle.putSerializable("SurveyCompletionCode", surveyCompletionStyle);
        bundle.putSerializable("SurveyPromptCode", surveyPromptStyle);
        bundle.putString("SurveyActivityClassName", "com.google.android.libraries.surveys.internal.view.SurveyActivity");
        bundle.putBoolean("keepNextButtonForLastQuestion", false);
        return bundle;
    }

    public JankObserverFactory(byte[] bArr) {
    }

    public JankObserverFactory(Provider provider) {
        provider.getClass();
    }
}
