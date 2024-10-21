package com.google.android.libraries.surveys.internal.controller;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.libraries.surveys.internal.config.SurveyConfigProvider;
import com.google.android.libraries.surveys.internal.datastore.SurveyDataStore;
import com.google.android.libraries.surveys.internal.model.SurveyDataImpl;
import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.collect.ImmutableList;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$LibraryEvent;
import googledata.experiments.mobile.surveys_android.features.Clearcut;
import j$.time.Instant;
import j$.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyControllerImpl {
    public static final SurveyControllerImpl instance;
    public static final AtomicBoolean isSurveyRunning;
    public String accountName;
    public long lastTriggerRequestTimeMs;
    public long minValidTriggerTimeMs;
    public ImmutableList psd;
    public String requestApiKey;
    public SurveyDataImpl surveyData;
    public PresentSurveyRequest.SurveyEventListener surveyEventListener;
    public final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    public final String surveyActivityClassName = "com.google.android.libraries.surveys.internal.view.SurveyActivity";
    public final SurveyDataStore surveyDataStore = SurveyDataStore.instance;

    static {
        Arrays.asList("com.google.android.surveys.testapp", "com.google.android.maps", "com.google.android.apps.tv.launcherx", "com.google.android.tvrecommendations");
        instance = new SurveyControllerImpl();
        isSurveyRunning = new AtomicBoolean(false);
    }

    private SurveyControllerImpl() {
        Instant truncatedTo;
        JankObserverFactory.eventListener$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        this.lastTriggerRequestTimeMs = 0L;
        truncatedTo = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        this.minValidTriggerTimeMs = truncatedTo.toEpochMilli();
    }

    public static void markSurveyFinished() {
        AtomicBoolean atomicBoolean = isSurveyRunning;
        synchronized (atomicBoolean) {
            if (!atomicBoolean.get()) {
                Log.e("SurveyController", "Notified that survey was destroyed when it wasn't marked as running.");
            }
            atomicBoolean.set(false);
        }
    }

    public static void markSurveyRunning() {
        AtomicBoolean atomicBoolean = isSurveyRunning;
        synchronized (atomicBoolean) {
            atomicBoolean.set(true);
        }
    }

    public final NetworkCaller createNetworkCaller(SurveyRequest surveyRequest, String str) {
        NetworkCaller createNetworkCaller = SurveyConfigProvider.instance.networkCallerProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.createNetworkCaller(surveyRequest.clientContext, surveyRequest.triggerId, "", str);
        ((NetworkCallerGrpc) createNetworkCaller).callback = surveyRequest.requestSurveyCallback;
        return createNetworkCaller;
    }

    public final void reportLibraryEventForPresentSurvey(UserVoiceSurveysLogging$LibraryEvent.PresentSurveyCallInfo presentSurveyCallInfo, Stopwatch stopwatch, Context context) {
        String str;
        if (TextUtils.isEmpty(this.accountName)) {
            str = null;
        } else {
            str = this.accountName;
        }
        String str2 = str;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
            return;
        }
        MetricsLogger metricsLogger = MetricsLogger.getInstance();
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$LibraryEvent.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        UserVoiceSurveysLogging$LibraryEvent userVoiceSurveysLogging$LibraryEvent = (UserVoiceSurveysLogging$LibraryEvent) builder.instance;
        presentSurveyCallInfo.getClass();
        userVoiceSurveysLogging$LibraryEvent.event_ = presentSurveyCallInfo;
        userVoiceSurveysLogging$LibraryEvent.eventCase_ = 4;
        metricsLogger.reportLibraryEvent((UserVoiceSurveysLogging$LibraryEvent) builder.build(), stopwatch.getStart(), stopwatch.getElapsed(), context, str2);
    }

    public final void runOnPresentSurveyFailedCallback(PresentSurveyRequest.ErrorType errorType, SurveyDataImpl surveyDataImpl) {
        PresentSurveyRequest.SurveyEventListener surveyEventListener = this.surveyEventListener;
        if (surveyEventListener != null) {
            surveyDataImpl.getSurveyMetadata();
            surveyEventListener.onPresentSurveyFailed$ar$ds(errorType);
        }
    }
}
