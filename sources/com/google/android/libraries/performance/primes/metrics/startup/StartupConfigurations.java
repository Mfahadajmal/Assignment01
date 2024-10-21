package com.google.android.libraries.performance.primes.metrics.startup;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupConfigurations implements MetricConfigurations {
    private final Optional customTimestampProvider;
    private final int enablement$ar$edu;
    private final Optional metricExtensionProvider;

    public StartupConfigurations() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StartupConfigurations)) {
            return false;
        }
        StartupConfigurations startupConfigurations = (StartupConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = startupConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (enablement$ar$edu == 1 && this.metricExtensionProvider.equals(startupConfigurations.getMetricExtensionProvider()) && this.customTimestampProvider.equals(startupConfigurations.getCustomTimestampProvider())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final Optional getCustomTimestampProvider() {
        return this.customTimestampProvider;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    public final Optional getMetricExtensionProvider() {
        return this.metricExtensionProvider;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final /* synthetic */ int getRateLimitPerSecond() {
        return Preference.DEFAULT_ORDER;
    }

    public final int hashCode() {
        return ((((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ 2040732332) * 1000003) ^ 2040732332;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement$ar$edu() == 1) {
            return true;
        }
        return false;
    }

    public final String toString() {
        Optional optional = this.customTimestampProvider;
        return "StartupConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", metricExtensionProvider=" + String.valueOf(this.metricExtensionProvider) + ", customTimestampProvider=" + String.valueOf(optional) + "}";
    }

    public StartupConfigurations(int i, Optional optional, Optional optional2) {
        this();
        this.enablement$ar$edu = 1;
        this.metricExtensionProvider = optional;
        this.customTimestampProvider = optional2;
    }
}
