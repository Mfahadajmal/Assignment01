package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackGeminiProto$TalkBackGemini extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackGeminiProto$TalkBackGemini DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public GeminiFailCount geminiFailCount_;
    public int geminiOptInConsentCount_;
    public int geminiOptInDissentCount_;
    public int geminiOptInShownCount_;
    public int geminiRequestCount_;
    public int geminiSuccessCount_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GeminiFailCount extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final GeminiFailCount DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public Internal.ProtobufList geminiFailReasonCount_ = emptyProtobufList();

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class GeminiFailReasonCount extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final GeminiFailReasonCount DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public int bitField0_;
            public int count_;
            public int geminiFailReason_;

            static {
                GeminiFailReasonCount geminiFailReasonCount = new GeminiFailReasonCount();
                DEFAULT_INSTANCE = geminiFailReasonCount;
                GeneratedMessageLite.registerDefaultInstance(GeminiFailReasonCount.class, geminiFailReasonCount);
            }

            private GeminiFailReasonCount() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "geminiFailReason_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.INSTANCE, "count_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new GeminiFailReasonCount();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (byte[]) null, (short[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (GeminiFailReasonCount.class) {
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
            GeminiFailCount geminiFailCount = new GeminiFailCount();
            DEFAULT_INSTANCE = geminiFailCount;
            GeneratedMessageLite.registerDefaultInstance(GeminiFailCount.class, geminiFailCount);
        }

        private GeminiFailCount() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"geminiFailReasonCount_", GeminiFailReasonCount.class});
                case NEW_MUTABLE_INSTANCE:
                    return new GeminiFailCount();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (byte[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (GeminiFailCount.class) {
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
        TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini = new TalkBackGeminiProto$TalkBackGemini();
        DEFAULT_INSTANCE = talkBackGeminiProto$TalkBackGemini;
        GeneratedMessageLite.registerDefaultInstance(TalkBackGeminiProto$TalkBackGemini.class, talkBackGeminiProto$TalkBackGemini);
    }

    private TalkBackGeminiProto$TalkBackGemini() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003ဉ\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005", new Object[]{"bitField0_", "geminiRequestCount_", "geminiSuccessCount_", "geminiFailCount_", "geminiOptInConsentCount_", "geminiOptInDissentCount_", "geminiOptInShownCount_"});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackGeminiProto$TalkBackGemini();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (char[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackGeminiProto$TalkBackGemini.class) {
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
