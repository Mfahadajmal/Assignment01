package com.google.wireless.android.play.playlog.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogSamplingRulesProto$LogSamplingRules extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final LogSamplingRulesProto$LogSamplingRules DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.ProtobufList rules_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Rule extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final Rule DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String correlationToken_ = "";
        public int eventCode_;
        public long keepDenominator_;
        public long keepNumerator_;

        static {
            Rule rule = new Rule();
            DEFAULT_INSTANCE = rule;
            GeneratedMessageLite.registerDefaultInstance(Rule.class, rule);
        }

        private Rule() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ဂ\u0003", new Object[]{"bitField0_", "eventCode_", "correlationToken_", "keepNumerator_", "keepDenominator_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Rule();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((int[][]) null, (char[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (Rule.class) {
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
        LogSamplingRulesProto$LogSamplingRules logSamplingRulesProto$LogSamplingRules = new LogSamplingRulesProto$LogSamplingRules();
        DEFAULT_INSTANCE = logSamplingRulesProto$LogSamplingRules;
        GeneratedMessageLite.registerDefaultInstance(LogSamplingRulesProto$LogSamplingRules.class, logSamplingRulesProto$LogSamplingRules);
    }

    private LogSamplingRulesProto$LogSamplingRules() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"rules_", Rule.class});
            case NEW_MUTABLE_INSTANCE:
                return new LogSamplingRulesProto$LogSamplingRules();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (LogSamplingRulesProto$LogSamplingRules.class) {
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
