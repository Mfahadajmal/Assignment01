package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashLoopMonitorFlags extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final CrashLoopMonitorFlags DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public int detectionThreshold_;
    public boolean enabled_;
    public float logInitSampleRate_;
    public int overflowThreshold_;
    public int timeoutMs_;

    static {
        CrashLoopMonitorFlags crashLoopMonitorFlags = new CrashLoopMonitorFlags();
        DEFAULT_INSTANCE = crashLoopMonitorFlags;
        GeneratedMessageLite.registerDefaultInstance(CrashLoopMonitorFlags.class, crashLoopMonitorFlags);
    }

    private CrashLoopMonitorFlags() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဇ\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005ခ\u0004", new Object[]{"bitField0_", "enabled_", "detectionThreshold_", "overflowThreshold_", "timeoutMs_", "logInitSampleRate_"});
            case NEW_MUTABLE_INSTANCE:
                return new CrashLoopMonitorFlags();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CrashLoopMonitorFlags.class) {
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
