package com.google.frameworks.client.data.android.impl;

import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor;
import com.google.android.libraries.grpc.ExtensionRegistryUtils;
import com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl$$ExternalSyntheticLambda3;
import com.google.android.libraries.phenotype.client.stable.PhenotypeAccountStore$$ExternalSyntheticLambda4;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.apps.tiktok.concurrent.Once;
import com.google.apps.tiktok.tracing.Trace;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SequentialExecutor;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import com.google.frameworks.client.data.android.Transport$TransportConfig;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.protobuf.ExtensionRegistryLite;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
final class TransportChannel extends Channel {
    private final String authority;
    private final Once channelFutureOnce;
    private final Executor transportExecutor;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EnqueueingClientCall extends ClientCall {
        private final ListenableFuture callFuture;
        public OnDeviceFaceMeshCreateLogEvent listener$ar$class_merging$a40ae667_0$ar$class_merging;
        private final Executor sequentialExecutor = new SequentialExecutor(DirectExecutor.INSTANCE);
        public final Queue pending = new ArrayDeque();
        public ClientCall delegate = null;
        public boolean clientCallFutureFailed = false;

        public EnqueueingClientCall(ListenableFuture listenableFuture) {
            this.callFuture = listenableFuture;
        }

        private final void runOrEnqueue(Runnable runnable) {
            this.sequentialExecutor.execute(TracePropagation.propagateRunnable(new EventBus$$ExternalSyntheticLambda0(this, runnable, 2, null)));
        }

        @Override // io.grpc.ClientCall
        public final void cancel(String str, Throwable th) {
            runOrEnqueue(new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, str, th, 6));
        }

        @Override // io.grpc.ClientCall
        public final void halfClose() {
            runOrEnqueue(new Futures$$ExternalSyntheticLambda1(this, 2));
        }

        @Override // io.grpc.ClientCall
        public final void request(int i) {
            runOrEnqueue(new RatingView$$ExternalSyntheticLambda5(this, i, 5));
        }

        @Override // io.grpc.ClientCall
        public final void sendMessage(Object obj) {
            runOrEnqueue(new EventBus$$ExternalSyntheticLambda0(this, obj, 3, null));
        }

        @Override // io.grpc.ClientCall
        public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
            this.listener$ar$class_merging$a40ae667_0$ar$class_merging = onDeviceFaceMeshCreateLogEvent;
            final ImageDescriptionProcessor.AnonymousClass1 anonymousClass1 = new ImageDescriptionProcessor.AnonymousClass1(this, onDeviceFaceMeshCreateLogEvent, 3, null);
            int i = TracePropagation.TracePropagation$ar$NoOp;
            final Trace orCreateDebug = Tracer.getOrCreateDebug();
            ContextDataProvider.addCallback(this.callFuture, new FutureCallback() { // from class: com.google.apps.tiktok.tracing.TracePropagation$propagateFutureCallback$1
                @Override // com.google.common.util.concurrent.FutureCallback
                public final void onFailure(Throwable th) {
                    th.getClass();
                    Trace trace = Tracer.set(Tracer.getCurrentThreadState(), Trace.this);
                    try {
                        anonymousClass1.onFailure(th);
                    } finally {
                    }
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public final void onSuccess(Object obj) {
                    Trace trace = Tracer.set(Tracer.getCurrentThreadState(), Trace.this);
                    try {
                        anonymousClass1.onSuccess(obj);
                    } finally {
                    }
                }
            }, this.sequentialExecutor);
            runOrEnqueue(new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, onDeviceFaceMeshCreateLogEvent, metadata, 7));
        }

        public final String toString() {
            return super.toString() + "delegate=[" + String.valueOf(this.delegate) + "]";
        }
    }

    public TransportChannel(RetryingNameResolver.ResolutionResultListener resolutionResultListener, Transport$TransportConfig transport$TransportConfig, Executor executor) {
        this.authority = transport$TransportConfig.uri().getAuthority();
        this.transportExecutor = transport$TransportConfig.transportExecutor;
        this.channelFutureOnce = new Once(new NetworkMetricServiceImpl$$ExternalSyntheticLambda3(resolutionResultListener, transport$TransportConfig, 3, null), executor);
    }

    @Override // io.grpc.Channel
    public final String authority() {
        return this.authority;
    }

    @Override // io.grpc.Channel
    public final ClientCall newCall(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        if (ExtensionRegistryUtils.isRegistered.compareAndSet(false, true)) {
            ExtensionRegistryLite generatedRegistry = ExtensionRegistryLite.getGeneratedRegistry();
            ExtensionRegistryLite extensionRegistryLite = ProtoLiteUtils.globalRegistry;
            generatedRegistry.getClass();
            ProtoLiteUtils.globalRegistry = generatedRegistry;
        }
        Executor executor = callOptions.executor;
        ListenableFuture listenableFuture = this.channelFutureOnce.get();
        PhenotypeAccountStore$$ExternalSyntheticLambda4 phenotypeAccountStore$$ExternalSyntheticLambda4 = new PhenotypeAccountStore$$ExternalSyntheticLambda4(methodDescriptor, callOptions, 2);
        if (executor == null) {
            executor = this.transportExecutor;
        }
        return new EnqueueingClientCall(ContextDataProvider.transform(listenableFuture, phenotypeAccountStore$$ExternalSyntheticLambda4, executor));
    }
}
