package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceImageCaptioningCreateLogEvent;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.InternalChannelz;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import io.grpc.NameResolverRegistry;
import io.grpc.util.MultiChildLoadBalancer;
import java.lang.reflect.Method;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ManagedChannelImplBuilder extends ManagedChannelBuilder {
    public static final Method GET_CLIENT_INTERCEPTOR_METHOD;
    public final ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider;
    InternalChannelz channelz;
    public final ClientTransportFactoryBuilder clientTransportFactoryBuilder;
    CompressorRegistry compressorRegistry;
    DecompressorRegistry decompressorRegistry;
    String defaultLbPolicy;
    public ObjectPool executorPool;
    public long idleTimeoutMillis;
    public final List interceptors;
    boolean lookUpServiceConfig;
    int maxHedgedAttempts;
    int maxRetryAttempts;
    List metricSinks;
    public NameResolverRegistry nameResolverRegistry;
    public ObjectPool offloadExecutorPool;
    long perRpcBufferLimit;
    public boolean recordFinishedRpcs;
    public boolean recordRetryMetrics;
    public boolean recordStartedRpcs;
    long retryBufferSize;
    boolean retryEnabled;
    public boolean statsEnabled;
    public final String target;
    public boolean tracingEnabled;
    final List transportFilters;
    public String userAgent;
    public static final Logger log = Logger.getLogger(ManagedChannelImplBuilder.class.getName());
    static final long IDLE_MODE_DEFAULT_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(30);
    public static final long IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1);
    public static final ObjectPool DEFAULT_EXECUTOR_POOL = new SharedResourcePool(GrpcUtil.SHARED_CHANNEL_EXECUTOR, 0);
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.DEFAULT_INSTANCE;
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.DEFAULT_INSTANCE;
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ChannelBuilderDefaultPortProvider {
        int getDefaultPort();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ClientTransportFactoryBuilder {
        ClientTransportFactory buildClientTransportFactory();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InterceptorFactoryWrapper implements ClientInterceptor {
        public final ManagedChannelBuilder.InterceptorFactory factory;

        @Override // io.grpc.ClientInterceptor
        public final ClientCall interceptCall(MethodDescriptor methodDescriptor, CallOptions callOptions, Channel channel) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ManagedChannelDefaultPortProvider implements ChannelBuilderDefaultPortProvider {
        @Override // io.grpc.internal.ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider
        public final int getDefaultPort() {
            return 443;
        }
    }

    static {
        Method method = null;
        try {
            Class<?> cls = Class.forName("io.grpc.census.InternalCensusStatsAccessor");
            Class<?> cls2 = Boolean.TYPE;
            method = cls.getDeclaredMethod("getClientInterceptor", cls2, cls2, cls2, cls2);
        } catch (ClassNotFoundException e) {
            log.logp(Level.FINE, "io.grpc.internal.ManagedChannelImplBuilder", "<clinit>", "Unable to apply census stats", (Throwable) e);
        } catch (NoSuchMethodException e2) {
            log.logp(Level.FINE, "io.grpc.internal.ManagedChannelImplBuilder", "<clinit>", "Unable to apply census stats", (Throwable) e2);
        }
        GET_CLIENT_INTERCEPTOR_METHOD = method;
    }

    public ManagedChannelImplBuilder(String str, ClientTransportFactoryBuilder clientTransportFactoryBuilder, ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider) {
        ObjectPool objectPool = DEFAULT_EXECUTOR_POOL;
        this.executorPool = objectPool;
        this.offloadExecutorPool = objectPool;
        this.interceptors = new ArrayList();
        this.nameResolverRegistry = NameResolverRegistry.getDefaultRegistry();
        this.transportFilters = new ArrayList();
        this.defaultLbPolicy = "pick_first";
        this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        this.idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        this.maxRetryAttempts = 5;
        this.maxHedgedAttempts = 5;
        this.retryBufferSize = 16777216L;
        this.perRpcBufferLimit = 1048576L;
        this.retryEnabled = true;
        this.channelz = InternalChannelz.INSTANCE;
        this.lookUpServiceConfig = true;
        this.statsEnabled = true;
        this.recordStartedRpcs = true;
        this.recordFinishedRpcs = true;
        this.recordRetryMetrics = true;
        this.tracingEnabled = true;
        this.metricSinks = new ArrayList();
        str.getClass();
        this.target = str;
        this.clientTransportFactoryBuilder = clientTransportFactoryBuilder;
        this.channelBuilderDefaultPortProvider = channelBuilderDefaultPortProvider;
        OnDeviceImageCaptioningCreateLogEvent.configureChannelBuilder(this);
    }

    public static MultiChildLoadBalancer.AcceptResolvedAddrRetVal getNameResolverProvider$ar$class_merging(String str, NameResolverRegistry nameResolverRegistry, Collection collection) {
        URI uri;
        NameResolverProvider nameResolverProvider;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            sb.append(e.getMessage());
            uri = null;
        }
        if (uri != null) {
            nameResolverProvider = nameResolverRegistry.getProviderForScheme(uri.getScheme());
        } else {
            nameResolverProvider = null;
        }
        String str2 = "";
        if (nameResolverProvider == null && !URI_PATTERN.matcher(str).matches()) {
            try {
                uri = new URI(nameResolverRegistry.getDefaultScheme(), "", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(str, "/"), null);
                nameResolverProvider = nameResolverRegistry.getProviderForScheme(uri.getScheme());
            } catch (URISyntaxException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        if (nameResolverProvider == null) {
            if (sb.length() > 0) {
                str2 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(sb, " (", ")");
            }
            throw new IllegalArgumentException(String.format("Could not find a NameResolverProvider for %s%s", str, str2));
        }
        if (collection != null && !collection.containsAll(nameResolverProvider.getProducedSocketAddressTypes())) {
            throw new IllegalArgumentException(String.format("Address types of NameResolver '%s' for '%s' not supported by transport", uri.getScheme(), str));
        }
        return new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(uri, nameResolverProvider);
    }

    static String makeTargetStringForDirectAddress(SocketAddress socketAddress) {
        try {
            return new URI("directaddress", "", "/" + String.valueOf(socketAddress), null).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // io.grpc.ManagedChannelBuilder
    public final ManagedChannel build() {
        throw null;
    }

    public ManagedChannelImplBuilder(SocketAddress socketAddress, String str, ClientTransportFactoryBuilder clientTransportFactoryBuilder) {
        ObjectPool objectPool = DEFAULT_EXECUTOR_POOL;
        this.executorPool = objectPool;
        this.offloadExecutorPool = objectPool;
        this.interceptors = new ArrayList();
        this.nameResolverRegistry = NameResolverRegistry.getDefaultRegistry();
        this.transportFilters = new ArrayList();
        this.defaultLbPolicy = "pick_first";
        this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        this.idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        this.maxRetryAttempts = 5;
        this.maxHedgedAttempts = 5;
        this.retryBufferSize = 16777216L;
        this.perRpcBufferLimit = 1048576L;
        this.retryEnabled = true;
        this.channelz = InternalChannelz.INSTANCE;
        this.lookUpServiceConfig = true;
        this.statsEnabled = true;
        this.recordStartedRpcs = true;
        this.recordFinishedRpcs = true;
        this.recordRetryMetrics = true;
        this.tracingEnabled = true;
        this.metricSinks = new ArrayList();
        this.target = makeTargetStringForDirectAddress(socketAddress);
        this.clientTransportFactoryBuilder = clientTransportFactoryBuilder;
        NameResolverRegistry nameResolverRegistry = new NameResolverRegistry();
        nameResolverRegistry.register(new DirectAddressNameResolverProvider(socketAddress, str));
        this.nameResolverRegistry = nameResolverRegistry;
        this.channelBuilderDefaultPortProvider = new ManagedChannelDefaultPortProvider();
        OnDeviceImageCaptioningCreateLogEvent.configureChannelBuilder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DirectAddressNameResolverProvider extends NameResolverProvider {
        final SocketAddress address;
        final String authority;
        final Collection producedSocketAddressTypes;

        public DirectAddressNameResolverProvider(SocketAddress socketAddress, String str) {
            this.address = socketAddress;
            this.authority = str;
            this.producedSocketAddressTypes = Collections.singleton(socketAddress.getClass());
        }

        @Override // io.grpc.NameResolver.Factory
        public final String getDefaultScheme() {
            return "directaddress";
        }

        @Override // io.grpc.NameResolverProvider
        public final Collection getProducedSocketAddressTypes() {
            return this.producedSocketAddressTypes;
        }

        @Override // io.grpc.NameResolver.Factory
        public final NameResolver newNameResolver(URI uri, NameResolver.Args args) {
            return new NameResolver() { // from class: io.grpc.internal.ManagedChannelImplBuilder.DirectAddressNameResolverProvider.1
                public AnonymousClass1() {
                }

                @Override // io.grpc.NameResolver
                public final String getServiceAuthority() {
                    return DirectAddressNameResolverProvider.this.authority;
                }

                @Override // io.grpc.NameResolver
                public final void start(NameResolver.Listener2 listener2) {
                    AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent((byte[]) null, (byte[]) null);
                    aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = Collections.singletonList(new EquivalentAddressGroup(DirectAddressNameResolverProvider.this.address));
                    aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = Attributes.EMPTY;
                    listener2.onResult(aggregatedOnDeviceTextDetectionLogEvent.m221build());
                }

                @Override // io.grpc.NameResolver
                public final void shutdown() {
                }
            };
        }

        /* compiled from: PG */
        /* renamed from: io.grpc.internal.ManagedChannelImplBuilder$DirectAddressNameResolverProvider$1 */
        /* loaded from: classes.dex */
        final class AnonymousClass1 extends NameResolver {
            public AnonymousClass1() {
            }

            @Override // io.grpc.NameResolver
            public final String getServiceAuthority() {
                return DirectAddressNameResolverProvider.this.authority;
            }

            @Override // io.grpc.NameResolver
            public final void start(NameResolver.Listener2 listener2) {
                AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent((byte[]) null, (byte[]) null);
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = Collections.singletonList(new EquivalentAddressGroup(DirectAddressNameResolverProvider.this.address));
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = Attributes.EMPTY;
                listener2.onResult(aggregatedOnDeviceTextDetectionLogEvent.m221build());
            }

            @Override // io.grpc.NameResolver
            public final void shutdown() {
            }
        }

        @Override // io.grpc.NameResolverProvider
        public final void isAvailable$ar$ds$f3439281_1() {
        }

        @Override // io.grpc.NameResolverProvider
        public final void priority$ar$ds() {
        }
    }
}
