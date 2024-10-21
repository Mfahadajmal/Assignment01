package com.google.android.libraries.phenotype.client.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Configurations extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Configurations DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long configurationVersion_;
    public boolean isDelta_;
    public String snapshotToken_ = "";
    public ByteString experimentToken_ = ByteString.EMPTY;
    public String serverToken_ = "";
    public Internal.ProtobufList flag_ = emptyProtobufList();
    public Internal.ProtobufList deleteFlag_ = GeneratedMessageLite.emptyProtobufList();

    static {
        Configurations configurations = new Configurations();
        DEFAULT_INSTANCE = configurations;
        GeneratedMessageLite.registerDefaultInstance(Configurations.class, configurations);
    }

    private Configurations() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\t\u0007\u0000\u0002\u0000\u0001ဈ\u0002\u0002ဈ\u0000\u0003ည\u0001\u0004\u001b\u0005\u001a\bဇ\u0003\tဂ\u0004", new Object[]{"bitField0_", "serverToken_", "snapshotToken_", "experimentToken_", "flag_", Flag.class, "deleteFlag_", "isDelta_", "configurationVersion_"});
            case NEW_MUTABLE_INSTANCE:
                return new Configurations();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Configurations.class) {
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
