package com.google.scone.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$Session extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$Session DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public String sessionId_ = "";
    public ByteString sessionToken_ = ByteString.EMPTY;

    static {
        Survey$Session survey$Session = new Survey$Session();
        DEFAULT_INSTANCE = survey$Session;
        GeneratedMessageLite.registerDefaultInstance(Survey$Session.class, survey$Session);
    }

    private Survey$Session() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\n", new Object[]{"sessionId_", "sessionToken_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$Session();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (byte[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$Session.class) {
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
