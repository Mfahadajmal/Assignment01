package com.google.android.libraries.performance.primes.transmitter.clearcut;

import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutMetricSnapshot extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ClearcutMetricSnapshot DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public static final GeneratedMessageLite.GeneratedExtension clearcutMetricSnapshot;
    public boolean anonymous_;
    public int bitField0_;
    public boolean requireCheckbox_;
    public String logSource_ = "";
    public String mendelPackageName_ = "";
    public String zwiebackCookieOverride_ = "";
    public String uploadAccountName_ = "";
    public Internal.IntList experimentIds_ = emptyIntList();

    static {
        ClearcutMetricSnapshot clearcutMetricSnapshot2 = new ClearcutMetricSnapshot();
        DEFAULT_INSTANCE = clearcutMetricSnapshot2;
        GeneratedMessageLite.registerDefaultInstance(ClearcutMetricSnapshot.class, clearcutMetricSnapshot2);
        clearcutMetricSnapshot = GeneratedMessageLite.newSingularGeneratedExtension(MetricSnapshot.DEFAULT_INSTANCE, clearcutMetricSnapshot2, clearcutMetricSnapshot2, null, 334728578, WireFormat.FieldType.MESSAGE, ClearcutMetricSnapshot.class);
    }

    private ClearcutMetricSnapshot() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006'\u0007ဇ\u0005", new Object[]{"bitField0_", "logSource_", "mendelPackageName_", "anonymous_", "zwiebackCookieOverride_", "uploadAccountName_", "experimentIds_", "requireCheckbox_"});
            case NEW_MUTABLE_INSTANCE:
                return new ClearcutMetricSnapshot();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[][][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ClearcutMetricSnapshot.class) {
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
