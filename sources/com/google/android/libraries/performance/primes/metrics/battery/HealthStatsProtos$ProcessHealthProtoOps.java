package com.google.android.libraries.performance.primes.metrics.battery;

import android.os.health.HealthStats;
import androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0;
import com.google.protobuf.MessageLite;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.BatteryMetric$ProcessHealthProto;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HealthStatsProtos$ProcessHealthProtoOps extends HealthStatsProtos$ProtoStatsOps {
    public static final HealthStatsProtos$ProcessHealthProtoOps INSTANCE = new HealthStatsProtos$ProcessHealthProtoOps();

    private HealthStatsProtos$ProcessHealthProtoOps() {
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* synthetic */ MessageLite convert(String str, Object obj) {
        HealthStats m8m = ViewCompat$$ExternalSyntheticApiModelOutline0.m8m(obj);
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$ProcessHealthProto.DEFAULT_INSTANCE.createBuilder();
        long measurement = BatteryMetricService.getMeasurement(m8m, 30001);
        if (measurement != 0) {
            builder.copyOnWrite();
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto = (BatteryMetric$ProcessHealthProto) builder.instance;
            batteryMetric$ProcessHealthProto.bitField0_ |= 1;
            batteryMetric$ProcessHealthProto.userTimeMs_ = measurement;
        }
        long measurement2 = BatteryMetricService.getMeasurement(m8m, 30002);
        if (measurement2 != 0) {
            builder.copyOnWrite();
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto2 = (BatteryMetric$ProcessHealthProto) builder.instance;
            batteryMetric$ProcessHealthProto2.bitField0_ |= 2;
            batteryMetric$ProcessHealthProto2.systemTimeMs_ = measurement2;
        }
        long measurement3 = BatteryMetricService.getMeasurement(m8m, 30003);
        if (measurement3 != 0) {
            builder.copyOnWrite();
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto3 = (BatteryMetric$ProcessHealthProto) builder.instance;
            batteryMetric$ProcessHealthProto3.bitField0_ |= 4;
            batteryMetric$ProcessHealthProto3.startsCount_ = measurement3;
        }
        long measurement4 = BatteryMetricService.getMeasurement(m8m, 30004);
        if (measurement4 != 0) {
            builder.copyOnWrite();
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto4 = (BatteryMetric$ProcessHealthProto) builder.instance;
            batteryMetric$ProcessHealthProto4.bitField0_ |= 8;
            batteryMetric$ProcessHealthProto4.crashesCount_ = measurement4;
        }
        long measurement5 = BatteryMetricService.getMeasurement(m8m, 30005);
        if (measurement5 != 0) {
            builder.copyOnWrite();
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto5 = (BatteryMetric$ProcessHealthProto) builder.instance;
            batteryMetric$ProcessHealthProto5.bitField0_ |= 16;
            batteryMetric$ProcessHealthProto5.anrCount_ = measurement5;
        }
        long measurement6 = BatteryMetricService.getMeasurement(m8m, 30006);
        if (measurement6 != 0) {
            builder.copyOnWrite();
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto6 = (BatteryMetric$ProcessHealthProto) builder.instance;
            batteryMetric$ProcessHealthProto6.bitField0_ |= 32;
            batteryMetric$ProcessHealthProto6.foregroundMs_ = measurement6;
        }
        if (str != null) {
            BatteryMetric$HashedString hashedString = BatteryMetricService.hashedString(str);
            builder.copyOnWrite();
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto7 = (BatteryMetric$ProcessHealthProto) builder.instance;
            hashedString.getClass();
            batteryMetric$ProcessHealthProto7.name_ = hashedString;
            batteryMetric$ProcessHealthProto7.bitField0_ |= 64;
        }
        BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto8 = (BatteryMetric$ProcessHealthProto) builder.build();
        if (BatteryMetricService.isZero(batteryMetric$ProcessHealthProto8)) {
            return null;
        }
        return batteryMetric$ProcessHealthProto8;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* bridge */ /* synthetic */ String nameOf(MessageLite messageLite) {
        BatteryMetric$HashedString batteryMetric$HashedString = ((BatteryMetric$ProcessHealthProto) messageLite).name_;
        if (batteryMetric$HashedString == null) {
            batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
        }
        return batteryMetric$HashedString.unhashedName_;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos$ProtoStatsOps
    public final /* synthetic */ MessageLite subtract(MessageLite messageLite, MessageLite messageLite2) {
        BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto = (BatteryMetric$ProcessHealthProto) messageLite;
        BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto2 = (BatteryMetric$ProcessHealthProto) messageLite2;
        if (batteryMetric$ProcessHealthProto != null && batteryMetric$ProcessHealthProto2 != null) {
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$ProcessHealthProto.DEFAULT_INSTANCE.createBuilder();
            if ((batteryMetric$ProcessHealthProto.bitField0_ & 1) != 0) {
                long j = batteryMetric$ProcessHealthProto.userTimeMs_ - batteryMetric$ProcessHealthProto2.userTimeMs_;
                if (j != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto3 = (BatteryMetric$ProcessHealthProto) builder.instance;
                    batteryMetric$ProcessHealthProto3.bitField0_ |= 1;
                    batteryMetric$ProcessHealthProto3.userTimeMs_ = j;
                }
            }
            if ((batteryMetric$ProcessHealthProto.bitField0_ & 2) != 0) {
                long j2 = batteryMetric$ProcessHealthProto.systemTimeMs_ - batteryMetric$ProcessHealthProto2.systemTimeMs_;
                if (j2 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto4 = (BatteryMetric$ProcessHealthProto) builder.instance;
                    batteryMetric$ProcessHealthProto4.bitField0_ |= 2;
                    batteryMetric$ProcessHealthProto4.systemTimeMs_ = j2;
                }
            }
            if ((batteryMetric$ProcessHealthProto.bitField0_ & 4) != 0) {
                long j3 = batteryMetric$ProcessHealthProto.startsCount_ - batteryMetric$ProcessHealthProto2.startsCount_;
                if (j3 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto5 = (BatteryMetric$ProcessHealthProto) builder.instance;
                    batteryMetric$ProcessHealthProto5.bitField0_ |= 4;
                    batteryMetric$ProcessHealthProto5.startsCount_ = j3;
                }
            }
            if ((batteryMetric$ProcessHealthProto.bitField0_ & 8) != 0) {
                long j4 = batteryMetric$ProcessHealthProto.crashesCount_ - batteryMetric$ProcessHealthProto2.crashesCount_;
                if (j4 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto6 = (BatteryMetric$ProcessHealthProto) builder.instance;
                    batteryMetric$ProcessHealthProto6.bitField0_ |= 8;
                    batteryMetric$ProcessHealthProto6.crashesCount_ = j4;
                }
            }
            if ((batteryMetric$ProcessHealthProto.bitField0_ & 16) != 0) {
                long j5 = batteryMetric$ProcessHealthProto.anrCount_ - batteryMetric$ProcessHealthProto2.anrCount_;
                if (j5 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto7 = (BatteryMetric$ProcessHealthProto) builder.instance;
                    batteryMetric$ProcessHealthProto7.bitField0_ |= 16;
                    batteryMetric$ProcessHealthProto7.anrCount_ = j5;
                }
            }
            if ((batteryMetric$ProcessHealthProto.bitField0_ & 32) != 0) {
                long j6 = batteryMetric$ProcessHealthProto.foregroundMs_ - batteryMetric$ProcessHealthProto2.foregroundMs_;
                if (j6 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto8 = (BatteryMetric$ProcessHealthProto) builder.instance;
                    batteryMetric$ProcessHealthProto8.bitField0_ |= 32;
                    batteryMetric$ProcessHealthProto8.foregroundMs_ = j6;
                }
            }
            BatteryMetric$HashedString batteryMetric$HashedString = batteryMetric$ProcessHealthProto.name_;
            if (batteryMetric$HashedString == null) {
                batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
            }
            builder.copyOnWrite();
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto9 = (BatteryMetric$ProcessHealthProto) builder.instance;
            batteryMetric$HashedString.getClass();
            batteryMetric$ProcessHealthProto9.name_ = batteryMetric$HashedString;
            batteryMetric$ProcessHealthProto9.bitField0_ |= 64;
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto10 = (BatteryMetric$ProcessHealthProto) builder.build();
            if (BatteryMetricService.isZero(batteryMetric$ProcessHealthProto10)) {
                return null;
            }
            return batteryMetric$ProcessHealthProto10;
        }
        return batteryMetric$ProcessHealthProto;
    }
}
