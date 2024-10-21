package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$PrivacySettings extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$PrivacySettings DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public boolean piiCollectionEnabled_;
    public boolean productCallbacksEnabled_;

    static {
        UserVoiceSurveysLogging$PrivacySettings userVoiceSurveysLogging$PrivacySettings = new UserVoiceSurveysLogging$PrivacySettings();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$PrivacySettings;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$PrivacySettings.class, userVoiceSurveysLogging$PrivacySettings);
    }

    private UserVoiceSurveysLogging$PrivacySettings() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0007\u0002\u0007", new Object[]{"piiCollectionEnabled_", "productCallbacksEnabled_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$PrivacySettings();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$PrivacySettings.class) {
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
