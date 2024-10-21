package com.google.android.libraries.performance.primes.metrics.jank;

import android.os.SystemClock;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import org.chromium.net.NetError;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameTimeHistogram {
    public static final int[] BUCKETS_BOUNDS = {0, 4, 8, 10, 12, 14, 16, 18, 20, 25, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
    public static final int[] NEGATIVE_SLACK_BUCKET_BOUNDS = {Integer.MIN_VALUE, NetError.ERR_CERT_COMMON_NAME_INVALID, NetError.ERR_SSL_PINNED_KEY_NOT_IN_CERT_CHAIN, -100, -90, -80, -70, -60, -50, -40, -30, -25, -20, -18, -16, -14, -12, -10, -8, -6, -4, -2, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 25, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
    public final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    public int droppedReportCount;
    public int framesMissingRefreshRateBasedDrawDeadline;
    public int jankyFrameCount;
    public int maxFrameDurationMs;
    public final long recordingStartTimeMs;
    public int renderedFrameCount;
    public int totalDurationOfFramesMissingRefreshRateDeadlineMs;
    public int totalFrameDurationMs;
    public int totalJankyFrameDurationMs;
    public final int[] buckets = new int[28];
    public final int[] slackBuckets = new int[52];
    public int maxSlackTimeMs = Integer.MIN_VALUE;

    public FrameTimeHistogram(SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan) {
        long elapsedRealtime;
        this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        elapsedRealtime = SystemClock.elapsedRealtime();
        this.recordingStartTimeMs = elapsedRealtime;
    }
}
