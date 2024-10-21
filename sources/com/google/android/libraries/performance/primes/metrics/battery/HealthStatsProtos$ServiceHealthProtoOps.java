package com.google.android.libraries.performance.primes.metrics.battery;

import android.os.health.HealthStats;
import androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0;
import com.google.protobuf.MessageLite;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.BatteryMetric$ServiceHealthProto;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
final class HealthStatsProtos$ServiceHealthProtoOps extends HealthStatsProtos$ProtoStatsOps {
    public static final HealthStatsProtos$ServiceHealthProtoOps INSTANCE = new HealthStatsProtos$ServiceHealthProtoOps();

    private HealthStatsProtos$ServiceHealthProtoOps() {
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* synthetic */ MessageLite convert(String str, Object obj) {
        HealthStats m8m = ViewCompat$$ExternalSyntheticApiModelOutline0.m8m(obj);
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$ServiceHealthProto.DEFAULT_INSTANCE.createBuilder();
        int measurement = (int) BatteryMetricService.getMeasurement(m8m, 50001);
        if (measurement != 0) {
            builder.copyOnWrite();
            BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto = (BatteryMetric$ServiceHealthProto) builder.instance;
            batteryMetric$ServiceHealthProto.bitField0_ |= 1;
            batteryMetric$ServiceHealthProto.startServiceCount_ = measurement;
        }
        int measurement2 = (int) BatteryMetricService.getMeasurement(m8m, 50002);
        if (measurement2 != 0) {
            builder.copyOnWrite();
            BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto2 = (BatteryMetric$ServiceHealthProto) builder.instance;
            batteryMetric$ServiceHealthProto2.bitField0_ |= 2;
            batteryMetric$ServiceHealthProto2.launchCount_ = measurement2;
        }
        if (str != null) {
            BatteryMetric$HashedString hashedString = BatteryMetricService.hashedString(str);
            builder.copyOnWrite();
            BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto3 = (BatteryMetric$ServiceHealthProto) builder.instance;
            hashedString.getClass();
            batteryMetric$ServiceHealthProto3.name_ = hashedString;
            batteryMetric$ServiceHealthProto3.bitField0_ |= 4;
        }
        BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto4 = (BatteryMetric$ServiceHealthProto) builder.build();
        if (BatteryMetricService.isZero(batteryMetric$ServiceHealthProto4)) {
            return null;
        }
        return batteryMetric$ServiceHealthProto4;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* bridge */ /* synthetic */ String nameOf(MessageLite messageLite) {
        BatteryMetric$HashedString batteryMetric$HashedString = ((BatteryMetric$ServiceHealthProto) messageLite).name_;
        if (batteryMetric$HashedString == null) {
            batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
        }
        return batteryMetric$HashedString.unhashedName_;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* synthetic */ MessageLite subtract(MessageLite messageLite, MessageLite messageLite2) {
        int i;
        int i2;
        BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto = (BatteryMetric$ServiceHealthProto) messageLite;
        BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto2 = (BatteryMetric$ServiceHealthProto) messageLite2;
        if (batteryMetric$ServiceHealthProto != null && batteryMetric$ServiceHealthProto2 != null) {
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$ServiceHealthProto.DEFAULT_INSTANCE.createBuilder();
            if ((batteryMetric$ServiceHealthProto.bitField0_ & 1) != 0 && (i2 = batteryMetric$ServiceHealthProto.startServiceCount_ - batteryMetric$ServiceHealthProto2.startServiceCount_) != 0) {
                builder.copyOnWrite();
                BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto3 = (BatteryMetric$ServiceHealthProto) builder.instance;
                batteryMetric$ServiceHealthProto3.bitField0_ |= 1;
                batteryMetric$ServiceHealthProto3.startServiceCount_ = i2;
            }
            if ((batteryMetric$ServiceHealthProto.bitField0_ & 2) != 0 && (i = batteryMetric$ServiceHealthProto.launchCount_ - batteryMetric$ServiceHealthProto2.launchCount_) != 0) {
                builder.copyOnWrite();
                BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto4 = (BatteryMetric$ServiceHealthProto) builder.instance;
                batteryMetric$ServiceHealthProto4.bitField0_ |= 2;
                batteryMetric$ServiceHealthProto4.launchCount_ = i;
            }
            BatteryMetric$HashedString batteryMetric$HashedString = batteryMetric$ServiceHealthProto.name_;
            if (batteryMetric$HashedString == null) {
                batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
            }
            builder.copyOnWrite();
            BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto5 = (BatteryMetric$ServiceHealthProto) builder.instance;
            batteryMetric$HashedString.getClass();
            batteryMetric$ServiceHealthProto5.name_ = batteryMetric$HashedString;
            batteryMetric$ServiceHealthProto5.bitField0_ |= 4;
            BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto6 = (BatteryMetric$ServiceHealthProto) builder.build();
            if (BatteryMetricService.isZero(batteryMetric$ServiceHealthProto6)) {
                return null;
            }
            return batteryMetric$ServiceHealthProto6;
        }
        return batteryMetric$ServiceHealthProto;
    }
}
