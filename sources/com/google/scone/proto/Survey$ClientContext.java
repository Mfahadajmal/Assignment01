package com.google.scone.proto;

import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$ClientContext extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$ClientContext DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public DeviceInfo deviceInfo_;
    public LibraryInfo libraryInfo_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DeviceInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final DeviceInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public BrowserInfo browserInfo_;
        public MobileInfo mobileInfo_;
        public Duration timezoneOffset_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class BrowserInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final BrowserInfo DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public String userAgent_ = "";
            public String initialUrl_ = "";

            static {
                BrowserInfo browserInfo = new BrowserInfo();
                DEFAULT_INSTANCE = browserInfo;
                GeneratedMessageLite.registerDefaultInstance(BrowserInfo.class, browserInfo);
            }

            private BrowserInfo() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"userAgent_", "initialUrl_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new BrowserInfo();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (char[]) null, (byte[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (BrowserInfo.class) {
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
        public final class MobileInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final MobileInfo DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public int osType_;
            public String deviceModel_ = "";
            public String deviceBrand_ = "";
            public String osVersion_ = "";
            public String appName_ = "";
            public String appId_ = "";
            public String appVersion_ = "";
            public String gmsCoreVersion_ = "";

            /* compiled from: PG */
            /* loaded from: classes.dex */
            public final class OsType {
                public static final int OS_TYPE_UNKNOWN$ar$edu$7c36702f_0 = 2;
                public static final int OS_TYPE_ANDROID$ar$edu$7c36702f_0 = 3;
                public static final int OS_TYPE_IOS$ar$edu$7c36702f_0 = 4;
                public static final int UNRECOGNIZED$ar$edu$7c36702f_0 = 1;
                private static final /* synthetic */ int[] $VALUES$ar$edu$2a0872ff_0 = {OS_TYPE_UNKNOWN$ar$edu$7c36702f_0, OS_TYPE_ANDROID$ar$edu$7c36702f_0, OS_TYPE_IOS$ar$edu$7c36702f_0, UNRECOGNIZED$ar$edu$7c36702f_0};

                public static int forNumber$ar$edu$c540fb10_0(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                return 0;
                            }
                            return OS_TYPE_IOS$ar$edu$7c36702f_0;
                        }
                        return OS_TYPE_ANDROID$ar$edu$7c36702f_0;
                    }
                    return OS_TYPE_UNKNOWN$ar$edu$7c36702f_0;
                }

                public static int getNumber$ar$edu$7c36702f_0(int i) {
                    if (i != UNRECOGNIZED$ar$edu$7c36702f_0) {
                        return i - 2;
                    }
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }

                public static /* synthetic */ String toStringGeneratedcf6bed038c46f567(int i) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return "null";
                                }
                                return "OS_TYPE_IOS";
                            }
                            return "OS_TYPE_ANDROID";
                        }
                        return "OS_TYPE_UNKNOWN";
                    }
                    return "UNRECOGNIZED";
                }

                public static int[] values$ar$edu$73b538ec_0() {
                    return new int[]{OS_TYPE_UNKNOWN$ar$edu$7c36702f_0, OS_TYPE_ANDROID$ar$edu$7c36702f_0, OS_TYPE_IOS$ar$edu$7c36702f_0, UNRECOGNIZED$ar$edu$7c36702f_0};
                }
            }

            static {
                MobileInfo mobileInfo = new MobileInfo();
                DEFAULT_INSTANCE = mobileInfo;
                GeneratedMessageLite.registerDefaultInstance(MobileInfo.class, mobileInfo);
            }

            private MobileInfo() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\f\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ȉ\bȈ", new Object[]{"deviceModel_", "deviceBrand_", "osType_", "osVersion_", "appName_", "appId_", "appVersion_", "gmsCoreVersion_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new MobileInfo();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (short[]) null, (byte[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (MobileInfo.class) {
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
            DeviceInfo deviceInfo = new DeviceInfo();
            DEFAULT_INSTANCE = deviceInfo;
            GeneratedMessageLite.registerDefaultInstance(DeviceInfo.class, deviceInfo);
        }

        private DeviceInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "browserInfo_", "mobileInfo_", "timezoneOffset_"});
                case NEW_MUTABLE_INSTANCE:
                    return new DeviceInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (short[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (DeviceInfo.class) {
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
    public final class LibraryInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final LibraryInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int libraryVersionInt_;
        public int platform_;
        public String libraryVersion_ = "";
        public Internal.IntList supportedCapability_ = emptyIntList();

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Platform {
            public static final int PLATFORM_UNKNOWN$ar$edu$2fe3ac50_0 = 2;
            public static final int PLATFORM_WEB$ar$edu$2fe3ac50_0 = 3;
            public static final int PLATFORM_ANDROID$ar$edu$2fe3ac50_0 = 4;
            public static final int PLATFORM_IOS$ar$edu$2fe3ac50_0 = 5;
            public static final int UNRECOGNIZED$ar$edu$2fe3ac50_0 = 1;
            private static final /* synthetic */ int[] $VALUES$ar$edu$cf685d94_0 = {PLATFORM_UNKNOWN$ar$edu$2fe3ac50_0, PLATFORM_WEB$ar$edu$2fe3ac50_0, PLATFORM_ANDROID$ar$edu$2fe3ac50_0, PLATFORM_IOS$ar$edu$2fe3ac50_0, UNRECOGNIZED$ar$edu$2fe3ac50_0};

            public static int forNumber$ar$edu$7920202b_0(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return 0;
                            }
                            return PLATFORM_IOS$ar$edu$2fe3ac50_0;
                        }
                        return PLATFORM_ANDROID$ar$edu$2fe3ac50_0;
                    }
                    return PLATFORM_WEB$ar$edu$2fe3ac50_0;
                }
                return PLATFORM_UNKNOWN$ar$edu$2fe3ac50_0;
            }

            public static int getNumber$ar$edu$2fe3ac50_0(int i) {
                if (i != UNRECOGNIZED$ar$edu$2fe3ac50_0) {
                    return i - 2;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            public static /* synthetic */ String toStringGeneratedfd6efeaa6957c3cd(int i) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return "null";
                                }
                                return "PLATFORM_IOS";
                            }
                            return "PLATFORM_ANDROID";
                        }
                        return "PLATFORM_WEB";
                    }
                    return "PLATFORM_UNKNOWN";
                }
                return "UNRECOGNIZED";
            }

            public static int[] values$ar$edu$2a6bde76_0() {
                return new int[]{PLATFORM_UNKNOWN$ar$edu$2fe3ac50_0, PLATFORM_WEB$ar$edu$2fe3ac50_0, PLATFORM_ANDROID$ar$edu$2fe3ac50_0, PLATFORM_IOS$ar$edu$2fe3ac50_0, UNRECOGNIZED$ar$edu$2fe3ac50_0};
            }
        }

        static {
            LibraryInfo libraryInfo = new LibraryInfo();
            DEFAULT_INSTANCE = libraryInfo;
            GeneratedMessageLite.registerDefaultInstance(LibraryInfo.class, libraryInfo);
        }

        private LibraryInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001\f\u0002Ȉ\u0003,\u0004\u0004", new Object[]{"platform_", "libraryVersion_", "supportedCapability_", "libraryVersionInt_"});
                case NEW_MUTABLE_INSTANCE:
                    return new LibraryInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (short[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (LibraryInfo.class) {
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
        Survey$ClientContext survey$ClientContext = new Survey$ClientContext();
        DEFAULT_INSTANCE = survey$ClientContext;
        GeneratedMessageLite.registerDefaultInstance(Survey$ClientContext.class, survey$ClientContext);
    }

    private Survey$ClientContext() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "deviceInfo_", "libraryInfo_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$ClientContext();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$ClientContext.class) {
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
