package com.google.android.libraries.performance.primes.metrics.battery;

import androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0;
import com.google.protobuf.MessageLite;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.BatteryMetric$Timer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HealthStatsProtos$TimerOps extends HealthStatsProtos$ProtoStatsOps {
    public static final HealthStatsProtos$TimerOps INSTANCE = new HealthStatsProtos$TimerOps();

    /* renamed from: -$$Nest$sfgetINSTANCE */
    public static /* bridge */ /* synthetic */ HealthStatsProtos$TimerOps m194$$Nest$sfgetINSTANCE() {
        return INSTANCE;
    }

    private HealthStatsProtos$TimerOps() {
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* synthetic */ MessageLite convert(String str, Object obj) {
        return BatteryMetricService.timer(str, ViewCompat$$ExternalSyntheticApiModelOutline0.m11m(obj));
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* bridge */ /* synthetic */ String nameOf(MessageLite messageLite) {
        BatteryMetric$Timer batteryMetric$Timer = (BatteryMetric$Timer) messageLite;
        BatteryMetric$HashedString batteryMetric$HashedString = batteryMetric$Timer.name_;
        if (batteryMetric$HashedString == null) {
            batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
        }
        int i = batteryMetric$HashedString.bitField0_ & 2;
        BatteryMetric$HashedString batteryMetric$HashedString2 = batteryMetric$Timer.name_;
        if (i != 0) {
            if (batteryMetric$HashedString2 == null) {
                batteryMetric$HashedString2 = BatteryMetric$HashedString.DEFAULT_INSTANCE;
            }
            return batteryMetric$HashedString2.unhashedName_;
        }
        if (batteryMetric$HashedString2 == null) {
            batteryMetric$HashedString2 = BatteryMetric$HashedString.DEFAULT_INSTANCE;
        }
        return Long.toHexString(batteryMetric$HashedString2.hash_);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* synthetic */ MessageLite subtract(MessageLite messageLite, MessageLite messageLite2) {
        return BatteryMetricService.subtract((BatteryMetric$Timer) messageLite, (BatteryMetric$Timer) messageLite2);
    }
}
