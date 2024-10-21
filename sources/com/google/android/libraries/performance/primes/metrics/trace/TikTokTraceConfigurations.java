package com.google.android.libraries.performance.primes.metrics.trace;

import _COROUTINE._BOUNDARY;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricService;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TikTokTraceConfigurations implements MetricConfigurations {
    private final ApplicationExitMetricService dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging;
    private final int enablement$ar$edu;
    private final int rateLimitPerSecond;
    private final boolean recordTimerDuration;
    private final int traceFormat$ar$edu;
    private final Optional traceMetricExtensionProvider;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public ApplicationExitMetricService dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging;
        public int enablement$ar$edu;
        public int rateLimitPerSecond;
        public boolean recordTimerDuration;
        public byte set$0;
        public int traceFormat$ar$edu;
        public Optional traceMetricExtensionProvider;

        public Builder() {
        }

        public final Builder setEnablement$ar$edu$9b3aba90_0(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public Builder(byte[] bArr) {
            this();
            this.traceMetricExtensionProvider = Absent.INSTANCE;
        }
    }

    public TikTokTraceConfigurations() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TikTokTraceConfigurations)) {
            return false;
        }
        TikTokTraceConfigurations tikTokTraceConfigurations = (TikTokTraceConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = tikTokTraceConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && this.rateLimitPerSecond == tikTokTraceConfigurations.getRateLimitPerSecond() && this.dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging.equals(tikTokTraceConfigurations.getDynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging()) && this.traceMetricExtensionProvider.equals(tikTokTraceConfigurations.getTraceMetricExtensionProvider()) && this.recordTimerDuration == tikTokTraceConfigurations.isRecordTimerDuration()) {
                int i2 = this.traceFormat$ar$edu;
                int traceFormat$ar$edu = tikTokTraceConfigurations.getTraceFormat$ar$edu();
                if (i2 != 0) {
                    if (traceFormat$ar$edu == 1) {
                        return true;
                    }
                } else {
                    throw null;
                }
            }
            return false;
        }
        throw null;
    }

    public final ApplicationExitMetricService getDynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final int getRateLimitPerSecond() {
        return this.rateLimitPerSecond;
    }

    public final int getTraceFormat$ar$edu() {
        return this.traceFormat$ar$edu;
    }

    public final Optional getTraceMetricExtensionProvider() {
        return this.traceMetricExtensionProvider;
    }

    public final int hashCode() {
        int i;
        int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = ((((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ this.rateLimitPerSecond) * 1000003) ^ this.dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging.hashCode();
        if (true != this.recordTimerDuration) {
            i = 1237;
        } else {
            i = 1231;
        }
        return (((((((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ 2040732332) * 1000003) ^ i) * 1000003) ^ 1237) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.traceFormat$ar$edu);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return false;
    }

    public final boolean isRecordTimerDuration() {
        return this.recordTimerDuration;
    }

    public final String toString() {
        String str;
        int i = this.traceFormat$ar$edu;
        Optional optional = this.traceMetricExtensionProvider;
        String valueOf = String.valueOf(this.dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging);
        String valueOf2 = String.valueOf(optional);
        if (i != 1) {
            str = "null";
        } else {
            str = "DEFAULT";
        }
        int i2 = this.enablement$ar$edu;
        boolean z = this.recordTimerDuration;
        return "TikTokTraceConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(i2) + ", rateLimitPerSecond=" + this.rateLimitPerSecond + ", dynamicSampler=" + valueOf + ", traceMetricExtensionProvider=" + valueOf2 + ", recordTimerDuration=" + z + ", sendEmptyTraces=false, traceFormat=" + str + "}";
    }

    public TikTokTraceConfigurations(int i, int i2, ApplicationExitMetricService applicationExitMetricService, Optional optional, boolean z, int i3) {
        this();
        this.enablement$ar$edu = i;
        this.rateLimitPerSecond = i2;
        this.dynamicSampler$ar$class_merging$ar$class_merging$ar$class_merging = applicationExitMetricService;
        this.traceMetricExtensionProvider = optional;
        this.recordTimerDuration = z;
        this.traceFormat$ar$edu = 1;
    }
}
