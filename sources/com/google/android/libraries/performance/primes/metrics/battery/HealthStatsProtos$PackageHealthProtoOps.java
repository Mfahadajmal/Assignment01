package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.protobuf.MessageLite;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HealthStatsProtos$PackageHealthProtoOps extends HealthStatsProtos$ProtoStatsOps {
    public static final HealthStatsProtos$PackageHealthProtoOps INSTANCE = new HealthStatsProtos$PackageHealthProtoOps();

    private HealthStatsProtos$PackageHealthProtoOps() {
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005d A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003b  */
    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ com.google.protobuf.MessageLite convert(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            android.os.health.HealthStats r6 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m8m(r6)
            logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto r0 = logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto.DEFAULT_INSTANCE
            com.google.protobuf.GeneratedMessageLite$Builder r0 = r0.createBuilder()
            logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram$Builder r0 = (logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram.Builder) r0
            com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ServiceHealthProtoOps r1 = com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ServiceHealthProtoOps.INSTANCE
            r2 = 40001(0x9c41, float:5.6053E-41)
            java.util.Map r2 = com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService.getStatsMap(r6, r2)
            java.util.List r1 = r1.convert(r2)
            r0.addAllStatsServices$ar$ds(r1)
            com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$CounterOps r1 = com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$CounterOps.INSTANCE
            if (r6 == 0) goto L2e
            r2 = 40002(0x9c42, float:5.6055E-41)
            boolean r3 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m$4(r6, r2)
            if (r3 == 0) goto L2e
            java.util.Map r6 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m$2(r6, r2)
            goto L32
        L2e:
            java.util.Map r6 = java.util.Collections.emptyMap()
        L32:
            java.util.List r6 = r1.convert(r6)
            r0.addAllWakeupAlarmsCount$ar$ds(r6)
            if (r5 == 0) goto L51
            logs.proto.wireless.performance.mobile.BatteryMetric$HashedString r5 = com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService.hashedString(r5)
            r0.copyOnWrite()
            MessageType extends com.google.protobuf.GeneratedMessageLite<MessageType, BuilderType> r6 = r0.instance
            logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto r6 = (logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto) r6
            r5.getClass()
            r6.name_ = r5
            int r5 = r6.bitField0_
            r5 = r5 | 1
            r6.bitField0_ = r5
        L51:
            com.google.protobuf.GeneratedMessageLite r5 = r0.build()
            logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto r5 = (logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto) r5
            boolean r6 = com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService.isZero(r5)
            if (r6 == 0) goto L5e
            r5 = 0
        L5e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$PackageHealthProtoOps.convert(java.lang.String, java.lang.Object):com.google.protobuf.MessageLite");
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* bridge */ /* synthetic */ String nameOf(MessageLite messageLite) {
        BatteryMetric$HashedString batteryMetric$HashedString = ((BatteryMetric$PackageHealthProto) messageLite).name_;
        if (batteryMetric$HashedString == null) {
            batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
        }
        return batteryMetric$HashedString.unhashedName_;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* synthetic */ MessageLite subtract(MessageLite messageLite, MessageLite messageLite2) {
        BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto = (BatteryMetric$PackageHealthProto) messageLite;
        BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto2 = (BatteryMetric$PackageHealthProto) messageLite2;
        if (batteryMetric$PackageHealthProto != null && batteryMetric$PackageHealthProto2 != null) {
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$PackageHealthProto.DEFAULT_INSTANCE.createBuilder();
            builder.addAllStatsServices$ar$ds(HealthStatsProtos$ServiceHealthProtoOps.INSTANCE.subtract(batteryMetric$PackageHealthProto.statsServices_, batteryMetric$PackageHealthProto2.statsServices_));
            builder.addAllWakeupAlarmsCount$ar$ds(HealthStatsProtos$CounterOps.INSTANCE.subtract(batteryMetric$PackageHealthProto.wakeupAlarmsCount_, batteryMetric$PackageHealthProto2.wakeupAlarmsCount_));
            BatteryMetric$HashedString batteryMetric$HashedString = batteryMetric$PackageHealthProto.name_;
            if (batteryMetric$HashedString == null) {
                batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
            }
            builder.copyOnWrite();
            BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto3 = (BatteryMetric$PackageHealthProto) builder.instance;
            batteryMetric$HashedString.getClass();
            batteryMetric$PackageHealthProto3.name_ = batteryMetric$HashedString;
            batteryMetric$PackageHealthProto3.bitField0_ |= 1;
            BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto4 = (BatteryMetric$PackageHealthProto) builder.build();
            if (BatteryMetricService.isZero(batteryMetric$PackageHealthProto4)) {
                return null;
            }
            return batteryMetric$PackageHealthProto4;
        }
        return batteryMetric$PackageHealthProto;
    }
}
