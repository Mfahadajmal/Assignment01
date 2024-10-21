package com.google.android.libraries.performance.primes.metrics.memory;

import _COROUTINE._BOUNDARY;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryConfigurations implements MetricConfigurations {
    private final boolean captureMemoryInfo;
    private final int enablement$ar$edu;
    private final Optional metricExtensionProvider;
    private final int rateLimitPerSecond;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public boolean captureMemoryInfo;
        private int enablement$ar$edu;
        public Optional metricExtensionProvider;
        public int rateLimitPerSecond;
        public byte set$0;

        public Builder() {
        }

        public final MemoryConfigurations build() {
            int i;
            if (this.set$0 == Byte.MAX_VALUE && (i = this.enablement$ar$edu) != 0) {
                return new MemoryConfigurations(i, this.rateLimitPerSecond, this.metricExtensionProvider, this.captureMemoryInfo);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement$ar$edu == 0) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" rateLimitPerSecond");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" recordMetricPerProcess");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" forceGcBeforeRecordMemory");
            }
            if ((this.set$0 & 8) == 0) {
                sb.append(" captureDebugMetrics");
            }
            if ((this.set$0 & 16) == 0) {
                sb.append(" captureMemoryInfo");
            }
            if ((this.set$0 & 32) == 0) {
                sb.append(" recordMemoryPeriodically");
            }
            if ((this.set$0 & 64) == 0) {
                sb.append(" randomizePeriodicMemoryMetricStartTime");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final Builder setEnabled(boolean z) {
            int i;
            if (true != z) {
                i = 2;
            } else {
                i = 3;
            }
            return setEnablement$ar$edu$358676b1_0(i);
        }

        public final Builder setEnablement$ar$edu$358676b1_0(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public Builder(byte[] bArr) {
            this();
            this.metricExtensionProvider = Absent.INSTANCE;
        }
    }

    public MemoryConfigurations() {
    }

    public static Builder newBuilder() {
        Builder builder = new Builder(null);
        builder.rateLimitPerSecond = 3;
        byte b = builder.set$0;
        builder.metricExtensionProvider = Absent.INSTANCE;
        builder.captureMemoryInfo = true;
        builder.set$0 = (byte) (b | 31);
        builder.setEnablement$ar$edu$358676b1_0(1);
        builder.set$0 = (byte) (builder.set$0 | 96);
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MemoryConfigurations)) {
            return false;
        }
        MemoryConfigurations memoryConfigurations = (MemoryConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = memoryConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && this.rateLimitPerSecond == memoryConfigurations.getRateLimitPerSecond() && this.metricExtensionProvider.equals(memoryConfigurations.getMetricExtensionProvider()) && this.captureMemoryInfo == memoryConfigurations.getCaptureMemoryInfo()) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final boolean getCaptureMemoryInfo() {
        return this.captureMemoryInfo;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    public final Optional getMetricExtensionProvider() {
        return this.metricExtensionProvider;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final int getRateLimitPerSecond() {
        return this.rateLimitPerSecond;
    }

    public final int hashCode() {
        int i;
        int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003;
        if (true != this.captureMemoryInfo) {
            i = 1237;
        } else {
            i = 1231;
        }
        return (((((((((((((((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ this.rateLimitPerSecond) * 1000003) ^ 1237) * 1000003) ^ 2040732332) * 1000003) ^ 1237) * 1000003) ^ 1237) * 1000003) ^ i) * 1000003) ^ 1237) * 1000003) ^ 1237;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement$ar$edu() != 2) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "MemoryConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", rateLimitPerSecond=" + this.rateLimitPerSecond + ", recordMetricPerProcess=false, metricExtensionProvider=" + String.valueOf(this.metricExtensionProvider) + ", forceGcBeforeRecordMemory=false, captureDebugMetrics=false, captureMemoryInfo=" + this.captureMemoryInfo + ", recordMemoryPeriodically=false, randomizePeriodicMemoryMetricStartTime=false}";
    }

    public MemoryConfigurations(int i, int i2, Optional optional, boolean z) {
        this();
        this.enablement$ar$edu = i;
        this.rateLimitPerSecond = i2;
        this.metricExtensionProvider = optional;
        this.captureMemoryInfo = z;
    }
}
