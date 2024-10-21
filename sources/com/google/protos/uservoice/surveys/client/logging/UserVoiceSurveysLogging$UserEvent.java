package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$UserEvent extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$UserEvent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int eventTrigger_;
    public int eventType_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EventTrigger {
        public static final int CLICK$ar$edu = 2;
        public static final int KEYPRESS$ar$edu = 3;
        public static final int UNRECOGNIZED$ar$edu$5137a177_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$51f97bfe_0 = {CLICK$ar$edu, KEYPRESS$ar$edu, UNRECOGNIZED$ar$edu$5137a177_0};

        public static int getNumber$ar$edu$5137a177_0(int i) {
            if (i != UNRECOGNIZED$ar$edu$5137a177_0) {
                return i - 2;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static int[] values$ar$edu$1d984bd5_0() {
            return new int[]{CLICK$ar$edu, KEYPRESS$ar$edu, UNRECOGNIZED$ar$edu$5137a177_0};
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EventType {
        public static final int SURVEY_DISMISSED$ar$edu = 2;
        public static final int SURVEY_INVITATION_DECLINED$ar$edu = 3;
        public static final int PRIVACY_LINK_CLICKED$ar$edu = 4;
        public static final int TERMS_LINK_CLICKED$ar$edu = 5;
        public static final int ACCOUNT_AND_SYSTEM_INFO_LINK_CLICKED$ar$edu = 6;
        public static final int NEXT_BUTTON_CLICKED$ar$edu = 7;
        public static final int CLOSE_BUTTON_CLICKED$ar$edu = 8;
        public static final int UNRECOGNIZED$ar$edu$62b19512_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$cc2b9b0b_0 = {SURVEY_DISMISSED$ar$edu, SURVEY_INVITATION_DECLINED$ar$edu, PRIVACY_LINK_CLICKED$ar$edu, TERMS_LINK_CLICKED$ar$edu, ACCOUNT_AND_SYSTEM_INFO_LINK_CLICKED$ar$edu, NEXT_BUTTON_CLICKED$ar$edu, CLOSE_BUTTON_CLICKED$ar$edu, UNRECOGNIZED$ar$edu$62b19512_0};

        public static int getNumber$ar$edu$62b19512_0(int i) {
            if (i != UNRECOGNIZED$ar$edu$62b19512_0) {
                return i - 2;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static int[] values$ar$edu$eddd1d0d_0() {
            return new int[]{SURVEY_DISMISSED$ar$edu, SURVEY_INVITATION_DECLINED$ar$edu, PRIVACY_LINK_CLICKED$ar$edu, TERMS_LINK_CLICKED$ar$edu, ACCOUNT_AND_SYSTEM_INFO_LINK_CLICKED$ar$edu, NEXT_BUTTON_CLICKED$ar$edu, CLOSE_BUTTON_CLICKED$ar$edu, UNRECOGNIZED$ar$edu$62b19512_0};
        }
    }

    static {
        UserVoiceSurveysLogging$UserEvent userVoiceSurveysLogging$UserEvent = new UserVoiceSurveysLogging$UserEvent();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$UserEvent;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$UserEvent.class, userVoiceSurveysLogging$UserEvent);
    }

    private UserVoiceSurveysLogging$UserEvent() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\f", new Object[]{"eventTrigger_", "eventType_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$UserEvent();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$UserEvent.class) {
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
