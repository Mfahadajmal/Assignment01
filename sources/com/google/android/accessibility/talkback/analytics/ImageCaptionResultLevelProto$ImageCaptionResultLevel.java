package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageCaptionResultLevelProto$ImageCaptionResultLevel extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ImageCaptionResultLevelProto$ImageCaptionResultLevel DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int levelHighQualityCount_;
    private int levelLowQualityCount_;
    private int levelMiddleQualityCount_;

    static {
        ImageCaptionResultLevelProto$ImageCaptionResultLevel imageCaptionResultLevelProto$ImageCaptionResultLevel = new ImageCaptionResultLevelProto$ImageCaptionResultLevel();
        DEFAULT_INSTANCE = imageCaptionResultLevelProto$ImageCaptionResultLevel;
        GeneratedMessageLite.registerDefaultInstance(ImageCaptionResultLevelProto$ImageCaptionResultLevel.class, imageCaptionResultLevelProto$ImageCaptionResultLevel);
    }

    private ImageCaptionResultLevelProto$ImageCaptionResultLevel() {
    }

    public static /* synthetic */ void access$100(ImageCaptionResultLevelProto$ImageCaptionResultLevel imageCaptionResultLevelProto$ImageCaptionResultLevel, int i) {
        imageCaptionResultLevelProto$ImageCaptionResultLevel.bitField0_ |= 1;
        imageCaptionResultLevelProto$ImageCaptionResultLevel.levelHighQualityCount_ = i;
    }

    public static /* synthetic */ void access$300(ImageCaptionResultLevelProto$ImageCaptionResultLevel imageCaptionResultLevelProto$ImageCaptionResultLevel, int i) {
        imageCaptionResultLevelProto$ImageCaptionResultLevel.bitField0_ |= 2;
        imageCaptionResultLevelProto$ImageCaptionResultLevel.levelMiddleQualityCount_ = i;
    }

    public static /* synthetic */ void access$500(ImageCaptionResultLevelProto$ImageCaptionResultLevel imageCaptionResultLevelProto$ImageCaptionResultLevel, int i) {
        imageCaptionResultLevelProto$ImageCaptionResultLevel.bitField0_ |= 4;
        imageCaptionResultLevelProto$ImageCaptionResultLevel.levelLowQualityCount_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"bitField0_", "levelHighQualityCount_", "levelMiddleQualityCount_", "levelLowQualityCount_"});
            case NEW_MUTABLE_INSTANCE:
                return new ImageCaptionResultLevelProto$ImageCaptionResultLevel();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (char[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ImageCaptionResultLevelProto$ImageCaptionResultLevel.class) {
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
