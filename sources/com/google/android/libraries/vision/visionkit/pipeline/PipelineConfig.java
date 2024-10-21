package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PipelineConfig extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final PipelineConfig DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public AsynchronousApiOptions asynchronousApiOptions_;
    public int bitField0_;
    public boolean enableMobileiqTracing_;
    public int experimentalMaxInflightFrames_;
    public SchedulerOptions schedulerOptions_;
    public Object soLibraryConfiguration_;
    public int soLibraryConfigurationCase_ = 0;
    private byte memoizedIsInitialized = 2;

    static {
        PipelineConfig pipelineConfig = new PipelineConfig();
        DEFAULT_INSTANCE = pipelineConfig;
        GeneratedMessageLite.registerDefaultInstance(PipelineConfig.class, pipelineConfig);
    }

    private PipelineConfig() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0001\u0001\u0001\b\u0006\u0000\u0000\u0001\u0001ᐉ\u0000\u0002ဇ\u0001\u0004ဉ\u0002\u0005:\u0000\u0006:\u0000\bင\u0005", new Object[]{"soLibraryConfiguration_", "soLibraryConfigurationCase_", "bitField0_", "schedulerOptions_", "enableMobileiqTracing_", "asynchronousApiOptions_", "experimentalMaxInflightFrames_"});
            case NEW_MUTABLE_INSTANCE:
                return new PipelineConfig();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PipelineConfig.class) {
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
