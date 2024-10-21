package com.google.android.accessibility.selecttospeak;

import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakSettingsProto$SelectToSpeakSettings extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SelectToSpeakSettingsProto$SelectToSpeakSettings DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList settingsRecord_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SettingsRecord extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SettingsRecord DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public boolean enabled_;
        public int settings_;

        static {
            SettingsRecord settingsRecord = new SettingsRecord();
            DEFAULT_INSTANCE = settingsRecord;
            GeneratedMessageLite.registerDefaultInstance(SettingsRecord.class, settingsRecord);
        }

        private SettingsRecord() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001", new Object[]{"bitField0_", "settings_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$15, "enabled_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SettingsRecord();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SettingsRecord.class) {
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
        SelectToSpeakSettingsProto$SelectToSpeakSettings selectToSpeakSettingsProto$SelectToSpeakSettings = new SelectToSpeakSettingsProto$SelectToSpeakSettings();
        DEFAULT_INSTANCE = selectToSpeakSettingsProto$SelectToSpeakSettings;
        GeneratedMessageLite.registerDefaultInstance(SelectToSpeakSettingsProto$SelectToSpeakSettings.class, selectToSpeakSettingsProto$SelectToSpeakSettings);
    }

    private SelectToSpeakSettingsProto$SelectToSpeakSettings() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"settingsRecord_", SettingsRecord.class});
            case NEW_MUTABLE_INSTANCE:
                return new SelectToSpeakSettingsProto$SelectToSpeakSettings();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SelectToSpeakSettingsProto$SelectToSpeakSettings.class) {
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
