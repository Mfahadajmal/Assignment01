package com.google.protos.uservoice.surveys.client.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$Rating extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final UserVoiceSurveysLogging$Rating DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int numRatingChoices_;
    public int ratingType_;
    public Internal.IntList screenInOrdinal_ = emptyIntList();
    public String minOrdinalLabel_ = "";
    public String maxOrdinalLabel_ = "";

    static {
        UserVoiceSurveysLogging$Rating userVoiceSurveysLogging$Rating = new UserVoiceSurveysLogging$Rating();
        DEFAULT_INSTANCE = userVoiceSurveysLogging$Rating;
        GeneratedMessageLite.registerDefaultInstance(UserVoiceSurveysLogging$Rating.class, userVoiceSurveysLogging$Rating);
    }

    private UserVoiceSurveysLogging$Rating() {
        emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0001\u0000\u0001\f\u0002\u000b\u0003+\u0004Ȉ\u0005Ȉ", new Object[]{"ratingType_", "numRatingChoices_", "screenInOrdinal_", "minOrdinalLabel_", "maxOrdinalLabel_"});
            case NEW_MUTABLE_INSTANCE:
                return new UserVoiceSurveysLogging$Rating();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (UserVoiceSurveysLogging$Rating.class) {
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
