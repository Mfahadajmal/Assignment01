package com.google.android.libraries.performance.primes.metrics.network;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkConfigurations implements MetricConfigurations {
    private final int batchSize;
    private final int enablement$ar$edu;
    private final Optional metricExtensionProvider;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private int batchSize;
        private int enablement$ar$edu;
        public Optional metricExtensionProvider;
        public byte set$0;

        public Builder() {
        }

        public final NetworkConfigurations autoBuild() {
            int i;
            if (this.set$0 == 3 && (i = this.enablement$ar$edu) != 0) {
                return new NetworkConfigurations(i, this.batchSize, this.metricExtensionProvider);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement$ar$edu == 0) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" batchSize");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" enableUrlAutoSanitization");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final NetworkConfigurations build() {
            NetworkConfigurations autoBuild = autoBuild();
            ContextDataProvider.checkArgument(true, (Object) "only one of auto url auto sanitization and custom url sanitizer can be enabled.");
            return autoBuild;
        }

        public final void setBatchSize$ar$ds(int i) {
            this.batchSize = i;
            this.set$0 = (byte) (this.set$0 | 1);
        }

        public final Builder setEnabled(boolean z) {
            int i;
            if (true != z) {
                i = 2;
            } else {
                i = 3;
            }
            return setEnablement$ar$edu$75efd98c_0(i);
        }

        public final Builder setEnablement$ar$edu$75efd98c_0(int i) {
            this.enablement$ar$edu = i;
            return this;
        }

        public Builder(byte[] bArr) {
            this();
            this.metricExtensionProvider = Absent.INSTANCE;
        }
    }

    public NetworkConfigurations() {
    }

    public static final Builder newBuilder() {
        Builder builder = new Builder(null);
        builder.set$0 = (byte) (builder.set$0 | 2);
        builder.setBatchSize$ar$ds(50);
        builder.metricExtensionProvider = Absent.INSTANCE;
        builder.setEnablement$ar$edu$75efd98c_0(1);
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NetworkConfigurations)) {
            return false;
        }
        NetworkConfigurations networkConfigurations = (NetworkConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = networkConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (i == enablement$ar$edu && this.batchSize == networkConfigurations.getBatchSize() && this.metricExtensionProvider.equals(networkConfigurations.getMetricExtensionProvider())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final int getBatchSize() {
        return this.batchSize;
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
        return ((((((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ this.batchSize) * (-721379959)) ^ 1237) * 1000003) ^ 2040732332;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement$ar$edu() == 3) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "NetworkConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", batchSize=" + this.batchSize + ", urlSanitizer=null, enableUrlAutoSanitization=false, metricExtensionProvider=" + String.valueOf(this.metricExtensionProvider) + "}";
    }

    public NetworkConfigurations(int i, int i2, Optional optional) {
        this();
        this.enablement$ar$edu = i;
        this.batchSize = i2;
        this.metricExtensionProvider = optional;
    }
}
