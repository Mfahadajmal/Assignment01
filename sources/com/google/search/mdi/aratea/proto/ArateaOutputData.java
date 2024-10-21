package com.google.search.mdi.aratea.proto;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.search.mdi.aratea.cros.agent.proto.CrosAgentResponse;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArateaOutputData extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ArateaOutputData DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int outputDataCase_ = 0;
    public Object outputData_;

    static {
        ArateaOutputData arateaOutputData = new ArateaOutputData();
        DEFAULT_INSTANCE = arateaOutputData;
        GeneratedMessageLite.registerDefaultInstance(ArateaOutputData.class, arateaOutputData);
    }

    private ArateaOutputData() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0001\u0000\u0001\u0007\u0004\u0000\u0000\u0000\u0001;\u0000\u0002<\u0000\u0003<\u0000\u0007<\u0000", new Object[]{"outputData_", "outputDataCase_", Image.class, Any.class, CrosAgentResponse.class});
            case NEW_MUTABLE_INSTANCE:
                return new ArateaOutputData();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ArateaOutputData.class) {
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
