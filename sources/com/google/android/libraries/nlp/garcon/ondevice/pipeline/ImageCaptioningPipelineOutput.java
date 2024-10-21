package com.google.android.libraries.nlp.garcon.ondevice.pipeline;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageCaptioningPipelineOutput extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ImageCaptioningPipelineOutput DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList captions_ = emptyProtobufList();

    static {
        ImageCaptioningPipelineOutput imageCaptioningPipelineOutput = new ImageCaptioningPipelineOutput();
        DEFAULT_INSTANCE = imageCaptioningPipelineOutput;
        GeneratedMessageLite.registerDefaultInstance(ImageCaptioningPipelineOutput.class, imageCaptioningPipelineOutput);
    }

    private ImageCaptioningPipelineOutput() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"captions_", Caption.class});
            case NEW_MUTABLE_INSTANCE:
                return new ImageCaptioningPipelineOutput();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ImageCaptioningPipelineOutput.class) {
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
