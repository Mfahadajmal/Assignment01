package google.internal.feedback.v1;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Service$GetSurveyStartupConfigResponse extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Service$GetSurveyStartupConfigResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public Survey$SurveyExperimentFlags surveyExperimentFlags_;

    static {
        Service$GetSurveyStartupConfigResponse service$GetSurveyStartupConfigResponse = new Service$GetSurveyStartupConfigResponse();
        DEFAULT_INSTANCE = service$GetSurveyStartupConfigResponse;
        GeneratedMessageLite.registerDefaultInstance(Service$GetSurveyStartupConfigResponse.class, service$GetSurveyStartupConfigResponse);
    }

    private Service$GetSurveyStartupConfigResponse() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "surveyExperimentFlags_"});
            case NEW_MUTABLE_INSTANCE:
                return new Service$GetSurveyStartupConfigResponse();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Service$GetSurveyStartupConfigResponse.class) {
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
