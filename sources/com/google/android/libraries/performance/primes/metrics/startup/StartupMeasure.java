package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.metrics.core.PrimesInstant;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupMeasure {
    public static final StartupMeasure instance = new StartupMeasure();
    public volatile PrimesInstant onDrawBasedFirstDrawnAt;
    public volatile PrimesInstant preDrawBasedFirstDrawnAt;
}
