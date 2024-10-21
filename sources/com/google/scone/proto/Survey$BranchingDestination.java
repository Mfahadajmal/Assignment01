package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$BranchingDestination extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$BranchingDestination DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int destinationType_;
    public String toBranchingGroup_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DestinationType {
        public static final int DESTINATION_TYPE_UNKNOWN$ar$edu = 2;
        public static final int DESTINATION_TYPE_DEFAULT_NEXT_QUESTION$ar$edu = 3;
        public static final int DESTINATION_TYPE_BRANCHING_GROUP$ar$edu = 4;
        public static final int DESTINATION_TYPE_COMPLETE_SURVEY$ar$edu = 5;
        public static final int UNRECOGNIZED$ar$edu$b8e6fbeb_0 = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$5a2a86c6_0 = {DESTINATION_TYPE_UNKNOWN$ar$edu, DESTINATION_TYPE_DEFAULT_NEXT_QUESTION$ar$edu, DESTINATION_TYPE_BRANCHING_GROUP$ar$edu, DESTINATION_TYPE_COMPLETE_SURVEY$ar$edu, UNRECOGNIZED$ar$edu$b8e6fbeb_0};

        public static int forNumber$ar$edu$36e8454a_0(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return 0;
                        }
                        return DESTINATION_TYPE_COMPLETE_SURVEY$ar$edu;
                    }
                    return DESTINATION_TYPE_BRANCHING_GROUP$ar$edu;
                }
                return DESTINATION_TYPE_DEFAULT_NEXT_QUESTION$ar$edu;
            }
            return DESTINATION_TYPE_UNKNOWN$ar$edu;
        }

        public static int[] values$ar$edu$649fd6d4_0() {
            return new int[]{DESTINATION_TYPE_UNKNOWN$ar$edu, DESTINATION_TYPE_DEFAULT_NEXT_QUESTION$ar$edu, DESTINATION_TYPE_BRANCHING_GROUP$ar$edu, DESTINATION_TYPE_COMPLETE_SURVEY$ar$edu, UNRECOGNIZED$ar$edu$b8e6fbeb_0};
        }
    }

    static {
        Survey$BranchingDestination survey$BranchingDestination = new Survey$BranchingDestination();
        DEFAULT_INSTANCE = survey$BranchingDestination;
        GeneratedMessageLite.registerDefaultInstance(Survey$BranchingDestination.class, survey$BranchingDestination);
    }

    private Survey$BranchingDestination() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002Ȉ", new Object[]{"destinationType_", "toBranchingGroup_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$BranchingDestination();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$BranchingDestination.class) {
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
