package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import androidx.collection.SimpleArrayMap;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.PhenotypeContextTestMode;
import com.google.android.libraries.phenotype.client.lockdown.FlagExemptionsReader;
import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStablePhenotypeFlag implements Supplier {
    public static final /* synthetic */ int ProcessStablePhenotypeFlag$ar$NoOp = 0;
    private static volatile FlagExemptionsReader exemptionsReader = new FlagExemptionsReader(new ProcessStablePhenotypeFlag$$ExternalSyntheticLambda0(0));
    private volatile Object cachedValueForLoggedOutUser;
    private SimpleArrayMap cachedValuesByAccountName;
    private final String configurationPackageName;
    private Object defaultValue;
    private final String flagName;
    private volatile AppLifecycleMonitor packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging;
    private SimpleArrayMap packageVersionCachesByAccountName;
    private final CombinedFlagSource source$ar$class_merging;
    private SimpleArrayMap versionByAccountName;
    private volatile int versionForLoggedOutUser = -1;
    private volatile boolean lazyInitDefaultValue = true;

    public ProcessStablePhenotypeFlag(String str, String str2, CombinedFlagSource combinedFlagSource, String str3) {
        this.configurationPackageName = str;
        this.flagName = str2;
        this.defaultValue = str3;
        this.source$ar$class_merging = combinedFlagSource;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0038  */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$Converter, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object getNonCached$ar$ds(com.google.android.libraries.phenotype.client.PhenotypeContext r11) {
        /*
            r10 = this;
            com.google.android.libraries.phenotype.client.PhenotypeContext.isTestMode$ar$ds()
            android.content.Context r0 = r11.context
            com.google.common.base.Optional r0 = com.google.android.libraries.phenotype.client.HermeticFileOverridesReader$CachingReader.readFromFileAndCacheIfEligible(r0)
            boolean r1 = r0.isPresent()
            com.google.android.libraries.phenotype.client.stable.CombinedFlagSource r2 = r10.source$ar$class_merging
            java.lang.String r3 = r10.configurationPackageName
            java.lang.String r4 = r10.flagName
            r5 = 0
            if (r1 == 0) goto L2c
            java.lang.Object r1 = r0.get()
            android.net.Uri r6 = com.google.android.libraries.phenotype.client.PhenotypeConstants.getContentProviderUri(r3)
            com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor r1 = (com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor) r1
            java.lang.String r1 = r1.get$ar$ds$e3f95f0a_0(r6, r5, r4)
            if (r1 != 0) goto L27
            goto L2c
        L27:
            java.lang.Object r1 = r2.convertValue(r4, r1)
            goto L2d
        L2c:
            r1 = r5
        L2d:
            android.content.Context r6 = r11.context
            java.lang.String r3 = com.google.android.libraries.phenotype.client.PhenotypeConstants.getSubpackagedName(r6, r3)
            boolean r6 = r2.directBootAware
            r7 = 1
            if (r6 == 0) goto L3d
            java.lang.String r6 = "DirectBoot aware package %s can not access account-scoped flags."
            com.google.common.flogger.context.ContextDataProvider.checkState(r7, r6, r3)
        L3d:
            com.google.common.util.concurrent.ListeningScheduledExecutorService r6 = r11.getExecutor()
            com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0 r8 = new com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0
            r9 = 6
            r8.<init>(r11, r3, r9)
            com.google.common.util.concurrent.ListenableFuture r6 = r6.submit(r8)
            com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator.crashOnFailure(r6)
            com.google.android.libraries.phenotype.client.stable.FlagStore r11 = r2.getFlagStore$ar$ds(r11, r3)
            java.lang.Object r11 = r11.getFlag(r4)
            if (r11 != 0) goto L59
            goto L6e
        L59:
            java.lang.Object r2 = r2.CombinedFlagSource$ar$objectConverter     // Catch: java.io.IOException -> L60 java.lang.ClassCastException -> L62
            java.lang.Object r5 = r2.convert(r11)     // Catch: java.io.IOException -> L60 java.lang.ClassCastException -> L62
            goto L6e
        L60:
            r11 = move-exception
            goto L63
        L62:
            r11 = move-exception
        L63:
            java.lang.String r2 = "Invalid Phenotype flag value for flag "
            java.lang.String r2 = r2.concat(r4)
            java.lang.String r3 = "PhenotypeCombinedFlags"
            android.util.Log.e(r3, r2, r11)
        L6e:
            boolean r11 = r0.isPresent()
            if (r7 != r11) goto L75
            goto L76
        L75:
            r1 = r5
        L76:
            if (r1 != 0) goto L9d
            boolean r11 = r10.lazyInitDefaultValue
            if (r11 == 0) goto L9a
            monitor-enter(r10)
            boolean r11 = r10.lazyInitDefaultValue     // Catch: java.lang.Throwable -> L97
            if (r11 == 0) goto L95
            com.google.android.libraries.phenotype.client.stable.CombinedFlagSource r11 = r10.source$ar$class_merging     // Catch: java.lang.Throwable -> L97
            java.lang.String r0 = r10.flagName     // Catch: java.lang.Throwable -> L97
            java.lang.Object r1 = r10.defaultValue     // Catch: java.lang.Throwable -> L97
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L97
            java.lang.Object r11 = r11.convertValue(r0, r1)     // Catch: java.lang.Throwable -> L97
            r11.getClass()     // Catch: java.lang.Throwable -> L97
            r10.defaultValue = r11     // Catch: java.lang.Throwable -> L97
            r11 = 0
            r10.lazyInitDefaultValue = r11     // Catch: java.lang.Throwable -> L97
        L95:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L97
            goto L9a
        L97:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L97
            throw r11
        L9a:
            java.lang.Object r11 = r10.defaultValue
            return r11
        L9d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag.getNonCached$ar$ds(com.google.android.libraries.phenotype.client.PhenotypeContext):java.lang.Object");
    }

    private final Object getWithPhenotypeContext$ar$ds(PhenotypeContext phenotypeContext) {
        int i = this.versionForLoggedOutUser;
        Object obj = this.cachedValueForLoggedOutUser;
        if (this.packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging == null || i < this.packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging.get() || obj == null) {
            synchronized (this) {
                if (this.packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging == null) {
                    this.packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging = this.source$ar$class_merging.getVersionCache$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(phenotypeContext, this.configurationPackageName);
                }
                if (this.versionForLoggedOutUser < this.packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging.get()) {
                    this.versionForLoggedOutUser = this.packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging.get();
                    this.cachedValueForLoggedOutUser = getNonCached$ar$ds(phenotypeContext);
                }
                obj = this.cachedValueForLoggedOutUser;
            }
        }
        return obj;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        Context context = PhenotypeContext.applicationContext;
        PhenotypeContextTestMode.contextRead = true;
        if (PhenotypeContextTestMode.contextReadStackTrace == null) {
            PhenotypeContextTestMode.contextReadStackTrace = new PhenotypeContextTestMode.FirstFlagReadHere();
        }
        Context context2 = PhenotypeContext.applicationContext;
        if (context2 != null) {
            return getWithPhenotypeContext$ar$ds(PhenotypeContext.getPhenotypeContextFrom(context2));
        }
        PhenotypeContextTestMode.testMode$ar$ds();
        throw new IllegalStateException("Must call PhenotypeContext.setContext() first");
    }

    public ProcessStablePhenotypeFlag(String str, String str2, Object obj, CombinedFlagSource combinedFlagSource) {
        this.configurationPackageName = str;
        this.flagName = str2;
        this.defaultValue = obj;
        this.source$ar$class_merging = combinedFlagSource;
    }

    public final Object get(Context context) {
        Context applicationContext = context.getApplicationContext();
        applicationContext.getClass();
        return getWithPhenotypeContext$ar$ds(PhenotypeContext.getPhenotypeContextFrom(applicationContext));
    }
}
