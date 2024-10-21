package org.chromium.base.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CachingUmaRecorder {
    public final ReentrantReadWriteLock mRwLock = new ReentrantReadWriteLock(false);
    public final Map mHistogramByName = new HashMap();
    public final AtomicInteger mDroppedHistogramSampleCount = new AtomicInteger();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Histogram {
        private final List mSamples = new ArrayList(1);

        public final synchronized boolean addSample$ar$ds(int i) {
            if (this.mSamples.size() >= 256) {
                return false;
            }
            this.mSamples.add(Integer.valueOf(i));
            return true;
        }
    }

    public CachingUmaRecorder() {
        new ArrayList();
    }
}
