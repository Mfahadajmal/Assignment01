package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$ProcessHealthProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BatteryMetric$ProcessHealthProto DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public long anrCount_;
    public int bitField0_;
    public long crashesCount_;
    public long foregroundMs_;
    public BatteryMetric$HashedString name_;
    public long startsCount_;
    public long systemTimeMs_;
    public long userTimeMs_;

    static {
        BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto = new BatteryMetric$ProcessHealthProto();
        DEFAULT_INSTANCE = batteryMetric$ProcessHealthProto;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$ProcessHealthProto.class, batteryMetric$ProcessHealthProto);
    }

    private BatteryMetric$ProcessHealthProto() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဉ\u0006", new Object[]{"bitField0_", "userTimeMs_", "systemTimeMs_", "startsCount_", "crashesCount_", "anrCount_", "foregroundMs_", "name_"});
            case NEW_MUTABLE_INSTANCE:
                return new BatteryMetric$ProcessHealthProto();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[][]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$ProcessHealthProto.class) {
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
