package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Logrecord$LogRecordProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Logrecord$LogRecordProto DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private Eventid$EventIdMessage eventId_;
    private int level_;
    private byte memoizedIsInitialized = 2;
    private String threadName_ = "";
    private Logrecord$ThrowableProto thrown_;

    static {
        Logrecord$LogRecordProto logrecord$LogRecordProto = new Logrecord$LogRecordProto();
        DEFAULT_INSTANCE = logrecord$LogRecordProto;
        GeneratedMessageLite.registerDefaultInstance(Logrecord$LogRecordProto.class, logrecord$LogRecordProto);
    }

    private Logrecord$LogRecordProto() {
        GeneratedMessageLite.emptyProtobufList();
        GeneratedMessageLite.emptyProtobufList();
        emptyProtobufList();
        emptyProtobufList();
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\b\u0004\u0000\u0000\u0004\u0001ᔉ\u0000\u0002ᔈ\u0001\u0003ᔄ\u0002\bᐉ\n", new Object[]{"bitField0_", "eventId_", "threadName_", "level_", "thrown_"});
            case NEW_MUTABLE_INSTANCE:
                return new Logrecord$LogRecordProto();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Logrecord$LogRecordProto.class) {
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
