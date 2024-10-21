package logs.proto.wireless.performance.mobile;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitReasons extends GeneratedMessageLite<ApplicationExitReasons, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
    public static final ApplicationExitReasons DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.IntList exitReason_ = emptyIntList();

    static {
        ApplicationExitReasons applicationExitReasons = new ApplicationExitReasons();
        DEFAULT_INSTANCE = applicationExitReasons;
        GeneratedMessageLite.registerDefaultInstance(ApplicationExitReasons.class, applicationExitReasons);
    }

    private ApplicationExitReasons() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001ࠞ", new Object[]{"exitReason_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$18});
            case NEW_MUTABLE_INSTANCE:
                return new ApplicationExitReasons();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ApplicationExitReasons.class) {
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
