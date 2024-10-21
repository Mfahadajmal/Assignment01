package logs.proto.wireless.performance.mobile;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$BatteryStatsDiff extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BatteryMetric$BatteryStatsDiff DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long chargeCounterDiffUah_;
    public long durationMs_;
    public long elapedRealtimeMs_;
    public int endInfo_;
    public long startHashedCustomEventName_;
    public int startInfo_;
    public ExtensionMetric$MetricExtension startMetricExtension_;
    public BatteryMetric$UidHealthProto uidHealthProtoDiff_;
    private byte memoizedIsInitialized = 2;
    public String startCustomEventName_ = "";
    public String startConstantEventName_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum SampleInfo implements Internal.EnumLite {
        UNKNOWN(0),
        FOREGROUND_TO_BACKGROUND(1),
        BACKGROUND_TO_FOREGROUND(2),
        FOREGROUND_SERVICE_START(3),
        FOREGROUND_SERVICE_STOP(4),
        CUSTOM_MEASURE_START(5),
        CUSTOM_MEASURE_STOP(6);

        public final int value;

        SampleInfo(int i) {
            this.value = i;
        }

        public static SampleInfo forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return FOREGROUND_TO_BACKGROUND;
                case 2:
                    return BACKGROUND_TO_FOREGROUND;
                case 3:
                    return FOREGROUND_SERVICE_START;
                case 4:
                    return FOREGROUND_SERVICE_STOP;
                case 5:
                    return CUSTOM_MEASURE_START;
                case 6:
                    return CUSTOM_MEASURE_STOP;
                default:
                    return null;
            }
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return Integer.toString(this.value);
        }
    }

    static {
        BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = new BatteryMetric$BatteryStatsDiff();
        DEFAULT_INSTANCE = batteryMetric$BatteryStatsDiff;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$BatteryStatsDiff.class, batteryMetric$BatteryStatsDiff);
    }

    private BatteryMetric$BatteryStatsDiff() {
        emptyProtobufList();
        emptyProtobufList();
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
                Internal.EnumVerifier enumVerifier = VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$19;
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\n\u0000\u0001\u0001\f\n\u0000\u0000\u0001\u0001᠌\u0000\u0002᠌\u0005\u0003ဂ\u0006\u0006ဉ\u0007\u0007ဂ\b\bစ\u0001\tဈ\u0002\nဈ\u0003\u000bᐉ\u0004\fဂ\t", new Object[]{"bitField0_", "startInfo_", enumVerifier, "endInfo_", enumVerifier, "durationMs_", "uidHealthProtoDiff_", "elapedRealtimeMs_", "startHashedCustomEventName_", "startCustomEventName_", "startConstantEventName_", "startMetricExtension_", "chargeCounterDiffUah_"});
            case NEW_MUTABLE_INSTANCE:
                return new BatteryMetric$BatteryStatsDiff();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$BatteryStatsDiff.class) {
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
