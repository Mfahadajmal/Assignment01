package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$BatteryUsageMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BatteryMetric$BatteryUsageMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public BatteryMetric$BatteryStatsDiff batteryStatsDiff_;
    public int bitField0_;
    private byte memoizedIsInitialized = 2;

    static {
        BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric = new BatteryMetric$BatteryUsageMetric();
        DEFAULT_INSTANCE = batteryMetric$BatteryUsageMetric;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$BatteryUsageMetric.class, batteryMetric$BatteryUsageMetric);
    }

    private BatteryMetric$BatteryUsageMetric() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"bitField0_", "batteryStatsDiff_"});
            case NEW_MUTABLE_INSTANCE:
                return new BatteryMetric$BatteryUsageMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$BatteryUsageMetric.class) {
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
