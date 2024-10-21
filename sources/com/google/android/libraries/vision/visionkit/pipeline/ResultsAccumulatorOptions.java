package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ResultsAccumulatorOptions extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ResultsAccumulatorOptions DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int mode_ = 1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Mode {
        public static final int UNSPECIFIED$ar$edu = 1;
        public static final int PERIODIC$ar$edu = 2;
        public static final int FLUSH_IMMEDIATELY$ar$edu = 3;
        public static final int SYNCHRONIZED$ar$edu = 4;
        public static final int EXPERIMENTAL_SYNCHRONIZED$ar$edu = 5;
        private static final /* synthetic */ int[] $VALUES$ar$edu$ab03917f_0 = {UNSPECIFIED$ar$edu, PERIODIC$ar$edu, FLUSH_IMMEDIATELY$ar$edu, SYNCHRONIZED$ar$edu, EXPERIMENTAL_SYNCHRONIZED$ar$edu};

        public static int forNumber$ar$edu$e0b8549f_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return 0;
                            }
                            return EXPERIMENTAL_SYNCHRONIZED$ar$edu;
                        }
                        return SYNCHRONIZED$ar$edu;
                    }
                    return FLUSH_IMMEDIATELY$ar$edu;
                }
                return PERIODIC$ar$edu;
            }
            return UNSPECIFIED$ar$edu;
        }

        public static int[] values$ar$edu$d91fdbdd_0() {
            return new int[]{UNSPECIFIED$ar$edu, PERIODIC$ar$edu, FLUSH_IMMEDIATELY$ar$edu, SYNCHRONIZED$ar$edu, EXPERIMENTAL_SYNCHRONIZED$ar$edu};
        }
    }

    static {
        ResultsAccumulatorOptions resultsAccumulatorOptions = new ResultsAccumulatorOptions();
        DEFAULT_INSTANCE = resultsAccumulatorOptions;
        GeneratedMessageLite.registerDefaultInstance(ResultsAccumulatorOptions.class, resultsAccumulatorOptions);
    }

    private ResultsAccumulatorOptions() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"bitField0_", "mode_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$4});
            case NEW_MUTABLE_INSTANCE:
                return new ResultsAccumulatorOptions();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ResultsAccumulatorOptions.class) {
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
