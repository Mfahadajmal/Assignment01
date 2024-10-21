package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.room.util.SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda2;
import com.google.android.gms.clearcut.AbstractLogEventBuilder;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpClient;
import com.google.android.gms.phenotype.PhenotypeClient;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.core.MetricStamper;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingServiceScheduler_Factory;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.startup.WarmStartupConfiguration$WarmStartupDelayType;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.HermeticFileOverridesReader$CachingReader;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.android.libraries.phenotype.client.api.PhenotypeRuntimeException;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl;
import com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3;
import com.google.android.libraries.phenotype.client.stable.FlagStore;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.libraries.phenotype.client.stable.StorageInfoHandler;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Pair;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import googledata.experiments.mobile.primes_android.features.MetricTransmitterFeature;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ Object ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v34, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v37, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v78, types: [com.google.common.base.Supplier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v8, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v20, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object, dagger.Lazy] */
    @Override // com.google.common.base.Supplier
    public final Object get() {
        int i = 3;
        switch (this.switching_field) {
            case 0:
                return ((Context) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0).getSharedPreferences("primes", 0);
            case 1:
                return new GoogleHelpClient((Activity) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0);
            case 2:
                return this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0.get();
            case 3:
                return (Boolean) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0.get();
            case 4:
                return ImmutableList.sortedCopyOf(new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(11), (Iterable) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0.get());
            case 5:
                return Long.valueOf(((MetricStamper) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0).dataPartitionSize$ar$class_merging.getDataPartition().getTotalSpace() / 1024);
            case 6:
                return ((CpuProfilingServiceScheduler_Factory) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0).get();
            case 7:
                Object obj = this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0;
                long j = DisplayStats.maxFrameRenderTimeNs;
                if (j == 0) {
                    synchronized (DisplayStats.class) {
                        j = DisplayStats.maxFrameRenderTimeNs;
                        if (j == 0) {
                            float f = 60.0f;
                            float floatValue = ((Float) DisplayStats.getRefreshRate((Context) obj).or(Float.valueOf(60.0f))).floatValue();
                            if (floatValue >= 1.0f) {
                                f = floatValue;
                            }
                            long ceil = (long) Math.ceil(1.0E9d / f);
                            DisplayStats.maxFrameRenderTimeNs = ceil;
                            j = ceil;
                        }
                    }
                }
                return Long.valueOf(j);
            case 8:
                return (MemoryConfigurations) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0.get();
            case 9:
                int intValue = ((Long) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0.get()).intValue();
                for (WarmStartupConfiguration$WarmStartupDelayType warmStartupConfiguration$WarmStartupDelayType : WarmStartupConfiguration$WarmStartupDelayType.values()) {
                    if (intValue == warmStartupConfiguration$WarmStartupDelayType.value) {
                        return warmStartupConfiguration$WarmStartupDelayType;
                    }
                }
                return WarmStartupConfiguration$WarmStartupDelayType.DELAY_UNSPECIFIED;
            case 10:
                return Boolean.valueOf(MetricTransmitterFeature.INSTANCE.get().usePackedHistogramEncodingInClearcut((Context) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0));
            case 11:
                return new PhenotypeContext((Context) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0);
            case 12:
                return new StatsStorage(new PhenotypeClient((Context) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0));
            case 13:
                return new OptionalMethod((List) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0);
            case 14:
                Context context = PhenotypeContext.applicationContext;
                try {
                    return Optional.of(((Context) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0).getPackageManager().getApplicationInfo("com.google.android.gms", 0));
                } catch (PackageManager.NameNotFoundException unused) {
                    return Absent.INSTANCE;
                }
            case 15:
                int i2 = PhenotypeFlag.PhenotypeFlag$ar$NoOp;
                return HermeticFileOverridesReader$CachingReader.readFromFileAndCacheIfEligible((Context) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0);
            case 16:
                DefaultExperimentTokenDecorator.get();
                AbstractLogEventBuilder abstractLogEventBuilder = (AbstractLogEventBuilder) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0;
                Set set = (Set) ExperimentTokenDecoratorImpl.accountScopedLogSourceKeyMap.get(new Pair(abstractLogEventBuilder.logSourceName, abstractLogEventBuilder.uploadAccountName));
                if (set != null) {
                    return set;
                }
                return RegularImmutableSet.EMPTY;
            case 17:
                DefaultExperimentTokenDecorator.get();
                Set set2 = (Set) ExperimentTokenDecoratorImpl.deviceScopedLogSourceKeyMap.get(((AbstractLogEventBuilder) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0).logSourceName);
                if (set2 != null) {
                    return set2;
                }
                return RegularImmutableSet.EMPTY;
            case 18:
                return ((FlagStore) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0).commitToSnapshot();
            case 19:
                Object obj2 = this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0;
                StorageInfoHandler storageInfoHandler = (StorageInfoHandler) obj2;
                ListeningScheduledExecutorService listeningScheduledExecutorService = (ListeningScheduledExecutorService) storageInfoHandler.executorProvider.get();
                listeningScheduledExecutorService.getClass();
                StatsStorage statsStorage = (StatsStorage) storageInfoHandler.clientProvider.get();
                statsStorage.getClass();
                ListenableFuture create = AbstractTransformFuture.create(AbstractCatchingFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(statsStorage.getStorageInfo()), PhenotypeRuntimeException.class, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(i), listeningScheduledExecutorService), new AiCoreClientImpl$$ExternalSyntheticLambda2(obj2, 8), listeningScheduledExecutorService);
                create.addListener(new FlagStore$$ExternalSyntheticLambda3(create, 5), listeningScheduledExecutorService);
                return create;
            default:
                ListeningScheduledExecutorService listeningScheduledExecutorService2 = (ListeningScheduledExecutorService) this.ConfigurationsModule$$ExternalSyntheticLambda0$ar$f$0.get();
                listeningScheduledExecutorService2.getClass();
                return listeningScheduledExecutorService2.schedule(new TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0(3), 10000L, TimeUnit.MILLISECONDS);
        }
    }
}
