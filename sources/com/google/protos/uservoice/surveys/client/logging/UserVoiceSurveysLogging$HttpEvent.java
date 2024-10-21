package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$HttpEvent extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$HttpEvent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Object request_;
    public Object response_;
    public int requestCase_ = 0;
    public int responseCase_ = 0;

    static {
        UserVoiceSurveysLogging$HttpEvent userVoiceSurveysLogging$HttpEvent = new UserVoiceSurveysLogging$HttpEvent();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$HttpEvent;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$HttpEvent.class, userVoiceSurveysLogging$HttpEvent);
    }

    private UserVoiceSurveysLogging$HttpEvent() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0002\u0000\u0002\u0005\u0004\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0001\u0005<\u0001", new Object[]{"request_", "requestCase_", "response_", "responseCase_", UserVoiceSurveysLogging$SurveyTriggerRequest.class, UserVoiceSurveysLogging$SurveyRecordEventRequest.class, UserVoiceSurveysLogging$SurveyTriggerResponse.class, UserVoiceSurveysLogging$SurveyRecordEventResponse.class});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$HttpEvent();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$HttpEvent.class) {
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
