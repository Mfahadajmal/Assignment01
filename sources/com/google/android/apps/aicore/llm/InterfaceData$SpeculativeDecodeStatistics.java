package com.google.android.apps.aicore.llm;

import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InterfaceData$SpeculativeDecodeStatistics extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final InterfaceData$SpeculativeDecodeStatistics DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public float acceptanceRate_;
    private int bitField0_;
    public Internal.ProtobufList drafterGuessesPerPosition_ = emptyProtobufList();
    public long drafterId_;
    public Duration drafterTime_;

    static {
        InterfaceData$SpeculativeDecodeStatistics interfaceData$SpeculativeDecodeStatistics = new InterfaceData$SpeculativeDecodeStatistics();
        DEFAULT_INSTANCE = interfaceData$SpeculativeDecodeStatistics;
        GeneratedMessageLite.registerDefaultInstance(InterfaceData$SpeculativeDecodeStatistics.class, interfaceData$SpeculativeDecodeStatistics);
    }

    private InterfaceData$SpeculativeDecodeStatistics() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဃ\u0000\u0002\u001b\u0003ဉ\u0001\u0004ခ\u0002", new Object[]{"bitField0_", "drafterId_", "drafterGuessesPerPosition_", InterfaceData$DrafterGuess.class, "drafterTime_", "acceptanceRate_"});
            case NEW_MUTABLE_INSTANCE:
                return new InterfaceData$SpeculativeDecodeStatistics();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (boolean[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (InterfaceData$SpeculativeDecodeStatistics.class) {
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
