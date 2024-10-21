package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackVoiceCommandProto$TalkBackVoiceCommand extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackVoiceCommandProto$TalkBackVoiceCommand DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int numberOfAttempts_;
    private int numberOfEngineError_;
    private int numberOfRecognized_;
    private int numberOfTimeouts_;
    private int numberOfUnrecognized_;
    public Internal.ProtobufList voiceCommandMetrics_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VoiceCommandMetrics extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final VoiceCommandMetrics DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private int bitField0_;
        private int commandCount_;
        private int commandType_;

        static {
            VoiceCommandMetrics voiceCommandMetrics = new VoiceCommandMetrics();
            DEFAULT_INSTANCE = voiceCommandMetrics;
            GeneratedMessageLite.registerDefaultInstance(VoiceCommandMetrics.class, voiceCommandMetrics);
        }

        private VoiceCommandMetrics() {
        }

        public static /* synthetic */ void access$100$ar$edu$9ca0d2fd_0(VoiceCommandMetrics voiceCommandMetrics, int i) {
            if (i != 0) {
                voiceCommandMetrics.commandType_ = i - 1;
                voiceCommandMetrics.bitField0_ |= 1;
                return;
            }
            throw null;
        }

        public static /* synthetic */ void access$300(VoiceCommandMetrics voiceCommandMetrics, int i) {
            voiceCommandMetrics.bitField0_ |= 2;
            voiceCommandMetrics.commandCount_ = i;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "commandType_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.INSTANCE, "commandCount_"});
                case NEW_MUTABLE_INSTANCE:
                    return new VoiceCommandMetrics();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (char[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (VoiceCommandMetrics.class) {
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

    static {
        TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand = new TalkBackVoiceCommandProto$TalkBackVoiceCommand();
        DEFAULT_INSTANCE = talkBackVoiceCommandProto$TalkBackVoiceCommand;
        GeneratedMessageLite.registerDefaultInstance(TalkBackVoiceCommandProto$TalkBackVoiceCommand.class, talkBackVoiceCommandProto$TalkBackVoiceCommand);
    }

    private TalkBackVoiceCommandProto$TalkBackVoiceCommand() {
    }

    public static /* synthetic */ void access$1100(TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand, int i) {
        talkBackVoiceCommandProto$TalkBackVoiceCommand.bitField0_ |= 4;
        talkBackVoiceCommandProto$TalkBackVoiceCommand.numberOfUnrecognized_ = i;
    }

    public static /* synthetic */ void access$1300(TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand, int i) {
        talkBackVoiceCommandProto$TalkBackVoiceCommand.bitField0_ |= 8;
        talkBackVoiceCommandProto$TalkBackVoiceCommand.numberOfRecognized_ = i;
    }

    public static /* synthetic */ void access$1500(TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand, int i) {
        talkBackVoiceCommandProto$TalkBackVoiceCommand.bitField0_ |= 16;
        talkBackVoiceCommandProto$TalkBackVoiceCommand.numberOfEngineError_ = i;
    }

    public static /* synthetic */ void access$700(TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand, int i) {
        talkBackVoiceCommandProto$TalkBackVoiceCommand.bitField0_ |= 1;
        talkBackVoiceCommandProto$TalkBackVoiceCommand.numberOfAttempts_ = i;
    }

    public static /* synthetic */ void access$900(TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand, int i) {
        talkBackVoiceCommandProto$TalkBackVoiceCommand.bitField0_ |= 2;
        talkBackVoiceCommandProto$TalkBackVoiceCommand.numberOfTimeouts_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006\u001b", new Object[]{"bitField0_", "numberOfAttempts_", "numberOfTimeouts_", "numberOfUnrecognized_", "numberOfRecognized_", "numberOfEngineError_", "voiceCommandMetrics_", VoiceCommandMetrics.class});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackVoiceCommandProto$TalkBackVoiceCommand();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (byte[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackVoiceCommandProto$TalkBackVoiceCommand.class) {
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
