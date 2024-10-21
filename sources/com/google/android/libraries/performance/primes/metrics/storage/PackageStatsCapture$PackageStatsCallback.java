package com.google.android.libraries.performance.primes.metrics.storage;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageStats;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.common.flogger.GoogleLogger;
import java.util.concurrent.Semaphore;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PackageStatsCapture$PackageStatsCallback extends IPackageStatsObserver.Stub {
    private volatile PackageStats packageStats;
    private final Semaphore semaphore = new Semaphore(1);

    private PackageStatsCapture$PackageStatsCallback() {
    }

    public void onGetStatsCompleted(PackageStats packageStats, boolean z) {
        if (z) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture$PackageStatsCallback", "onGetStatsCompleted", 50, "PackageStatsCapture.java")).log("Success getting PackageStats: %s", packageStats);
            this.packageStats = packageStats;
        } else {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture$PackageStatsCallback", "onGetStatsCompleted", 53, "PackageStatsCapture.java")).log("Failure getting PackageStats");
        }
        this.semaphore.release();
    }
}
