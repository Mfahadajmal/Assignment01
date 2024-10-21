package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackSettingChangesProto$TalkBackSettingChanges extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackSettingChangesProto$TalkBackSettingChanges DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList settingChangeEntities_ = emptyProtobufList();
    public Internal.ProtobufList settingExtChangeEntities_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SettingChangeEntity extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SettingChangeEntity DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public TalkBackSettingsProto$TalkBackSettings changedSetting_;
        public int count_;
        public int userActionType_;

        static {
            SettingChangeEntity settingChangeEntity = new SettingChangeEntity();
            DEFAULT_INSTANCE = settingChangeEntity;
            GeneratedMessageLite.registerDefaultInstance(SettingChangeEntity.class, settingChangeEntity);
        }

        private SettingChangeEntity() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003င\u0002", new Object[]{"bitField0_", "changedSetting_", "userActionType_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SettingChangeEntity();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (byte[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SettingChangeEntity.class) {
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
    public final class SettingExtChangeEntity extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SettingExtChangeEntity DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public TalkBackSettingsExtProto$TalkBackSettingsExt changedSetting_;
        public int count_;
        public int userActionType_;

        static {
            SettingExtChangeEntity settingExtChangeEntity = new SettingExtChangeEntity();
            DEFAULT_INSTANCE = settingExtChangeEntity;
            GeneratedMessageLite.registerDefaultInstance(SettingExtChangeEntity.class, settingExtChangeEntity);
        }

        private SettingExtChangeEntity() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003င\u0002", new Object[]{"bitField0_", "changedSetting_", "userActionType_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SettingExtChangeEntity();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (byte[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SettingExtChangeEntity.class) {
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
        TalkBackSettingChangesProto$TalkBackSettingChanges talkBackSettingChangesProto$TalkBackSettingChanges = new TalkBackSettingChangesProto$TalkBackSettingChanges();
        DEFAULT_INSTANCE = talkBackSettingChangesProto$TalkBackSettingChanges;
        GeneratedMessageLite.registerDefaultInstance(TalkBackSettingChangesProto$TalkBackSettingChanges.class, talkBackSettingChangesProto$TalkBackSettingChanges);
    }

    private TalkBackSettingChangesProto$TalkBackSettingChanges() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"settingChangeEntities_", SettingChangeEntity.class, "settingExtChangeEntities_", SettingExtChangeEntity.class});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackSettingChangesProto$TalkBackSettingChanges();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (char[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackSettingChangesProto$TalkBackSettingChanges.class) {
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
