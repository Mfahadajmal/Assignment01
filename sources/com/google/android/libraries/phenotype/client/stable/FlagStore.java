package com.google.android.libraries.phenotype.client.stable;

import android.util.Log;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda16;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.api.PhenotypeRuntimeException;
import com.google.android.libraries.phenotype.client.shareddir.SnapshotBlob;
import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagStore {
    public static final StatsStorage SHARED_REGISTRY$ar$class_merging = new StatsStorage();
    public final String configPackage;
    public final PhenotypeContext context;
    private final Set logSourceNames;
    public final SnapshotHandler snapshotHandler;
    private final Object cacheLock = new Object();
    public final String account = "";
    public final boolean stickyAccountSupport = false;
    public final boolean canInvalidate = false;
    private volatile OptionalMethod cache$ar$class_merging = null;
    public final AppLifecycleMonitor packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging = new AppLifecycleMonitor((char[]) null);

    public FlagStore(PhenotypeContext phenotypeContext, String str, String str2, boolean z, Set set) {
        this.context = phenotypeContext;
        this.configPackage = str;
        this.logSourceNames = set;
        this.snapshotHandler = new SnapshotHandler(phenotypeContext, str, "", z);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00b6 A[Catch: all -> 0x012c, TryCatch #0 {, blocks: (B:6:0x0009, B:8:0x000d, B:10:0x0026, B:13:0x0035, B:15:0x004c, B:16:0x0073, B:18:0x007b, B:20:0x0085, B:21:0x0127, B:22:0x009f, B:24:0x00b6, B:25:0x00c6, B:27:0x00d2, B:29:0x00da, B:30:0x00df, B:31:0x0103, B:33:0x0109, B:35:0x0118, B:36:0x00bb, B:37:0x0071, B:38:0x012a), top: B:5:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0109 A[Catch: all -> 0x012c, LOOP:0: B:31:0x0103->B:33:0x0109, LOOP_END, TryCatch #0 {, blocks: (B:6:0x0009, B:8:0x000d, B:10:0x0026, B:13:0x0035, B:15:0x004c, B:16:0x0073, B:18:0x007b, B:20:0x0085, B:21:0x0127, B:22:0x009f, B:24:0x00b6, B:25:0x00c6, B:27:0x00d2, B:29:0x00da, B:30:0x00df, B:31:0x0103, B:33:0x0109, B:35:0x0118, B:36:0x00bb, B:37:0x0071, B:38:0x012a), top: B:5:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bb A[Catch: all -> 0x012c, TryCatch #0 {, blocks: (B:6:0x0009, B:8:0x000d, B:10:0x0026, B:13:0x0035, B:15:0x004c, B:16:0x0073, B:18:0x007b, B:20:0x0085, B:21:0x0127, B:22:0x009f, B:24:0x00b6, B:25:0x00c6, B:27:0x00d2, B:29:0x00da, B:30:0x00df, B:31:0x0103, B:33:0x0109, B:35:0x0118, B:36:0x00bb, B:37:0x0071, B:38:0x012a), top: B:5:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final io.grpc.okhttp.internal.OptionalMethod getSnapshotWrapper$ar$class_merging() {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.phenotype.client.stable.FlagStore.getSnapshotWrapper$ar$class_merging():io.grpc.okhttp.internal.OptionalMethod");
    }

    public final ListenableFuture commitToSnapshot() {
        String snapshotToken = getSnapshotWrapper$ar$class_merging().getSnapshotToken();
        if (ContextDataProvider.stringIsNullOrEmpty(snapshotToken)) {
            return ImmediateFuture.NULL;
        }
        return AbstractCatchingFuture.create(this.context.getPhenotypeClient$ar$class_merging$ar$class_merging$ar$class_merging().commitToConfiguration(snapshotToken), PhenotypeRuntimeException.class, new AiCoreBaseService$$ExternalSyntheticLambda16(this, 5), this.context.getExecutor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object getFlag(String str) {
        return ((ImmutableMap) getSnapshotWrapper$ar$class_merging().OptionalMethod$ar$methodName).get(str);
    }

    public final void handleFlagUpdates() {
        ListenableFuture latestSnapshot = this.snapshotHandler.getLatestSnapshot(this.account);
        AbstractTransformFuture.create(latestSnapshot, new AiCoreBaseService$$ExternalSyntheticLambda16(this.snapshotHandler, 4), this.context.getExecutor()).addListener(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, latestSnapshot, 7), this.context.getExecutor());
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.Map, java.lang.Object] */
    /* renamed from: lambda$handleFlagUpdates$3$com-google-android-libraries-phenotype-client-stable-FlagStore, reason: not valid java name */
    public final /* synthetic */ void m204xe16b5662(ListenableFuture listenableFuture) {
        try {
            OptionalMethod optionalMethod = new OptionalMethod((SnapshotProto$Snapshot) ContextDataProvider.getDone(listenableFuture), (SnapshotBlob) null);
            synchronized (this.cacheLock) {
                if (this.cache$ar$class_merging != null) {
                    boolean equalsImpl = ContextDataProvider.equalsImpl((Map) this.cache$ar$class_merging.OptionalMethod$ar$methodName, optionalMethod.OptionalMethod$ar$methodName);
                    if (!equalsImpl) {
                        this.context.getProcessReaper$ar$class_merging().scheduleReap();
                        return;
                    }
                } else {
                    this.cache$ar$class_merging = optionalMethod;
                }
                ((AtomicInteger) this.packageVersionCache$ar$class_merging$ar$class_merging$ar$class_merging.AppLifecycleMonitor$ar$tracker).incrementAndGet();
            }
        } catch (CancellationException | ExecutionException e) {
            Log.w("MobStoreFlagStore", "Unable to update local snapshot for " + this.configPackage + ", may result in stale flags.", e);
        }
    }
}
