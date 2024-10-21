package com.google.identity.signedoutstate.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SignedOutState extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SignedOutState DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public MobileSignedOutConsent mobileSignedOutConsent_;
    public String zwiebackToken_ = "";

    static {
        SignedOutState signedOutState = new SignedOutState();
        DEFAULT_INSTANCE = signedOutState;
        GeneratedMessageLite.registerDefaultInstance(SignedOutState.class, signedOutState);
    }

    private SignedOutState() {
        ByteString byteString = ByteString.EMPTY;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002ဉ\u0000", new Object[]{"bitField0_", "zwiebackToken_", "mobileSignedOutConsent_"});
            case NEW_MUTABLE_INSTANCE:
                return new SignedOutState();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (short[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SignedOutState.class) {
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
