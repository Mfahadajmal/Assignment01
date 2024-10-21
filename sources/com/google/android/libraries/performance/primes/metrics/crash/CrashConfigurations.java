package com.google.android.libraries.performance.primes.metrics.crash;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashConfigurations implements MetricConfigurations {
    private final Optional crashLoopListener;
    private final int debugLogsSize;
    private final int enablement$ar$edu;
    public final float startupSamplePercentage;

    public CrashConfigurations() {
    }

    public static final TimerConfigurations.Builder newBuilder$ar$class_merging$a37d6a6_0() {
        TimerConfigurations.Builder builder = new TimerConfigurations.Builder(null, null);
        builder.samplingProbability = 100.0f;
        builder.set$0 = (byte) (builder.set$0 | 1);
        builder.setEnablement$ar$edu$62097d16_0$ar$class_merging(1);
        builder.enablement$ar$edu = 100;
        builder.set$0 = (byte) (builder.set$0 | 2);
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashConfigurations)) {
            return false;
        }
        CrashConfigurations crashConfigurations = (CrashConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = crashConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && Float.floatToIntBits(this.startupSamplePercentage) == Float.floatToIntBits(crashConfigurations.getStartupSamplePercentage()) && this.debugLogsSize == crashConfigurations.getDebugLogsSize() && this.crashLoopListener.equals(crashConfigurations.getCrashLoopListener())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final Optional getCrashLoopListener() {
        return this.crashLoopListener;
    }

    public final int getDebugLogsSize() {
        return this.debugLogsSize;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final /* synthetic */ int getRateLimitPerSecond() {
        return Preference.DEFAULT_ORDER;
    }

    public final float getStartupSamplePercentage() {
        return this.startupSamplePercentage;
    }

    public final int hashCode() {
        return ((((((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.startupSamplePercentage)) * 1000003) ^ this.debugLogsSize) * (-721379959)) ^ 2040732332;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        int enablement$ar$edu = getEnablement$ar$edu();
        if (enablement$ar$edu == 3 || enablement$ar$edu == 1) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "CrashConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", startupSamplePercentage=" + this.startupSamplePercentage + ", debugLogsSize=" + this.debugLogsSize + ", metricExtensionProvider=null, crashLoopListener=" + String.valueOf(this.crashLoopListener) + "}";
    }

    public CrashConfigurations(int i, float f, int i2, Optional optional) {
        this();
        this.enablement$ar$edu = i;
        this.startupSamplePercentage = f;
        this.debugLogsSize = i2;
        this.crashLoopListener = optional;
    }
}
