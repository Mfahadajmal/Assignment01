package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MagnificationUsedProto$MagnificationUsed extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final MagnificationUsedProto$MagnificationUsed DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int fullScreenMagnificationCount_;
    private int windowMagnificationCount_;

    static {
        MagnificationUsedProto$MagnificationUsed magnificationUsedProto$MagnificationUsed = new MagnificationUsedProto$MagnificationUsed();
        DEFAULT_INSTANCE = magnificationUsedProto$MagnificationUsed;
        GeneratedMessageLite.registerDefaultInstance(MagnificationUsedProto$MagnificationUsed.class, magnificationUsedProto$MagnificationUsed);
    }

    private MagnificationUsedProto$MagnificationUsed() {
    }

    public static /* synthetic */ void access$100(MagnificationUsedProto$MagnificationUsed magnificationUsedProto$MagnificationUsed, int i) {
        magnificationUsedProto$MagnificationUsed.bitField0_ |= 1;
        magnificationUsedProto$MagnificationUsed.fullScreenMagnificationCount_ = i;
    }

    public static /* synthetic */ void access$300(MagnificationUsedProto$MagnificationUsed magnificationUsedProto$MagnificationUsed, int i) {
        magnificationUsedProto$MagnificationUsed.bitField0_ |= 2;
        magnificationUsedProto$MagnificationUsed.windowMagnificationCount_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"bitField0_", "fullScreenMagnificationCount_", "windowMagnificationCount_"});
            case NEW_MUTABLE_INSTANCE:
                return new MagnificationUsedProto$MagnificationUsed();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (byte[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MagnificationUsedProto$MagnificationUsed.class) {
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
