package com.google.ocr.photo;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageProtos$TextImage extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ImageProtos$TextImage DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;
    private ByteString imageData_ = ByteString.EMPTY;

    static {
        ImageProtos$TextImage imageProtos$TextImage = new ImageProtos$TextImage();
        DEFAULT_INSTANCE = imageProtos$TextImage;
        GeneratedMessageLite.registerDefaultInstance(ImageProtos$TextImage.class, imageProtos$TextImage);
    }

    private ImageProtos$TextImage() {
        emptyProtobufList();
        emptyProtobufList();
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᔊ\u0000", new Object[]{"bitField0_", "imageData_"});
            case NEW_MUTABLE_INSTANCE:
                return new ImageProtos$TextImage();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (char[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ImageProtos$TextImage.class) {
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
