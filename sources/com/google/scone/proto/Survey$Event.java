package com.google.scone.proto;

import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$Event extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$Event DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int eventCase_ = 0;
    public Object event_;
    public Duration timeSinceTrigger_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EventCase {
        public static final int SURVEY_SHOWN$ar$edu = 1;
        public static final int SURVEY_ACCEPTED$ar$edu = 2;
        public static final int INVITATION_ANSWERED$ar$edu = 3;
        public static final int QUESTION_ANSWERED$ar$edu = 4;
        public static final int SURVEY_CLOSED$ar$edu = 5;
        public static final int EVENT_NOT_SET$ar$edu = 6;
        private static final /* synthetic */ int[] $VALUES$ar$edu$1f9a8e96_0 = {SURVEY_SHOWN$ar$edu, SURVEY_ACCEPTED$ar$edu, INVITATION_ANSWERED$ar$edu, QUESTION_ANSWERED$ar$edu, SURVEY_CLOSED$ar$edu, EVENT_NOT_SET$ar$edu};

        public static int[] values$ar$edu$af18a6e6_0() {
            return new int[]{SURVEY_SHOWN$ar$edu, SURVEY_ACCEPTED$ar$edu, INVITATION_ANSWERED$ar$edu, QUESTION_ANSWERED$ar$edu, SURVEY_CLOSED$ar$edu, EVENT_NOT_SET$ar$edu};
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InvitationAnswered extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final InvitationAnswered DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public boolean accepted_;

        static {
            InvitationAnswered invitationAnswered = new InvitationAnswered();
            DEFAULT_INSTANCE = invitationAnswered;
            GeneratedMessageLite.registerDefaultInstance(InvitationAnswered.class, invitationAnswered);
        }

        private InvitationAnswered() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007", new Object[]{"accepted_"});
                case NEW_MUTABLE_INSTANCE:
                    return new InvitationAnswered();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (char[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (InvitationAnswered.class) {
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
    public final class QuestionAnswered extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final QuestionAnswered DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int answerCase_ = 0;
        public Object answer_;
        public int questionOrdinal_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class AnswerCase {
            public static final int SINGLE_SELECTION$ar$edu = 1;
            public static final int MULTIPLE_SELECTION$ar$edu = 2;
            public static final int RATING$ar$edu = 3;
            public static final int OPEN_TEXT$ar$edu = 4;
            public static final int ANSWER_NOT_SET$ar$edu = 5;
            private static final /* synthetic */ int[] $VALUES$ar$edu$fcc66e9a_0 = {SINGLE_SELECTION$ar$edu, MULTIPLE_SELECTION$ar$edu, RATING$ar$edu, OPEN_TEXT$ar$edu, ANSWER_NOT_SET$ar$edu};

            public static int forNumber$ar$edu$f9ea4f52_0(int i) {
                if (i != 0) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return 0;
                                }
                                return OPEN_TEXT$ar$edu;
                            }
                            return RATING$ar$edu;
                        }
                        return MULTIPLE_SELECTION$ar$edu;
                    }
                    return SINGLE_SELECTION$ar$edu;
                }
                return ANSWER_NOT_SET$ar$edu;
            }

            public static int[] values$ar$edu$1a577467_0() {
                return new int[]{SINGLE_SELECTION$ar$edu, MULTIPLE_SELECTION$ar$edu, RATING$ar$edu, OPEN_TEXT$ar$edu, ANSWER_NOT_SET$ar$edu};
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class MultipleSelectAnswer extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final MultipleSelectAnswer DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public Internal.ProtobufList answer_ = emptyProtobufList();

            static {
                MultipleSelectAnswer multipleSelectAnswer = new MultipleSelectAnswer();
                DEFAULT_INSTANCE = multipleSelectAnswer;
                GeneratedMessageLite.registerDefaultInstance(MultipleSelectAnswer.class, multipleSelectAnswer);
            }

            private MultipleSelectAnswer() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"answer_", Selection.class});
                    case NEW_MUTABLE_INSTANCE:
                        return new MultipleSelectAnswer();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (MultipleSelectAnswer.class) {
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
        public final class OpenTextAnswer extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final OpenTextAnswer DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public String answer_ = "";

            static {
                OpenTextAnswer openTextAnswer = new OpenTextAnswer();
                DEFAULT_INSTANCE = openTextAnswer;
                GeneratedMessageLite.registerDefaultInstance(OpenTextAnswer.class, openTextAnswer);
            }

            private OpenTextAnswer() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"answer_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new OpenTextAnswer();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((boolean[][]) null, (char[]) null, (byte[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (OpenTextAnswer.class) {
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
        public final class RatingAnswer extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final RatingAnswer DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public Selection answer_;
            public int bitField0_;

            static {
                RatingAnswer ratingAnswer = new RatingAnswer();
                DEFAULT_INSTANCE = ratingAnswer;
                GeneratedMessageLite.registerDefaultInstance(RatingAnswer.class, ratingAnswer);
            }

            private RatingAnswer() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "answer_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new RatingAnswer();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((float[][]) null, (char[]) null, (byte[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (RatingAnswer.class) {
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
        public final class Selection extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final Selection DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public int answerOrdinal_;
            public int answerType_;
            public String text_ = "";

            /* compiled from: PG */
            /* loaded from: classes.dex */
            public final class AnswerType {
                public static final int ANSWER_TYPE_UNSPECIFIED$ar$edu$def17366_0 = 2;
                public static final int ANSWER_TYPE_TEXT$ar$edu$def17366_0 = 3;
                public static final int ANSWER_TYPE_WRITE_IN$ar$edu$def17366_0 = 4;
                public static final int ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu$def17366_0 = 5;
                public static final int UNRECOGNIZED$ar$edu$def17366_0 = 1;
                private static final /* synthetic */ int[] $VALUES$ar$edu$f23ac4f_0 = {ANSWER_TYPE_UNSPECIFIED$ar$edu$def17366_0, ANSWER_TYPE_TEXT$ar$edu$def17366_0, ANSWER_TYPE_WRITE_IN$ar$edu$def17366_0, ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu$def17366_0, UNRECOGNIZED$ar$edu$def17366_0};

                public static int getNumber$ar$edu$def17366_0(int i) {
                    if (i != UNRECOGNIZED$ar$edu$def17366_0) {
                        return i - 2;
                    }
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }

                public static int[] values$ar$edu$b9dbe2ec_0() {
                    return new int[]{ANSWER_TYPE_UNSPECIFIED$ar$edu$def17366_0, ANSWER_TYPE_TEXT$ar$edu$def17366_0, ANSWER_TYPE_WRITE_IN$ar$edu$def17366_0, ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu$def17366_0, UNRECOGNIZED$ar$edu$def17366_0};
                }
            }

            static {
                Selection selection = new Selection();
                DEFAULT_INSTANCE = selection;
                GeneratedMessageLite.registerDefaultInstance(Selection.class, selection);
            }

            private Selection() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\u000b\u0003Ȉ", new Object[]{"answerType_", "answerOrdinal_", "text_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new Selection();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (char[]) null, (byte[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (Selection.class) {
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
        public final class SingleSelectAnswer extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final SingleSelectAnswer DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public Selection answer_;
            public int bitField0_;

            static {
                SingleSelectAnswer singleSelectAnswer = new SingleSelectAnswer();
                DEFAULT_INSTANCE = singleSelectAnswer;
                GeneratedMessageLite.registerDefaultInstance(SingleSelectAnswer.class, singleSelectAnswer);
            }

            private SingleSelectAnswer() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "answer_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new SingleSelectAnswer();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null, (char[]) null, (byte[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (SingleSelectAnswer.class) {
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
            QuestionAnswered questionAnswered = new QuestionAnswered();
            DEFAULT_INSTANCE = questionAnswered;
            GeneratedMessageLite.registerDefaultInstance(QuestionAnswered.class, questionAnswered);
        }

        private QuestionAnswered() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u000b\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000", new Object[]{"answer_", "answerCase_", "questionOrdinal_", SingleSelectAnswer.class, MultipleSelectAnswer.class, RatingAnswer.class, OpenTextAnswer.class});
                case NEW_MUTABLE_INSTANCE:
                    return new QuestionAnswered();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[][]) null, (char[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (QuestionAnswered.class) {
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
    public final class SurveyAccepted extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SurveyAccepted DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public Survey$ProductContext productContext_;
        public Survey$SensitiveClientContext sensitiveClientContext_;

        static {
            SurveyAccepted surveyAccepted = new SurveyAccepted();
            DEFAULT_INSTANCE = surveyAccepted;
            GeneratedMessageLite.registerDefaultInstance(SurveyAccepted.class, surveyAccepted);
        }

        private SurveyAccepted() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0002\u0003\u0002\u0000\u0000\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "productContext_", "sensitiveClientContext_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SurveyAccepted();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (char[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SurveyAccepted.class) {
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
    public final class SurveyClosed extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SurveyClosed DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        static {
            SurveyClosed surveyClosed = new SurveyClosed();
            DEFAULT_INSTANCE = surveyClosed;
            GeneratedMessageLite.registerDefaultInstance(SurveyClosed.class, surveyClosed);
        }

        private SurveyClosed() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case NEW_MUTABLE_INSTANCE:
                    return new SurveyClosed();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (char[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SurveyClosed.class) {
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
    public final class SurveyShown extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final SurveyShown DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int surveyPromptType_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SurveyPromptType {
            public static final int SURVEY_PROMPT_TYPE_UNKNOWN$ar$edu = 2;
            public static final int SURVEY_PROMPT_TYPE_EMBEDDED$ar$edu = 3;
            public static final int SURVEY_PROMPT_TYPE_MODAL$ar$edu = 4;
            public static final int SURVEY_PROMPT_TYPE_NON_MODAL$ar$edu = 5;
            public static final int UNRECOGNIZED$ar$edu$a6ec0b40_0 = 1;
            private static final /* synthetic */ int[] $VALUES$ar$edu$bbbba612_0 = {SURVEY_PROMPT_TYPE_UNKNOWN$ar$edu, SURVEY_PROMPT_TYPE_EMBEDDED$ar$edu, SURVEY_PROMPT_TYPE_MODAL$ar$edu, SURVEY_PROMPT_TYPE_NON_MODAL$ar$edu, UNRECOGNIZED$ar$edu$a6ec0b40_0};

            public static int getNumber$ar$edu$a6ec0b40_0(int i) {
                if (i != UNRECOGNIZED$ar$edu$a6ec0b40_0) {
                    return i - 2;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            public static int[] values$ar$edu$68f8d9be_0() {
                return new int[]{SURVEY_PROMPT_TYPE_UNKNOWN$ar$edu, SURVEY_PROMPT_TYPE_EMBEDDED$ar$edu, SURVEY_PROMPT_TYPE_MODAL$ar$edu, SURVEY_PROMPT_TYPE_NON_MODAL$ar$edu, UNRECOGNIZED$ar$edu$a6ec0b40_0};
            }
        }

        static {
            SurveyShown surveyShown = new SurveyShown();
            DEFAULT_INSTANCE = surveyShown;
            GeneratedMessageLite.registerDefaultInstance(SurveyShown.class, surveyShown);
        }

        private SurveyShown() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဌ\u0000", new Object[]{"bitField0_", "surveyPromptType_"});
                case NEW_MUTABLE_INSTANCE:
                    return new SurveyShown();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (char[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (SurveyShown.class) {
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
        Survey$Event survey$Event = new Survey$Event();
        DEFAULT_INSTANCE = survey$Event;
        GeneratedMessageLite.registerDefaultInstance(Survey$Event.class, survey$Event);
    }

    private Survey$Event() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0001\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000", new Object[]{"event_", "eventCase_", "bitField0_", "timeSinceTrigger_", SurveyShown.class, SurveyAccepted.class, InvitationAnswered.class, QuestionAnswered.class, SurveyClosed.class});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$Event();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$Event.class) {
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
