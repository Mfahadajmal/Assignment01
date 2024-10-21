package com.google.search.mdi.aratea.proto;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GenerateRequest extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final GenerateRequest DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int featureName_;
    public Internal.ProtobufList inputData_ = emptyProtobufList();
    public GenerateModelConfiguration modelConfig_;

    static {
        GenerateRequest generateRequest = new GenerateRequest();
        DEFAULT_INSTANCE = generateRequest;
        GeneratedMessageLite.registerDefaultInstance(GenerateRequest.class, generateRequest);
    }

    private GenerateRequest() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0001\u0000\u0001᠌\u0000\u0003ဉ\u0002\u0004\u001b", new Object[]{"bitField0_", "featureName_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$8, "modelConfig_", "inputData_", ArateaInputData.class});
            case NEW_MUTABLE_INSTANCE:
                return new GenerateRequest();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (GenerateRequest.class) {
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
