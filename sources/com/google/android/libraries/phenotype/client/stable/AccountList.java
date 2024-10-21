package com.google.android.libraries.phenotype.client.stable;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccountList extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final AccountList DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Internal.ProtobufList values_ = GeneratedMessageLite.emptyProtobufList();
    public String latestAccount_ = "";

    static {
        AccountList accountList = new AccountList();
        DEFAULT_INSTANCE = accountList;
        GeneratedMessageLite.registerDefaultInstance(AccountList.class, accountList);
    }

    private AccountList() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001a\u0002ဈ\u0000", new Object[]{"bitField0_", "values_", "latestAccount_"});
            case NEW_MUTABLE_INSTANCE:
                return new AccountList();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (AccountList.class) {
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
