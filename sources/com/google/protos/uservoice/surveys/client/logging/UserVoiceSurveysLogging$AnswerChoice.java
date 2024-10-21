package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$AnswerChoice extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$AnswerChoice DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int answerOrdinal_;
    public int answerType_;
    public boolean screenIn_;
    public String text_ = "";

    static {
        UserVoiceSurveysLogging$AnswerChoice userVoiceSurveysLogging$AnswerChoice = new UserVoiceSurveysLogging$AnswerChoice();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$AnswerChoice;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$AnswerChoice.class, userVoiceSurveysLogging$AnswerChoice);
    }

    private UserVoiceSurveysLogging$AnswerChoice() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0002\u000b\u0003Ȉ\u0004\u0007", new Object[]{"answerType_", "answerOrdinal_", "text_", "screenIn_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$AnswerChoice();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (char[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$AnswerChoice.class) {
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
