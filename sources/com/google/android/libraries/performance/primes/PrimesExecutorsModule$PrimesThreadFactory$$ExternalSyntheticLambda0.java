package com.google.android.libraries.performance.primes;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.text.TextUtils;
import android.util.Log;
import androidx.viewpager.widget.ViewPager;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentUiProviderImpl;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor;
import com.google.android.libraries.performance.primes.sampling.Sampler;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.stable.FlagStore;
import com.google.android.libraries.phenotype.client.stable.PackageInfo;
import com.google.android.libraries.phenotype.client.stable.PhenotypeStickyAccount;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.libraries.surveys.internal.controller.SurveyControllerImpl;
import com.google.android.libraries.surveys.internal.datastore.SurveyDataStore;
import com.google.android.libraries.surveys.internal.network.NetworkExecutor;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.libraries.surveys.internal.view.SurveyViewPager;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.SplitInstallManagerImpl;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import com.google.common.android.concurrent.ParcelableFuture;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.LogSiteKey;
import com.google.common.flogger.LogSiteMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.components.OptionalProvider;
import com.google.firebase.inject.Provider;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$LibraryEvent;
import com.google.scone.proto.Service$SurveyTriggerRequest;
import com.google.scone.proto.Service$SurveyTriggerResponse;
import com.google.scone.proto.Survey$ClientContext;
import com.google.scone.proto.Survey$TriggerContext;
import googledata.experiments.mobile.surveys_android.features.Clearcut;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Bugfixes;
import googledata.experiments.mobile.surveys_android.features.HatsV1M5Features;
import io.grpc.CallCredentials$RequestInfo;
import j$.time.Instant;
import j$.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Object PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1;
    private final /* synthetic */ int switching_field;

    public PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(LogSiteMap logSiteMap, LogSiteKey logSiteKey, int i) {
        this.switching_field = i;
        this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0 = logSiteKey;
        this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1 = logSiteMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Object, dagger.Lazy] */
    /* JADX WARN: Type inference failed for: r0v30, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v31, types: [java.util.concurrent.Future, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.Set, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v9, types: [com.google.firebase.inject.Provider, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        Instant truncatedTo;
        Instant truncatedTo2;
        SharedPreferences.Editor editor = null;
        switch (this.switching_field) {
            case 0:
                int i = ((PrimesExecutorsModule$PrimesThreadFactory) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).priority;
                if (i != 0) {
                    Process.setThreadPriority(i);
                }
                this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1.run();
                return;
            case 1:
                MddDebugListFragmentUiProviderImpl mddDebugListFragmentUiProviderImpl = (MddDebugListFragmentUiProviderImpl) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0;
                mddDebugListFragmentUiProviderImpl.recyclerView.setVisibility(8);
                mddDebugListFragmentUiProviderImpl.listStatusTextView.setVisibility(0);
                mddDebugListFragmentUiProviderImpl.listStatusTextView.setText((CharSequence) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1);
                return;
            case 2:
                ((MemoryMetricMonitor.AnonymousClass1) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1).this$0.callback.onEvent$ar$edu(MemoryMetric$MemoryUsageMetric.MemoryEventCode.APP_IN_BACKGROUND_FOR_SECONDS$ar$edu, ((NoPiiString) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).value);
                return;
            case 3:
                ((MemoryMetricMonitor.AnonymousClass1) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1).this$0.callback.onEvent$ar$edu(MemoryMetric$MemoryUsageMetric.MemoryEventCode.APP_IN_FOREGROUND_FOR_SECONDS$ar$edu, ((NoPiiString) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).value);
                return;
            case 4:
                ((Sampler) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).fetchConfig(this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1);
                return;
            case 5:
                SharedPreferences preferences = PhenotypeStickyAccount.getPreferences((Context) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0);
                for (Map.Entry<String, ?> entry : preferences.getAll().entrySet()) {
                    if (entry.getValue() instanceof String) {
                        if (entry.getValue().equals(this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1)) {
                            if (editor == null) {
                                editor = preferences.edit();
                            }
                            editor.remove(entry.getKey());
                        }
                    }
                }
                if (editor != null) {
                    editor.commit();
                    return;
                }
                return;
            case 6:
                PhenotypeContext phenotypeContext = (PhenotypeContext) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0;
                if (!phenotypeContext.context.getPackageName().equals("com.android.vending")) {
                    Object obj = this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1;
                    if (!PackageInfo.getRegisteredPackages(phenotypeContext.context).containsKey(obj)) {
                        Log.e("PhenotypeCombinedFlags", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4((String) obj, "Config package ", " cannot use PROCESS_STABLE backing without declarative registration. See go/phenotype-android-integration#phenotype for more information. This will lead to stale flags."));
                        return;
                    }
                    return;
                }
                return;
            case 7:
                ((FlagStore) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).m204xe16b5662(this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1);
                return;
            case 8:
                try {
                    ContextDataProvider.getDone(this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1);
                    return;
                } catch (Exception e) {
                    Log.w("MobStoreFlagStore", "Failed to store account on flag read for: " + ((FlagStore) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).configPackage + " which may lead to stale flags.", e);
                    return;
                }
            case 9:
                NetworkCaller networkCaller = (NetworkCaller) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0;
                networkCaller.callback.onRequestSuccess(networkCaller.buildSurveyData((Service$SurveyTriggerResponse) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1));
                return;
            case 10:
                NetworkCaller networkCaller2 = (NetworkCaller) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1;
                networkCaller2.callback.onRequestFailed(networkCaller2.triggerId, (SurveyRequest.ErrorType) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0);
                return;
            case 11:
                Stopwatch stopwatch = new Stopwatch();
                Object obj2 = this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0;
                AtomicBoolean atomicBoolean = SurveyControllerImpl.isSurveyRunning;
                CallCredentials$RequestInfo callCredentials$RequestInfo = (CallCredentials$RequestInfo) obj2;
                Object obj3 = callCredentials$RequestInfo.CallCredentials$RequestInfo$ar$val$method;
                Object obj4 = callCredentials$RequestInfo.CallCredentials$RequestInfo$ar$this$1;
                Object obj5 = callCredentials$RequestInfo.CallCredentials$RequestInfo$ar$val$callOptions;
                synchronized (atomicBoolean) {
                    if (!TextUtils.isEmpty(((SurveyRequest) obj4).triggerId)) {
                        truncatedTo = Instant.now().truncatedTo(ChronoUnit.MILLIS);
                        ((SurveyControllerImpl) obj5).lastTriggerRequestTimeMs = truncatedTo.toEpochMilli();
                        SurveyDataStore surveyDataStore = ((SurveyControllerImpl) obj5).surveyDataStore;
                        String str = ((SurveyRequest) obj4).triggerId;
                        truncatedTo2 = Instant.now().truncatedTo(ChronoUnit.MILLIS);
                        surveyDataStore.triggerIdToLastTriggerRequestTimeMsMap.put(str, Long.valueOf(truncatedTo2.toEpochMilli()));
                        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Survey$TriggerContext.DEFAULT_INSTANCE.createBuilder();
                        String str2 = ((SurveyRequest) obj4).triggerId;
                        builder.copyOnWrite();
                        Survey$TriggerContext survey$TriggerContext = (Survey$TriggerContext) builder.instance;
                        str2.getClass();
                        survey$TriggerContext.triggerId_ = str2;
                        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                        FlagsUtil.isFeatureEnabled(HatsV1M5Features.INSTANCE.get().enablePreferredSurveyLanguages(FlagsUtil.phenotypeContext));
                        String language = Locale.getDefault().getLanguage();
                        CurrentProcess currentProcess2 = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                        if (FlagsUtil.isBugfixEnabled(HatsV1M3Bugfixes.fixLocaleLanguage(FlagsUtil.phenotypeContext))) {
                            language = Locale.getDefault().toLanguageTag();
                        }
                        ImmutableList of = ImmutableList.of((Object) language);
                        builder.copyOnWrite();
                        Survey$TriggerContext survey$TriggerContext2 = (Survey$TriggerContext) builder.instance;
                        Internal.ProtobufList protobufList = survey$TriggerContext2.language_;
                        if (!protobufList.isModifiable()) {
                            survey$TriggerContext2.language_ = GeneratedMessageLite.mutableCopy(protobufList);
                        }
                        AbstractMessageLite.addAll(of, survey$TriggerContext2.language_);
                        boolean z = ((SurveyRequest) obj4).enableProofMode;
                        builder.copyOnWrite();
                        ((Survey$TriggerContext) builder.instance).testingMode_ = z;
                        Survey$TriggerContext survey$TriggerContext3 = (Survey$TriggerContext) builder.build();
                        Survey$ClientContext createClientContext = SurveyUtils.createClientContext(((SurveyRequest) obj4).clientContext);
                        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Service$SurveyTriggerRequest.DEFAULT_INSTANCE.createBuilder();
                        builder2.copyOnWrite();
                        Service$SurveyTriggerRequest service$SurveyTriggerRequest = (Service$SurveyTriggerRequest) builder2.instance;
                        survey$TriggerContext3.getClass();
                        service$SurveyTriggerRequest.triggerContext_ = survey$TriggerContext3;
                        service$SurveyTriggerRequest.bitField0_ |= 1;
                        builder2.copyOnWrite();
                        Service$SurveyTriggerRequest service$SurveyTriggerRequest2 = (Service$SurveyTriggerRequest) builder2.instance;
                        createClientContext.getClass();
                        service$SurveyTriggerRequest2.clientContext_ = createClientContext;
                        service$SurveyTriggerRequest2.bitField0_ |= 2;
                        Service$SurveyTriggerRequest service$SurveyTriggerRequest3 = (Service$SurveyTriggerRequest) builder2.build();
                        Stopwatch stopwatch2 = new Stopwatch();
                        if (service$SurveyTriggerRequest3 == null) {
                            Log.e("NetworkCallerGrpc", "Survey trigger request was null");
                        } else {
                            NetworkExecutor.getNetworkExecutor().execute(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(obj3, service$SurveyTriggerRequest3, stopwatch2, 19, (char[]) null));
                        }
                        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$LibraryEvent.RequestSurveyCallInfo.DEFAULT_INSTANCE.createBuilder();
                        String str3 = ((SurveyRequest) obj4).triggerId;
                        builder3.copyOnWrite();
                        UserVoiceSurveysLogging$LibraryEvent.RequestSurveyCallInfo requestSurveyCallInfo = (UserVoiceSurveysLogging$LibraryEvent.RequestSurveyCallInfo) builder3.instance;
                        str3.getClass();
                        requestSurveyCallInfo.triggerId_ = str3;
                        boolean z2 = ((SurveyRequest) obj4).enableProofMode;
                        builder3.copyOnWrite();
                        ((UserVoiceSurveysLogging$LibraryEvent.RequestSurveyCallInfo) builder3.instance).enableTestingMode_ = z2;
                        builder3.copyOnWrite();
                        ((UserVoiceSurveysLogging$LibraryEvent.RequestSurveyCallInfo) builder3.instance).nonProd_ = false;
                        UserVoiceSurveysLogging$LibraryEvent.RequestSurveyCallInfo requestSurveyCallInfo2 = (UserVoiceSurveysLogging$LibraryEvent.RequestSurveyCallInfo) builder3.build();
                        Context context = ((SurveyRequest) obj4).clientContext;
                        CurrentProcess currentProcess3 = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                        if (FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
                            MetricsLogger metricsLogger = MetricsLogger.getInstance();
                            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$LibraryEvent.DEFAULT_INSTANCE.createBuilder();
                            builder4.copyOnWrite();
                            UserVoiceSurveysLogging$LibraryEvent userVoiceSurveysLogging$LibraryEvent = (UserVoiceSurveysLogging$LibraryEvent) builder4.instance;
                            requestSurveyCallInfo2.getClass();
                            userVoiceSurveysLogging$LibraryEvent.event_ = requestSurveyCallInfo2;
                            userVoiceSurveysLogging$LibraryEvent.eventCase_ = 3;
                            metricsLogger.reportLibraryEvent((UserVoiceSurveysLogging$LibraryEvent) builder4.build(), stopwatch.getStart(), stopwatch.getElapsed(), context, null);
                        }
                        return;
                    }
                    Log.w("SurveyController", "No trigger ID set, ignoring show request.");
                    SurveyRequest.ErrorType errorType = SurveyRequest.ErrorType.TRIGGER_ID_NOT_SET;
                    SurveyRequest.RequestSurveyCallback requestSurveyCallback = ((SurveyRequest) obj4).requestSurveyCallback;
                    if (requestSurveyCallback != null) {
                        requestSurveyCallback.onRequestFailed(((SurveyRequest) obj4).triggerId, errorType);
                    }
                    return;
                }
            case 12:
                ((SurveyViewPager) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).getCurrentItemFragment().updateQuestionText((String) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1);
                return;
            case 13:
                ((ViewPager.SimpleOnPageChangeListener) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).onPageSelected$ar$ds();
                return;
            case 14:
                try {
                    ((SplitCompat) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0).deleteSplitsSync(this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1);
                    return;
                } catch (Exception e2) {
                    Log.e("SplitCompat", "Failed to remove from splitcompat storage split that is already installed", e2);
                    return;
                }
            case 15:
                SplitInstallRequest splitInstallRequest = (SplitInstallRequest) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1;
                List languageNames = SplitInstallManagerImpl.getLanguageNames(splitInstallRequest.languages);
                Bundle bundle = new Bundle();
                bundle.putInt("session_id", 0);
                bundle.putInt("status", 5);
                bundle.putInt("error_code", 0);
                List list = splitInstallRequest.moduleNames;
                if (!list.isEmpty()) {
                    bundle.putStringArrayList("module_names", new ArrayList<>(list));
                }
                if (!languageNames.isEmpty()) {
                    bundle.putStringArrayList("languages", new ArrayList<>(languageNames));
                }
                Object obj6 = this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0;
                bundle.putLong("total_bytes_to_download", 0L);
                bundle.putLong("bytes_downloaded", 0L);
                bundle.putParcelableArrayList("split_file_intents", new ArrayList<>());
                ((SplitInstallManagerImpl) obj6).listenerRegistry.updateListeners(SplitInstallSessionState.fromBundle(bundle));
                return;
            case 16:
                Object obj7 = this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0;
                FakeSplitInstallManager fakeSplitInstallManager = (FakeSplitInstallManager) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1;
                fakeSplitInstallManager.frameworkListenerRegistry$ar$class_merging$ar$class_merging.updateListeners(obj7);
                fakeSplitInstallManager.listenerRegistry$ar$class_merging$ar$class_merging.updateListeners(obj7);
                return;
            case 17:
                ParcelableFuture parcelableFuture = (ParcelableFuture) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1;
                Object obj8 = parcelableFuture.input;
                ListenableFuture listenableFuture = parcelableFuture.future;
                return;
            case 18:
                Object obj9 = ((ParcelableFuture) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1).input;
                return;
            case 19:
                ((LogSiteMap) this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1).concurrentMap.remove(this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0);
                return;
            default:
                Object obj10 = this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0;
                Provider provider = ((OptionalProvider) obj10).delegate;
                ?? r3 = this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1;
                if (provider == OptionalProvider.EMPTY_PROVIDER) {
                    synchronized (obj10) {
                        ((OptionalProvider) obj10).handler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
                        ((OptionalProvider) obj10).delegate = r3;
                    }
                    return;
                }
                throw new IllegalStateException("provide() can be called only once.");
        }
    }

    public /* synthetic */ PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0 = obj;
        this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1 = obj2;
    }

    public /* synthetic */ PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(Object obj, Object obj2, int i, byte[] bArr) {
        this.switching_field = i;
        this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1 = obj;
        this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0 = obj2;
    }

    public PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(Object obj, Object obj2, int i, char[] cArr) {
        this.switching_field = i;
        this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$1 = obj2;
        this.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }
}
