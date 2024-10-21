package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Service$SurveyRecordEventRequest extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Service$SurveyRecordEventRequest DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Survey$Event event_;
    public Survey$Session session_;

    static {
        Service$SurveyRecordEventRequest service$SurveyRecordEventRequest = new Service$SurveyRecordEventRequest();
        DEFAULT_INSTANCE = service$SurveyRecordEventRequest;
        GeneratedMessageLite.registerDefaultInstance(Service$SurveyRecordEventRequest.class, service$SurveyRecordEventRequest);
    }

    private Service$SurveyRecordEventRequest() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "event_", "session_"});
            case NEW_MUTABLE_INSTANCE:
                return new Service$SurveyRecordEventRequest();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Service$SurveyRecordEventRequest.class) {
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
