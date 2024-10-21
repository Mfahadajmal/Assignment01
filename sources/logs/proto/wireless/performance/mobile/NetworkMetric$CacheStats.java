package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$CacheStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final NetworkMetric$CacheStats DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int hitCount_;
    public int lookupCount_;

    static {
        NetworkMetric$CacheStats networkMetric$CacheStats = new NetworkMetric$CacheStats();
        DEFAULT_INSTANCE = networkMetric$CacheStats;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$CacheStats.class, networkMetric$CacheStats);
    }

    private NetworkMetric$CacheStats() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"bitField0_", "lookupCount_", "hitCount_"});
            case NEW_MUTABLE_INSTANCE:
                return new NetworkMetric$CacheStats();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$CacheStats.class) {
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
