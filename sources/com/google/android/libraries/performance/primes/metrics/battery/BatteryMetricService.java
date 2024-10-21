package com.google.android.libraries.performance.primes.metrics.battery;

import android.app.Application;
import android.content.Context;
import android.os.health.HealthStats;
import android.os.health.TimerStat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import com.google.android.libraries.performance.primes.PrimesConfigurations;
import com.google.android.libraries.performance.primes.PrimesThreadsConfigurations;
import com.google.android.libraries.performance.primes.foreground.DebouncedForegroundSignalAdapter;
import com.google.android.libraries.performance.primes.foreground.ForegroundListener;
import com.google.android.libraries.performance.primes.foreground.ProcessImportanceForegroundSignalAdapter;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentLoadLogEvent;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto;
import logs.proto.wireless.performance.mobile.BatteryMetric$ProcessHealthProto;
import logs.proto.wireless.performance.mobile.BatteryMetric$ServiceHealthProto;
import logs.proto.wireless.performance.mobile.BatteryMetric$Timer;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BatteryMetricService {
    public BatteryMetricService() {
    }

    public static ProcessImportanceForegroundSignalAdapter create$ar$ds$84b3e942_0(ForegroundListener foregroundListener) {
        return new ProcessImportanceForegroundSignalAdapter(foregroundListener);
    }

    public static DebouncedForegroundSignalAdapter create$ar$ds$c34404f_0(ForegroundListener foregroundListener) {
        return new DebouncedForegroundSignalAdapter(foregroundListener);
    }

    public static long getMeasurement(HealthStats healthStats, int i) {
        boolean hasMeasurement;
        long measurement;
        if (healthStats != null) {
            hasMeasurement = healthStats.hasMeasurement(i);
            if (hasMeasurement) {
                measurement = healthStats.getMeasurement(i);
                return measurement;
            }
            return 0L;
        }
        return 0L;
    }

    public static Map getStatsMap(HealthStats healthStats, int i) {
        boolean hasStats;
        Map stats;
        if (healthStats != null) {
            hasStats = healthStats.hasStats(i);
            if (hasStats) {
                stats = healthStats.getStats(i);
                return stats;
            }
        }
        return Collections.emptyMap();
    }

    public static BatteryMetric$Timer getTimer(HealthStats healthStats, int i) {
        boolean hasTimer;
        TimerStat timer;
        if (healthStats != null) {
            hasTimer = healthStats.hasTimer(i);
            if (hasTimer) {
                timer = healthStats.getTimer(i);
                return timer(null, timer);
            }
        }
        return null;
    }

    public static List getTimers(HealthStats healthStats, int i) {
        boolean hasTimers;
        Map timers;
        if (healthStats != null) {
            hasTimers = healthStats.hasTimers(i);
            if (hasTimers) {
                HealthStatsProtos$TimerOps healthStatsProtos$TimerOps = HealthStatsProtos$TimerOps.INSTANCE;
                timers = healthStats.getTimers(i);
                return healthStatsProtos$TimerOps.convert(timers);
            }
        }
        return Collections.emptyList();
    }

    public static BatteryMetric$HashedString hashedString(String str) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$HashedString.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        BatteryMetric$HashedString batteryMetric$HashedString = (BatteryMetric$HashedString) builder.instance;
        batteryMetric$HashedString.bitField0_ |= 2;
        batteryMetric$HashedString.unhashedName_ = str;
        return (BatteryMetric$HashedString) builder.build();
    }

    public static boolean isZero(BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto) {
        if (batteryMetric$ProcessHealthProto == null) {
            return true;
        }
        if (batteryMetric$ProcessHealthProto.userTimeMs_ > 0 || batteryMetric$ProcessHealthProto.systemTimeMs_ > 0 || batteryMetric$ProcessHealthProto.startsCount_ > 0 || batteryMetric$ProcessHealthProto.crashesCount_ > 0 || batteryMetric$ProcessHealthProto.anrCount_ > 0) {
            return false;
        }
        return batteryMetric$ProcessHealthProto.foregroundMs_ <= 0;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [com.google.common.base.Supplier, java.lang.Object] */
    public static DaggerProdInternalComponent.ProdInternalComponentImpl newInstance$ar$class_merging(Application application, Provider provider) {
        PrimesThreadsConfigurations build = PrimesThreadsConfigurations.newBuilder().build();
        application.getClass();
        AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor(application, new DaggerProdInternalComponent.Builder());
        PrimesConfigurations primesConfigurations = (PrimesConfigurations) provider.get();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = new ConfigurationsModule$$ExternalSyntheticLambda0(primesConfigurations.metricTransmittersProvider(), 2);
        Optional memoryConfigurationsProvider = primesConfigurations.memoryConfigurationsProvider();
        DaggerProdInternalComponent.Builder builder = (DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
        builder.DaggerProdInternalComponent$Builder$ar$setMemoryConfigurationsProvider = memoryConfigurationsProvider;
        builder.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = Absent.INSTANCE;
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setGlobalConfigurationsProvider = primesConfigurations.globalConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider = primesConfigurations.timerConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider = primesConfigurations.crashConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider = primesConfigurations.applicationExitConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider = primesConfigurations.networkConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setStorageConfigurationsProvider = primesConfigurations.storageConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = primesConfigurations.jankConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider = primesConfigurations.tikTokTraceConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = primesConfigurations.traceConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = primesConfigurations.batteryConfigurationsProvider();
        ((DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = primesConfigurations.cpuProfilingConfigurationsProvider();
        Optional monitorAllActivitiesProvider = primesConfigurations.monitorAllActivitiesProvider();
        DaggerProdInternalComponent.Builder builder2 = (DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
        builder2.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = monitorAllActivitiesProvider;
        builder2.DaggerProdInternalComponent$Builder$ar$setThreadsConfigurations = Optional.of(build);
        DaggerProdInternalComponent.Builder builder3 = (DaggerProdInternalComponent.Builder) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setApplicationContext, Context.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier, Supplier.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setMemoryConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setGlobalConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setStorageConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setSharedPreferencesSupplier, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setThreadsConfigurations, Optional.class);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(builder3.DaggerProdInternalComponent$Builder$ar$setDisableAutomaticCrashInitToken, Optional.class);
        Object obj = builder3.DaggerProdInternalComponent$Builder$ar$setApplicationContext;
        ?? r5 = builder3.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier;
        Object obj2 = builder3.DaggerProdInternalComponent$Builder$ar$setMemoryConfigurationsProvider;
        Object obj3 = builder3.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider;
        Object obj4 = builder3.DaggerProdInternalComponent$Builder$ar$setGlobalConfigurationsProvider;
        Object obj5 = builder3.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider;
        Object obj6 = builder3.DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider;
        Object obj7 = builder3.DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider;
        Object obj8 = builder3.DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider;
        Object obj9 = builder3.DaggerProdInternalComponent$Builder$ar$setStorageConfigurationsProvider;
        Object obj10 = builder3.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider;
        Object obj11 = builder3.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider;
        Object obj12 = builder3.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider;
        Object obj13 = builder3.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider;
        Object obj14 = builder3.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider;
        Object obj15 = builder3.DaggerProdInternalComponent$Builder$ar$setSharedPreferencesSupplier;
        Object obj16 = builder3.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider;
        Optional optional = (Optional) obj12;
        Optional optional2 = (Optional) obj11;
        Optional optional3 = (Optional) obj10;
        Optional optional4 = (Optional) obj9;
        Optional optional5 = (Optional) obj8;
        Optional optional6 = (Optional) obj7;
        Optional optional7 = (Optional) obj6;
        Optional optional8 = (Optional) obj5;
        Optional optional9 = (Optional) obj4;
        Optional optional10 = (Optional) obj3;
        Optional optional11 = (Optional) obj2;
        return new DaggerProdInternalComponent.ProdInternalComponentImpl((Context) obj, r5, optional11, optional10, optional9, optional8, optional7, optional6, optional5, optional4, optional3, optional2, optional, (Optional) obj13, (Optional) obj14, (Optional) obj15, (Optional) obj16, (Optional) builder3.DaggerProdInternalComponent$Builder$ar$setThreadsConfigurations, (Optional) builder3.DaggerProdInternalComponent$Builder$ar$setDisableAutomaticCrashInitToken);
    }

    public static MetricConfigurations provideMetricConfigurations(Optional optional, Provider provider) {
        return (MetricConfigurations) ((Provider) optional.or(provider)).get();
    }

    public static BatteryMetric$Timer subtract(BatteryMetric$Timer batteryMetric$Timer, BatteryMetric$Timer batteryMetric$Timer2) {
        if (batteryMetric$Timer == null || batteryMetric$Timer2 == null) {
            return batteryMetric$Timer;
        }
        int i = batteryMetric$Timer.count_ - batteryMetric$Timer2.count_;
        long j = batteryMetric$Timer.durationMs_ - batteryMetric$Timer2.durationMs_;
        if (i == 0) {
            if (j == 0) {
                return null;
            }
            i = 0;
        }
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$Timer.DEFAULT_INSTANCE.createBuilder();
        if ((batteryMetric$Timer.bitField0_ & 4) != 0) {
            BatteryMetric$HashedString batteryMetric$HashedString = batteryMetric$Timer.name_;
            if (batteryMetric$HashedString == null) {
                batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
            }
            builder.copyOnWrite();
            BatteryMetric$Timer batteryMetric$Timer3 = (BatteryMetric$Timer) builder.instance;
            batteryMetric$HashedString.getClass();
            batteryMetric$Timer3.name_ = batteryMetric$HashedString;
            batteryMetric$Timer3.bitField0_ |= 4;
        }
        builder.copyOnWrite();
        BatteryMetric$Timer batteryMetric$Timer4 = (BatteryMetric$Timer) builder.instance;
        batteryMetric$Timer4.bitField0_ |= 1;
        batteryMetric$Timer4.count_ = i;
        builder.copyOnWrite();
        BatteryMetric$Timer batteryMetric$Timer5 = (BatteryMetric$Timer) builder.instance;
        batteryMetric$Timer5.bitField0_ |= 2;
        batteryMetric$Timer5.durationMs_ = j;
        return (BatteryMetric$Timer) builder.build();
    }

    public static BatteryMetric$Timer timer(String str, TimerStat timerStat) {
        int count;
        long time;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$Timer.DEFAULT_INSTANCE.createBuilder();
        count = timerStat.getCount();
        builder.copyOnWrite();
        BatteryMetric$Timer batteryMetric$Timer = (BatteryMetric$Timer) builder.instance;
        batteryMetric$Timer.bitField0_ |= 1;
        batteryMetric$Timer.count_ = count;
        time = timerStat.getTime();
        builder.copyOnWrite();
        BatteryMetric$Timer batteryMetric$Timer2 = (BatteryMetric$Timer) builder.instance;
        batteryMetric$Timer2.bitField0_ |= 2;
        batteryMetric$Timer2.durationMs_ = time;
        if (((BatteryMetric$Timer) builder.instance).count_ < 0) {
            builder.copyOnWrite();
            BatteryMetric$Timer batteryMetric$Timer3 = (BatteryMetric$Timer) builder.instance;
            batteryMetric$Timer3.bitField0_ |= 1;
            batteryMetric$Timer3.count_ = 0;
        }
        if (str != null) {
            BatteryMetric$HashedString hashedString = hashedString(str);
            builder.copyOnWrite();
            BatteryMetric$Timer batteryMetric$Timer4 = (BatteryMetric$Timer) builder.instance;
            hashedString.getClass();
            batteryMetric$Timer4.name_ = hashedString;
            batteryMetric$Timer4.bitField0_ |= 4;
        }
        BatteryMetric$Timer batteryMetric$Timer5 = (BatteryMetric$Timer) builder.instance;
        if (batteryMetric$Timer5.count_ == 0 && batteryMetric$Timer5.durationMs_ == 0) {
            return null;
        }
        return (BatteryMetric$Timer) builder.build();
    }

    public BatteryMetricService(byte[] bArr) {
    }

    public static boolean isZero(BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto) {
        if (batteryMetric$ServiceHealthProto != null) {
            return ((long) batteryMetric$ServiceHealthProto.startServiceCount_) <= 0 && ((long) batteryMetric$ServiceHealthProto.launchCount_) <= 0;
        }
        return true;
    }

    public /* synthetic */ BatteryMetricService(char[] cArr) {
    }

    public static boolean isZero(BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto) {
        if (batteryMetric$PackageHealthProto != null) {
            return batteryMetric$PackageHealthProto.statsServices_.size() == 0 && batteryMetric$PackageHealthProto.wakeupAlarmsCount_.size() == 0;
        }
        return true;
    }

    static boolean isZero(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto) {
        if (batteryMetric$UidHealthProto == null) {
            return true;
        }
        if (batteryMetric$UidHealthProto.realtimeBatteryMs_ > 0 || batteryMetric$UidHealthProto.uptimeBatteryMs_ > 0 || batteryMetric$UidHealthProto.realtimeScreenOffBatteryMs_ > 0 || batteryMetric$UidHealthProto.uptimeScreenOffBatteryMs_ > 0 || batteryMetric$UidHealthProto.wakelocksFull_.size() != 0 || batteryMetric$UidHealthProto.wakelocksPartial_.size() != 0 || batteryMetric$UidHealthProto.wakelocksWindow_.size() != 0 || batteryMetric$UidHealthProto.wakelocksDraw_.size() != 0 || batteryMetric$UidHealthProto.syncs_.size() != 0 || batteryMetric$UidHealthProto.jobs_.size() != 0 || batteryMetric$UidHealthProto.sensors_.size() != 0 || batteryMetric$UidHealthProto.statsPids_.size() != 0 || batteryMetric$UidHealthProto.statsProcesses_.size() != 0 || batteryMetric$UidHealthProto.statsPackages_.size() != 0 || batteryMetric$UidHealthProto.wifiIdleMs_ > 0 || batteryMetric$UidHealthProto.wifiRxMs_ > 0 || batteryMetric$UidHealthProto.wifiTxMs_ > 0 || batteryMetric$UidHealthProto.wifiPowerMams_ > 0 || batteryMetric$UidHealthProto.bluetoothIdleMs_ > 0 || batteryMetric$UidHealthProto.bluetoothRxMs_ > 0 || batteryMetric$UidHealthProto.bluetoothTxMs_ > 0 || batteryMetric$UidHealthProto.bluetoothPowerMams_ > 0 || batteryMetric$UidHealthProto.mobileIdleMs_ > 0 || batteryMetric$UidHealthProto.mobileRxMs_ > 0 || batteryMetric$UidHealthProto.mobileTxMs_ > 0 || batteryMetric$UidHealthProto.mobilePowerMams_ > 0 || batteryMetric$UidHealthProto.wifiRunningMs_ > 0 || batteryMetric$UidHealthProto.wifiFullLockMs_ > 0 || batteryMetric$UidHealthProto.wifiMulticastMs_ > 0 || batteryMetric$UidHealthProto.otherUserActivityCount_ > 0 || batteryMetric$UidHealthProto.buttonUserActivityCount_ > 0 || batteryMetric$UidHealthProto.touchUserActivityCount_ > 0 || batteryMetric$UidHealthProto.mobileRxBytes_ > 0 || batteryMetric$UidHealthProto.mobileTxBytes_ > 0 || batteryMetric$UidHealthProto.wifiRxBytes_ > 0 || batteryMetric$UidHealthProto.wifiTxBytes_ > 0 || batteryMetric$UidHealthProto.bluetoothRxBytes_ > 0 || batteryMetric$UidHealthProto.bluetoothTxBytes_ > 0 || batteryMetric$UidHealthProto.mobileRxPackets_ > 0 || batteryMetric$UidHealthProto.mobileTxPackets_ > 0 || batteryMetric$UidHealthProto.wifiRxPackets_ > 0 || batteryMetric$UidHealthProto.wifiTxPackets_ > 0 || batteryMetric$UidHealthProto.bluetoothRxPackets_ > 0 || batteryMetric$UidHealthProto.bluetoothTxPackets_ > 0 || batteryMetric$UidHealthProto.userCpuTimeMs_ > 0 || batteryMetric$UidHealthProto.systemCpuTimeMs_ > 0) {
            return false;
        }
        return batteryMetric$UidHealthProto.cpuPowerMams_ <= 0;
    }

    public static BatteryMetric$UidHealthProto subtract(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2) {
        HealthStatsProtos$TimerOps healthStatsProtos$TimerOps;
        HealthStatsProtos$TimerOps healthStatsProtos$TimerOps2;
        HealthStatsProtos$TimerOps healthStatsProtos$TimerOps3;
        HealthStatsProtos$TimerOps healthStatsProtos$TimerOps4;
        HealthStatsProtos$TimerOps healthStatsProtos$TimerOps5;
        BatteryMetric$Timer batteryMetric$Timer;
        BatteryMetric$Timer batteryMetric$Timer2;
        HealthStatsProtos$ProcessHealthProtoOps healthStatsProtos$ProcessHealthProtoOps;
        HealthStatsProtos$PackageHealthProtoOps healthStatsProtos$PackageHealthProtoOps;
        BatteryMetric$Timer batteryMetric$Timer3;
        BatteryMetric$Timer batteryMetric$Timer4;
        BatteryMetric$Timer batteryMetric$Timer5;
        BatteryMetric$Timer batteryMetric$Timer6;
        BatteryMetric$Timer batteryMetric$Timer7;
        BatteryMetric$Timer batteryMetric$Timer8;
        BatteryMetric$Timer batteryMetric$Timer9;
        BatteryMetric$Timer batteryMetric$Timer10;
        BatteryMetric$Timer batteryMetric$Timer11;
        BatteryMetric$Timer batteryMetric$Timer12;
        BatteryMetric$Timer batteryMetric$Timer13;
        BatteryMetric$Timer batteryMetric$Timer14;
        BatteryMetric$Timer batteryMetric$Timer15;
        BatteryMetric$Timer batteryMetric$Timer16;
        BatteryMetric$Timer batteryMetric$Timer17;
        BatteryMetric$Timer batteryMetric$Timer18;
        BatteryMetric$Timer batteryMetric$Timer19;
        BatteryMetric$Timer batteryMetric$Timer20;
        BatteryMetric$Timer batteryMetric$Timer21;
        BatteryMetric$Timer batteryMetric$Timer22;
        BatteryMetric$Timer batteryMetric$Timer23;
        BatteryMetric$Timer batteryMetric$Timer24;
        BatteryMetric$Timer batteryMetric$Timer25;
        BatteryMetric$Timer batteryMetric$Timer26;
        BatteryMetric$Timer batteryMetric$Timer27;
        BatteryMetric$Timer batteryMetric$Timer28;
        BatteryMetric$Timer batteryMetric$Timer29;
        BatteryMetric$Timer batteryMetric$Timer30;
        BatteryMetric$Timer batteryMetric$Timer31;
        BatteryMetric$Timer batteryMetric$Timer32;
        if (batteryMetric$UidHealthProto != null && batteryMetric$UidHealthProto2 != null) {
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BatteryMetric$UidHealthProto.DEFAULT_INSTANCE.createBuilder();
            if ((batteryMetric$UidHealthProto.bitField0_ & 1) != 0) {
                long j = batteryMetric$UidHealthProto.realtimeBatteryMs_ - batteryMetric$UidHealthProto2.realtimeBatteryMs_;
                if (j != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$4400((BatteryMetric$UidHealthProto) builder.instance, j);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 2) != 0) {
                long j2 = batteryMetric$UidHealthProto.uptimeBatteryMs_ - batteryMetric$UidHealthProto2.uptimeBatteryMs_;
                if (j2 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$4600((BatteryMetric$UidHealthProto) builder.instance, j2);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 4) != 0) {
                long j3 = batteryMetric$UidHealthProto.realtimeScreenOffBatteryMs_ - batteryMetric$UidHealthProto2.realtimeScreenOffBatteryMs_;
                if (j3 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$4800((BatteryMetric$UidHealthProto) builder.instance, j3);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 8) != 0) {
                long j4 = batteryMetric$UidHealthProto.uptimeScreenOffBatteryMs_ - batteryMetric$UidHealthProto2.uptimeScreenOffBatteryMs_;
                if (j4 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$5000((BatteryMetric$UidHealthProto) builder.instance, j4);
                }
            }
            builder.addAllWakelocksFull$ar$ds(HealthStatsProtos$TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.wakelocksFull_, batteryMetric$UidHealthProto2.wakelocksFull_));
            healthStatsProtos$TimerOps = HealthStatsProtos$TimerOps.INSTANCE;
            builder.addAllWakelocksPartial$ar$ds(healthStatsProtos$TimerOps.subtract(batteryMetric$UidHealthProto.wakelocksPartial_, batteryMetric$UidHealthProto2.wakelocksPartial_));
            healthStatsProtos$TimerOps2 = HealthStatsProtos$TimerOps.INSTANCE;
            builder.addAllWakelocksWindow$ar$ds(healthStatsProtos$TimerOps2.subtract(batteryMetric$UidHealthProto.wakelocksWindow_, batteryMetric$UidHealthProto2.wakelocksWindow_));
            healthStatsProtos$TimerOps3 = HealthStatsProtos$TimerOps.INSTANCE;
            builder.addAllWakelocksDraw$ar$ds(healthStatsProtos$TimerOps3.subtract(batteryMetric$UidHealthProto.wakelocksDraw_, batteryMetric$UidHealthProto2.wakelocksDraw_));
            healthStatsProtos$TimerOps4 = HealthStatsProtos$TimerOps.INSTANCE;
            builder.addAllSyncs$ar$ds(healthStatsProtos$TimerOps4.subtract(batteryMetric$UidHealthProto.syncs_, batteryMetric$UidHealthProto2.syncs_));
            healthStatsProtos$TimerOps5 = HealthStatsProtos$TimerOps.INSTANCE;
            builder.addAllJobs$ar$ds(healthStatsProtos$TimerOps5.subtract(batteryMetric$UidHealthProto.jobs_, batteryMetric$UidHealthProto2.jobs_));
            if ((batteryMetric$UidHealthProto.bitField0_ & 16) != 0) {
                batteryMetric$Timer = batteryMetric$UidHealthProto.gpsSensor_;
                if (batteryMetric$Timer == null) {
                    batteryMetric$Timer = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 16) != 0) {
                batteryMetric$Timer2 = batteryMetric$UidHealthProto2.gpsSensor_;
                if (batteryMetric$Timer2 == null) {
                    batteryMetric$Timer2 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer2 = null;
            }
            BatteryMetric$Timer subtract = subtract(batteryMetric$Timer, batteryMetric$Timer2);
            if (subtract != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$8800((BatteryMetric$UidHealthProto) builder.instance, subtract);
            }
            builder.addAllSensors$ar$ds(HealthStatsProtos$TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.sensors_, batteryMetric$UidHealthProto2.sensors_));
            healthStatsProtos$ProcessHealthProtoOps = HealthStatsProtos$ProcessHealthProtoOps.INSTANCE;
            builder.addAllStatsProcesses$ar$ds(healthStatsProtos$ProcessHealthProtoOps.subtract(batteryMetric$UidHealthProto.statsProcesses_, batteryMetric$UidHealthProto2.statsProcesses_));
            healthStatsProtos$PackageHealthProtoOps = HealthStatsProtos$PackageHealthProtoOps.INSTANCE;
            builder.addAllStatsPackages$ar$ds(healthStatsProtos$PackageHealthProtoOps.subtract(batteryMetric$UidHealthProto.statsPackages_, batteryMetric$UidHealthProto2.statsPackages_));
            if ((batteryMetric$UidHealthProto.bitField0_ & 32) != 0) {
                long j5 = batteryMetric$UidHealthProto.wifiIdleMs_ - batteryMetric$UidHealthProto2.wifiIdleMs_;
                if (j5 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$11500((BatteryMetric$UidHealthProto) builder.instance, j5);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 64) != 0) {
                long j6 = batteryMetric$UidHealthProto.wifiRxMs_ - batteryMetric$UidHealthProto2.wifiRxMs_;
                if (j6 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$11700((BatteryMetric$UidHealthProto) builder.instance, j6);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) != 0) {
                long j7 = batteryMetric$UidHealthProto.wifiTxMs_ - batteryMetric$UidHealthProto2.wifiTxMs_;
                if (j7 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$11900((BatteryMetric$UidHealthProto) builder.instance, j7);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 256) != 0) {
                long j8 = batteryMetric$UidHealthProto.wifiPowerMams_ - batteryMetric$UidHealthProto2.wifiPowerMams_;
                if (j8 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$12100((BatteryMetric$UidHealthProto) builder.instance, j8);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 512) != 0) {
                long j9 = batteryMetric$UidHealthProto.bluetoothIdleMs_ - batteryMetric$UidHealthProto2.bluetoothIdleMs_;
                if (j9 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$12300((BatteryMetric$UidHealthProto) builder.instance, j9);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 1024) != 0) {
                long j10 = batteryMetric$UidHealthProto.bluetoothRxMs_ - batteryMetric$UidHealthProto2.bluetoothRxMs_;
                if (j10 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$12500((BatteryMetric$UidHealthProto) builder.instance, j10);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 2048) != 0) {
                long j11 = batteryMetric$UidHealthProto.bluetoothTxMs_ - batteryMetric$UidHealthProto2.bluetoothTxMs_;
                if (j11 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$12700((BatteryMetric$UidHealthProto) builder.instance, j11);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 4096) != 0) {
                long j12 = batteryMetric$UidHealthProto.bluetoothPowerMams_ - batteryMetric$UidHealthProto2.bluetoothPowerMams_;
                if (j12 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$12900((BatteryMetric$UidHealthProto) builder.instance, j12);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 8192) != 0) {
                long j13 = batteryMetric$UidHealthProto.mobileIdleMs_ - batteryMetric$UidHealthProto2.mobileIdleMs_;
                if (j13 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$13100((BatteryMetric$UidHealthProto) builder.instance, j13);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 16384) != 0) {
                long j14 = batteryMetric$UidHealthProto.mobileRxMs_ - batteryMetric$UidHealthProto2.mobileRxMs_;
                if (j14 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$13300((BatteryMetric$UidHealthProto) builder.instance, j14);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 32768) != 0) {
                long j15 = batteryMetric$UidHealthProto.mobileTxMs_ - batteryMetric$UidHealthProto2.mobileTxMs_;
                if (j15 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$13500((BatteryMetric$UidHealthProto) builder.instance, j15);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 65536) != 0) {
                long j16 = batteryMetric$UidHealthProto.mobilePowerMams_ - batteryMetric$UidHealthProto2.mobilePowerMams_;
                if (j16 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$13700((BatteryMetric$UidHealthProto) builder.instance, j16);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 131072) != 0) {
                long j17 = batteryMetric$UidHealthProto.wifiRunningMs_ - batteryMetric$UidHealthProto2.wifiRunningMs_;
                if (j17 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$13900((BatteryMetric$UidHealthProto) builder.instance, j17);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 262144) != 0) {
                long j18 = batteryMetric$UidHealthProto.wifiFullLockMs_ - batteryMetric$UidHealthProto2.wifiFullLockMs_;
                if (j18 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$14100((BatteryMetric$UidHealthProto) builder.instance, j18);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 524288) != 0) {
                batteryMetric$Timer3 = batteryMetric$UidHealthProto.wifiScan_;
                if (batteryMetric$Timer3 == null) {
                    batteryMetric$Timer3 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer3 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 524288) != 0) {
                batteryMetric$Timer4 = batteryMetric$UidHealthProto2.wifiScan_;
                if (batteryMetric$Timer4 == null) {
                    batteryMetric$Timer4 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer4 = null;
            }
            BatteryMetric$Timer subtract2 = subtract(batteryMetric$Timer3, batteryMetric$Timer4);
            if (subtract2 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$14300((BatteryMetric$UidHealthProto) builder.instance, subtract2);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 1048576) != 0) {
                long j19 = batteryMetric$UidHealthProto.wifiMulticastMs_ - batteryMetric$UidHealthProto2.wifiMulticastMs_;
                if (j19 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$14600((BatteryMetric$UidHealthProto) builder.instance, j19);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 2097152) != 0) {
                batteryMetric$Timer5 = batteryMetric$UidHealthProto.audio_;
                if (batteryMetric$Timer5 == null) {
                    batteryMetric$Timer5 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer5 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 2097152) != 0) {
                batteryMetric$Timer6 = batteryMetric$UidHealthProto2.audio_;
                if (batteryMetric$Timer6 == null) {
                    batteryMetric$Timer6 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer6 = null;
            }
            BatteryMetric$Timer subtract3 = subtract(batteryMetric$Timer5, batteryMetric$Timer6);
            if (subtract3 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$14800((BatteryMetric$UidHealthProto) builder.instance, subtract3);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 4194304) != 0) {
                batteryMetric$Timer7 = batteryMetric$UidHealthProto.video_;
                if (batteryMetric$Timer7 == null) {
                    batteryMetric$Timer7 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer7 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 4194304) != 0) {
                batteryMetric$Timer8 = batteryMetric$UidHealthProto2.video_;
                if (batteryMetric$Timer8 == null) {
                    batteryMetric$Timer8 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer8 = null;
            }
            BatteryMetric$Timer subtract4 = subtract(batteryMetric$Timer7, batteryMetric$Timer8);
            if (subtract4 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$15100((BatteryMetric$UidHealthProto) builder.instance, subtract4);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 8388608) != 0) {
                batteryMetric$Timer9 = batteryMetric$UidHealthProto.flashlight_;
                if (batteryMetric$Timer9 == null) {
                    batteryMetric$Timer9 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer9 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 8388608) != 0) {
                batteryMetric$Timer10 = batteryMetric$UidHealthProto2.flashlight_;
                if (batteryMetric$Timer10 == null) {
                    batteryMetric$Timer10 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer10 = null;
            }
            BatteryMetric$Timer subtract5 = subtract(batteryMetric$Timer9, batteryMetric$Timer10);
            if (subtract5 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$15400((BatteryMetric$UidHealthProto) builder.instance, subtract5);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 16777216) != 0) {
                batteryMetric$Timer11 = batteryMetric$UidHealthProto.camera_;
                if (batteryMetric$Timer11 == null) {
                    batteryMetric$Timer11 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer11 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 16777216) != 0) {
                batteryMetric$Timer12 = batteryMetric$UidHealthProto2.camera_;
                if (batteryMetric$Timer12 == null) {
                    batteryMetric$Timer12 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer12 = null;
            }
            BatteryMetric$Timer subtract6 = subtract(batteryMetric$Timer11, batteryMetric$Timer12);
            if (subtract6 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$15700((BatteryMetric$UidHealthProto) builder.instance, subtract6);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 33554432) != 0) {
                batteryMetric$Timer13 = batteryMetric$UidHealthProto.foregroundActivity_;
                if (batteryMetric$Timer13 == null) {
                    batteryMetric$Timer13 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer13 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 33554432) != 0) {
                batteryMetric$Timer14 = batteryMetric$UidHealthProto2.foregroundActivity_;
                if (batteryMetric$Timer14 == null) {
                    batteryMetric$Timer14 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer14 = null;
            }
            BatteryMetric$Timer subtract7 = subtract(batteryMetric$Timer13, batteryMetric$Timer14);
            if (subtract7 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$16000((BatteryMetric$UidHealthProto) builder.instance, subtract7);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 67108864) != 0) {
                batteryMetric$Timer15 = batteryMetric$UidHealthProto.bluetoothScan_;
                if (batteryMetric$Timer15 == null) {
                    batteryMetric$Timer15 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer15 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 67108864) != 0) {
                batteryMetric$Timer16 = batteryMetric$UidHealthProto2.bluetoothScan_;
                if (batteryMetric$Timer16 == null) {
                    batteryMetric$Timer16 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer16 = null;
            }
            BatteryMetric$Timer subtract8 = subtract(batteryMetric$Timer15, batteryMetric$Timer16);
            if (subtract8 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$16300((BatteryMetric$UidHealthProto) builder.instance, subtract8);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 134217728) != 0) {
                batteryMetric$Timer17 = batteryMetric$UidHealthProto.processStateTopMs_;
                if (batteryMetric$Timer17 == null) {
                    batteryMetric$Timer17 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer17 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 134217728) != 0) {
                batteryMetric$Timer18 = batteryMetric$UidHealthProto2.processStateTopMs_;
                if (batteryMetric$Timer18 == null) {
                    batteryMetric$Timer18 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer18 = null;
            }
            BatteryMetric$Timer subtract9 = subtract(batteryMetric$Timer17, batteryMetric$Timer18);
            if (subtract9 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$16600((BatteryMetric$UidHealthProto) builder.instance, subtract9);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 268435456) != 0) {
                batteryMetric$Timer19 = batteryMetric$UidHealthProto.processStateForegroundServiceMs_;
                if (batteryMetric$Timer19 == null) {
                    batteryMetric$Timer19 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer19 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 268435456) != 0) {
                batteryMetric$Timer20 = batteryMetric$UidHealthProto2.processStateForegroundServiceMs_;
                if (batteryMetric$Timer20 == null) {
                    batteryMetric$Timer20 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer20 = null;
            }
            BatteryMetric$Timer subtract10 = subtract(batteryMetric$Timer19, batteryMetric$Timer20);
            if (subtract10 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$16900((BatteryMetric$UidHealthProto) builder.instance, subtract10);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 536870912) != 0) {
                batteryMetric$Timer21 = batteryMetric$UidHealthProto.processStateTopSleepingMs_;
                if (batteryMetric$Timer21 == null) {
                    batteryMetric$Timer21 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer21 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 536870912) != 0) {
                batteryMetric$Timer22 = batteryMetric$UidHealthProto2.processStateTopSleepingMs_;
                if (batteryMetric$Timer22 == null) {
                    batteryMetric$Timer22 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer22 = null;
            }
            BatteryMetric$Timer subtract11 = subtract(batteryMetric$Timer21, batteryMetric$Timer22);
            if (subtract11 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$17200((BatteryMetric$UidHealthProto) builder.instance, subtract11);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & 1073741824) != 0) {
                batteryMetric$Timer23 = batteryMetric$UidHealthProto.processStateForegroundMs_;
                if (batteryMetric$Timer23 == null) {
                    batteryMetric$Timer23 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer23 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & 1073741824) != 0) {
                batteryMetric$Timer24 = batteryMetric$UidHealthProto2.processStateForegroundMs_;
                if (batteryMetric$Timer24 == null) {
                    batteryMetric$Timer24 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer24 = null;
            }
            BatteryMetric$Timer subtract12 = subtract(batteryMetric$Timer23, batteryMetric$Timer24);
            if (subtract12 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$17500((BatteryMetric$UidHealthProto) builder.instance, subtract12);
            }
            if ((batteryMetric$UidHealthProto.bitField0_ & Integer.MIN_VALUE) != 0) {
                batteryMetric$Timer25 = batteryMetric$UidHealthProto.processStateBackgroundMs_;
                if (batteryMetric$Timer25 == null) {
                    batteryMetric$Timer25 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer25 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField0_ & Integer.MIN_VALUE) != 0) {
                batteryMetric$Timer26 = batteryMetric$UidHealthProto2.processStateBackgroundMs_;
                if (batteryMetric$Timer26 == null) {
                    batteryMetric$Timer26 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer26 = null;
            }
            BatteryMetric$Timer subtract13 = subtract(batteryMetric$Timer25, batteryMetric$Timer26);
            if (subtract13 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$17800((BatteryMetric$UidHealthProto) builder.instance, subtract13);
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 1) != 0) {
                batteryMetric$Timer27 = batteryMetric$UidHealthProto.processStateCachedMs_;
                if (batteryMetric$Timer27 == null) {
                    batteryMetric$Timer27 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer27 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField1_ & 1) != 0) {
                batteryMetric$Timer28 = batteryMetric$UidHealthProto2.processStateCachedMs_;
                if (batteryMetric$Timer28 == null) {
                    batteryMetric$Timer28 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer28 = null;
            }
            BatteryMetric$Timer subtract14 = subtract(batteryMetric$Timer27, batteryMetric$Timer28);
            if (subtract14 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$18100((BatteryMetric$UidHealthProto) builder.instance, subtract14);
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 2) != 0) {
                batteryMetric$Timer29 = batteryMetric$UidHealthProto.vibrator_;
                if (batteryMetric$Timer29 == null) {
                    batteryMetric$Timer29 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer29 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField1_ & 2) != 0) {
                batteryMetric$Timer30 = batteryMetric$UidHealthProto2.vibrator_;
                if (batteryMetric$Timer30 == null) {
                    batteryMetric$Timer30 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer30 = null;
            }
            BatteryMetric$Timer subtract15 = subtract(batteryMetric$Timer29, batteryMetric$Timer30);
            if (subtract15 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$18400((BatteryMetric$UidHealthProto) builder.instance, subtract15);
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 4) != 0) {
                long j20 = batteryMetric$UidHealthProto.otherUserActivityCount_ - batteryMetric$UidHealthProto2.otherUserActivityCount_;
                if (j20 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$18700((BatteryMetric$UidHealthProto) builder.instance, j20);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 8) != 0) {
                long j21 = batteryMetric$UidHealthProto.buttonUserActivityCount_ - batteryMetric$UidHealthProto2.buttonUserActivityCount_;
                if (j21 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$18900((BatteryMetric$UidHealthProto) builder.instance, j21);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 16) != 0) {
                long j22 = batteryMetric$UidHealthProto.touchUserActivityCount_ - batteryMetric$UidHealthProto2.touchUserActivityCount_;
                if (j22 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$19100((BatteryMetric$UidHealthProto) builder.instance, j22);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 32) != 0) {
                long j23 = batteryMetric$UidHealthProto.mobileRxBytes_ - batteryMetric$UidHealthProto2.mobileRxBytes_;
                if (j23 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$19300((BatteryMetric$UidHealthProto) builder.instance, j23);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 64) != 0) {
                long j24 = batteryMetric$UidHealthProto.mobileTxBytes_ - batteryMetric$UidHealthProto2.mobileTxBytes_;
                if (j24 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$19500((BatteryMetric$UidHealthProto) builder.instance, j24);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) != 0) {
                long j25 = batteryMetric$UidHealthProto.wifiRxBytes_ - batteryMetric$UidHealthProto2.wifiRxBytes_;
                if (j25 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$19700((BatteryMetric$UidHealthProto) builder.instance, j25);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 256) != 0) {
                long j26 = batteryMetric$UidHealthProto.wifiTxBytes_ - batteryMetric$UidHealthProto2.wifiTxBytes_;
                if (j26 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$19900((BatteryMetric$UidHealthProto) builder.instance, j26);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 512) != 0) {
                long j27 = batteryMetric$UidHealthProto.bluetoothRxBytes_ - batteryMetric$UidHealthProto2.bluetoothRxBytes_;
                if (j27 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$20100((BatteryMetric$UidHealthProto) builder.instance, j27);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 1024) != 0) {
                long j28 = batteryMetric$UidHealthProto.bluetoothTxBytes_ - batteryMetric$UidHealthProto2.bluetoothTxBytes_;
                if (j28 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$20300((BatteryMetric$UidHealthProto) builder.instance, j28);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 2048) != 0) {
                long j29 = batteryMetric$UidHealthProto.mobileRxPackets_ - batteryMetric$UidHealthProto2.mobileRxPackets_;
                if (j29 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$20500((BatteryMetric$UidHealthProto) builder.instance, j29);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 4096) != 0) {
                long j30 = batteryMetric$UidHealthProto.mobileTxPackets_ - batteryMetric$UidHealthProto2.mobileTxPackets_;
                if (j30 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$20700((BatteryMetric$UidHealthProto) builder.instance, j30);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 8192) != 0) {
                long j31 = batteryMetric$UidHealthProto.wifiRxPackets_ - batteryMetric$UidHealthProto2.wifiRxPackets_;
                if (j31 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$20900((BatteryMetric$UidHealthProto) builder.instance, j31);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 16384) != 0) {
                long j32 = batteryMetric$UidHealthProto.wifiTxPackets_ - batteryMetric$UidHealthProto2.wifiTxPackets_;
                if (j32 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$21100((BatteryMetric$UidHealthProto) builder.instance, j32);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 32768) != 0) {
                long j33 = batteryMetric$UidHealthProto.bluetoothRxPackets_ - batteryMetric$UidHealthProto2.bluetoothRxPackets_;
                if (j33 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$21300((BatteryMetric$UidHealthProto) builder.instance, j33);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 65536) != 0) {
                long j34 = batteryMetric$UidHealthProto.bluetoothTxPackets_ - batteryMetric$UidHealthProto2.bluetoothTxPackets_;
                if (j34 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$21500((BatteryMetric$UidHealthProto) builder.instance, j34);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 131072) != 0) {
                batteryMetric$Timer31 = batteryMetric$UidHealthProto.mobileRadioActive_;
                if (batteryMetric$Timer31 == null) {
                    batteryMetric$Timer31 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer31 = null;
            }
            if ((batteryMetric$UidHealthProto2.bitField1_ & 131072) != 0) {
                batteryMetric$Timer32 = batteryMetric$UidHealthProto2.mobileRadioActive_;
                if (batteryMetric$Timer32 == null) {
                    batteryMetric$Timer32 = BatteryMetric$Timer.DEFAULT_INSTANCE;
                }
            } else {
                batteryMetric$Timer32 = null;
            }
            BatteryMetric$Timer subtract16 = subtract(batteryMetric$Timer31, batteryMetric$Timer32);
            if (subtract16 != null) {
                builder.copyOnWrite();
                BatteryMetric$UidHealthProto.access$21700((BatteryMetric$UidHealthProto) builder.instance, subtract16);
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 262144) != 0) {
                long j35 = batteryMetric$UidHealthProto.userCpuTimeMs_ - batteryMetric$UidHealthProto2.userCpuTimeMs_;
                if (j35 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$22000((BatteryMetric$UidHealthProto) builder.instance, j35);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 524288) != 0) {
                long j36 = batteryMetric$UidHealthProto.systemCpuTimeMs_ - batteryMetric$UidHealthProto2.systemCpuTimeMs_;
                if (j36 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$22200((BatteryMetric$UidHealthProto) builder.instance, j36);
                }
            }
            if ((batteryMetric$UidHealthProto.bitField1_ & 1048576) != 0) {
                long j37 = batteryMetric$UidHealthProto.cpuPowerMams_ - batteryMetric$UidHealthProto2.cpuPowerMams_;
                if (j37 != 0) {
                    builder.copyOnWrite();
                    BatteryMetric$UidHealthProto.access$22400((BatteryMetric$UidHealthProto) builder.instance, j37);
                }
            }
            batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) builder.build();
            if (isZero(batteryMetric$UidHealthProto)) {
                return null;
            }
        }
        return batteryMetric$UidHealthProto;
    }
}
