package com.google.android.libraries.surveys;

import android.app.Activity;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PresentSurveyRequest {
    public final Activity clientActivity;
    public final Integer maxPromptWidth;
    public final int parentResId;
    public final List psd;
    public final SurveyCompletionStyle surveyCompletionStyle;
    public final SurveyData surveyData;
    public final SurveyEventListener surveyEventListener;
    public final SurveyPromptStyle surveyPromptStyle;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private final Activity clientActivity;
        private Integer maxPromptWidth;
        private int parentResId;
        public List psd;
        private final SurveyData surveyData;
        public SurveyEventListener surveyEventListener;
        private SurveyPromptStyle surveyPromptStyle = SurveyPromptStyle.FIRST_CARD_NON_MODAL;
        private SurveyCompletionStyle surveyCompletionStyle = SurveyCompletionStyle.CARD;

        public Builder(Activity activity, SurveyData surveyData) {
            this.clientActivity = activity;
            this.surveyData = surveyData;
        }

        public final PresentSurveyRequest build() {
            return new PresentSurveyRequest(this.clientActivity, this.parentResId, this.maxPromptWidth, this.surveyEventListener, this.surveyData, this.psd, this.surveyPromptStyle, this.surveyCompletionStyle);
        }

        public final void insertIntoParent$ar$ds(int i, Integer num) {
            num.intValue();
            this.parentResId = i;
            this.maxPromptWidth = num;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ErrorType {
        CLIENT_ACTIVITY_WAS_DESTROYED,
        CLIENT_ACTIVITY_WAS_FINISHING,
        CLIENT_ACTIVITY_WAS_NULL,
        INVALID_COMPLETION_STYLE,
        INVALID_PROMPT_STYLE,
        INVALID_SURVEY_DATA_TYPE,
        INVALID_SURVEY_PAYLOAD,
        SURVEY_ALREADY_RUNNING,
        SURVEY_EXPIRED,
        UNSUPPORTED_EMBEDDED_SURVEY_CONTAINER
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum SurveyCompletionStyle {
        CARD,
        TOAST,
        SILENT
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface SurveyEventListener {
        void onPresentSurveyFailed$ar$ds(ErrorType errorType);

        void onSurveyClosed$ar$ds();

        void onSurveyPrompted(SurveyMetadata surveyMetadata);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum SurveyPromptStyle {
        FIRST_CARD_MODAL,
        FIRST_CARD_NON_MODAL
    }

    public PresentSurveyRequest(Activity activity, int i, Integer num, SurveyEventListener surveyEventListener, SurveyData surveyData, List list, SurveyPromptStyle surveyPromptStyle, SurveyCompletionStyle surveyCompletionStyle) {
        this.clientActivity = activity;
        this.parentResId = i;
        this.maxPromptWidth = num;
        this.surveyEventListener = surveyEventListener;
        this.surveyData = surveyData;
        this.psd = list;
        this.surveyPromptStyle = surveyPromptStyle;
        this.surveyCompletionStyle = surveyCompletionStyle;
    }
}
