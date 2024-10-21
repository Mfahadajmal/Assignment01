package google.internal.feedback.v1;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Service$SurveyTriggerResponse extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Service$SurveyTriggerResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER;

    static {
        Service$SurveyTriggerResponse service$SurveyTriggerResponse = new Service$SurveyTriggerResponse();
        DEFAULT_INSTANCE = service$SurveyTriggerResponse;
        GeneratedMessageLite.registerDefaultInstance(Service$SurveyTriggerResponse.class, service$SurveyTriggerResponse);
    }

    private Service$SurveyTriggerResponse() {
        GeneratedMessageLite.emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
            case NEW_MUTABLE_INSTANCE:
                return new Service$SurveyTriggerResponse();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Service$SurveyTriggerResponse.class) {
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
