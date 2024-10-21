package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$Timer extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BatteryMetric$Timer DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int count_;
    public long durationMs_;
    public BatteryMetric$HashedString name_;

    static {
        BatteryMetric$Timer batteryMetric$Timer = new BatteryMetric$Timer();
        DEFAULT_INSTANCE = batteryMetric$Timer;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$Timer.class, batteryMetric$Timer);
    }

    private BatteryMetric$Timer() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "count_", "durationMs_", "name_"});
            case NEW_MUTABLE_INSTANCE:
                return new BatteryMetric$Timer();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$Timer.class) {
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
