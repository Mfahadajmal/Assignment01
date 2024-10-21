package com.google.android.ssb;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SsbProto$SsbState extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SsbProto$SsbState DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int audioState_;
    private int bitField0_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AudioState {
        public static final int IDLE$ar$edu$63119a3c_0 = 1;
        public static final int LISTENING$ar$edu = 2;
        public static final int RECORDING$ar$edu = 3;
        public static final int PROCESSING$ar$edu = 4;
        public static final int PLAYING_TTS$ar$edu = 5;
        private static final /* synthetic */ int[] $VALUES$ar$edu$80a67fbb_0 = {IDLE$ar$edu$63119a3c_0, LISTENING$ar$edu, RECORDING$ar$edu, PROCESSING$ar$edu, PLAYING_TTS$ar$edu};

        public static int forNumber$ar$edu$9af75a9c_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return 0;
                            }
                            return PLAYING_TTS$ar$edu;
                        }
                        return PROCESSING$ar$edu;
                    }
                    return RECORDING$ar$edu;
                }
                return LISTENING$ar$edu;
            }
            return IDLE$ar$edu$63119a3c_0;
        }

        public static int[] values$ar$edu$f2af9dae_0() {
            return new int[]{IDLE$ar$edu$63119a3c_0, LISTENING$ar$edu, RECORDING$ar$edu, PROCESSING$ar$edu, PLAYING_TTS$ar$edu};
        }
    }

    static {
        SsbProto$SsbState ssbProto$SsbState = new SsbProto$SsbState();
        DEFAULT_INSTANCE = ssbProto$SsbState;
        GeneratedMessageLite.registerDefaultInstance(SsbProto$SsbState.class, ssbProto$SsbState);
    }

    private SsbProto$SsbState() {
        emptyIntList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0000\u0004᠌\u0004", new Object[]{"bitField0_", "audioState_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$5});
            case NEW_MUTABLE_INSTANCE:
                return new SsbProto$SsbState();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SsbProto$SsbState.class) {
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
