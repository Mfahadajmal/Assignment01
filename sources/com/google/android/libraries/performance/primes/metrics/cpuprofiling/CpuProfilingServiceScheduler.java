package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import android.content.Context;
import android.provider.Settings;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import dagger.Lazy;
import j$.time.Instant;
import j$.time.temporal.ChronoUnit;
import j$.util.Objects;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfilingServiceScheduler {
    static final long AVERAGE_MILLIS_PER_YEAR = TimeUnit.DAYS.toMillis(365) + TimeUnit.HOURS.toMillis(6);
    private final String androidId;
    public final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    private final Lazy configs;
    private final String processName = ProcessStats.getCurrentProcessName();

    public CpuProfilingServiceScheduler(SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, Lazy lazy, Context context) {
        this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        this.configs = lazy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Long getNextWindow(long j) {
        Instant truncatedTo;
        Instant truncatedTo2;
        int i = 0;
        Random random = new Random(Objects.hash(Long.valueOf(j), this.androidId, this.processName));
        double nextDouble = random.nextDouble();
        if (((CpuProfilingConfigurations) this.configs.get()).getSamplesPerEpoch() >= 1.0d) {
            double samplesPerEpoch = ((CpuProfilingConfigurations) this.configs.get()).getSamplesPerEpoch();
            i = (int) Math.min(Math.round((samplesPerEpoch + samplesPerEpoch) * nextDouble), 2147483646L);
        } else if (nextDouble < ((CpuProfilingConfigurations) this.configs.get()).getSamplesPerEpoch()) {
            i = 1;
        }
        long j2 = j + AVERAGE_MILLIS_PER_YEAR;
        long j3 = j2 - j;
        int sampleDurationMs = ((CpuProfilingConfigurations) this.configs.get()).getSampleDurationMs();
        int i2 = sampleDurationMs + sampleDurationMs;
        TreeSet treeSet = new TreeSet();
        while (treeSet.size() < i) {
            long abs = j + (Math.abs(Math.max(random.nextLong(), -9223372036854775807L)) % (j3 - i2));
            long sampleDurationMs2 = ((CpuProfilingConfigurations) this.configs.get()).getSampleDurationMs();
            long j4 = sampleDurationMs2 + sampleDurationMs2;
            if (treeSet.subSet(Long.valueOf(abs - j4), Long.valueOf(abs + j4)).isEmpty()) {
                treeSet.add(Long.valueOf(abs));
            }
        }
        truncatedTo = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        Long l = (Long) treeSet.ceiling(Long.valueOf(truncatedTo.toEpochMilli() + 100));
        if (l == null) {
            truncatedTo2 = Instant.now().truncatedTo(ChronoUnit.MILLIS);
            if (j < truncatedTo2.toEpochMilli()) {
                return getNextWindow(j2);
            }
            return null;
        }
        return l;
    }
}
