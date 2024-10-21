package com.google.frameworks.client.data.android.metrics;

import android.os.SystemClock;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.common.flogger.android.AndroidAbstractLogger;
import com.google.common.flogger.android.AndroidFluentLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.ChannelConfig;
import com.google.frameworks.client.data.android.ChannelConfigOption;
import com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor;
import com.google.frameworks.client.data.android.interceptor.Outcome;
import com.google.frameworks.client.data.android.interceptor.ResponseOutcome;
import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkLatencyInterceptor implements AsyncClientInterceptor {
    private static final AndroidFluentLogger logger = AndroidFluentLogger.create$ar$ds$7ce4a6ce_0();
    private SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    private MethodDescriptor.MethodType methodType;
    private MutableMetricsContext metricsContext;
    private long startTime;

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueOnCompleteProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ Outcome continueRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ Outcome continueRequestMessageProcessing$ar$ds() {
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueResponseHeaderProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueResponseMessageProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final ResponseOutcome startOnCompleteProcessing$ar$class_merging$ar$class_merging(RemoteModelManager remoteModelManager) {
        long elapsedRealtime;
        boolean z;
        boolean z2;
        try {
            if (((Status) remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances).isOk()) {
                elapsedRealtime = SystemClock.elapsedRealtime();
                long j = elapsedRealtime - this.startTime;
                boolean z3 = true;
                if (this.methodType.equals(MethodDescriptor.MethodType.UNARY)) {
                    if (j <= 2147483647L) {
                        MutableMetricsContext mutableMetricsContext = this.metricsContext;
                        int i = (int) j;
                        if (i >= 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        ContextDataProvider.checkArgument(z2, (Object) "Cannot record negative latency.");
                        if (mutableMetricsContext.latency.getAndSet(i) != -1) {
                            z3 = false;
                        }
                        ContextDataProvider.checkState(z3, "Already recorded latency.");
                    } else {
                        ((AndroidAbstractLogger.Api) ((AndroidAbstractLogger.Api) logger.atSevere()).withInjectedLogSite("com/google/frameworks/client/data/android/metrics/NetworkLatencyInterceptor", "startOnCompleteProcessing", 52, "NetworkLatencyInterceptor.java")).log("Recorded latency overflows Integer.MAX_VALUE, cannot be recorded");
                    }
                } else {
                    MutableMetricsContext mutableMetricsContext2 = this.metricsContext;
                    if (j >= 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ContextDataProvider.checkArgument(z, (Object) "Cannot record negative stream duration.");
                    if (mutableMetricsContext2.streamDurationMs.getAndSet(j) != -1) {
                        z3 = false;
                    }
                    ContextDataProvider.checkState(z3, "Already recorded stream duration.");
                }
            }
            this.metricsContext.requests.incrementAndGet();
        } catch (Throwable th) {
            ((AndroidAbstractLogger.Api) ((AndroidAbstractLogger.Api) ((AndroidAbstractLogger.Api) logger.atSevere()).withCause(th)).withInjectedLogSite("com/google/frameworks/client/data/android/metrics/NetworkLatencyInterceptor", "startOnCompleteProcessing", 62, "NetworkLatencyInterceptor.java")).log("Failed to record network latency");
        }
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final Outcome startRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        long elapsedRealtime;
        this.methodType = requestHeaderContext.methodDescriptor().type;
        MutableMetricsContext mutableMetricsContext = (MutableMetricsContext) requestHeaderContext.callOptions.getOption(MutableMetricsContext.KEY);
        mutableMetricsContext.getClass();
        this.metricsContext = mutableMetricsContext;
        this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = ((ChannelConfig) requestHeaderContext.callOptions.getOption(ChannelConfigOption.KEY)).clock$ar$class_merging$ar$class_merging$ar$class_merging();
        elapsedRealtime = SystemClock.elapsedRealtime();
        this.startTime = elapsedRealtime;
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ Outcome startRequestMessageProcessing$ar$ds() {
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startResponseHeaderProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startResponseMessageProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }
}
