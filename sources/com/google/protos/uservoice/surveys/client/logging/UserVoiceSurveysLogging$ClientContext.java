package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$ClientContext extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$ClientContext DEFAULT_INSTANCE;
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
        public MobileInfo mobileInfo_;
        public Duration timezoneOffset_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class MobileInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final MobileInfo DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public int osType_;
            public String deviceModel_ = "";
            public String osVersion_ = "";
            public String appName_ = "";
            public String appId_ = "";
            public String appVersion_ = "";

            /* compiled from: PG */
            /* loaded from: classes.dex */
            public final class OsType {
                public static final int OS_TYPE_UNKNOWN$ar$edu = 2;
                public static final int OS_TYPE_ANDROID$ar$edu = 3;
                public static final int OS_TYPE_IOS$ar$edu = 4;
                public static final int UNRECOGNIZED$ar$edu$dd957849_0 = 1;
                private static final /* synthetic */ int[] $VALUES$ar$edu$6bcbc2cd_0 = {OS_TYPE_UNKNOWN$ar$edu, OS_TYPE_ANDROID$ar$edu, OS_TYPE_IOS$ar$edu, UNRECOGNIZED$ar$edu$dd957849_0};

                public static int getNumber$ar$edu$dd957849_0(int i) {
                    if (i != UNRECOGNIZED$ar$edu$dd957849_0) {
                        return i - 2;
                    }
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }

                public static int[] values$ar$edu$1794a1bd_0() {
                    return new int[]{OS_TYPE_UNKNOWN$ar$edu, OS_TYPE_ANDROID$ar$edu, OS_TYPE_IOS$ar$edu, UNRECOGNIZED$ar$edu$dd957849_0};
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
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0007\u0006\u0000\u0000\u0000\u0001Ȉ\u0003\f\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ȉ", new Object[]{"deviceModel_", "osType_", "osVersion_", "appName_", "appId_", "appVersion_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new MobileInfo();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (byte[]) null, (char[]) null, (byte[]) null);
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
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "mobileInfo_", "timezoneOffset_"});
                case NEW_MUTABLE_INSTANCE:
                    return new DeviceInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (byte[]) null, (char[]) null, (byte[]) null);
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
            public static final int PLATFORM_UNKNOWN$ar$edu = 2;
            public static final int PLATFORM_WEB$ar$edu = 3;
            public static final int PLATFORM_ANDROID$ar$edu = 4;
            public static final int PLATFORM_IOS$ar$edu = 5;
            public static final int UNRECOGNIZED$ar$edu$6adb469f_0 = 1;
            private static final /* synthetic */ int[] $VALUES$ar$edu$71794b10_0 = {PLATFORM_UNKNOWN$ar$edu, PLATFORM_WEB$ar$edu, PLATFORM_ANDROID$ar$edu, PLATFORM_IOS$ar$edu, UNRECOGNIZED$ar$edu$6adb469f_0};

            public static int getNumber$ar$edu$6adb469f_0(int i) {
                if (i != UNRECOGNIZED$ar$edu$6adb469f_0) {
                    return i - 2;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            public static int[] values$ar$edu$aaff5b2f_0() {
                return new int[]{PLATFORM_UNKNOWN$ar$edu, PLATFORM_WEB$ar$edu, PLATFORM_ANDROID$ar$edu, PLATFORM_IOS$ar$edu, UNRECOGNIZED$ar$edu$6adb469f_0};
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
                    return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (byte[]) null, (char[]) null, (byte[]) null);
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
        UserVoiceSurveysLogging$ClientContext userVoiceSurveysLogging$ClientContext = new UserVoiceSurveysLogging$ClientContext();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$ClientContext;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$ClientContext.class, userVoiceSurveysLogging$ClientContext);
    }

    private UserVoiceSurveysLogging$ClientContext() {
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
                return new UserVoiceSurveysLogging$ClientContext();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (byte[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$ClientContext.class) {
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
