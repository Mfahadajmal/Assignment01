package com.google.android.libraries.performance.primes.sampling;

import android.R;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApproximateHistogram {
    public final short[] histogramForHighBits;
    public final short[] histogramForNextBits;
    public long lastScaleDownTimeMillis;
    public final int multiplier;

    public ApproximateHistogram(Random random) {
        ContextDataProvider.checkArgument(true);
        this.histogramForHighBits = new short[256];
        this.histogramForNextBits = new short[256];
        this.multiplier = (random.nextInt() & (-33686019)) | R.attr.cacheColorHint;
        this.lastScaleDownTimeMillis = 0L;
    }
}
