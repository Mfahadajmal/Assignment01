package com.google.scone.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$Invitation extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Survey$Invitation DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public String invitationMessage_ = "";
    public boolean showInvitation_;

    static {
        Survey$Invitation survey$Invitation = new Survey$Invitation();
        DEFAULT_INSTANCE = survey$Invitation;
        GeneratedMessageLite.registerDefaultInstance(Survey$Invitation.class, survey$Invitation);
    }

    private Survey$Invitation() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0007\u0002Ȉ", new Object[]{"showInvitation_", "invitationMessage_"});
            case NEW_MUTABLE_INSTANCE:
                return new Survey$Invitation();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Survey$Invitation.class) {
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
