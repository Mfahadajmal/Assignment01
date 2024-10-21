package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Logrecord$ThrowableProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Logrecord$ThrowableProto DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Object kind_;
    public Logrecord$ThrowableBlockProto outermost_;
    public int kindCase_ = 0;
    private byte memoizedIsInitialized = 2;
    public Internal.ProtobufList causes_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ThrowableGraph extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final ThrowableGraph DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private byte memoizedIsInitialized = 2;
        public Internal.ProtobufList nodes_ = emptyProtobufList();

        static {
            ThrowableGraph throwableGraph = new ThrowableGraph();
            DEFAULT_INSTANCE = throwableGraph;
            GeneratedMessageLite.registerDefaultInstance(ThrowableGraph.class, throwableGraph);
        }

        private ThrowableGraph() {
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
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"nodes_", ThrowableNode.class});
                case NEW_MUTABLE_INSTANCE:
                    return new ThrowableGraph();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (byte[]) null, (char[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ThrowableGraph.class) {
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

        public final void ensureNodesIsMutable() {
            Internal.ProtobufList protobufList = this.nodes_;
            if (!protobufList.isModifiable()) {
                this.nodes_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ThrowableNode extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final ThrowableNode DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int cause_;
        private byte memoizedIsInitialized = 2;
        public Internal.IntList suppressed_ = emptyIntList();
        public Logrecord$ThrowableBlockProto throwableInfo_;

        static {
            ThrowableNode throwableNode = new ThrowableNode();
            DEFAULT_INSTANCE = throwableNode;
            GeneratedMessageLite.registerDefaultInstance(ThrowableNode.class, throwableNode);
        }

        private ThrowableNode() {
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
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0001\u0001ᐉ\u0000\u0002င\u0001\u0003'", new Object[]{"bitField0_", "throwableInfo_", "cause_", "suppressed_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ThrowableNode();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ThrowableNode.class) {
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
        Logrecord$ThrowableProto logrecord$ThrowableProto = new Logrecord$ThrowableProto();
        DEFAULT_INSTANCE = logrecord$ThrowableProto;
        GeneratedMessageLite.registerDefaultInstance(Logrecord$ThrowableProto.class, logrecord$ThrowableProto);
    }

    private Logrecord$ThrowableProto() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0001\u0001\u0001\u0004\u0003\u0000\u0001\u0003\u0001ᔉ\u0000\u0002Л\u0004м\u0000", new Object[]{"kind_", "kindCase_", "bitField0_", "outermost_", "causes_", Logrecord$ThrowableBlockProto.class, ThrowableGraph.class});
            case NEW_MUTABLE_INSTANCE:
                return new Logrecord$ThrowableProto();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (short[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Logrecord$ThrowableProto.class) {
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

    public final void ensureCausesIsMutable() {
        Internal.ProtobufList protobufList = this.causes_;
        if (!protobufList.isModifiable()) {
            this.causes_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }
}
