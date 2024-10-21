package com.google.android.libraries.performance.primes.metrics.trace;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceConfigurations implements MetricConfigurations {
    private final int enablement$ar$edu;
    public final float samplingProbability;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private int enablement$ar$edu;
        public float samplingProbability;
        public byte set$0;

        public Builder() {
        }

        public final TraceConfigurations autoBuild() {
            int i;
            if (this.set$0 == 1 && (i = this.enablement$ar$edu) != 0) {
                return new TraceConfigurations(i, this.samplingProbability);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement$ar$edu == 0) {
                sb.append(" enablement");
            }
            if (this.set$0 == 0) {
                sb.append(" samplingProbability");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final TraceConfigurations build() {
            TraceConfigurations autoBuild = autoBuild();
            float f = autoBuild.samplingProbability;
            boolean z = false;
            if (f >= 0.0f && f <= 1.0f) {
                z = true;
            }
            ContextDataProvider.checkState(z, "Probability shall be between 0 and 1.");
            return autoBuild;
        }

        public final Builder setEnabled(boolean z) {
            int i;
            if (true != z) {
                i = 2;
            } else {
                i = 3;
            }
            return setEnablement$ar$edu$a1cec65d_0(i);
        }

        public final Builder setEnablement$ar$edu$a1cec65d_0(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public TraceConfigurations() {
    }

    public static final Builder newBuilder() {
        Builder builder = new Builder(null);
        builder.samplingProbability = 0.5f;
        builder.set$0 = (byte) 1;
        builder.setEnablement$ar$edu$a1cec65d_0(1);
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceConfigurations)) {
            return false;
        }
        TraceConfigurations traceConfigurations = (TraceConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = traceConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && Float.floatToIntBits(this.samplingProbability) == Float.floatToIntBits(traceConfigurations.getSamplingProbability())) {
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
    public final /* synthetic */ int getRateLimitPerSecond() {
        return Preference.DEFAULT_ORDER;
    }

    public final float getSamplingProbability() {
        return this.samplingProbability;
    }

    public final int hashCode() {
        return ((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.samplingProbability);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement$ar$edu() == 3) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "TraceConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", samplingProbability=" + this.samplingProbability + "}";
    }

    public TraceConfigurations(int i, float f) {
        this();
        this.enablement$ar$edu = i;
        this.samplingProbability = f;
    }
}
