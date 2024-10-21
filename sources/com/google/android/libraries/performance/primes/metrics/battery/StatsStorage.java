package com.google.android.libraries.performance.primes.metrics.battery;

import _COROUTINE._BOUNDARY;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewOverlay;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods$Builder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.service.InternalTelemetryLoggingClient$$ExternalSyntheticLambda0;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.Features;
import com.google.android.gms.phenotype.PhenotypeClient;
import com.google.android.gms.phenotype.internal.PhenotypeClientImpl;
import com.google.android.gms.pseudonymous.PseudonymousIdToken;
import com.google.android.gms.tasks.OnCanceledCompletionListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskImpl;
import com.google.android.gms.tasks.TaskTracing;
import com.google.android.gms.usagereporting.InternalUsageReportingClient;
import com.google.android.gms.usagereporting.InternalUsageReportingClient$$ExternalSyntheticLambda3;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactory$$ExternalSyntheticLambda1;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StatsStorage {
    public final Object StatsStorage$ar$storage;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class StatsRecord {
        public final Integer chargeCounter;
        public final Long currentTime;
        public final String customEventName;
        public final Long elapsedTime;
        public final ExtensionMetric$MetricExtension metricExtension;
        public final Long primesVersion;
        public final BatteryMetric$UidHealthProto proto;
        public final BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo;
        public final Long versionNameHash;

        public StatsRecord(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, Long l, Long l2, Long l3, Long l4, BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo, String str, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, Integer num) {
            this.proto = batteryMetric$UidHealthProto;
            this.elapsedTime = l;
            this.currentTime = l2;
            this.primesVersion = l3;
            this.versionNameHash = l4;
            this.sampleInfo = sampleInfo;
            this.customEventName = str;
            this.metricExtension = extensionMetric$MetricExtension;
            this.chargeCounter = num;
        }

        public final String toString() {
            return String.format("StatsRecord:\n  elapsed: %d\n  current: %d\n  Primes version: %d\n  version name #: %d\n  customName: %s\n", this.elapsedTime, this.currentTime, this.primesVersion, this.versionNameHash, this.customEventName);
        }
    }

    public StatsStorage(Object obj) {
        this.StatsStorage$ar$storage = obj;
    }

    private static ListenableFuture toListenableFuture(Task task) {
        return AbstractCatchingFuture.create(SpannableUtils$NonCopyableTextSpan.toListenableFuture(task), ApiException.class, new ProtoDataStoreFactory$$ExternalSyntheticLambda1(1), DirectExecutor.INSTANCE);
    }

    public final ListenableFuture commitToConfiguration(String str) {
        str.getClass();
        TaskApiCall.Builder builder = new TaskApiCall.Builder();
        builder.TaskApiCall$Builder$ar$execute = new InternalTelemetryLoggingClient$$ExternalSyntheticLambda0(str, 3);
        return toListenableFuture(((GoogleApi) this.StatsStorage$ar$storage).doRead(builder.build()));
    }

    public final ListenableFuture getConfigurationSnapshot(String str, String str2) {
        str2.getClass();
        TaskApiCall.Builder builder = new TaskApiCall.Builder();
        builder.TaskApiCall$Builder$ar$execute = new ClearcutLoggerApiImpl$$ExternalSyntheticLambda2(str, str2, 3);
        Task doRead = ((GoogleApi) this.StatsStorage$ar$storage).doRead(builder.build());
        DirectExecutor directExecutor = DirectExecutor.INSTANCE;
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(this, null);
        TaskImpl taskImpl = new TaskImpl();
        TaskImpl taskImpl2 = (TaskImpl) doRead;
        taskImpl2.listenerQueue$ar$class_merging.add(new OnCanceledCompletionListener(TaskTracing.traceExecutor(directExecutor), shadowDelegateImpl, taskImpl, 1));
        taskImpl2.flushIfComplete();
        return toListenableFuture(taskImpl);
    }

    public final String getPseudonymousId() {
        long j;
        try {
            InternalUsageReportingClient internalUsageReportingClient = new InternalUsageReportingClient((Context) this.StatsStorage$ar$storage);
            TaskApiCall.Builder builder = new TaskApiCall.Builder();
            builder.TaskApiCall$Builder$ar$execute = new InternalTelemetryLoggingClient$$ExternalSyntheticLambda0(internalUsageReportingClient, 4);
            builder.methodKey = 3901;
            Task doRead = internalUsageReportingClient.doRead(builder.build());
            if (true != SurveyUtils.isWatchPlatform((Context) this.StatsStorage$ar$storage)) {
                j = 1000;
            } else {
                j = 3000;
            }
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(((PseudonymousIdToken) SpannableUtils$NonCopyableTextSpan.await(doRead, j, TimeUnit.MILLISECONDS)).value, "NID=");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.e("SurveyPseudonymousIdProviderZwieback", "Task was interrupted while fetching Zwieback ID.", e);
            return "";
        } catch (ExecutionException e2) {
            Log.e("SurveyPseudonymousIdProviderZwieback", "Task failed while fetching Zwieback ID.", e2);
            return "";
        } catch (TimeoutException e3) {
            Log.e("SurveyPseudonymousIdProviderZwieback", "Task timed out while fetching Zwieback ID.", e3);
            return "";
        }
    }

    public final ListenableFuture getStorageInfo() {
        TaskApiCall.Builder builder = new TaskApiCall.Builder();
        Object obj = this.StatsStorage$ar$storage;
        builder.TaskApiCall$Builder$ar$execute = new InternalTelemetryLoggingClient$$ExternalSyntheticLambda0(obj, 2);
        builder.TaskApiCall$Builder$ar$features = new Feature[]{Features.GET_STORAGE_INFO_API};
        builder.autoResolveMissingFeatures = false;
        return toListenableFuture(((GoogleApi) obj).doRead(builder.build()));
    }

    public final ListenableFuture registerFlagUpdateListener$ar$class_merging$c65f86ce_0$ar$class_merging(WindowTrackerFactory windowTrackerFactory) {
        BufferedReader bufferedReader;
        String _BOUNDARY$ar$MethodOutlining$dc56d17a_7;
        StrictMode.ThreadPolicy allowThreadDiskReads;
        String processName;
        Object obj = this.StatsStorage$ar$storage;
        GoogleApi googleApi = (GoogleApi) obj;
        ListenerHolder createListenerHolder = StrictModeUtils$VmPolicyBuilderCompatS.createListenerHolder(windowTrackerFactory, googleApi.looper, PhenotypeClientImpl.class.getSimpleName());
        if (ProcessUtils.myProcessName == null) {
            if (Build.VERSION.SDK_INT >= 28) {
                processName = Application.getProcessName();
                ProcessUtils.myProcessName = processName;
            } else {
                int i = ProcessUtils.myPid;
                if (i == 0) {
                    i = Process.myPid();
                    ProcessUtils.myPid = i;
                }
                String str = null;
                str = null;
                str = null;
                BufferedReader bufferedReader2 = null;
                if (i > 0) {
                    try {
                        _BOUNDARY$ar$MethodOutlining$dc56d17a_7 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "/proc/", "/cmdline");
                        allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    } catch (IOException unused) {
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        bufferedReader = new BufferedReader(new FileReader(_BOUNDARY$ar$MethodOutlining$dc56d17a_7));
                        try {
                            String readLine = bufferedReader.readLine();
                            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(readLine);
                            str = readLine.trim();
                        } catch (IOException unused2) {
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader2 = bufferedReader;
                            IOUtils.closeQuietly(bufferedReader2);
                            throw th;
                        }
                        IOUtils.closeQuietly(bufferedReader);
                    } finally {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    }
                }
                ProcessUtils.myProcessName = str;
            }
        }
        String str2 = ProcessUtils.myProcessName;
        if (str2 == null) {
            str2 = "__PH_INTERNAL__NO_PROCESS__";
        }
        InternalUsageReportingClient$$ExternalSyntheticLambda3 internalUsageReportingClient$$ExternalSyntheticLambda3 = new InternalUsageReportingClient$$ExternalSyntheticLambda3((PhenotypeClient) obj, str2, createListenerHolder, 1);
        RemoteCall remoteCall = new RemoteCall() { // from class: com.google.android.gms.phenotype.PhenotypeClient$$ExternalSyntheticLambda21
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj2, Object obj3) {
                int i2 = PhenotypeClient.PhenotypeClient$ar$NoOp;
            }
        };
        RegistrationMethods$Builder registrationMethods$Builder = new RegistrationMethods$Builder();
        registrationMethods$Builder.holder = createListenerHolder;
        registrationMethods$Builder.register = internalUsageReportingClient$$ExternalSyntheticLambda3;
        registrationMethods$Builder.unregister = remoteCall;
        registrationMethods$Builder.features = new Feature[]{Features.REGISTER_FLAG_UPDATE_LISTENER_API};
        registrationMethods$Builder.shouldAutoResolveMissingFeatures = false;
        return toListenableFuture(googleApi.doRegisterEventListener$ar$class_merging$ar$class_merging$ar$class_merging(registrationMethods$Builder.build$ar$class_merging$8fd660b1_0$ar$class_merging$ar$class_merging()));
    }

    public final void remove(Drawable drawable) {
        ((ViewOverlay) this.StatsStorage$ar$storage).remove(drawable);
    }

    public StatsStorage() {
        this.StatsStorage$ar$storage = new ConcurrentHashMap();
    }

    public StatsStorage(View view) {
        this.StatsStorage$ar$storage = view.getOverlay();
    }
}
