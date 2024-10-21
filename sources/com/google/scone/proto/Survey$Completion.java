package com.google.scone.proto;

import com.google.android.accessibility.talkback.analytics.TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$Completion extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$Completion DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public static final Internal.IntListAdapter.IntConverter allowedCompletionStyle_converter_ = new TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.AnonymousClass4(4);
    public String completionText_ = "";
    public String followUpText_ = "";
    public String followUpUrl_ = "";
    public Internal.IntList allowedCompletionStyle_ = emptyIntList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum CompletionStyle implements Internal.EnumLite {
        COMPLETION_STYLE_UNKNOWN(0),
        COMPLETION_STYLE_CARD(1),
        COMPLETION_STYLE_TOAST(2),
        UNRECOGNIZED(-1);

        private final int value;

        CompletionStyle(int i) {
            this.value = i;
        }

        public static CompletionStyle forNumber(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        return null;
                    }
                    return COMPLETION_STYLE_TOAST;
                }
                return COMPLETION_STYLE_CARD;
            }
            return COMPLETION_STYLE_UNKNOWN;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Override // java.lang.Enum
        public final String toString() {
            return Integer.toString(getNumber());
        }
    }

    static {
        Survey$Completion survey$Completion = new Survey$Completion();
        DEFAULT_INSTANCE = survey$Completion;
        GeneratedMessageLite.registerDefaultInstance(Survey$Completion.class, survey$Completion);
    }

    private Survey$Completion() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004,", new Object[]{"completionText_", "followUpText_", "followUpUrl_", "allowedCompletionStyle_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$Completion();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$Completion.class) {
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
