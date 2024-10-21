package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$LegalSettings extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$LegalSettings DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String customEntityName_ = "";
    public String customPrivacyUrl_ = "";
    public String customTermsUrl_ = "";

    static {
        Survey$LegalSettings survey$LegalSettings = new Survey$LegalSettings();
        DEFAULT_INSTANCE = survey$LegalSettings;
        GeneratedMessageLite.registerDefaultInstance(Survey$LegalSettings.class, survey$LegalSettings);
    }

    private Survey$LegalSettings() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002", new Object[]{"bitField0_", "customEntityName_", "customPrivacyUrl_", "customTermsUrl_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$LegalSettings();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$LegalSettings.class) {
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
