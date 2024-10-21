package com.google.protos.research.socrates;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Visual$VisualAnnotation extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Visual$VisualAnnotation DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList uiComponent_;

    static {
        Visual$VisualAnnotation visual$VisualAnnotation = new Visual$VisualAnnotation();
        DEFAULT_INSTANCE = visual$VisualAnnotation;
        GeneratedMessageLite.registerDefaultInstance(Visual$VisualAnnotation.class, visual$VisualAnnotation);
    }

    private Visual$VisualAnnotation() {
        emptyProtobufList();
        emptyProtobufList();
        emptyProtobufList();
        this.uiComponent_ = emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0004\u0004\u0001\u0000\u0001\u0000\u0004\u001b", new Object[]{"uiComponent_", Visual$UIComponent.class});
            case NEW_MUTABLE_INSTANCE:
                return new Visual$VisualAnnotation();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (char[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Visual$VisualAnnotation.class) {
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
