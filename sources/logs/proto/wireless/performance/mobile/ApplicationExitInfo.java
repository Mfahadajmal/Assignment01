package logs.proto.wireless.performance.mobile;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitInfo extends GeneratedMessageLite<ApplicationExitInfo, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
    public static final ApplicationExitInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public AnrDiagnostic anrExitDiagnostic_;
    public int bitField0_;
    public int importance_;
    public boolean lowMemoryKillSupported_;
    public String processName_ = "";
    public long pssKb_;
    public int reason_;
    public long rssKb_;
    public int status_;
    public long timestampMillis_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Importance {
        public static final int IMPORTANCE_UNSET$ar$edu = 1;
        public static final int FOREGROUND$ar$edu = 2;
        public static final int FOREGROUND_SERVICE$ar$edu = 3;
        public static final int TOP_SLEEPING$ar$edu = 4;
        public static final int VISIBLE$ar$edu = 5;
        public static final int PERCEPTIBLE$ar$edu = 6;
        public static final int CANT_SAVE_STATE$ar$edu = 7;
        public static final int SERVICE$ar$edu = 8;
        public static final int CACHED$ar$edu = 9;
        public static final int GONE$ar$edu = 10;
        private static final /* synthetic */ int[] $VALUES$ar$edu$afe948a6_0 = {IMPORTANCE_UNSET$ar$edu, FOREGROUND$ar$edu, FOREGROUND_SERVICE$ar$edu, TOP_SLEEPING$ar$edu, VISIBLE$ar$edu, PERCEPTIBLE$ar$edu, CANT_SAVE_STATE$ar$edu, SERVICE$ar$edu, CACHED$ar$edu, GONE$ar$edu};

        public static int forNumber$ar$edu$24911479_0(int i) {
            switch (i) {
                case 0:
                    return IMPORTANCE_UNSET$ar$edu;
                case 1:
                    return FOREGROUND$ar$edu;
                case 2:
                    return FOREGROUND_SERVICE$ar$edu;
                case 3:
                    return TOP_SLEEPING$ar$edu;
                case 4:
                    return VISIBLE$ar$edu;
                case 5:
                    return PERCEPTIBLE$ar$edu;
                case 6:
                    return CANT_SAVE_STATE$ar$edu;
                case 7:
                    return SERVICE$ar$edu;
                case 8:
                    return CACHED$ar$edu;
                case 9:
                    return GONE$ar$edu;
                default:
                    return 0;
            }
        }

        public static int[] values$ar$edu$4d38a4f7_0() {
            return new int[]{IMPORTANCE_UNSET$ar$edu, FOREGROUND$ar$edu, FOREGROUND_SERVICE$ar$edu, TOP_SLEEPING$ar$edu, VISIBLE$ar$edu, PERCEPTIBLE$ar$edu, CANT_SAVE_STATE$ar$edu, SERVICE$ar$edu, CACHED$ar$edu, GONE$ar$edu};
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Reason {
        public static final int REASON_UNSET$ar$edu = 1;
        public static final int ANR$ar$edu = 7;
        public static final int CRASH$ar$edu = 5;
        public static final int CRASH_NATIVE$ar$edu = 6;
        public static final int DEPENDENCY_DIED$ar$edu = 13;
        public static final int EXCESSIVE_RESOURCE_USAGE$ar$edu = 10;
        public static final int EXIT_SELF$ar$edu = 2;
        public static final int FREEZER$ar$edu = 100;
        public static final int INITIALIZATION_FAILURE$ar$edu = 8;
        public static final int LOW_MEMORY$ar$edu = 4;
        public static final int PERMISSION_CHANGE$ar$edu = 9;
        public static final int SIGNALED$ar$edu = 3;
        public static final int USER_REQUESTED$ar$edu = 11;
        public static final int USER_STOPPED$ar$edu = 12;
        public static final int OTHER$ar$edu = 14;
        public static final int UNKNOWN$ar$edu$4e197d28_0 = 15;
        private static final /* synthetic */ int[] $VALUES$ar$edu$a06e340e_0 = {REASON_UNSET$ar$edu, ANR$ar$edu, CRASH$ar$edu, CRASH_NATIVE$ar$edu, DEPENDENCY_DIED$ar$edu, EXCESSIVE_RESOURCE_USAGE$ar$edu, EXIT_SELF$ar$edu, FREEZER$ar$edu, INITIALIZATION_FAILURE$ar$edu, LOW_MEMORY$ar$edu, PERMISSION_CHANGE$ar$edu, SIGNALED$ar$edu, USER_REQUESTED$ar$edu, USER_STOPPED$ar$edu, OTHER$ar$edu, UNKNOWN$ar$edu$4e197d28_0};

        public static int forNumber$ar$edu$f66dba11_0(int i) {
            if (i != 99) {
                switch (i) {
                    case 0:
                        return REASON_UNSET$ar$edu;
                    case 1:
                        return EXIT_SELF$ar$edu;
                    case 2:
                        return SIGNALED$ar$edu;
                    case 3:
                        return LOW_MEMORY$ar$edu;
                    case 4:
                        return CRASH$ar$edu;
                    case 5:
                        return CRASH_NATIVE$ar$edu;
                    case 6:
                        return ANR$ar$edu;
                    case 7:
                        return INITIALIZATION_FAILURE$ar$edu;
                    case 8:
                        return PERMISSION_CHANGE$ar$edu;
                    case 9:
                        return EXCESSIVE_RESOURCE_USAGE$ar$edu;
                    case 10:
                        return USER_REQUESTED$ar$edu;
                    case 11:
                        return USER_STOPPED$ar$edu;
                    case 12:
                        return DEPENDENCY_DIED$ar$edu;
                    case 13:
                        return OTHER$ar$edu;
                    case 14:
                        return UNKNOWN$ar$edu$4e197d28_0;
                    default:
                        return 0;
                }
            }
            return FREEZER$ar$edu;
        }

        public static int[] values$ar$edu$47e809bb_0() {
            return new int[]{REASON_UNSET$ar$edu, ANR$ar$edu, CRASH$ar$edu, CRASH_NATIVE$ar$edu, DEPENDENCY_DIED$ar$edu, EXCESSIVE_RESOURCE_USAGE$ar$edu, EXIT_SELF$ar$edu, FREEZER$ar$edu, INITIALIZATION_FAILURE$ar$edu, LOW_MEMORY$ar$edu, PERMISSION_CHANGE$ar$edu, SIGNALED$ar$edu, USER_REQUESTED$ar$edu, USER_STOPPED$ar$edu, OTHER$ar$edu, UNKNOWN$ar$edu$4e197d28_0};
        }
    }

    static {
        ApplicationExitInfo applicationExitInfo = new ApplicationExitInfo();
        DEFAULT_INSTANCE = applicationExitInfo;
        GeneratedMessageLite.registerDefaultInstance(ApplicationExitInfo.class, applicationExitInfo);
    }

    private ApplicationExitInfo() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003င\u0002\u0004᠌\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဇ\u0007\tဉ\b", new Object[]{"bitField0_", "processName_", "reason_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$18, "status_", "importance_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$17, "timestampMillis_", "pssKb_", "rssKb_", "lowMemoryKillSupported_", "anrExitDiagnostic_"});
            case NEW_MUTABLE_INSTANCE:
                return new ApplicationExitInfo();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ApplicationExitInfo.class) {
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
