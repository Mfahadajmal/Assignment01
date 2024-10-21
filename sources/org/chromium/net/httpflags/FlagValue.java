package org.chromium.net.httpflags;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagValue extends GeneratedMessageLite<FlagValue, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
    public static final int CONSTRAINED_VALUES_FIELD_NUMBER = 8;
    public static final FlagValue DEFAULT_INSTANCE;
    private static volatile Parser<FlagValue> PARSER;
    public Internal.ProtobufList<ConstrainedValue> constrainedValues_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ConstrainedValue extends GeneratedMessageLite<ConstrainedValue, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
        public static final int APP_ID_FIELD_NUMBER = 1;
        public static final int BOOL_VALUE_FIELD_NUMBER = 3;
        public static final int BYTES_VALUE_FIELD_NUMBER = 7;
        public static final ConstrainedValue DEFAULT_INSTANCE;
        public static final int FLOAT_VALUE_FIELD_NUMBER = 5;
        public static final int INT_VALUE_FIELD_NUMBER = 4;
        public static final int MIN_VERSION_FIELD_NUMBER = 2;
        private static volatile Parser<ConstrainedValue> PARSER = null;
        public static final int STRING_VALUE_FIELD_NUMBER = 6;
        public int bitField0_;
        public Object value_;
        public int valueCase_ = 0;
        public String appId_ = "";
        public String minVersion_ = "";

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class ValueCase {
            public static final int BOOL_VALUE$ar$edu$cf8fd95b_0 = 1;
            public static final int INT_VALUE$ar$edu = 2;
            public static final int FLOAT_VALUE$ar$edu = 3;
            public static final int STRING_VALUE$ar$edu$cf8fd95b_0 = 4;
            public static final int BYTES_VALUE$ar$edu$cf8fd95b_0 = 5;
            public static final int VALUE_NOT_SET$ar$edu$cf8fd95b_0 = 6;
            private static final /* synthetic */ int[] $VALUES$ar$edu$c597d882_0 = {BOOL_VALUE$ar$edu$cf8fd95b_0, INT_VALUE$ar$edu, FLOAT_VALUE$ar$edu, STRING_VALUE$ar$edu$cf8fd95b_0, BYTES_VALUE$ar$edu$cf8fd95b_0, VALUE_NOT_SET$ar$edu$cf8fd95b_0};

            public static /* synthetic */ String toStringGeneratedd1815f9e3435b51d(int i) {
                switch (i) {
                    case 1:
                        return "BOOL_VALUE";
                    case 2:
                        return "INT_VALUE";
                    case 3:
                        return "FLOAT_VALUE";
                    case 4:
                        return "STRING_VALUE";
                    case 5:
                        return "BYTES_VALUE";
                    case 6:
                        return "VALUE_NOT_SET";
                    default:
                        return "null";
                }
            }

            public static int[] values$ar$edu$a3bfe3be_0() {
                return new int[]{BOOL_VALUE$ar$edu$cf8fd95b_0, INT_VALUE$ar$edu, FLOAT_VALUE$ar$edu, STRING_VALUE$ar$edu$cf8fd95b_0, BYTES_VALUE$ar$edu$cf8fd95b_0, VALUE_NOT_SET$ar$edu$cf8fd95b_0};
            }
        }

        static {
            ConstrainedValue constrainedValue = new ConstrainedValue();
            DEFAULT_INSTANCE = constrainedValue;
            GeneratedMessageLite.registerDefaultInstance(ConstrainedValue.class, constrainedValue);
        }

        private ConstrainedValue() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0001\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003်\u0000\u0004ဵ\u0000\u0005ဴ\u0000\u0006ျ\u0000\u0007ွ\u0000", new Object[]{"value_", "valueCase_", "bitField0_", "appId_", "minVersion_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ConstrainedValue();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<ConstrainedValue> parser = PARSER;
                    if (parser == null) {
                        synchronized (ConstrainedValue.class) {
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
        FlagValue flagValue = new FlagValue();
        DEFAULT_INSTANCE = flagValue;
        GeneratedMessageLite.registerDefaultInstance(FlagValue.class, flagValue);
    }

    private FlagValue() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\b\b\u0001\u0000\u0001\u0000\b\u001b", new Object[]{"constrainedValues_", ConstrainedValue.class});
            case NEW_MUTABLE_INSTANCE:
                return new FlagValue();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<FlagValue> parser = PARSER;
                if (parser == null) {
                    synchronized (FlagValue.class) {
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
