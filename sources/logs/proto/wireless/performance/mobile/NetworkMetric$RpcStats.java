package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$RpcStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final NetworkMetric$RpcStats DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int rpcStatusCode_;

    static {
        NetworkMetric$RpcStats networkMetric$RpcStats = new NetworkMetric$RpcStats();
        DEFAULT_INSTANCE = networkMetric$RpcStats;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$RpcStats.class, networkMetric$RpcStats);
    }

    private NetworkMetric$RpcStats() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"bitField0_", "rpcStatusCode_"});
            case NEW_MUTABLE_INSTANCE:
                return new NetworkMetric$RpcStats();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$RpcStats.class) {
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
