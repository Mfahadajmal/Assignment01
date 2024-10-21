package com.google.scone.proto;

import com.google.android.accessibility.talkback.analytics.TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog;
import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$DisplaySettings extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$DisplaySettings DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public static final Internal.IntListAdapter.IntConverter allowedPromptStyle_converter_ = new TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.AnonymousClass4(5);
    public Internal.IntList allowedPromptStyle_ = emptyIntList();
    public int bitField0_;
    public PromptDelay promptDelay_;
    public int surveyLogo_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PromptDelay extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final PromptDelay DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private int bitField0_;
        public Duration maxDelay_;
        public Duration minDelay_;

        static {
            PromptDelay promptDelay = new PromptDelay();
            DEFAULT_INSTANCE = promptDelay;
            GeneratedMessageLite.registerDefaultInstance(PromptDelay.class, promptDelay);
        }

        private PromptDelay() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "minDelay_", "maxDelay_"});
                case NEW_MUTABLE_INSTANCE:
                    return new PromptDelay();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (char[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (PromptDelay.class) {
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
    public enum PromptStyle implements Internal.EnumLite {
        PROMPT_STYLE_UNKNOWN(0),
        PROMPT_STYLE_FIRST_CARD_NON_MODAL(1),
        PROMPT_STYLE_FIRST_CARD_MODAL(2),
        UNRECOGNIZED(-1);

        private final int value;

        PromptStyle(int i) {
            this.value = i;
        }

        public static PromptStyle forNumber(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        return null;
                    }
                    return PROMPT_STYLE_FIRST_CARD_MODAL;
                }
                return PROMPT_STYLE_FIRST_CARD_NON_MODAL;
            }
            return PROMPT_STYLE_UNKNOWN;
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SurveyLogo {
        public static final int SURVEY_LOGO_UNKNOWN$ar$edu = 2;
        public static final int SURVEY_LOGO_NONE$ar$edu = 3;
        public static final int SURVEY_LOGO_STANDARD$ar$edu = 4;
        public static final int SURVEY_LOGO_CUSTOM$ar$edu = 5;
        public static final int UNRECOGNIZED$ar$edu$7a93a6b4_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$e3f56d28_0 = {SURVEY_LOGO_UNKNOWN$ar$edu, SURVEY_LOGO_NONE$ar$edu, SURVEY_LOGO_STANDARD$ar$edu, SURVEY_LOGO_CUSTOM$ar$edu, UNRECOGNIZED$ar$edu$7a93a6b4_0};

        public static int forNumber$ar$edu$b0b542e5_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return 0;
                        }
                        return SURVEY_LOGO_CUSTOM$ar$edu;
                    }
                    return SURVEY_LOGO_STANDARD$ar$edu;
                }
                return SURVEY_LOGO_NONE$ar$edu;
            }
            return SURVEY_LOGO_UNKNOWN$ar$edu;
        }

        public static int[] values$ar$edu$f325b4eb_0() {
            return new int[]{SURVEY_LOGO_UNKNOWN$ar$edu, SURVEY_LOGO_NONE$ar$edu, SURVEY_LOGO_STANDARD$ar$edu, SURVEY_LOGO_CUSTOM$ar$edu, UNRECOGNIZED$ar$edu$7a93a6b4_0};
        }
    }

    static {
        Survey$DisplaySettings survey$DisplaySettings = new Survey$DisplaySettings();
        DEFAULT_INSTANCE = survey$DisplaySettings;
        GeneratedMessageLite.registerDefaultInstance(Survey$DisplaySettings.class, survey$DisplaySettings);
    }

    private Survey$DisplaySettings() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0001\u0000\u0001ဉ\u0000\u0003\f\u0004,", new Object[]{"bitField0_", "promptDelay_", "surveyLogo_", "allowedPromptStyle_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$DisplaySettings();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$DisplaySettings.class) {
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
