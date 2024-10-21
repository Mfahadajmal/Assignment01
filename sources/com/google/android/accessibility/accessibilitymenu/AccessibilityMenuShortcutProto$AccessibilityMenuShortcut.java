package com.google.android.accessibility.accessibilitymenu;

import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityMenuShortcutProto$AccessibilityMenuShortcut extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final AccessibilityMenuShortcutProto$AccessibilityMenuShortcut DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList shortcutRecord_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ShortcutRecord extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final ShortcutRecord DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int count_;
        public int shortcutId_;

        static {
            ShortcutRecord shortcutRecord = new ShortcutRecord();
            DEFAULT_INSTANCE = shortcutRecord;
            GeneratedMessageLite.registerDefaultInstance(ShortcutRecord.class, shortcutRecord);
        }

        private ShortcutRecord() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "shortcutId_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.INSTANCE, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ShortcutRecord();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (char[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ShortcutRecord.class) {
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
        AccessibilityMenuShortcutProto$AccessibilityMenuShortcut accessibilityMenuShortcutProto$AccessibilityMenuShortcut = new AccessibilityMenuShortcutProto$AccessibilityMenuShortcut();
        DEFAULT_INSTANCE = accessibilityMenuShortcutProto$AccessibilityMenuShortcut;
        GeneratedMessageLite.registerDefaultInstance(AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.class, accessibilityMenuShortcutProto$AccessibilityMenuShortcut);
    }

    private AccessibilityMenuShortcutProto$AccessibilityMenuShortcut() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"shortcutRecord_", ShortcutRecord.class});
            case NEW_MUTABLE_INSTANCE:
                return new AccessibilityMenuShortcutProto$AccessibilityMenuShortcut();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (char[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.class) {
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
