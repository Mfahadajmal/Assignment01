package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$PrimesStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$PrimesStats DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    private PrimesDebugMessage primesDebugMessage_;
    public int primesEvent_;
    private byte memoizedIsInitialized = 2;
    public int estimatedCount_ = 1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PrimesDebugMessage extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final PrimesDebugMessage DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private SystemHealthProto$CrashMetric previousCrash_;

        static {
            PrimesDebugMessage primesDebugMessage = new PrimesDebugMessage();
            DEFAULT_INSTANCE = primesDebugMessage;
            GeneratedMessageLite.registerDefaultInstance(PrimesDebugMessage.class, primesDebugMessage);
        }

        private PrimesDebugMessage() {
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
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"bitField0_", "previousCrash_"});
                case NEW_MUTABLE_INSTANCE:
                    return new PrimesDebugMessage();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (PrimesDebugMessage.class) {
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
    public final class PrimesEvent {
        public static final int UNKNOWN$ar$edu$b072fa9e_0 = 1;

        @Deprecated
        public static final int PRIMES_INITIALIZED$ar$edu = 2;
        public static final int PRIMES_CRASH_MONITORING_INITIALIZED$ar$edu = 3;
        public static final int PRIMES_FIRST_ACTIVITY_LAUNCHED$ar$edu = 4;
        public static final int PRIMES_CUSTOM_LAUNCHED$ar$edu = 5;
        public static final int PRIMES_CRASH_LOOP_MONITOR_INITIALIZED$ar$edu = 6;
        public static final int PRIMES_CRASH_LOOP_MONITOR_RECOVERED$ar$edu = 7;
        private static final /* synthetic */ int[] $VALUES$ar$edu$3605839e_0 = {UNKNOWN$ar$edu$b072fa9e_0, PRIMES_INITIALIZED$ar$edu, PRIMES_CRASH_MONITORING_INITIALIZED$ar$edu, PRIMES_FIRST_ACTIVITY_LAUNCHED$ar$edu, PRIMES_CUSTOM_LAUNCHED$ar$edu, PRIMES_CRASH_LOOP_MONITOR_INITIALIZED$ar$edu, PRIMES_CRASH_LOOP_MONITOR_RECOVERED$ar$edu};

        public static int forNumber$ar$edu$8c9c582f_0(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN$ar$edu$b072fa9e_0;
                case 1:
                    return PRIMES_INITIALIZED$ar$edu;
                case 2:
                    return PRIMES_CRASH_MONITORING_INITIALIZED$ar$edu;
                case 3:
                    return PRIMES_FIRST_ACTIVITY_LAUNCHED$ar$edu;
                case 4:
                    return PRIMES_CUSTOM_LAUNCHED$ar$edu;
                case 5:
                    return PRIMES_CRASH_LOOP_MONITOR_INITIALIZED$ar$edu;
                case 6:
                    return PRIMES_CRASH_LOOP_MONITOR_RECOVERED$ar$edu;
                default:
                    return 0;
            }
        }

        public static int[] values$ar$edu$7b1243c2_0() {
            return new int[]{UNKNOWN$ar$edu$b072fa9e_0, PRIMES_INITIALIZED$ar$edu, PRIMES_CRASH_MONITORING_INITIALIZED$ar$edu, PRIMES_FIRST_ACTIVITY_LAUNCHED$ar$edu, PRIMES_CUSTOM_LAUNCHED$ar$edu, PRIMES_CRASH_LOOP_MONITOR_INITIALIZED$ar$edu, PRIMES_CRASH_LOOP_MONITOR_RECOVERED$ar$edu};
        }
    }

    static {
        SystemHealthProto$PrimesStats systemHealthProto$PrimesStats = new SystemHealthProto$PrimesStats();
        DEFAULT_INSTANCE = systemHealthProto$PrimesStats;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$PrimesStats.class, systemHealthProto$PrimesStats);
    }

    private SystemHealthProto$PrimesStats() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001᠌\u0000\u0002င\u0001\u0003ᐉ\u0002", new Object[]{"bitField0_", "primesEvent_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$13, "estimatedCount_", "primesDebugMessage_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$PrimesStats();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$PrimesStats.class) {
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
