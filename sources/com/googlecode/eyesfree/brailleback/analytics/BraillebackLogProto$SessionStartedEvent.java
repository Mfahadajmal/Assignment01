package com.googlecode.eyesfree.brailleback.analytics;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BraillebackLogProto$SessionStartedEvent extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BraillebackLogProto$SessionStartedEvent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long bluetoothConnectingTimeMs_;
    public long brailleDisplayConnectingTimeMs_;
    public int connectProtocol_;
    public int connectType_;
    public BraillebackLogProto$DeviceInfo deviceInfo_;
    public long hidConnectingTimeMs_;
    public BraillebackLogProto$SettingBrailleCode settingBrailleInputCode_;
    public BraillebackLogProto$SettingBrailleCode settingBrailleOutputCode_;

    static {
        BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent = new BraillebackLogProto$SessionStartedEvent();
        DEFAULT_INSTANCE = braillebackLogProto$SessionStartedEvent;
        GeneratedMessageLite.registerDefaultInstance(BraillebackLogProto$SessionStartedEvent.class, braillebackLogProto$SessionStartedEvent);
    }

    private BraillebackLogProto$SessionStartedEvent() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\b\u0000\u0001\u0002\t\b\u0000\u0000\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006᠌\u0005\u0007ဂ\u0006\b᠌\u0007\tဉ\b", new Object[]{"bitField0_", "settingBrailleInputCode_", "settingBrailleOutputCode_", "bluetoothConnectingTimeMs_", "brailleDisplayConnectingTimeMs_", "connectType_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$12, "hidConnectingTimeMs_", "connectProtocol_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$11, "deviceInfo_"});
            case NEW_MUTABLE_INSTANCE:
                return new BraillebackLogProto$SessionStartedEvent();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BraillebackLogProto$SessionStartedEvent.class) {
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
