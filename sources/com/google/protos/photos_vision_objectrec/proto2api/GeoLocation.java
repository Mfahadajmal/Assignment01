package com.google.protos.photos_vision_objectrec.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeoLocation extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final GeoLocation DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private double lat_;
    private double lon_;
    private byte memoizedIsInitialized = 2;

    static {
        GeoLocation geoLocation = new GeoLocation();
        DEFAULT_INSTANCE = geoLocation;
        GeneratedMessageLite.registerDefaultInstance(GeoLocation.class, geoLocation);
    }

    private GeoLocation() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔀ\u0000\u0002ᔀ\u0001", new Object[]{"bitField0_", "lat_", "lon_"});
            case NEW_MUTABLE_INSTANCE:
                return new GeoLocation();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (char[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (GeoLocation.class) {
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
