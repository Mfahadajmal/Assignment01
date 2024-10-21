package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$SurveyRecordEventRequest extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$SurveyRecordEventRequest DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public UserVoiceSurveysLogging$Event event_;
    public UserVoiceSurveysLogging$Session session_;

    static {
        UserVoiceSurveysLogging$SurveyRecordEventRequest userVoiceSurveysLogging$SurveyRecordEventRequest = new UserVoiceSurveysLogging$SurveyRecordEventRequest();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$SurveyRecordEventRequest;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$SurveyRecordEventRequest.class, userVoiceSurveysLogging$SurveyRecordEventRequest);
    }

    private UserVoiceSurveysLogging$SurveyRecordEventRequest() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "event_", "session_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$SurveyRecordEventRequest();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$SurveyRecordEventRequest.class) {
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
