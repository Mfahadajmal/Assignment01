package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ShortcutActionsProto$GestureShortcutActions extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ShortcutActionsProto$GestureShortcutActions DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList gestureShortcutEntity_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GestureShortcutEntity extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final GestureShortcutEntity DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private int action_;
        private int bitField0_;
        private int count_;

        static {
            GestureShortcutEntity gestureShortcutEntity = new GestureShortcutEntity();
            DEFAULT_INSTANCE = gestureShortcutEntity;
            GeneratedMessageLite.registerDefaultInstance(GestureShortcutEntity.class, gestureShortcutEntity);
        }

        private GestureShortcutEntity() {
        }

        public static /* synthetic */ void access$100$ar$edu(GestureShortcutEntity gestureShortcutEntity, int i) {
            if (i != 0) {
                gestureShortcutEntity.action_ = i - 1;
                gestureShortcutEntity.bitField0_ |= 1;
                return;
            }
            throw null;
        }

        public static /* synthetic */ void access$300(GestureShortcutEntity gestureShortcutEntity, int i) {
            gestureShortcutEntity.bitField0_ |= 2;
            gestureShortcutEntity.count_ = i;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "action_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$19, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new GestureShortcutEntity();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (char[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (GestureShortcutEntity.class) {
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
        ShortcutActionsProto$GestureShortcutActions shortcutActionsProto$GestureShortcutActions = new ShortcutActionsProto$GestureShortcutActions();
        DEFAULT_INSTANCE = shortcutActionsProto$GestureShortcutActions;
        GeneratedMessageLite.registerDefaultInstance(ShortcutActionsProto$GestureShortcutActions.class, shortcutActionsProto$GestureShortcutActions);
    }

    private ShortcutActionsProto$GestureShortcutActions() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"gestureShortcutEntity_", GestureShortcutEntity.class});
            case NEW_MUTABLE_INSTANCE:
                return new ShortcutActionsProto$GestureShortcutActions();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (short[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ShortcutActionsProto$GestureShortcutActions.class) {
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
