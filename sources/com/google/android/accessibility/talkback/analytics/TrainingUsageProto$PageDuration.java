package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrainingUsageProto$PageDuration extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TrainingUsageProto$PageDuration DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public boolean completed_;
    public int durationInSecond_;
    public int pageId_;

    static {
        TrainingUsageProto$PageDuration trainingUsageProto$PageDuration = new TrainingUsageProto$PageDuration();
        DEFAULT_INSTANCE = trainingUsageProto$PageDuration;
        GeneratedMessageLite.registerDefaultInstance(TrainingUsageProto$PageDuration.class, trainingUsageProto$PageDuration);
    }

    private TrainingUsageProto$PageDuration() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003ဇ\u0002", new Object[]{"bitField0_", "pageId_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$18, "durationInSecond_", "completed_"});
            case NEW_MUTABLE_INSTANCE:
                return new TrainingUsageProto$PageDuration();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (boolean[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TrainingUsageProto$PageDuration.class) {
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
