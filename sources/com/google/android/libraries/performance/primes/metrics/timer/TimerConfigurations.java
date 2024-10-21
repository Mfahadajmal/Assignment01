package com.google.android.libraries.performance.primes.metrics.timer;

import _COROUTINE._BOUNDARY;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerConfigurations implements MetricConfigurations {
    private final int enablement$ar$edu;
    private final Optional perEventConfigurationFlags;
    private final int rateLimitPerSecond;
    public final float samplingProbability;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public int enablement$ar$edu;
        public Optional perEventConfigurationFlags;
        public int rateLimitPerSecond;
        public float samplingProbability;
        public byte set$0;

        public Builder() {
        }

        public final CrashConfigurations autoBuild() {
            int i;
            if (this.set$0 == 3 && (i = this.rateLimitPerSecond) != 0) {
                return new CrashConfigurations(i, this.samplingProbability, this.enablement$ar$edu, this.perEventConfigurationFlags);
            }
            StringBuilder sb = new StringBuilder();
            if (this.rateLimitPerSecond == 0) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" startupSamplePercentage");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" debugLogsSize");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final CrashConfigurations build() {
            CrashConfigurations autoBuild = autoBuild();
            float f = autoBuild.startupSamplePercentage;
            boolean z = false;
            if (f > 0.0f && f <= 100.0f) {
                z = true;
            }
            ContextDataProvider.checkArgument(z, (Object) "StartupSamplePercentage should be a floating number > 0 and <= 100.");
            return autoBuild;
        }

        public final Builder setEnabled(boolean z) {
            int i;
            if (true != z) {
                i = 2;
            } else {
                i = 3;
            }
            return setEnablement$ar$edu$1772fab0_0(i);
        }

        public final Builder setEnabled$ar$class_merging$5fe4575b_0(boolean z) {
            int i;
            if (true != z) {
                i = 2;
            } else {
                i = 3;
            }
            return setEnablement$ar$edu$62097d16_0$ar$class_merging(i);
        }

        public final Builder setEnablement$ar$edu$1772fab0_0(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public final Builder setEnablement$ar$edu$62097d16_0$ar$class_merging(int i) {
            this.rateLimitPerSecond = i;
            return this;
        }

        public final void setSamplingProbability$ar$ds(float f) {
            this.samplingProbability = f;
            this.set$0 = (byte) (this.set$0 | 2);
        }

        public Builder(byte[] bArr) {
            this();
            this.perEventConfigurationFlags = Absent.INSTANCE;
        }

        public Builder(byte[] bArr, byte[] bArr2) {
            this();
            this.perEventConfigurationFlags = Absent.INSTANCE;
        }

        /* renamed from: build, reason: collision with other method in class */
        public final TimerConfigurations m203build() {
            TimerConfigurations m202autoBuild = m202autoBuild();
            ContextDataProvider.checkState(true, "Rate limit per second must be >= 0");
            float f = m202autoBuild.samplingProbability;
            ContextDataProvider.checkState(f > 0.0f && f <= 1.0f, "Sampling Probability shall be > 0 and <= 1");
            return m202autoBuild;
        }

        /* renamed from: autoBuild, reason: collision with other method in class */
        public final TimerConfigurations m202autoBuild() {
            int i;
            if (this.set$0 == 3 && (i = this.enablement$ar$edu) != 0) {
                return new TimerConfigurations(i, this.rateLimitPerSecond, this.samplingProbability, this.perEventConfigurationFlags);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement$ar$edu == 0) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" rateLimitPerSecond");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" samplingProbability");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
    }

    public TimerConfigurations() {
    }

    public static final Builder newBuilder() {
        Builder builder = new Builder(null);
        builder.rateLimitPerSecond = 10;
        builder.set$0 = (byte) (builder.set$0 | 1);
        builder.setSamplingProbability$ar$ds(1.0f);
        builder.perEventConfigurationFlags = Absent.INSTANCE;
        builder.setEnablement$ar$edu$1772fab0_0(1);
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimerConfigurations)) {
            return false;
        }
        TimerConfigurations timerConfigurations = (TimerConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = timerConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && this.rateLimitPerSecond == timerConfigurations.getRateLimitPerSecond() && Float.floatToIntBits(this.samplingProbability) == Float.floatToIntBits(timerConfigurations.getSamplingProbability()) && this.perEventConfigurationFlags.equals(timerConfigurations.getPerEventConfigurationFlags())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    public final Optional getPerEventConfigurationFlags() {
        return this.perEventConfigurationFlags;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final int getRateLimitPerSecond() {
        return this.rateLimitPerSecond;
    }

    public final float getSamplingProbability() {
        return this.samplingProbability;
    }

    public final int hashCode() {
        return ((((((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ this.rateLimitPerSecond) * 1000003) ^ Float.floatToIntBits(this.samplingProbability)) * 1000003) ^ 2040732332;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement$ar$edu() == 3) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "TimerConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", rateLimitPerSecond=" + this.rateLimitPerSecond + ", samplingProbability=" + this.samplingProbability + ", perEventConfigurationFlags=" + String.valueOf(this.perEventConfigurationFlags) + "}";
    }

    public TimerConfigurations(int i, int i2, float f, Optional optional) {
        this();
        this.enablement$ar$edu = i;
        this.rateLimitPerSecond = i2;
        this.samplingProbability = f;
        this.perEventConfigurationFlags = optional;
    }
}
