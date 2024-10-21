package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$Payload extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$Payload DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public UserVoiceSurveysLogging$Completion completion_;
    public UserVoiceSurveysLogging$DisplaySettings displaySettings_;
    public UserVoiceSurveysLogging$Invitation invitation_;
    public UserVoiceSurveysLogging$PrivacySettings privacySettings_;
    public Internal.ProtobufList question_ = emptyProtobufList();
    public Internal.IntList requiredCapability_ = emptyIntList();

    static {
        UserVoiceSurveysLogging$Payload userVoiceSurveysLogging$Payload = new UserVoiceSurveysLogging$Payload();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$Payload;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$Payload.class, userVoiceSurveysLogging$Payload);
    }

    private UserVoiceSurveysLogging$Payload() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005\u001b\u0007,", new Object[]{"bitField0_", "invitation_", "completion_", "displaySettings_", "privacySettings_", "question_", UserVoiceSurveysLogging$Question.class, "requiredCapability_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$Payload();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$Payload.class) {
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
