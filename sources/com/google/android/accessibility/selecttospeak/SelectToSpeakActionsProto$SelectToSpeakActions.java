package com.google.android.accessibility.selecttospeak;

import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakActionsProto$SelectToSpeakActions extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SelectToSpeakActionsProto$SelectToSpeakActions DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList actionsRecord_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ActionsRecord extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final ActionsRecord DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int actions_;
        public int bitField0_;
        public int count_;

        static {
            ActionsRecord actionsRecord = new ActionsRecord();
            DEFAULT_INSTANCE = actionsRecord;
            GeneratedMessageLite.registerDefaultInstance(ActionsRecord.class, actionsRecord);
        }

        private ActionsRecord() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "actions_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$13, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ActionsRecord();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ActionsRecord.class) {
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
        SelectToSpeakActionsProto$SelectToSpeakActions selectToSpeakActionsProto$SelectToSpeakActions = new SelectToSpeakActionsProto$SelectToSpeakActions();
        DEFAULT_INSTANCE = selectToSpeakActionsProto$SelectToSpeakActions;
        GeneratedMessageLite.registerDefaultInstance(SelectToSpeakActionsProto$SelectToSpeakActions.class, selectToSpeakActionsProto$SelectToSpeakActions);
    }

    private SelectToSpeakActionsProto$SelectToSpeakActions() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"actionsRecord_", ActionsRecord.class});
            case NEW_MUTABLE_INSTANCE:
                return new SelectToSpeakActionsProto$SelectToSpeakActions();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SelectToSpeakActionsProto$SelectToSpeakActions.class) {
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
