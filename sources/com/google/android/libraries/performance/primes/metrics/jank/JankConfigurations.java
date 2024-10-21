package com.google.android.libraries.performance.primes.metrics.jank;

import _COROUTINE._BOUNDARY;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JankConfigurations implements MetricConfigurations {
    private final int enablement$ar$edu;
    private final int rateLimitPerSecond;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public int enablement$ar$edu;
        public int rateLimitPerSecond;
        public byte set$0;

        public Builder() {
        }

        public final Builder setEnablement$ar$edu$b45733ce_0(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public JankConfigurations() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JankConfigurations)) {
            return false;
        }
        JankConfigurations jankConfigurations = (JankConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = jankConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && this.rateLimitPerSecond == jankConfigurations.getRateLimitPerSecond()) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final int getRateLimitPerSecond() {
        return this.rateLimitPerSecond;
    }

    public final int hashCode() {
        return ((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ this.rateLimitPerSecond;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return false;
    }

    public final String toString() {
        return "JankConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", rateLimitPerSecond=" + this.rateLimitPerSecond + "}";
    }

    public JankConfigurations(int i, int i2) {
        this();
        this.enablement$ar$edu = i;
        this.rateLimitPerSecond = i2;
    }
}
