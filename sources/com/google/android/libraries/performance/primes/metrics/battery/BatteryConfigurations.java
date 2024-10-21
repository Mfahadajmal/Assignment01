package com.google.android.libraries.performance.primes.metrics.battery;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryConfigurations implements MetricConfigurations {
    private final int enablement$ar$edu;
    private final BatteryMetricService metricExtensionProvider$ar$class_merging$ar$class_merging;

    public BatteryConfigurations() {
    }

    public static final StorageConfigurations.Builder newBuilder$ar$class_merging$868ce2b7_0() {
        StorageConfigurations.Builder builder = new StorageConfigurations.Builder(null, null);
        builder.StorageConfigurations$Builder$ar$dirStatsConfigurations = new BatteryMetricService((char[]) null);
        builder.setEnablement$ar$edu$ar$class_merging(1);
        builder.set$0 = (byte) 1;
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BatteryConfigurations)) {
            return false;
        }
        BatteryConfigurations batteryConfigurations = (BatteryConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = batteryConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && this.metricExtensionProvider$ar$class_merging$ar$class_merging.equals(batteryConfigurations.getMetricExtensionProvider$ar$class_merging$ar$class_merging())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    public final BatteryMetricService getMetricExtensionProvider$ar$class_merging$ar$class_merging() {
        return this.metricExtensionProvider$ar$class_merging$ar$class_merging;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final /* synthetic */ int getRateLimitPerSecond() {
        return Preference.DEFAULT_ORDER;
    }

    public final int hashCode() {
        return ((((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ 1237) * 1000003) ^ this.metricExtensionProvider$ar$class_merging$ar$class_merging.hashCode();
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement$ar$edu() == 3) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "BatteryConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", chargeCounterEnabled=false, metricExtensionProvider=" + String.valueOf(this.metricExtensionProvider$ar$class_merging$ar$class_merging) + "}";
    }

    public BatteryConfigurations(int i, BatteryMetricService batteryMetricService) {
        this();
        this.enablement$ar$edu = i;
        this.metricExtensionProvider$ar$class_merging$ar$class_merging = batteryMetricService;
    }
}
