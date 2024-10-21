package logs.proto.wireless.performance.mobile;

import com.google.common.logging.proto2api.Logrecord$ThrowableProto;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$CrashMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$CrashMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public CrashLoopInfo crashLoopInfo_;
    public int crashType_;
    public SystemHealthProto$CrashedTikTokTraceInfo crashedTraceInfo_;
    public Logrecord$ThrowableProto exception_;
    public boolean hasCrashed_;
    public ProcessProto$ProcessStats processStats_;
    private byte memoizedIsInitialized = 2;
    public String activeComponentName_ = "";
    public String threadName_ = "";
    public String crashClassName_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CrashLoopInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final CrashLoopInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int loopState_;
        public int previousCrashCount_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class LoopState {
            public static final int LOOP_STATE_UNSPECIFIED$ar$edu = 1;
            public static final int NO_LOOP$ar$edu = 2;
            public static final int NO_LOOP_TIMEOUT$ar$edu = 3;
            public static final int LOOP_COUNTED$ar$edu = 4;
            public static final int LOOP_DETECTED$ar$edu = 5;
            public static final int LOOP_OVERFLOW$ar$edu = 6;
            public static final int LOOP_STATE_ERROR$ar$edu = 7;
            private static final /* synthetic */ int[] $VALUES$ar$edu$33313158_0 = {LOOP_STATE_UNSPECIFIED$ar$edu, NO_LOOP$ar$edu, NO_LOOP_TIMEOUT$ar$edu, LOOP_COUNTED$ar$edu, LOOP_DETECTED$ar$edu, LOOP_OVERFLOW$ar$edu, LOOP_STATE_ERROR$ar$edu};

            public static int forNumber$ar$edu$eac8bba5_0(int i) {
                switch (i) {
                    case 0:
                        return LOOP_STATE_UNSPECIFIED$ar$edu;
                    case 1:
                        return NO_LOOP$ar$edu;
                    case 2:
                        return NO_LOOP_TIMEOUT$ar$edu;
                    case 3:
                        return LOOP_COUNTED$ar$edu;
                    case 4:
                        return LOOP_DETECTED$ar$edu;
                    case 5:
                        return LOOP_OVERFLOW$ar$edu;
                    case 6:
                        return LOOP_STATE_ERROR$ar$edu;
                    default:
                        return 0;
                }
            }

            public static int[] values$ar$edu$9ed78386_0() {
                return new int[]{LOOP_STATE_UNSPECIFIED$ar$edu, NO_LOOP$ar$edu, NO_LOOP_TIMEOUT$ar$edu, LOOP_COUNTED$ar$edu, LOOP_DETECTED$ar$edu, LOOP_OVERFLOW$ar$edu, LOOP_STATE_ERROR$ar$edu};
            }
        }

        static {
            CrashLoopInfo crashLoopInfo = new CrashLoopInfo();
            DEFAULT_INSTANCE = crashLoopInfo;
            GeneratedMessageLite.registerDefaultInstance(CrashLoopInfo.class, crashLoopInfo);
        }

        private CrashLoopInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "loopState_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$11, "previousCrashCount_"});
                case NEW_MUTABLE_INSTANCE:
                    return new CrashLoopInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (CrashLoopInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CrashType {
        public static final int UNKNOWN$ar$edu$774f7881_0 = 1;
        public static final int NULL_POINTER_EXCEPTION$ar$edu = 2;
        public static final int OUT_OF_MEMORY_ERROR$ar$edu = 3;
        public static final int OTHER_RUNTIME_EXCEPTION$ar$edu = 4;
        public static final int OTHER_ERROR$ar$edu = 5;
        public static final int NATIVE_CRASH$ar$edu = 6;
        private static final /* synthetic */ int[] $VALUES$ar$edu$49b96dbd_0 = {UNKNOWN$ar$edu$774f7881_0, NULL_POINTER_EXCEPTION$ar$edu, OUT_OF_MEMORY_ERROR$ar$edu, OTHER_RUNTIME_EXCEPTION$ar$edu, OTHER_ERROR$ar$edu, NATIVE_CRASH$ar$edu};

        public static int forNumber$ar$edu$e0a8c317_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return 0;
                                }
                                return NATIVE_CRASH$ar$edu;
                            }
                            return OTHER_ERROR$ar$edu;
                        }
                        return OTHER_RUNTIME_EXCEPTION$ar$edu;
                    }
                    return OUT_OF_MEMORY_ERROR$ar$edu;
                }
                return NULL_POINTER_EXCEPTION$ar$edu;
            }
            return UNKNOWN$ar$edu$774f7881_0;
        }

        public static int[] values$ar$edu$c1b6ffa_0() {
            return new int[]{UNKNOWN$ar$edu$774f7881_0, NULL_POINTER_EXCEPTION$ar$edu, OUT_OF_MEMORY_ERROR$ar$edu, OTHER_RUNTIME_EXCEPTION$ar$edu, OTHER_ERROR$ar$edu, NATIVE_CRASH$ar$edu};
        }
    }

    static {
        SystemHealthProto$CrashMetric systemHealthProto$CrashMetric = new SystemHealthProto$CrashMetric();
        DEFAULT_INSTANCE = systemHealthProto$CrashMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$CrashMetric.class, systemHealthProto$CrashMetric);
    }

    private SystemHealthProto$CrashMetric() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\f\t\u0000\u0000\u0001\u0001ဇ\u0000\u0002ဉ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005᠌\u0004\u0007ဈ\u0007\tᐉ\b\u000bဉ\n\fဉ\u000b", new Object[]{"bitField0_", "hasCrashed_", "processStats_", "activeComponentName_", "threadName_", "crashType_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$12, "crashClassName_", "exception_", "crashedTraceInfo_", "crashLoopInfo_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$CrashMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$CrashMetric.class) {
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
