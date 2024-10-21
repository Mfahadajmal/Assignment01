package com.google.android.libraries.performance.primes.metrics.jank;

import android.os.Build;
import android.view.FrameMetrics;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LegacyDeadlineTracker {
    long swapDeadlineNs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSupported() {
        if (Build.VERSION.SDK_INT <= 30) {
            return true;
        }
        return false;
    }

    public long computeNextDeadlineDuration(FrameMetrics frameMetrics, long j) {
        long metric;
        long metric2;
        long metric3;
        metric = frameMetrics.getMetric(10);
        metric2 = frameMetrics.getMetric(8);
        long j2 = metric + metric2;
        long max = Math.max(this.swapDeadlineNs + j, metric + j);
        this.swapDeadlineNs = max;
        long j3 = max - metric;
        if (j2 >= max && metric2 >= j) {
            metric3 = frameMetrics.getMetric(11);
            this.swapDeadlineNs = (j2 - ((j2 - metric3) % j)) + j;
        }
        return j3;
    }
}
