package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$CrashedTikTokTraceInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$CrashedTikTokTraceInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Internal.ProtobufList spanName_ = GeneratedMessageLite.emptyProtobufList();
    public TruncationInfo truncationInfo_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TruncationInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final TruncationInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int spansDroppedBySpanLimit_;
        public int spansDroppedByTotalLimit_;
        public Internal.IntList truncatedNamesIndices_ = emptyIntList();
        public Internal.IntList truncatedNamesDroppedCharsCount_ = emptyIntList();

        static {
            TruncationInfo truncationInfo = new TruncationInfo();
            DEFAULT_INSTANCE = truncationInfo;
            GeneratedMessageLite.registerDefaultInstance(TruncationInfo.class, truncationInfo);
        }

        private TruncationInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001င\u0000\u0002င\u0001\u0003'\u0004'", new Object[]{"bitField0_", "spansDroppedBySpanLimit_", "spansDroppedByTotalLimit_", "truncatedNamesIndices_", "truncatedNamesDroppedCharsCount_"});
                case NEW_MUTABLE_INSTANCE:
                    return new TruncationInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (TruncationInfo.class) {
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

    static {
        SystemHealthProto$CrashedTikTokTraceInfo systemHealthProto$CrashedTikTokTraceInfo = new SystemHealthProto$CrashedTikTokTraceInfo();
        DEFAULT_INSTANCE = systemHealthProto$CrashedTikTokTraceInfo;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$CrashedTikTokTraceInfo.class, systemHealthProto$CrashedTikTokTraceInfo);
    }

    private SystemHealthProto$CrashedTikTokTraceInfo() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001a\u0002ဉ\u0000", new Object[]{"bitField0_", "spanName_", "truncationInfo_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$CrashedTikTokTraceInfo();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$CrashedTikTokTraceInfo.class) {
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
