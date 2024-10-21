package com.google.ocr;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PageLayoutMutator$PageLayoutMutatorContextOptions extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final PageLayoutMutator$PageLayoutMutatorContextOptions DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private MapFieldLite mutatorOptions_ = MapFieldLite.EMPTY_MAP_FIELD;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MutatorOptionsDefaultEntryHolder {
        static final ExecutorSelector defaultEntry$ar$class_merging = new ExecutorSelector(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, PageLayoutMutator$PageLayoutMutatorRuntimeOptions.DEFAULT_INSTANCE);
    }

    static {
        PageLayoutMutator$PageLayoutMutatorContextOptions pageLayoutMutator$PageLayoutMutatorContextOptions = new PageLayoutMutator$PageLayoutMutatorContextOptions();
        DEFAULT_INSTANCE = pageLayoutMutator$PageLayoutMutatorContextOptions;
        GeneratedMessageLite.registerDefaultInstance(PageLayoutMutator$PageLayoutMutatorContextOptions.class, pageLayoutMutator$PageLayoutMutatorContextOptions);
    }

    private PageLayoutMutator$PageLayoutMutatorContextOptions() {
        GeneratedMessageLite.emptyProtobufList();
        GeneratedMessageLite.emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"mutatorOptions_", MutatorOptionsDefaultEntryHolder.defaultEntry$ar$class_merging});
            case NEW_MUTABLE_INSTANCE:
                return new PageLayoutMutator$PageLayoutMutatorContextOptions();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (char[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PageLayoutMutator$PageLayoutMutatorContextOptions.class) {
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