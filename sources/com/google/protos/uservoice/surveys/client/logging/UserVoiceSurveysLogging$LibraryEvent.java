package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$LibraryEvent extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$LibraryEvent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int eventCase_ = 0;
    public Object event_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CreateClientCallInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final CreateClientCallInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        static {
            CreateClientCallInfo createClientCallInfo = new CreateClientCallInfo();
            DEFAULT_INSTANCE = createClientCallInfo;
            GeneratedMessageLite.registerDefaultInstance(CreateClientCallInfo.class, createClientCallInfo);
        }

        private CreateClientCallInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case NEW_MUTABLE_INSTANCE:
                    return new CreateClientCallInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (int[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (CreateClientCallInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DismissSurveyCallInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final DismissSurveyCallInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        static {
            DismissSurveyCallInfo dismissSurveyCallInfo = new DismissSurveyCallInfo();
            DEFAULT_INSTANCE = dismissSurveyCallInfo;
            GeneratedMessageLite.registerDefaultInstance(DismissSurveyCallInfo.class, dismissSurveyCallInfo);
        }

        private DismissSurveyCallInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case NEW_MUTABLE_INSTANCE:
                    return new DismissSurveyCallInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (DismissSurveyCallInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LoginStateChangedInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final LoginStateChangedInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        static {
            LoginStateChangedInfo loginStateChangedInfo = new LoginStateChangedInfo();
            DEFAULT_INSTANCE = loginStateChangedInfo;
            GeneratedMessageLite.registerDefaultInstance(LoginStateChangedInfo.class, loginStateChangedInfo);
        }

        private LoginStateChangedInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case NEW_MUTABLE_INSTANCE:
                    return new LoginStateChangedInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (LoginStateChangedInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PresentSurveyCallInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final PresentSurveyCallInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int completionStyle_;
        public int maxPromptWidth_;
        public Internal.IntList presentError_ = emptyIntList();
        public int promptStyle_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class CompletionStyle {
            public static final int COMPLETION_STYLE_UNKNOWN$ar$edu = 2;
            public static final int COMPLETION_STYLE_CARD$ar$edu = 3;
            public static final int COMPLETION_STYLE_TOAST$ar$edu = 4;
            public static final int UNRECOGNIZED$ar$edu$73fbbe3d_0 = 1;
            private static final /* synthetic */ int[] $VALUES$ar$edu$7b3c02be_0 = {COMPLETION_STYLE_UNKNOWN$ar$edu, COMPLETION_STYLE_CARD$ar$edu, COMPLETION_STYLE_TOAST$ar$edu, UNRECOGNIZED$ar$edu$73fbbe3d_0};

            public static int getNumber$ar$edu$73fbbe3d_0(int i) {
                if (i != UNRECOGNIZED$ar$edu$73fbbe3d_0) {
                    return i - 2;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            public static int[] values$ar$edu$d4ef1bd0_0() {
                return new int[]{COMPLETION_STYLE_UNKNOWN$ar$edu, COMPLETION_STYLE_CARD$ar$edu, COMPLETION_STYLE_TOAST$ar$edu, UNRECOGNIZED$ar$edu$73fbbe3d_0};
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class PresentError {
            public static final int UNKNOWN$ar$edu = 2;
            public static final int CLIENT_ACTIVITY_WAS_DESTROYED$ar$edu = 3;
            public static final int CLIENT_ACTIVITY_WAS_FINISHING$ar$edu = 4;
            public static final int CLIENT_ACTIVITY_WAS_NULL$ar$edu = 5;
            public static final int INVALID_AUTH_PARAMS$ar$edu = 12;
            public static final int INVALID_COMPLETION_STYLE$ar$edu = 10;
            public static final int INVALID_PROMPT_STYLE$ar$edu = 11;
            public static final int INVALID_SURVEY_DATA_TYPE$ar$edu = 6;
            public static final int INVALID_SURVEY_PAYLOAD$ar$edu = 7;
            public static final int SURVEY_ALREADY_RUNNING$ar$edu = 8;
            public static final int SURVEY_EXPIRED$ar$edu = 9;
            public static final int SURVEY_NOT_IN_KEY_WINDOW$ar$edu = 13;
            public static final int VIEW_CONTROLLER_NOT_FULL_SIZE$ar$edu = 14;
            public static final int UNSUPPORTED_EMBEDDED_SURVEY_CONTAINER$ar$edu = 15;
            public static final int UNRECOGNIZED$ar$edu$9ec2dfce_0 = 1;
            private static final /* synthetic */ int[] $VALUES$ar$edu$a169340d_0 = {UNKNOWN$ar$edu, CLIENT_ACTIVITY_WAS_DESTROYED$ar$edu, CLIENT_ACTIVITY_WAS_FINISHING$ar$edu, CLIENT_ACTIVITY_WAS_NULL$ar$edu, INVALID_AUTH_PARAMS$ar$edu, INVALID_COMPLETION_STYLE$ar$edu, INVALID_PROMPT_STYLE$ar$edu, INVALID_SURVEY_DATA_TYPE$ar$edu, INVALID_SURVEY_PAYLOAD$ar$edu, SURVEY_ALREADY_RUNNING$ar$edu, SURVEY_EXPIRED$ar$edu, SURVEY_NOT_IN_KEY_WINDOW$ar$edu, VIEW_CONTROLLER_NOT_FULL_SIZE$ar$edu, UNSUPPORTED_EMBEDDED_SURVEY_CONTAINER$ar$edu, UNRECOGNIZED$ar$edu$9ec2dfce_0};

            public static int getNumber$ar$edu$9ec2dfce_0(int i) {
                if (i != UNRECOGNIZED$ar$edu$9ec2dfce_0) {
                    return i - 2;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            public static int[] values$ar$edu$3202c6d4_0() {
                return new int[]{UNKNOWN$ar$edu, CLIENT_ACTIVITY_WAS_DESTROYED$ar$edu, CLIENT_ACTIVITY_WAS_FINISHING$ar$edu, CLIENT_ACTIVITY_WAS_NULL$ar$edu, INVALID_AUTH_PARAMS$ar$edu, INVALID_COMPLETION_STYLE$ar$edu, INVALID_PROMPT_STYLE$ar$edu, INVALID_SURVEY_DATA_TYPE$ar$edu, INVALID_SURVEY_PAYLOAD$ar$edu, SURVEY_ALREADY_RUNNING$ar$edu, SURVEY_EXPIRED$ar$edu, SURVEY_NOT_IN_KEY_WINDOW$ar$edu, VIEW_CONTROLLER_NOT_FULL_SIZE$ar$edu, UNSUPPORTED_EMBEDDED_SURVEY_CONTAINER$ar$edu, UNRECOGNIZED$ar$edu$9ec2dfce_0};
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class PromptStyle {
            public static final int PROMPT_STYLE_UNKNOWN$ar$edu = 2;
            public static final int PROMPT_STYLE_FIRST_CARD_NON_MODAL$ar$edu = 3;
            public static final int PROMPT_STYLE_FIRST_CARD_MODAL$ar$edu = 4;
            public static final int UNRECOGNIZED$ar$edu$c97f4529_0 = 1;
            private static final /* synthetic */ int[] $VALUES$ar$edu$3b45ab73_0 = {PROMPT_STYLE_UNKNOWN$ar$edu, PROMPT_STYLE_FIRST_CARD_NON_MODAL$ar$edu, PROMPT_STYLE_FIRST_CARD_MODAL$ar$edu, UNRECOGNIZED$ar$edu$c97f4529_0};

            public static int getNumber$ar$edu$c97f4529_0(int i) {
                if (i != UNRECOGNIZED$ar$edu$c97f4529_0) {
                    return i - 2;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            public static int[] values$ar$edu$ed0fbb0d_0() {
                return new int[]{PROMPT_STYLE_UNKNOWN$ar$edu, PROMPT_STYLE_FIRST_CARD_NON_MODAL$ar$edu, PROMPT_STYLE_FIRST_CARD_MODAL$ar$edu, UNRECOGNIZED$ar$edu$c97f4529_0};
            }
        }

        static {
            PresentSurveyCallInfo presentSurveyCallInfo = new PresentSurveyCallInfo();
            DEFAULT_INSTANCE = presentSurveyCallInfo;
            GeneratedMessageLite.registerDefaultInstance(PresentSurveyCallInfo.class, presentSurveyCallInfo);
        }

        private PresentSurveyCallInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001င\u0000\u0002,\u0003ဌ\u0001\u0004ဌ\u0002", new Object[]{"bitField0_", "maxPromptWidth_", "presentError_", "completionStyle_", "promptStyle_"});
                case NEW_MUTABLE_INSTANCE:
                    return new PresentSurveyCallInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (PresentSurveyCallInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ReportSurveyDeclinedCallInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final ReportSurveyDeclinedCallInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        static {
            ReportSurveyDeclinedCallInfo reportSurveyDeclinedCallInfo = new ReportSurveyDeclinedCallInfo();
            DEFAULT_INSTANCE = reportSurveyDeclinedCallInfo;
            GeneratedMessageLite.registerDefaultInstance(ReportSurveyDeclinedCallInfo.class, reportSurveyDeclinedCallInfo);
        }

        private ReportSurveyDeclinedCallInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case NEW_MUTABLE_INSTANCE:
                    return new ReportSurveyDeclinedCallInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ReportSurveyDeclinedCallInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RequestSurveyCallInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final RequestSurveyCallInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public boolean enableTestingMode_;
        public boolean nonProd_;
        public String triggerId_ = "";

        static {
            RequestSurveyCallInfo requestSurveyCallInfo = new RequestSurveyCallInfo();
            DEFAULT_INSTANCE = requestSurveyCallInfo;
            GeneratedMessageLite.registerDefaultInstance(RequestSurveyCallInfo.class, requestSurveyCallInfo);
        }

        private RequestSurveyCallInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0004\u0003\u0000\u0000\u0000\u0001Ȉ\u0003\u0007\u0004\u0007", new Object[]{"triggerId_", "enableTestingMode_", "nonProd_"});
                case NEW_MUTABLE_INSTANCE:
                    return new RequestSurveyCallInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (RequestSurveyCallInfo.class) {
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
        UserVoiceSurveysLogging$LibraryEvent userVoiceSurveysLogging$LibraryEvent = new UserVoiceSurveysLogging$LibraryEvent();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$LibraryEvent;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$LibraryEvent.class, userVoiceSurveysLogging$LibraryEvent);
    }

    private UserVoiceSurveysLogging$LibraryEvent() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0001\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000", new Object[]{"event_", "eventCase_", LoginStateChangedInfo.class, CreateClientCallInfo.class, RequestSurveyCallInfo.class, PresentSurveyCallInfo.class, DismissSurveyCallInfo.class, ReportSurveyDeclinedCallInfo.class});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$LibraryEvent();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$LibraryEvent.class) {
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
