package com.google.android.libraries.performance.primes.metrics.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.foreground.ForegroundListener;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl$$ExternalSyntheticLambda1;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import dagger.Lazy;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkConnectionInfo;
import logs.proto.wireless.performance.mobile.NetworkMetric$RequestStatus;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetricServiceImpl extends NetworkMetricService implements ForegroundListener, MetricService {
    public final Lazy configsProvider;
    public final Context context;
    private final ListeningScheduledExecutorService executorService;
    public final Lazy metricCollector;
    private final MetricRecorder metricRecorder;
    public final ProcessStatsCapture processStatsCapture;
    public final Provider samplingParameters;
    public final Object lock = new Object();
    public ArrayList batchedMetric = new ArrayList(0);
    public final AtomicInteger pendingRecords = new AtomicInteger();

    public NetworkMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, ForegroundTracker foregroundTracker, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Lazy lazy2, Provider provider, Executor executor, ProcessStatsCapture processStatsCapture) {
        this.processStatsCapture = processStatsCapture;
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider);
        this.context = context;
        this.executorService = listeningScheduledExecutorService;
        this.configsProvider = lazy;
        this.metricCollector = lazy2;
        this.samplingParameters = provider;
        foregroundTracker.register(this);
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public final void onAppToBackground(NoPiiString noPiiString) {
        sendPendingEvents();
    }

    @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkMetricService
    public final void recordAsFuture$ar$ds(final NetworkEvent networkEvent) {
        String str;
        String stringGenerated3049f23212f12367;
        if (networkEvent.timeToResponseHeaderMs <= 0 && networkEvent.timeToResponseDataFinishMs <= 0 && networkEvent.bytesDownloaded <= 0 && networkEvent.bytesUploaded <= 0 && networkEvent.cacheLookupCount <= 0 && networkEvent.requestStatus$ar$edu != NetworkMetric$RequestStatus.FAILED$ar$edu && networkEvent.requestStatus$ar$edu != NetworkMetric$RequestStatus.CANCELED$ar$edu && networkEvent.rpcStatusCode <= 0) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/network/NetworkMetricServiceImpl", "recordAsFuture", 98, "NetworkMetricServiceImpl.java")).log("skip logging NetworkEvent due to empty bandwidth/latency data");
            ListenableFuture listenableFuture = ImmediateFuture.NULL;
            return;
        }
        MetricRecorder metricRecorder = this.metricRecorder;
        String str2 = networkEvent.domainPath;
        if (str2 != null && networkEvent.isConstantRpcPath) {
            str = str2 + "/" + networkEvent.requestPath;
        } else {
            str = networkEvent.requestPath;
        }
        String str3 = networkEvent.contentType;
        if (ContextDataProvider.stringIsNullOrEmpty(str)) {
            str = "";
        } else {
            Matcher matcher = NetworkMetricCollector.PARAMETERS_PATTERN.matcher(str);
            if (matcher.find()) {
                str = matcher.group(1);
            } else {
                Matcher matcher2 = NetworkMetricCollector.FILENAME_PATTERN_WITH_EXTENSION.matcher(str);
                if (matcher2.find()) {
                    str = matcher2.group(1);
                } else {
                    Matcher matcher3 = NetworkMetricCollector.FILENAME_PATTERN.matcher(str);
                    if (matcher3.find() && str3 != null && !str3.startsWith("application/")) {
                        str = matcher3.group(1);
                    }
                }
            }
        }
        int i = networkEvent.networkType$ar$edu;
        if (i == 0) {
            stringGenerated3049f23212f12367 = null;
        } else {
            stringGenerated3049f23212f12367 = NetworkMetric$NetworkConnectionInfo.NetworkType.toStringGenerated3049f23212f12367(i);
        }
        Joiner joiner = new Joiner(":");
        final long samplingRatePermilleIfShouldCollect = metricRecorder.samplingRatePermilleIfShouldCollect(new Joiner(joiner) { // from class: com.google.common.base.Joiner.1
            final /* synthetic */ String val$nullText = "";

            public AnonymousClass1(Joiner joiner2) {
                super(joiner2);
            }

            @Override // com.google.common.base.Joiner
            public final CharSequence toString(Object obj) {
                if (obj == null) {
                    return this.val$nullText;
                }
                return Joiner.this.toString(obj);
            }
        }.join(new AbstractList() { // from class: com.google.common.base.Joiner.3
            final /* synthetic */ Object val$first;
            final /* synthetic */ Object[] val$rest;
            final /* synthetic */ Object val$second;

            public AnonymousClass3(Object[] objArr, Object str4, Object obj) {
                r1 = objArr;
                r2 = str4;
                r3 = obj;
            }

            @Override // java.util.AbstractList, java.util.List
            public final Object get(int i2) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        return r1[i2 - 2];
                    }
                    return r3;
                }
                return r2;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public final int size() {
                return 4;
            }
        }));
        if (samplingRatePermilleIfShouldCollect == -1) {
            ListenableFuture listenableFuture2 = ImmediateFuture.NULL;
        } else {
            this.pendingRecords.incrementAndGet();
            ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl$$ExternalSyntheticLambda1
                @Override // com.google.common.util.concurrent.AsyncCallable
                public final ListenableFuture call() {
                    ArrayList arrayList;
                    ListenableFuture recordMetric;
                    NetworkInfo activeNetworkInfo;
                    NetworkMetricServiceImpl networkMetricServiceImpl = NetworkMetricServiceImpl.this;
                    long j = samplingRatePermilleIfShouldCollect;
                    try {
                        int forNumber$ar$edu$fde10cc9_0 = SystemHealthProto$SamplingParameters.SamplingStrategy.forNumber$ar$edu$fde10cc9_0(((SystemHealthProto$SamplingParameters) networkMetricServiceImpl.samplingParameters.get()).samplingStrategy_);
                        if (forNumber$ar$edu$fde10cc9_0 == 0) {
                            forNumber$ar$edu$fde10cc9_0 = SystemHealthProto$SamplingParameters.SamplingStrategy.UNKNOWN$ar$edu$b47db991_0;
                        }
                        int i2 = SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY$ar$edu;
                        NetworkEvent networkEvent2 = networkEvent;
                        if (forNumber$ar$edu$fde10cc9_0 == i2) {
                            networkEvent2.eventSampleRatePerMille = Optional.of(Long.valueOf(j));
                        }
                        Context context = networkMetricServiceImpl.context;
                        networkEvent2.processStats = networkMetricServiceImpl.processStatsCapture.getAndroidProcessStats();
                        int i3 = -1;
                        try {
                            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                                i3 = activeNetworkInfo.getType();
                            }
                        } catch (SecurityException e) {
                            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/network/NetworkCapture", "getNetworkType", 36, "NetworkCapture.java")).log("Failed to get network type, Please add: android.permission.ACCESS_NETWORK_STATE to AndroidManifest.xml");
                        }
                        int forNumber$ar$edu$1098a20c_0 = NetworkMetric$NetworkConnectionInfo.NetworkType.forNumber$ar$edu$1098a20c_0(i3);
                        if (forNumber$ar$edu$1098a20c_0 == 0) {
                            forNumber$ar$edu$1098a20c_0 = NetworkMetric$NetworkConnectionInfo.NetworkType.NONE$ar$edu$5e383aad_0;
                        }
                        networkEvent2.networkType$ar$edu = forNumber$ar$edu$1098a20c_0;
                        int batchSize = ((NetworkConfigurations) networkMetricServiceImpl.configsProvider.get()).getBatchSize();
                        synchronized (networkMetricServiceImpl.lock) {
                            networkMetricServiceImpl.batchedMetric.ensureCapacity(batchSize);
                            networkMetricServiceImpl.batchedMetric.add(networkEvent2);
                            if (networkMetricServiceImpl.batchedMetric.size() >= batchSize) {
                                arrayList = networkMetricServiceImpl.batchedMetric;
                                networkMetricServiceImpl.batchedMetric = new ArrayList(0);
                            } else {
                                arrayList = null;
                            }
                        }
                        if (arrayList == null) {
                            recordMetric = ImmediateFuture.NULL;
                        } else {
                            recordMetric = networkMetricServiceImpl.recordMetric(((NetworkMetricCollector) networkMetricServiceImpl.metricCollector.get()).getMetric(arrayList));
                        }
                        return recordMetric;
                    } finally {
                        networkMetricServiceImpl.pendingRecords.decrementAndGet();
                    }
                }
            }, this.executorService);
        }
    }

    public final ListenableFuture recordMetric(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        try {
            ((NetworkConfigurations) this.configsProvider.get()).getMetricExtensionProvider();
        } catch (Exception e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/network/NetworkMetricServiceImpl", "recordMetric", 191, "NetworkMetricServiceImpl.java")).log("Exception while getting network metric extension!");
        }
        MetricRecorder metricRecorder = this.metricRecorder;
        Metric.Builder newBuilder = Metric.newBuilder();
        newBuilder.setMetric$ar$ds(systemHealthProto$SystemHealthMetric);
        newBuilder.metricExtension = null;
        return metricRecorder.recordMetric(newBuilder.build());
    }

    public final ListenableFuture sendPendingEvents() {
        if (this.pendingRecords.get() > 0) {
            CrashMetricServiceImpl$$ExternalSyntheticLambda1 crashMetricServiceImpl$$ExternalSyntheticLambda1 = new CrashMetricServiceImpl$$ExternalSyntheticLambda1(this, 4);
            ListeningScheduledExecutorService listeningScheduledExecutorService = this.executorService;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(crashMetricServiceImpl$$ExternalSyntheticLambda1);
            trustedListenableFutureTask.addListener(new Futures$$ExternalSyntheticLambda1(listeningScheduledExecutorService.schedule(trustedListenableFutureTask, 1L, timeUnit), 0), DirectExecutor.INSTANCE);
            return trustedListenableFutureTask;
        }
        synchronized (this.lock) {
            if (this.batchedMetric.isEmpty()) {
                return ImmediateFuture.NULL;
            }
            ArrayList arrayList = this.batchedMetric;
            this.batchedMetric = new ArrayList(0);
            return ContextDataProvider.submitAsync(new NetworkMetricServiceImpl$$ExternalSyntheticLambda3(this, arrayList, 0), this.executorService);
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final /* synthetic */ void onApplicationStartup() {
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public final /* synthetic */ void onAppToForeground(NoPiiString noPiiString) {
    }
}
