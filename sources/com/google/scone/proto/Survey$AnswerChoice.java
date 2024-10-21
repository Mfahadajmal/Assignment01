package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$AnswerChoice extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$AnswerChoice DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int answerOrdinal_;
    public int answerType_;
    public int bitField0_;
    public Survey$BranchingDestination branchingDestination_;
    public boolean screenIn_;
    public String text_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AnswerType {
        public static final int ANSWER_TYPE_UNSPECIFIED$ar$edu = 2;
        public static final int ANSWER_TYPE_TEXT$ar$edu = 3;
        public static final int ANSWER_TYPE_WRITE_IN$ar$edu = 4;
        public static final int ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu = 5;
        public static final int UNRECOGNIZED$ar$edu$57c31185_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$271a2c54_0 = {ANSWER_TYPE_UNSPECIFIED$ar$edu, ANSWER_TYPE_TEXT$ar$edu, ANSWER_TYPE_WRITE_IN$ar$edu, ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu, UNRECOGNIZED$ar$edu$57c31185_0};

        public static int forNumber$ar$edu$ac62307f_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return 0;
                        }
                        return ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu;
                    }
                    return ANSWER_TYPE_WRITE_IN$ar$edu;
                }
                return ANSWER_TYPE_TEXT$ar$edu;
            }
            return ANSWER_TYPE_UNSPECIFIED$ar$edu;
        }

        public static int[] values$ar$edu$6e6a1f6f_0() {
            return new int[]{ANSWER_TYPE_UNSPECIFIED$ar$edu, ANSWER_TYPE_TEXT$ar$edu, ANSWER_TYPE_WRITE_IN$ar$edu, ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu, UNRECOGNIZED$ar$edu$57c31185_0};
        }
    }

    static {
        Survey$AnswerChoice survey$AnswerChoice = new Survey$AnswerChoice();
        DEFAULT_INSTANCE = survey$AnswerChoice;
        GeneratedMessageLite.registerDefaultInstance(Survey$AnswerChoice.class, survey$AnswerChoice);
    }

    private Survey$AnswerChoice() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0000\u0000\u0001\f\u0002\u000b\u0003Ȉ\u0004\u0007\u0006ဉ\u0000", new Object[]{"bitField0_", "answerType_", "answerOrdinal_", "text_", "screenIn_", "branchingDestination_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$AnswerChoice();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$AnswerChoice.class) {
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
