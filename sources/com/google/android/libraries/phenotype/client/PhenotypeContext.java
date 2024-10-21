package com.google.android.libraries.phenotype.client;

import android.content.Context;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.phenotype.client.PhenotypeContextTestMode;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.libraries.phenotype.client.stable.StorageInfoHandler;
import com.google.android.libraries.storage.file.backends.AndroidFileBackend;
import com.google.android.libraries.storage.file.backends.JavaFileBackend;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.hilt.internal.GeneratedComponentManager;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeContext {
    private final Supplier clientProvider;
    public final Context context;
    private final Supplier executorProvider;
    public volatile ListenableFuture flagUpdateListenerRegistrationFuture;
    public final Object flagUpdateListenerRegistrationLock;
    public final AtomicReference onFlagUpdateFunction;
    public final Supplier optionalGmsCoreApplicationInfo;
    private final Optional optionalProcessReaper;
    public final StorageInfoHandler storageInfoHandler;
    private final Supplier storageProvider;
    private static final Object LOCK = new Object();
    private static final DefaultExperimentTokenDecorator phlogger$ar$class_merging = new DefaultExperimentTokenDecorator(null);
    public static Context applicationContext = null;
    private static volatile PhenotypeContext instanceForNonHilt = null;
    private static volatile PhenotypeContext phenotypeContextForTest = null;
    private static final Supplier EXECUTOR = ContextDataProvider.memoize(new PrimesController$$ExternalSyntheticLambda9(14));

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface PhenotypeApplication {
        Optional getPhenotypeContext();
    }

    @Deprecated
    public PhenotypeContext(Context context) {
        Supplier supplier = EXECUTOR;
        Supplier memoize = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(context, 12));
        Optional of = Optional.of(new PhenotypeProcessReaper(supplier));
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new AndroidFileBackend(new MetricsLogger(context)), new JavaFileBackend());
        Supplier memoize2 = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(arrayList, 13));
        ConfigurationsModule$$ExternalSyntheticLambda0 configurationsModule$$ExternalSyntheticLambda0 = new ConfigurationsModule$$ExternalSyntheticLambda0(context, 14);
        this.flagUpdateListenerRegistrationLock = new Object();
        this.flagUpdateListenerRegistrationFuture = null;
        Context applicationContext2 = context.getApplicationContext();
        applicationContext2.getClass();
        this.context = applicationContext2;
        this.executorProvider = ContextDataProvider.memoize(supplier);
        this.clientProvider = ContextDataProvider.memoize(memoize);
        this.optionalProcessReaper = of;
        this.storageProvider = ContextDataProvider.memoize(memoize2);
        this.storageInfoHandler = new StorageInfoHandler(applicationContext2, supplier, memoize2, memoize);
        this.optionalGmsCoreApplicationInfo = ContextDataProvider.memoize(configurationsModule$$ExternalSyntheticLambda0);
        this.onFlagUpdateFunction = new AtomicReference();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PhenotypeContext getPhenotypeContextFrom(Context context) {
        boolean z;
        PhenotypeContext phenotypeContext = instanceForNonHilt;
        if (phenotypeContext != null) {
            return phenotypeContext;
        }
        Context applicationContext2 = context.getApplicationContext();
        try {
            Object applicationContext3 = applicationContext2.getApplicationContext();
            if (applicationContext3 instanceof GeneratedComponentManager) {
                try {
                    try {
                        return (PhenotypeContext) ((Present) ((PhenotypeApplication) PhenotypeApplication.class.cast(((GeneratedComponentManager) applicationContext3).generatedComponent())).getPhenotypeContext()).reference;
                    } catch (IllegalStateException unused) {
                        z = true;
                        synchronized (LOCK) {
                            if (instanceForNonHilt != null) {
                                return instanceForNonHilt;
                            }
                            Optional optional = Absent.INSTANCE;
                            boolean z2 = applicationContext2 instanceof PhenotypeApplication;
                            if (z2) {
                                optional = ((PhenotypeApplication) applicationContext2).getPhenotypeContext();
                            }
                            PhenotypeContext phenotypeContext2 = (PhenotypeContext) optional.or((Supplier) new ConfigurationsModule$$ExternalSyntheticLambda0(applicationContext2, 11));
                            instanceForNonHilt = phenotypeContext2;
                            if (!z && !z2) {
                                phlogger$ar$class_merging.log(Level.CONFIG, phenotypeContext2.getExecutor(), "Application doesn't implement PhenotypeApplication interface, falling back to globally set context. See go/phenotype-flag#process-stable-init for more info.", new Object[0]);
                            }
                            return phenotypeContext2;
                        }
                    }
                } catch (ClassCastException e) {
                    throw new IllegalStateException("Failed to get an entry point. Did you mark your interface with @SingletonEntryPoint?", e);
                }
            }
            throw new IllegalStateException("Given application context does not implement GeneratedComponentManager: ".concat(String.valueOf(String.valueOf(applicationContext3.getClass()))));
        } catch (IllegalStateException unused2) {
            z = false;
        }
    }

    public static void isTestMode$ar$ds() {
        PhenotypeContextTestMode.testMode$ar$ds();
        if (applicationContext == null && PhenotypeContextTestMode.testModeReadStackTrace == null) {
            PhenotypeContextTestMode.testModeReadStackTrace = new PhenotypeContextTestMode.FirstFlagReadHere();
        }
    }

    public static void setContext(Context context) {
        synchronized (LOCK) {
            if (applicationContext != null) {
                return;
            }
            try {
                applicationContext = context.getApplicationContext();
            } catch (NullPointerException unused) {
                isTestMode$ar$ds();
                phlogger$ar$class_merging.log(Level.WARNING, (Executor) EXECUTOR.get(), "context.getApplicationContext() yielded NullPointerException", new Object[0]);
            }
        }
    }

    public final ListeningScheduledExecutorService getExecutor() {
        return (ListeningScheduledExecutorService) this.executorProvider.get();
    }

    public final StatsStorage getPhenotypeClient$ar$class_merging$ar$class_merging$ar$class_merging() {
        return (StatsStorage) this.clientProvider.get();
    }

    public final PhenotypeProcessReaper getProcessReaper$ar$class_merging() {
        return (PhenotypeProcessReaper) ((Present) this.optionalProcessReaper).reference;
    }

    public final OptionalMethod getStorageBackend$ar$class_merging$ar$class_merging$ar$class_merging() {
        return (OptionalMethod) this.storageProvider.get();
    }
}
