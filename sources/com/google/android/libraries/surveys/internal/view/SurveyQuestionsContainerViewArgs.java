package com.google.android.libraries.surveys.internal.view;

import android.os.Bundle;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.model.Answer;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$Session;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyQuestionsContainerViewArgs {
    public final Answer answer;
    public final Integer currentItem;
    public final boolean hideCloseButton;
    public final boolean isSubmitting;
    public final boolean keepNextButtonForLastQuestion;
    public final Integer logoResId;
    public final Bundle singleSelectOrdinalAnswerMappings;
    public final int startingQuestionIndex;
    public final PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle;
    public final Survey$Payload surveyPayload;
    public final Survey$Session surveySession;
    public final SurveyStyle surveyStyle;
    public final String triggerId;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Answer answer;
        public Integer currentItem;
        public boolean hideCloseButton;
        public boolean isSubmitting;
        public boolean keepNextButtonForLastQuestion;
        public Integer logoResId;
        public byte set$0;
        public Bundle singleSelectOrdinalAnswerMappings;
        public int startingQuestionIndex;
        public PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle;
        public Survey$Payload surveyPayload;
        public Survey$Session surveySession;
        public SurveyStyle surveyStyle;
        public String triggerId;

        public Builder() {
        }

        public final void setHideCloseButton$ar$ds(boolean z) {
            this.hideCloseButton = z;
            this.set$0 = (byte) (this.set$0 | 8);
        }

        public final void setIsSubmitting$ar$ds(boolean z) {
            this.isSubmitting = z;
            this.set$0 = (byte) (this.set$0 | 1);
        }

        public final void setKeepNextButtonForLastQuestion$ar$ds(boolean z) {
            this.keepNextButtonForLastQuestion = z;
            this.set$0 = (byte) (this.set$0 | 16);
        }

        public final void setStartingQuestionIndex$ar$ds(int i) {
            this.startingQuestionIndex = i;
            this.set$0 = (byte) (this.set$0 | 4);
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public SurveyQuestionsContainerViewArgs() {
    }

    public final Answer answer() {
        return this.answer;
    }

    public final Integer currentItem() {
        return this.currentItem;
    }

    public final boolean equals(Object obj) {
        Integer num;
        Integer num2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof SurveyQuestionsContainerViewArgs) {
            SurveyQuestionsContainerViewArgs surveyQuestionsContainerViewArgs = (SurveyQuestionsContainerViewArgs) obj;
            if (this.surveyPayload.equals(surveyQuestionsContainerViewArgs.surveyPayload()) && this.answer.equals(surveyQuestionsContainerViewArgs.answer()) && this.isSubmitting == surveyQuestionsContainerViewArgs.isSubmitting() && ((num = this.logoResId) != null ? num.equals(surveyQuestionsContainerViewArgs.logoResId()) : surveyQuestionsContainerViewArgs.logoResId() == null) && this.triggerId.equals(surveyQuestionsContainerViewArgs.triggerId()) && this.surveySession.equals(surveyQuestionsContainerViewArgs.surveySession()) && this.startingQuestionIndex == surveyQuestionsContainerViewArgs.startingQuestionIndex() && ((num2 = this.currentItem) != null ? num2.equals(surveyQuestionsContainerViewArgs.currentItem()) : surveyQuestionsContainerViewArgs.currentItem() == null) && this.surveyCompletionStyle.equals(surveyQuestionsContainerViewArgs.surveyCompletionStyle()) && this.hideCloseButton == surveyQuestionsContainerViewArgs.hideCloseButton() && this.keepNextButtonForLastQuestion == surveyQuestionsContainerViewArgs.keepNextButtonForLastQuestion() && this.surveyStyle.equals(surveyQuestionsContainerViewArgs.surveyStyle()) && this.singleSelectOrdinalAnswerMappings.equals(surveyQuestionsContainerViewArgs.singleSelectOrdinalAnswerMappings())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int i;
        int i2;
        int hashCode2 = ((this.surveyPayload.hashCode() ^ 1000003) * 1000003) ^ this.answer.hashCode();
        Integer num = this.logoResId;
        int i3 = 0;
        if (num == null) {
            hashCode = 0;
        } else {
            hashCode = num.hashCode();
        }
        int i4 = 1231;
        if (true != this.isSubmitting) {
            i = 1237;
        } else {
            i = 1231;
        }
        int hashCode3 = ((((((((((((hashCode2 * 1000003) ^ i) * 1000003) ^ 1237) * 1000003) ^ hashCode) * 1000003) ^ this.triggerId.hashCode()) * 1000003) ^ this.surveySession.hashCode()) * 1000003) ^ this.startingQuestionIndex) * 1000003;
        Integer num2 = this.currentItem;
        if (num2 != null) {
            i3 = num2.hashCode();
        }
        int hashCode4 = (((hashCode3 ^ i3) * 1000003) ^ this.surveyCompletionStyle.hashCode()) * 1000003;
        if (true != this.hideCloseButton) {
            i2 = 1237;
        } else {
            i2 = 1231;
        }
        int i5 = (hashCode4 ^ i2) * 1000003;
        if (true != this.keepNextButtonForLastQuestion) {
            i4 = 1237;
        }
        return ((((i5 ^ i4) * 1000003) ^ this.surveyStyle.hashCode()) * 1000003) ^ this.singleSelectOrdinalAnswerMappings.hashCode();
    }

    public final boolean hideCloseButton() {
        return this.hideCloseButton;
    }

    public final boolean isSubmitting() {
        return this.isSubmitting;
    }

    public final boolean keepNextButtonForLastQuestion() {
        return this.keepNextButtonForLastQuestion;
    }

    public final Integer logoResId() {
        return this.logoResId;
    }

    public final Bundle singleSelectOrdinalAnswerMappings() {
        return this.singleSelectOrdinalAnswerMappings;
    }

    public final int startingQuestionIndex() {
        return this.startingQuestionIndex;
    }

    public final PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle() {
        return this.surveyCompletionStyle;
    }

    public final Survey$Payload surveyPayload() {
        return this.surveyPayload;
    }

    public final Survey$Session surveySession() {
        return this.surveySession;
    }

    public final SurveyStyle surveyStyle() {
        return this.surveyStyle;
    }

    public final String toString() {
        Bundle bundle = this.singleSelectOrdinalAnswerMappings;
        SurveyStyle surveyStyle = this.surveyStyle;
        PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle = this.surveyCompletionStyle;
        Survey$Session survey$Session = this.surveySession;
        Answer answer = this.answer;
        return "SurveyQuestionsContainerViewArgs{surveyPayload=" + String.valueOf(this.surveyPayload) + ", answer=" + String.valueOf(answer) + ", isSubmitting=" + this.isSubmitting + ", ignoreFirstQuestion=false, logoResId=" + this.logoResId + ", triggerId=" + this.triggerId + ", surveySession=" + String.valueOf(survey$Session) + ", startingQuestionIndex=" + this.startingQuestionIndex + ", currentItem=" + this.currentItem + ", surveyCompletionStyle=" + String.valueOf(surveyCompletionStyle) + ", hideCloseButton=" + this.hideCloseButton + ", keepNextButtonForLastQuestion=" + this.keepNextButtonForLastQuestion + ", surveyStyle=" + String.valueOf(surveyStyle) + ", singleSelectOrdinalAnswerMappings=" + String.valueOf(bundle) + "}";
    }

    public final String triggerId() {
        return this.triggerId;
    }

    public SurveyQuestionsContainerViewArgs(Survey$Payload survey$Payload, Answer answer, boolean z, Integer num, String str, Survey$Session survey$Session, int i, Integer num2, PresentSurveyRequest.SurveyCompletionStyle surveyCompletionStyle, boolean z2, boolean z3, SurveyStyle surveyStyle, Bundle bundle) {
        this();
        this.surveyPayload = survey$Payload;
        this.answer = answer;
        this.isSubmitting = z;
        this.logoResId = num;
        this.triggerId = str;
        this.surveySession = survey$Session;
        this.startingQuestionIndex = i;
        this.currentItem = num2;
        this.surveyCompletionStyle = surveyCompletionStyle;
        this.hideCloseButton = z2;
        this.keepNextButtonForLastQuestion = z3;
        this.surveyStyle = surveyStyle;
        this.singleSelectOrdinalAnswerMappings = bundle;
    }
}
