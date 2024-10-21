package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$TraceMetadata extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$TraceMetadata DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public boolean isPartialTrace_;
    private byte memoizedIsInitialized = 2;

    static {
        SystemHealthProto$TraceMetadata systemHealthProto$TraceMetadata = new SystemHealthProto$TraceMetadata();
        DEFAULT_INSTANCE = systemHealthProto$TraceMetadata;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$TraceMetadata.class, systemHealthProto$TraceMetadata);
    }

    private SystemHealthProto$TraceMetadata() {
        emptyProtobufList();
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0003\u0003\u0001\u0000\u0000\u0000\u0003ဇ\u0001", new Object[]{"bitField0_", "isPartialTrace_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$TraceMetadata();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$TraceMetadata.class) {
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
