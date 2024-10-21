package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfilingConfigurations implements MetricConfigurations {
    private final int enablement$ar$edu;
    private final int maxBufferSizeBytes;
    private final int sampleDurationMs;
    private final int sampleDurationSkewMs;
    private final int sampleFrequencyMicro;
    private final double samplesPerEpoch;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public int enablement$ar$edu;
        public int maxBufferSizeBytes;
        public int sampleDurationMs;
        public int sampleDurationSkewMs;
        public int sampleFrequencyMicro;
        public double samplesPerEpoch;
        public byte set$0;

        public Builder() {
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public CpuProfilingConfigurations() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CpuProfilingConfigurations)) {
            return false;
        }
        CpuProfilingConfigurations cpuProfilingConfigurations = (CpuProfilingConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = cpuProfilingConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (enablement$ar$edu == 1 && this.maxBufferSizeBytes == cpuProfilingConfigurations.getMaxBufferSizeBytes() && this.sampleDurationMs == cpuProfilingConfigurations.getSampleDurationMs() && this.sampleDurationSkewMs == cpuProfilingConfigurations.getSampleDurationSkewMs() && this.sampleFrequencyMicro == cpuProfilingConfigurations.getSampleFrequencyMicro() && Double.doubleToLongBits(this.samplesPerEpoch) == Double.doubleToLongBits(cpuProfilingConfigurations.getSamplesPerEpoch())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    public final int getMaxBufferSizeBytes() {
        return this.maxBufferSizeBytes;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final /* synthetic */ int getRateLimitPerSecond() {
        return Preference.DEFAULT_ORDER;
    }

    public final int getSampleDurationMs() {
        return this.sampleDurationMs;
    }

    public final int getSampleDurationSkewMs() {
        return this.sampleDurationSkewMs;
    }

    public final int getSampleFrequencyMicro() {
        return this.sampleFrequencyMicro;
    }

    public final double getSamplesPerEpoch() {
        return this.samplesPerEpoch;
    }

    public final int hashCode() {
        return ((((((((((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ this.maxBufferSizeBytes) * 1000003) ^ this.sampleDurationMs) * 1000003) ^ this.sampleDurationSkewMs) * 1000003) ^ this.sampleFrequencyMicro) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.samplesPerEpoch) >>> 32) ^ Double.doubleToLongBits(this.samplesPerEpoch)));
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return false;
    }

    public final String toString() {
        return "CpuProfilingConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", maxBufferSizeBytes=" + this.maxBufferSizeBytes + ", sampleDurationMs=" + this.sampleDurationMs + ", sampleDurationSkewMs=" + this.sampleDurationSkewMs + ", sampleFrequencyMicro=" + this.sampleFrequencyMicro + ", samplesPerEpoch=" + this.samplesPerEpoch + "}";
    }

    public CpuProfilingConfigurations(int i, int i2, int i3, int i4, int i5, double d) {
        this();
        this.enablement$ar$edu = 1;
        this.maxBufferSizeBytes = i2;
        this.sampleDurationMs = i3;
        this.sampleDurationSkewMs = i4;
        this.sampleFrequencyMicro = i5;
        this.samplesPerEpoch = d;
    }
}
