package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$HashedString extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BatteryMetric$HashedString DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long hash_;
    public String unhashedName_ = "";

    static {
        BatteryMetric$HashedString batteryMetric$HashedString = new BatteryMetric$HashedString();
        DEFAULT_INSTANCE = batteryMetric$HashedString;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$HashedString.class, batteryMetric$HashedString);
    }

    private BatteryMetric$HashedString() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001စ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "hash_", "unhashedName_"});
            case NEW_MUTABLE_INSTANCE:
                return new BatteryMetric$HashedString();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$HashedString.class) {
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
