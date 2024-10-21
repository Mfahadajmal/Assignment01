package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkbackContextMenuActionsProto$TalkbackContextMenuActions extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkbackContextMenuActionsProto$TalkbackContextMenuActions DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public Internal.ProtobufList contextMenuActionEntities_ = emptyProtobufList();
    private int listGlobalMenuOpenCount_;
    private int listLocalMenuOpenCount_;
    private int radialGlobalMenuOpenCount_;
    private int radialLocalMenuOpenCount_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ContextMenuActions extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final ContextMenuActions DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int contextMenuAction_;
        public int count_;

        static {
            ContextMenuActions contextMenuActions = new ContextMenuActions();
            DEFAULT_INSTANCE = contextMenuActions;
            GeneratedMessageLite.registerDefaultInstance(ContextMenuActions.class, contextMenuActions);
        }

        private ContextMenuActions() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "contextMenuAction_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ContextMenuActions();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (char[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ContextMenuActions.class) {
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
        TalkbackContextMenuActionsProto$TalkbackContextMenuActions talkbackContextMenuActionsProto$TalkbackContextMenuActions = new TalkbackContextMenuActionsProto$TalkbackContextMenuActions();
        DEFAULT_INSTANCE = talkbackContextMenuActionsProto$TalkbackContextMenuActions;
        GeneratedMessageLite.registerDefaultInstance(TalkbackContextMenuActionsProto$TalkbackContextMenuActions.class, talkbackContextMenuActionsProto$TalkbackContextMenuActions);
    }

    private TalkbackContextMenuActionsProto$TalkbackContextMenuActions() {
    }

    public static /* synthetic */ void access$1300(TalkbackContextMenuActionsProto$TalkbackContextMenuActions talkbackContextMenuActionsProto$TalkbackContextMenuActions, int i) {
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.bitField0_ |= 1;
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.listGlobalMenuOpenCount_ = i;
    }

    public static /* synthetic */ void access$1500(TalkbackContextMenuActionsProto$TalkbackContextMenuActions talkbackContextMenuActionsProto$TalkbackContextMenuActions, int i) {
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.bitField0_ |= 2;
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.listLocalMenuOpenCount_ = i;
    }

    public static /* synthetic */ void access$1700(TalkbackContextMenuActionsProto$TalkbackContextMenuActions talkbackContextMenuActionsProto$TalkbackContextMenuActions, int i) {
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.bitField0_ |= 4;
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.radialGlobalMenuOpenCount_ = i;
    }

    public static /* synthetic */ void access$1900(TalkbackContextMenuActionsProto$TalkbackContextMenuActions talkbackContextMenuActionsProto$TalkbackContextMenuActions, int i) {
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.bitField0_ |= 8;
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.radialLocalMenuOpenCount_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002င\u0000\u0003င\u0001\u0004င\u0002\u0005င\u0003", new Object[]{"bitField0_", "contextMenuActionEntities_", ContextMenuActions.class, "listGlobalMenuOpenCount_", "listLocalMenuOpenCount_", "radialGlobalMenuOpenCount_", "radialLocalMenuOpenCount_"});
            case NEW_MUTABLE_INSTANCE:
                return new TalkbackContextMenuActionsProto$TalkbackContextMenuActions();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkbackContextMenuActionsProto$TalkbackContextMenuActions.class) {
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
