package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$ServerNetworkEventUsage extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final NetworkMetric$ServerNetworkEventUsage DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String rpcPath_ = "";
    public Internal.LongList hashedRpcPath_ = emptyLongList();

    static {
        NetworkMetric$ServerNetworkEventUsage networkMetric$ServerNetworkEventUsage = new NetworkMetric$ServerNetworkEventUsage();
        DEFAULT_INSTANCE = networkMetric$ServerNetworkEventUsage;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$ServerNetworkEventUsage.class, networkMetric$ServerNetworkEventUsage);
    }

    private NetworkMetric$ServerNetworkEventUsage() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0003\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0003(", new Object[]{"bitField0_", "rpcPath_", "hashedRpcPath_"});
            case NEW_MUTABLE_INSTANCE:
                return new NetworkMetric$ServerNetworkEventUsage();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$ServerNetworkEventUsage.class) {
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
