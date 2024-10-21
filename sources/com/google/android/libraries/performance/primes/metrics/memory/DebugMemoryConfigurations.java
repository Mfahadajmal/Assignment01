package com.google.android.libraries.performance.primes.metrics.memory;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.common.collect.ImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DebugMemoryConfigurations implements MetricConfigurations {
    private final ImmutableSet debugMemoryEventsToSample;
    private final long debugMemoryServiceThrottleMs;
    private final int enablement$ar$edu;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public ImmutableSet debugMemoryEventsToSample;
        public long debugMemoryServiceThrottleMs;
        public int enablement$ar$edu;
        public byte set$0;

        public Builder() {
        }

        public final Builder setEnablement$ar$edu$dea24210_0(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public DebugMemoryConfigurations() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DebugMemoryConfigurations)) {
            return false;
        }
        DebugMemoryConfigurations debugMemoryConfigurations = (DebugMemoryConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = debugMemoryConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && this.debugMemoryServiceThrottleMs == debugMemoryConfigurations.getDebugMemoryServiceThrottleMs() && this.debugMemoryEventsToSample.equals(debugMemoryConfigurations.getDebugMemoryEventsToSample())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final ImmutableSet getDebugMemoryEventsToSample() {
        return this.debugMemoryEventsToSample;
    }

    public final long getDebugMemoryServiceThrottleMs() {
        return this.debugMemoryServiceThrottleMs;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final /* synthetic */ int getRateLimitPerSecond() {
        return Preference.DEFAULT_ORDER;
    }

    public final int hashCode() {
        int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003;
        long j = this.debugMemoryServiceThrottleMs;
        return (((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.debugMemoryEventsToSample.hashCode();
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return false;
    }

    public final String toString() {
        return "DebugMemoryConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", debugMemoryServiceThrottleMs=" + this.debugMemoryServiceThrottleMs + ", debugMemoryEventsToSample=" + String.valueOf(this.debugMemoryEventsToSample) + "}";
    }

    public DebugMemoryConfigurations(int i, long j, ImmutableSet immutableSet) {
        this();
        this.enablement$ar$edu = i;
        this.debugMemoryServiceThrottleMs = j;
        this.debugMemoryEventsToSample = immutableSet;
    }
}
