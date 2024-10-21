package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public boolean hasTurnedOffTalkback_;
    public int recoveryType_;

    static {
        TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery = new TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery();
        DEFAULT_INSTANCE = talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery;
        GeneratedMessageLite.registerDefaultInstance(TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery.class, talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery);
    }

    private TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002᠌\u0001", new Object[]{"bitField0_", "hasTurnedOffTalkback_", "recoveryType_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$2});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (byte[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery.class) {
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
