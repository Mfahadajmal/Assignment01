package com.google.search.mdi.aratea.proto;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FilteredData extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final FilteredData DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public int reason_;

    static {
        FilteredData filteredData = new FilteredData();
        DEFAULT_INSTANCE = filteredData;
        GeneratedMessageLite.registerDefaultInstance(FilteredData.class, filteredData);
    }

    private FilteredData() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0003\u0003\u0001\u0000\u0000\u0000\u0003᠌\u0002", new Object[]{"bitField0_", "reason_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$9});
            case NEW_MUTABLE_INSTANCE:
                return new FilteredData();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((int[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (FilteredData.class) {
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
