package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$DebugLogs extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$DebugLogs DEFAULT_INSTANCE;
    private static volatile Parser PARSER;

    static {
        SystemHealthProto$DebugLogs systemHealthProto$DebugLogs = new SystemHealthProto$DebugLogs();
        DEFAULT_INSTANCE = systemHealthProto$DebugLogs;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$DebugLogs.class, systemHealthProto$DebugLogs);
    }

    private SystemHealthProto$DebugLogs() {
        emptyLongList();
        emptyLongList();
        emptyIntList();
        emptyIntList();
        emptyIntList();
        GeneratedMessageLite.emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$DebugLogs();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$DebugLogs.class) {
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
