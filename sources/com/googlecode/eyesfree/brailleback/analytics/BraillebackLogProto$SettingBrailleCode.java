package com.googlecode.eyesfree.brailleback.analytics;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BraillebackLogProto$SettingBrailleCode extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BraillebackLogProto$SettingBrailleCode DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int code_;
    public boolean contracted_;
    public String locale_ = "";
    public int translator_;

    static {
        BraillebackLogProto$SettingBrailleCode braillebackLogProto$SettingBrailleCode = new BraillebackLogProto$SettingBrailleCode();
        DEFAULT_INSTANCE = braillebackLogProto$SettingBrailleCode;
        GeneratedMessageLite.registerDefaultInstance(BraillebackLogProto$SettingBrailleCode.class, braillebackLogProto$SettingBrailleCode);
    }

    private BraillebackLogProto$SettingBrailleCode() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003᠌\u0002\u0004ဇ\u0003", new Object[]{"bitField0_", "code_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$13, "locale_", "translator_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$16, "contracted_"});
            case NEW_MUTABLE_INSTANCE:
                return new BraillebackLogProto$SettingBrailleCode();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BraillebackLogProto$SettingBrailleCode.class) {
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
