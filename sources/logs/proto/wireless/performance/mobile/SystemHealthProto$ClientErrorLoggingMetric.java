package logs.proto.wireless.performance.mobile;

import com.google.common.logging.proto2api.Logrecord$LogRecordProto;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$ClientErrorLoggingMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$ClientErrorLoggingMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private Logrecord$LogRecordProto logRecord_;
    private byte memoizedIsInitialized = 2;

    static {
        SystemHealthProto$ClientErrorLoggingMetric systemHealthProto$ClientErrorLoggingMetric = new SystemHealthProto$ClientErrorLoggingMetric();
        DEFAULT_INSTANCE = systemHealthProto$ClientErrorLoggingMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$ClientErrorLoggingMetric.class, systemHealthProto$ClientErrorLoggingMetric);
    }

    private SystemHealthProto$ClientErrorLoggingMetric() {
        emptyProtobufList();
        GeneratedMessageLite.emptyProtobufList();
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0005\u0005\u0001\u0000\u0000\u0001\u0005ᐉ\u0003", new Object[]{"bitField0_", "logRecord_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$ClientErrorLoggingMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$ClientErrorLoggingMetric.class) {
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