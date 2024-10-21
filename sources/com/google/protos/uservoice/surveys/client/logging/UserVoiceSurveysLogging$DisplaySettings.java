package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$DisplaySettings extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$DisplaySettings DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.IntList allowedPromptStyle_ = emptyIntList();
    public int bitField0_;
    public PromptDelay promptDelay_;
    public int surveyLogo_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PromptDelay extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final PromptDelay DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public Duration maxDelay_;
        public Duration minDelay_;

        static {
            PromptDelay promptDelay = new PromptDelay();
            DEFAULT_INSTANCE = promptDelay;
            GeneratedMessageLite.registerDefaultInstance(PromptDelay.class, promptDelay);
        }

        private PromptDelay() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "minDelay_", "maxDelay_"});
                case NEW_MUTABLE_INSTANCE:
                    return new PromptDelay();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((boolean[][]) null, (byte[]) null, (char[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (PromptDelay.class) {
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
        UserVoiceSurveysLogging$DisplaySettings userVoiceSurveysLogging$DisplaySettings = new UserVoiceSurveysLogging$DisplaySettings();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$DisplaySettings;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$DisplaySettings.class, userVoiceSurveysLogging$DisplaySettings);
    }

    private UserVoiceSurveysLogging$DisplaySettings() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0001\u0000\u0001ဉ\u0000\u0003\f\u0004,", new Object[]{"bitField0_", "promptDelay_", "surveyLogo_", "allowedPromptStyle_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$DisplaySettings();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][]) null, (byte[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$DisplaySettings.class) {
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
