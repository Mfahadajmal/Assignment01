package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$NetworkConnectionInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final NetworkMetric$NetworkConnectionInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int networkType_ = -1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NetworkType {
        public static final int NONE$ar$edu$5e383aad_0 = 1;
        public static final int MOBILE$ar$edu = 2;
        public static final int WIFI$ar$edu = 3;
        public static final int MOBILE_MMS$ar$edu = 4;
        public static final int MOBILE_SUPL$ar$edu = 5;
        public static final int MOBILE_DUN$ar$edu = 6;
        public static final int MOBILE_HIPRI$ar$edu = 7;
        public static final int WIMAX$ar$edu = 8;
        public static final int BLUETOOTH$ar$edu = 9;
        public static final int DUMMY$ar$edu = 10;
        public static final int ETHERNET$ar$edu = 11;
        public static final int MOBILE_FOTA$ar$edu = 12;
        public static final int MOBILE_IMS$ar$edu = 13;
        public static final int MOBILE_CBS$ar$edu = 14;
        public static final int WIFI_P2P$ar$edu = 15;
        public static final int MOBILE_IA$ar$edu = 16;
        public static final int MOBILE_EMERGENCY$ar$edu = 17;
        public static final int PROXY$ar$edu = 18;
        public static final int VPN$ar$edu = 19;
        private static final /* synthetic */ int[] $VALUES$ar$edu$cd928d9d_0 = {NONE$ar$edu$5e383aad_0, MOBILE$ar$edu, WIFI$ar$edu, MOBILE_MMS$ar$edu, MOBILE_SUPL$ar$edu, MOBILE_DUN$ar$edu, MOBILE_HIPRI$ar$edu, WIMAX$ar$edu, BLUETOOTH$ar$edu, DUMMY$ar$edu, ETHERNET$ar$edu, MOBILE_FOTA$ar$edu, MOBILE_IMS$ar$edu, MOBILE_CBS$ar$edu, WIFI_P2P$ar$edu, MOBILE_IA$ar$edu, MOBILE_EMERGENCY$ar$edu, PROXY$ar$edu, VPN$ar$edu};

        public static int forNumber$ar$edu$1098a20c_0(int i) {
            switch (i) {
                case -1:
                    return NONE$ar$edu$5e383aad_0;
                case 0:
                    return MOBILE$ar$edu;
                case 1:
                    return WIFI$ar$edu;
                case 2:
                    return MOBILE_MMS$ar$edu;
                case 3:
                    return MOBILE_SUPL$ar$edu;
                case 4:
                    return MOBILE_DUN$ar$edu;
                case 5:
                    return MOBILE_HIPRI$ar$edu;
                case 6:
                    return WIMAX$ar$edu;
                case 7:
                    return BLUETOOTH$ar$edu;
                case 8:
                    return DUMMY$ar$edu;
                case 9:
                    return ETHERNET$ar$edu;
                case 10:
                    return MOBILE_FOTA$ar$edu;
                case 11:
                    return MOBILE_IMS$ar$edu;
                case 12:
                    return MOBILE_CBS$ar$edu;
                case 13:
                    return WIFI_P2P$ar$edu;
                case 14:
                    return MOBILE_IA$ar$edu;
                case 15:
                    return MOBILE_EMERGENCY$ar$edu;
                case 16:
                    return PROXY$ar$edu;
                case 17:
                    return VPN$ar$edu;
                default:
                    return 0;
            }
        }

        public static /* synthetic */ String toStringGenerated3049f23212f12367(int i) {
            switch (i) {
                case 1:
                    return "NONE";
                case 2:
                    return "MOBILE";
                case 3:
                    return "WIFI";
                case 4:
                    return "MOBILE_MMS";
                case 5:
                    return "MOBILE_SUPL";
                case 6:
                    return "MOBILE_DUN";
                case 7:
                    return "MOBILE_HIPRI";
                case 8:
                    return "WIMAX";
                case 9:
                    return "BLUETOOTH";
                case 10:
                    return "DUMMY";
                case 11:
                    return "ETHERNET";
                case 12:
                    return "MOBILE_FOTA";
                case 13:
                    return "MOBILE_IMS";
                case 14:
                    return "MOBILE_CBS";
                case 15:
                    return "WIFI_P2P";
                case 16:
                    return "MOBILE_IA";
                case 17:
                    return "MOBILE_EMERGENCY";
                case 18:
                    return "PROXY";
                case 19:
                    return "VPN";
                default:
                    return "null";
            }
        }

        public static int[] values$ar$edu$a7a6812b_0() {
            return new int[]{NONE$ar$edu$5e383aad_0, MOBILE$ar$edu, WIFI$ar$edu, MOBILE_MMS$ar$edu, MOBILE_SUPL$ar$edu, MOBILE_DUN$ar$edu, MOBILE_HIPRI$ar$edu, WIMAX$ar$edu, BLUETOOTH$ar$edu, DUMMY$ar$edu, ETHERNET$ar$edu, MOBILE_FOTA$ar$edu, MOBILE_IMS$ar$edu, MOBILE_CBS$ar$edu, WIFI_P2P$ar$edu, MOBILE_IA$ar$edu, MOBILE_EMERGENCY$ar$edu, PROXY$ar$edu, VPN$ar$edu};
        }
    }

    static {
        NetworkMetric$NetworkConnectionInfo networkMetric$NetworkConnectionInfo = new NetworkMetric$NetworkConnectionInfo();
        DEFAULT_INSTANCE = networkMetric$NetworkConnectionInfo;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$NetworkConnectionInfo.class, networkMetric$NetworkConnectionInfo);
    }

    private NetworkMetric$NetworkConnectionInfo() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"bitField0_", "networkType_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$2});
            case NEW_MUTABLE_INSTANCE:
                return new NetworkMetric$NetworkConnectionInfo();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$NetworkConnectionInfo.class) {
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
