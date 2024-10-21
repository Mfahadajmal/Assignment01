package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$TraceMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$TraceMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    private byte memoizedIsInitialized = 2;
    public SystemHealthProto$TraceMetadata traceMetadata_;
    public PrimesTracing$Trace trace_;

    static {
        SystemHealthProto$TraceMetric systemHealthProto$TraceMetric = new SystemHealthProto$TraceMetric();
        DEFAULT_INSTANCE = systemHealthProto$TraceMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$TraceMetric.class, systemHealthProto$TraceMetric);
    }

    private SystemHealthProto$TraceMetric() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        byte b = 1;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                if (obj == null) {
                    b = 0;
                }
                this.memoizedIsInitialized = b;
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ဉ\u0000\u0002ᐉ\u0001", new Object[]{"bitField0_", "trace_", "traceMetadata_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$TraceMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$TraceMetric.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
