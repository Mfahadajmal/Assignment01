package com.google.android.accessibility.selecttospeak;

import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakWordCountsProto$SelectToSpeakWordCounts extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SelectToSpeakWordCountsProto$SelectToSpeakWordCounts DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList wordCountsRecord_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WordCountsRecord extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final WordCountsRecord DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int entry_;
        public int usageCount_;
        public int wordCount_;

        static {
            WordCountsRecord wordCountsRecord = new WordCountsRecord();
            DEFAULT_INSTANCE = wordCountsRecord;
            GeneratedMessageLite.registerDefaultInstance(WordCountsRecord.class, wordCountsRecord);
        }

        private WordCountsRecord() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"bitField0_", "entry_", A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.class_merging$INSTANCE$14, "wordCount_", "usageCount_"});
                case NEW_MUTABLE_INSTANCE:
                    return new WordCountsRecord();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (WordCountsRecord.class) {
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
        SelectToSpeakWordCountsProto$SelectToSpeakWordCounts selectToSpeakWordCountsProto$SelectToSpeakWordCounts = new SelectToSpeakWordCountsProto$SelectToSpeakWordCounts();
        DEFAULT_INSTANCE = selectToSpeakWordCountsProto$SelectToSpeakWordCounts;
        GeneratedMessageLite.registerDefaultInstance(SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.class, selectToSpeakWordCountsProto$SelectToSpeakWordCounts);
    }

    private SelectToSpeakWordCountsProto$SelectToSpeakWordCounts() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"wordCountsRecord_", WordCountsRecord.class});
            case NEW_MUTABLE_INSTANCE:
                return new SelectToSpeakWordCountsProto$SelectToSpeakWordCounts();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder(null, null, null, null, null, null, null, null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.class) {
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
