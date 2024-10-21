package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackSelectorProto$TalkBackSelector extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackSelectorProto$TalkBackSelector DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public Internal.ProtobufList selectorEntities_ = emptyProtobufList();
    private int selectorSettingReached_;
    private int selectorSettingsMovingCount_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SelectorEntity extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SelectorEntity DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private int bitField0_;
        private int count_;
        private int selectorItem_;

        static {
            SelectorEntity selectorEntity = new SelectorEntity();
            DEFAULT_INSTANCE = selectorEntity;
            GeneratedMessageLite.registerDefaultInstance(SelectorEntity.class, selectorEntity);
        }

        private SelectorEntity() {
        }

        public static /* synthetic */ void access$100$ar$edu$3ea9e5e3_0(SelectorEntity selectorEntity, int i) {
            if (i != 0) {
                selectorEntity.selectorItem_ = i - 1;
                selectorEntity.bitField0_ |= 1;
                return;
            }
            throw null;
        }

        public static /* synthetic */ void access$300(SelectorEntity selectorEntity, int i) {
            selectorEntity.bitField0_ |= 2;
            selectorEntity.count_ = i;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "selectorItem_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$18, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SelectorEntity();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null, (byte[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SelectorEntity.class) {
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
        TalkBackSelectorProto$TalkBackSelector talkBackSelectorProto$TalkBackSelector = new TalkBackSelectorProto$TalkBackSelector();
        DEFAULT_INSTANCE = talkBackSelectorProto$TalkBackSelector;
        GeneratedMessageLite.registerDefaultInstance(TalkBackSelectorProto$TalkBackSelector.class, talkBackSelectorProto$TalkBackSelector);
    }

    private TalkBackSelectorProto$TalkBackSelector() {
    }

    public static /* synthetic */ void access$1300(TalkBackSelectorProto$TalkBackSelector talkBackSelectorProto$TalkBackSelector, int i) {
        talkBackSelectorProto$TalkBackSelector.bitField0_ |= 1;
        talkBackSelectorProto$TalkBackSelector.selectorSettingsMovingCount_ = i;
    }

    public static /* synthetic */ void access$1500(TalkBackSelectorProto$TalkBackSelector talkBackSelectorProto$TalkBackSelector, int i) {
        talkBackSelectorProto$TalkBackSelector.bitField0_ |= 2;
        talkBackSelectorProto$TalkBackSelector.selectorSettingReached_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002င\u0000\u0003င\u0001", new Object[]{"bitField0_", "selectorEntities_", SelectorEntity.class, "selectorSettingsMovingCount_", "selectorSettingReached_"});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackSelectorProto$TalkBackSelector();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (char[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackSelectorProto$TalkBackSelector.class) {
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
