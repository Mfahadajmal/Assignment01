package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrainingUsageProto$TrainingUsage extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TrainingUsageProto$TrainingUsage DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int basicNavigationEntryCount_;
    private int bitField0_;
    private int buttonCloseCount_;
    private int buttonNextCount_;
    private int buttonPreviousCount_;
    private int buttonTurnOffTalkbackCount_;
    private int everydayTaskEntryCount_;
    private int onboardingEntryCount_;
    private int practiceGestureEntryCount_;
    private int readingNavigationEntryCount_;
    private int textEditingEntryCount_;
    public Internal.ProtobufList trainingMetricWrapper_ = emptyProtobufList();
    private int tutorialEntryCount_;
    private int voiceCommandEntryCount_;

    static {
        TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage = new TrainingUsageProto$TrainingUsage();
        DEFAULT_INSTANCE = trainingUsageProto$TrainingUsage;
        GeneratedMessageLite.registerDefaultInstance(TrainingUsageProto$TrainingUsage.class, trainingUsageProto$TrainingUsage);
    }

    private TrainingUsageProto$TrainingUsage() {
    }

    public static /* synthetic */ void access$100(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 1;
        trainingUsageProto$TrainingUsage.onboardingEntryCount_ = i;
    }

    public static /* synthetic */ void access$1100(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 32;
        trainingUsageProto$TrainingUsage.voiceCommandEntryCount_ = i;
    }

    public static /* synthetic */ void access$1300(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 64;
        trainingUsageProto$TrainingUsage.everydayTaskEntryCount_ = i;
    }

    public static /* synthetic */ void access$1500(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        trainingUsageProto$TrainingUsage.practiceGestureEntryCount_ = i;
    }

    public static /* synthetic */ void access$1700(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 256;
        trainingUsageProto$TrainingUsage.buttonNextCount_ = i;
    }

    public static /* synthetic */ void access$1900(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 512;
        trainingUsageProto$TrainingUsage.buttonPreviousCount_ = i;
    }

    public static /* synthetic */ void access$2100(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 1024;
        trainingUsageProto$TrainingUsage.buttonCloseCount_ = i;
    }

    public static /* synthetic */ void access$2300(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 2048;
        trainingUsageProto$TrainingUsage.buttonTurnOffTalkbackCount_ = i;
    }

    public static /* synthetic */ void access$300(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 2;
        trainingUsageProto$TrainingUsage.tutorialEntryCount_ = i;
    }

    public static /* synthetic */ void access$500(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 4;
        trainingUsageProto$TrainingUsage.basicNavigationEntryCount_ = i;
    }

    public static /* synthetic */ void access$700(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 8;
        trainingUsageProto$TrainingUsage.textEditingEntryCount_ = i;
    }

    public static /* synthetic */ void access$900(TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage, int i) {
        trainingUsageProto$TrainingUsage.bitField0_ |= 16;
        trainingUsageProto$TrainingUsage.readingNavigationEntryCount_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\r\u0000\u0001\u0001\u000e\r\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006\bင\u0007\tင\b\nင\t\u000bင\n\fင\u000b\u000e\u001b", new Object[]{"bitField0_", "onboardingEntryCount_", "tutorialEntryCount_", "basicNavigationEntryCount_", "textEditingEntryCount_", "readingNavigationEntryCount_", "voiceCommandEntryCount_", "everydayTaskEntryCount_", "practiceGestureEntryCount_", "buttonNextCount_", "buttonPreviousCount_", "buttonCloseCount_", "buttonTurnOffTalkbackCount_", "trainingMetricWrapper_", TrainingUsageProto$TrainingMetricWrapper.class});
            case NEW_MUTABLE_INSTANCE:
                return new TrainingUsageProto$TrainingUsage();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (boolean[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TrainingUsageProto$TrainingUsage.class) {
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
