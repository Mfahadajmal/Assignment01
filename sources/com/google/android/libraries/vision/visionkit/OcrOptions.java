package com.google.android.libraries.vision.visionkit;

import com.google.ocr.OcrEngine$OcrEngineRuntimeOptions;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OcrOptions extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final OcrOptions DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private OcrEngine$OcrEngineRuntimeOptions googleocrRuntimeOptions_;

    static {
        OcrOptions ocrOptions = new OcrOptions();
        DEFAULT_INSTANCE = ocrOptions;
        GeneratedMessageLite.registerDefaultInstance(OcrOptions.class, ocrOptions);
    }

    private OcrOptions() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0017\u0017\u0001\u0000\u0000\u0000\u0017ဉ\u0003", new Object[]{"bitField0_", "googleocrRuntimeOptions_"});
            case NEW_MUTABLE_INSTANCE:
                return new OcrOptions();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (int[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (OcrOptions.class) {
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
