package com.google.android.libraries.phenotype.client.stable;

import android.net.Uri;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.storage.file.backends.AndroidUri;
import com.google.android.libraries.storage.protostore.ProtoDataStoreConfig;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactory;
import com.google.android.libraries.storage.protostore.SingleProcConfig;
import com.google.android.libraries.storage.protostore.SingleProcProtoDataStore;
import com.google.android.libraries.storage.protostore.XDataStore;
import com.google.android.libraries.storage.protostore.XDataStoreVariantFactory;
import com.google.android.libraries.storage.protostore.loggers.NoOpLogger;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.protobuf.MessageLite;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeAccountStore {
    public static final ConcurrentMap accountCommitterByPackage = new ConcurrentHashMap();
    private static final AppLifecycleMonitor exceptionHandler$ar$class_merging$ar$class_merging = new AppLifecycleMonitor((MessageLite) Accounts.DEFAULT_INSTANCE);
    private static final Object FACTORY_LOCK = new Object();
    private static volatile ProtoDataStoreFactory pdsFactory = null;

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0023, code lost:
    
        if (r4 != false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean deleteRecursively(java.io.File r6) {
        /*
            boolean r0 = r6.isDirectory()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L25
            java.io.File[] r0 = r6.listFiles()
            if (r0 == 0) goto L25
            r4 = r1
            r3 = r2
        L10:
            int r5 = r0.length
            if (r3 >= r5) goto L23
            r5 = r0[r3]
            if (r4 == 0) goto L1f
            boolean r4 = deleteRecursively(r5)
            if (r4 == 0) goto L1f
            r4 = r1
            goto L20
        L1f:
            r4 = r2
        L20:
            int r3 = r3 + 1
            goto L10
        L23:
            if (r4 == 0) goto L2c
        L25:
            boolean r6 = r6.delete()
            if (r6 == 0) goto L2c
            return r1
        L2c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.phenotype.client.stable.PhenotypeAccountStore.deleteRecursively(java.io.File):boolean");
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.util.concurrent.Executor, java.lang.Object] */
    public static XDataStore getAccountsStore$ar$class_merging(PhenotypeContext phenotypeContext) {
        Uri uri;
        MessageLite messageLite;
        DisplayStats displayStats;
        ProtoDataStoreConfig.Builder builder = new ProtoDataStoreConfig.Builder(null);
        builder.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging = SingleProcConfig.INSTANCE;
        builder.setEnableTracing$ar$ds();
        builder.useGeneratedExtensionRegistry = true;
        builder.set$0 = (byte) (builder.set$0 | 1);
        AndroidUri.Builder builder2 = new AndroidUri.Builder(phenotypeContext.context);
        builder2.setModule$ar$ds("phenotype");
        builder2.setRelativePath$ar$ds("all_accounts.pb");
        Uri build = builder2.build();
        if (build != null) {
            builder.uri = build;
            Accounts accounts = Accounts.DEFAULT_INSTANCE;
            if (accounts != null) {
                builder.schema = accounts;
                builder.handler = Optional.of(exceptionHandler$ar$class_merging$ar$class_merging);
                builder.setEnableTracing$ar$ds();
                if (builder.migrations == null) {
                    int i = ImmutableList.ImmutableList$ar$NoOp;
                    builder.migrations = RegularImmutableList.EMPTY;
                }
                if (builder.set$0 == 3 && (uri = builder.uri) != null && (messageLite = builder.schema) != null && (displayStats = builder.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging) != null) {
                    ProtoDataStoreConfig protoDataStoreConfig = new ProtoDataStoreConfig(uri, messageLite, builder.handler, builder.migrations, displayStats, builder.useGeneratedExtensionRegistry);
                    ProtoDataStoreFactory protoDataStoreFactory = pdsFactory;
                    if (protoDataStoreFactory == null) {
                        synchronized (FACTORY_LOCK) {
                            protoDataStoreFactory = pdsFactory;
                            if (protoDataStoreFactory == null) {
                                SnackbarManager snackbarManager = new SnackbarManager((byte[]) null, (byte[]) null);
                                snackbarManager.SnackbarManager$ar$currentSnackbar = phenotypeContext.getExecutor();
                                snackbarManager.SnackbarManager$ar$nextSnackbar = phenotypeContext.getStorageBackend$ar$class_merging$ar$class_merging$ar$class_merging();
                                XDataStoreVariantFactory xDataStoreVariantFactory = SingleProcProtoDataStore.Factory.INSTANCE;
                                String id$ar$edu = xDataStoreVariantFactory.id$ar$edu(1);
                                ContextDataProvider.checkArgument(true ^ ((HashMap) snackbarManager.lock).containsKey(id$ar$edu), "There is already a factory registered for the ID %s", id$ar$edu);
                                ((HashMap) snackbarManager.lock).put(id$ar$edu, xDataStoreVariantFactory);
                                ProtoDataStoreFactory protoDataStoreFactory2 = new ProtoDataStoreFactory(snackbarManager.SnackbarManager$ar$currentSnackbar, (OptionalMethod) snackbarManager.SnackbarManager$ar$nextSnackbar, (NoOpLogger) snackbarManager.SnackbarManager$ar$handler, snackbarManager.lock);
                                pdsFactory = protoDataStoreFactory2;
                                protoDataStoreFactory = protoDataStoreFactory2;
                            }
                        }
                    }
                    return protoDataStoreFactory.getOrCreateInternal$ar$class_merging(protoDataStoreConfig);
                }
                StringBuilder sb = new StringBuilder();
                if (builder.uri == null) {
                    sb.append(" uri");
                }
                if (builder.schema == null) {
                    sb.append(" schema");
                }
                if (builder.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging == null) {
                    sb.append(" variantConfig");
                }
                if ((builder.set$0 & 1) == 0) {
                    sb.append(" useGeneratedExtensionRegistry");
                }
                if ((builder.set$0 & 2) == 0) {
                    sb.append(" enableTracing");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }
            throw new NullPointerException("Null schema");
        }
        throw new NullPointerException("Null uri");
    }
}
