package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.protobuf.MessageLite;
import logs.proto.wireless.performance.mobile.BatteryMetric$Counter;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
final class HealthStatsProtos$CounterOps extends HealthStatsProtos$ProtoStatsOps {
    public static final HealthStatsProtos$CounterOps INSTANCE = new HealthStatsProtos$CounterOps();

    private HealthStatsProtos$CounterOps() {
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* bridge */ /* synthetic */ MessageLite convert(String str, Object obj) {
        int intValue = ((Long) obj).intValue();
        if (intValue == 0) {
            return null;
        }
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$Counter.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        BatteryMetric$Counter batteryMetric$Counter = (BatteryMetric$Counter) builder.instance;
        batteryMetric$Counter.bitField0_ |= 1;
        batteryMetric$Counter.count_ = intValue;
        if (str != null) {
            BatteryMetric$HashedString hashedString = BatteryMetricService.hashedString(str);
            builder.copyOnWrite();
            BatteryMetric$Counter batteryMetric$Counter2 = (BatteryMetric$Counter) builder.instance;
            hashedString.getClass();
            batteryMetric$Counter2.name_ = hashedString;
            batteryMetric$Counter2.bitField0_ |= 2;
        }
        return (BatteryMetric$Counter) builder.build();
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* bridge */ /* synthetic */ String nameOf(MessageLite messageLite) {
        BatteryMetric$HashedString batteryMetric$HashedString = ((BatteryMetric$Counter) messageLite).name_;
        if (batteryMetric$HashedString == null) {
            batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
        }
        return batteryMetric$HashedString.unhashedName_;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* synthetic */ MessageLite subtract(MessageLite messageLite, MessageLite messageLite2) {
        int i;
        BatteryMetric$Counter batteryMetric$Counter = (BatteryMetric$Counter) messageLite;
        BatteryMetric$Counter batteryMetric$Counter2 = (BatteryMetric$Counter) messageLite2;
        if (batteryMetric$Counter != null && batteryMetric$Counter2 != null) {
            if ((batteryMetric$Counter.bitField0_ & 1) == 0 || (i = batteryMetric$Counter.count_ - batteryMetric$Counter2.count_) == 0) {
                return null;
            }
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$Counter.DEFAULT_INSTANCE.createBuilder();
            if ((batteryMetric$Counter.bitField0_ & 2) != 0) {
                BatteryMetric$HashedString batteryMetric$HashedString = batteryMetric$Counter.name_;
                if (batteryMetric$HashedString == null) {
                    batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
                }
                builder.copyOnWrite();
                BatteryMetric$Counter batteryMetric$Counter3 = (BatteryMetric$Counter) builder.instance;
                batteryMetric$HashedString.getClass();
                batteryMetric$Counter3.name_ = batteryMetric$HashedString;
                batteryMetric$Counter3.bitField0_ |= 2;
            }
            builder.copyOnWrite();
            BatteryMetric$Counter batteryMetric$Counter4 = (BatteryMetric$Counter) builder.instance;
            batteryMetric$Counter4.bitField0_ |= 1;
            batteryMetric$Counter4.count_ = i;
            return (BatteryMetric$Counter) builder.build();
        }
        return batteryMetric$Counter;
    }
}
