package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$DeviceInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$DeviceInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public long availableDiskSizeKb_;
    public int bitField0_;
    public long totalDiskSizeKb_;

    static {
        SystemHealthProto$DeviceInfo systemHealthProto$DeviceInfo = new SystemHealthProto$DeviceInfo();
        DEFAULT_INSTANCE = systemHealthProto$DeviceInfo;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$DeviceInfo.class, systemHealthProto$DeviceInfo);
    }

    private SystemHealthProto$DeviceInfo() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001", new Object[]{"bitField0_", "availableDiskSizeKb_", "totalDiskSizeKb_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$DeviceInfo();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$DeviceInfo.class) {
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
