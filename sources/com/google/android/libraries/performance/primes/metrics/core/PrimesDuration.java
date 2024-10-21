package com.google.android.libraries.performance.primes.metrics.core;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesDuration {
    private static final PrimesInstant UNSET_END_INSTANCE = new PrimesInstant(-1, -1);
    public PrimesInstant endInstant;
    public final PrimesInstant startInstant;

    public PrimesDuration(PrimesInstant primesInstant, PrimesInstant primesInstant2) {
        this.startInstant = primesInstant;
        this.endInstant = primesInstant2;
    }

    public PrimesDuration(SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan) {
        this.endInstant = UNSET_END_INSTANCE;
        this.startInstant = PrimesInstant.create$ar$class_merging$f6703772_0$ar$ds();
    }
}
