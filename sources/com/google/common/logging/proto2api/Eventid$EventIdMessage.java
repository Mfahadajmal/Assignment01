package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Eventid$EventIdMessage extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Eventid$EventIdMessage DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;
    private int processId_;
    private int serverIp_;
    private long timeUsec_;

    static {
        Eventid$EventIdMessage eventid$EventIdMessage = new Eventid$EventIdMessage();
        DEFAULT_INSTANCE = eventid$EventIdMessage;
        GeneratedMessageLite.registerDefaultInstance(Eventid$EventIdMessage.class, eventid$EventIdMessage);
    }

    private Eventid$EventIdMessage() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        byte b = 1;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                if (obj == null) {
                    b = 0;
                }
                this.memoizedIsInitialized = b;
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔂ\u0000\u0002ᔆ\u0001\u0003ᔆ\u0002", new Object[]{"bitField0_", "timeUsec_", "serverIp_", "processId_"});
            case NEW_MUTABLE_INSTANCE:
                return new Eventid$EventIdMessage();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Eventid$EventIdMessage.class) {
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
