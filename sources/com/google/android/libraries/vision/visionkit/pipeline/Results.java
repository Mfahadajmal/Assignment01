package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.libraries.nlp.garcon.ondevice.pipeline.ImageCaptioningPipelineOutput;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.ocr.photo.ImageProtos$TextImage;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protos.photos_vision_objectrec.proto2api.ImageTemplate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Results extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final Results DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    private HandTrackingResult handTrackingResult_;
    public ImageCaptioningPipelineOutput imageCaptionOutput_;
    private ImageTemplate imageTemplateResult_;
    private byte memoizedIsInitialized = 2;
    private ImageProtos$TextImage ocrResult_;

    static {
        Results results = new Results();
        DEFAULT_INSTANCE = results;
        GeneratedMessageLite.registerDefaultInstance(Results.class, results);
    }

    private Results() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\b$\u0004\u0000\u0000\u0003\bᐉ\u0007\u0013ᐉ\u0011\u0015ᐉ\u0013$ဉ\u001d", new Object[]{"bitField0_", "ocrResult_", "imageTemplateResult_", "handTrackingResult_", "imageCaptionOutput_"});
            case NEW_MUTABLE_INSTANCE:
                return new Results();
            case NEW_BUILDER:
                return new SchedulerOptions.Builder((byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Results.class) {
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
