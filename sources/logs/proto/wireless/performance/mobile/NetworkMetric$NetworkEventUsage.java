package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$NetworkEventUsage extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final NetworkMetric$NetworkEventUsage DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public NetworkMetric$CacheStats cacheStats_;
    public String domainPath_;
    public long eventSampleRatePermille_;
    public Internal.LongList hashedRpcPath_;
    public int httpStatusCode_;
    public NetworkMetric$NetworkConnectionInfo networkConnectionInfo_;
    public int networkingStack_;
    public ProcessProto$AndroidProcessStats processStats_;
    public int quicDetailedErrorCode_;
    public int requestFailedReason_;
    public int requestNegotiatedProtocol_;
    public int requestSizeBytes_;
    public int requestStatus_;
    public int responseSizeBytes_;
    public int retryCount_;
    public String rpcPath_;
    public NetworkMetric$RpcStats rpcStats_;
    public int serverDistance_;
    public long startTimeMs_;
    public int timeToResponseDataFinishMs_;
    public int timeToResponseHeaderMs_;
    private byte memoizedIsInitialized = 2;
    public String contentType_ = "";
    public String requestPath_ = "";
    public String constantRpcPath_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum NetworkingStack implements Internal.EnumLite {
        UNKNOWN(0),
        CRONET(1);

        public final int value;

        NetworkingStack(int i) {
            this.value = i;
        }

        public static NetworkingStack forNumber(int i) {
            if (i != 0) {
                if (i != 1) {
                    return null;
                }
                return CRONET;
            }
            return UNKNOWN;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return Integer.toString(this.value);
        }
    }

    static {
        NetworkMetric$NetworkEventUsage networkMetric$NetworkEventUsage = new NetworkMetric$NetworkEventUsage();
        DEFAULT_INSTANCE = networkMetric$NetworkEventUsage;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$NetworkEventUsage.class, networkMetric$NetworkEventUsage);
    }

    private NetworkMetric$NetworkEventUsage() {
        emptyProtobufList();
        this.rpcPath_ = "";
        this.hashedRpcPath_ = emptyLongList();
        this.domainPath_ = "";
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0018\u0000\u0001\u0001\u001c\u0018\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003င\u0003\u0004င\u0004\u0005င\u0005\u0006ဋ\u0006\u0007ဋ\u0007\b᠌\b\nဉ\t\u000bဉ\u000b\rဂ\u000e\u000e᠌\u000f\u000f᠌\u0010\u0010င\u0012\u0011ဈ\u0013\u0013င\u0011\u0014ဈ\u0015\u0015(\u0016ဉ\u0016\u0018᠌\n\u0019ဈ\u0002\u001aဉ\u0017\u001b᠌\f\u001cဂ\u0018", new Object[]{"bitField0_", "contentType_", "requestPath_", "timeToResponseDataFinishMs_", "timeToResponseHeaderMs_", "httpStatusCode_", "responseSizeBytes_", "requestSizeBytes_", "requestNegotiatedProtocol_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$6, "processStats_", "networkConnectionInfo_", "startTimeMs_", "requestStatus_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$7, "requestFailedReason_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$5, "retryCount_", "rpcPath_", "quicDetailedErrorCode_", "domainPath_", "hashedRpcPath_", "rpcStats_", "networkingStack_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$3, "constantRpcPath_", "cacheStats_", "serverDistance_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$4, "eventSampleRatePermille_"});
            case NEW_MUTABLE_INSTANCE:
                return new NetworkMetric$NetworkEventUsage();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$NetworkEventUsage.class) {
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
