package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$SamplingParameters extends GeneratedMessageLite<SystemHealthProto$SamplingParameters, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
    public static final SystemHealthProto$SamplingParameters DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long sampleRatePermille_;
    public int samplingStrategy_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SamplingStrategy {
        public static final int UNKNOWN$ar$edu$b47db991_0 = 1;
        public static final int SAMPLING_STRATEGY_FLOOR$ar$edu = 2;
        public static final int SAMPLING_STRATEGY_ALWAYS_ON$ar$edu = 3;
        public static final int SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY$ar$edu = 4;
        public static final int SAMPLING_STRATEGY_FIXED_EVENT_PROBABILITY$ar$edu = 6;
        public static final int SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY$ar$edu = 5;
        private static final /* synthetic */ int[] $VALUES$ar$edu$479e221a_0 = {UNKNOWN$ar$edu$b47db991_0, SAMPLING_STRATEGY_FLOOR$ar$edu, SAMPLING_STRATEGY_ALWAYS_ON$ar$edu, SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY$ar$edu, SAMPLING_STRATEGY_FIXED_EVENT_PROBABILITY$ar$edu, SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY$ar$edu};

        public static int forNumber$ar$edu$fde10cc9_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return 0;
                                }
                                return SAMPLING_STRATEGY_FIXED_EVENT_PROBABILITY$ar$edu;
                            }
                            return SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY$ar$edu;
                        }
                        return SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY$ar$edu;
                    }
                    return SAMPLING_STRATEGY_ALWAYS_ON$ar$edu;
                }
                return SAMPLING_STRATEGY_FLOOR$ar$edu;
            }
            return UNKNOWN$ar$edu$b47db991_0;
        }

        public static int[] values$ar$edu$b383091f_0() {
            return new int[]{UNKNOWN$ar$edu$b47db991_0, SAMPLING_STRATEGY_FLOOR$ar$edu, SAMPLING_STRATEGY_ALWAYS_ON$ar$edu, SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY$ar$edu, SAMPLING_STRATEGY_FIXED_EVENT_PROBABILITY$ar$edu, SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY$ar$edu};
        }
    }

    static {
        SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters = new SystemHealthProto$SamplingParameters();
        DEFAULT_INSTANCE = systemHealthProto$SamplingParameters;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$SamplingParameters.class, systemHealthProto$SamplingParameters);
    }

    private SystemHealthProto$SamplingParameters() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0002\u0003\u0002\u0000\u0000\u0000\u0002ဂ\u0001\u0003᠌\u0002", new Object[]{"bitField0_", "sampleRatePermille_", "samplingStrategy_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$14});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$SamplingParameters();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$SamplingParameters.class) {
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
