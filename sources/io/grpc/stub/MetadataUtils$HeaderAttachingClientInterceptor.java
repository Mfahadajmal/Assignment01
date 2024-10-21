package io.grpc.stub;

import android.os.SystemClock;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.libraries.grpc.CallOptionsKeys;
import com.google.android.libraries.grpc.primes.PrimesInterceptor$PrimesStreamTracerFactory;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.metrics.network.NetworkEvent;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.PartialForwardingClientCallListener;
import io.grpc.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetadataUtils$HeaderAttachingClientInterceptor implements ClientInterceptor {
    private static MetadataUtils$HeaderAttachingClientInterceptor instance$ar$class_merging$202585dc_0;
    public final Object MetadataUtils$HeaderAttachingClientInterceptor$ar$extraHeaders;
    private final /* synthetic */ int switching_field;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class HeaderAttachingClientCall extends ForwardingClientCall.SimpleForwardingClientCall {
        public HeaderAttachingClientCall(ClientCall clientCall) {
            super(clientCall);
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
        public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
            metadata.merge((Metadata) MetadataUtils$HeaderAttachingClientInterceptor.this.MetadataUtils$HeaderAttachingClientInterceptor$ar$extraHeaders);
            delegate().start$ar$class_merging$ar$class_merging(onDeviceFaceMeshCreateLogEvent, metadata);
        }
    }

    public MetadataUtils$HeaderAttachingClientInterceptor(Object obj, int i) {
        this.switching_field = i;
        this.MetadataUtils$HeaderAttachingClientInterceptor$ar$extraHeaders = obj;
    }

    public static synchronized MetadataUtils$HeaderAttachingClientInterceptor getPrimesInterceptor$ar$class_merging() {
        MetadataUtils$HeaderAttachingClientInterceptor metadataUtils$HeaderAttachingClientInterceptor;
        synchronized (MetadataUtils$HeaderAttachingClientInterceptor.class) {
            if (instance$ar$class_merging$202585dc_0 == null) {
                instance$ar$class_merging$202585dc_0 = new MetadataUtils$HeaderAttachingClientInterceptor(new PrimesController$$ExternalSyntheticLambda9(9), 1);
            }
            metadataUtils$HeaderAttachingClientInterceptor = instance$ar$class_merging$202585dc_0;
        }
        return metadataUtils$HeaderAttachingClientInterceptor;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.common.base.Supplier, java.lang.Object] */
    @Override // io.grpc.ClientInterceptor
    public final ClientCall interceptCall(MethodDescriptor methodDescriptor, CallOptions callOptions, Channel channel) {
        final PrimesInterceptor$PrimesStreamTracerFactory primesInterceptor$PrimesStreamTracerFactory;
        if (this.switching_field != 0) {
            if (!((Boolean) this.MetadataUtils$HeaderAttachingClientInterceptor$ar$extraHeaders.get()).booleanValue()) {
                return channel.newCall(methodDescriptor, callOptions);
            }
            NoPiiString noPiiString = (NoPiiString) callOptions.getOption(CallOptionsKeys.CONSTANT_RPC_PATH_KEY);
            if (noPiiString == null) {
                primesInterceptor$PrimesStreamTracerFactory = new PrimesInterceptor$PrimesStreamTracerFactory(new NetworkEvent(null, "https://" + channel.authority() + "/" + methodDescriptor.fullMethodName, false, SystemClock.elapsedRealtime()));
            } else {
                primesInterceptor$PrimesStreamTracerFactory = new PrimesInterceptor$PrimesStreamTracerFactory(NetworkEvent.forConstantRpcPath(channel.authority(), noPiiString));
            }
            return new ForwardingClientCall.SimpleForwardingClientCall(channel.newCall(methodDescriptor, callOptions.withStreamTracerFactory$ar$class_merging$ar$class_merging(primesInterceptor$PrimesStreamTracerFactory))) { // from class: com.google.android.libraries.grpc.primes.PrimesInterceptor$1
                @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
                public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
                    this.delegate.start$ar$class_merging$ar$class_merging(new PartialForwardingClientCallListener(onDeviceFaceMeshCreateLogEvent) { // from class: com.google.android.libraries.grpc.primes.PrimesInterceptor$1.1
                        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
                        public final void onClose(Status status, Metadata metadata2) {
                            PrimesInterceptor$PrimesStreamTracerFactory primesInterceptor$PrimesStreamTracerFactory2 = primesInterceptor$PrimesStreamTracerFactory;
                            synchronized (primesInterceptor$PrimesStreamTracerFactory2.lock) {
                                if (!primesInterceptor$PrimesStreamTracerFactory2.streamClosed) {
                                    primesInterceptor$PrimesStreamTracerFactory2.streamClosed = true;
                                    PrimesInterceptor$PrimesStreamTracer primesInterceptor$PrimesStreamTracer = primesInterceptor$PrimesStreamTracerFactory2.tracer;
                                    if (primesInterceptor$PrimesStreamTracer == null) {
                                        primesInterceptor$PrimesStreamTracerFactory2.networkEvent.rpcStatusCode = status.code.value;
                                        Primes.get().recordNetwork(primesInterceptor$PrimesStreamTracerFactory2.networkEvent);
                                    } else {
                                        synchronized (primesInterceptor$PrimesStreamTracer.lock) {
                                            NetworkEvent networkEvent = primesInterceptor$PrimesStreamTracer.networkEvent;
                                            int i = primesInterceptor$PrimesStreamTracer.inboundBytes;
                                            int i2 = primesInterceptor$PrimesStreamTracer.outboundBytes;
                                            networkEvent.timeToResponseDataFinishMs = SystemClock.elapsedRealtime() - networkEvent.startTimeMs;
                                            networkEvent.bytesDownloaded = i;
                                            networkEvent.bytesUploaded = i2;
                                            primesInterceptor$PrimesStreamTracer.networkEvent.rpcStatusCode = status.code.value;
                                            Primes.get().recordNetwork(primesInterceptor$PrimesStreamTracer.networkEvent);
                                            primesInterceptor$PrimesStreamTracer.recordedToPrimes = true;
                                        }
                                    }
                                }
                            }
                            delegate$ar$class_merging$1cf311ea_0$ar$class_merging().onClose(status, metadata2);
                        }
                    }, metadata);
                }
            };
        }
        return new HeaderAttachingClientCall(channel.newCall(methodDescriptor, callOptions));
    }
}
