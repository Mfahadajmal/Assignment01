package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$TriggerContext extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$TriggerContext DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public boolean testingMode_;
    public String triggerId_ = "";
    public Internal.ProtobufList language_ = GeneratedMessageLite.emptyProtobufList();

    static {
        UserVoiceSurveysLogging$TriggerContext userVoiceSurveysLogging$TriggerContext = new UserVoiceSurveysLogging$TriggerContext();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$TriggerContext;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$TriggerContext.class, userVoiceSurveysLogging$TriggerContext);
    }

    private UserVoiceSurveysLogging$TriggerContext() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002Ț\u0003\u0007", new Object[]{"triggerId_", "language_", "testingMode_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$TriggerContext();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$TriggerContext.class) {
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
