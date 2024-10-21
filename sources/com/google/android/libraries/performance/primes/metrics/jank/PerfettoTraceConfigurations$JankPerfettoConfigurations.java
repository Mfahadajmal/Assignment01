package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PerfettoTraceConfigurations$JankPerfettoConfigurations extends GeneratedMessageLite<PerfettoTraceConfigurations$JankPerfettoConfigurations, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
    public static final PerfettoTraceConfigurations$JankPerfettoConfigurations DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public boolean flushingToPerfettoEnabled_;
    public String triggerNameFormatString_ = "";
    public Internal.ProtobufList counter_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Counter extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final Counter DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private int bitField0_;
        public String name_ = "";
        public int type_;

        static {
            Counter counter = new Counter();
            DEFAULT_INSTANCE = counter;
            GeneratedMessageLite.registerDefaultInstance(Counter.class, counter);
        }

        private Counter() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "type_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$2, "name_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Counter();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (int[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (Counter.class) {
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
    public final class CounterType {
        public static final int COUNTER_UNKNOWN$ar$edu = 1;
        public static final int COUNTER_EMPTY$ar$edu = 2;
        public static final int COUNTER_JANKY_FRAME_COUNT$ar$edu = 3;
        public static final int COUNTER_TOTAL_FRAME_COUNT$ar$edu = 4;
        public static final int COUNTER_DROPPED_REPORT_COUNT$ar$edu = 5;
        public static final int COUNTER_MAX_FRAME_DURATION_MS$ar$edu = 6;
        public static final int COUNTER_TOTAL_JANKY_FRAME_DURATION_MS$ar$edu = 7;
        public static final int COUNTER_TOTAL_FRAME_DURATION_MS$ar$edu = 8;
        private static final /* synthetic */ int[] $VALUES$ar$edu$58908ff3_0 = {COUNTER_UNKNOWN$ar$edu, COUNTER_EMPTY$ar$edu, COUNTER_JANKY_FRAME_COUNT$ar$edu, COUNTER_TOTAL_FRAME_COUNT$ar$edu, COUNTER_DROPPED_REPORT_COUNT$ar$edu, COUNTER_MAX_FRAME_DURATION_MS$ar$edu, COUNTER_TOTAL_JANKY_FRAME_DURATION_MS$ar$edu, COUNTER_TOTAL_FRAME_DURATION_MS$ar$edu};

        public static int forNumber$ar$edu$9c03f9c5_0(int i) {
            switch (i) {
                case 0:
                    return COUNTER_UNKNOWN$ar$edu;
                case 1:
                    return COUNTER_EMPTY$ar$edu;
                case 2:
                    return COUNTER_JANKY_FRAME_COUNT$ar$edu;
                case 3:
                    return COUNTER_TOTAL_FRAME_COUNT$ar$edu;
                case 4:
                    return COUNTER_DROPPED_REPORT_COUNT$ar$edu;
                case 5:
                    return COUNTER_MAX_FRAME_DURATION_MS$ar$edu;
                case 6:
                    return COUNTER_TOTAL_JANKY_FRAME_DURATION_MS$ar$edu;
                case 7:
                    return COUNTER_TOTAL_FRAME_DURATION_MS$ar$edu;
                default:
                    return 0;
            }
        }

        public static int[] values$ar$edu$b952c264_0() {
            return new int[]{COUNTER_UNKNOWN$ar$edu, COUNTER_EMPTY$ar$edu, COUNTER_JANKY_FRAME_COUNT$ar$edu, COUNTER_TOTAL_FRAME_COUNT$ar$edu, COUNTER_DROPPED_REPORT_COUNT$ar$edu, COUNTER_MAX_FRAME_DURATION_MS$ar$edu, COUNTER_TOTAL_JANKY_FRAME_DURATION_MS$ar$edu, COUNTER_TOTAL_FRAME_DURATION_MS$ar$edu};
        }
    }

    static {
        PerfettoTraceConfigurations$JankPerfettoConfigurations perfettoTraceConfigurations$JankPerfettoConfigurations = new PerfettoTraceConfigurations$JankPerfettoConfigurations();
        DEFAULT_INSTANCE = perfettoTraceConfigurations$JankPerfettoConfigurations;
        GeneratedMessageLite.registerDefaultInstance(PerfettoTraceConfigurations$JankPerfettoConfigurations.class, perfettoTraceConfigurations$JankPerfettoConfigurations);
    }

    private PerfettoTraceConfigurations$JankPerfettoConfigurations() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဇ\u0001", new Object[]{"bitField0_", "triggerNameFormatString_", "counter_", Counter.class, "flushingToPerfettoEnabled_"});
            case NEW_MUTABLE_INSTANCE:
                return new PerfettoTraceConfigurations$JankPerfettoConfigurations();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PerfettoTraceConfigurations$JankPerfettoConfigurations.class) {
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
