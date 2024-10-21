package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$AnswerChoices extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$AnswerChoices DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList answerChoice_ = emptyProtobufList();

    static {
        UserVoiceSurveysLogging$AnswerChoices userVoiceSurveysLogging$AnswerChoices = new UserVoiceSurveysLogging$AnswerChoices();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$AnswerChoices;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$AnswerChoices.class, userVoiceSurveysLogging$AnswerChoices);
    }

    private UserVoiceSurveysLogging$AnswerChoices() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"answerChoice_", UserVoiceSurveysLogging$AnswerChoice.class});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$AnswerChoices();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (char[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$AnswerChoices.class) {
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
