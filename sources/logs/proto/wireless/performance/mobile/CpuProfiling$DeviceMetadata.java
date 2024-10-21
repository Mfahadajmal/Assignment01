package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfiling$DeviceMetadata extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final CpuProfiling$DeviceMetadata DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public CpuProfiling$DeviceState afterState_;
    public float batteryDropPercent_;
    public CpuProfiling$DeviceState beforeState_;
    public int bitField0_;

    static {
        CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata = new CpuProfiling$DeviceMetadata();
        DEFAULT_INSTANCE = cpuProfiling$DeviceMetadata;
        GeneratedMessageLite.registerDefaultInstance(CpuProfiling$DeviceMetadata.class, cpuProfiling$DeviceMetadata);
    }

    private CpuProfiling$DeviceMetadata() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ခ\u0002", new Object[]{"bitField0_", "beforeState_", "afterState_", "batteryDropPercent_"});
            case NEW_MUTABLE_INSTANCE:
                return new CpuProfiling$DeviceMetadata();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CpuProfiling$DeviceMetadata.class) {
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