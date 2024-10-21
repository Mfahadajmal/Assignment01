package com.google.ocr;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OcrEngine$OcrEngineRuntimeOptions extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final OcrEngine$OcrEngineRuntimeOptions DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private PageLayoutMutator$PageLayoutMutatorContextOptions contextOptions_;

    static {
        OcrEngine$OcrEngineRuntimeOptions ocrEngine$OcrEngineRuntimeOptions = new OcrEngine$OcrEngineRuntimeOptions();
        DEFAULT_INSTANCE = ocrEngine$OcrEngineRuntimeOptions;
        GeneratedMessageLite.registerDefaultInstance(OcrEngine$OcrEngineRuntimeOptions.class, ocrEngine$OcrEngineRuntimeOptions);
    }

    private OcrEngine$OcrEngineRuntimeOptions() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "contextOptions_"});
            case NEW_MUTABLE_INSTANCE:
                return new OcrEngine$OcrEngineRuntimeOptions();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (char[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (OcrEngine$OcrEngineRuntimeOptions.class) {
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
