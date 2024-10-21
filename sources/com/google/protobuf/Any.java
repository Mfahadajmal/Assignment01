package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Any extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Any DEFAULT_INSTANCE;
    private static volatile Parser PARSER;

    static {
        Any any = new Any();
        DEFAULT_INSTANCE = any;
        GeneratedMessageLite.registerDefaultInstance(Any.class, any);
    }

    private Any() {
        ByteString byteString = ByteString.EMPTY;
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
                return new Any();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (char[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Any.class) {
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
