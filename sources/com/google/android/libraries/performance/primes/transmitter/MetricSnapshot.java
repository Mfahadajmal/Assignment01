package com.google.android.libraries.performance.primes.transmitter;

import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricSnapshot extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final MetricSnapshot DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    private byte memoizedIsInitialized = 2;
    public SystemHealthProto$SystemHealthMetric systemHealthMetric_;

    static {
        MetricSnapshot metricSnapshot = new MetricSnapshot();
        DEFAULT_INSTANCE = metricSnapshot;
        GeneratedMessageLite.registerDefaultInstance(MetricSnapshot.class, metricSnapshot);
    }

    private MetricSnapshot() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"bitField0_", "systemHealthMetric_"});
            case NEW_MUTABLE_INSTANCE:
                return new MetricSnapshot();
            case NEW_BUILDER:
                return new SchedulerOptions.Builder(null, null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MetricSnapshot.class) {
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
