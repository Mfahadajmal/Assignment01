package com.google.android.libraries.surveys.internal.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.scone.proto.Survey$Completion;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$Question;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseFragment extends Fragment {
    protected Survey$Completion completion;
    protected Integer logoResId;
    public Survey$Question question;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle createArguments(Survey$Question survey$Question, Integer num, int i) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("Question", survey$Question.toByteArray());
        if (num != null) {
            bundle.putInt("DisplayLogoResId", num.intValue());
        }
        bundle.putInt("QuestionIndex", i);
        return bundle;
    }

    public abstract Survey$Event.QuestionAnswered computeQuestionResponse();

    /* JADX INFO: Access modifiers changed from: protected */
    public final SurveyActivityInterface getActivityIfRunning() {
        SurveyActivityInterface surveyActivityInterface;
        Activity activity;
        Object context = getContext();
        LifecycleOwner parentFragment = getParentFragment();
        if (parentFragment instanceof SurveyActivityInterface) {
            return (SurveyActivityInterface) parentFragment;
        }
        if (!(context instanceof SurveyActivityInterface) || (activity = (surveyActivityInterface = (SurveyActivityInterface) context).getActivity()) == null || activity.isFinishing() || activity.isDestroyed()) {
            return null;
        }
        return surveyActivityInterface;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        Integer num;
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        byte[] byteArray = arguments.getByteArray("Question");
        if (byteArray != null) {
            this.question = (Survey$Question) SurveyUtils.getMessage(Survey$Question.DEFAULT_INSTANCE, byteArray);
        }
        if (arguments.containsKey("DisplayLogoResId")) {
            num = Integer.valueOf(arguments.getInt("DisplayLogoResId", 0));
        } else {
            num = null;
        }
        this.logoResId = num;
        arguments.getInt("QuestionIndex");
        byte[] byteArray2 = arguments.getByteArray("Completion");
        if (byteArray2 != null) {
            this.completion = (Survey$Completion) SurveyUtils.getMessage(Survey$Completion.DEFAULT_INSTANCE, byteArray2);
        }
    }

    public abstract void onPageScrolledIntoView();

    public abstract void updateQuestionText(String str);

    public void animateFadeIn() {
    }
}
