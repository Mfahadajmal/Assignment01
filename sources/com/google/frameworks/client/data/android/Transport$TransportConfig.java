package com.google.frameworks.client.data.android;

import android.content.Context;
import com.google.common.base.Supplier;
import java.net.URI;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Transport$TransportConfig {
    public static final long DEFAULT_KEEPALIVE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(20);
    public final Context applicationContext;
    public final long grpcIdleTimeoutMillis;
    private final long grpcKeepAliveTimeMillis;
    private final long grpcKeepAliveTimeoutMillis;
    public final int maxMessageSize;
    public final Executor networkExecutor;
    public final Supplier recordNetworkMetricsToPrimes;
    public final Integer trafficStatsTag;
    public final Integer trafficStatsUid;
    public final Executor transportExecutor;
    private final URI uri;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Context applicationContext;
        public long grpcIdleTimeoutMillis;
        public long grpcKeepAliveTimeMillis;
        public long grpcKeepAliveTimeoutMillis;
        public int maxMessageSize;
        public Executor networkExecutor;
        public Supplier recordNetworkMetricsToPrimes;
        public byte set$0;
        public Integer trafficStatsTag;
        public Integer trafficStatsUid;
        public Executor transportExecutor;
        public URI uri;

        public Builder() {
        }

        public final void setGrpcKeepAliveTimeMillis$ar$ds(long j) {
            this.grpcKeepAliveTimeMillis = j;
            this.set$0 = (byte) (this.set$0 | 4);
        }

        public final void setGrpcKeepAliveTimeoutMillis$ar$ds(long j) {
            this.grpcKeepAliveTimeoutMillis = j;
            this.set$0 = (byte) (this.set$0 | 8);
        }

        public final void setMaxMessageSize$ar$ds(int i) {
            this.maxMessageSize = i;
            this.set$0 = (byte) (this.set$0 | 2);
        }

        public final void setRecordNetworkMetricsToPrimes$ar$ds(Supplier supplier) {
            if (supplier != null) {
                this.recordNetworkMetricsToPrimes = supplier;
                return;
            }
            throw new NullPointerException("Null recordNetworkMetricsToPrimes");
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public Transport$TransportConfig() {
    }

    public final Context applicationContext() {
        return this.applicationContext;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Transport$TransportConfig) {
            Transport$TransportConfig transport$TransportConfig = (Transport$TransportConfig) obj;
            if (this.applicationContext.equals(transport$TransportConfig.applicationContext()) && this.uri.equals(transport$TransportConfig.uri()) && this.networkExecutor.equals(transport$TransportConfig.networkExecutor()) && this.transportExecutor.equals(transport$TransportConfig.transportExecutor())) {
                transport$TransportConfig.transportScheduledExecutorService$ar$ds();
                transport$TransportConfig.userAgentOverride$ar$ds$c7f6a4fc_0();
                if (this.recordNetworkMetricsToPrimes.equals(transport$TransportConfig.recordNetworkMetricsToPrimes())) {
                    transport$TransportConfig.grpcServiceConfig$ar$ds$cb4bd73f_0();
                    Integer num = this.trafficStatsUid;
                    if (num != null ? num.equals(transport$TransportConfig.trafficStatsUid()) : transport$TransportConfig.trafficStatsUid() == null) {
                        Integer num2 = this.trafficStatsTag;
                        if (num2 != null ? num2.equals(transport$TransportConfig.trafficStatsTag()) : transport$TransportConfig.trafficStatsTag() == null) {
                            if (this.grpcIdleTimeoutMillis == transport$TransportConfig.grpcIdleTimeoutMillis() && this.maxMessageSize == transport$TransportConfig.maxMessageSize() && this.grpcKeepAliveTimeMillis == transport$TransportConfig.grpcKeepAliveTimeMillis() && this.grpcKeepAliveTimeoutMillis == transport$TransportConfig.grpcKeepAliveTimeoutMillis()) {
                                transport$TransportConfig.channelCredentials$ar$ds$12134c08_0();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final long grpcIdleTimeoutMillis() {
        return this.grpcIdleTimeoutMillis;
    }

    public final long grpcKeepAliveTimeMillis() {
        return this.grpcKeepAliveTimeMillis;
    }

    public final long grpcKeepAliveTimeoutMillis() {
        return this.grpcKeepAliveTimeoutMillis;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2 = ((((((((this.applicationContext.hashCode() ^ 1000003) * 1000003) ^ this.uri.hashCode()) * 1000003) ^ this.networkExecutor.hashCode()) * 1000003) ^ this.transportExecutor.hashCode()) * 583896283) ^ this.recordNetworkMetricsToPrimes.hashCode();
        Integer num = this.trafficStatsUid;
        int i = 0;
        if (num == null) {
            hashCode = 0;
        } else {
            hashCode = num.hashCode();
        }
        int i2 = ((hashCode2 * (-721379959)) ^ hashCode) * 1000003;
        Integer num2 = this.trafficStatsTag;
        if (num2 != null) {
            i = num2.hashCode();
        }
        long j = this.grpcIdleTimeoutMillis;
        int i3 = (((((i2 ^ i) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.maxMessageSize) * 1000003;
        long j2 = this.grpcKeepAliveTimeMillis;
        int i4 = (i3 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        long j3 = this.grpcKeepAliveTimeoutMillis;
        return (i4 ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003;
    }

    public final int maxMessageSize() {
        return this.maxMessageSize;
    }

    public final Executor networkExecutor() {
        return this.networkExecutor;
    }

    public final Supplier recordNetworkMetricsToPrimes() {
        return this.recordNetworkMetricsToPrimes;
    }

    public final String toString() {
        Supplier supplier = this.recordNetworkMetricsToPrimes;
        Executor executor = this.transportExecutor;
        Executor executor2 = this.networkExecutor;
        URI uri = this.uri;
        return "TransportConfig{applicationContext=" + String.valueOf(this.applicationContext) + ", uri=" + String.valueOf(uri) + ", networkExecutor=" + String.valueOf(executor2) + ", transportExecutor=" + String.valueOf(executor) + ", transportScheduledExecutorService=null, userAgentOverride=null, recordNetworkMetricsToPrimes=" + String.valueOf(supplier) + ", grpcServiceConfig=null, trafficStatsUid=" + this.trafficStatsUid + ", trafficStatsTag=" + this.trafficStatsTag + ", grpcIdleTimeoutMillis=" + this.grpcIdleTimeoutMillis + ", maxMessageSize=" + this.maxMessageSize + ", grpcKeepAliveTimeMillis=" + this.grpcKeepAliveTimeMillis + ", grpcKeepAliveTimeoutMillis=" + this.grpcKeepAliveTimeoutMillis + ", channelCredentials=null}";
    }

    public final Integer trafficStatsTag() {
        return this.trafficStatsTag;
    }

    public final Integer trafficStatsUid() {
        return this.trafficStatsUid;
    }

    public final Executor transportExecutor() {
        return this.transportExecutor;
    }

    public final URI uri() {
        return this.uri;
    }

    public Transport$TransportConfig(Context context, URI uri, Executor executor, Executor executor2, Supplier supplier, Integer num, Integer num2, long j, int i, long j2, long j3) {
        this();
        this.applicationContext = context;
        this.uri = uri;
        this.networkExecutor = executor;
        this.transportExecutor = executor2;
        this.recordNetworkMetricsToPrimes = supplier;
        this.trafficStatsUid = num;
        this.trafficStatsTag = num2;
        this.grpcIdleTimeoutMillis = j;
        this.maxMessageSize = i;
        this.grpcKeepAliveTimeMillis = j2;
        this.grpcKeepAliveTimeoutMillis = j3;
    }

    public final void channelCredentials$ar$ds$12134c08_0() {
    }

    public final void grpcServiceConfig$ar$ds$cb4bd73f_0() {
    }

    public final void transportScheduledExecutorService$ar$ds() {
    }

    public final void userAgentOverride$ar$ds$c7f6a4fc_0() {
    }
}
