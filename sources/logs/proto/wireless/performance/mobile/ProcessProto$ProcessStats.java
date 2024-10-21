package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessProto$ProcessStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ProcessProto$ProcessStats DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public ProcessProto$AndroidProcessStats androidProcessStats_;
    public int bitField0_;

    static {
        ProcessProto$ProcessStats processProto$ProcessStats = new ProcessProto$ProcessStats();
        DEFAULT_INSTANCE = processProto$ProcessStats;
        GeneratedMessageLite.registerDefaultInstance(ProcessProto$ProcessStats.class, processProto$ProcessStats);
    }

    private ProcessProto$ProcessStats() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "androidProcessStats_"});
            case NEW_MUTABLE_INSTANCE:
                return new ProcessProto$ProcessStats();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ProcessProto$ProcessStats.class) {
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
