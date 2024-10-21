package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ApplicationExitMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList applicationExitInfo_ = emptyProtobufList();
    public int bitField0_;
    public ApplicationExitReasons reportableReasons_;
    public int totalExits_;

    static {
        ApplicationExitMetric applicationExitMetric = new ApplicationExitMetric();
        DEFAULT_INSTANCE = applicationExitMetric;
        GeneratedMessageLite.registerDefaultInstance(ApplicationExitMetric.class, applicationExitMetric);
    }

    private ApplicationExitMetric() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000\u0003င\u0001", new Object[]{"bitField0_", "applicationExitInfo_", ApplicationExitInfo.class, "reportableReasons_", "totalExits_"});
            case NEW_MUTABLE_INSTANCE:
                return new ApplicationExitMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ApplicationExitMetric.class) {
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
