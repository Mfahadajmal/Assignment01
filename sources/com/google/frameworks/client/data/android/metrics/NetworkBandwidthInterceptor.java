package com.google.frameworks.client.data.android.metrics;

import com.google.common.base.Suppliers$SupplierOfInstance;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.ChannelConfig;
import com.google.frameworks.client.data.android.ChannelConfigOption;
import com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor;
import com.google.frameworks.client.data.android.interceptor.Outcome;
import com.google.frameworks.client.data.android.interceptor.ResponseOutcome;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.internal.GrpcUtil;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkBandwidthInterceptor implements AsyncClientInterceptor {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Tracer extends ClientStreamTracer {
        private final Object lock = new Object();
        private final MutableMetricsContext metricsContext;
        private long requestBytes;
        private long responseBytes;

        public Tracer(MutableMetricsContext mutableMetricsContext) {
            this.metricsContext = mutableMetricsContext;
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent
        public final void inboundWireSize(long j) {
            synchronized (this.lock) {
                this.responseBytes += j;
            }
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent
        public final void outboundWireSize(long j) {
            synchronized (this.lock) {
                this.requestBytes += j;
            }
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent
        public final void streamClosed$ar$ds() {
            boolean z;
            synchronized (this.lock) {
                MutableMetricsContext mutableMetricsContext = this.metricsContext;
                long j = this.requestBytes;
                boolean z2 = true;
                if (j >= 0) {
                    z = true;
                } else {
                    z = false;
                }
                ContextDataProvider.checkArgument(z, (Object) "Cannot record negative request bytes.");
                mutableMetricsContext.requestBytes.add(Long.valueOf(j));
                MutableMetricsContext mutableMetricsContext2 = this.metricsContext;
                long j2 = this.responseBytes;
                if (j2 < 0) {
                    z2 = false;
                }
                ContextDataProvider.checkArgument(z2, (Object) "Cannot record negative response bytes.");
                mutableMetricsContext2.responseBytes.add(Long.valueOf(j2));
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TracerFactory extends OnDeviceFaceMeshLoadLogEvent {
        private final Object lock;
        private final MutableMetricsContext metricsContext;
        private Tracer tracer;

        public TracerFactory(MutableMetricsContext mutableMetricsContext) {
            super(null);
            this.lock = new Object();
            this.metricsContext = mutableMetricsContext;
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent
        public final ClientStreamTracer newClientStreamTracer$ar$ds() {
            synchronized (this.lock) {
                if (this.tracer != null) {
                    return new GrpcUtil.AnonymousClass2();
                }
                Tracer tracer = new Tracer(this.metricsContext);
                this.tracer = tracer;
                return tracer;
            }
        }
    }

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
    public final /* synthetic */ ResponseOutcome startOnCompleteProcessing$ar$class_merging$ar$class_merging(RemoteModelManager remoteModelManager) {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final Outcome startRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        if (((Boolean) ((Suppliers$SupplierOfInstance) ((ChannelConfig) requestHeaderContext.callOptions.getOption(ChannelConfigOption.KEY)).recordBandwidthMetrics()).instance).booleanValue()) {
            CallOptions callOptions = requestHeaderContext.callOptions;
            MutableMetricsContext mutableMetricsContext = (MutableMetricsContext) callOptions.getOption(MutableMetricsContext.KEY);
            mutableMetricsContext.getClass();
            return Outcome.proceedWithCallOptions(callOptions.withStreamTracerFactory$ar$class_merging$ar$class_merging(new TracerFactory(mutableMetricsContext)));
        }
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
