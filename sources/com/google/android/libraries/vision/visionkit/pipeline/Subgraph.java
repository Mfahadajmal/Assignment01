package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.libraries.vision.visionkit.OcrOptions;
import com.google.drishti.proto.GraphTemplateProto$TemplateDict;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Subgraph extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Subgraph DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    private OcrOptions ocrOptions_;
    public GraphTemplateProto$TemplateDict templateDict_;
    public String subgraphName_ = "";
    public Internal.ProtobufList inputStreamName_ = GeneratedMessageLite.emptyProtobufList();

    static {
        Subgraph subgraph = new Subgraph();
        DEFAULT_INSTANCE = subgraph;
        GeneratedMessageLite.registerDefaultInstance(Subgraph.class, subgraph);
    }

    private Subgraph() {
        GeneratedMessageLite.emptyProtobufList();
        GeneratedMessageLite.emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001a\u0003ဉ\u0001\u0005ဉ\u0002", new Object[]{"bitField0_", "subgraphName_", "inputStreamName_", "ocrOptions_", "templateDict_"});
            case NEW_MUTABLE_INSTANCE:
                return new Subgraph();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (int[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Subgraph.class) {
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
