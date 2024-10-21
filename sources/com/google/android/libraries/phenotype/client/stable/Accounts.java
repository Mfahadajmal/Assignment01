package com.google.android.libraries.phenotype.client.stable;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Accounts extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Accounts DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public MapFieldLite accountLists_ = MapFieldLite.EMPTY_MAP_FIELD;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AccountListsDefaultEntryHolder {
        static final ExecutorSelector defaultEntry$ar$class_merging = new ExecutorSelector(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, AccountList.DEFAULT_INSTANCE);
    }

    static {
        Accounts accounts = new Accounts();
        DEFAULT_INSTANCE = accounts;
        GeneratedMessageLite.registerDefaultInstance(Accounts.class, accounts);
    }

    private Accounts() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0002\u0002\u0001\u0001\u0000\u0000\u00022", new Object[]{"accountLists_", AccountListsDefaultEntryHolder.defaultEntry$ar$class_merging});
            case NEW_MUTABLE_INSTANCE:
                return new Accounts();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Accounts.class) {
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
