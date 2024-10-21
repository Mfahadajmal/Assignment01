package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$ServiceHealthProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BatteryMetric$ServiceHealthProto DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int launchCount_;
    public BatteryMetric$HashedString name_;
    public int startServiceCount_;

    static {
        BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto = new BatteryMetric$ServiceHealthProto();
        DEFAULT_INSTANCE = batteryMetric$ServiceHealthProto;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$ServiceHealthProto.class, batteryMetric$ServiceHealthProto);
    }

    private BatteryMetric$ServiceHealthProto() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "startServiceCount_", "launchCount_", "name_"});
            case NEW_MUTABLE_INSTANCE:
                return new BatteryMetric$ServiceHealthProto();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$ServiceHealthProto.class) {
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
