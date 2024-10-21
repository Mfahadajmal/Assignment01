package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$CommonData extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$CommonData DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public UserVoiceSurveysLogging$ClientContext clientContext_;
    public Duration eventDuration_;
    public Timestamp eventStart_;
    public UserVoiceSurveysLogging$UserData userData_;

    static {
        UserVoiceSurveysLogging$CommonData userVoiceSurveysLogging$CommonData = new UserVoiceSurveysLogging$CommonData();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$CommonData;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$CommonData.class, userVoiceSurveysLogging$CommonData);
    }

    private UserVoiceSurveysLogging$CommonData() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0007\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0004ဉ\u0002\u0007ဉ\u0005", new Object[]{"bitField0_", "eventStart_", "eventDuration_", "userData_", "clientContext_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$CommonData();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][]) null, (byte[]) null, (char[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$CommonData.class) {
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
