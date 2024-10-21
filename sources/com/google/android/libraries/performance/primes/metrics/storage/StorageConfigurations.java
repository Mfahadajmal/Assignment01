package com.google.android.libraries.performance.primes.metrics.storage;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageConfigurations implements MetricConfigurations {
    private final Optional dirStatsConfigurations;
    private final int enablement$ar$edu;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object StorageConfigurations$Builder$ar$dirStatsConfigurations;
        private int enablement$ar$edu;
        public byte set$0;

        public Builder() {
        }

        public final BatteryConfigurations build() {
            int i;
            Object obj;
            if (this.set$0 == 1 && (i = this.enablement$ar$edu) != 0 && (obj = this.StorageConfigurations$Builder$ar$dirStatsConfigurations) != null) {
                return new BatteryConfigurations(i, (BatteryMetricService) obj);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement$ar$edu == 0) {
                sb.append(" enablement");
            }
            if (this.set$0 == 0) {
                sb.append(" chargeCounterEnabled");
            }
            if (this.StorageConfigurations$Builder$ar$dirStatsConfigurations == null) {
                sb.append(" metricExtensionProvider");
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
            return setEnablement$ar$edu$d76d5f5_0(i);
        }

        public final Builder setEnabled$ar$class_merging(boolean z) {
            int i;
            if (true != z) {
                i = 2;
            } else {
                i = 3;
            }
            return setEnablement$ar$edu$ar$class_merging(i);
        }

        public final Builder setEnablement$ar$edu$ar$class_merging(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public final Builder setEnablement$ar$edu$d76d5f5_0(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public Builder(byte[] bArr) {
            this();
            this.StorageConfigurations$Builder$ar$dirStatsConfigurations = Absent.INSTANCE;
        }

        public Builder(byte[] bArr, byte[] bArr2) {
            this();
        }

        /* renamed from: build, reason: collision with other method in class */
        public final StorageConfigurations m201build() {
            int i;
            if (this.set$0 == 1 && (i = this.enablement$ar$edu) != 0) {
                return new StorageConfigurations(i, (Optional) this.StorageConfigurations$Builder$ar$dirStatsConfigurations);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement$ar$edu == 0) {
                sb.append(" enablement");
            }
            if (this.set$0 == 0) {
                sb.append(" manualCapture");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
    }

    public StorageConfigurations() {
    }

    public static final Builder newBuilder() {
        Builder builder = new Builder(null);
        builder.set$0 = (byte) 1;
        builder.StorageConfigurations$Builder$ar$dirStatsConfigurations = Absent.INSTANCE;
        builder.setEnablement$ar$edu$d76d5f5_0(1);
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StorageConfigurations)) {
            return false;
        }
        StorageConfigurations storageConfigurations = (StorageConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = storageConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && this.dirStatsConfigurations.equals(storageConfigurations.getDirStatsConfigurations())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final Optional getDirStatsConfigurations() {
        return this.dirStatsConfigurations;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final /* synthetic */ int getRateLimitPerSecond() {
        return Preference.DEFAULT_ORDER;
    }

    public final int hashCode() {
        return ((((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ 1237) * 1000003) ^ 2040732332;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement$ar$edu() == 3 || getEnablement$ar$edu() == 1) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "StorageConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", manualCapture=false, dirStatsConfigurations=" + String.valueOf(this.dirStatsConfigurations) + "}";
    }

    public StorageConfigurations(int i, Optional optional) {
        this();
        this.enablement$ar$edu = i;
        this.dirStatsConfigurations = optional;
    }
}
