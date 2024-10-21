package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Service$SurveyTriggerResponse extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Service$SurveyTriggerResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Survey$Session session_;
    public Survey$Payload surveyPayload_;
    public String noAvailableSurveyReason_ = "";
    public Internal.ProtobufList error_ = GeneratedMessageLite.emptyProtobufList();
    public String surveyId_ = "";

    static {
        Service$SurveyTriggerResponse service$SurveyTriggerResponse = new Service$SurveyTriggerResponse();
        DEFAULT_INSTANCE = service$SurveyTriggerResponse;
        GeneratedMessageLite.registerDefaultInstance(Service$SurveyTriggerResponse.class, service$SurveyTriggerResponse);
    }

    private Service$SurveyTriggerResponse() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003Ȉ\u0004Ț\u0005Ȉ", new Object[]{"bitField0_", "session_", "surveyPayload_", "noAvailableSurveyReason_", "error_", "surveyId_"});
            case NEW_MUTABLE_INSTANCE:
                return new Service$SurveyTriggerResponse();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (char[]) null, (byte[]) null);
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
