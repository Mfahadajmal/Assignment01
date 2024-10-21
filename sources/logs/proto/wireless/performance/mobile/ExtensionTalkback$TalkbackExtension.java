package logs.proto.wireless.performance.mobile;

import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import com.google.search.mdi.aratea.proto.FeatureName;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExtensionTalkback$TalkbackExtension extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ExtensionTalkback$TalkbackExtension DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public static final GeneratedMessageLite.GeneratedExtension metricExtension;
    public int bitField0_;
    public EventLatencyInfo eventLatencyInfo_;
    public int featureStateBitmask_;
    public TtsLatencyInfo ttsLatencyInfo_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ActionLatencyInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final ActionLatencyInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int actionType_;
        public int bitField0_;
        public long processingTimeMs_;
        public boolean success_;

        static {
            ActionLatencyInfo actionLatencyInfo = new ActionLatencyInfo();
            DEFAULT_INSTANCE = actionLatencyInfo;
            GeneratedMessageLite.registerDefaultInstance(ActionLatencyInfo.class, actionLatencyInfo);
        }

        private ActionLatencyInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဃ\u0002", new Object[]{"bitField0_", "actionType_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$16, "success_", "processingTimeMs_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ActionLatencyInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ActionLatencyInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EventLatencyInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final EventLatencyInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public ActionLatencyInfo actionLatencyInfo_;
        public int bitField0_;
        public int eventType_;
        public GestureEventLatencyInfo gestureEventLatencyInfo_;
        public long latencyEventTransmissionMs_;
        public long latencyFeedbackComposedMs_;
        public long latencyFeedbackQueuedMs_;
        public MessageQueueDetails messageQueueDetails_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class EventType {
            public static final int TYPE_UNDEFINED$ar$edu$46c2921f_0 = 1;
            public static final int TYPE_ACCESSIBILITY_UNDEFINED$ar$edu = 2;
            public static final int TYPE_VIEW_CLICKED$ar$edu = 3;
            public static final int TYPE_VIEW_LONG_CLICKED$ar$edu = 4;
            public static final int TYPE_VIEW_SELECTED$ar$edu = 5;
            public static final int TYPE_VIEW_FOCUSED$ar$edu = 6;
            public static final int TYPE_VIEW_TEXT_CHANGED$ar$edu = 7;
            public static final int TYPE_WINDOW_STATE_CHANGED$ar$edu = 8;
            public static final int TYPE_NOTIFICATION_STATE_CHANGED$ar$edu = 9;
            public static final int TYPE_VIEW_HOVER_ENTER$ar$edu = 10;
            public static final int TYPE_VIEW_HOVER_EXIT$ar$edu = 11;
            public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START$ar$edu = 12;
            public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END$ar$edu = 13;
            public static final int TYPE_WINDOW_CONTENT_CHANGED$ar$edu = 14;
            public static final int TYPE_VIEW_SCROLLED$ar$edu = 15;
            public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED$ar$edu = 16;
            public static final int TYPE_ANNOUNCEMENT$ar$edu = 17;
            public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED$ar$edu = 18;
            public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED$ar$edu = 19;
            public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY$ar$edu = 20;
            public static final int TYPE_GESTURE_DETECTION_START$ar$edu = 21;
            public static final int TYPE_GESTURE_DETECTION_END$ar$edu = 22;
            public static final int TYPE_TOUCH_INTERACTION_START$ar$edu = 23;
            public static final int TYPE_TOUCH_INTERACTION_END$ar$edu = 24;
            public static final int TYPE_WINDOWS_CHANGED$ar$edu = 25;
            public static final int TYPE_VIEW_CONTEXT_CLICKED$ar$edu = 26;
            public static final int TYPE_ASSIST_READING_CONTEXT$ar$edu = 27;
            public static final int TYPE_SPEECH_STATE_CHANGE$ar$edu = 28;
            public static final int TYPE_VIEW_TARGETED_BY_SCROLL$ar$edu = 29;
            public static final int TYPE_GESTURE$ar$edu = 102;
            public static final int TYPE_FINGERPRINT_GESTURE$ar$edu = 103;
            public static final int TYPE_KEY_EVENT$ar$edu = 104;
            public static final int TYPE_DEVICE_ROTATE$ar$edu = 105;
            public static final int TYPE_MOTION_EVENT_SOURCE$ar$edu = 106;
            private static final /* synthetic */ int[] $VALUES$ar$edu$506b4d3_0 = {TYPE_UNDEFINED$ar$edu$46c2921f_0, TYPE_ACCESSIBILITY_UNDEFINED$ar$edu, TYPE_VIEW_CLICKED$ar$edu, TYPE_VIEW_LONG_CLICKED$ar$edu, TYPE_VIEW_SELECTED$ar$edu, TYPE_VIEW_FOCUSED$ar$edu, TYPE_VIEW_TEXT_CHANGED$ar$edu, TYPE_WINDOW_STATE_CHANGED$ar$edu, TYPE_NOTIFICATION_STATE_CHANGED$ar$edu, TYPE_VIEW_HOVER_ENTER$ar$edu, TYPE_VIEW_HOVER_EXIT$ar$edu, TYPE_TOUCH_EXPLORATION_GESTURE_START$ar$edu, TYPE_TOUCH_EXPLORATION_GESTURE_END$ar$edu, TYPE_WINDOW_CONTENT_CHANGED$ar$edu, TYPE_VIEW_SCROLLED$ar$edu, TYPE_VIEW_TEXT_SELECTION_CHANGED$ar$edu, TYPE_ANNOUNCEMENT$ar$edu, TYPE_VIEW_ACCESSIBILITY_FOCUSED$ar$edu, TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED$ar$edu, TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY$ar$edu, TYPE_GESTURE_DETECTION_START$ar$edu, TYPE_GESTURE_DETECTION_END$ar$edu, TYPE_TOUCH_INTERACTION_START$ar$edu, TYPE_TOUCH_INTERACTION_END$ar$edu, TYPE_WINDOWS_CHANGED$ar$edu, TYPE_VIEW_CONTEXT_CLICKED$ar$edu, TYPE_ASSIST_READING_CONTEXT$ar$edu, TYPE_SPEECH_STATE_CHANGE$ar$edu, TYPE_VIEW_TARGETED_BY_SCROLL$ar$edu, TYPE_GESTURE$ar$edu, TYPE_FINGERPRINT_GESTURE$ar$edu, TYPE_KEY_EVENT$ar$edu, TYPE_DEVICE_ROTATE$ar$edu, TYPE_MOTION_EVENT_SOURCE$ar$edu};

            public static int forNumber$ar$edu$7f0b8ee6_0(int i) {
                switch (i) {
                    case 0:
                        return TYPE_UNDEFINED$ar$edu$46c2921f_0;
                    case 1:
                        return TYPE_ACCESSIBILITY_UNDEFINED$ar$edu;
                    case 2:
                        return TYPE_VIEW_CLICKED$ar$edu;
                    case 3:
                        return TYPE_VIEW_LONG_CLICKED$ar$edu;
                    case 4:
                        return TYPE_VIEW_SELECTED$ar$edu;
                    case 5:
                        return TYPE_VIEW_FOCUSED$ar$edu;
                    case 6:
                        return TYPE_VIEW_TEXT_CHANGED$ar$edu;
                    case 7:
                        return TYPE_WINDOW_STATE_CHANGED$ar$edu;
                    case 8:
                        return TYPE_NOTIFICATION_STATE_CHANGED$ar$edu;
                    case 9:
                        return TYPE_VIEW_HOVER_ENTER$ar$edu;
                    case 10:
                        return TYPE_VIEW_HOVER_EXIT$ar$edu;
                    case 11:
                        return TYPE_TOUCH_EXPLORATION_GESTURE_START$ar$edu;
                    case 12:
                        return TYPE_TOUCH_EXPLORATION_GESTURE_END$ar$edu;
                    case 13:
                        return TYPE_WINDOW_CONTENT_CHANGED$ar$edu;
                    case 14:
                        return TYPE_VIEW_SCROLLED$ar$edu;
                    case 15:
                        return TYPE_VIEW_TEXT_SELECTION_CHANGED$ar$edu;
                    case 16:
                        return TYPE_ANNOUNCEMENT$ar$edu;
                    case 17:
                        return TYPE_VIEW_ACCESSIBILITY_FOCUSED$ar$edu;
                    case 18:
                        return TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED$ar$edu;
                    case 19:
                        return TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY$ar$edu;
                    case 20:
                        return TYPE_GESTURE_DETECTION_START$ar$edu;
                    case 21:
                        return TYPE_GESTURE_DETECTION_END$ar$edu;
                    case 22:
                        return TYPE_TOUCH_INTERACTION_START$ar$edu;
                    case 23:
                        return TYPE_TOUCH_INTERACTION_END$ar$edu;
                    case 24:
                        return TYPE_WINDOWS_CHANGED$ar$edu;
                    case 25:
                        return TYPE_VIEW_CONTEXT_CLICKED$ar$edu;
                    case 26:
                        return TYPE_ASSIST_READING_CONTEXT$ar$edu;
                    case 27:
                        return TYPE_SPEECH_STATE_CHANGE$ar$edu;
                    case 28:
                        return TYPE_VIEW_TARGETED_BY_SCROLL$ar$edu;
                    default:
                        switch (i) {
                            case FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu /* 101 */:
                                return TYPE_GESTURE$ar$edu;
                            case 102:
                                return TYPE_FINGERPRINT_GESTURE$ar$edu;
                            case TYPE_FINGERPRINT_GESTURE$ar$edu /* 103 */:
                                return TYPE_KEY_EVENT$ar$edu;
                            case TYPE_KEY_EVENT$ar$edu /* 104 */:
                                return TYPE_DEVICE_ROTATE$ar$edu;
                            case TYPE_DEVICE_ROTATE$ar$edu /* 105 */:
                                return TYPE_MOTION_EVENT_SOURCE$ar$edu;
                            default:
                                return 0;
                        }
                }
            }

            public static int[] values$ar$edu$8e7b811c_0() {
                return new int[]{TYPE_UNDEFINED$ar$edu$46c2921f_0, TYPE_ACCESSIBILITY_UNDEFINED$ar$edu, TYPE_VIEW_CLICKED$ar$edu, TYPE_VIEW_LONG_CLICKED$ar$edu, TYPE_VIEW_SELECTED$ar$edu, TYPE_VIEW_FOCUSED$ar$edu, TYPE_VIEW_TEXT_CHANGED$ar$edu, TYPE_WINDOW_STATE_CHANGED$ar$edu, TYPE_NOTIFICATION_STATE_CHANGED$ar$edu, TYPE_VIEW_HOVER_ENTER$ar$edu, TYPE_VIEW_HOVER_EXIT$ar$edu, TYPE_TOUCH_EXPLORATION_GESTURE_START$ar$edu, TYPE_TOUCH_EXPLORATION_GESTURE_END$ar$edu, TYPE_WINDOW_CONTENT_CHANGED$ar$edu, TYPE_VIEW_SCROLLED$ar$edu, TYPE_VIEW_TEXT_SELECTION_CHANGED$ar$edu, TYPE_ANNOUNCEMENT$ar$edu, TYPE_VIEW_ACCESSIBILITY_FOCUSED$ar$edu, TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED$ar$edu, TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY$ar$edu, TYPE_GESTURE_DETECTION_START$ar$edu, TYPE_GESTURE_DETECTION_END$ar$edu, TYPE_TOUCH_INTERACTION_START$ar$edu, TYPE_TOUCH_INTERACTION_END$ar$edu, TYPE_WINDOWS_CHANGED$ar$edu, TYPE_VIEW_CONTEXT_CLICKED$ar$edu, TYPE_ASSIST_READING_CONTEXT$ar$edu, TYPE_SPEECH_STATE_CHANGE$ar$edu, TYPE_VIEW_TARGETED_BY_SCROLL$ar$edu, TYPE_GESTURE$ar$edu, TYPE_FINGERPRINT_GESTURE$ar$edu, TYPE_KEY_EVENT$ar$edu, TYPE_DEVICE_ROTATE$ar$edu, TYPE_MOTION_EVENT_SOURCE$ar$edu};
            }
        }

        static {
            EventLatencyInfo eventLatencyInfo = new EventLatencyInfo();
            DEFAULT_INSTANCE = eventLatencyInfo;
            GeneratedMessageLite.registerDefaultInstance(EventLatencyInfo.class, eventLatencyInfo);
        }

        private EventLatencyInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\t\u0007\u0000\u0000\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0007ဉ\u0006\bဉ\u0007\tဉ\b", new Object[]{"bitField0_", "eventType_", TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE, "latencyEventTransmissionMs_", "latencyFeedbackComposedMs_", "latencyFeedbackQueuedMs_", "actionLatencyInfo_", "messageQueueDetails_", "gestureEventLatencyInfo_"});
                case NEW_MUTABLE_INSTANCE:
                    return new EventLatencyInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (EventLatencyInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GestureEventLatencyInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final GestureEventLatencyInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public long firstMotionEventReceivedMs_;
        public long gestureDecisionTimeMs_;
        public int gestureId_;
        public boolean isDefaultDisplay_;
        public long lastMotionEventTransmissionLatencyMs_;
        public long onGestureDetectedTimeMs_;

        static {
            GestureEventLatencyInfo gestureEventLatencyInfo = new GestureEventLatencyInfo();
            DEFAULT_INSTANCE = gestureEventLatencyInfo;
            GeneratedMessageLite.registerDefaultInstance(GestureEventLatencyInfo.class, gestureEventLatencyInfo);
        }

        private GestureEventLatencyInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဇ\u0000\u0002င\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005", new Object[]{"bitField0_", "isDefaultDisplay_", "gestureId_", "firstMotionEventReceivedMs_", "lastMotionEventTransmissionLatencyMs_", "gestureDecisionTimeMs_", "onGestureDetectedTimeMs_"});
                case NEW_MUTABLE_INSTANCE:
                    return new GestureEventLatencyInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (GestureEventLatencyInfo.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MessageQueueDetails extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final MessageQueueDetails DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public long maxWaitingTimeMs_;
        public long pendingMessageCounts_;
        public long queuedMessageCounts_;

        static {
            MessageQueueDetails messageQueueDetails = new MessageQueueDetails();
            DEFAULT_INSTANCE = messageQueueDetails;
            GeneratedMessageLite.registerDefaultInstance(MessageQueueDetails.class, messageQueueDetails);
        }

        private MessageQueueDetails() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဃ\u0000\u0002ဃ\u0001\u0003ဃ\u0002", new Object[]{"bitField0_", "maxWaitingTimeMs_", "pendingMessageCounts_", "queuedMessageCounts_"});
                case NEW_MUTABLE_INSTANCE:
                    return new MessageQueueDetails();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (MessageQueueDetails.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TtsLatencyInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final TtsLatencyInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int enginePackageName_;
        public long engineVersionCode_;
        public String locale_ = "";
        public int textLength_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class TtsEngineName {
            public static final int ENGINE_UNDEFINED$ar$edu = 1;
            public static final int ENGINE_GOOGLE$ar$edu = 2;
            public static final int ENGINE_SAMSUNG$ar$edu = 3;
            public static final int ENGINE_ACAPELA$ar$edu = 4;
            public static final int ENGINE_VOCALIZER$ar$edu = 5;
            public static final int ENGINE_ESPEAK$ar$edu = 6;
            public static final int ENGINE_DTALKER$ar$edu = 7;
            private static final /* synthetic */ int[] $VALUES$ar$edu$cf60d242_0 = {ENGINE_UNDEFINED$ar$edu, ENGINE_GOOGLE$ar$edu, ENGINE_SAMSUNG$ar$edu, ENGINE_ACAPELA$ar$edu, ENGINE_VOCALIZER$ar$edu, ENGINE_ESPEAK$ar$edu, ENGINE_DTALKER$ar$edu};

            /* compiled from: PG */
            /* loaded from: classes.dex */
            public final class TtsEngineNameVerifier implements Internal.EnumVerifier {
                private final /* synthetic */ int switching_field;
                static final Internal.EnumVerifier class_merging$INSTANCE$14 = new TtsEngineNameVerifier(15);
                static final Internal.EnumVerifier class_merging$INSTANCE$13 = new TtsEngineNameVerifier(14);
                static final Internal.EnumVerifier class_merging$INSTANCE$12 = new TtsEngineNameVerifier(13);
                static final Internal.EnumVerifier class_merging$INSTANCE$11 = new TtsEngineNameVerifier(12);
                public static final Internal.EnumVerifier class_merging$INSTANCE$10 = new TtsEngineNameVerifier(11);
                static final Internal.EnumVerifier class_merging$INSTANCE$9 = new TtsEngineNameVerifier(10);
                static final Internal.EnumVerifier class_merging$INSTANCE$8 = new TtsEngineNameVerifier(9);
                static final Internal.EnumVerifier class_merging$INSTANCE$7 = new TtsEngineNameVerifier(8);
                static final Internal.EnumVerifier class_merging$INSTANCE$6 = new TtsEngineNameVerifier(7);
                static final Internal.EnumVerifier class_merging$INSTANCE$5 = new TtsEngineNameVerifier(6);
                static final Internal.EnumVerifier class_merging$INSTANCE$4 = new TtsEngineNameVerifier(5);
                static final Internal.EnumVerifier class_merging$INSTANCE$3 = new TtsEngineNameVerifier(4);
                static final Internal.EnumVerifier class_merging$INSTANCE$2 = new TtsEngineNameVerifier(3);
                static final Internal.EnumVerifier class_merging$INSTANCE$1 = new TtsEngineNameVerifier(2);
                static final Internal.EnumVerifier class_merging$INSTANCE = new TtsEngineNameVerifier(1);
                static final Internal.EnumVerifier INSTANCE = new TtsEngineNameVerifier(0);

                private TtsEngineNameVerifier(int i) {
                    this.switching_field = i;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0085 A[RETURN] */
                @Override // com.google.protobuf.Internal.EnumVerifier
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final boolean isInRange(int r4) {
                    /*
                        r3 = this;
                        int r0 = r3.switching_field
                        r1 = 1
                        r2 = 0
                        switch(r0) {
                            case 0: goto L7e;
                            case 1: goto L76;
                            case 2: goto L6e;
                            case 3: goto L66;
                            case 4: goto L5e;
                            case 5: goto L56;
                            case 6: goto L4e;
                            case 7: goto L46;
                            case 8: goto L3e;
                            case 9: goto L36;
                            case 10: goto L2e;
                            case 11: goto L26;
                            case 12: goto L1e;
                            case 13: goto L16;
                            case 14: goto Le;
                            default: goto L7;
                        }
                    L7:
                        int r4 = logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters.SamplingStrategy.forNumber$ar$edu$fde10cc9_0(r4)
                        if (r4 == 0) goto L85
                        return r1
                    Le:
                        int r4 = logs.proto.wireless.performance.mobile.SystemHealthProto$PrimesStats.PrimesEvent.forNumber$ar$edu$8c9c582f_0(r4)
                        if (r4 == 0) goto L15
                        return r1
                    L15:
                        return r2
                    L16:
                        int r4 = logs.proto.wireless.performance.mobile.SystemHealthProto$CrashMetric.CrashType.forNumber$ar$edu$e0a8c317_0(r4)
                        if (r4 == 0) goto L1d
                        return r1
                    L1d:
                        return r2
                    L1e:
                        int r4 = logs.proto.wireless.performance.mobile.SystemHealthProto.CrashMetric.CrashLoopInfo.LoopState.forNumber$ar$edu$eac8bba5_0(r4)
                        if (r4 == 0) goto L25
                        return r1
                    L25:
                        return r2
                    L26:
                        int r4 = logs.proto.wireless.performance.mobile.SystemHealthProto$ApplicationInfo.HardwareVariant.forNumber$ar$edu$1f4071e5_0(r4)
                        if (r4 == 0) goto L2d
                        return r1
                    L2d:
                        return r2
                    L2e:
                        int r4 = logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$Span.SpanType.forNumber$ar$edu$4e0b883_0(r4)
                        if (r4 == 0) goto L35
                        return r1
                    L35:
                        return r2
                    L36:
                        int r4 = logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$EndStatus.forNumber$ar$edu$609d8896_0(r4)
                        if (r4 == 0) goto L3d
                        return r1
                    L3d:
                        return r2
                    L3e:
                        int r4 = logs.proto.wireless.performance.mobile.NetworkMetric$RequestStatus.forNumber$ar$edu$8ed70632_0(r4)
                        if (r4 == 0) goto L45
                        return r1
                    L45:
                        return r2
                    L46:
                        int r4 = logs.proto.wireless.performance.mobile.NetworkMetric$RequestNegotiatedProtocol.forNumber$ar$edu$57054137_0(r4)
                        if (r4 == 0) goto L4d
                        return r1
                    L4d:
                        return r2
                    L4e:
                        int r4 = logs.proto.wireless.performance.mobile.NetworkMetric$RequestFailedReason.forNumber$ar$edu$a09348fc_0(r4)
                        if (r4 == 0) goto L55
                        return r1
                    L55:
                        return r2
                    L56:
                        int r4 = logs.proto.wireless.performance.mobile.NetworkMetric$PeerDistance.forNumber$ar$edu$edc228e9_0(r4)
                        if (r4 == 0) goto L5d
                        return r1
                    L5d:
                        return r2
                    L5e:
                        logs.proto.wireless.performance.mobile.NetworkMetric$NetworkEventUsage$NetworkingStack r4 = logs.proto.wireless.performance.mobile.NetworkMetric$NetworkEventUsage.NetworkingStack.forNumber(r4)
                        if (r4 == 0) goto L65
                        return r1
                    L65:
                        return r2
                    L66:
                        int r4 = logs.proto.wireless.performance.mobile.NetworkMetric$NetworkConnectionInfo.NetworkType.forNumber$ar$edu$1098a20c_0(r4)
                        if (r4 == 0) goto L6d
                        return r1
                    L6d:
                        return r2
                    L6e:
                        int r4 = logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric.MemoryEventCode.forNumber$ar$edu$cb599861_0(r4)
                        if (r4 == 0) goto L75
                        return r1
                    L75:
                        return r2
                    L76:
                        int r4 = logs.proto.wireless.performance.mobile.ExtensionTalkback.TalkbackExtension.EventLatencyInfo.EventType.forNumber$ar$edu$7f0b8ee6_0(r4)
                        if (r4 == 0) goto L7d
                        return r1
                    L7d:
                        return r2
                    L7e:
                        int r4 = logs.proto.wireless.performance.mobile.ExtensionTalkback.TalkbackExtension.TtsLatencyInfo.TtsEngineName.forNumber$ar$edu$4c41847d_0(r4)
                        if (r4 == 0) goto L85
                        return r1
                    L85:
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: logs.proto.wireless.performance.mobile.ExtensionTalkback.TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.isInRange(int):boolean");
                }
            }

            public static int forNumber$ar$edu$4c41847d_0(int i) {
                switch (i) {
                    case 0:
                        return ENGINE_UNDEFINED$ar$edu;
                    case 1:
                        return ENGINE_GOOGLE$ar$edu;
                    case 2:
                        return ENGINE_SAMSUNG$ar$edu;
                    case 3:
                        return ENGINE_ACAPELA$ar$edu;
                    case 4:
                        return ENGINE_VOCALIZER$ar$edu;
                    case 5:
                        return ENGINE_ESPEAK$ar$edu;
                    case 6:
                        return ENGINE_DTALKER$ar$edu;
                    default:
                        return 0;
                }
            }

            public static int[] values$ar$edu$744f262_0() {
                return new int[]{ENGINE_UNDEFINED$ar$edu, ENGINE_GOOGLE$ar$edu, ENGINE_SAMSUNG$ar$edu, ENGINE_ACAPELA$ar$edu, ENGINE_VOCALIZER$ar$edu, ENGINE_ESPEAK$ar$edu, ENGINE_DTALKER$ar$edu};
            }
        }

        static {
            TtsLatencyInfo ttsLatencyInfo = new TtsLatencyInfo();
            DEFAULT_INSTANCE = ttsLatencyInfo;
            GeneratedMessageLite.registerDefaultInstance(TtsLatencyInfo.class, ttsLatencyInfo);
        }

        private TtsLatencyInfo() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003င\u0002\u0004ဈ\u0003", new Object[]{"bitField0_", "enginePackageName_", TtsEngineName.TtsEngineNameVerifier.INSTANCE, "engineVersionCode_", "textLength_", "locale_"});
                case NEW_MUTABLE_INSTANCE:
                    return new TtsLatencyInfo();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (TtsLatencyInfo.class) {
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
        ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension = new ExtensionTalkback$TalkbackExtension();
        DEFAULT_INSTANCE = extensionTalkback$TalkbackExtension;
        GeneratedMessageLite.registerDefaultInstance(ExtensionTalkback$TalkbackExtension.class, extensionTalkback$TalkbackExtension);
        metricExtension = GeneratedMessageLite.newSingularGeneratedExtension(ExtensionMetric$MetricExtension.DEFAULT_INSTANCE, extensionTalkback$TalkbackExtension, extensionTalkback$TalkbackExtension, null, 28, WireFormat.FieldType.MESSAGE, ExtensionTalkback$TalkbackExtension.class);
    }

    private ExtensionTalkback$TalkbackExtension() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဋ\u0002", new Object[]{"bitField0_", "eventLatencyInfo_", "ttsLatencyInfo_", "featureStateBitmask_"});
            case NEW_MUTABLE_INSTANCE:
                return new ExtensionTalkback$TalkbackExtension();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ExtensionTalkback$TalkbackExtension.class) {
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
