package com.google.android.accessibility.selecttospeak;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakPipelineProto$SelectToSpeakPipeline extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SelectToSpeakPipelineProto$SelectToSpeakPipeline DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList pipelineRecord_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PipelineRecord extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final PipelineRecord DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public TimeBucket interactionToJobStart_;
        public TimeBucket interactionToNoTextFound_;
        public TimeBucket selectionStartToEnd_;
        public TimeBucket uiReadyToPlay_;
        public TimeBucket uiReadyToSelection_;
        public TimeBucket uiReadyToStop_;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class TimeBucket extends GeneratedMessageLite implements MessageLiteOrBuilder {
            public static final TimeBucket DEFAULT_INSTANCE;
            private static volatile Parser PARSER;
            public int bitField0_;
            public int bucket10STo20S_;
            public int bucket1MsTo500Ms_;
            public int bucket1STo2S_;
            public int bucket20STo40S_;
            public int bucket2STo5S_;
            public int bucket40SPlus_;
            public int bucket500MsTo1S_;
            public int bucket5STo10S_;
            public int unknown_;

            static {
                TimeBucket timeBucket = new TimeBucket();
                DEFAULT_INSTANCE = timeBucket;
                GeneratedMessageLite.registerDefaultInstance(TimeBucket.class, timeBucket);
            }

            private TimeBucket() {
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006\bင\u0007\tင\b", new Object[]{"bitField0_", "bucket1MsTo500Ms_", "bucket500MsTo1S_", "bucket1STo2S_", "bucket2STo5S_", "bucket5STo10S_", "bucket10STo20S_", "bucket20STo40S_", "bucket40SPlus_", "unknown_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new TimeBucket();
                    case NEW_BUILDER:
                        return new SystemHealthProto$PackedHistogram.Builder((int[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser parser = PARSER;
                        if (parser == null) {
                            synchronized (TimeBucket.class) {
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

        static {
            PipelineRecord pipelineRecord = new PipelineRecord();
            DEFAULT_INSTANCE = pipelineRecord;
            GeneratedMessageLite.registerDefaultInstance(PipelineRecord.class, pipelineRecord);
        }

        private PipelineRecord() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005", new Object[]{"bitField0_", "uiReadyToSelection_", "uiReadyToPlay_", "uiReadyToStop_", "selectionStartToEnd_", "interactionToJobStart_", "interactionToNoTextFound_"});
                case NEW_MUTABLE_INSTANCE:
                    return new PipelineRecord();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (PipelineRecord.class) {
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

    static {
        SelectToSpeakPipelineProto$SelectToSpeakPipeline selectToSpeakPipelineProto$SelectToSpeakPipeline = new SelectToSpeakPipelineProto$SelectToSpeakPipeline();
        DEFAULT_INSTANCE = selectToSpeakPipelineProto$SelectToSpeakPipeline;
        GeneratedMessageLite.registerDefaultInstance(SelectToSpeakPipelineProto$SelectToSpeakPipeline.class, selectToSpeakPipelineProto$SelectToSpeakPipeline);
    }

    private SelectToSpeakPipelineProto$SelectToSpeakPipeline() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"pipelineRecord_", PipelineRecord.class});
            case NEW_MUTABLE_INSTANCE:
                return new SelectToSpeakPipelineProto$SelectToSpeakPipeline();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SelectToSpeakPipelineProto$SelectToSpeakPipeline.class) {
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
