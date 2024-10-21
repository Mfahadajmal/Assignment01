package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$HistogramBucket extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$HistogramBucket DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int count_;
    public int max_;
    public int min_;

    static {
        SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket = new SystemHealthProto$HistogramBucket();
        DEFAULT_INSTANCE = systemHealthProto$HistogramBucket;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$HistogramBucket.class, systemHealthProto$HistogramBucket);
    }

    private SystemHealthProto$HistogramBucket() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"bitField0_", "count_", "min_", "max_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$HistogramBucket();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$HistogramBucket.class) {
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
