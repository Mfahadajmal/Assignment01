package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$TextSubstitution extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$TextSubstitution DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Object replacementOperation_;
    public int replacementOperationCase_ = 0;
    public String matchText_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AnswerPipe extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final AnswerPipe DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int fromQuestionOrdinal_;

        static {
            AnswerPipe answerPipe = new AnswerPipe();
            DEFAULT_INSTANCE = answerPipe;
            GeneratedMessageLite.registerDefaultInstance(AnswerPipe.class, answerPipe);
        }

        private AnswerPipe() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"fromQuestionOrdinal_"});
                case NEW_MUTABLE_INSTANCE:
                    return new AnswerPipe();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (byte[]) null, (char[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (AnswerPipe.class) {
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
        Survey$TextSubstitution survey$TextSubstitution = new Survey$TextSubstitution();
        DEFAULT_INSTANCE = survey$TextSubstitution;
        GeneratedMessageLite.registerDefaultInstance(Survey$TextSubstitution.class, survey$TextSubstitution);
    }

    private Survey$TextSubstitution() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000", new Object[]{"replacementOperation_", "replacementOperationCase_", "matchText_", AnswerPipe.class});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$TextSubstitution();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$TextSubstitution.class) {
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
