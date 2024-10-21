package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$TriggerContext extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$TriggerContext DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public boolean testingMode_;
    public String triggerId_ = "";
    public Internal.ProtobufList language_ = GeneratedMessageLite.emptyProtobufList();

    static {
        Survey$TriggerContext survey$TriggerContext = new Survey$TriggerContext();
        DEFAULT_INSTANCE = survey$TriggerContext;
        GeneratedMessageLite.registerDefaultInstance(Survey$TriggerContext.class, survey$TriggerContext);
    }

    private Survey$TriggerContext() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002Ț\u0003\u0007", new Object[]{"triggerId_", "language_", "testingMode_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$TriggerContext();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (byte[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$TriggerContext.class) {
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
