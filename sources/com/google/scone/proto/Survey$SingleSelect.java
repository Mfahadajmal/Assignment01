package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$SingleSelect extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$SingleSelect DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Survey$AnswerChoices answerChoices_;
    public int bitField0_;
    public int iconType_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IconType {
        public static final int ICON_TYPE_UNKNOWN$ar$edu = 2;
        public static final int ICON_TYPE_NONE$ar$edu = 3;
        public static final int ICON_TYPE_SMILEYS_SATISFIED_FIRST$ar$edu = 4;
        public static final int ICON_TYPE_SMILEYS_DISSATISFIED_FIRST$ar$edu = 5;
        public static final int UNRECOGNIZED$ar$edu$43c21c2a_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$3b7dc9b0_0 = {ICON_TYPE_UNKNOWN$ar$edu, ICON_TYPE_NONE$ar$edu, ICON_TYPE_SMILEYS_SATISFIED_FIRST$ar$edu, ICON_TYPE_SMILEYS_DISSATISFIED_FIRST$ar$edu, UNRECOGNIZED$ar$edu$43c21c2a_0};

        public static int forNumber$ar$edu$389e289_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return 0;
                        }
                        return ICON_TYPE_SMILEYS_DISSATISFIED_FIRST$ar$edu;
                    }
                    return ICON_TYPE_SMILEYS_SATISFIED_FIRST$ar$edu;
                }
                return ICON_TYPE_NONE$ar$edu;
            }
            return ICON_TYPE_UNKNOWN$ar$edu;
        }

        public static int[] values$ar$edu$dfae1903_0() {
            return new int[]{ICON_TYPE_UNKNOWN$ar$edu, ICON_TYPE_NONE$ar$edu, ICON_TYPE_SMILEYS_SATISFIED_FIRST$ar$edu, ICON_TYPE_SMILEYS_DISSATISFIED_FIRST$ar$edu, UNRECOGNIZED$ar$edu$43c21c2a_0};
        }
    }

    static {
        Survey$SingleSelect survey$SingleSelect = new Survey$SingleSelect();
        DEFAULT_INSTANCE = survey$SingleSelect;
        GeneratedMessageLite.registerDefaultInstance(Survey$SingleSelect.class, survey$SingleSelect);
    }

    private Survey$SingleSelect() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\f", new Object[]{"bitField0_", "answerChoices_", "iconType_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$SingleSelect();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (byte[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$SingleSelect.class) {
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
