package com.google.protos.photos_vision_objectrec.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ROI extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ROI DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;
    private int xMax_;
    private int xMin_;
    private int yMax_;
    private int yMin_;

    static {
        ROI roi = new ROI();
        DEFAULT_INSTANCE = roi;
        GeneratedMessageLite.registerDefaultInstance(ROI.class, roi);
    }

    private ROI() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0004\u0001ᔄ\u0000\u0002ᔄ\u0001\u0003ᔄ\u0002\u0004ᔄ\u0003", new Object[]{"bitField0_", "xMin_", "xMax_", "yMin_", "yMax_"});
            case NEW_MUTABLE_INSTANCE:
                return new ROI();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (char[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ROI.class) {
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
