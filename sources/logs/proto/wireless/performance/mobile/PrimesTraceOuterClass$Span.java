package logs.proto.wireless.performance.mobile;

import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceOuterClass$Span extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final PrimesTraceOuterClass$Span DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long durationMs_;
    public long hashedName_;
    public long id_;
    public long parentId_;
    public int spanType_;
    public long startTimeMs_;
    public long threadId_;
    private byte memoizedIsInitialized = 2;
    public String constantName_ = "";
    public String name_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SpanType {
        public static final int NONE$ar$edu$f0a1aee3_0 = 1;
        public static final int TRACE$ar$edu = 2;

        @Deprecated
        public static final int DEPRECATED_SCENARIO$ar$edu = 3;
        public static final int METRIC$ar$edu = 4;
        public static final int TIMER$ar$edu = 5;
        public static final int DOMINANT_SPAN$ar$edu = 6;
        private static final /* synthetic */ int[] $VALUES$ar$edu$216d692e_0 = {NONE$ar$edu$f0a1aee3_0, TRACE$ar$edu, DEPRECATED_SCENARIO$ar$edu, METRIC$ar$edu, TIMER$ar$edu, DOMINANT_SPAN$ar$edu};

        public static int forNumber$ar$edu$4e0b883_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return 0;
                                }
                                return DOMINANT_SPAN$ar$edu;
                            }
                            return TIMER$ar$edu;
                        }
                        return METRIC$ar$edu;
                    }
                    return DEPRECATED_SCENARIO$ar$edu;
                }
                return TRACE$ar$edu;
            }
            return NONE$ar$edu$f0a1aee3_0;
        }

        public static int[] values$ar$edu$99de180a_0() {
            return new int[]{NONE$ar$edu$f0a1aee3_0, TRACE$ar$edu, DEPRECATED_SCENARIO$ar$edu, METRIC$ar$edu, TIMER$ar$edu, DOMINANT_SPAN$ar$edu};
        }
    }

    static {
        PrimesTraceOuterClass$Span primesTraceOuterClass$Span = new PrimesTraceOuterClass$Span();
        DEFAULT_INSTANCE = primesTraceOuterClass$Span;
        GeneratedMessageLite.registerDefaultInstance(PrimesTraceOuterClass$Span.class, primesTraceOuterClass$Span);
    }

    private PrimesTraceOuterClass$Span() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        byte b = 1;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                if (obj == null) {
                    b = 0;
                }
                this.memoizedIsInitialized = b;
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002စ\u0003\u0003စ\u0004\u0004ဂ\t\u0005ဂ\n\u0006ဂ\f\u0007᠌\r\bစ\u0001\tဈ\u0002", new Object[]{"bitField0_", "constantName_", "id_", "parentId_", "startTimeMs_", "durationMs_", "threadId_", "spanType_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$9, "hashedName_", "name_"});
            case NEW_MUTABLE_INSTANCE:
                return new PrimesTraceOuterClass$Span();
            case NEW_BUILDER:
                return new SchedulerOptions.Builder((float[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PrimesTraceOuterClass$Span.class) {
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
