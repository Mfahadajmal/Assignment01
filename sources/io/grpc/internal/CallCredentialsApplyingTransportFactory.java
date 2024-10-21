package io.grpc.internal;

import androidx.preference.Preference;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceFaceDetectionLogEvent;
import io.grpc.CallCredentials$MetadataApplier;
import io.grpc.CallCredentials$RequestInfo;
import io.grpc.CallOptions;
import io.grpc.ChannelLogger;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.auth.GoogleAuthLibraryCallCredentials;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.RetryingNameResolver;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CallCredentialsApplyingTransportFactory implements ClientTransportFactory {
    public final Executor appExecutor;
    private final ClientTransportFactory delegate;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CallCredentialsApplyingTransport extends ForwardingConnectionClientTransport {
        public final String authority;
        public final ConnectionClientTransport delegate;
        private Status savedShutdownNowStatus;
        private Status savedShutdownStatus;
        private volatile Status shutdownStatus;
        public final AtomicInteger pendingApplier = new AtomicInteger(-2147483647);
        private final RetryingNameResolver.ResolutionResultListener applierListener$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);

        public CallCredentialsApplyingTransport(ConnectionClientTransport connectionClientTransport, String str) {
            this.delegate = connectionClientTransport;
            str.getClass();
            this.authority = str;
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport
        protected final ConnectionClientTransport delegate() {
            return this.delegate;
        }

        public final void maybeShutdown() {
            synchronized (this) {
                if (this.pendingApplier.get() != 0) {
                    return;
                }
                Status status = this.savedShutdownStatus;
                this.savedShutdownStatus = null;
                this.savedShutdownNowStatus = null;
                if (status != null) {
                    super.shutdown(status);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [io.grpc.InternalMayRequireSpecificExecutor] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        @Override // io.grpc.internal.ForwardingConnectionClientTransport, io.grpc.internal.ClientTransport
        public final ClientStream newStream(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
            ClientStream clientStream;
            Executor executor;
            OnDeviceFaceDetectionLogEvent onDeviceFaceDetectionLogEvent = callOptions.credentials$ar$class_merging;
            GoogleAuthLibraryCallCredentials googleAuthLibraryCallCredentials = onDeviceFaceDetectionLogEvent;
            if (onDeviceFaceDetectionLogEvent == null) {
                googleAuthLibraryCallCredentials = 0;
            }
            if (googleAuthLibraryCallCredentials != 0) {
                CallCredentials$MetadataApplier callCredentials$MetadataApplier = new CallCredentials$MetadataApplier(this.delegate, methodDescriptor, metadata, callOptions, this.applierListener$ar$class_merging$ar$class_merging$ar$class_merging, clientStreamTracerArr);
                if (this.pendingApplier.incrementAndGet() > 0) {
                    this.applierListener$ar$class_merging$ar$class_merging$ar$class_merging.onComplete();
                    return new FailingClientStream(this.shutdownStatus, clientStreamTracerArr);
                }
                CallCredentials$RequestInfo callCredentials$RequestInfo = new CallCredentials$RequestInfo(this, methodDescriptor, callOptions);
                try {
                    if (!googleAuthLibraryCallCredentials.isSpecificExecutorRequired() || (executor = callOptions.executor) == null) {
                        executor = CallCredentialsApplyingTransportFactory.this.appExecutor;
                    }
                    SecurityLevel securityLevel = (SecurityLevel) ContextDataProvider.firstNonNull((SecurityLevel) ((CallCredentialsApplyingTransport) callCredentials$RequestInfo.CallCredentials$RequestInfo$ar$this$1).delegate.getAttributes().get(GrpcAttributes.ATTR_SECURITY_LEVEL), SecurityLevel.NONE);
                    if (googleAuthLibraryCallCredentials.requirePrivacy && securityLevel != SecurityLevel.PRIVACY_AND_INTEGRITY) {
                        callCredentials$MetadataApplier.fail(Status.UNAUTHENTICATED.withDescription("Credentials require channel with PRIVACY_AND_INTEGRITY security level. Observed security level: ".concat(String.valueOf(String.valueOf(securityLevel)))));
                    } else {
                        try {
                            GoogleAuthLibraryCallCredentials.serviceUri((String) ContextDataProvider.firstNonNull(null, ((CallCredentialsApplyingTransport) callCredentials$RequestInfo.CallCredentials$RequestInfo$ar$this$1).authority), (MethodDescriptor) callCredentials$RequestInfo.CallCredentials$RequestInfo$ar$val$method);
                            googleAuthLibraryCallCredentials.creds.getRequestMetadata$ar$class_merging$ar$ds(executor, new GoogleAuthLibraryCallCredentials.AnonymousClass1(googleAuthLibraryCallCredentials, callCredentials$MetadataApplier));
                        } catch (StatusException e) {
                            callCredentials$MetadataApplier.fail(e.status);
                        }
                    }
                } catch (Throwable th) {
                    callCredentials$MetadataApplier.fail(Status.UNAUTHENTICATED.withDescription("Credentials should use fail() instead of throwing exceptions").withCause(th));
                }
                synchronized (callCredentials$MetadataApplier.lock) {
                    clientStream = callCredentials$MetadataApplier.returnedStream;
                    if (clientStream == null) {
                        callCredentials$MetadataApplier.delayedStream = new DelayedStream();
                        clientStream = callCredentials$MetadataApplier.delayedStream;
                        callCredentials$MetadataApplier.returnedStream = clientStream;
                    }
                }
                return clientStream;
            }
            if (this.pendingApplier.get() >= 0) {
                return new FailingClientStream(this.shutdownStatus, clientStreamTracerArr);
            }
            return this.delegate.newStream(methodDescriptor, metadata, callOptions, clientStreamTracerArr);
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport, io.grpc.internal.ManagedClientTransport
        public final void shutdown(Status status) {
            status.getClass();
            synchronized (this) {
                if (this.pendingApplier.get() < 0) {
                    this.shutdownStatus = status;
                    this.pendingApplier.addAndGet(Preference.DEFAULT_ORDER);
                    if (this.pendingApplier.get() != 0) {
                        this.savedShutdownStatus = status;
                    } else {
                        super.shutdown(status);
                    }
                }
            }
        }
    }

    public CallCredentialsApplyingTransportFactory(ClientTransportFactory clientTransportFactory, Executor executor) {
        this.delegate = clientTransportFactory;
        executor.getClass();
        this.appExecutor = executor;
    }

    @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.delegate.close();
    }

    @Override // io.grpc.internal.ClientTransportFactory
    public final ScheduledExecutorService getScheduledExecutorService() {
        return this.delegate.getScheduledExecutorService();
    }

    @Override // io.grpc.internal.ClientTransportFactory
    public final Collection getSupportedSocketAddressTypes() {
        return this.delegate.getSupportedSocketAddressTypes();
    }

    @Override // io.grpc.internal.ClientTransportFactory
    public final ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
        return new CallCredentialsApplyingTransport(this.delegate.newClientTransport(socketAddress, clientTransportOptions, channelLogger), clientTransportOptions.authority);
    }
}
