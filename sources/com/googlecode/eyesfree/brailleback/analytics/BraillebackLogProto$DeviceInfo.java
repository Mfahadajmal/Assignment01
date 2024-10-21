package com.googlecode.eyesfree.brailleback.analytics;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BraillebackLogProto$DeviceInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BraillebackLogProto$DeviceInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String displayDriver_ = "";
    public String displayName_ = "";

    static {
        BraillebackLogProto$DeviceInfo braillebackLogProto$DeviceInfo = new BraillebackLogProto$DeviceInfo();
        DEFAULT_INSTANCE = braillebackLogProto$DeviceInfo;
        GeneratedMessageLite.registerDefaultInstance(BraillebackLogProto$DeviceInfo.class, braillebackLogProto$DeviceInfo);
    }

    private BraillebackLogProto$DeviceInfo() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "displayDriver_", "displayName_"});
            case NEW_MUTABLE_INSTANCE:
                return new BraillebackLogProto$DeviceInfo();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BraillebackLogProto$DeviceInfo.class) {
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
