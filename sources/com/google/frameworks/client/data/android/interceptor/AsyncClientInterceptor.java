package com.google.frameworks.client.data.android.interceptor;

import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AsyncClientInterceptor {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RequestHeaderContext {
        public final String authority;
        public final CallOptions callOptions;
        private final MethodDescriptor methodDescriptor;
        public final Metadata requestMetadata;
        private final int transportType$ar$edu;

        public RequestHeaderContext(int i, MethodDescriptor methodDescriptor, CallOptions callOptions, Metadata metadata, String str) {
            this.transportType$ar$edu = i;
            this.callOptions = callOptions;
            this.methodDescriptor = methodDescriptor;
            this.requestMetadata = metadata;
            this.authority = str;
        }

        public final MethodDescriptor methodDescriptor() {
            if (this.transportType$ar$edu == 2) {
                return this.methodDescriptor;
            }
            throw new IllegalStateException("MethodDescriptor is not defined for non-grpc TransportType: ".concat("PROTO_OVER_HTTP"));
        }
    }

    ResponseOutcome continueOnCompleteProcessing$ar$ds();

    Outcome continueRequestHeaderProcessing(RequestHeaderContext requestHeaderContext);

    Outcome continueRequestMessageProcessing$ar$ds();

    ResponseOutcome continueResponseHeaderProcessing$ar$ds();

    ResponseOutcome continueResponseMessageProcessing$ar$ds();

    ResponseOutcome startOnCompleteProcessing$ar$class_merging$ar$class_merging(RemoteModelManager remoteModelManager);

    Outcome startRequestHeaderProcessing(RequestHeaderContext requestHeaderContext);

    Outcome startRequestMessageProcessing$ar$ds();

    ResponseOutcome startResponseHeaderProcessing$ar$ds();

    ResponseOutcome startResponseMessageProcessing$ar$ds();
}
