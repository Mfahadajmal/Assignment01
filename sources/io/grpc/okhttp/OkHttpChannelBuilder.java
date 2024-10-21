package io.grpc.okhttp;

import androidx.preference.Preference;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.OnDeviceImageQualityAnalysisLoadLogEvent;
import io.grpc.ChannelLogger;
import io.grpc.ForwardingChannelBuilder2;
import io.grpc.ManagedChannelBuilder;
import io.grpc.TlsChannelCredentials$Feature;
import io.grpc.cronet.CronetChannelBuilder;
import io.grpc.internal.AtomicBackoff;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.internal.CipherSuite;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.TlsVersion;
import io.grpc.okhttp.internal.framed.Http2;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OkHttpChannelBuilder extends ForwardingChannelBuilder2 {
    static final ObjectPool DEFAULT_TRANSPORT_EXECUTOR_POOL;
    static final ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC;
    private static final SharedResourceHolder.Resource SHARED_EXECUTOR;
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private SSLSocketFactory sslSocketFactory;
    public RemoteModelManager transportTracerFactory$ar$class_merging$ar$class_merging = TransportTracer.DEFAULT_FACTORY$ar$class_merging$ar$class_merging;
    public ObjectPool transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
    public ObjectPool scheduledExecutorServicePool = new SharedResourcePool(GrpcUtil.TIMER_SERVICE, 0);
    public ConnectionSpec connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
    public int negotiationType$ar$edu = 1;
    public long keepAliveTimeNanos = Long.MAX_VALUE;
    public long keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
    public int flowControlWindow = 65535;
    public int maxInboundMessageSize = 4194304;
    public int maxInboundMetadataSize = Preference.DEFAULT_ORDER;

    /* compiled from: PG */
    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements SharedResourceHolder.Resource {
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(int i) {
            this.switching_field = i;
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public final /* synthetic */ void close(Object obj) {
            if (this.switching_field != 0) {
                ((ScheduledExecutorService) obj).shutdown();
            } else {
                ((ExecutorService) obj).shutdown();
            }
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public final /* synthetic */ Object create() {
            if (this.switching_field != 0) {
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, GrpcUtil.getThreadFactory$ar$ds("grpc-timer-%d"));
                try {
                    newScheduledThreadPool.getClass().getMethod("setRemoveOnCancelPolicy", Boolean.TYPE).invoke(newScheduledThreadPool, true);
                } catch (NoSuchMethodException unused) {
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
                return Executors.unconfigurableScheduledExecutorService(newScheduledThreadPool);
            }
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory$ar$ds("grpc-okhttp-%d"));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OkHttpChannelDefaultPortProvider implements ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider {
        public OkHttpChannelDefaultPortProvider() {
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider
        public final int getDefaultPort() {
            int i = OkHttpChannelBuilder.this.negotiationType$ar$edu;
            int i2 = i - 1;
            if (i != 0) {
                if (i2 == 0) {
                    return 443;
                }
                throw new AssertionError(OnDeviceImageQualityAnalysisLoadLogEvent.toStringGenerated342f7df7af3f90fc(1).concat(" not handled"));
            }
            throw null;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OkHttpChannelTransportFactoryBuilder implements ManagedChannelImplBuilder.ClientTransportFactoryBuilder {
        final /* synthetic */ ForwardingChannelBuilder2 OkHttpChannelBuilder$OkHttpChannelTransportFactoryBuilder$ar$this$0;
        private final /* synthetic */ int switching_field;

        public OkHttpChannelTransportFactoryBuilder(ForwardingChannelBuilder2 forwardingChannelBuilder2, int i) {
            this.switching_field = i;
            this.OkHttpChannelBuilder$OkHttpChannelTransportFactoryBuilder$ar$this$0 = forwardingChannelBuilder2;
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
        public final ClientTransportFactory buildClientTransportFactory() {
            boolean z;
            if (this.switching_field != 0) {
                CronetChannelBuilder cronetChannelBuilder = (CronetChannelBuilder) this.OkHttpChannelBuilder$OkHttpChannelTransportFactoryBuilder$ar$this$0;
                return new CronetChannelBuilder.CronetTransportFactory(new CronetChannelBuilder.StreamBuilderFactory(cronetChannelBuilder.cronetEngine, cronetChannelBuilder.trafficStatsTagSet, cronetChannelBuilder.trafficStatsTag, cronetChannelBuilder.trafficStatsUidSet, cronetChannelBuilder.trafficStatsUid), DirectExecutor.INSTANCE, null, cronetChannelBuilder.maxMessageSize, cronetChannelBuilder.transportTracerFactory$ar$class_merging$ar$class_merging.create());
            }
            OkHttpChannelBuilder okHttpChannelBuilder = (OkHttpChannelBuilder) this.OkHttpChannelBuilder$OkHttpChannelTransportFactoryBuilder$ar$this$0;
            if (okHttpChannelBuilder.keepAliveTimeNanos != Long.MAX_VALUE) {
                z = true;
            } else {
                z = false;
            }
            return new OkHttpTransportFactory(okHttpChannelBuilder.transportExecutorPool, okHttpChannelBuilder.scheduledExecutorServicePool, okHttpChannelBuilder.createSslSocketFactory(), okHttpChannelBuilder.connectionSpec, okHttpChannelBuilder.maxInboundMessageSize, z, okHttpChannelBuilder.keepAliveTimeNanos, okHttpChannelBuilder.keepAliveTimeoutNanos, okHttpChannelBuilder.flowControlWindow, okHttpChannelBuilder.maxInboundMetadataSize, okHttpChannelBuilder.transportTracerFactory$ar$class_merging$ar$class_merging);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        final ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        final Executor executor;
        private final ObjectPool executorPool;
        final int flowControlWindow;
        private final AtomicBackoff keepAliveBackoff;
        private final long keepAliveTimeoutNanos;
        final int maxInboundMetadataSize;
        final int maxMessageSize;
        final ScheduledExecutorService scheduledExecutorService;
        private final ObjectPool scheduledExecutorServicePool;
        final SSLSocketFactory sslSocketFactory;
        final RemoteModelManager transportTracerFactory$ar$class_merging$ar$class_merging;

        /* JADX WARN: Type inference failed for: r1v1, types: [java.util.concurrent.Executor, java.lang.Object] */
        public OkHttpTransportFactory(ObjectPool objectPool, ObjectPool objectPool2, SSLSocketFactory sSLSocketFactory, ConnectionSpec connectionSpec, int i, boolean z, long j, long j2, int i2, int i3, RemoteModelManager remoteModelManager) {
            this.executorPool = objectPool;
            this.executor = objectPool.getObject();
            this.scheduledExecutorServicePool = objectPool2;
            this.scheduledExecutorService = (ScheduledExecutorService) objectPool2.getObject();
            this.sslSocketFactory = sSLSocketFactory;
            this.connectionSpec = connectionSpec;
            this.maxMessageSize = i;
            this.enableKeepAlive = z;
            this.keepAliveBackoff = new AtomicBackoff(j);
            this.keepAliveTimeoutNanos = j2;
            this.flowControlWindow = i2;
            this.maxInboundMetadataSize = i3;
            remoteModelManager.getClass();
            this.transportTracerFactory$ar$class_merging$ar$class_merging = remoteModelManager;
        }

        @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.executorPool.returnObject$ar$ds(this.executor);
            this.scheduledExecutorServicePool.returnObject$ar$ds(this.scheduledExecutorService);
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public final ScheduledExecutorService getScheduledExecutorService() {
            return this.scheduledExecutorService;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public final Collection getSupportedSocketAddressTypes() {
            return Collections.singleton(InetSocketAddress.class);
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public final ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            if (!this.closed) {
                AtomicBackoff atomicBackoff = this.keepAliveBackoff;
                AtomicBackoff.State state = new AtomicBackoff.State(atomicBackoff.value.get());
                RetriableStream$Sublistener$1RetryBackoffRunnable$1 retriableStream$Sublistener$1RetryBackoffRunnable$1 = new RetriableStream$Sublistener$1RetryBackoffRunnable$1(state, 7);
                OkHttpClientTransport okHttpClientTransport = new OkHttpClientTransport(this, (InetSocketAddress) socketAddress, clientTransportOptions.authority, clientTransportOptions.userAgent, clientTransportOptions.eagAttributes, GrpcUtil.STOPWATCH_SUPPLIER, new Http2(), clientTransportOptions.connectProxiedSocketAddr, retriableStream$Sublistener$1RetryBackoffRunnable$1);
                if (this.enableKeepAlive) {
                    long j = state.savedValue;
                    long j2 = this.keepAliveTimeoutNanos;
                    okHttpClientTransport.enableKeepAlive = true;
                    okHttpClientTransport.keepAliveTimeNanos = j;
                    okHttpClientTransport.keepAliveTimeoutNanos = j2;
                }
                return okHttpClientTransport;
            }
            throw new IllegalStateException("The transport factory is closed.");
        }
    }

    static {
        Logger.getLogger(OkHttpChannelBuilder.class.getName());
        ConnectionSpec.Builder builder = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
        builder.cipherSuites$ar$ds(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256);
        builder.tlsVersions$ar$ds(TlsVersion.TLS_1_2);
        builder.supportsTlsExtensions$ar$ds();
        INTERNAL_DEFAULT_CONNECTION_SPEC = new ConnectionSpec(builder);
        TimeUnit.DAYS.toNanos(1000L);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        SHARED_EXECUTOR = anonymousClass1;
        DEFAULT_TRANSPORT_EXECUTOR_POOL = new SharedResourcePool(anonymousClass1, 0);
        EnumSet.of(TlsChannelCredentials$Feature.MTLS, TlsChannelCredentials$Feature.CUSTOM_MANAGERS);
    }

    public OkHttpChannelBuilder(String str) {
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(str, new OkHttpChannelTransportFactoryBuilder(this, 0), new OkHttpChannelDefaultPortProvider());
    }

    final SSLSocketFactory createSslSocketFactory() {
        int i = this.negotiationType$ar$edu;
        int i2 = i - 1;
        if (i != 0) {
            if (i2 == 0) {
                try {
                    if (this.sslSocketFactory == null) {
                        this.sslSocketFactory = SSLContext.getInstance("Default", Platform.PLATFORM.sslProvider).getSocketFactory();
                    }
                    return this.sslSocketFactory;
                } catch (GeneralSecurityException e) {
                    throw new RuntimeException("TLS Provider failure", e);
                }
            }
            throw new RuntimeException("Unknown negotiation type: ".concat(OnDeviceImageQualityAnalysisLoadLogEvent.toStringGenerated342f7df7af3f90fc(1)));
        }
        throw null;
    }

    @Override // io.grpc.ForwardingChannelBuilder2
    public final ManagedChannelBuilder delegate() {
        return this.managedChannelImplBuilder;
    }
}
