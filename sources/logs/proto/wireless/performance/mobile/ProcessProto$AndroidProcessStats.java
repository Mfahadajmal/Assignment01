package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessProto$AndroidProcessStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ProcessProto$AndroidProcessStats DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public boolean isInForeground_;
    public int oomScoreAdj_;
    public long processElapsedTimeMs_;
    public int threadCount_;
    public String processName_ = "";
    public String importanceReasonComponent_ = "";

    static {
        ProcessProto$AndroidProcessStats processProto$AndroidProcessStats = new ProcessProto$AndroidProcessStats();
        DEFAULT_INSTANCE = processProto$AndroidProcessStats;
        GeneratedMessageLite.registerDefaultInstance(ProcessProto$AndroidProcessStats.class, processProto$AndroidProcessStats);
    }

    private ProcessProto$AndroidProcessStats() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဇ\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006ဈ\u0005", new Object[]{"bitField0_", "processElapsedTimeMs_", "isInForeground_", "threadCount_", "processName_", "oomScoreAdj_", "importanceReasonComponent_"});
            case NEW_MUTABLE_INSTANCE:
                return new ProcessProto$AndroidProcessStats();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ProcessProto$AndroidProcessStats.class) {
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
