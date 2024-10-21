package com.google.android.accessibility.brailleime.analytics;

import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeLogProto$BrailleImeExtension extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BrailleImeLogProto$BrailleImeExtension DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int accumulatedModeV2_;
    public int bitField0_;
    public int countOfTypingBrailleCharacters_;
    public int errorType_;
    public int keyboardSessionSeconds_;
    public Language language_;
    public int layoutMode_;
    public int reverseDotsModeV2_;
    public TutorialFinishEvent tutorialFinish_;
    public Internal.ProtobufList action_ = emptyProtobufList();
    public Internal.ProtobufList settingsRecord_ = emptyProtobufList();
    public Internal.ProtobufList contextMenuRecord_ = emptyProtobufList();
    public Internal.ProtobufList calibrationRecord_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ContextMenuRecord extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final ContextMenuRecord DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int count_;
        public int selectOption_;

        static {
            ContextMenuRecord contextMenuRecord = new ContextMenuRecord();
            DEFAULT_INSTANCE = contextMenuRecord;
            GeneratedMessageLite.registerDefaultInstance(ContextMenuRecord.class, contextMenuRecord);
        }

        private ContextMenuRecord() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "selectOption_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$4, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ContextMenuRecord();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ContextMenuRecord.class) {
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
    public final class GestureAction extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final GestureAction DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int count_;
        public int type_;

        static {
            GestureAction gestureAction = new GestureAction();
            DEFAULT_INSTANCE = gestureAction;
            GeneratedMessageLite.registerDefaultInstance(GestureAction.class, gestureAction);
        }

        private GestureAction() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "type_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$6, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new GestureAction();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (GestureAction.class) {
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
    public final class Language extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final Language DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int code_;
        public boolean contracted_;
        public int translator_;

        static {
            Language language = new Language();
            DEFAULT_INSTANCE = language;
            GeneratedMessageLite.registerDefaultInstance(Language.class, language);
        }

        private Language() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003᠌\u0002", new Object[]{"bitField0_", "code_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$7, "contracted_", "translator_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$11});
                case NEW_MUTABLE_INSTANCE:
                    return new Language();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (Language.class) {
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
    public final class SettingsRecord extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SettingsRecord DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int count_;
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
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "settings_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$9, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SettingsRecord();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TutorialFinishEvent extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final TutorialFinishEvent DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public boolean autoLaunchTutorial_;
        public int bitField0_;
        public int finishState_;

        static {
            TutorialFinishEvent tutorialFinishEvent = new TutorialFinishEvent();
            DEFAULT_INSTANCE = tutorialFinishEvent;
            GeneratedMessageLite.registerDefaultInstance(TutorialFinishEvent.class, tutorialFinishEvent);
        }

        private TutorialFinishEvent() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001", new Object[]{"bitField0_", "finishState_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$12, "autoLaunchTutorial_"});
                case NEW_MUTABLE_INSTANCE:
                    return new TutorialFinishEvent();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (TutorialFinishEvent.class) {
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
        BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension = new BrailleImeLogProto$BrailleImeExtension();
        DEFAULT_INSTANCE = brailleImeLogProto$BrailleImeExtension;
        GeneratedMessageLite.registerDefaultInstance(BrailleImeLogProto$BrailleImeExtension.class, brailleImeLogProto$BrailleImeExtension);
    }

    private BrailleImeLogProto$BrailleImeExtension() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                Internal.EnumVerifier enumVerifier = A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$10;
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\f\u0000\u0001\u0002\u000f\f\u0000\u0004\u0000\u0002ဉ\u0001\u0003င\u0002\u0005င\u0004\u0006\u001b\b\u001b\t᠌\u0006\nဉ\u0007\u000b\u001b\f᠌\b\r\u001b\u000e᠌\t\u000f᠌\n", new Object[]{"bitField0_", "language_", "countOfTypingBrailleCharacters_", "keyboardSessionSeconds_", "action_", GestureAction.class, "settingsRecord_", SettingsRecord.class, "errorType_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$5, "tutorialFinish_", "contextMenuRecord_", ContextMenuRecord.class, "layoutMode_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$8, "calibrationRecord_", BrailleImeLogProto$CalibrationRecord.class, "accumulatedModeV2_", enumVerifier, "reverseDotsModeV2_", enumVerifier});
            case NEW_MUTABLE_INSTANCE:
                return new BrailleImeLogProto$BrailleImeExtension();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BrailleImeLogProto$BrailleImeExtension.class) {
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
