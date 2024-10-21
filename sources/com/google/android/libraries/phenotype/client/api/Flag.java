package com.google.android.libraries.phenotype.client.api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Flag extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Flag DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Object value_;
    public int valueCase_ = 0;
    public String name_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ValueCase {
        public static final int LONG_VALUE$ar$edu = 1;
        public static final int BOOL_VALUE$ar$edu = 2;
        public static final int DOUBLE_VALUE$ar$edu = 3;
        public static final int STRING_VALUE$ar$edu = 4;
        public static final int BYTES_VALUE$ar$edu = 5;
        public static final int VALUE_NOT_SET$ar$edu = 6;
        private static final /* synthetic */ int[] $VALUES$ar$edu$f43da67f_0 = {LONG_VALUE$ar$edu, BOOL_VALUE$ar$edu, DOUBLE_VALUE$ar$edu, STRING_VALUE$ar$edu, BYTES_VALUE$ar$edu, VALUE_NOT_SET$ar$edu};

        public static int[] values$ar$edu$5d4df8a7_0() {
            return new int[]{LONG_VALUE$ar$edu, BOOL_VALUE$ar$edu, DOUBLE_VALUE$ar$edu, STRING_VALUE$ar$edu, BYTES_VALUE$ar$edu, VALUE_NOT_SET$ar$edu};
        }
    }

    static {
        Flag flag = new Flag();
        DEFAULT_INSTANCE = flag;
        GeneratedMessageLite.registerDefaultInstance(Flag.class, flag);
    }

    private Flag() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0001\u0001\u0001\n\u0006\u0000\u0000\u0000\u00018\u0000\u0002:\u0000\u00033\u0000\u0004;\u0000\u0005=\u0000\nဈ\u0000", new Object[]{"value_", "valueCase_", "bitField0_", "name_"});
            case NEW_MUTABLE_INSTANCE:
                return new Flag();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Flag.class) {
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
