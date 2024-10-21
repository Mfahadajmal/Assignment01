package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$MultiSelect extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$MultiSelect DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public UserVoiceSurveysLogging$AnswerChoices answerChoices_;
    public int bitField0_;

    static {
        UserVoiceSurveysLogging$MultiSelect userVoiceSurveysLogging$MultiSelect = new UserVoiceSurveysLogging$MultiSelect();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$MultiSelect;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$MultiSelect.class, userVoiceSurveysLogging$MultiSelect);
    }

    private UserVoiceSurveysLogging$MultiSelect() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "answerChoices_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$MultiSelect();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$MultiSelect.class) {
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
