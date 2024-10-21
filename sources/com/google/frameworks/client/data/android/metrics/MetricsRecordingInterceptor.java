package com.google.frameworks.client.data.android.metrics;

import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.metrics.network.NetworkEvent;
import com.google.common.base.Suppliers$SupplierOfInstance;
import com.google.common.flogger.android.AndroidAbstractLogger;
import com.google.common.flogger.android.AndroidFluentLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.ChannelConfig;
import com.google.frameworks.client.data.android.ChannelConfigOption;
import com.google.frameworks.client.data.android.RpcId;
import com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor;
import com.google.frameworks.client.data.android.interceptor.Outcome;
import com.google.frameworks.client.data.android.interceptor.ResponseOutcome;
import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.CallOptions;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricsRecordingInterceptor implements AsyncClientInterceptor {
    private static final AndroidFluentLogger logger = AndroidFluentLogger.create$ar$ds$7ce4a6ce_0();
    private MethodDescriptor.MethodType methodType;
    private MutableMetricsContext metricsContext;
    private RemoteModelManager metricsSink$ar$class_merging$ar$class_merging;

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
        boolean z;
        try {
            if (this.metricsContext.result.getAndSet(((Status) remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances).code) == null) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "Already recorded result.");
            RemoteModelManager remoteModelManager2 = this.metricsSink$ar$class_merging$ar$class_merging;
            MutableMetricsContext mutableMetricsContext = this.metricsContext;
            if (((Boolean) ((Suppliers$SupplierOfInstance) remoteModelManager2.RemoteModelManager$ar$remoteModelManagerInstances).instance).booleanValue() && mutableMetricsContext.cacheLookup.get()) {
                Primes primes = Primes.get();
                NetworkEvent forConstantRpcPath = NetworkEvent.forConstantRpcPath(null, mutableMetricsContext.rpcId.rpcIdString());
                boolean z2 = mutableMetricsContext.cacheHit.get();
                ContextDataProvider.checkArgument(true);
                ContextDataProvider.checkArgument(true);
                forConstantRpcPath.cacheLookupCount = 1;
                forConstantRpcPath.cacheHitCount = z2 ? 1 : 0;
                primes.recordNetwork(forConstantRpcPath);
            }
        } catch (Throwable th) {
            ((AndroidAbstractLogger.Api) ((AndroidAbstractLogger.Api) ((AndroidAbstractLogger.Api) logger.atSevere()).withCause(th)).withInjectedLogSite("com/google/frameworks/client/data/android/metrics/MetricsRecordingInterceptor", "startOnCompleteProcessing", 82, "MetricsRecordingInterceptor.java")).log("Failed to record RPC metrics");
        }
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final Outcome startRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        boolean z;
        this.methodType = requestHeaderContext.methodDescriptor().type;
        CallOptions callOptions = requestHeaderContext.callOptions;
        this.metricsSink$ar$class_merging$ar$class_merging = new RemoteModelManager(((ChannelConfig) callOptions.getOption(ChannelConfigOption.KEY)).recordCachingMetricsToPrimes());
        boolean z2 = true;
        if (callOptions.getOption(MutableMetricsContext.KEY) == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, "Unexpected option %s already set.", MutableMetricsContext.KEY);
        if (callOptions.getOption(MetricsContext.KEY) != null) {
            z2 = false;
        }
        ContextDataProvider.checkArgument(z2, "Unexpected option %s already set.", MetricsContext.KEY);
        this.metricsContext = new MutableMetricsContext((RpcId) callOptions.getOption(RpcId.KEY));
        return Outcome.proceedWithCallOptions(callOptions.withOption(MutableMetricsContext.KEY, this.metricsContext).withOption(MetricsContext.KEY, this.metricsContext));
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final Outcome startRequestMessageProcessing$ar$ds() {
        if (this.methodType.equals(MethodDescriptor.MethodType.CLIENT_STREAMING) || this.methodType.equals(MethodDescriptor.MethodType.BIDI_STREAMING)) {
            this.metricsContext.clientMessages.incrementAndGet();
        }
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startResponseHeaderProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final ResponseOutcome startResponseMessageProcessing$ar$ds() {
        if (this.methodType.equals(MethodDescriptor.MethodType.SERVER_STREAMING) || this.methodType.equals(MethodDescriptor.MethodType.BIDI_STREAMING)) {
            this.metricsContext.serverMessages.incrementAndGet();
        }
        return ResponseOutcome.PROCEED;
    }
}
