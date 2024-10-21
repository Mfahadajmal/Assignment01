package google.internal.feedback.v1;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Service$GetSurveyStartupConfigRequest extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Service$GetSurveyStartupConfigRequest DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public String apiKey_ = "";
    public int platform_;

    static {
        Service$GetSurveyStartupConfigRequest service$GetSurveyStartupConfigRequest = new Service$GetSurveyStartupConfigRequest();
        DEFAULT_INSTANCE = service$GetSurveyStartupConfigRequest;
        GeneratedMessageLite.registerDefaultInstance(Service$GetSurveyStartupConfigRequest.class, service$GetSurveyStartupConfigRequest);
    }

    private Service$GetSurveyStartupConfigRequest() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002Ȉ\u0003\f", new Object[]{"apiKey_", "platform_"});
            case NEW_MUTABLE_INSTANCE:
                return new Service$GetSurveyStartupConfigRequest();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Service$GetSurveyStartupConfigRequest.class) {
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
