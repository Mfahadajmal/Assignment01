package com.google.android.libraries.surveys.internal.network.provider;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.talkback.TouchInteractionMonitor$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.surveys.SurveyData;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.libraries.surveys.internal.model.SurveyDataImpl;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.Duration;
import com.google.scone.proto.Service$SurveyTriggerRequest;
import com.google.scone.proto.Service$SurveyTriggerResponse;
import com.google.scone.proto.Survey$DisplaySettings;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$Session;
import google.internal.feedback.v1.Service$GetSurveyStartupConfigResponse;
import io.grpc.CallCredentials$RequestInfo;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NetworkCaller {
    protected final String accountName;
    protected final String apiKey;
    public SurveyRequest.RequestSurveyCallback callback;
    protected final Context context;
    private final Handler handler = new Handler(Looper.getMainLooper());
    public final String triggerId;

    public NetworkCaller(Context context, String str, String str2, String str3) {
        this.context = context;
        this.triggerId = str;
        this.accountName = str2;
        this.apiKey = str3;
    }

    public final SurveyData buildSurveyData(Service$SurveyTriggerResponse service$SurveyTriggerResponse) {
        String str = service$SurveyTriggerResponse.surveyId_;
        Survey$Payload survey$Payload = service$SurveyTriggerResponse.surveyPayload_;
        if (survey$Payload == null) {
            survey$Payload = Survey$Payload.DEFAULT_INSTANCE;
        }
        SurveyDataImpl.Builder builder = new SurveyDataImpl.Builder(this.triggerId, str, survey$Payload);
        Survey$Session survey$Session = service$SurveyTriggerResponse.session_;
        if (survey$Session == null) {
            survey$Session = Survey$Session.DEFAULT_INSTANCE;
        }
        builder.session = survey$Session;
        builder.noSurveyReason = service$SurveyTriggerResponse.noAvailableSurveyReason_;
        builder.triggerTimeMs = System.currentTimeMillis();
        builder.backendErrors = ImmutableList.copyOf((Collection) service$SurveyTriggerResponse.error_);
        long j = builder.triggerTimeMs;
        if (j != 0) {
            return new SurveyDataImpl(builder.triggerId, builder.surveyId, j, builder.session, builder.surveyPayload, builder.noSurveyReason, builder.backendErrors);
        }
        throw new IllegalStateException("Trigger time is not set");
    }

    public final void handleGetSurveyStartupConfigResponse$ar$class_merging$ar$class_merging(Service$GetSurveyStartupConfigResponse service$GetSurveyStartupConfigResponse, CallCredentials$RequestInfo callCredentials$RequestInfo) {
        this.handler.post(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(callCredentials$RequestInfo, service$GetSurveyStartupConfigResponse, 11));
    }

    public final void handleSurveyTriggerResponse(Service$SurveyTriggerRequest service$SurveyTriggerRequest, Service$SurveyTriggerResponse service$SurveyTriggerResponse, Stopwatch stopwatch) {
        Runnable touchInteractionMonitor$$ExternalSyntheticLambda1;
        String str;
        if (service$SurveyTriggerResponse == null) {
            Log.e("SurveyNetworkConnection", "Survey trigger response was null for trigger id: ".concat(String.valueOf(this.triggerId)));
            return;
        }
        Survey$Payload survey$Payload = service$SurveyTriggerResponse.surveyPayload_;
        if (survey$Payload == null) {
            survey$Payload = Survey$Payload.DEFAULT_INSTANCE;
        }
        if (survey$Payload.question_.size() == 0) {
            runOnRequestFailedCallback(SurveyRequest.ErrorType.NO_AVAILABLE_SURVEY);
            return;
        }
        long j = SurveyUtils.TIMEOUT_MS;
        if (this.callback != null) {
            Survey$Payload survey$Payload2 = service$SurveyTriggerResponse.surveyPayload_;
            if (survey$Payload2 == null) {
                survey$Payload2 = Survey$Payload.DEFAULT_INSTANCE;
            }
            Survey$DisplaySettings survey$DisplaySettings = survey$Payload2.displaySettings_;
            if (survey$DisplaySettings == null) {
                survey$DisplaySettings = Survey$DisplaySettings.DEFAULT_INSTANCE;
            }
            Survey$DisplaySettings.PromptDelay promptDelay = survey$DisplaySettings.promptDelay_;
            if (promptDelay == null) {
                promptDelay = Survey$DisplaySettings.PromptDelay.DEFAULT_INSTANCE;
            }
            TimeUnit timeUnit = TimeUnit.SECONDS;
            Duration duration = promptDelay.minDelay_;
            if (duration == null) {
                duration = Duration.DEFAULT_INSTANCE;
            }
            long millis = timeUnit.toMillis(duration.seconds_);
            TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
            Duration duration2 = promptDelay.minDelay_;
            if (duration2 == null) {
                duration2 = Duration.DEFAULT_INSTANCE;
            }
            long millis2 = millis + timeUnit2.toMillis(duration2.nanos_);
            Handler handler = this.handler;
            if (millis2 < 100) {
                touchInteractionMonitor$$ExternalSyntheticLambda1 = new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, service$SurveyTriggerResponse, 9);
            } else {
                touchInteractionMonitor$$ExternalSyntheticLambda1 = new TouchInteractionMonitor$$ExternalSyntheticLambda1(this, millis2, service$SurveyTriggerResponse, 6);
            }
            handler.post(touchInteractionMonitor$$ExternalSyntheticLambda1);
            Context context = this.context;
            if (TextUtils.isEmpty(this.accountName)) {
                str = null;
            } else {
                str = this.accountName;
            }
            DefaultExperimentTokenDecorator.reportHttpEventForSurveyTrigger(service$SurveyTriggerRequest, service$SurveyTriggerResponse, stopwatch, context, str);
            return;
        }
        Log.w("SurveyNetworkConnection", "RequestSurveyCallback was null for trigger request.");
    }

    public final void runOnRequestFailedCallback(SurveyRequest.ErrorType errorType) {
        if (this.callback != null) {
            this.handler.post(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, errorType, 10, (byte[]) null));
        } else {
            Log.w("SurveyNetworkConnection", "RequestSurveyCallback was null for trigger request.");
        }
    }
}
