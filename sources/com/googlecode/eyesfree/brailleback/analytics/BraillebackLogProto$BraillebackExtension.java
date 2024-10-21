package com.googlecode.eyesfree.brailleback.analytics;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BraillebackLogProto$BraillebackExtension extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BraillebackLogProto$BraillebackExtension DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int autoConnectV2_;
    public int bitField0_;
    public int blinkFrequencyMs_;
    public int brailleCommand_;
    public int changeTypingMode_;
    public int enablerV2_;
    public int inputRecord_;
    public int readRecord_;
    public int reversePanningV2_;
    public BraillebackLogProto$SessionStartedEvent sessionStartedEvent_;
    public BraillebackLogProto$SettingBrailleCode settingBrailleInputCode_;
    public BraillebackLogProto$SettingBrailleCode settingBrailleOutputCode_;
    public int timedMessageDurationMs_;

    static {
        BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = new BraillebackLogProto$BraillebackExtension();
        DEFAULT_INSTANCE = braillebackLogProto$BraillebackExtension;
        GeneratedMessageLite.registerDefaultInstance(BraillebackLogProto$BraillebackExtension.class, braillebackLogProto$BraillebackExtension);
    }

    private BraillebackLogProto$BraillebackExtension() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                Internal.EnumVerifier enumVerifier = VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$15;
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\f\u0000\u0001\u0001\u000f\f\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004င\u0003\u0005င\u0004\u0006᠌\u0005\tင\b\u000bင\n\fင\u000b\r᠌\f\u000e᠌\r\u000f᠌\u000e", new Object[]{"bitField0_", "sessionStartedEvent_", "settingBrailleInputCode_", "settingBrailleOutputCode_", "inputRecord_", "readRecord_", "changeTypingMode_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$14, "brailleCommand_", "blinkFrequencyMs_", "timedMessageDurationMs_", "autoConnectV2_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$15, "enablerV2_", enumVerifier, "reversePanningV2_", enumVerifier});
            case NEW_MUTABLE_INSTANCE:
                return new BraillebackLogProto$BraillebackExtension();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BraillebackLogProto$BraillebackExtension.class) {
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
