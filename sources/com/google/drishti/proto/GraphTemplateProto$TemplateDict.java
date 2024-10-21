package com.google.drishti.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GraphTemplateProto$TemplateDict extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final GraphTemplateProto$TemplateDict DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList arg_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Parameter extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final Parameter DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String key_ = "";
        public GraphTemplateProto$TemplateArgument value_;

        static {
            Parameter parameter = new Parameter();
            DEFAULT_INSTANCE = parameter;
            GeneratedMessageLite.registerDefaultInstance(Parameter.class, parameter);
        }

        private Parameter() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "key_", "value_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Parameter();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (char[]) null, (char[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (Parameter.class) {
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
        GraphTemplateProto$TemplateDict graphTemplateProto$TemplateDict = new GraphTemplateProto$TemplateDict();
        DEFAULT_INSTANCE = graphTemplateProto$TemplateDict;
        GeneratedMessageLite.registerDefaultInstance(GraphTemplateProto$TemplateDict.class, graphTemplateProto$TemplateDict);
    }

    private GraphTemplateProto$TemplateDict() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"arg_", Parameter.class});
            case NEW_MUTABLE_INSTANCE:
                return new GraphTemplateProto$TemplateDict();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (short[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (GraphTemplateProto$TemplateDict.class) {
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
