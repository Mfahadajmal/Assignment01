package com.google.android.libraries.phenotype.client;

import android.content.Context;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.lockdown.FlagExemptionsReader;
import com.google.android.libraries.phenotype.client.stable.CombinedFlagSource;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag$$ExternalSyntheticLambda0;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PhenotypeFlag {
    public static final /* synthetic */ int PhenotypeFlag$ar$NoOp = 0;
    private static final FlagExemptionsReader exemptionsReader;
    private static volatile FlagsContext flagsContext = null;
    private static final AtomicInteger globalVersion;
    private static final Object setContextLock = new Object();
    private static volatile boolean testMode = false;
    private volatile Object cachedValue;
    private volatile int cachedVersion = -1;
    private final boolean codegenFlag;
    private Object defaultValue;
    final CombinedFlagSource factory$ar$class_merging;
    private volatile boolean lazyInitDefaultValue;
    final String name;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BytesConverter {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FlagsContext {
        public final Context context;
        public final Supplier hermeticFileOverrides;

        public FlagsContext() {
        }

        public final Context context() {
            return this.context;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof FlagsContext) {
                FlagsContext flagsContext = (FlagsContext) obj;
                if (this.context.equals(flagsContext.context()) && this.hermeticFileOverrides.equals(flagsContext.hermeticFileOverrides())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.context.hashCode() ^ 1000003) * 1000003) ^ this.hermeticFileOverrides.hashCode();
        }

        public final Supplier hermeticFileOverrides() {
            return this.hermeticFileOverrides;
        }

        public final String toString() {
            Supplier supplier = this.hermeticFileOverrides;
            return "FlagsContext{context=" + this.context.toString() + ", hermeticFileOverrides=" + supplier.toString() + "}";
        }

        public FlagsContext(Context context, Supplier supplier) {
            this();
            this.context = context;
            this.hermeticFileOverrides = supplier;
        }
    }

    static {
        new AtomicReference();
        exemptionsReader = new FlagExemptionsReader(new ProcessStablePhenotypeFlag$$ExternalSyntheticLambda0(1));
        globalVersion = new AtomicInteger();
    }

    public PhenotypeFlag(CombinedFlagSource combinedFlagSource, String str, Object obj, boolean z) {
        if (combinedFlagSource.CombinedFlagSource$ar$objectConverter == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.factory$ar$class_merging = combinedFlagSource;
        this.name = str;
        this.defaultValue = obj;
        this.codegenFlag = z;
        this.lazyInitDefaultValue = false;
    }

    private final String getPrefixedName(String str) {
        if (str.isEmpty()) {
            return this.name;
        }
        return str.concat(this.name);
    }

    public static void invalidateProcessCache() {
        globalVersion.incrementAndGet();
    }

    public static void maybeInit(Context context) {
        if (flagsContext == null && context != null) {
            Object obj = setContextLock;
            synchronized (obj) {
                if (flagsContext == null) {
                    synchronized (obj) {
                        FlagsContext flagsContext2 = flagsContext;
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext != null) {
                            context = applicationContext;
                        }
                        if (flagsContext2 == null || flagsContext2.context != context) {
                            if (flagsContext2 != null) {
                                ConfigurationContentLoader.clearLoaderMap();
                                SharedPreferencesLoader.clearLoaderMap();
                                GservicesLoader.clearLoader();
                            }
                            flagsContext = new FlagsContext(context, ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(context, 15)));
                            invalidateProcessCache();
                        }
                    }
                }
            }
        }
    }

    public abstract Object convertValue(Object obj);

    /* JADX WARN: Can't wrap try/catch for region: R(11:81|(8:83|(1:85)(1:94)|86|(1:88)|90|91|92|93)|95|96|97|98|(4:100|91|92|93)|90|91|92|93) */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00d8, code lost:
    
        if ("com.google.android.gms".equals(r9.packageName) != false) goto L46;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0192 A[Catch: all -> 0x019b, TryCatch #3 {, blocks: (B:8:0x0019, B:10:0x001d, B:12:0x0024, B:14:0x0032, B:18:0x0050, B:20:0x005b, B:22:0x006d, B:25:0x0139, B:27:0x0143, B:29:0x014b, B:31:0x0151, B:34:0x0167, B:36:0x016d, B:37:0x015f, B:39:0x0173, B:41:0x0179, B:44:0x0181, B:45:0x0186, B:46:0x018a, B:48:0x007f, B:50:0x0087, B:52:0x0105, B:53:0x0119, B:71:0x0136, B:73:0x0095, B:74:0x0097, B:93:0x00f7, B:105:0x0191, B:106:0x0192, B:107:0x0198, B:110:0x0199, B:55:0x011a, B:61:0x0124, B:63:0x012c, B:57:0x0132, B:76:0x0098, B:78:0x00a0, B:79:0x00ac, B:81:0x00ae, B:83:0x00ba, B:86:0x00ca, B:88:0x00d0, B:91:0x00ec, B:92:0x00f6, B:95:0x00da, B:97:0x00de, B:98:0x00e4), top: B:7:0x0019, inners: #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005b A[Catch: all -> 0x019b, TryCatch #3 {, blocks: (B:8:0x0019, B:10:0x001d, B:12:0x0024, B:14:0x0032, B:18:0x0050, B:20:0x005b, B:22:0x006d, B:25:0x0139, B:27:0x0143, B:29:0x014b, B:31:0x0151, B:34:0x0167, B:36:0x016d, B:37:0x015f, B:39:0x0173, B:41:0x0179, B:44:0x0181, B:45:0x0186, B:46:0x018a, B:48:0x007f, B:50:0x0087, B:52:0x0105, B:53:0x0119, B:71:0x0136, B:73:0x0095, B:74:0x0097, B:93:0x00f7, B:105:0x0191, B:106:0x0192, B:107:0x0198, B:110:0x0199, B:55:0x011a, B:61:0x0124, B:63:0x012c, B:57:0x0132, B:76:0x0098, B:78:0x00a0, B:79:0x00ac, B:81:0x00ae, B:83:0x00ba, B:86:0x00ca, B:88:0x00d0, B:91:0x00ec, B:92:0x00f6, B:95:0x00da, B:97:0x00de, B:98:0x00e4), top: B:7:0x0019, inners: #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x014b A[Catch: all -> 0x019b, TryCatch #3 {, blocks: (B:8:0x0019, B:10:0x001d, B:12:0x0024, B:14:0x0032, B:18:0x0050, B:20:0x005b, B:22:0x006d, B:25:0x0139, B:27:0x0143, B:29:0x014b, B:31:0x0151, B:34:0x0167, B:36:0x016d, B:37:0x015f, B:39:0x0173, B:41:0x0179, B:44:0x0181, B:45:0x0186, B:46:0x018a, B:48:0x007f, B:50:0x0087, B:52:0x0105, B:53:0x0119, B:71:0x0136, B:73:0x0095, B:74:0x0097, B:93:0x00f7, B:105:0x0191, B:106:0x0192, B:107:0x0198, B:110:0x0199, B:55:0x011a, B:61:0x0124, B:63:0x012c, B:57:0x0132, B:76:0x0098, B:78:0x00a0, B:79:0x00ac, B:81:0x00ae, B:83:0x00ba, B:86:0x00ca, B:88:0x00d0, B:91:0x00ec, B:92:0x00f6, B:95:0x00da, B:97:0x00de, B:98:0x00e4), top: B:7:0x0019, inners: #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x017f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get() {
        /*
            Method dump skipped, instructions count: 417
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.phenotype.client.PhenotypeFlag.get():java.lang.Object");
    }

    public final Object getDefaultValue() {
        if (this.lazyInitDefaultValue) {
            synchronized (this) {
                if (this.lazyInitDefaultValue) {
                    Object convertValue = convertValue(this.defaultValue);
                    convertValue.getClass();
                    this.defaultValue = convertValue;
                    this.lazyInitDefaultValue = false;
                }
            }
        }
        return this.defaultValue;
    }

    public final String getMendelFlagName() {
        return getPrefixedName((String) this.factory$ar$class_merging.CombinedFlagSource$ar$logSourceNames);
    }

    public PhenotypeFlag(CombinedFlagSource combinedFlagSource) {
        if (combinedFlagSource.CombinedFlagSource$ar$objectConverter == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.factory$ar$class_merging = combinedFlagSource;
        this.name = "getTokenRefactor__blocked_packages";
        this.defaultValue = "ChNjb20uYW5kcm9pZC52ZW5kaW5nCiBjb20uZ29vZ2xlLmFuZHJvaWQuYXBwcy5tZWV0aW5ncwohY29tLmdvb2dsZS5hbmRyb2lkLmFwcHMubWVzc2FnaW5n";
        this.codegenFlag = true;
        this.lazyInitDefaultValue = true;
    }
}
