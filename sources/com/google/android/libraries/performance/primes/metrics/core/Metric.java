package com.google.android.libraries.performance.primes.metrics.core;

import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Metric {
    public final String accountableComponentName;
    public final String customEventName;
    public final int debugLogsSize;
    public final RecentLogs.TimestampCollection debugLogsTime;
    public final boolean isEventNameConstant;
    public final boolean isUnsampled;
    public final SystemHealthProto$SystemHealthMetric metric;
    public final ExtensionMetric$MetricExtension metricExtension;
    public final Long sampleRatePermille;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public String accountableComponentName;
        public String customEventName;
        private int debugLogsSize;
        public RecentLogs.TimestampCollection debugLogsTime;
        private boolean isEventNameConstant;
        private boolean isUnsampled;
        private SystemHealthProto$SystemHealthMetric metric;
        public ExtensionMetric$MetricExtension metricExtension;
        public Long sampleRatePermille;
        private byte set$0;

        public Builder() {
        }

        public final Metric build() {
            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric;
            if (this.set$0 == 7 && (systemHealthProto$SystemHealthMetric = this.metric) != null) {
                return new Metric(this.customEventName, this.isEventNameConstant, systemHealthProto$SystemHealthMetric, this.metricExtension, this.accountableComponentName, this.sampleRatePermille, this.isUnsampled, this.debugLogsTime, this.debugLogsSize);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" isEventNameConstant");
            }
            if (this.metric == null) {
                sb.append(" metric");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" isUnsampled");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" debugLogsSize");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final void setDebugLogsSize$ar$ds(int i) {
            this.debugLogsSize = i;
            this.set$0 = (byte) (this.set$0 | 4);
        }

        public final void setIsEventNameConstant$ar$ds(boolean z) {
            this.isEventNameConstant = z;
            this.set$0 = (byte) (this.set$0 | 1);
        }

        public final void setIsUnsampled$ar$ds(boolean z) {
            this.isUnsampled = z;
            this.set$0 = (byte) (this.set$0 | 2);
        }

        public final void setMetric$ar$ds(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
            if (systemHealthProto$SystemHealthMetric != null) {
                this.metric = systemHealthProto$SystemHealthMetric;
                return;
            }
            throw new NullPointerException("Null metric");
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public Metric() {
    }

    public static Builder newBuilder() {
        Builder builder = new Builder(null);
        builder.setIsEventNameConstant$ar$ds(false);
        builder.setIsUnsampled$ar$ds(false);
        builder.setDebugLogsSize$ar$ds(0);
        return builder;
    }

    public final boolean equals(Object obj) {
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension;
        String str;
        Long l;
        RecentLogs.TimestampCollection timestampCollection;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Metric) {
            Metric metric = (Metric) obj;
            String str2 = this.customEventName;
            if (str2 != null ? str2.equals(metric.getCustomEventName()) : metric.getCustomEventName() == null) {
                if (this.isEventNameConstant == metric.getIsEventNameConstant() && this.metric.equals(metric.getMetric()) && ((extensionMetric$MetricExtension = this.metricExtension) != null ? extensionMetric$MetricExtension.equals(metric.getMetricExtension()) : metric.getMetricExtension() == null) && ((str = this.accountableComponentName) != null ? str.equals(metric.getAccountableComponentName()) : metric.getAccountableComponentName() == null) && ((l = this.sampleRatePermille) != null ? l.equals(metric.getSampleRatePermille()) : metric.getSampleRatePermille() == null) && this.isUnsampled == metric.getIsUnsampled() && ((timestampCollection = this.debugLogsTime) != null ? timestampCollection.equals(metric.getDebugLogsTime()) : metric.getDebugLogsTime() == null) && this.debugLogsSize == metric.getDebugLogsSize()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String getAccountableComponentName() {
        return this.accountableComponentName;
    }

    public final String getCustomEventName() {
        return this.customEventName;
    }

    public final int getDebugLogsSize() {
        return this.debugLogsSize;
    }

    public final RecentLogs.TimestampCollection getDebugLogsTime() {
        return this.debugLogsTime;
    }

    public final boolean getIsEventNameConstant() {
        return this.isEventNameConstant;
    }

    public final boolean getIsUnsampled() {
        return this.isUnsampled;
    }

    public final SystemHealthProto$SystemHealthMetric getMetric() {
        return this.metric;
    }

    public final ExtensionMetric$MetricExtension getMetricExtension() {
        return this.metricExtension;
    }

    public final Long getSampleRatePermille() {
        return this.sampleRatePermille;
    }

    public final int hashCode() {
        int hashCode;
        int i;
        int hashCode2;
        int hashCode3;
        int hashCode4;
        String str = this.customEventName;
        int i2 = 0;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        int i3 = 1237;
        if (true != this.isEventNameConstant) {
            i = 1237;
        } else {
            i = 1231;
        }
        int hashCode5 = ((((hashCode ^ 1000003) * 1000003) ^ i) * 1000003) ^ this.metric.hashCode();
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension = this.metricExtension;
        if (extensionMetric$MetricExtension == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = extensionMetric$MetricExtension.hashCode();
        }
        int i4 = ((hashCode5 * 1000003) ^ hashCode2) * 1000003;
        String str2 = this.accountableComponentName;
        if (str2 == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = str2.hashCode();
        }
        int i5 = (i4 ^ hashCode3) * 1000003;
        Long l = this.sampleRatePermille;
        if (l == null) {
            hashCode4 = 0;
        } else {
            hashCode4 = l.hashCode();
        }
        int i6 = (i5 ^ hashCode4) * 1000003;
        if (true == this.isUnsampled) {
            i3 = 1231;
        }
        int i7 = (i6 ^ i3) * 1000003;
        RecentLogs.TimestampCollection timestampCollection = this.debugLogsTime;
        if (timestampCollection != null) {
            i2 = timestampCollection.hashCode();
        }
        return ((i7 ^ i2) * 1000003) ^ this.debugLogsSize;
    }

    public final String toString() {
        RecentLogs.TimestampCollection timestampCollection = this.debugLogsTime;
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension = this.metricExtension;
        return "Metric{customEventName=" + this.customEventName + ", isEventNameConstant=" + this.isEventNameConstant + ", metric=" + String.valueOf(this.metric) + ", metricExtension=" + String.valueOf(extensionMetric$MetricExtension) + ", accountableComponentName=" + this.accountableComponentName + ", sampleRatePermille=" + this.sampleRatePermille + ", isUnsampled=" + this.isUnsampled + ", debugLogsTime=" + String.valueOf(timestampCollection) + ", debugLogsSize=" + this.debugLogsSize + "}";
    }

    public Metric(String str, boolean z, SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, String str2, Long l, boolean z2, RecentLogs.TimestampCollection timestampCollection, int i) {
        this();
        this.customEventName = str;
        this.isEventNameConstant = z;
        this.metric = systemHealthProto$SystemHealthMetric;
        this.metricExtension = extensionMetric$MetricExtension;
        this.accountableComponentName = str2;
        this.sampleRatePermille = l;
        this.isUnsampled = z2;
        this.debugLogsTime = timestampCollection;
        this.debugLogsSize = i;
    }
}
