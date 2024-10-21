package com.google.search.mdi.aratea.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GenerateResponse extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final GenerateResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList outputData_ = emptyProtobufList();
    public Internal.ProtobufList filteredData_ = emptyProtobufList();

    static {
        GenerateResponse generateResponse = new GenerateResponse();
        DEFAULT_INSTANCE = generateResponse;
        GeneratedMessageLite.registerDefaultInstance(GenerateResponse.class, generateResponse);
    }

    private GenerateResponse() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"outputData_", ArateaOutputData.class, "filteredData_", FilteredData.class});
            case NEW_MUTABLE_INSTANCE:
                return new GenerateResponse();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (GenerateResponse.class) {
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
