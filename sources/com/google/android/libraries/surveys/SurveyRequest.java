package com.google.android.libraries.surveys;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyRequest {
    public final String apiKey;
    public final Context clientContext;
    public final boolean enableProofMode;
    public final RequestSurveyCallback requestSurveyCallback;
    public final String triggerId;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public String apiKey;
        private final Context clientContext;
        public boolean enableProofMode;
        public RequestSurveyCallback requestSurveyCallback;
        private final String triggerId;

        public Builder(Context context, String str) {
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    this.clientContext = context;
                    this.triggerId = str;
                    return;
                }
                throw new IllegalArgumentException("Trigger ID cannot be null or empty.");
            }
            throw new IllegalArgumentException("Client context is not set.");
        }

        public final SurveyRequest build() {
            return new SurveyRequest(this.clientContext, this.triggerId, this.requestSurveyCallback, this.apiKey, this.enableProofMode);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ErrorType {
        BACKEND_TIMEOUT,
        FAILED_TO_FETCH_SURVEY,
        NO_AVAILABLE_SURVEY,
        TRIGGER_ID_NOT_SET,
        UNSUPPORTED_CRONET_ENGINE
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface RequestSurveyCallback {
        void onRequestFailed(String str, ErrorType errorType);

        void onRequestSuccess(SurveyData surveyData);
    }

    public SurveyRequest(Context context, String str, RequestSurveyCallback requestSurveyCallback, String str2, boolean z) {
        this.clientContext = context;
        this.triggerId = str;
        this.requestSurveyCallback = requestSurveyCallback;
        this.apiKey = str2;
        this.enableProofMode = z;
    }
}
