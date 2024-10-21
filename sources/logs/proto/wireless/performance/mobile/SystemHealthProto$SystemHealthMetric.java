package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$SystemHealthMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$SystemHealthMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public SystemHealthProto$AccountableComponent accountableComponent_;
    public ApplicationExitMetric applicationExitMetric_;
    public SystemHealthProto$ApplicationInfo applicationInfo_;
    public BatteryMetric$BatteryUsageMetric batteryUsageMetric_;
    public int bitField0_;
    private SystemHealthProto$ClientErrorLoggingMetric clientErrorLoggingMetrics_;
    public CpuProfiling$CpuProfilingMetric cpuProfilingMetric_;
    public SystemHealthProto$CrashMetric crashMetric_;
    public SystemHealthProto$DebugLogs debugLogs_;
    public SystemHealthProto$DeviceInfo deviceInfo_;
    public long hashedCustomEventName_;
    public Internal.ProtobufList interactionContext_;
    public SystemHealthProto$JankMetric jankMetric_;
    public MemoryMetric$MemoryUsageMetric memoryUsageMetric_;
    public ExtensionMetric$MetricExtension metricExtension_;
    public NetworkMetric$NetworkUsageMetric networkUsageMetric_;
    public SystemHealthProto$PackageMetric packageMetric_;
    public SystemHealthProto$PrimesStats primesStats_;
    public PrimesTraceOuterClass$PrimesTrace primesTrace_;
    public SystemHealthProto$SamplingParameters samplingParameters_;
    public SystemHealthProto$TimerMetric timerMetric_;
    private byte memoizedIsInitialized = 2;
    public String customEventName_ = "";
    public String constantEventName_ = "";
    public Internal.ProtobufList traceMetric_ = emptyProtobufList();

    static {
        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = new SystemHealthProto$SystemHealthMetric();
        DEFAULT_INSTANCE = systemHealthProto$SystemHealthMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$SystemHealthMetric.class, systemHealthProto$SystemHealthMetric);
    }

    private SystemHealthProto$SystemHealthMetric() {
        emptyProtobufList();
        this.interactionContext_ = emptyProtobufList();
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0017\u0000\u0001\u0001%\u0017\u0000\u0002\t\u0001ᐉ\u0003\u0002စ\u0000\u0003ဈ\u0001\u0004ဉ\u0004\u0005ဉ\u0015\u0006ᐉ\u0005\u0007ᐉ\u0006\bᐉ\u0016\tဉ\u0007\nᐉ\b\fဉ\n\u000eᐉ\u0018\u0010ᐉ\u000b\u0011ဈ\u0002\u0015ဉ\u0019\u0017ဉ\u0017\u001bဉ\u000e\u001dᐉ\u0012\u001eဉ\u0013\u001fဉ\u0014 ဉ\u0010\"\u001b%Л", new Object[]{"bitField0_", "memoryUsageMetric_", "hashedCustomEventName_", "customEventName_", "timerMetric_", "applicationInfo_", "networkUsageMetric_", "crashMetric_", "primesStats_", "packageMetric_", "batteryUsageMetric_", "jankMetric_", "metricExtension_", "primesTrace_", "constantEventName_", "accountableComponent_", "deviceInfo_", "cpuProfilingMetric_", "clientErrorLoggingMetrics_", "samplingParameters_", "debugLogs_", "applicationExitMetric_", "interactionContext_", InteractionContext.class, "traceMetric_", SystemHealthProto$TraceMetric.class});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$SystemHealthMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$SystemHealthMetric.class) {
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
