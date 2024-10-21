package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$JankMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$JankMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int deviceRefreshRate_;
    public int droppedReportCount_;
    public int durationMs_;
    public int durationOfFramesMissingRefreshRateBasedDeadlineMs_;
    public Internal.ProtobufList frameTimeHistogram_ = emptyProtobufList();
    public int framesMissingRefreshRateBasedDrawDeadline_;
    public int jankyDurationMs_;
    public int jankyFrameCount_;
    public int maxFrameRenderTimeMs_;
    public SystemHealthProto$PackedHistogram packedFrameTimeHistogram_;
    public int recordingDurationMs_;
    public int renderedFrameCount_;
    public SystemHealthProto$PackedHistogram slackTimeHistogram_;

    static {
        SystemHealthProto$JankMetric systemHealthProto$JankMetric = new SystemHealthProto$JankMetric();
        DEFAULT_INSTANCE = systemHealthProto$JankMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$JankMetric.class, systemHealthProto$JankMetric);
    }

    private SystemHealthProto$JankMetric() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0003\u0004င\u0006\u0005\u001b\u0006င\b\u0007င\u0002\bင\u0004\tင\u0005\nဉ\u0007\u000bင\t\fင\n\rဉ\u000b", new Object[]{"bitField0_", "jankyFrameCount_", "renderedFrameCount_", "maxFrameRenderTimeMs_", "durationMs_", "frameTimeHistogram_", SystemHealthProto$HistogramBucket.class, "deviceRefreshRate_", "droppedReportCount_", "recordingDurationMs_", "jankyDurationMs_", "packedFrameTimeHistogram_", "framesMissingRefreshRateBasedDrawDeadline_", "durationOfFramesMissingRefreshRateBasedDeadlineMs_", "slackTimeHistogram_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$JankMetric();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$JankMetric.class) {
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
