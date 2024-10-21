package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetric$AndroidMemoryStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final MemoryMetric$AndroidMemoryStats DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public long anonRssKb_;
    public int availableMemoryKb_;
    public int bitField0_;
    public int dalvikPrivateDirtyKb_;
    public int dalvikPssKb_;
    public int nativePrivateDirtyKb_;
    public int nativePssKb_;
    public int otherGraphicsPssKb_;
    public int otherPrivateDirtyKb_;
    public int otherPssKb_;
    public long rssHwmKb_;
    public int summaryCodeKb_;
    public int summaryGraphicsKb_;
    public int summaryJavaHeapKb_;
    public int summaryPrivateOtherKb_;
    public int summaryStackKb_;
    public int summarySystemKb_;
    public long swapKb_;
    public int totalMemoryMb_;
    public int totalPrivateCleanKb_;
    public int totalPssByMemInfoKb_;
    public long totalRssKb_;
    public int totalSharedDirtyKb_;
    public int totalSwappablePssKb_;
    public long vmSizeKb_;

    static {
        MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats = new MemoryMetric$AndroidMemoryStats();
        DEFAULT_INSTANCE = memoryMetric$AndroidMemoryStats;
        GeneratedMessageLite.registerDefaultInstance(MemoryMetric$AndroidMemoryStats.class, memoryMetric$AndroidMemoryStats);
    }

    private MemoryMetric$AndroidMemoryStats() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0011\u0018\u0007\u0000\u0000\u0000\u0011င\u0011\u0012င\u0012\u0014ဂ\u0013\u0015ဂ\u0014\u0016ဂ\u0015\u0017ဂ\u0016\u0018ဂ\u0017", new Object[]{"bitField0_", "availableMemoryKb_", "totalMemoryMb_", "rssHwmKb_", "totalRssKb_", "anonRssKb_", "swapKb_", "vmSizeKb_"});
            case NEW_MUTABLE_INSTANCE:
                return new MemoryMetric$AndroidMemoryStats();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MemoryMetric$AndroidMemoryStats.class) {
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
