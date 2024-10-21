package com.google.drishti.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GraphTemplateProto$TemplateArgument extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final GraphTemplateProto$TemplateArgument DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int paramValueCase_ = 0;
    public Object paramValue_;

    static {
        GraphTemplateProto$TemplateArgument graphTemplateProto$TemplateArgument = new GraphTemplateProto$TemplateArgument();
        DEFAULT_INSTANCE = graphTemplateProto$TemplateArgument;
        GeneratedMessageLite.registerDefaultInstance(GraphTemplateProto$TemplateArgument.class, graphTemplateProto$TemplateArgument);
    }

    private GraphTemplateProto$TemplateArgument() {
        emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001;\u0000\u00023\u0000\u0003<\u0000", new Object[]{"paramValue_", "paramValueCase_", GraphTemplateProto$TemplateDict.class});
            case NEW_MUTABLE_INSTANCE:
                return new GraphTemplateProto$TemplateArgument();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (GraphTemplateProto$TemplateArgument.class) {
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
