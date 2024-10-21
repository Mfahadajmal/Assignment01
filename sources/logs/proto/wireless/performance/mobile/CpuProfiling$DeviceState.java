package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfiling$DeviceState extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final CpuProfiling$DeviceState DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public boolean bluetoothOn_;
    public boolean charging_;
    public boolean screenOn_;
    public boolean wifiOn_;

    static {
        CpuProfiling$DeviceState cpuProfiling$DeviceState = new CpuProfiling$DeviceState();
        DEFAULT_INSTANCE = cpuProfiling$DeviceState;
        GeneratedMessageLite.registerDefaultInstance(CpuProfiling$DeviceState.class, cpuProfiling$DeviceState);
    }

    private CpuProfiling$DeviceState() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003", new Object[]{"bitField0_", "screenOn_", "charging_", "wifiOn_", "bluetoothOn_"});
            case NEW_MUTABLE_INSTANCE:
                return new CpuProfiling$DeviceState();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CpuProfiling$DeviceState.class) {
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
