package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetric$MemoryStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final MemoryMetric$MemoryStats DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public MemoryMetric$AndroidMemoryStats androidMemoryStats_;
    public int bitField0_;

    static {
        MemoryMetric$MemoryStats memoryMetric$MemoryStats = new MemoryMetric$MemoryStats();
        DEFAULT_INSTANCE = memoryMetric$MemoryStats;
        GeneratedMessageLite.registerDefaultInstance(MemoryMetric$MemoryStats.class, memoryMetric$MemoryStats);
    }

    private MemoryMetric$MemoryStats() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "androidMemoryStats_"});
            case NEW_MUTABLE_INSTANCE:
                return new MemoryMetric$MemoryStats();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MemoryMetric$MemoryStats.class) {
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
