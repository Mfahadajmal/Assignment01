package com.google.android.apps.aicore.llm;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InterfaceData$DrafterGuess extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final InterfaceData$DrafterGuess DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public int numCorrect_;
    public int numGuesses_;

    static {
        InterfaceData$DrafterGuess interfaceData$DrafterGuess = new InterfaceData$DrafterGuess();
        DEFAULT_INSTANCE = interfaceData$DrafterGuess;
        GeneratedMessageLite.registerDefaultInstance(InterfaceData$DrafterGuess.class, interfaceData$DrafterGuess);
    }

    private InterfaceData$DrafterGuess() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001", new Object[]{"bitField0_", "numGuesses_", "numCorrect_"});
            case NEW_MUTABLE_INSTANCE:
                return new InterfaceData$DrafterGuess();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (InterfaceData$DrafterGuess.class) {
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
