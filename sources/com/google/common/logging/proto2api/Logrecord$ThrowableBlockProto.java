package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Logrecord$ThrowableBlockProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Logrecord$ThrowableBlockProto DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long messageHash_;
    private byte memoizedIsInitialized = 2;
    public String originalClass_ = "";
    public String message_ = "";
    public Internal.ProtobufList stackTraceElement_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StackTraceElement extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final StackTraceElement DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int lineNumber_;
        private byte memoizedIsInitialized = 2;
        public String declaringClass_ = "";
        public String methodName_ = "";
        public String fileName_ = "";

        static {
            StackTraceElement stackTraceElement = new StackTraceElement();
            DEFAULT_INSTANCE = stackTraceElement;
            GeneratedMessageLite.registerDefaultInstance(StackTraceElement.class, stackTraceElement);
        }

        private StackTraceElement() {
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
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0005\b\u0004\u0000\u0000\u0003\u0005ᔈ\u0000\u0006ᔈ\u0001\u0007ဈ\u0002\bᔄ\u0003", new Object[]{"bitField0_", "declaringClass_", "methodName_", "fileName_", "lineNumber_"});
                case NEW_MUTABLE_INSTANCE:
                    return new StackTraceElement();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (short[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (StackTraceElement.class) {
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

    static {
        Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto = new Logrecord$ThrowableBlockProto();
        DEFAULT_INSTANCE = logrecord$ThrowableBlockProto;
        GeneratedMessageLite.registerDefaultInstance(Logrecord$ThrowableBlockProto.class, logrecord$ThrowableBlockProto);
    }

    private Logrecord$ThrowableBlockProto() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0002\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004б", new Object[]{"bitField0_", "originalClass_", "message_", "messageHash_", "stackTraceElement_", StackTraceElement.class});
            case NEW_MUTABLE_INSTANCE:
                return new Logrecord$ThrowableBlockProto();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Logrecord$ThrowableBlockProto.class) {
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
