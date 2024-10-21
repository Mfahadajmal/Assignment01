package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.experiments.mobile.base.AndroidBacking;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.scone.proto.Survey$Completion;
import com.google.scone.proto.Survey$DisplaySettings;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public static final Internal.IntListAdapter.IntConverter keymapTypeChanged_converter_ = new AnonymousClass4(1);
    public static final Internal.IntListAdapter.IntConverter modifierKeyChanged_converter_ = new AnonymousClass4(0);
    public Internal.ProtobufList keyboardShortcutChangedEntities_;
    public Internal.ProtobufList keyboardShortcutUsedEntities_;
    public Internal.IntList keymapTypeUsed_ = emptyIntList();
    public Internal.IntList keymapTypeChanged_ = emptyIntList();
    public Internal.IntList modifierKeyUsed_ = emptyIntList();
    public Internal.IntList modifierKeyChanged_ = emptyIntList();

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.analytics.TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog$4, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 implements Internal.IntListAdapter.IntConverter {
        private final /* synthetic */ int switching_field;

        public AnonymousClass4(int i) {
            this.switching_field = i;
        }

        @Override // com.google.protobuf.Internal.IntListAdapter.IntConverter
        public final /* synthetic */ Object convert(int i) {
            int i2 = this.switching_field;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                Survey$DisplaySettings.PromptStyle forNumber = Survey$DisplaySettings.PromptStyle.forNumber(i);
                                if (forNumber == null) {
                                    return Survey$DisplaySettings.PromptStyle.UNRECOGNIZED;
                                }
                                return forNumber;
                            }
                            Survey$Completion.CompletionStyle forNumber2 = Survey$Completion.CompletionStyle.forNumber(i);
                            if (forNumber2 == null) {
                                return Survey$Completion.CompletionStyle.UNRECOGNIZED;
                            }
                            return forNumber2;
                        }
                        AndroidBacking forNumber3 = AndroidBacking.forNumber(i);
                        if (forNumber3 == null) {
                            return AndroidBacking.UNKNOWN;
                        }
                        return forNumber3;
                    }
                    AndroidBacking forNumber4 = AndroidBacking.forNumber(i);
                    if (forNumber4 == null) {
                        return AndroidBacking.UNKNOWN;
                    }
                    return forNumber4;
                }
                TalkBackSettingEnums$KeymapType forNumber5 = TalkBackSettingEnums$KeymapType.forNumber(i);
                if (forNumber5 == null) {
                    return TalkBackSettingEnums$KeymapType.KEYMAP_DEFAULT;
                }
                return forNumber5;
            }
            TalkBackSettingEnums$ModifierKey forNumber6 = TalkBackSettingEnums$ModifierKey.forNumber(i);
            if (forNumber6 == null) {
                return TalkBackSettingEnums$ModifierKey.MODIFIER_KEY_ALT;
            }
            return forNumber6;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class KeyboardShortcutEntity extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final KeyboardShortcutEntity DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public Internal.IntList keyCombination_ = emptyIntList();
        public int keyboardShortcut_;
        public int modifierKey_;

        static {
            KeyboardShortcutEntity keyboardShortcutEntity = new KeyboardShortcutEntity();
            DEFAULT_INSTANCE = keyboardShortcutEntity;
            GeneratedMessageLite.registerDefaultInstance(KeyboardShortcutEntity.class, keyboardShortcutEntity);
        }

        private KeyboardShortcutEntity() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003'", new Object[]{"bitField0_", "keyboardShortcut_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$9, "modifierKey_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$12, "keyCombination_"});
                case NEW_MUTABLE_INSTANCE:
                    return new KeyboardShortcutEntity();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (byte[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (KeyboardShortcutEntity.class) {
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
        TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog = new TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog();
        DEFAULT_INSTANCE = talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog;
        GeneratedMessageLite.registerDefaultInstance(TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.class, talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog);
    }

    private TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog() {
        emptyIntList();
        this.keyboardShortcutChangedEntities_ = emptyProtobufList();
        this.keyboardShortcutUsedEntities_ = emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                Internal.EnumVerifier enumVerifier = TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$10;
                Internal.EnumVerifier enumVerifier2 = TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$12;
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0000\u0001\u0007\u0006\u0000\u0006\u0000\u0001ࠬ\u0002ࠬ\u0003ࠬ\u0004ࠬ\u0006\u001b\u0007\u001b", new Object[]{"keymapTypeUsed_", enumVerifier, "keymapTypeChanged_", enumVerifier, "modifierKeyUsed_", enumVerifier2, "modifierKeyChanged_", enumVerifier2, "keyboardShortcutChangedEntities_", KeyboardShortcutEntity.class, "keyboardShortcutUsedEntities_", KeyboardShortcutEntity.class});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (short[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.class) {
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
