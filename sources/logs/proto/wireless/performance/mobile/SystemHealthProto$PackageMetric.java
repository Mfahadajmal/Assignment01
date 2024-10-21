package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$PackageMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$PackageMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long cacheSize_;
    public long codeSize_;
    public long dataSize_;
    public Internal.ProtobufList dirStats_ = emptyProtobufList();
    public long externalCacheSize_;
    public long externalCodeSize_;
    public long externalDataSize_;
    public long externalMediaSize_;
    public long externalObbSize_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DirStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final DirStats DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String dirPath_ = "";
        public Internal.LongList hashedDirPath_ = emptyLongList();

        static {
            DirStats dirStats = new DirStats();
            DEFAULT_INSTANCE = dirStats;
            GeneratedMessageLite.registerDefaultInstance(DirStats.class, dirStats);
        }

        private DirStats() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0003\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0003(", new Object[]{"bitField0_", "dirPath_", "hashedDirPath_"});
                case NEW_MUTABLE_INSTANCE:
                    return new DirStats();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (DirStats.class) {
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
        SystemHealthProto$PackageMetric systemHealthProto$PackageMetric = new SystemHealthProto$PackageMetric();
        DEFAULT_INSTANCE = systemHealthProto$PackageMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$PackageMetric.class, systemHealthProto$PackageMetric);
    }

    private SystemHealthProto$PackageMetric() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\n\t\u0000\u0001\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဂ\u0007\n\u001b", new Object[]{"bitField0_", "cacheSize_", "codeSize_", "dataSize_", "externalCacheSize_", "externalCodeSize_", "externalDataSize_", "externalMediaSize_", "externalObbSize_", "dirStats_", DirStats.class});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$PackageMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$PackageMetric.class) {
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
