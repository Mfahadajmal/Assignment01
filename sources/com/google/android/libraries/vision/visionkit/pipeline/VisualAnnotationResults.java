package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import com.google.protos.research.socrates.Visual$VisualAnnotation;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VisualAnnotationResults extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final VisualAnnotationResults DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public static final GeneratedMessageLite.GeneratedExtension ext;
    private int bitField0_;
    public Visual$VisualAnnotation visualAnnotation_;

    static {
        VisualAnnotationResults visualAnnotationResults = new VisualAnnotationResults();
        DEFAULT_INSTANCE = visualAnnotationResults;
        GeneratedMessageLite.registerDefaultInstance(VisualAnnotationResults.class, visualAnnotationResults);
        ext = GeneratedMessageLite.newSingularGeneratedExtension(Results.DEFAULT_INSTANCE, visualAnnotationResults, visualAnnotationResults, null, 242663235, WireFormat.FieldType.MESSAGE, VisualAnnotationResults.class);
    }

    private VisualAnnotationResults() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "visualAnnotation_"});
            case NEW_MUTABLE_INSTANCE:
                return new VisualAnnotationResults();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (VisualAnnotationResults.class) {
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
