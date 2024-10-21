package logs.proto.wireless.performance.mobile;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfiling$CpuProfilingMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final CpuProfiling$CpuProfilingMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public CpuProfiling$DeviceMetadata deviceMetadata_;
    public int sampleBufferSize_;
    public int sampleDurationActual_;
    public int sampleDurationScheduled_;
    public int sampleFrequency_;
    public double samplesPerEpoch_;
    public ByteString traceBlob_ = ByteString.EMPTY;

    static {
        CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric = new CpuProfiling$CpuProfilingMetric();
        DEFAULT_INSTANCE = cpuProfiling$CpuProfilingMetric;
        GeneratedMessageLite.registerDefaultInstance(CpuProfiling$CpuProfilingMetric.class, cpuProfiling$CpuProfilingMetric);
    }

    private CpuProfiling$CpuProfilingMetric() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ည\u0000\u0002ဉ\u0001\u0003က\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006", new Object[]{"bitField0_", "traceBlob_", "deviceMetadata_", "samplesPerEpoch_", "sampleDurationScheduled_", "sampleDurationActual_", "sampleFrequency_", "sampleBufferSize_"});
            case NEW_MUTABLE_INSTANCE:
                return new CpuProfiling$CpuProfilingMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CpuProfiling$CpuProfilingMetric.class) {
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
