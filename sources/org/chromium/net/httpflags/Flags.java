package org.chromium.net.httpflags;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Flags extends GeneratedMessageLite<Flags, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
    public static final Flags DEFAULT_INSTANCE;
    public static final int FLAGS_FIELD_NUMBER = 1;
    private static volatile Parser<Flags> PARSER;
    public MapFieldLite<String, FlagValue> flags_ = MapFieldLite.EMPTY_MAP_FIELD;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FlagsDefaultEntryHolder {
        static final ExecutorSelector defaultEntry$ar$class_merging = new ExecutorSelector(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, FlagValue.DEFAULT_INSTANCE);
    }

    static {
        Flags flags = new Flags();
        DEFAULT_INSTANCE = flags;
        GeneratedMessageLite.registerDefaultInstance(Flags.class, flags);
    }

    private Flags() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"flags_", FlagsDefaultEntryHolder.defaultEntry$ar$class_merging});
            case NEW_MUTABLE_INSTANCE:
                return new Flags();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Flags> parser = PARSER;
                if (parser == null) {
                    synchronized (Flags.class) {
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
