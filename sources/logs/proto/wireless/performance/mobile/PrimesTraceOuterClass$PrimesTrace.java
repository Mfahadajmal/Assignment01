package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceOuterClass$PrimesTrace extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final PrimesTraceOuterClass$PrimesTrace DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    private byte memoizedIsInitialized = 2;
    public Internal.ProtobufList spans_ = emptyProtobufList();
    private PrimesTraceOuterClass$StartupMeasurements startupMeasurements_;
    public long traceId_;

    static {
        PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace = new PrimesTraceOuterClass$PrimesTrace();
        DEFAULT_INSTANCE = primesTraceOuterClass$PrimesTrace;
        GeneratedMessageLite.registerDefaultInstance(PrimesTraceOuterClass$PrimesTrace.class, primesTraceOuterClass$PrimesTrace);
    }

    private PrimesTraceOuterClass$PrimesTrace() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0006\u0003\u0000\u0001\u0001\u0001စ\u0000\u0002Л\u0006ဉ\u0004", new Object[]{"bitField0_", "traceId_", "spans_", PrimesTraceOuterClass$Span.class, "startupMeasurements_"});
            case NEW_MUTABLE_INSTANCE:
                return new PrimesTraceOuterClass$PrimesTrace();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PrimesTraceOuterClass$PrimesTrace.class) {
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

    public final void ensureSpansIsMutable() {
        Internal.ProtobufList protobufList = this.spans_;
        if (!protobufList.isModifiable()) {
            this.spans_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }
}
