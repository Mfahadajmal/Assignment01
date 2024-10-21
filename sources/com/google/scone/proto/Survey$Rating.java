package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$Rating extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$Rating DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int numRatingChoices_;
    public int ratingType_;
    public Internal.IntList screenInOrdinal_ = emptyIntList();
    public String minOrdinalLabel_ = "";
    public String maxOrdinalLabel_ = "";
    public Internal.ProtobufList branchingConfig_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BranchingConfig extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final BranchingConfig DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private int bitField0_;
        public Survey$BranchingDestination branchingDestination_;
        public Internal.IntList ratingOrdinal_ = emptyIntList();

        static {
            BranchingConfig branchingConfig = new BranchingConfig();
            DEFAULT_INSTANCE = branchingConfig;
            GeneratedMessageLite.registerDefaultInstance(BranchingConfig.class, branchingConfig);
        }

        private BranchingConfig() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001'\u0002ဉ\u0000", new Object[]{"bitField0_", "ratingOrdinal_", "branchingDestination_"});
                case NEW_MUTABLE_INSTANCE:
                    return new BranchingConfig();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (byte[]) null, (char[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (BranchingConfig.class) {
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
    public final class RatingType {
        public static final int RATING_TYPE_UNKNOWN$ar$edu = 2;
        public static final int RATING_TYPE_SMILEYS$ar$edu = 3;
        public static final int RATING_TYPE_STARS$ar$edu = 4;
        public static final int RATING_TYPE_NUMBERS$ar$edu = 5;
        public static final int RATING_TYPE_THUMBS_UP_THUMBS_DOWN$ar$edu = 6;
        public static final int UNRECOGNIZED$ar$edu$f6e0fe3d_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$b06da374_0 = {RATING_TYPE_UNKNOWN$ar$edu, RATING_TYPE_SMILEYS$ar$edu, RATING_TYPE_STARS$ar$edu, RATING_TYPE_NUMBERS$ar$edu, RATING_TYPE_THUMBS_UP_THUMBS_DOWN$ar$edu, UNRECOGNIZED$ar$edu$f6e0fe3d_0};

        public static int forNumber$ar$edu$e870e65f_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return 0;
                            }
                            return RATING_TYPE_THUMBS_UP_THUMBS_DOWN$ar$edu;
                        }
                        return RATING_TYPE_NUMBERS$ar$edu;
                    }
                    return RATING_TYPE_STARS$ar$edu;
                }
                return RATING_TYPE_SMILEYS$ar$edu;
            }
            return RATING_TYPE_UNKNOWN$ar$edu;
        }

        public static /* synthetic */ String toStringGenerated2a14248702d19c74(int i) {
            switch (i) {
                case 1:
                    return "UNRECOGNIZED";
                case 2:
                    return "RATING_TYPE_UNKNOWN";
                case 3:
                    return "RATING_TYPE_SMILEYS";
                case 4:
                    return "RATING_TYPE_STARS";
                case 5:
                    return "RATING_TYPE_NUMBERS";
                case 6:
                    return "RATING_TYPE_THUMBS_UP_THUMBS_DOWN";
                default:
                    return "null";
            }
        }

        public static int[] values$ar$edu$b59bada_0() {
            return new int[]{RATING_TYPE_UNKNOWN$ar$edu, RATING_TYPE_SMILEYS$ar$edu, RATING_TYPE_STARS$ar$edu, RATING_TYPE_NUMBERS$ar$edu, RATING_TYPE_THUMBS_UP_THUMBS_DOWN$ar$edu, UNRECOGNIZED$ar$edu$f6e0fe3d_0};
        }
    }

    static {
        Survey$Rating survey$Rating = new Survey$Rating();
        DEFAULT_INSTANCE = survey$Rating;
        GeneratedMessageLite.registerDefaultInstance(Survey$Rating.class, survey$Rating);
    }

    private Survey$Rating() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0002\u0000\u0001\f\u0002\u000b\u0003+\u0004Ȉ\u0005Ȉ\u0006\u001b", new Object[]{"ratingType_", "numRatingChoices_", "screenInOrdinal_", "minOrdinalLabel_", "maxOrdinalLabel_", "branchingConfig_", BranchingConfig.class});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$Rating();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (byte[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$Rating.class) {
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
