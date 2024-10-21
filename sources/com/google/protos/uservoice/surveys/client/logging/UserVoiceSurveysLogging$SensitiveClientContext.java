package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$SensitiveClientContext extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$SensitiveClientContext DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public SensitiveDeviceInfo deviceInfo_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SensitiveDeviceInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SensitiveDeviceInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public SensitiveMobileInfo mobileInfo_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SensitiveMobileInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final SensitiveMobileInfo DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public int bitField0_;
            public SensitiveChimeraInfo chimeraInfo_;
            public SensitiveTelephonyInfo telephonyInfo_;

            /* compiled from: PG */
            /* loaded from: classes.dex */
            public final class SensitiveChimeraInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
                public static final SensitiveChimeraInfo DEFAULT_INSTANCE;
                private static volatile Parser PARSER;
                public Internal.ProtobufList moduleSetInfo_ = emptyProtobufList();

                /* compiled from: PG */
                /* loaded from: classes.dex */
                public final class SensitiveModuleSetInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
                    public static final SensitiveModuleSetInfo DEFAULT_INSTANCE;
                    private static volatile Parser PARSER;
                    public String moduleSetId_ = "";
                    public String moduleSetVariant_ = "";

                    static {
                        SensitiveModuleSetInfo sensitiveModuleSetInfo = new SensitiveModuleSetInfo();
                        DEFAULT_INSTANCE = sensitiveModuleSetInfo;
                        GeneratedMessageLite.registerDefaultInstance(SensitiveModuleSetInfo.class, sensitiveModuleSetInfo);
                    }

                    private SensitiveModuleSetInfo() {
                    }

                    @Override // com.google.protobuf.GeneratedMessageLite
                    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                        switch (methodToInvoke) {
                            case GET_MEMOIZED_IS_INITIALIZED:
                                return (byte) 1;
                            case SET_MEMOIZED_IS_INITIALIZED:
                                return null;
                            case BUILD_MESSAGE_INFO:
                                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"moduleSetId_", "moduleSetVariant_"});
                            case NEW_MUTABLE_INSTANCE:
                                return new SensitiveModuleSetInfo();
                            case NEW_BUILDER:
                                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                            case GET_DEFAULT_INSTANCE:
                                return DEFAULT_INSTANCE;
                            case GET_PARSER:
                                Parser parser = PARSER;
                                if (parser == null) {
                                    synchronized (SensitiveModuleSetInfo.class) {
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
                    SensitiveChimeraInfo sensitiveChimeraInfo = new SensitiveChimeraInfo();
                    DEFAULT_INSTANCE = sensitiveChimeraInfo;
                    GeneratedMessageLite.registerDefaultInstance(SensitiveChimeraInfo.class, sensitiveChimeraInfo);
                }

                private SensitiveChimeraInfo() {
                }

                @Override // com.google.protobuf.GeneratedMessageLite
                protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                    switch (methodToInvoke) {
                        case GET_MEMOIZED_IS_INITIALIZED:
                            return (byte) 1;
                        case SET_MEMOIZED_IS_INITIALIZED:
                            return null;
                        case BUILD_MESSAGE_INFO:
                            return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"moduleSetInfo_", SensitiveModuleSetInfo.class});
                        case NEW_MUTABLE_INSTANCE:
                            return new SensitiveChimeraInfo();
                        case NEW_BUILDER:
                            return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
                        case GET_DEFAULT_INSTANCE:
                            return DEFAULT_INSTANCE;
                        case GET_PARSER:
                            Parser parser = PARSER;
                            if (parser == null) {
                                synchronized (SensitiveChimeraInfo.class) {
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
            public final class SensitiveTelephonyInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
                public static final SensitiveTelephonyInfo DEFAULT_INSTANCE;
                private static volatile Parser PARSER;
                public String phoneType_ = "";
                public String networkName_ = "";
                public String networkType_ = "";
                public String networkMccCode_ = "";
                public String networkMncCode_ = "";

                static {
                    SensitiveTelephonyInfo sensitiveTelephonyInfo = new SensitiveTelephonyInfo();
                    DEFAULT_INSTANCE = sensitiveTelephonyInfo;
                    GeneratedMessageLite.registerDefaultInstance(SensitiveTelephonyInfo.class, sensitiveTelephonyInfo);
                }

                private SensitiveTelephonyInfo() {
                }

                @Override // com.google.protobuf.GeneratedMessageLite
                protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                    switch (methodToInvoke) {
                        case GET_MEMOIZED_IS_INITIALIZED:
                            return (byte) 1;
                        case SET_MEMOIZED_IS_INITIALIZED:
                            return null;
                        case BUILD_MESSAGE_INFO:
                            return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ", new Object[]{"phoneType_", "networkName_", "networkType_", "networkMccCode_", "networkMncCode_"});
                        case NEW_MUTABLE_INSTANCE:
                            return new SensitiveTelephonyInfo();
                        case NEW_BUILDER:
                            return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (char[]) null, (byte[]) null, (byte[]) null);
                        case GET_DEFAULT_INSTANCE:
                            return DEFAULT_INSTANCE;
                        case GET_PARSER:
                            Parser parser = PARSER;
                            if (parser == null) {
                                synchronized (SensitiveTelephonyInfo.class) {
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
                SensitiveMobileInfo sensitiveMobileInfo = new SensitiveMobileInfo();
                DEFAULT_INSTANCE = sensitiveMobileInfo;
                GeneratedMessageLite.registerDefaultInstance(SensitiveMobileInfo.class, sensitiveMobileInfo);
            }

            private SensitiveMobileInfo() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "telephonyInfo_", "chimeraInfo_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new SensitiveMobileInfo();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (short[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (SensitiveMobileInfo.class) {
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
            SensitiveDeviceInfo sensitiveDeviceInfo = new SensitiveDeviceInfo();
            DEFAULT_INSTANCE = sensitiveDeviceInfo;
            GeneratedMessageLite.registerDefaultInstance(SensitiveDeviceInfo.class, sensitiveDeviceInfo);
        }

        private SensitiveDeviceInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "mobileInfo_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SensitiveDeviceInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (int[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SensitiveDeviceInfo.class) {
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
        UserVoiceSurveysLogging$SensitiveClientContext userVoiceSurveysLogging$SensitiveClientContext = new UserVoiceSurveysLogging$SensitiveClientContext();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$SensitiveClientContext;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$SensitiveClientContext.class, userVoiceSurveysLogging$SensitiveClientContext);
    }

    private UserVoiceSurveysLogging$SensitiveClientContext() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "deviceInfo_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$SensitiveClientContext();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$SensitiveClientContext.class) {
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
