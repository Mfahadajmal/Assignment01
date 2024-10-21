package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$Question extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$Question DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int questionOrdinal_;
    public int questionType_;
    public Object question_;
    public boolean screeningEnabled_;
    public int questionCase_ = 0;
    public String questionText_ = "";
    public String questionHtml_ = "";
    public Internal.ProtobufList textSubstitution_ = emptyProtobufList();

    static {
        UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question = new UserVoiceSurveysLogging$Question();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$Question;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$Question.class, userVoiceSurveysLogging$Question);
    }

    private UserVoiceSurveysLogging$Question() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\n\u0001\u0000\u0001\n\n\u0000\u0001\u0000\u0001\u000b\u0002Ȉ\u0003\f\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000\b\u0007\tȈ\n\u001b", new Object[]{"question_", "questionCase_", "questionOrdinal_", "questionText_", "questionType_", UserVoiceSurveysLogging$SingleSelect.class, UserVoiceSurveysLogging$MultiSelect.class, UserVoiceSurveysLogging$Rating.class, UserVoiceSurveysLogging$OpenText.class, "screeningEnabled_", "questionHtml_", "textSubstitution_", UserVoiceSurveysLogging$TextSubstitution.class});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$Question();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$Question.class) {
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
