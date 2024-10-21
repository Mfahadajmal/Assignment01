package google.internal.feedback.v1;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$SurveyExperimentFlags extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$SurveyExperimentFlags DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public boolean sendRequestsToFeedbackOneplatform_;

    static {
        Survey$SurveyExperimentFlags survey$SurveyExperimentFlags = new Survey$SurveyExperimentFlags();
        DEFAULT_INSTANCE = survey$SurveyExperimentFlags;
        GeneratedMessageLite.registerDefaultInstance(Survey$SurveyExperimentFlags.class, survey$SurveyExperimentFlags);
    }

    private Survey$SurveyExperimentFlags() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007", new Object[]{"sendRequestsToFeedbackOneplatform_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$SurveyExperimentFlags();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$SurveyExperimentFlags.class) {
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
