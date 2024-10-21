package com.google.android.libraries.performance.proto.primes.persistent;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentFormat$BatterySnapshot extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final PersistentFormat$BatterySnapshot DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int chargeCounter_;
    public long currentTime_;
    public long elapsedTime_;
    public ExtensionMetric$MetricExtension metricExtension_;
    public long primesVersion_;
    public int sampleInfo_;
    public BatteryMetric$UidHealthProto uidHealthProto_;
    public long versionNameHash_;
    private byte memoizedIsInitialized = 2;
    public String customEventName_ = "";

    static {
        PersistentFormat$BatterySnapshot persistentFormat$BatterySnapshot = new PersistentFormat$BatterySnapshot();
        DEFAULT_INSTANCE = persistentFormat$BatterySnapshot;
        GeneratedMessageLite.registerDefaultInstance(PersistentFormat$BatterySnapshot.class, persistentFormat$BatterySnapshot);
    }

    private PersistentFormat$BatterySnapshot() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        byte b = 1;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                if (obj == null) {
                    b = 0;
                }
                this.memoizedIsInitialized = b;
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\t\u0000\u0001\u0001\n\t\u0000\u0000\u0001\u0001ဉ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005စ\u0004\u0006င\u0005\u0007ဈ\u0006\tᐉ\b\nင\t", new Object[]{"bitField0_", "uidHealthProto_", "elapsedTime_", "currentTime_", "primesVersion_", "versionNameHash_", "sampleInfo_", "customEventName_", "metricExtension_", "chargeCounter_"});
            case NEW_MUTABLE_INSTANCE:
                return new PersistentFormat$BatterySnapshot();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[][][]) null, (int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PersistentFormat$BatterySnapshot.class) {
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
