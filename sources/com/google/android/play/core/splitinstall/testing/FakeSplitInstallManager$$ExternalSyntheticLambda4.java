package com.google.android.play.core.splitinstall.testing;

import android.os.SystemClock;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.logging.schema.MLKitEnum$EventName;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import io.grpc.internal.RetryingNameResolver;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FakeSplitInstallManager$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Object FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$0;
    public final /* synthetic */ Object FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$1;
    public final /* synthetic */ Object FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$2;
    public final /* synthetic */ Object FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$3;
    public final /* synthetic */ long f$4;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FakeSplitInstallManager$$ExternalSyntheticLambda4(FakeSplitInstallManager fakeSplitInstallManager, long j, List list, List list2, List list3, int i) {
        this.switching_field = i;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$0 = fakeSplitInstallManager;
        this.f$4 = j;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$1 = list;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$2 = list2;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$3 = list3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.util.List, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                MLKitStatsLogger mLKitStatsLogger = (MLKitStatsLogger) this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$1;
                Map map = mLKitStatsLogger.durationsMsMap;
                Object obj = this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$0;
                if (!map.containsKey(obj)) {
                    mLKitStatsLogger.durationsMsMap.put(obj, new ArrayListMultimap());
                }
                long j = this.f$4;
                ((ListMultimap) mLKitStatsLogger.durationsMsMap.get(obj)).put$ar$ds(this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$3, Long.valueOf(j));
                long elapsedRealtime = SystemClock.elapsedRealtime();
                MLKitEnum$EventName mLKitEnum$EventName = (MLKitEnum$EventName) obj;
                if (!mLKitStatsLogger.shouldSendLog$ar$ds(mLKitEnum$EventName, elapsedRealtime)) {
                    return;
                }
                Object obj2 = this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$2;
                mLKitStatsLogger.logTimestamps.put(obj, Long.valueOf(elapsedRealtime));
                MLTaskExecutor.WorkerThreadExecutor.INSTANCE.execute(new FutureCallbackViewModel$$ExternalSyntheticLambda1(mLKitStatsLogger, mLKitEnum$EventName, (RetryingNameResolver.ResolutionResultListener) obj2, 14));
                return;
            }
            int i2 = 0;
            long j2 = 0;
            while (true) {
                long j3 = this.f$4;
                Object obj3 = this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$0;
                if (i2 < 3) {
                    j2 = Math.min(j3, j2 + (j3 / 3));
                    FakeSplitInstallManager fakeSplitInstallManager = (FakeSplitInstallManager) obj3;
                    fakeSplitInstallManager.notifyStatus(2, 0, Long.valueOf(j2), Long.valueOf(j3), null, null, null);
                    SystemClock.sleep(FakeSplitInstallManager.DOWNLOAD_SEGMENT_DURATION_MS);
                    SplitInstallSessionState internalSessionState = fakeSplitInstallManager.getInternalSessionState();
                    if (internalSessionState.status() != 9 && internalSessionState.status() != 7 && internalSessionState.status() != 6) {
                        i2++;
                    } else {
                        return;
                    }
                } else {
                    ?? r8 = this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$3;
                    ?? r7 = this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$2;
                    FakeSplitInstallManager fakeSplitInstallManager2 = (FakeSplitInstallManager) obj3;
                    fakeSplitInstallManager2.backgroundExecutor.execute(new FakeSplitInstallManager$$ExternalSyntheticLambda4(fakeSplitInstallManager2, (List) this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$1, (List) r7, (List) r8, j3, 0));
                    return;
                }
            }
        } else {
            FakeSplitInstallManager fakeSplitInstallManager3 = (FakeSplitInstallManager) this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$0;
            if (fakeSplitInstallManager3.shouldNetworkError.get()) {
                fakeSplitInstallManager3.notifyStatus$ar$ds(-6);
                return;
            }
            long j4 = this.f$4;
            ?? r5 = this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$3;
            ?? r4 = this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$2;
            if (fakeSplitInstallManager3.interceptorProvider.getInterceptor$ar$class_merging$ar$class_merging() != null) {
                fakeSplitInstallManager3.ingest(this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$1, r4, r5, j4, false);
            } else {
                fakeSplitInstallManager3.setModulesInstalledAndNotify(r4, r5, j4);
            }
        }
    }

    public /* synthetic */ FakeSplitInstallManager$$ExternalSyntheticLambda4(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2, List list3, long j, int i) {
        this.switching_field = i;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$0 = fakeSplitInstallManager;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$1 = list;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$2 = list2;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$3 = list3;
        this.f$4 = j;
    }

    public /* synthetic */ FakeSplitInstallManager$$ExternalSyntheticLambda4(MLKitStatsLogger mLKitStatsLogger, MLKitEnum$EventName mLKitEnum$EventName, Object obj, long j, RetryingNameResolver.ResolutionResultListener resolutionResultListener, int i) {
        this.switching_field = i;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$1 = mLKitStatsLogger;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$0 = mLKitEnum$EventName;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$3 = obj;
        this.f$4 = j;
        this.FakeSplitInstallManager$$ExternalSyntheticLambda4$ar$f$2 = resolutionResultListener;
    }
}
