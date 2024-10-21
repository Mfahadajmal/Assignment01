package com.google.android.accessibility.selecttospeak;

import android.app.Application;
import android.content.Context;
import com.google.android.libraries.performance.primes.PrimesConfigurations;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter;
import googledata.experiments.mobile.accessibility_suite.features.PrimesConfig;
import googledata.experiments.mobile.accessibility_suite.features.PrimesConfigFlags;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PrimesController$$ExternalSyntheticLambda8 implements Provider {
    public final /* synthetic */ ClearcutMetricTransmitter f$0;
    public final /* synthetic */ Application f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PrimesController$$ExternalSyntheticLambda8(ClearcutMetricTransmitter clearcutMetricTransmitter, Application application, int i) {
        this.switching_field = i;
        this.f$0 = clearcutMetricTransmitter;
        this.f$1 = application;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        int i = this.switching_field;
        final int i2 = 0;
        if (i != 0) {
            final int i3 = 2;
            final int i4 = 1;
            if (i != 1) {
                PrimesConfigurations.Builder builder = new PrimesConfigurations.Builder(null);
                final ClearcutMetricTransmitter clearcutMetricTransmitter = this.f$0;
                final int i5 = 14;
                PrimesConfigurations.Builder metricTransmitterProvider = builder.setMetricTransmitterProvider(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                    @Override // javax.inject.Provider
                    public final Object get() {
                        switch (i5) {
                            case 0:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter, "accessibility-suite:enable_memory_metric")).build();
                            case 1:
                                return clearcutMetricTransmitter;
                            case 2:
                                return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter, "accessibility-suite:enable_timer_metric")).m203build();
                            case 3:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter, "accessibility-suite:enable_crash_metric")).build();
                            case 4:
                                NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter, "accessibility-suite:enable_network_metric"));
                                enabled.setBatchSize$ar$ds(1);
                                return enabled.build();
                            case 5:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter, "accessibility-suite:enable_package_metric")).m201build();
                            case 6:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter, "accessibility-suite:enable_battery_metric")).build();
                            case 7:
                                return clearcutMetricTransmitter;
                            case 8:
                                return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter, "accessibility-suite:enable_memory_metric")).build();
                            case 9:
                                return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter, "accessibility-suite:enable_timer_metric")).m203build();
                            case 10:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter, "accessibility-suite:enable_crash_metric")).build();
                            case 11:
                                NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter, "accessibility-suite:enable_network_metric"));
                                enabled2.setBatchSize$ar$ds(1);
                                return enabled2.build();
                            case 12:
                                return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter, "accessibility-suite:enable_package_metric")).m201build();
                            case 13:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter, "accessibility-suite:enable_battery_metric")).build();
                            case 14:
                                return clearcutMetricTransmitter;
                            case 15:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) clearcutMetricTransmitter)).build();
                            case 16:
                                TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                                PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                                Context context = (Context) clearcutMetricTransmitter;
                                TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                                enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                                return enabled3.m203build();
                            case 17:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) clearcutMetricTransmitter)).build();
                            case 18:
                                NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) clearcutMetricTransmitter));
                                enabled4.setBatchSize$ar$ds(1);
                                return enabled4.build();
                            case 19:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) clearcutMetricTransmitter)).m201build();
                            default:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) clearcutMetricTransmitter)).build();
                        }
                    }
                });
                final Application application = this.f$1;
                final int i6 = 15;
                metricTransmitterProvider.setMemoryConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                    @Override // javax.inject.Provider
                    public final Object get() {
                        switch (i6) {
                            case 0:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 1:
                                return application;
                            case 2:
                                return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 3:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 4:
                                NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled.setBatchSize$ar$ds(1);
                                return enabled.build();
                            case 5:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 6:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 7:
                                return application;
                            case 8:
                                return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 9:
                                return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 10:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 11:
                                NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled2.setBatchSize$ar$ds(1);
                                return enabled2.build();
                            case 12:
                                return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 13:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 14:
                                return application;
                            case 15:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application)).build();
                            case 16:
                                TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                                PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                                Context context = (Context) application;
                                TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                                enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                                return enabled3.m203build();
                            case 17:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application)).build();
                            case 18:
                                NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application));
                                enabled4.setBatchSize$ar$ds(1);
                                return enabled4.build();
                            case 19:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application)).m201build();
                            default:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application)).build();
                        }
                    }
                });
                final int i7 = 16;
                metricTransmitterProvider.setTimerConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                    @Override // javax.inject.Provider
                    public final Object get() {
                        switch (i7) {
                            case 0:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 1:
                                return application;
                            case 2:
                                return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 3:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 4:
                                NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled.setBatchSize$ar$ds(1);
                                return enabled.build();
                            case 5:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 6:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 7:
                                return application;
                            case 8:
                                return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 9:
                                return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 10:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 11:
                                NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled2.setBatchSize$ar$ds(1);
                                return enabled2.build();
                            case 12:
                                return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 13:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 14:
                                return application;
                            case 15:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application)).build();
                            case 16:
                                TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                                PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                                Context context = (Context) application;
                                TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                                enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                                return enabled3.m203build();
                            case 17:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application)).build();
                            case 18:
                                NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application));
                                enabled4.setBatchSize$ar$ds(1);
                                return enabled4.build();
                            case 19:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application)).m201build();
                            default:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application)).build();
                        }
                    }
                });
                final int i8 = 17;
                metricTransmitterProvider.setCrashConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                    @Override // javax.inject.Provider
                    public final Object get() {
                        switch (i8) {
                            case 0:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 1:
                                return application;
                            case 2:
                                return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 3:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 4:
                                NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled.setBatchSize$ar$ds(1);
                                return enabled.build();
                            case 5:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 6:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 7:
                                return application;
                            case 8:
                                return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 9:
                                return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 10:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 11:
                                NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled2.setBatchSize$ar$ds(1);
                                return enabled2.build();
                            case 12:
                                return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 13:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 14:
                                return application;
                            case 15:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application)).build();
                            case 16:
                                TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                                PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                                Context context = (Context) application;
                                TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                                enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                                return enabled3.m203build();
                            case 17:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application)).build();
                            case 18:
                                NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application));
                                enabled4.setBatchSize$ar$ds(1);
                                return enabled4.build();
                            case 19:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application)).m201build();
                            default:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application)).build();
                        }
                    }
                });
                final int i9 = 18;
                metricTransmitterProvider.setNetworkConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                    @Override // javax.inject.Provider
                    public final Object get() {
                        switch (i9) {
                            case 0:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 1:
                                return application;
                            case 2:
                                return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 3:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 4:
                                NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled.setBatchSize$ar$ds(1);
                                return enabled.build();
                            case 5:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 6:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 7:
                                return application;
                            case 8:
                                return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 9:
                                return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 10:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 11:
                                NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled2.setBatchSize$ar$ds(1);
                                return enabled2.build();
                            case 12:
                                return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 13:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 14:
                                return application;
                            case 15:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application)).build();
                            case 16:
                                TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                                PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                                Context context = (Context) application;
                                TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                                enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                                return enabled3.m203build();
                            case 17:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application)).build();
                            case 18:
                                NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application));
                                enabled4.setBatchSize$ar$ds(1);
                                return enabled4.build();
                            case 19:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application)).m201build();
                            default:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application)).build();
                        }
                    }
                });
                final int i10 = 19;
                metricTransmitterProvider.setStorageConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                    @Override // javax.inject.Provider
                    public final Object get() {
                        switch (i10) {
                            case 0:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 1:
                                return application;
                            case 2:
                                return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 3:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 4:
                                NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled.setBatchSize$ar$ds(1);
                                return enabled.build();
                            case 5:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 6:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 7:
                                return application;
                            case 8:
                                return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 9:
                                return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 10:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 11:
                                NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled2.setBatchSize$ar$ds(1);
                                return enabled2.build();
                            case 12:
                                return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 13:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 14:
                                return application;
                            case 15:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application)).build();
                            case 16:
                                TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                                PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                                Context context = (Context) application;
                                TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                                enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                                return enabled3.m203build();
                            case 17:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application)).build();
                            case 18:
                                NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application));
                                enabled4.setBatchSize$ar$ds(1);
                                return enabled4.build();
                            case 19:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application)).m201build();
                            default:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application)).build();
                        }
                    }
                });
                final int i11 = 20;
                metricTransmitterProvider.setBatteryConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                    @Override // javax.inject.Provider
                    public final Object get() {
                        switch (i11) {
                            case 0:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 1:
                                return application;
                            case 2:
                                return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 3:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 4:
                                NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled.setBatchSize$ar$ds(1);
                                return enabled.build();
                            case 5:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 6:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 7:
                                return application;
                            case 8:
                                return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_memory_metric")).build();
                            case 9:
                                return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_timer_metric")).m203build();
                            case 10:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_crash_metric")).build();
                            case 11:
                                NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_network_metric"));
                                enabled2.setBatchSize$ar$ds(1);
                                return enabled2.build();
                            case 12:
                                return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_package_metric")).m201build();
                            case 13:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application, "accessibility-suite:enable_battery_metric")).build();
                            case 14:
                                return application;
                            case 15:
                                return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application)).build();
                            case 16:
                                TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                                PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                                Context context = (Context) application;
                                TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                                enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                                return enabled3.m203build();
                            case 17:
                                return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application)).build();
                            case 18:
                                NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application));
                                enabled4.setBatchSize$ar$ds(1);
                                return enabled4.build();
                            case 19:
                                return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application)).m201build();
                            default:
                                return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application)).build();
                        }
                    }
                });
                metricTransmitterProvider.setGlobalConfigurationsProvider$ar$ds(new PrimesController$$ExternalSyntheticLambda7(2));
                return metricTransmitterProvider.build();
            }
            PrimesConfigurations.Builder builder2 = new PrimesConfigurations.Builder(null);
            final ClearcutMetricTransmitter clearcutMetricTransmitter2 = this.f$0;
            PrimesConfigurations.Builder metricTransmitterProvider2 = builder2.setMetricTransmitterProvider(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                @Override // javax.inject.Provider
                public final Object get() {
                    switch (i4) {
                        case 0:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_memory_metric")).build();
                        case 1:
                            return clearcutMetricTransmitter2;
                        case 2:
                            return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 3:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_crash_metric")).build();
                        case 4:
                            NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_network_metric"));
                            enabled.setBatchSize$ar$ds(1);
                            return enabled.build();
                        case 5:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_package_metric")).m201build();
                        case 6:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_battery_metric")).build();
                        case 7:
                            return clearcutMetricTransmitter2;
                        case 8:
                            return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_memory_metric")).build();
                        case 9:
                            return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 10:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_crash_metric")).build();
                        case 11:
                            NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_network_metric"));
                            enabled2.setBatchSize$ar$ds(1);
                            return enabled2.build();
                        case 12:
                            return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_package_metric")).m201build();
                        case 13:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter2, "accessibility-suite:enable_battery_metric")).build();
                        case 14:
                            return clearcutMetricTransmitter2;
                        case 15:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) clearcutMetricTransmitter2)).build();
                        case 16:
                            TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                            PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                            Context context = (Context) clearcutMetricTransmitter2;
                            TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                            enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                            return enabled3.m203build();
                        case 17:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) clearcutMetricTransmitter2)).build();
                        case 18:
                            NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) clearcutMetricTransmitter2));
                            enabled4.setBatchSize$ar$ds(1);
                            return enabled4.build();
                        case 19:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) clearcutMetricTransmitter2)).m201build();
                        default:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) clearcutMetricTransmitter2)).build();
                    }
                }
            });
            final Application application2 = this.f$1;
            metricTransmitterProvider2.setMemoryConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                @Override // javax.inject.Provider
                public final Object get() {
                    switch (i2) {
                        case 0:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 1:
                            return application2;
                        case 2:
                            return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 3:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 4:
                            NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled.setBatchSize$ar$ds(1);
                            return enabled.build();
                        case 5:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 6:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 7:
                            return application2;
                        case 8:
                            return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 9:
                            return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 10:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 11:
                            NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled2.setBatchSize$ar$ds(1);
                            return enabled2.build();
                        case 12:
                            return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 13:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 14:
                            return application2;
                        case 15:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application2)).build();
                        case 16:
                            TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                            PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                            Context context = (Context) application2;
                            TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                            enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                            return enabled3.m203build();
                        case 17:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application2)).build();
                        case 18:
                            NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application2));
                            enabled4.setBatchSize$ar$ds(1);
                            return enabled4.build();
                        case 19:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application2)).m201build();
                        default:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application2)).build();
                    }
                }
            });
            metricTransmitterProvider2.setTimerConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                @Override // javax.inject.Provider
                public final Object get() {
                    switch (i3) {
                        case 0:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 1:
                            return application2;
                        case 2:
                            return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 3:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 4:
                            NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled.setBatchSize$ar$ds(1);
                            return enabled.build();
                        case 5:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 6:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 7:
                            return application2;
                        case 8:
                            return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 9:
                            return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 10:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 11:
                            NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled2.setBatchSize$ar$ds(1);
                            return enabled2.build();
                        case 12:
                            return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 13:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 14:
                            return application2;
                        case 15:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application2)).build();
                        case 16:
                            TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                            PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                            Context context = (Context) application2;
                            TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                            enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                            return enabled3.m203build();
                        case 17:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application2)).build();
                        case 18:
                            NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application2));
                            enabled4.setBatchSize$ar$ds(1);
                            return enabled4.build();
                        case 19:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application2)).m201build();
                        default:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application2)).build();
                    }
                }
            });
            final int i12 = 3;
            metricTransmitterProvider2.setCrashConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                @Override // javax.inject.Provider
                public final Object get() {
                    switch (i12) {
                        case 0:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 1:
                            return application2;
                        case 2:
                            return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 3:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 4:
                            NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled.setBatchSize$ar$ds(1);
                            return enabled.build();
                        case 5:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 6:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 7:
                            return application2;
                        case 8:
                            return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 9:
                            return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 10:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 11:
                            NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled2.setBatchSize$ar$ds(1);
                            return enabled2.build();
                        case 12:
                            return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 13:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 14:
                            return application2;
                        case 15:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application2)).build();
                        case 16:
                            TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                            PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                            Context context = (Context) application2;
                            TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                            enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                            return enabled3.m203build();
                        case 17:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application2)).build();
                        case 18:
                            NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application2));
                            enabled4.setBatchSize$ar$ds(1);
                            return enabled4.build();
                        case 19:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application2)).m201build();
                        default:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application2)).build();
                    }
                }
            });
            final int i13 = 4;
            metricTransmitterProvider2.setNetworkConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                @Override // javax.inject.Provider
                public final Object get() {
                    switch (i13) {
                        case 0:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 1:
                            return application2;
                        case 2:
                            return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 3:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 4:
                            NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled.setBatchSize$ar$ds(1);
                            return enabled.build();
                        case 5:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 6:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 7:
                            return application2;
                        case 8:
                            return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 9:
                            return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 10:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 11:
                            NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled2.setBatchSize$ar$ds(1);
                            return enabled2.build();
                        case 12:
                            return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 13:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 14:
                            return application2;
                        case 15:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application2)).build();
                        case 16:
                            TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                            PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                            Context context = (Context) application2;
                            TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                            enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                            return enabled3.m203build();
                        case 17:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application2)).build();
                        case 18:
                            NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application2));
                            enabled4.setBatchSize$ar$ds(1);
                            return enabled4.build();
                        case 19:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application2)).m201build();
                        default:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application2)).build();
                    }
                }
            });
            final int i14 = 5;
            metricTransmitterProvider2.setStorageConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                @Override // javax.inject.Provider
                public final Object get() {
                    switch (i14) {
                        case 0:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 1:
                            return application2;
                        case 2:
                            return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 3:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 4:
                            NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled.setBatchSize$ar$ds(1);
                            return enabled.build();
                        case 5:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 6:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 7:
                            return application2;
                        case 8:
                            return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 9:
                            return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 10:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 11:
                            NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled2.setBatchSize$ar$ds(1);
                            return enabled2.build();
                        case 12:
                            return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 13:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 14:
                            return application2;
                        case 15:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application2)).build();
                        case 16:
                            TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                            PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                            Context context = (Context) application2;
                            TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                            enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                            return enabled3.m203build();
                        case 17:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application2)).build();
                        case 18:
                            NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application2));
                            enabled4.setBatchSize$ar$ds(1);
                            return enabled4.build();
                        case 19:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application2)).m201build();
                        default:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application2)).build();
                    }
                }
            });
            final int i15 = 6;
            metricTransmitterProvider2.setBatteryConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
                @Override // javax.inject.Provider
                public final Object get() {
                    switch (i15) {
                        case 0:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 1:
                            return application2;
                        case 2:
                            return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 3:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 4:
                            NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled.setBatchSize$ar$ds(1);
                            return enabled.build();
                        case 5:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 6:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 7:
                            return application2;
                        case 8:
                            return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_memory_metric")).build();
                        case 9:
                            return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_timer_metric")).m203build();
                        case 10:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_crash_metric")).build();
                        case 11:
                            NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_network_metric"));
                            enabled2.setBatchSize$ar$ds(1);
                            return enabled2.build();
                        case 12:
                            return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_package_metric")).m201build();
                        case 13:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application2, "accessibility-suite:enable_battery_metric")).build();
                        case 14:
                            return application2;
                        case 15:
                            return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application2)).build();
                        case 16:
                            TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                            PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                            Context context = (Context) application2;
                            TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                            enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                            return enabled3.m203build();
                        case 17:
                            return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application2)).build();
                        case 18:
                            NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application2));
                            enabled4.setBatchSize$ar$ds(1);
                            return enabled4.build();
                        case 19:
                            return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application2)).m201build();
                        default:
                            return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application2)).build();
                    }
                }
            });
            metricTransmitterProvider2.setGlobalConfigurationsProvider$ar$ds(new PrimesController$$ExternalSyntheticLambda7(1));
            return metricTransmitterProvider2.build();
        }
        PrimesConfigurations.Builder builder3 = new PrimesConfigurations.Builder(null);
        final ClearcutMetricTransmitter clearcutMetricTransmitter3 = this.f$0;
        final int i16 = 7;
        PrimesConfigurations.Builder metricTransmitterProvider3 = builder3.setMetricTransmitterProvider(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
            @Override // javax.inject.Provider
            public final Object get() {
                switch (i16) {
                    case 0:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_memory_metric")).build();
                    case 1:
                        return clearcutMetricTransmitter3;
                    case 2:
                        return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 3:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_crash_metric")).build();
                    case 4:
                        NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_network_metric"));
                        enabled.setBatchSize$ar$ds(1);
                        return enabled.build();
                    case 5:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_package_metric")).m201build();
                    case 6:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_battery_metric")).build();
                    case 7:
                        return clearcutMetricTransmitter3;
                    case 8:
                        return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_memory_metric")).build();
                    case 9:
                        return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 10:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_crash_metric")).build();
                    case 11:
                        NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_network_metric"));
                        enabled2.setBatchSize$ar$ds(1);
                        return enabled2.build();
                    case 12:
                        return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_package_metric")).m201build();
                    case 13:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) clearcutMetricTransmitter3, "accessibility-suite:enable_battery_metric")).build();
                    case 14:
                        return clearcutMetricTransmitter3;
                    case 15:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) clearcutMetricTransmitter3)).build();
                    case 16:
                        TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                        PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                        Context context = (Context) clearcutMetricTransmitter3;
                        TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                        enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                        return enabled3.m203build();
                    case 17:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) clearcutMetricTransmitter3)).build();
                    case 18:
                        NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) clearcutMetricTransmitter3));
                        enabled4.setBatchSize$ar$ds(1);
                        return enabled4.build();
                    case 19:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) clearcutMetricTransmitter3)).m201build();
                    default:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) clearcutMetricTransmitter3)).build();
                }
            }
        });
        final Application application3 = this.f$1;
        final int i17 = 8;
        metricTransmitterProvider3.setMemoryConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
            @Override // javax.inject.Provider
            public final Object get() {
                switch (i17) {
                    case 0:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 1:
                        return application3;
                    case 2:
                        return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 3:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 4:
                        NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled.setBatchSize$ar$ds(1);
                        return enabled.build();
                    case 5:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 6:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 7:
                        return application3;
                    case 8:
                        return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 9:
                        return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 10:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 11:
                        NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled2.setBatchSize$ar$ds(1);
                        return enabled2.build();
                    case 12:
                        return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 13:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 14:
                        return application3;
                    case 15:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application3)).build();
                    case 16:
                        TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                        PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                        Context context = (Context) application3;
                        TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                        enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                        return enabled3.m203build();
                    case 17:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application3)).build();
                    case 18:
                        NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application3));
                        enabled4.setBatchSize$ar$ds(1);
                        return enabled4.build();
                    case 19:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application3)).m201build();
                    default:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application3)).build();
                }
            }
        });
        final int i18 = 9;
        metricTransmitterProvider3.setTimerConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
            @Override // javax.inject.Provider
            public final Object get() {
                switch (i18) {
                    case 0:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 1:
                        return application3;
                    case 2:
                        return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 3:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 4:
                        NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled.setBatchSize$ar$ds(1);
                        return enabled.build();
                    case 5:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 6:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 7:
                        return application3;
                    case 8:
                        return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 9:
                        return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 10:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 11:
                        NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled2.setBatchSize$ar$ds(1);
                        return enabled2.build();
                    case 12:
                        return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 13:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 14:
                        return application3;
                    case 15:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application3)).build();
                    case 16:
                        TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                        PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                        Context context = (Context) application3;
                        TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                        enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                        return enabled3.m203build();
                    case 17:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application3)).build();
                    case 18:
                        NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application3));
                        enabled4.setBatchSize$ar$ds(1);
                        return enabled4.build();
                    case 19:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application3)).m201build();
                    default:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application3)).build();
                }
            }
        });
        final int i19 = 10;
        metricTransmitterProvider3.setCrashConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
            @Override // javax.inject.Provider
            public final Object get() {
                switch (i19) {
                    case 0:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 1:
                        return application3;
                    case 2:
                        return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 3:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 4:
                        NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled.setBatchSize$ar$ds(1);
                        return enabled.build();
                    case 5:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 6:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 7:
                        return application3;
                    case 8:
                        return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 9:
                        return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 10:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 11:
                        NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled2.setBatchSize$ar$ds(1);
                        return enabled2.build();
                    case 12:
                        return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 13:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 14:
                        return application3;
                    case 15:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application3)).build();
                    case 16:
                        TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                        PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                        Context context = (Context) application3;
                        TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                        enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                        return enabled3.m203build();
                    case 17:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application3)).build();
                    case 18:
                        NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application3));
                        enabled4.setBatchSize$ar$ds(1);
                        return enabled4.build();
                    case 19:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application3)).m201build();
                    default:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application3)).build();
                }
            }
        });
        final int i20 = 11;
        metricTransmitterProvider3.setNetworkConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
            @Override // javax.inject.Provider
            public final Object get() {
                switch (i20) {
                    case 0:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 1:
                        return application3;
                    case 2:
                        return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 3:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 4:
                        NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled.setBatchSize$ar$ds(1);
                        return enabled.build();
                    case 5:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 6:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 7:
                        return application3;
                    case 8:
                        return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 9:
                        return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 10:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 11:
                        NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled2.setBatchSize$ar$ds(1);
                        return enabled2.build();
                    case 12:
                        return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 13:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 14:
                        return application3;
                    case 15:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application3)).build();
                    case 16:
                        TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                        PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                        Context context = (Context) application3;
                        TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                        enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                        return enabled3.m203build();
                    case 17:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application3)).build();
                    case 18:
                        NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application3));
                        enabled4.setBatchSize$ar$ds(1);
                        return enabled4.build();
                    case 19:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application3)).m201build();
                    default:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application3)).build();
                }
            }
        });
        final int i21 = 12;
        metricTransmitterProvider3.setStorageConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
            @Override // javax.inject.Provider
            public final Object get() {
                switch (i21) {
                    case 0:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 1:
                        return application3;
                    case 2:
                        return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 3:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 4:
                        NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled.setBatchSize$ar$ds(1);
                        return enabled.build();
                    case 5:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 6:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 7:
                        return application3;
                    case 8:
                        return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 9:
                        return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 10:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 11:
                        NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled2.setBatchSize$ar$ds(1);
                        return enabled2.build();
                    case 12:
                        return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 13:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 14:
                        return application3;
                    case 15:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application3)).build();
                    case 16:
                        TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                        PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                        Context context = (Context) application3;
                        TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                        enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                        return enabled3.m203build();
                    case 17:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application3)).build();
                    case 18:
                        NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application3));
                        enabled4.setBatchSize$ar$ds(1);
                        return enabled4.build();
                    case 19:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application3)).m201build();
                    default:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application3)).build();
                }
            }
        });
        final int i22 = 13;
        metricTransmitterProvider3.setBatteryConfigurationsProvider$ar$ds(new Provider() { // from class: com.google.android.accessibility.accessibilitymenu.PrimesController$$ExternalSyntheticLambda3
            @Override // javax.inject.Provider
            public final Object get() {
                switch (i22) {
                    case 0:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 1:
                        return application3;
                    case 2:
                        return TimerConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 3:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 4:
                        NetworkConfigurations.Builder enabled = NetworkConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled.setBatchSize$ar$ds(1);
                        return enabled.build();
                    case 5:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 6:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesController.getBoolean$ar$ds((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 7:
                        return application3;
                    case 8:
                        return MemoryConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_memory_metric")).build();
                    case 9:
                        return TimerConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_timer_metric")).m203build();
                    case 10:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_crash_metric")).build();
                    case 11:
                        NetworkConfigurations.Builder enabled2 = NetworkConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_network_metric"));
                        enabled2.setBatchSize$ar$ds(1);
                        return enabled2.build();
                    case 12:
                        return StorageConfigurations.newBuilder().setEnabled(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_package_metric")).m201build();
                    case 13:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(com.google.android.accessibility.selecttospeak.PrimesController.getBoolean$ar$ds$53176685_0((Context) application3, "accessibility-suite:enable_battery_metric")).build();
                    case 14:
                        return application3;
                    case 15:
                        return MemoryConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isMemoryMetricEnabled((Context) application3)).build();
                    case 16:
                        TimerConfigurations.Builder newBuilder = TimerConfigurations.newBuilder();
                        PrimesConfigFlags primesConfigFlags = PrimesConfig.INSTANCE.get();
                        Context context = (Context) application3;
                        TimerConfigurations.Builder enabled3 = newBuilder.setEnabled(primesConfigFlags.isTimerMetricEnabled(context));
                        enabled3.setSamplingProbability$ar$ds((float) PrimesConfig.INSTANCE.get().getTimerSamplingProbability(context));
                        return enabled3.m203build();
                    case 17:
                        return CrashConfigurations.newBuilder$ar$class_merging$a37d6a6_0().setEnabled$ar$class_merging$5fe4575b_0(PrimesConfig.INSTANCE.get().isCrashMetricEnabled((Context) application3)).build();
                    case 18:
                        NetworkConfigurations.Builder enabled4 = NetworkConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isNetworkMetricEnabled((Context) application3));
                        enabled4.setBatchSize$ar$ds(1);
                        return enabled4.build();
                    case 19:
                        return StorageConfigurations.newBuilder().setEnabled(PrimesConfig.INSTANCE.get().isPackageMetricEnabled((Context) application3)).m201build();
                    default:
                        return BatteryConfigurations.newBuilder$ar$class_merging$868ce2b7_0().setEnabled$ar$class_merging(PrimesConfig.INSTANCE.get().isBatteryMetricEnabled((Context) application3)).build();
                }
            }
        });
        metricTransmitterProvider3.setGlobalConfigurationsProvider$ar$ds(new PrimesController$$ExternalSyntheticLambda7(0));
        return metricTransmitterProvider3.build();
    }
}
