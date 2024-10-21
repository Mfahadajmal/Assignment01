package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$NetworkUsageMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final NetworkMetric$NetworkUsageMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private byte memoizedIsInitialized = 2;
    public Internal.ProtobufList networkEventUsage_ = emptyProtobufList();
    public Internal.ProtobufList serverNetworkEventUsage_ = emptyProtobufList();

    static {
        NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric = new NetworkMetric$NetworkUsageMetric();
        DEFAULT_INSTANCE = networkMetric$NetworkUsageMetric;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$NetworkUsageMetric.class, networkMetric$NetworkUsageMetric);
    }

    private NetworkMetric$NetworkUsageMetric() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0002\u0001\u0001Л\u0003\u001b", new Object[]{"networkEventUsage_", NetworkMetric$NetworkEventUsage.class, "serverNetworkEventUsage_", NetworkMetric$ServerNetworkEventUsage.class});
            case NEW_MUTABLE_INSTANCE:
                return new NetworkMetric$NetworkUsageMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$NetworkUsageMetric.class) {
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

    public final void ensureNetworkEventUsageIsMutable() {
        Internal.ProtobufList protobufList = this.networkEventUsage_;
        if (!protobufList.isModifiable()) {
            this.networkEventUsage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }
}
