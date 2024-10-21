package com.google.frameworks.client.data.android;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.common.base.Supplier;
import io.grpc.internal.RetryingNameResolver;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChannelConfig {
    private final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    private final Context context;
    private final Supplier grpcIdleTimeoutMillis;
    private final long grpcKeepAliveTimeMillis;
    private final long grpcKeepAliveTimeoutMillis;
    private final Executor ioExecutor;
    private final int maxMessageSize;
    private final Executor networkExecutor;
    private final Supplier recordBandwidthMetrics;
    private final Supplier recordCachingMetricsToPrimes;
    private final Supplier recordNetworkMetricsToPrimes;
    private final RetryingNameResolver.ResolutionResultListener transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Executor transportExecutor;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
        public Context context;
        public Supplier grpcIdleTimeoutMillis;
        public long grpcKeepAliveTimeMillis;
        public long grpcKeepAliveTimeoutMillis;
        public Executor ioExecutor;
        public int maxMessageSize;
        public Executor networkExecutor;
        public Supplier recordBandwidthMetrics;
        public Supplier recordCachingMetricsToPrimes;
        public Supplier recordNetworkMetricsToPrimes;
        public byte set$0;
        public RetryingNameResolver.ResolutionResultListener transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging;
        public Executor transportExecutor;

        public Builder() {
        }

        public final ChannelConfig autoBuild() {
            Context context;
            SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan;
            RetryingNameResolver.ResolutionResultListener resolutionResultListener;
            Executor executor;
            Executor executor2;
            Executor executor3;
            Supplier supplier;
            Supplier supplier2;
            Supplier supplier3;
            Supplier supplier4;
            if (this.set$0 == 7 && (context = this.context) != null && (spannableUtils$NonCopyableTextSpan = this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging) != null && (resolutionResultListener = this.transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging) != null && (executor = this.transportExecutor) != null && (executor2 = this.ioExecutor) != null && (executor3 = this.networkExecutor) != null && (supplier = this.recordNetworkMetricsToPrimes) != null && (supplier2 = this.recordCachingMetricsToPrimes) != null && (supplier3 = this.recordBandwidthMetrics) != null && (supplier4 = this.grpcIdleTimeoutMillis) != null) {
                return new ChannelConfig(context, spannableUtils$NonCopyableTextSpan, resolutionResultListener, executor, executor2, executor3, supplier, supplier2, supplier3, supplier4, this.maxMessageSize, this.grpcKeepAliveTimeMillis, this.grpcKeepAliveTimeoutMillis);
            }
            StringBuilder sb = new StringBuilder();
            if (this.context == null) {
                sb.append(" context");
            }
            if (this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging == null) {
                sb.append(" clock");
            }
            if (this.transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging == null) {
                sb.append(" transport");
            }
            if (this.transportExecutor == null) {
                sb.append(" transportExecutor");
            }
            if (this.ioExecutor == null) {
                sb.append(" ioExecutor");
            }
            if (this.networkExecutor == null) {
                sb.append(" networkExecutor");
            }
            if (this.recordNetworkMetricsToPrimes == null) {
                sb.append(" recordNetworkMetricsToPrimes");
            }
            if (this.recordCachingMetricsToPrimes == null) {
                sb.append(" recordCachingMetricsToPrimes");
            }
            if (this.recordBandwidthMetrics == null) {
                sb.append(" recordBandwidthMetrics");
            }
            if (this.grpcIdleTimeoutMillis == null) {
                sb.append(" grpcIdleTimeoutMillis");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" maxMessageSize");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" grpcKeepAliveTimeMillis");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" grpcKeepAliveTimeoutMillis");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public ChannelConfig() {
    }

    public final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    }

    public final Context context() {
        return this.context;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ChannelConfig) {
            ChannelConfig channelConfig = (ChannelConfig) obj;
            if (this.context.equals(channelConfig.context()) && this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging.equals(channelConfig.clock$ar$class_merging$ar$class_merging$ar$class_merging()) && this.transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging.equals(channelConfig.transport$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging()) && this.transportExecutor.equals(channelConfig.transportExecutor()) && this.ioExecutor.equals(channelConfig.ioExecutor()) && this.networkExecutor.equals(channelConfig.networkExecutor()) && this.recordNetworkMetricsToPrimes.equals(channelConfig.recordNetworkMetricsToPrimes()) && this.recordCachingMetricsToPrimes.equals(channelConfig.recordCachingMetricsToPrimes()) && this.recordBandwidthMetrics.equals(channelConfig.recordBandwidthMetrics()) && this.grpcIdleTimeoutMillis.equals(channelConfig.grpcIdleTimeoutMillis()) && this.maxMessageSize == channelConfig.maxMessageSize() && this.grpcKeepAliveTimeMillis == channelConfig.grpcKeepAliveTimeMillis() && this.grpcKeepAliveTimeoutMillis == channelConfig.grpcKeepAliveTimeoutMillis()) {
                return true;
            }
        }
        return false;
    }

    public final Supplier grpcIdleTimeoutMillis() {
        return this.grpcIdleTimeoutMillis;
    }

    public final long grpcKeepAliveTimeMillis() {
        return this.grpcKeepAliveTimeMillis;
    }

    public final long grpcKeepAliveTimeoutMillis() {
        return this.grpcKeepAliveTimeoutMillis;
    }

    public final int hashCode() {
        int hashCode = ((((((((((((((((((this.context.hashCode() ^ 1000003) * 1000003) ^ this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging.hashCode()) * 1000003) ^ this.transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging.hashCode()) * 1000003) ^ this.transportExecutor.hashCode()) * 1000003) ^ this.ioExecutor.hashCode()) * 1000003) ^ this.networkExecutor.hashCode()) * (-429739981)) ^ this.recordNetworkMetricsToPrimes.hashCode()) * 1000003) ^ this.recordCachingMetricsToPrimes.hashCode()) * 1000003) ^ this.recordBandwidthMetrics.hashCode()) * 1000003) ^ this.grpcIdleTimeoutMillis.hashCode();
        long j = this.grpcKeepAliveTimeoutMillis;
        long j2 = j ^ (j >>> 32);
        long j3 = this.grpcKeepAliveTimeMillis;
        return ((((((hashCode * 1525764945) ^ this.maxMessageSize) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003) ^ ((int) j2)) * 1000003;
    }

    public final Executor ioExecutor() {
        return this.ioExecutor;
    }

    public final int maxMessageSize() {
        return this.maxMessageSize;
    }

    public final Executor networkExecutor() {
        return this.networkExecutor;
    }

    public final Supplier recordBandwidthMetrics() {
        return this.recordBandwidthMetrics;
    }

    public final Supplier recordCachingMetricsToPrimes() {
        return this.recordCachingMetricsToPrimes;
    }

    public final Supplier recordNetworkMetricsToPrimes() {
        return this.recordNetworkMetricsToPrimes;
    }

    public final String toString() {
        Supplier supplier = this.grpcIdleTimeoutMillis;
        Supplier supplier2 = this.recordBandwidthMetrics;
        Supplier supplier3 = this.recordCachingMetricsToPrimes;
        Supplier supplier4 = this.recordNetworkMetricsToPrimes;
        Executor executor = this.networkExecutor;
        Executor executor2 = this.ioExecutor;
        Executor executor3 = this.transportExecutor;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging;
        SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
        return "ChannelConfig{context=" + String.valueOf(this.context) + ", clock=" + String.valueOf(spannableUtils$NonCopyableTextSpan) + ", transport=" + String.valueOf(resolutionResultListener) + ", transportExecutor=" + String.valueOf(executor3) + ", ioExecutor=" + String.valueOf(executor2) + ", networkExecutor=" + String.valueOf(executor) + ", transportScheduledExecutor=null, authContextManager=null, rpcCacheProvider=null, userAgentOverride=null, recordNetworkMetricsToPrimes=" + String.valueOf(supplier4) + ", recordCachingMetricsToPrimes=" + String.valueOf(supplier3) + ", recordBandwidthMetrics=" + String.valueOf(supplier2) + ", grpcIdleTimeoutMillis=" + String.valueOf(supplier) + ", streamzConfig=null, grpcServiceConfig=null, consistencyTokenConfig=null, maxMessageSize=" + this.maxMessageSize + ", grpcKeepAliveTimeMillis=" + this.grpcKeepAliveTimeMillis + ", grpcKeepAliveTimeoutMillis=" + this.grpcKeepAliveTimeoutMillis + ", channelCredentials=null}";
    }

    public final RetryingNameResolver.ResolutionResultListener transport$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final Executor transportExecutor() {
        return this.transportExecutor;
    }

    public ChannelConfig(Context context, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, RetryingNameResolver.ResolutionResultListener resolutionResultListener, Executor executor, Executor executor2, Executor executor3, Supplier supplier, Supplier supplier2, Supplier supplier3, Supplier supplier4, int i, long j, long j2) {
        this();
        this.context = context;
        this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.transportExecutor = executor;
        this.ioExecutor = executor2;
        this.networkExecutor = executor3;
        this.recordNetworkMetricsToPrimes = supplier;
        this.recordCachingMetricsToPrimes = supplier2;
        this.recordBandwidthMetrics = supplier3;
        this.grpcIdleTimeoutMillis = supplier4;
        this.maxMessageSize = i;
        this.grpcKeepAliveTimeMillis = j;
        this.grpcKeepAliveTimeoutMillis = j2;
    }
}
