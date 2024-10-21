package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$Payload extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$Payload DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Survey$Completion completion_;
    public Survey$DisplaySettings displaySettings_;
    public Survey$Invitation invitation_;
    public Survey$LegalSettings legalSettings_;
    public Survey$PrivacySettings privacySettings_;
    public Internal.ProtobufList question_ = emptyProtobufList();
    public Internal.IntList requiredCapability_ = emptyIntList();

    static {
        Survey$Payload survey$Payload = new Survey$Payload();
        DEFAULT_INSTANCE = survey$Payload;
        GeneratedMessageLite.registerDefaultInstance(Survey$Payload.class, survey$Payload);
    }

    private Survey$Payload() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0001\u0001\t\u0007\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005\u001b\u0007,\tဉ\u0004", new Object[]{"bitField0_", "invitation_", "completion_", "displaySettings_", "privacySettings_", "question_", Survey$Question.class, "requiredCapability_", "legalSettings_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$Payload();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$Payload.class) {
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
