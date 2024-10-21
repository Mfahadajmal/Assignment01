package com.google.android.libraries.surveys.internal.event;

import com.google.android.libraries.surveys.internal.model.SurveyStyle;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyInternalEvent {
    public final String sessionId;
    public final SurveyStyle surveyStyle;
    private final String triggerId;

    public SurveyInternalEvent() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SurveyInternalEvent) {
            SurveyInternalEvent surveyInternalEvent = (SurveyInternalEvent) obj;
            if (this.sessionId.equals(surveyInternalEvent.sessionId()) && this.triggerId.equals(surveyInternalEvent.triggerId()) && this.surveyStyle.equals(surveyInternalEvent.surveyStyle())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.sessionId.hashCode() ^ 1000003) * 1000003) ^ this.triggerId.hashCode()) * 1000003) ^ this.surveyStyle.hashCode();
    }

    public final String sessionId() {
        return this.sessionId;
    }

    public final SurveyStyle surveyStyle() {
        return this.surveyStyle;
    }

    public final String toString() {
        return "SurveyInternalEvent{sessionId=" + this.sessionId + ", triggerId=" + this.triggerId + ", surveyStyle=" + String.valueOf(this.surveyStyle) + "}";
    }

    public final String triggerId() {
        return this.triggerId;
    }

    public SurveyInternalEvent(String str, String str2, SurveyStyle surveyStyle) {
        this();
        this.sessionId = str;
        this.triggerId = str2;
        this.surveyStyle = surveyStyle;
    }
}
