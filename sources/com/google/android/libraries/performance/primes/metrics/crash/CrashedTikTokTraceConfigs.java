package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashedTikTokTraceConfigs extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final CrashedTikTokTraceConfigs DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public boolean enabled_;
    public int maxCharsPerSpanName_;
    public int maxSpansPerTrace_;
    public int maxTotalSize_;

    static {
        CrashedTikTokTraceConfigs crashedTikTokTraceConfigs = new CrashedTikTokTraceConfigs();
        DEFAULT_INSTANCE = crashedTikTokTraceConfigs;
        GeneratedMessageLite.registerDefaultInstance(CrashedTikTokTraceConfigs.class, crashedTikTokTraceConfigs);
    }

    private CrashedTikTokTraceConfigs() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"bitField0_", "enabled_", "maxCharsPerSpanName_", "maxSpansPerTrace_", "maxTotalSize_"});
            case NEW_MUTABLE_INSTANCE:
                return new CrashedTikTokTraceConfigs();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CrashedTikTokTraceConfigs.class) {
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
