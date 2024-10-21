package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$Question extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$Question DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public QuestionBranching questionBranching_;
    public int questionOrdinal_;
    public int questionType_;
    public Object question_;
    public boolean screeningEnabled_;
    public int questionCase_ = 0;
    public String questionText_ = "";
    public String questionHtml_ = "";
    public Internal.ProtobufList textSubstitution_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class QuestionBranching extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final QuestionBranching DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public Survey$BranchingDestination branchingDestination_;
        public String branchingGroupName_ = "";

        static {
            QuestionBranching questionBranching = new QuestionBranching();
            DEFAULT_INSTANCE = questionBranching;
            GeneratedMessageLite.registerDefaultInstance(QuestionBranching.class, questionBranching);
        }

        private QuestionBranching() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002ဉ\u0000", new Object[]{"bitField0_", "branchingGroupName_", "branchingDestination_"});
                case NEW_MUTABLE_INSTANCE:
                    return new QuestionBranching();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (byte[]) null, (char[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (QuestionBranching.class) {
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
    public final class QuestionCase {
        public static final int SINGLE_SELECT$ar$edu = 1;
        public static final int MULTI_SELECT$ar$edu = 2;
        public static final int RATING$ar$edu$c9b707fe_0 = 3;
        public static final int OPEN_TEXT$ar$edu$c9b707fe_0 = 4;
        public static final int QUESTION_NOT_SET$ar$edu = 5;
        private static final /* synthetic */ int[] $VALUES$ar$edu$20200b81_0 = {SINGLE_SELECT$ar$edu, MULTI_SELECT$ar$edu, RATING$ar$edu$c9b707fe_0, OPEN_TEXT$ar$edu$c9b707fe_0, QUESTION_NOT_SET$ar$edu};

        public static int[] values$ar$edu$8a85be9c_0() {
            return new int[]{SINGLE_SELECT$ar$edu, MULTI_SELECT$ar$edu, RATING$ar$edu$c9b707fe_0, OPEN_TEXT$ar$edu$c9b707fe_0, QUESTION_NOT_SET$ar$edu};
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class QuestionType {
        public static final int QUESTION_TYPE_UNKNOWN$ar$edu = 2;
        public static final int QUESTION_TYPE_SINGLE_SELECT$ar$edu = 3;
        public static final int QUESTION_TYPE_MULTI_SELECT$ar$edu = 4;
        public static final int QUESTION_TYPE_RATING$ar$edu = 5;
        public static final int QUESTION_TYPE_OPEN_TEXT$ar$edu = 6;
        public static final int UNRECOGNIZED$ar$edu$f46b3ccb_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$a2eb225c_0 = {QUESTION_TYPE_UNKNOWN$ar$edu, QUESTION_TYPE_SINGLE_SELECT$ar$edu, QUESTION_TYPE_MULTI_SELECT$ar$edu, QUESTION_TYPE_RATING$ar$edu, QUESTION_TYPE_OPEN_TEXT$ar$edu, UNRECOGNIZED$ar$edu$f46b3ccb_0};

        public static int forNumber$ar$edu$83e82a14_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return 0;
                            }
                            return QUESTION_TYPE_OPEN_TEXT$ar$edu;
                        }
                        return QUESTION_TYPE_RATING$ar$edu;
                    }
                    return QUESTION_TYPE_MULTI_SELECT$ar$edu;
                }
                return QUESTION_TYPE_SINGLE_SELECT$ar$edu;
            }
            return QUESTION_TYPE_UNKNOWN$ar$edu;
        }

        public static /* synthetic */ String toStringGeneratedbda3b709391959ae(int i) {
            switch (i) {
                case 1:
                    return "UNRECOGNIZED";
                case 2:
                    return "QUESTION_TYPE_UNKNOWN";
                case 3:
                    return "QUESTION_TYPE_SINGLE_SELECT";
                case 4:
                    return "QUESTION_TYPE_MULTI_SELECT";
                case 5:
                    return "QUESTION_TYPE_RATING";
                case 6:
                    return "QUESTION_TYPE_OPEN_TEXT";
                default:
                    return "null";
            }
        }

        public static int[] values$ar$edu$82431fbc_0() {
            return new int[]{QUESTION_TYPE_UNKNOWN$ar$edu, QUESTION_TYPE_SINGLE_SELECT$ar$edu, QUESTION_TYPE_MULTI_SELECT$ar$edu, QUESTION_TYPE_RATING$ar$edu, QUESTION_TYPE_OPEN_TEXT$ar$edu, UNRECOGNIZED$ar$edu$f46b3ccb_0};
        }
    }

    static {
        Survey$Question survey$Question = new Survey$Question();
        DEFAULT_INSTANCE = survey$Question;
        GeneratedMessageLite.registerDefaultInstance(Survey$Question.class, survey$Question);
    }

    private Survey$Question() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0001\u0001\u0001\u000b\u000b\u0000\u0001\u0000\u0001\u000b\u0002Ȉ\u0003\f\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000\b\u0007\tȈ\n\u001b\u000bဉ\u0000", new Object[]{"question_", "questionCase_", "bitField0_", "questionOrdinal_", "questionText_", "questionType_", Survey$SingleSelect.class, Survey$MultiSelect.class, Survey$Rating.class, Survey$OpenText.class, "screeningEnabled_", "questionHtml_", "textSubstitution_", Survey$TextSubstitution.class, "questionBranching_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$Question();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (byte[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$Question.class) {
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
