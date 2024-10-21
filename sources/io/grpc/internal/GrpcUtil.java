package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import com.google.common.base.CharMatcher;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.ImageInfo;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.InternalMetadata;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.ProxyDetectorImpl;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.okhttp.OkHttpChannelBuilder;
import j$.util.DesugarCollections;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GrpcUtil {
    public static final CallOptions.Key CALL_OPTIONS_RPC_OWNED_BY_BALANCER;
    public static final Metadata.Key CONTENT_ACCEPT_ENCODING_KEY;
    public static final Metadata.Key CONTENT_ENCODING_KEY;
    static final Metadata.Key CONTENT_LENGTH_KEY;
    public static final Metadata.Key CONTENT_TYPE_KEY;
    public static final long DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
    public static final ProxyDetector DEFAULT_PROXY_DETECTOR;
    public static final Metadata.Key MESSAGE_ACCEPT_ENCODING_KEY;
    public static final Metadata.Key MESSAGE_ENCODING_KEY;
    private static final ClientStreamTracer NOOP_TRACER;
    public static final SharedResourceHolder.Resource SHARED_CHANNEL_EXECUTOR;
    public static final Supplier STOPWATCH_SUPPLIER;
    public static final Metadata.Key TE_HEADER;
    public static final Metadata.Key TIMEOUT_KEY;
    public static final SharedResourceHolder.Resource TIMER_SERVICE;
    public static final Metadata.Key USER_AGENT_KEY;
    private static final Logger log = Logger.getLogger(GrpcUtil.class.getName());
    private static final Set INAPPROPRIATE_CONTROL_PLANE_STATUS = DesugarCollections.unmodifiableSet(EnumSet.of(Status.Code.OK, Status.Code.INVALID_ARGUMENT, Status.Code.NOT_FOUND, Status.Code.ALREADY_EXISTS, Status.Code.FAILED_PRECONDITION, Status.Code.ABORTED, Status.Code.OUT_OF_RANGE, Status.Code.DATA_LOSS));

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.GrpcUtil$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 extends ClientStreamTracer {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Http2Error {
        NO_ERROR(0, Status.UNAVAILABLE),
        PROTOCOL_ERROR(1, Status.INTERNAL),
        INTERNAL_ERROR(2, Status.INTERNAL),
        FLOW_CONTROL_ERROR(3, Status.INTERNAL),
        SETTINGS_TIMEOUT(4, Status.INTERNAL),
        STREAM_CLOSED(5, Status.INTERNAL),
        FRAME_SIZE_ERROR(6, Status.INTERNAL),
        REFUSED_STREAM(7, Status.UNAVAILABLE),
        CANCEL(8, Status.CANCELLED),
        COMPRESSION_ERROR(9, Status.INTERNAL),
        CONNECT_ERROR(10, Status.INTERNAL),
        ENHANCE_YOUR_CALM(11, Status.RESOURCE_EXHAUSTED.withDescription("Bandwidth exhausted")),
        INADEQUATE_SECURITY(12, Status.PERMISSION_DENIED.withDescription("Permission denied as protocol is not secure enough to call")),
        HTTP_1_1_REQUIRED(13, Status.UNKNOWN);

        public static final Http2Error[] codeMap;
        private final int code;
        public final Status status;

        static {
            Http2Error[] values = values();
            Http2Error[] http2ErrorArr = new Http2Error[((int) values[values.length - 1].code()) + 1];
            for (Http2Error http2Error : values) {
                http2ErrorArr[(int) http2Error.code()] = http2Error;
            }
            codeMap = http2ErrorArr;
        }

        Http2Error(int i, Status status) {
            this.code = i;
            String concat = "HTTP/2 error code: ".concat(String.valueOf(name()));
            String str = status.description;
            if (str != null) {
                concat = concat + " (" + str + ")";
            }
            this.status = status.withDescription(concat);
        }

        public final long code() {
            return this.code;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TimeoutMarshaller implements Metadata.AsciiMarshaller {
        private final /* synthetic */ int switching_field;

        public TimeoutMarshaller(int i) {
            this.switching_field = i;
        }

        @Override // io.grpc.Metadata.AsciiMarshaller
        public final /* synthetic */ Object parseAsciiString(String str) {
            boolean z;
            boolean z2;
            if (this.switching_field != 0) {
                return str;
            }
            if (str.length() > 0) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z, (Object) "empty timeout");
            if (str.length() <= 9) {
                z2 = true;
            } else {
                z2 = false;
            }
            ContextDataProvider.checkArgument(z2, (Object) "bad timeout format");
            long parseLong = Long.parseLong(str.substring(0, str.length() - 1));
            char charAt = str.charAt(str.length() - 1);
            if (charAt != 'H') {
                if (charAt != 'M') {
                    if (charAt != 'S') {
                        if (charAt != 'u') {
                            if (charAt != 'm') {
                                if (charAt == 'n') {
                                    return Long.valueOf(parseLong);
                                }
                                throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", Character.valueOf(charAt)));
                            }
                            return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(parseLong));
                        }
                        return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(parseLong));
                    }
                    return Long.valueOf(TimeUnit.SECONDS.toNanos(parseLong));
                }
                return Long.valueOf(TimeUnit.MINUTES.toNanos(parseLong));
            }
            return Long.valueOf(TimeUnit.HOURS.toNanos(parseLong));
        }

        @Override // io.grpc.Metadata.AsciiMarshaller
        public final /* synthetic */ String toAsciiString(Object obj) {
            if (this.switching_field != 0) {
                return (String) obj;
            }
            Long l = (Long) obj;
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            if (l.longValue() >= 0) {
                if (l.longValue() < 100000000) {
                    Objects.toString(l);
                    return l.toString().concat("n");
                }
                if (l.longValue() < 100000000000L) {
                    return timeUnit.toMicros(l.longValue()) + "u";
                }
                if (l.longValue() < 100000000000000L) {
                    return timeUnit.toMillis(l.longValue()) + "m";
                }
                if (l.longValue() < 100000000000000000L) {
                    return timeUnit.toSeconds(l.longValue()) + "S";
                }
                if (l.longValue() < 6000000000000000000L) {
                    return timeUnit.toMinutes(l.longValue()) + "M";
                }
                return timeUnit.toHours(l.longValue()) + "H";
            }
            throw new IllegalArgumentException("Timeout too small");
        }
    }

    static {
        Charset.forName("US-ASCII");
        TIMEOUT_KEY = Metadata.Key.of("grpc-timeout", new TimeoutMarshaller(0));
        MESSAGE_ENCODING_KEY = Metadata.Key.of("grpc-encoding", Metadata.ASCII_STRING_MARSHALLER);
        MESSAGE_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf("grpc-accept-encoding", new Http2ClientStreamTransportState.AnonymousClass1(1));
        CONTENT_ENCODING_KEY = Metadata.Key.of("content-encoding", Metadata.ASCII_STRING_MARSHALLER);
        CONTENT_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf("accept-encoding", new Http2ClientStreamTransportState.AnonymousClass1(1));
        CONTENT_LENGTH_KEY = Metadata.Key.of("content-length", Metadata.ASCII_STRING_MARSHALLER);
        CONTENT_TYPE_KEY = Metadata.Key.of("content-type", Metadata.ASCII_STRING_MARSHALLER);
        TE_HEADER = Metadata.Key.of("te", Metadata.ASCII_STRING_MARSHALLER);
        USER_AGENT_KEY = Metadata.Key.of("user-agent", Metadata.ASCII_STRING_MARSHALLER);
        CharMatcher.Whitespace.INSTANCE.getClass();
        DEFAULT_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20L);
        TimeUnit.HOURS.toNanos(2L);
        TimeUnit.SECONDS.toNanos(20L);
        DEFAULT_PROXY_DETECTOR = new ProxyDetectorImpl();
        CALL_OPTIONS_RPC_OWNED_BY_BALANCER = new CallOptions.Key("io.grpc.internal.CALL_OPTIONS_RPC_OWNED_BY_BALANCER", null);
        NOOP_TRACER = new AnonymousClass2();
        SHARED_CHANNEL_EXECUTOR = new SharedResourceHolder.Resource() { // from class: io.grpc.internal.GrpcUtil.3
            @Override // io.grpc.internal.SharedResourceHolder.Resource
            public final /* bridge */ /* synthetic */ void close(Object obj) {
                ((ExecutorService) obj).shutdown();
            }

            @Override // io.grpc.internal.SharedResourceHolder.Resource
            public final /* bridge */ /* synthetic */ Object create() {
                return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory$ar$ds("grpc-default-executor-%d"));
            }

            public final String toString() {
                return "grpc-default-executor";
            }
        };
        TIMER_SERVICE = new OkHttpChannelBuilder.AnonymousClass1(1);
        STOPWATCH_SUPPLIER = new ProxyDetectorImpl.AnonymousClass2(1);
    }

    private GrpcUtil() {
    }

    public static String authorityFromHostAndPort(String str, int i) {
        try {
            return new URI(null, null, str, i, null, null, null).getAuthority();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid host or port: " + str + " " + i, e);
        }
    }

    public static URI authorityToUri(String str) {
        str.getClass();
        try {
            return new URI(null, str, null, null, null);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid authority: ".concat(str), e);
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            log.logp(Level.WARNING, "io.grpc.internal.GrpcUtil", "closeQuietly", "exception caught in closeQuietly", (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void closeQuietly$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        while (true) {
            InputStream next = onDeviceTextDetectionLoadLogEvent.next();
            if (next != null) {
                closeQuietly(next);
            } else {
                return;
            }
        }
    }

    public static ClientStreamTracer[] getClientStreamTracers(CallOptions callOptions, Metadata metadata, int i, boolean z) {
        List list = callOptions.streamTracerFactories;
        int size = list.size();
        ClientStreamTracer[] clientStreamTracerArr = new ClientStreamTracer[size + 1];
        ClientStreamTracer.StreamInfo.Builder builder = new ClientStreamTracer.StreamInfo.Builder();
        callOptions.getClass();
        builder.ClientStreamTracer$StreamInfo$Builder$ar$callOptions = callOptions;
        builder.previousAttempts = i;
        builder.isTransparentRetry = z;
        new ClientStreamTracer.StreamInfo((CallOptions) builder.ClientStreamTracer$StreamInfo$Builder$ar$callOptions, i, z);
        for (int i2 = 0; i2 < list.size(); i2++) {
            clientStreamTracerArr[i2] = ((OnDeviceFaceMeshLoadLogEvent) list.get(i2)).newClientStreamTracer$ar$ds();
        }
        clientStreamTracerArr[size] = NOOP_TRACER;
        return clientStreamTracerArr;
    }

    public static boolean getFlag$ar$ds(String str) {
        String str2 = System.getenv(str);
        if (str2 == null) {
            str2 = System.getProperty(str);
        }
        if (str2 != null) {
            str2 = str2.trim();
        }
        if (!ContextDataProvider.stringIsNullOrEmpty(str2) && Boolean.parseBoolean(str2)) {
            return true;
        }
        return false;
    }

    public static String getGrpcUserAgent(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append(str2);
            sb.append(' ');
        }
        sb.append("grpc-java-");
        sb.append(str);
        sb.append("/1.66.0-SNAPSHOT");
        return sb.toString();
    }

    public static String getHost(InetSocketAddress inetSocketAddress) {
        try {
            return (String) InetSocketAddress.class.getMethod("getHostString", null).invoke(inetSocketAddress, null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return inetSocketAddress.getHostName();
        }
    }

    public static ThreadFactory getThreadFactory$ar$ds(String str) {
        ImageInfo.Builder builder = new ImageInfo.Builder(null);
        builder.ImageInfo$Builder$ar$imageFormat = true;
        builder.setNameFormat$ar$ds(str);
        return ImageInfo.Builder.doBuild$ar$class_merging(builder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClientTransport getTransportFromPickResult(LoadBalancer.PickResult pickResult, boolean z) {
        ClientTransport clientTransport;
        LoadBalancer.Subchannel subchannel = pickResult.subchannel;
        if (subchannel != null) {
            clientTransport = ((InternalSubchannel) subchannel.getInternalSubchannel()).obtainActiveTransport();
        } else {
            clientTransport = null;
        }
        if (clientTransport == null) {
            if (!pickResult.status.isOk()) {
                if (pickResult.drop) {
                    return new FailingClientTransport(replaceInappropriateControlPlaneStatus(pickResult.status), ClientStreamListener.RpcProgress.DROPPED);
                }
                if (!z) {
                    return new FailingClientTransport(replaceInappropriateControlPlaneStatus(pickResult.status), ClientStreamListener.RpcProgress.PROCESSED);
                }
            }
            return null;
        }
        return clientTransport;
    }

    public static Status httpStatusToGrpcStatus(int i) {
        Status.Code code;
        if (i >= 100 && i < 200) {
            code = Status.Code.INTERNAL;
        } else {
            if (i != 400) {
                if (i != 401) {
                    if (i != 403) {
                        if (i != 404) {
                            if (i != 429) {
                                if (i != 431) {
                                    switch (i) {
                                        case 502:
                                        case 503:
                                        case 504:
                                            break;
                                        default:
                                            code = Status.Code.UNKNOWN;
                                            break;
                                    }
                                }
                            }
                            code = Status.Code.UNAVAILABLE;
                        } else {
                            code = Status.Code.UNIMPLEMENTED;
                        }
                    } else {
                        code = Status.Code.PERMISSION_DENIED;
                    }
                } else {
                    code = Status.Code.UNAUTHENTICATED;
                }
            }
            code = Status.Code.INTERNAL;
        }
        return code.toStatus().withDescription(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "HTTP status code "));
    }

    public static Status replaceInappropriateControlPlaneStatus(Status status) {
        boolean z;
        if (status != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z);
        if (INAPPROPRIATE_CONTROL_PLANE_STATUS.contains(status.code)) {
            Status.Code code = status.code;
            return Status.INTERNAL.withDescription("Inappropriate status code from control plane: " + code.toString() + " " + status.description).withCause(status.cause);
        }
        return status;
    }
}
