package com.google.android.libraries.phenotype.client.stable;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SnapshotProto$SnapshotFlag extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SnapshotProto$SnapshotFlag DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Object value_;
    public int valueCase_ = 0;
    public String name_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ValueCase {
        public static final int LONG_VALUE$ar$edu$d6cc3af1_0 = 1;
        public static final int BOOLEAN_VALUE$ar$edu = 2;
        public static final int DOUBLE_VALUE$ar$edu$d6cc3af1_0 = 3;
        public static final int STRING_VALUE$ar$edu$d6cc3af1_0 = 4;
        public static final int BYTES_VALUE$ar$edu$d6cc3af1_0 = 5;
        public static final int VALUE_NOT_SET$ar$edu$d6cc3af1_0 = 6;
        private static final /* synthetic */ int[] $VALUES$ar$edu$12a96ed0_0 = {LONG_VALUE$ar$edu$d6cc3af1_0, BOOLEAN_VALUE$ar$edu, DOUBLE_VALUE$ar$edu$d6cc3af1_0, STRING_VALUE$ar$edu$d6cc3af1_0, BYTES_VALUE$ar$edu$d6cc3af1_0, VALUE_NOT_SET$ar$edu$d6cc3af1_0};

        public static int[] values$ar$edu$4a88ea73_0() {
            return new int[]{LONG_VALUE$ar$edu$d6cc3af1_0, BOOLEAN_VALUE$ar$edu, DOUBLE_VALUE$ar$edu$d6cc3af1_0, STRING_VALUE$ar$edu$d6cc3af1_0, BYTES_VALUE$ar$edu$d6cc3af1_0, VALUE_NOT_SET$ar$edu$d6cc3af1_0};
        }
    }

    static {
        SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag = new SnapshotProto$SnapshotFlag();
        DEFAULT_INSTANCE = snapshotProto$SnapshotFlag;
        GeneratedMessageLite.registerDefaultInstance(SnapshotProto$SnapshotFlag.class, snapshotProto$SnapshotFlag);
    }

    private SnapshotProto$SnapshotFlag() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0001\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u00025\u0000\u0003:\u0000\u00043\u0000\u0005;\u0000\u0006=\u0000", new Object[]{"value_", "valueCase_", "bitField0_", "name_"});
            case NEW_MUTABLE_INSTANCE:
                return new SnapshotProto$SnapshotFlag();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (int[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SnapshotProto$SnapshotFlag.class) {
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
