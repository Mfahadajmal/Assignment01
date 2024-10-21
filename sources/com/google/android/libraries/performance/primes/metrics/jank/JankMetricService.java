package com.google.android.libraries.performance.primes.metrics.jank;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.libraries.surveys.internal.config.SurveyConfigProvider;
import com.google.android.libraries.surveys.internal.controller.SurveyControllerImpl;
import com.google.android.libraries.surveys.internal.network.NetworkExecutor;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.common.base.Functions$ConstantFunction;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import google.internal.feedback.v1.Service$GetSurveyStartupConfigRequest;
import google.internal.feedback.v1.Survey$ClientContext$LibraryInfo$Platform;
import io.grpc.CallCredentials$RequestInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JankMetricService {
    public JankMetricService() {
    }

    public static Uri addSuffix(Uri uri, String str) {
        return uri.buildUpon().path(String.valueOf(uri.getPath()).concat(str)).build();
    }

    public static JankMetricService createNetworkChannelBasedClient$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Context context, RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration) {
        context.getClass();
        ContextDataProvider.checkArgument(true, (Object) "SDK < 16 isn't supported");
        SurveyControllerImpl surveyControllerImpl = SurveyControllerImpl.instance;
        CurrentProcess currentProcess = new CurrentProcess();
        if (FlagsUtil.phenotypeContext == null) {
            synchronized (FlagsUtil.CONTEXT_LOCK) {
                if (FlagsUtil.phenotypeContext == null) {
                    FlagsUtil.phenotypeContext = context;
                }
            }
        }
        FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = currentProcess;
        long j = SurveyUtils.TIMEOUT_MS;
        SurveyConfigProvider.instance.pseudonymousIdProvider$ar$class_merging$ar$class_merging$ar$class_merging = new StatsStorage(context);
        MetricsLogger.getInstance().MetricsLogger$ar$loggerProvider$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
        SurveyConfigProvider.instance.networkCallerProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = remoteModelManagerRegistration;
        return new JankMetricService(null, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x0238, code lost:
    
        if (r0 != false) goto L344;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void presentSurvey$ar$ds(com.google.android.libraries.surveys.PresentSurveyRequest r20) {
        /*
            Method dump skipped, instructions count: 1419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.jank.JankMetricService.presentSurvey$ar$ds(com.google.android.libraries.surveys.PresentSurveyRequest):void");
    }

    public static void requestSurvey$ar$ds(SurveyRequest surveyRequest) {
        String str = surveyRequest.apiKey;
        SurveyControllerImpl surveyControllerImpl = SurveyControllerImpl.instance;
        surveyControllerImpl.requestApiKey = ContextDataProvider.nullToEmpty(str);
        TextUtils.isEmpty(surveyControllerImpl.requestApiKey);
        NetworkCaller createNetworkCaller = surveyControllerImpl.createNetworkCaller(surveyRequest, surveyControllerImpl.requestApiKey);
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Service$GetSurveyStartupConfigRequest.DEFAULT_INSTANCE.createBuilder();
        String str2 = surveyControllerImpl.requestApiKey;
        builder.copyOnWrite();
        Service$GetSurveyStartupConfigRequest service$GetSurveyStartupConfigRequest = (Service$GetSurveyStartupConfigRequest) builder.instance;
        str2.getClass();
        service$GetSurveyStartupConfigRequest.apiKey_ = str2;
        int i = Survey$ClientContext$LibraryInfo$Platform.PLATFORM_ANDROID$ar$edu$ca458a2c_0;
        builder.copyOnWrite();
        ((Service$GetSurveyStartupConfigRequest) builder.instance).platform_ = Survey$ClientContext$LibraryInfo$Platform.getNumber$ar$edu$ca458a2c_0(i);
        Service$GetSurveyStartupConfigRequest service$GetSurveyStartupConfigRequest2 = (Service$GetSurveyStartupConfigRequest) builder.build();
        NetworkCaller createNetworkCaller2 = surveyControllerImpl.createNetworkCaller(surveyRequest, "AIzaSyBmGDOmYcGmylWMKTdQxmf5vXWAuybv7qA");
        CallCredentials$RequestInfo callCredentials$RequestInfo = new CallCredentials$RequestInfo(surveyControllerImpl, surveyRequest, createNetworkCaller);
        if (service$GetSurveyStartupConfigRequest2 == null) {
            Log.e("NetworkCallerGrpc", "Survey startup config request was null");
        } else {
            long j = SurveyUtils.TIMEOUT_MS;
            NetworkExecutor.getNetworkExecutor().execute(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(createNetworkCaller2, service$GetSurveyStartupConfigRequest2, callCredentials$RequestInfo, 18));
        }
    }

    public static ListenableFuture toVoid(ListenableFuture listenableFuture) {
        return AbstractTransformFuture.create(listenableFuture, new Functions$ConstantFunction(), DirectExecutor.INSTANCE);
    }

    public /* synthetic */ JankMetricService(byte[] bArr) {
    }

    public JankMetricService(byte[] bArr, byte[] bArr2) {
        this(null);
    }
}
