package com.google.android.libraries.performance.primes.metrics.core;

import android.os.SystemClock;
import j$.time.Duration;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesInstant {
    public final long elapsedRealtimeMs;
    public final long uptimeMillis;

    public PrimesInstant() {
    }

    public static PrimesInstant create$ar$class_merging$f6703772_0$ar$ds() {
        return new PrimesInstant(SystemClock.elapsedRealtime(), Duration.ofMillis(SystemClock.uptimeMillis()).toMillis());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PrimesInstant) {
            PrimesInstant primesInstant = (PrimesInstant) obj;
            if (this.elapsedRealtimeMs == primesInstant.getElapsedRealtimeMs() && this.uptimeMillis == primesInstant.getUptimeMillis()) {
                return true;
            }
        }
        return false;
    }

    public final long getElapsedRealtimeMs() {
        return this.elapsedRealtimeMs;
    }

    public final long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public final int hashCode() {
        long j = this.uptimeMillis;
        long j2 = this.elapsedRealtimeMs;
        return ((int) (j ^ (j >>> 32))) ^ ((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003);
    }

    public final String toString() {
        return "PrimesInstant{elapsedRealtimeMs=" + this.elapsedRealtimeMs + ", uptimeMillis=" + this.uptimeMillis + "}";
    }

    public PrimesInstant(long j, long j2) {
        this();
        this.elapsedRealtimeMs = j;
        this.uptimeMillis = j2;
    }
}
