package org.chromium.net.httpflags;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BaseFeatureOverrides extends GeneratedMessageLite<BaseFeatureOverrides, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
    public static final BaseFeatureOverrides DEFAULT_INSTANCE;
    public static final int FEATURE_STATES_FIELD_NUMBER = 1;
    private static volatile Parser<BaseFeatureOverrides> PARSER;
    public MapFieldLite<String, FeatureState> featureStates_ = MapFieldLite.EMPTY_MAP_FIELD;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FeatureState extends GeneratedMessageLite<FeatureState, SystemHealthProto$PackedHistogram.Builder> implements MessageLiteOrBuilder {
        public static final FeatureState DEFAULT_INSTANCE;
        public static final int ENABLED_FIELD_NUMBER = 1;
        public static final int PARAMS_FIELD_NUMBER = 2;
        private static volatile Parser<FeatureState> PARSER;
        public int bitField0_;
        public boolean enabled_;
        public MapFieldLite<String, ByteString> params_ = MapFieldLite.EMPTY_MAP_FIELD;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class ParamsDefaultEntryHolder {
            static final ExecutorSelector defaultEntry$ar$class_merging = new ExecutorSelector(WireFormat.FieldType.STRING, "", WireFormat.FieldType.BYTES, ByteString.EMPTY);
        }

        static {
            FeatureState featureState = new FeatureState();
            DEFAULT_INSTANCE = featureState;
            GeneratedMessageLite.registerDefaultInstance(FeatureState.class, featureState);
        }

        private FeatureState() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0001\u0000\u0000\u0001ဇ\u0000\u00022", new Object[]{"bitField0_", "enabled_", "params_", ParamsDefaultEntryHolder.defaultEntry$ar$class_merging});
                case NEW_MUTABLE_INSTANCE:
                    return new FeatureState();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<FeatureState> parser = PARSER;
                    if (parser == null) {
                        synchronized (FeatureState.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FeatureStatesDefaultEntryHolder {
        static final ExecutorSelector defaultEntry$ar$class_merging = new ExecutorSelector(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, FeatureState.DEFAULT_INSTANCE);
    }

    static {
        BaseFeatureOverrides baseFeatureOverrides = new BaseFeatureOverrides();
        DEFAULT_INSTANCE = baseFeatureOverrides;
        GeneratedMessageLite.registerDefaultInstance(BaseFeatureOverrides.class, baseFeatureOverrides);
    }

    private BaseFeatureOverrides() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"featureStates_", FeatureStatesDefaultEntryHolder.defaultEntry$ar$class_merging});
            case NEW_MUTABLE_INSTANCE:
                return new BaseFeatureOverrides();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[][][]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BaseFeatureOverrides> parser = PARSER;
                if (parser == null) {
                    synchronized (BaseFeatureOverrides.class) {
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
