package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$ApplicationInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$ApplicationInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int hardwareVariant_;
    public long primesVersion_;
    public String applicationPackage_ = "";
    public String applicationVersionName_ = "";
    public String shortProcessName_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class HardwareVariant {
        public static final int UNKNOWN_HARDWARE_VARIANT$ar$edu = 1;
        public static final int PHONE_OR_TABLET$ar$edu = 2;
        public static final int WATCH$ar$edu = 3;
        public static final int LEANBACK$ar$edu = 4;
        public static final int AUTOMOTIVE$ar$edu = 5;
        private static final /* synthetic */ int[] $VALUES$ar$edu$3837ad5_0 = {UNKNOWN_HARDWARE_VARIANT$ar$edu, PHONE_OR_TABLET$ar$edu, WATCH$ar$edu, LEANBACK$ar$edu, AUTOMOTIVE$ar$edu};

        public static int forNumber$ar$edu$1f4071e5_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return 0;
                            }
                            return AUTOMOTIVE$ar$edu;
                        }
                        return LEANBACK$ar$edu;
                    }
                    return WATCH$ar$edu;
                }
                return PHONE_OR_TABLET$ar$edu;
            }
            return UNKNOWN_HARDWARE_VARIANT$ar$edu;
        }

        public static int[] values$ar$edu$9b2f9da9_0() {
            return new int[]{UNKNOWN_HARDWARE_VARIANT$ar$edu, PHONE_OR_TABLET$ar$edu, WATCH$ar$edu, LEANBACK$ar$edu, AUTOMOTIVE$ar$edu};
        }
    }

    static {
        SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo = new SystemHealthProto$ApplicationInfo();
        DEFAULT_INSTANCE = systemHealthProto$ApplicationInfo;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$ApplicationInfo.class, systemHealthProto$ApplicationInfo);
    }

    private SystemHealthProto$ApplicationInfo() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003᠌\u0002\u0004ဂ\u0003\u0005ဈ\u0004", new Object[]{"bitField0_", "applicationPackage_", "applicationVersionName_", "hardwareVariant_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$10, "primesVersion_", "shortProcessName_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$ApplicationInfo();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$ApplicationInfo.class) {
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
