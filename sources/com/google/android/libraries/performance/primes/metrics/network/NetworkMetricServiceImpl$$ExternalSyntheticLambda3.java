package com.google.android.libraries.performance.primes.metrics.network;

import androidx.room.util.SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1;
import com.google.android.libraries.mdi.download.workmanager.workers.PeriodicWorker;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.trace.SpanProtoGenerator;
import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TraceData;
import com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.apps.tiktok.tracing.SpanEndSignal;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.frameworks.client.data.android.Transport$TransportConfig;
import io.grpc.internal.RetryingNameResolver;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$PrimesTrace;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$Span;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NetworkMetricServiceImpl$$ExternalSyntheticLambda3 implements AsyncCallable {
    public final /* synthetic */ Object NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$0;
    public final /* synthetic */ Object NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ NetworkMetricServiceImpl$$ExternalSyntheticLambda3(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$0 = obj;
        this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$1 = obj2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.lang.Iterable] */
    @Override // com.google.common.util.concurrent.AsyncCallable
    public final ListenableFuture call() {
        List unmodifiableList;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    Object obj = this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$0;
                    Object obj2 = this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$1;
                    SpanEndSignal beginSpan = Tracer.beginSpan("FrameworkChannel#getTransportChannel");
                    try {
                        ListenableFuture immediateFuture = ContextDataProvider.immediateFuture(((RetryingNameResolver.ResolutionResultListener) obj2).getTransportChannel((Transport$TransportConfig) obj));
                        beginSpan.close();
                        return immediateFuture;
                    } catch (Throwable th) {
                        try {
                            beginSpan.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                TraceMetricServiceImpl traceMetricServiceImpl = (TraceMetricServiceImpl) this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$1;
                if (!((TraceConfigurations) traceMetricServiceImpl.traceConfigurationsProvider.get()).isEnabled()) {
                    return ImmediateFuture.NULL;
                }
                if (((TikTokTraceConfigurations) traceMetricServiceImpl.tikTokTraceConfigurationsProvider.get()).isEnabled()) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/TraceMetricServiceImpl", "lambda$endTracingIfStarted$3", 156, "TraceMetricServiceImpl.java")).log("Primes Tracing is not supported if TikTok tracing is enabled");
                    return ImmediateFuture.NULL;
                }
                if (traceMetricServiceImpl.rateLimiter.isRateLimitExceeded()) {
                    return ImmediateFuture.NULL;
                }
                Object obj3 = this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$0;
                traceMetricServiceImpl.rateLimiter.incrementEventCount();
                ThreadUtil.ensureBackgroundThread();
                TraceData traceData = (TraceData) obj3;
                String str = null;
                if (traceData.getSpanCount() != 0) {
                    SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1 schemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1 = new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(12);
                    synchronized (traceData.timerSpans) {
                        Collections.sort(((TraceData) obj3).timerSpans, schemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1);
                        ((TraceData) obj3).rootSpan.addChildSpans(((TraceData) obj3).timerSpans);
                    }
                    ArrayList arrayList = new ArrayList(traceData.parentSpanToThreadData.keySet());
                    Collections.sort(arrayList, schemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1);
                    traceData.rootSpan.addChildSpans(arrayList);
                    SpanProtoGenerator spanProtoGenerator = new SpanProtoGenerator(traceData.rootSpan);
                    ArrayList arrayList2 = new ArrayList();
                    spanProtoGenerator.traverse(spanProtoGenerator.rootSpan, 0L, arrayList2);
                    if (arrayList2.size() == 1) {
                        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/SpanProtoGenerator", "generate", 71, "SpanProtoGenerator.java")).log("No other span except for root span. Dropping trace...");
                    } else {
                        unmodifiableList = DesugarCollections.unmodifiableList(arrayList2);
                        if (unmodifiableList == null && !unmodifiableList.isEmpty()) {
                            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) PrimesTraceOuterClass$PrimesTrace.DEFAULT_INSTANCE.createBuilder();
                            long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
                            builder.copyOnWrite();
                            PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace = (PrimesTraceOuterClass$PrimesTrace) builder.instance;
                            primesTraceOuterClass$PrimesTrace.bitField0_ = 1 | primesTraceOuterClass$PrimesTrace.bitField0_;
                            primesTraceOuterClass$PrimesTrace.traceId_ = leastSignificantBits;
                            builder.addAllSpans$ar$ds(unmodifiableList);
                            PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace2 = (PrimesTraceOuterClass$PrimesTrace) builder.build();
                            if (primesTraceOuterClass$PrimesTrace2.spans_.size() > 0) {
                                str = ((PrimesTraceOuterClass$Span) primesTraceOuterClass$PrimesTrace2.spans_.get(0)).constantName_;
                            }
                            long samplingRatePermilleIfShouldCollect = traceMetricServiceImpl.metricRecorder.samplingRatePermilleIfShouldCollect(str);
                            if (samplingRatePermilleIfShouldCollect == -1) {
                                return ImmediateFuture.NULL;
                            }
                            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
                            builder2.copyOnWrite();
                            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder2.instance;
                            primesTraceOuterClass$PrimesTrace2.getClass();
                            systemHealthProto$SystemHealthMetric.primesTrace_ = primesTraceOuterClass$PrimesTrace2;
                            systemHealthProto$SystemHealthMetric.bitField0_ |= 2048;
                            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = (SystemHealthProto$SystemHealthMetric) builder2.build();
                            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFiner()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/TraceMetricServiceImpl", "lambda$endTracingIfStarted$3", 189, "TraceMetricServiceImpl.java")).log("Recording trace %d: %s", primesTraceOuterClass$PrimesTrace2.traceId_, str);
                            MetricRecorder metricRecorder = traceMetricServiceImpl.metricRecorder;
                            Metric.Builder newBuilder = Metric.newBuilder();
                            newBuilder.setMetric$ar$ds(systemHealthProto$SystemHealthMetric2);
                            newBuilder.sampleRatePermille = Long.valueOf(samplingRatePermilleIfShouldCollect);
                            return metricRecorder.recordMetric(newBuilder.build());
                        }
                        return ImmediateFuture.NULL;
                    }
                }
                unmodifiableList = null;
                if (unmodifiableList == null) {
                }
                return ImmediateFuture.NULL;
            }
            return ((PeriodicWorker) this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$0).mobileDataDownload.handleTask$ar$ds();
        }
        NetworkMetricServiceImpl networkMetricServiceImpl = (NetworkMetricServiceImpl) this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$0;
        return networkMetricServiceImpl.recordMetric(((NetworkMetricCollector) networkMetricServiceImpl.metricCollector.get()).getMetric(this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$1));
    }

    public /* synthetic */ NetworkMetricServiceImpl$$ExternalSyntheticLambda3(Object obj, Object obj2, int i, byte[] bArr) {
        this.switching_field = i;
        this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$1 = obj;
        this.NetworkMetricServiceImpl$$ExternalSyntheticLambda3$ar$f$0 = obj2;
    }
}
