package com.google.android.accessibility.selecttospeak;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakLogProto$SelectToSpeakExtension extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SelectToSpeakLogProto$SelectToSpeakExtension DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public SelectToSpeakActionsProto$SelectToSpeakActions a11Ys2SActions_;
    public SelectToSpeakPipelineProto$SelectToSpeakPipeline a11Ys2SPipeline_;
    public SelectToSpeakSettingsProto$SelectToSpeakSettings a11Ys2SSettings_;
    public SelectToSpeakWordCountsProto$SelectToSpeakWordCounts a11Ys2SWordCounts_;
    public String accessibilitySuiteVersion_ = "";
    public int bitField0_;
    public boolean talkbackEnabledDuringLogCycle_;
    public boolean talkbackEnabledWhenLogSent_;

    static {
        SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension = new SelectToSpeakLogProto$SelectToSpeakExtension();
        DEFAULT_INSTANCE = selectToSpeakLogProto$SelectToSpeakExtension;
        GeneratedMessageLite.registerDefaultInstance(SelectToSpeakLogProto$SelectToSpeakExtension.class, selectToSpeakLogProto$SelectToSpeakExtension);
    }

    private SelectToSpeakLogProto$SelectToSpeakExtension() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဈ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဉ\u0005\u0007ဉ\u0006", new Object[]{"bitField0_", "a11Ys2SSettings_", "a11Ys2SActions_", "accessibilitySuiteVersion_", "talkbackEnabledDuringLogCycle_", "talkbackEnabledWhenLogSent_", "a11Ys2SWordCounts_", "a11Ys2SPipeline_"});
            case NEW_MUTABLE_INSTANCE:
                return new SelectToSpeakLogProto$SelectToSpeakExtension();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SelectToSpeakLogProto$SelectToSpeakExtension.class) {
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
