package com.google.android.libraries.performance.primes.metrics.battery;

import android.os.health.HealthStats;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.metrics.battery.HashingNameSanitizer;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import java.util.Collections;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.BatteryMetric$Timer;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
final class BatteryCapture {
    public final Provider batteryConfigurations;
    public final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    public final WindowTrackerFactory systemHealthCapture$ar$class_merging;
    public final String versionName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Snapshot {
        final Long currentTime;
        final Long elapsedTime;
        final HealthStats healthStats;
        final BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo;
        final String customEventName = null;
        final Integer chargeCounter = null;

        public Snapshot(Long l, Long l2, HealthStats healthStats, BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo, String str, Integer num) {
            this.elapsedTime = l;
            this.currentTime = l2;
            this.healthStats = healthStats;
            this.sampleInfo = sampleInfo;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final StatsStorage.StatsRecord toStatsRecord() {
            Object obj;
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$UidHealthProto.DEFAULT_INSTANCE.createBuilder();
            HealthStats healthStats = this.healthStats;
            long measurement = BatteryMetricService.getMeasurement(healthStats, 10001);
            long j = 0;
            if (measurement != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto.bitField0_ |= 1;
                batteryMetric$UidHealthProto.realtimeBatteryMs_ = measurement;
            }
            long measurement2 = BatteryMetricService.getMeasurement(healthStats, 10002);
            if (measurement2 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto2.bitField0_ |= 2;
                batteryMetric$UidHealthProto2.uptimeBatteryMs_ = measurement2;
            }
            long measurement3 = BatteryMetricService.getMeasurement(healthStats, 10003);
            if (measurement3 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto3 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto3.bitField0_ |= 4;
                batteryMetric$UidHealthProto3.realtimeScreenOffBatteryMs_ = measurement3;
            }
            long measurement4 = BatteryMetricService.getMeasurement(healthStats, 10004);
            if (measurement4 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto4 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto4.bitField0_ |= 8;
                batteryMetric$UidHealthProto4.uptimeScreenOffBatteryMs_ = measurement4;
            }
            builder.addAllWakelocksFull$ar$ds(BatteryMetricService.getTimers(healthStats, 10005));
            builder.addAllWakelocksPartial$ar$ds(BatteryMetricService.getTimers(healthStats, 10006));
            builder.addAllWakelocksWindow$ar$ds(BatteryMetricService.getTimers(healthStats, 10007));
            builder.addAllWakelocksDraw$ar$ds(BatteryMetricService.getTimers(healthStats, 10008));
            builder.addAllSyncs$ar$ds(BatteryMetricService.getTimers(healthStats, 10009));
            builder.addAllJobs$ar$ds(BatteryMetricService.getTimers(healthStats, 10010));
            BatteryMetric$Timer timer = BatteryMetricService.getTimer(healthStats, 10011);
            if (timer != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto5 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto5.gpsSensor_ = timer;
                batteryMetric$UidHealthProto5.bitField0_ |= 16;
            }
            builder.addAllSensors$ar$ds(BatteryMetricService.getTimers(healthStats, 10012));
            builder.addAllStatsProcesses$ar$ds(HealthStatsProtos$ProcessHealthProtoOps.INSTANCE.convert(BatteryMetricService.getStatsMap(healthStats, 10014)));
            builder.addAllStatsPackages$ar$ds(HealthStatsProtos$PackageHealthProtoOps.INSTANCE.convert(BatteryMetricService.getStatsMap(healthStats, 10015)));
            long measurement5 = BatteryMetricService.getMeasurement(healthStats, 10016);
            if (measurement5 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto6 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto6.bitField0_ |= 32;
                batteryMetric$UidHealthProto6.wifiIdleMs_ = measurement5;
            }
            long measurement6 = BatteryMetricService.getMeasurement(healthStats, 10017);
            if (measurement6 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto7 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto7.bitField0_ |= 64;
                batteryMetric$UidHealthProto7.wifiRxMs_ = measurement6;
            }
            long measurement7 = BatteryMetricService.getMeasurement(healthStats, 10018);
            if (measurement7 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto8 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto8.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
                batteryMetric$UidHealthProto8.wifiTxMs_ = measurement7;
            }
            long measurement8 = BatteryMetricService.getMeasurement(healthStats, 10019);
            if (measurement8 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto9 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto9.bitField0_ |= 256;
                batteryMetric$UidHealthProto9.wifiPowerMams_ = measurement8;
            }
            long measurement9 = BatteryMetricService.getMeasurement(healthStats, 10020);
            if (measurement9 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto10 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto10.bitField0_ |= 512;
                batteryMetric$UidHealthProto10.bluetoothIdleMs_ = measurement9;
            }
            long measurement10 = BatteryMetricService.getMeasurement(healthStats, 10021);
            if (measurement10 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto11 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto11.bitField0_ |= 1024;
                batteryMetric$UidHealthProto11.bluetoothRxMs_ = measurement10;
            }
            long measurement11 = BatteryMetricService.getMeasurement(healthStats, 10022);
            if (measurement11 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto12 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto12.bitField0_ |= 2048;
                batteryMetric$UidHealthProto12.bluetoothTxMs_ = measurement11;
            }
            long measurement12 = BatteryMetricService.getMeasurement(healthStats, 10023);
            if (measurement12 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto13 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto13.bitField0_ |= 4096;
                batteryMetric$UidHealthProto13.bluetoothPowerMams_ = measurement12;
            }
            long measurement13 = BatteryMetricService.getMeasurement(healthStats, 10024);
            if (measurement13 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto14 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto14.bitField0_ |= 8192;
                batteryMetric$UidHealthProto14.mobileIdleMs_ = measurement13;
            }
            long measurement14 = BatteryMetricService.getMeasurement(healthStats, 10025);
            if (measurement14 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto15 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto15.bitField0_ |= 16384;
                batteryMetric$UidHealthProto15.mobileRxMs_ = measurement14;
            }
            long measurement15 = BatteryMetricService.getMeasurement(healthStats, 10026);
            if (measurement15 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto16 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto16.bitField0_ |= 32768;
                batteryMetric$UidHealthProto16.mobileTxMs_ = measurement15;
            }
            long measurement16 = BatteryMetricService.getMeasurement(healthStats, 10027);
            if (measurement16 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto17 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto17.bitField0_ |= 65536;
                batteryMetric$UidHealthProto17.mobilePowerMams_ = measurement16;
            }
            long measurement17 = BatteryMetricService.getMeasurement(healthStats, 10028);
            if (measurement17 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto18 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto18.bitField0_ |= 131072;
                batteryMetric$UidHealthProto18.wifiRunningMs_ = measurement17;
            }
            long measurement18 = BatteryMetricService.getMeasurement(healthStats, 10029);
            if (measurement18 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto19 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto19.bitField0_ |= 262144;
                batteryMetric$UidHealthProto19.wifiFullLockMs_ = measurement18;
            }
            BatteryMetric$Timer timer2 = BatteryMetricService.getTimer(healthStats, 10030);
            if (timer2 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto20 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto20.wifiScan_ = timer2;
                batteryMetric$UidHealthProto20.bitField0_ |= 524288;
            }
            long measurement19 = BatteryMetricService.getMeasurement(healthStats, 10031);
            if (measurement19 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto21 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto21.bitField0_ |= 1048576;
                batteryMetric$UidHealthProto21.wifiMulticastMs_ = measurement19;
            }
            BatteryMetric$Timer timer3 = BatteryMetricService.getTimer(healthStats, 10032);
            if (timer3 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto22 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto22.audio_ = timer3;
                batteryMetric$UidHealthProto22.bitField0_ |= 2097152;
            }
            BatteryMetric$Timer timer4 = BatteryMetricService.getTimer(healthStats, 10033);
            if (timer4 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto23 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto23.video_ = timer4;
                batteryMetric$UidHealthProto23.bitField0_ |= 4194304;
            }
            BatteryMetric$Timer timer5 = BatteryMetricService.getTimer(healthStats, 10034);
            if (timer5 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto24 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto24.flashlight_ = timer5;
                batteryMetric$UidHealthProto24.bitField0_ |= 8388608;
            }
            BatteryMetric$Timer timer6 = BatteryMetricService.getTimer(healthStats, 10035);
            if (timer6 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto25 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto25.camera_ = timer6;
                batteryMetric$UidHealthProto25.bitField0_ |= 16777216;
            }
            BatteryMetric$Timer timer7 = BatteryMetricService.getTimer(healthStats, 10036);
            if (timer7 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto26 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto26.foregroundActivity_ = timer7;
                batteryMetric$UidHealthProto26.bitField0_ |= 33554432;
            }
            BatteryMetric$Timer timer8 = BatteryMetricService.getTimer(healthStats, 10037);
            if (timer8 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto27 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto27.bluetoothScan_ = timer8;
                batteryMetric$UidHealthProto27.bitField0_ |= 67108864;
            }
            BatteryMetric$Timer timer9 = BatteryMetricService.getTimer(healthStats, 10038);
            if (timer9 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto28 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto28.processStateTopMs_ = timer9;
                batteryMetric$UidHealthProto28.bitField0_ |= 134217728;
            }
            BatteryMetric$Timer timer10 = BatteryMetricService.getTimer(healthStats, 10039);
            if (timer10 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto29 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto29.processStateForegroundServiceMs_ = timer10;
                batteryMetric$UidHealthProto29.bitField0_ |= 268435456;
            }
            BatteryMetric$Timer timer11 = BatteryMetricService.getTimer(healthStats, 10040);
            if (timer11 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto30 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto30.processStateTopSleepingMs_ = timer11;
                batteryMetric$UidHealthProto30.bitField0_ |= 536870912;
            }
            BatteryMetric$Timer timer12 = BatteryMetricService.getTimer(healthStats, 10041);
            if (timer12 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto31 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto31.processStateForegroundMs_ = timer12;
                batteryMetric$UidHealthProto31.bitField0_ |= 1073741824;
            }
            BatteryMetric$Timer timer13 = BatteryMetricService.getTimer(healthStats, 10042);
            if (timer13 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto32 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto32.processStateBackgroundMs_ = timer13;
                batteryMetric$UidHealthProto32.bitField0_ |= Integer.MIN_VALUE;
            }
            BatteryMetric$Timer timer14 = BatteryMetricService.getTimer(healthStats, 10043);
            if (timer14 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto33 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto33.processStateCachedMs_ = timer14;
                batteryMetric$UidHealthProto33.bitField1_ |= 1;
            }
            BatteryMetric$Timer timer15 = BatteryMetricService.getTimer(healthStats, 10044);
            if (timer15 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto34 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto34.vibrator_ = timer15;
                batteryMetric$UidHealthProto34.bitField1_ |= 2;
            }
            long measurement20 = BatteryMetricService.getMeasurement(healthStats, 10045);
            if (measurement20 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto35 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto35.bitField1_ |= 4;
                batteryMetric$UidHealthProto35.otherUserActivityCount_ = measurement20;
            }
            long measurement21 = BatteryMetricService.getMeasurement(healthStats, 10046);
            if (measurement21 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto36 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto36.bitField1_ |= 8;
                batteryMetric$UidHealthProto36.buttonUserActivityCount_ = measurement21;
            }
            long measurement22 = BatteryMetricService.getMeasurement(healthStats, 10047);
            if (measurement22 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto37 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto37.bitField1_ |= 16;
                batteryMetric$UidHealthProto37.touchUserActivityCount_ = measurement22;
            }
            long measurement23 = BatteryMetricService.getMeasurement(healthStats, 10048);
            if (measurement23 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto38 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto38.bitField1_ |= 32;
                batteryMetric$UidHealthProto38.mobileRxBytes_ = measurement23;
            }
            long measurement24 = BatteryMetricService.getMeasurement(healthStats, 10049);
            if (measurement24 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto39 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto39.bitField1_ |= 64;
                batteryMetric$UidHealthProto39.mobileTxBytes_ = measurement24;
            }
            long measurement25 = BatteryMetricService.getMeasurement(healthStats, 10050);
            if (measurement25 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto40 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto40.bitField1_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
                batteryMetric$UidHealthProto40.wifiRxBytes_ = measurement25;
            }
            long measurement26 = BatteryMetricService.getMeasurement(healthStats, 10051);
            if (measurement26 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto41 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto41.bitField1_ |= 256;
                batteryMetric$UidHealthProto41.wifiTxBytes_ = measurement26;
            }
            long measurement27 = BatteryMetricService.getMeasurement(healthStats, 10052);
            if (measurement27 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto42 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto42.bitField1_ |= 512;
                batteryMetric$UidHealthProto42.bluetoothRxBytes_ = measurement27;
            }
            long measurement28 = BatteryMetricService.getMeasurement(healthStats, 10053);
            if (measurement28 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto43 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto43.bitField1_ |= 1024;
                batteryMetric$UidHealthProto43.bluetoothTxBytes_ = measurement28;
            }
            long measurement29 = BatteryMetricService.getMeasurement(healthStats, 10054);
            if (measurement29 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto44 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto44.bitField1_ |= 2048;
                batteryMetric$UidHealthProto44.mobileRxPackets_ = measurement29;
            }
            long measurement30 = BatteryMetricService.getMeasurement(healthStats, 10055);
            if (measurement30 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto45 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto45.bitField1_ |= 4096;
                batteryMetric$UidHealthProto45.mobileTxPackets_ = measurement30;
            }
            long measurement31 = BatteryMetricService.getMeasurement(healthStats, 10056);
            if (measurement31 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto46 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto46.bitField1_ |= 8192;
                batteryMetric$UidHealthProto46.wifiRxPackets_ = measurement31;
            }
            long measurement32 = BatteryMetricService.getMeasurement(healthStats, 10057);
            if (measurement32 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto47 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto47.bitField1_ |= 16384;
                batteryMetric$UidHealthProto47.wifiTxPackets_ = measurement32;
            }
            long measurement33 = BatteryMetricService.getMeasurement(healthStats, 10058);
            if (measurement33 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto48 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto48.bitField1_ |= 32768;
                batteryMetric$UidHealthProto48.bluetoothRxPackets_ = measurement33;
            }
            long measurement34 = BatteryMetricService.getMeasurement(healthStats, 10059);
            if (measurement34 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto49 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto49.bitField1_ = 65536 | batteryMetric$UidHealthProto49.bitField1_;
                batteryMetric$UidHealthProto49.bluetoothTxPackets_ = measurement34;
            }
            BatteryMetric$Timer timer16 = BatteryMetricService.getTimer(healthStats, 10061);
            if (timer16 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto50 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto50.mobileRadioActive_ = timer16;
                batteryMetric$UidHealthProto50.bitField1_ |= 131072;
            }
            long measurement35 = BatteryMetricService.getMeasurement(healthStats, 10062);
            if (measurement35 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto51 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto51.bitField1_ |= 262144;
                batteryMetric$UidHealthProto51.userCpuTimeMs_ = measurement35;
            }
            long measurement36 = BatteryMetricService.getMeasurement(healthStats, 10063);
            if (measurement36 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto52 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto52.bitField1_ = 524288 | batteryMetric$UidHealthProto52.bitField1_;
                batteryMetric$UidHealthProto52.systemCpuTimeMs_ = measurement36;
            }
            long measurement37 = BatteryMetricService.getMeasurement(healthStats, 10064);
            if (measurement37 != 0) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto batteryMetric$UidHealthProto53 = (BatteryMetric$UidHealthProto) builder.instance;
                batteryMetric$UidHealthProto53.bitField1_ |= 1048576;
                batteryMetric$UidHealthProto53.cpuPowerMams_ = measurement37;
            }
            BatteryCapture batteryCapture = BatteryCapture.this;
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) ((BatteryMetric$UidHealthProto) builder.build()).toBuilder();
            Collections.unmodifiableList(((BatteryMetric$UidHealthProto) builder2.instance).wakelocksFull_);
            int i = 0;
            while (true) {
                WindowTrackerFactory windowTrackerFactory = batteryCapture.systemHealthCapture$ar$class_merging;
                int size = ((BatteryMetric$UidHealthProto) builder2.instance).wakelocksFull_.size();
                obj = windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
                if (i >= size) {
                    break;
                }
                builder2.setWakelocksFull$ar$ds(i, ((HashingNameSanitizer) obj).hashRawTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder2.getWakelocksFull(i)));
                i++;
            }
            Collections.unmodifiableList(((BatteryMetric$UidHealthProto) builder2.instance).wakelocksPartial_);
            for (int i2 = 0; i2 < ((BatteryMetric$UidHealthProto) builder2.instance).wakelocksPartial_.size(); i2++) {
                builder2.setWakelocksPartial$ar$ds(i2, ((HashingNameSanitizer) obj).hashRawTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder2.getWakelocksPartial(i2)));
            }
            Collections.unmodifiableList(((BatteryMetric$UidHealthProto) builder2.instance).wakelocksWindow_);
            for (int i3 = 0; i3 < ((BatteryMetric$UidHealthProto) builder2.instance).wakelocksWindow_.size(); i3++) {
                builder2.setWakelocksWindow$ar$ds(i3, ((HashingNameSanitizer) obj).hashRawTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder2.getWakelocksWindow(i3)));
            }
            Collections.unmodifiableList(((BatteryMetric$UidHealthProto) builder2.instance).wakelocksDraw_);
            for (int i4 = 0; i4 < ((BatteryMetric$UidHealthProto) builder2.instance).wakelocksDraw_.size(); i4++) {
                builder2.setWakelocksDraw$ar$ds(i4, ((HashingNameSanitizer) obj).hashRawTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder2.getWakelocksDraw(i4)));
            }
            Collections.unmodifiableList(((BatteryMetric$UidHealthProto) builder2.instance).syncs_);
            for (int i5 = 0; i5 < ((BatteryMetric$UidHealthProto) builder2.instance).syncs_.size(); i5++) {
                builder2.setSyncs$ar$ds(i5, ((HashingNameSanitizer) obj).hashRawTimerName(HashingNameSanitizer.NameType.SYNC, builder2.getSyncs(i5)));
            }
            Collections.unmodifiableList(((BatteryMetric$UidHealthProto) builder2.instance).jobs_);
            for (int i6 = 0; i6 < ((BatteryMetric$UidHealthProto) builder2.instance).jobs_.size(); i6++) {
                builder2.setJobs$ar$ds(i6, ((HashingNameSanitizer) obj).hashRawTimerName(HashingNameSanitizer.NameType.JOB, builder2.getJobs(i6)));
            }
            Collections.unmodifiableList(((BatteryMetric$UidHealthProto) builder2.instance).sensors_);
            for (int i7 = 0; i7 < ((BatteryMetric$UidHealthProto) builder2.instance).sensors_.size(); i7++) {
                builder2.setSensors$ar$ds(i7, ((HashingNameSanitizer) obj).hashRawTimerName(HashingNameSanitizer.NameType.SENSOR, builder2.getSensors(i7)));
            }
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto54 = (BatteryMetric$UidHealthProto) builder2.build();
            Long l = this.elapsedTime;
            Long l2 = this.currentTime;
            String str = BatteryCapture.this.versionName;
            if (str != null) {
                j = str.hashCode();
            }
            return new StatsStorage.StatsRecord(batteryMetric$UidHealthProto54, l, l2, 643847009L, Long.valueOf(j), this.sampleInfo, null, null, null);
        }
    }

    public BatteryCapture(String str, WindowTrackerFactory windowTrackerFactory, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, Provider provider) {
        this.versionName = str;
        this.systemHealthCapture$ar$class_merging = windowTrackerFactory;
        this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.batteryConfigurations = provider;
    }
}
