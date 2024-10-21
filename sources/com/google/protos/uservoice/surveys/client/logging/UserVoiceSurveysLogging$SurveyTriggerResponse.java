package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$SurveyTriggerResponse extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$SurveyTriggerResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public UserVoiceSurveysLogging$Payload payload_;
    public UserVoiceSurveysLogging$Session session_;
    public String noAvailableSurveyReason_ = "";
    public Internal.IntList triggerError_ = emptyIntList();
    public String surveyId_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TriggerError {
        public static final int UNKNOWN$ar$edu$5d5b468a_0 = 2;
        public static final int BACKEND_TIMEOUT$ar$edu = 3;
        public static final int FAILED_TO_FETCH_SURVEY$ar$edu = 4;
        public static final int NO_AVAILABLE_SURVEY$ar$edu = 5;
        public static final int TRIGGER_ID_NOT_SET$ar$edu = 6;
        public static final int UNSUPPORTED_CRONET_ENGINE$ar$edu = 7;
        public static final int UNRECOGNIZED$ar$edu$5d5b468a_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$a7d08a62_0 = {UNKNOWN$ar$edu$5d5b468a_0, BACKEND_TIMEOUT$ar$edu, FAILED_TO_FETCH_SURVEY$ar$edu, NO_AVAILABLE_SURVEY$ar$edu, TRIGGER_ID_NOT_SET$ar$edu, UNSUPPORTED_CRONET_ENGINE$ar$edu, UNRECOGNIZED$ar$edu$5d5b468a_0};

        public static int getNumber$ar$edu$5d5b468a_0(int i) {
            if (i != UNRECOGNIZED$ar$edu$5d5b468a_0) {
                return i - 2;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static int[] values$ar$edu$64d59ad7_0() {
            return new int[]{UNKNOWN$ar$edu$5d5b468a_0, BACKEND_TIMEOUT$ar$edu, FAILED_TO_FETCH_SURVEY$ar$edu, NO_AVAILABLE_SURVEY$ar$edu, TRIGGER_ID_NOT_SET$ar$edu, UNSUPPORTED_CRONET_ENGINE$ar$edu, UNRECOGNIZED$ar$edu$5d5b468a_0};
        }
    }

    static {
        UserVoiceSurveysLogging$SurveyTriggerResponse userVoiceSurveysLogging$SurveyTriggerResponse = new UserVoiceSurveysLogging$SurveyTriggerResponse();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$SurveyTriggerResponse;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$SurveyTriggerResponse.class, userVoiceSurveysLogging$SurveyTriggerResponse);
    }

    private UserVoiceSurveysLogging$SurveyTriggerResponse() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003Ȉ\u0004,\u0005Ȉ", new Object[]{"bitField0_", "session_", "payload_", "noAvailableSurveyReason_", "triggerError_", "surveyId_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$SurveyTriggerResponse();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$SurveyTriggerResponse.class) {
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
