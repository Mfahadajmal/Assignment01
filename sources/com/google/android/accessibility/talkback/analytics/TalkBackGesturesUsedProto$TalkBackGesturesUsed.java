package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackGesturesUsedProto$TalkBackGesturesUsed extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackGesturesUsedProto$TalkBackGesturesUsed DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList talkbackGestureUsed_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TalkBackGesturesUsedEntry extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final TalkBackGesturesUsedEntry DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int count_;
        public int gestureId_;

        static {
            TalkBackGesturesUsedEntry talkBackGesturesUsedEntry = new TalkBackGesturesUsedEntry();
            DEFAULT_INSTANCE = talkBackGesturesUsedEntry;
            GeneratedMessageLite.registerDefaultInstance(TalkBackGesturesUsedEntry.class, talkBackGesturesUsedEntry);
        }

        private TalkBackGesturesUsedEntry() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"bitField0_", "gestureId_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$1, "count_"});
                case NEW_MUTABLE_INSTANCE:
                    return new TalkBackGesturesUsedEntry();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((boolean[][]) null, (byte[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (TalkBackGesturesUsedEntry.class) {
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
        TalkBackGesturesUsedProto$TalkBackGesturesUsed talkBackGesturesUsedProto$TalkBackGesturesUsed = new TalkBackGesturesUsedProto$TalkBackGesturesUsed();
        DEFAULT_INSTANCE = talkBackGesturesUsedProto$TalkBackGesturesUsed;
        GeneratedMessageLite.registerDefaultInstance(TalkBackGesturesUsedProto$TalkBackGesturesUsed.class, talkBackGesturesUsedProto$TalkBackGesturesUsed);
    }

    private TalkBackGesturesUsedProto$TalkBackGesturesUsed() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"talkbackGestureUsed_", TalkBackGesturesUsedEntry.class});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackGesturesUsedProto$TalkBackGesturesUsed();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (short[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackGesturesUsedProto$TalkBackGesturesUsed.class) {
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
