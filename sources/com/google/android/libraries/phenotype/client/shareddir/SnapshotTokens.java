package com.google.android.libraries.phenotype.client.shareddir;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SnapshotTokens extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SnapshotTokens DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public long configurationVersion_;
    public String snapshotToken_ = "";
    public ByteString experimentToken_ = ByteString.EMPTY;
    public String serverToken_ = "";

    static {
        SnapshotTokens snapshotTokens = new SnapshotTokens();
        DEFAULT_INSTANCE = snapshotTokens;
        GeneratedMessageLite.registerDefaultInstance(SnapshotTokens.class, snapshotTokens);
    }

    private SnapshotTokens() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ဂ\u0003", new Object[]{"bitField0_", "snapshotToken_", "experimentToken_", "serverToken_", "configurationVersion_"});
            case NEW_MUTABLE_INSTANCE:
                return new SnapshotTokens();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SnapshotTokens.class) {
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
