package com.google.search.mdi.aratea.proto;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GenerationSignalOverride extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final GenerationSignalOverride DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int backend_;
    public int bitField0_;
    public String signalName_ = "";

    static {
        GenerationSignalOverride generationSignalOverride = new GenerationSignalOverride();
        DEFAULT_INSTANCE = generationSignalOverride;
        GeneratedMessageLite.registerDefaultInstance(GenerationSignalOverride.class, generationSignalOverride);
    }

    private GenerationSignalOverride() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "backend_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$7, "signalName_"});
            case NEW_MUTABLE_INSTANCE:
                return new GenerationSignalOverride();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (GenerationSignalOverride.class) {
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
