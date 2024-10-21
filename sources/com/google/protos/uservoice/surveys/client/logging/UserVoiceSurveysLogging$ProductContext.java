package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$ProductContext extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$ProductContext DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String productVersion_ = "";
    public SensitiveContext sensitiveContext_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SensitiveContext extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SensitiveContext DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public Internal.ProtobufList experimentId_ = GeneratedMessageLite.emptyProtobufList();

        static {
            SensitiveContext sensitiveContext = new SensitiveContext();
            DEFAULT_INSTANCE = sensitiveContext;
            GeneratedMessageLite.registerDefaultInstance(SensitiveContext.class, sensitiveContext);
        }

        private SensitiveContext() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"experimentId_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SensitiveContext();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SensitiveContext.class) {
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
        UserVoiceSurveysLogging$ProductContext userVoiceSurveysLogging$ProductContext = new UserVoiceSurveysLogging$ProductContext();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$ProductContext;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$ProductContext.class, userVoiceSurveysLogging$ProductContext);
    }

    private UserVoiceSurveysLogging$ProductContext() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002Ȉ", new Object[]{"bitField0_", "sensitiveContext_", "productVersion_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$ProductContext();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$ProductContext.class) {
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
