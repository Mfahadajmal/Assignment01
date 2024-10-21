package com.google.android.libraries.performance.primes.sampling;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProbabilitySampler {
    private final Random random;
    private final float samplingRate;

    public ProbabilitySampler(Random random, float f) {
        boolean z = false;
        if (f >= 0.0f && f <= 1.0f) {
            z = true;
        }
        ContextDataProvider.checkArgument(z, (Object) "Sampling rate should be a floating number >= 0 and <= 1.");
        this.samplingRate = f;
        this.random = random;
    }

    public final boolean isSampleAllowed() {
        if (this.random.nextFloat() < this.samplingRate) {
            return true;
        }
        return false;
    }
}
