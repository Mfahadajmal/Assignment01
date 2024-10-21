package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.android.libraries.vision.visionkit.OcrOptions;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import com.google.wireless.android.play.playlog.proto.Compliance$ComplianceData;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$Span;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SchedulerOptions extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final SchedulerOptions DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int accelerationAllowlistFlavor_;
    public int bitField0_;
    public Internal.ProtobufList customSubgraph_;
    public boolean enableFrameBufferInputRepository_;
    private byte memoizedIsInitialized = 2;
    private OcrOptions ocrOptions_;
    private ScreenOptions screenOptions_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        public Builder(byte[] bArr, byte[] bArr2) {
            super(MetricSnapshot.DEFAULT_INSTANCE);
        }

        public final void addCustomSubgraph$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SystemHealthProto$PackedHistogram.Builder builder) {
            copyOnWrite();
            SchedulerOptions schedulerOptions = (SchedulerOptions) this.instance;
            Subgraph subgraph = (Subgraph) builder.build();
            SchedulerOptions schedulerOptions2 = SchedulerOptions.DEFAULT_INSTANCE;
            subgraph.getClass();
            Internal.ProtobufList protobufList = schedulerOptions.customSubgraph_;
            if (!protobufList.isModifiable()) {
                schedulerOptions.customSubgraph_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            schedulerOptions.customSubgraph_.add(subgraph);
        }

        public Builder(byte[] bArr) {
            super(Results.DEFAULT_INSTANCE);
        }

        public Builder() {
            super(SchedulerOptions.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr) {
            super(ClientAnalytics$LogEvent.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr) {
            super(Compliance$ComplianceData.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr) {
            super(ExtensionMetric$MetricExtension.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr) {
            super(MemoryMetric$MemoryUsageMetric.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr) {
            super(PrimesTraceOuterClass$Span.DEFAULT_INSTANCE);
        }
    }

    static {
        SchedulerOptions schedulerOptions = new SchedulerOptions();
        DEFAULT_INSTANCE = schedulerOptions;
        GeneratedMessageLite.registerDefaultInstance(SchedulerOptions.class, schedulerOptions);
    }

    private SchedulerOptions() {
        emptyProtobufList();
        emptyProtobufList();
        emptyProtobufList();
        emptyProtobufList();
        emptyProtobufList();
        emptyProtobufList();
        emptyProtobufList();
        emptyProtobufList();
        GeneratedMessageLite.emptyProtobufList();
        this.customSubgraph_ = emptyProtobufList();
        emptyProtobufList();
        GeneratedMessageLite.emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        byte b = 1;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                if (obj == null) {
                    b = 0;
                }
                this.memoizedIsInitialized = b;
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u000f.\u0005\u0000\u0001\u0001\u000fဉ\u001f\u001f\u001b&ᐉ\b-᠌\u0014.ဇ\n", new Object[]{"bitField0_", "ocrOptions_", "customSubgraph_", Subgraph.class, "screenOptions_", "accelerationAllowlistFlavor_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$3, "enableFrameBufferInputRepository_"});
            case NEW_MUTABLE_INSTANCE:
                return new SchedulerOptions();
            case NEW_BUILDER:
                return new Builder();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SchedulerOptions.class) {
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
