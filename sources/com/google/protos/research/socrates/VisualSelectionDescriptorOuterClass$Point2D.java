package com.google.protos.research.socrates;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VisualSelectionDescriptorOuterClass$Point2D extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final VisualSelectionDescriptorOuterClass$Point2D DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public float x_;
    public float y_;

    static {
        VisualSelectionDescriptorOuterClass$Point2D visualSelectionDescriptorOuterClass$Point2D = new VisualSelectionDescriptorOuterClass$Point2D();
        DEFAULT_INSTANCE = visualSelectionDescriptorOuterClass$Point2D;
        GeneratedMessageLite.registerDefaultInstance(VisualSelectionDescriptorOuterClass$Point2D.class, visualSelectionDescriptorOuterClass$Point2D);
    }

    private VisualSelectionDescriptorOuterClass$Point2D() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0001\u0002\u0001", new Object[]{"x_", "y_"});
            case NEW_MUTABLE_INSTANCE:
                return new VisualSelectionDescriptorOuterClass$Point2D();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (VisualSelectionDescriptorOuterClass$Point2D.class) {
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
