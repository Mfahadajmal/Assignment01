package logs.proto.wireless.performance.mobile;

import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetric$MemoryUsageMetric extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final MemoryMetric$MemoryUsageMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public MemoryMetric$DeviceStats deviceStats_;
    public int memoryEventCode_;
    public MemoryMetric$MemoryStats memoryStats_;
    public ProcessProto$ProcessStats processStats_;
    private byte memoizedIsInitialized = 2;
    public String activityName_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MemoryEventCode {
        public static final int UNKNOWN$ar$edu$307db262_0 = 1;

        @Deprecated
        public static final int APP_CREATED$ar$edu = 2;
        public static final int APP_TO_BACKGROUND$ar$edu = 3;
        public static final int APP_TO_FOREGROUND$ar$edu = 4;
        public static final int APP_IN_BACKGROUND_FOR_SECONDS$ar$edu = 5;
        public static final int APP_IN_FOREGROUND_FOR_SECONDS$ar$edu = 6;
        public static final int DELTA_OF_MEMORY$ar$edu = 7;
        public static final int PERIODIC$ar$edu$307db262_0 = 8;
        private static final /* synthetic */ int[] $VALUES$ar$edu$86a4dbed_0 = {UNKNOWN$ar$edu$307db262_0, APP_CREATED$ar$edu, APP_TO_BACKGROUND$ar$edu, APP_TO_FOREGROUND$ar$edu, APP_IN_BACKGROUND_FOR_SECONDS$ar$edu, APP_IN_FOREGROUND_FOR_SECONDS$ar$edu, DELTA_OF_MEMORY$ar$edu, PERIODIC$ar$edu$307db262_0};

        public static int forNumber$ar$edu$cb599861_0(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN$ar$edu$307db262_0;
                case 1:
                    return APP_CREATED$ar$edu;
                case 2:
                    return APP_TO_BACKGROUND$ar$edu;
                case 3:
                    return APP_TO_FOREGROUND$ar$edu;
                case 4:
                    return APP_IN_BACKGROUND_FOR_SECONDS$ar$edu;
                case 5:
                    return APP_IN_FOREGROUND_FOR_SECONDS$ar$edu;
                case 6:
                    return DELTA_OF_MEMORY$ar$edu;
                case 7:
                    return PERIODIC$ar$edu$307db262_0;
                default:
                    return 0;
            }
        }

        public static int[] values$ar$edu$242ab9a2_0() {
            return new int[]{UNKNOWN$ar$edu$307db262_0, APP_CREATED$ar$edu, APP_TO_BACKGROUND$ar$edu, APP_TO_FOREGROUND$ar$edu, APP_IN_BACKGROUND_FOR_SECONDS$ar$edu, APP_IN_FOREGROUND_FOR_SECONDS$ar$edu, DELTA_OF_MEMORY$ar$edu, PERIODIC$ar$edu$307db262_0};
        }
    }

    static {
        MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric = new MemoryMetric$MemoryUsageMetric();
        DEFAULT_INSTANCE = memoryMetric$MemoryUsageMetric;
        GeneratedMessageLite.registerDefaultInstance(MemoryMetric$MemoryUsageMetric.class, memoryMetric$MemoryUsageMetric);
    }

    private MemoryMetric$MemoryUsageMetric() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003᠌\u0002\u0004ဉ\u0003\u0005ဈ\u0004", new Object[]{"bitField0_", "memoryStats_", "processStats_", "memoryEventCode_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$1, "deviceStats_", "activityName_"});
            case NEW_MUTABLE_INSTANCE:
                return new MemoryMetric$MemoryUsageMetric();
            case NEW_BUILDER:
                return new SchedulerOptions.Builder((boolean[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MemoryMetric$MemoryUsageMetric.class) {
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
