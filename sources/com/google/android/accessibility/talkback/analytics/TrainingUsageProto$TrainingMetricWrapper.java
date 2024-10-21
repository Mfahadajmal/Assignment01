package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrainingUsageProto$TrainingMetricWrapper extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TrainingUsageProto$TrainingMetricWrapper DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Internal.ProtobufList pageDuration_ = emptyProtobufList();
    public int trainingCompletedDurationInSecond_;
    public boolean trainingStarted_;
    public int trainingType_;

    static {
        TrainingUsageProto$TrainingMetricWrapper trainingUsageProto$TrainingMetricWrapper = new TrainingUsageProto$TrainingMetricWrapper();
        DEFAULT_INSTANCE = trainingUsageProto$TrainingMetricWrapper;
        GeneratedMessageLite.registerDefaultInstance(TrainingUsageProto$TrainingMetricWrapper.class, trainingUsageProto$TrainingMetricWrapper);
    }

    private TrainingUsageProto$TrainingMetricWrapper() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003\u001b\u0004င\u0002", new Object[]{"bitField0_", "trainingType_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$19, "trainingStarted_", "pageDuration_", TrainingUsageProto$PageDuration.class, "trainingCompletedDurationInSecond_"});
            case NEW_MUTABLE_INSTANCE:
                return new TrainingUsageProto$TrainingMetricWrapper();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (boolean[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TrainingUsageProto$TrainingMetricWrapper.class) {
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
