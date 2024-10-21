package com.google.mediapipe.formats.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RectProto$NormalizedRect extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final RectProto$NormalizedRect DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private float height_;
    private byte memoizedIsInitialized = 2;
    private float width_;
    private float xCenter_;
    private float yCenter_;

    static {
        RectProto$NormalizedRect rectProto$NormalizedRect = new RectProto$NormalizedRect();
        DEFAULT_INSTANCE = rectProto$NormalizedRect;
        GeneratedMessageLite.registerDefaultInstance(RectProto$NormalizedRect.class, rectProto$NormalizedRect);
    }

    private RectProto$NormalizedRect() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0004\u0001ᔁ\u0000\u0002ᔁ\u0001\u0003ᔁ\u0002\u0004ᔁ\u0003", new Object[]{"bitField0_", "xCenter_", "yCenter_", "height_", "width_"});
            case NEW_MUTABLE_INSTANCE:
                return new RectProto$NormalizedRect();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (short[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (RectProto$NormalizedRect.class) {
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
