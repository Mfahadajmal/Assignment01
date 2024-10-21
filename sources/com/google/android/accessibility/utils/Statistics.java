package com.google.android.accessibility.utils;

import com.google.common.collect.EvictingQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Statistics {
    public long count;
    public long numMissing;
    private long sum;
    private long sumSquares;
    final ArrayList histogram = new ArrayList();
    private final Queue rawData = new EvictingQueue(300);

    public Statistics() {
        clear();
    }

    public static final long histogramBinToStartValue$ar$ds(int i) {
        if (i <= 0) {
            return 0L;
        }
        return 1 << (i - 1);
    }

    public final synchronized void clear() {
        this.numMissing = 0L;
        this.count = 0L;
        this.sum = 0L;
        this.sumSquares = 0L;
        this.histogram.clear();
        this.rawData.clear();
    }

    public final long getMean() {
        long j = this.count;
        if (j <= 0) {
            return 0L;
        }
        return this.sum / j;
    }

    public final long getMedianBinStart() {
        long j = this.count;
        long j2 = 0;
        if (j <= 0) {
            return 0L;
        }
        long j3 = j >> 1;
        for (int i = 0; i < this.histogram.size(); i++) {
            j2 += ((AtomicLong) this.histogram.get(i)).longValue();
            if (j2 >= j3) {
                return histogramBinToStartValue$ar$ds(i);
            }
        }
        return histogramBinToStartValue$ar$ds(this.histogram.size());
    }

    public final long getPercentile(int i) {
        if (this.rawData.isEmpty()) {
            return -1L;
        }
        ArrayList arrayList = new ArrayList(this.rawData);
        Collections.sort(arrayList);
        return ((Long) arrayList.get((((i * arrayList.size()) + 99) / 100) - 1)).longValue();
    }

    public final double getStdDev() {
        long j = this.count;
        if (j <= 0) {
            return 0.0d;
        }
        double d = j;
        double d2 = this.sum / d;
        return Math.sqrt((this.sumSquares / d) - (d2 * d2));
    }

    public final synchronized void increment(long j) {
        int i;
        this.count++;
        this.sum += j;
        long j2 = this.sumSquares;
        Long.signum(j);
        this.sumSquares = j2 + (j * j);
        if (j < 1) {
            i = -1;
        } else {
            long j3 = -1;
            for (long j4 = j; j4 > 0; j4 >>= 1) {
                j3++;
            }
            i = (int) j3;
        }
        int i2 = i + 1;
        int i3 = i + 2;
        if (this.histogram.size() < i3) {
            this.histogram.ensureCapacity(i3);
            while (this.histogram.size() <= i2) {
                this.histogram.add(new AtomicLong(0L));
            }
        }
        AtomicLong atomicLong = (AtomicLong) this.histogram.get(i2);
        atomicLong.set(atomicLong.longValue() + 1);
        this.rawData.add(Long.valueOf(j));
    }

    public final synchronized void incrementNumMissing() {
        this.numMissing++;
    }
}
