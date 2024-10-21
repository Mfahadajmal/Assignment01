package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$Completion extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$Completion DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public String completionText_ = "";
    public String followUpText_ = "";
    public String followUpUrl_ = "";
    public Internal.IntList allowedCompletionStyle_ = emptyIntList();

    static {
        UserVoiceSurveysLogging$Completion userVoiceSurveysLogging$Completion = new UserVoiceSurveysLogging$Completion();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$Completion;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$Completion.class, userVoiceSurveysLogging$Completion);
    }

    private UserVoiceSurveysLogging$Completion() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004,", new Object[]{"completionText_", "followUpText_", "followUpUrl_", "allowedCompletionStyle_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$Completion();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (byte[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$Completion.class) {
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
