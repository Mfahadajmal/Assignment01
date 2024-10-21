package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashRecordingTimeouts extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final CrashRecordingTimeouts DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bgThreadTimeoutMs_;
    private int bitField0_;
    public int mainThreadTimeoutMs_;

    static {
        CrashRecordingTimeouts crashRecordingTimeouts = new CrashRecordingTimeouts();
        DEFAULT_INSTANCE = crashRecordingTimeouts;
        GeneratedMessageLite.registerDefaultInstance(CrashRecordingTimeouts.class, crashRecordingTimeouts);
    }

    private CrashRecordingTimeouts() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"bitField0_", "mainThreadTimeoutMs_", "bgThreadTimeoutMs_"});
            case NEW_MUTABLE_INSTANCE:
                return new CrashRecordingTimeouts();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CrashRecordingTimeouts.class) {
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
