package com.google.android.libraries.phenotype.client.stable;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.google.android.gsf.GservicesConstants;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.phenotype.client.PhenotypeConstants;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.CombinedFlagSource;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.base.Pair;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.Objects;
import java.io.IOException;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CombinedFlagSource {
    public final Object CombinedFlagSource$ar$logSourceNames;
    public final Object CombinedFlagSource$ar$objectConverter;
    public final Object CombinedFlagSource$ar$stringConverter;
    public final boolean autoSubpackage;
    public final boolean directBootAware;

    public CombinedFlagSource(Uri uri, String str, String str2, boolean z, boolean z2) {
        this.CombinedFlagSource$ar$objectConverter = uri;
        this.CombinedFlagSource$ar$stringConverter = str;
        this.CombinedFlagSource$ar$logSourceNames = str2;
        this.directBootAware = z;
        this.autoSubpackage = z2;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$Converter, java.lang.Object] */
    public final Object convertValue(String str, String str2) {
        try {
            return this.CombinedFlagSource$ar$stringConverter.convert(str2);
        } catch (IOException | IllegalArgumentException e) {
            Log.e("PhenotypeCombinedFlags", "Invalid Phenotype flag value for flag ".concat(str), e);
            return null;
        }
    }

    public final PhenotypeFlag createFlagRestricted(String str, boolean z) {
        return new PhenotypeFlag(this, str, Boolean.valueOf(z)) { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag.3
            public AnonymousClass3(CombinedFlagSource this, String str2, Boolean bool) {
                super(this, str2, bool, true);
            }

            @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag
            public final /* bridge */ /* synthetic */ Object convertValue(Object obj) {
                if (obj instanceof Boolean) {
                    return (Boolean) obj;
                }
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (GservicesConstants.TRUE_PATTERN.matcher(str2).matches()) {
                        return true;
                    }
                    if (GservicesConstants.FALSE_PATTERN.matcher(str2).matches()) {
                        return false;
                    }
                }
                Log.e("PhenotypeFlag", "Invalid boolean value for " + super.getMendelFlagName() + ": " + String.valueOf(obj));
                return null;
            }
        };
    }

    public final void createFlagRestricted$ar$ds(String str) {
        new PhenotypeFlag(this, str, Double.valueOf(0.0d)) { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag.4
            public AnonymousClass4(CombinedFlagSource this, String str2, Double d) {
                super(this, str2, d, true);
            }

            @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag
            public final /* bridge */ /* synthetic */ Object convertValue(Object obj) {
                if (obj instanceof Double) {
                    return (Double) obj;
                }
                if (obj instanceof Float) {
                    return Double.valueOf(((Float) obj).doubleValue());
                }
                if (obj instanceof String) {
                    try {
                        return Double.valueOf(Double.parseDouble((String) obj));
                    } catch (NumberFormatException unused) {
                    }
                }
                Log.e("PhenotypeFlag", "Invalid double value for " + super.getMendelFlagName() + ": " + String.valueOf(obj));
                return null;
            }
        };
    }

    public final void createFlagRestricted$ar$ds$7c4d897c_0(String str, long j) {
        new PhenotypeFlag(this, str, Long.valueOf(j)) { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag.1
            public AnonymousClass1(CombinedFlagSource this, String str2, Long l) {
                super(this, str2, l, true);
            }

            @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag
            public final /* bridge */ /* synthetic */ Object convertValue(Object obj) {
                if (obj instanceof Long) {
                    return (Long) obj;
                }
                if (obj instanceof String) {
                    try {
                        return Long.valueOf(Long.parseLong((String) obj));
                    } catch (NumberFormatException unused) {
                    }
                }
                Log.e("PhenotypeFlag", "Invalid long value for " + super.getMendelFlagName() + ": " + String.valueOf(obj));
                return null;
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.concurrent.ConcurrentMap] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.Set, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object, java.util.concurrent.ConcurrentMap] */
    public final FlagStore getFlagStore$ar$ds(final PhenotypeContext phenotypeContext, final String str) {
        StatsStorage statsStorage = FlagStore.SHARED_REGISTRY$ar$class_merging;
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(phenotypeContext.onFlagUpdateFunction, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(2));
        final boolean z = this.directBootAware;
        final ?? r3 = this.CombinedFlagSource$ar$logSourceNames;
        Supplier supplier = new Supplier() { // from class: com.google.android.libraries.phenotype.client.stable.FlagStore$Registry$$ExternalSyntheticLambda1
            public final /* synthetic */ String f$2 = "";

            @Override // com.google.common.base.Supplier
            public final Object get() {
                return new FlagStore(PhenotypeContext.this, str, this.f$2, z, r3);
            }
        };
        Pair pair = new Pair(str, "");
        Object obj = (FlagStore) statsStorage.StatsStorage$ar$storage.get(pair);
        if (obj == null) {
            obj = supplier.get();
            FlagStore flagStore = (FlagStore) statsStorage.StatsStorage$ar$storage.putIfAbsent(pair, obj);
            if (flagStore == null) {
                Context context = phenotypeContext.context;
                PhenotypeUpdateBroadcastReceiver.packageAndAccountCallbacks.putIfAbsent(pair, new FloatingActionButton.ShadowDelegateImpl(obj, null));
                if (!PhenotypeUpdateBroadcastReceiver.registered) {
                    synchronized (PhenotypeUpdateBroadcastReceiver.LOCK) {
                        if (!PhenotypeUpdateBroadcastReceiver.registered && !Objects.equals(context.getPackageName(), "com.google.android.gms")) {
                            if (Build.VERSION.SDK_INT >= 33) {
                                context.registerReceiver(new PhenotypeUpdateBroadcastReceiver(), new IntentFilter("com.google.android.gms.phenotype.UPDATE"), 2);
                            } else {
                                context.registerReceiver(new PhenotypeUpdateBroadcastReceiver(), new IntentFilter("com.google.android.gms.phenotype.UPDATE"));
                            }
                            PhenotypeUpdateBroadcastReceiver.registered = true;
                        }
                    }
                }
                PhenotypeAccountStore.accountCommitterByPackage.putIfAbsent(pair, new ConfigurationsModule$$ExternalSyntheticLambda0(obj, 18));
            } else {
                obj = flagStore;
            }
        }
        FlagStore flagStore2 = (FlagStore) obj;
        boolean z2 = flagStore2.stickyAccountSupport;
        ContextDataProvider.checkArgument(true, "Package %s cannot be registered both with and without stickyAccountSupport", (Object) str);
        return flagStore2;
    }

    public final AppLifecycleMonitor getVersionCache$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(PhenotypeContext phenotypeContext, String str) {
        PhenotypeContext.isTestMode$ar$ds();
        return getFlagStore$ar$ds(phenotypeContext, PhenotypeConstants.getSubpackagedName(phenotypeContext.context, str)).packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public CombinedFlagSource(boolean z, boolean z2, Set set, ProcessStablePhenotypeFlagFactory.Converter converter, ProcessStablePhenotypeFlagFactory.Converter converter2) {
        this.autoSubpackage = true;
        this.directBootAware = z2;
        this.CombinedFlagSource$ar$logSourceNames = set;
        this.CombinedFlagSource$ar$stringConverter = converter;
        this.CombinedFlagSource$ar$objectConverter = converter2;
    }

    public CombinedFlagSource(Uri uri) {
        this(uri, "", "", false, false);
    }
}
