package com.google.frameworks.client.data.android.impl;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.base.Suppliers$SupplierOfInstance;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.ChannelConfig;
import com.google.frameworks.client.data.android.ServiceAuthority;
import com.google.frameworks.client.data.android.Transport$TransportConfig;
import com.google.frameworks.client.data.android.metrics.TrafficStatsKeys;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.MethodDescriptor;
import j$.util.concurrent.ConcurrentHashMap;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TransportChannelCachingChannel extends Channel {
    private final ChannelConfig channelConfig;
    private final String preferredHostname;
    private final Object authorityLock = new Object();
    private final ConcurrentHashMap uriToChannel = new ConcurrentHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ChannelRuntimeConfig {
        public final long grpcIdleTimeoutMillis;
        private final long grpcKeepAliveTimeMillis;
        private final long grpcKeepAliveTimeoutMillis;
        public final Integer trafficStatsTag;
        public final Integer trafficStatsUid;
        public final URI uri;

        public ChannelRuntimeConfig() {
        }

        public final boolean equals(Object obj) {
            Integer num;
            Integer num2;
            if (obj == this) {
                return true;
            }
            if (obj instanceof ChannelRuntimeConfig) {
                ChannelRuntimeConfig channelRuntimeConfig = (ChannelRuntimeConfig) obj;
                if (this.uri.equals(channelRuntimeConfig.uri()) && this.grpcIdleTimeoutMillis == channelRuntimeConfig.grpcIdleTimeoutMillis() && this.grpcKeepAliveTimeMillis == channelRuntimeConfig.grpcKeepAliveTimeMillis() && this.grpcKeepAliveTimeoutMillis == channelRuntimeConfig.grpcKeepAliveTimeoutMillis() && ((num = this.trafficStatsUid) != null ? num.equals(channelRuntimeConfig.trafficStatsUid()) : channelRuntimeConfig.trafficStatsUid() == null) && ((num2 = this.trafficStatsTag) != null ? num2.equals(channelRuntimeConfig.trafficStatsTag()) : channelRuntimeConfig.trafficStatsTag() == null)) {
                    return true;
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
            int hashCode2 = this.uri.hashCode() ^ 1000003;
            Integer num = this.trafficStatsUid;
            int i = 0;
            if (num == null) {
                hashCode = 0;
            } else {
                hashCode = num.hashCode();
            }
            long j = this.grpcIdleTimeoutMillis;
            long j2 = this.grpcKeepAliveTimeMillis;
            long j3 = this.grpcKeepAliveTimeoutMillis;
            int i2 = ((((((((hashCode2 * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003) ^ hashCode) * 1000003;
            Integer num2 = this.trafficStatsTag;
            if (num2 != null) {
                i = num2.hashCode();
            }
            return i2 ^ i;
        }

        public final String toString() {
            return "ChannelRuntimeConfig{uri=" + this.uri.toString() + ", grpcIdleTimeoutMillis=" + this.grpcIdleTimeoutMillis + ", grpcKeepAliveTimeMillis=" + this.grpcKeepAliveTimeMillis + ", grpcKeepAliveTimeoutMillis=" + this.grpcKeepAliveTimeoutMillis + ", trafficStatsUid=" + this.trafficStatsUid + ", trafficStatsTag=" + this.trafficStatsTag + "}";
        }

        public final Integer trafficStatsTag() {
            return this.trafficStatsTag;
        }

        public final Integer trafficStatsUid() {
            return this.trafficStatsUid;
        }

        public final URI uri() {
            return this.uri;
        }

        public ChannelRuntimeConfig(URI uri, long j, long j2, long j3, Integer num, Integer num2) {
            this();
            this.uri = uri;
            this.grpcIdleTimeoutMillis = j;
            this.grpcKeepAliveTimeMillis = j2;
            this.grpcKeepAliveTimeoutMillis = j3;
            this.trafficStatsUid = num;
            this.trafficStatsTag = num2;
        }
    }

    public TransportChannelCachingChannel(String str, ChannelConfig channelConfig) {
        this.preferredHostname = str;
        this.channelConfig = channelConfig;
    }

    private static final URI uriFromAuthority$ar$ds(String str) {
        try {
            URI uri = new URI(null, str, null, null, null);
            if (uri.getPort() != -1) {
                return uri;
            }
            return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), 443, uri.getPath(), uri.getQuery(), uri.getFragment());
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Malformed endpoint authority", e);
        }
    }

    @Override // io.grpc.Channel
    public final String authority() {
        return this.preferredHostname;
    }

    @Override // io.grpc.Channel
    public final ClientCall newCall(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        Object obj;
        ChannelRuntimeConfig channelRuntimeConfig;
        TransportChannelCachingChannel transportChannelCachingChannel = this;
        String str = (String) callOptions.getOption(ServiceAuthority.KEY);
        ChannelConfig channelConfig = transportChannelCachingChannel.channelConfig;
        if (str == null) {
            str = transportChannelCachingChannel.preferredHostname;
        }
        URI uriFromAuthority$ar$ds = uriFromAuthority$ar$ds(str);
        ContextDataProvider.checkState(!TextUtils.isEmpty(uriFromAuthority$ar$ds.getAuthority()), "Could not parse channel authority");
        Integer num = (Integer) callOptions.getOption(TrafficStatsKeys.TRAFFIC_STATS_UID);
        Integer num2 = (Integer) callOptions.getOption(TrafficStatsKeys.TRAFFIC_STATS_TAG);
        long longValue = ((Long) ((Suppliers$SupplierOfInstance) transportChannelCachingChannel.channelConfig.grpcIdleTimeoutMillis()).instance).longValue();
        ChannelConfig channelConfig2 = transportChannelCachingChannel.channelConfig;
        ChannelRuntimeConfig channelRuntimeConfig2 = new ChannelRuntimeConfig(uriFromAuthority$ar$ds, longValue, channelConfig2.grpcKeepAliveTimeMillis(), channelConfig2.grpcKeepAliveTimeoutMillis(), num, num2);
        TransportChannel transportChannel = (TransportChannel) transportChannelCachingChannel.uriToChannel.get(channelRuntimeConfig2);
        if (transportChannel == null) {
            Object obj2 = transportChannelCachingChannel.authorityLock;
            synchronized (obj2) {
                try {
                    try {
                        if (!transportChannelCachingChannel.uriToChannel.containsKey(channelRuntimeConfig2)) {
                            Suppliers$SupplierOfInstance suppliers$SupplierOfInstance = new Suppliers$SupplierOfInstance(false);
                            Transport$TransportConfig.Builder builder = new Transport$TransportConfig.Builder(null);
                            builder.setRecordNetworkMetricsToPrimes$ar$ds(suppliers$SupplierOfInstance);
                            builder.setMaxMessageSize$ar$ds(4194304);
                            builder.setGrpcKeepAliveTimeMillis$ar$ds(Long.MAX_VALUE);
                            builder.setGrpcKeepAliveTimeoutMillis$ar$ds(Transport$TransportConfig.DEFAULT_KEEPALIVE_TIMEOUT_MILLIS);
                            Context context = channelConfig.context();
                            if (context != null) {
                                builder.applicationContext = context;
                                builder.uri = channelRuntimeConfig2.uri;
                                builder.trafficStatsUid = channelRuntimeConfig2.trafficStatsUid;
                                builder.trafficStatsTag = channelRuntimeConfig2.trafficStatsTag;
                                builder.grpcIdleTimeoutMillis = channelRuntimeConfig2.grpcIdleTimeoutMillis;
                                builder.set$0 = (byte) (builder.set$0 | 1);
                                Executor networkExecutor = channelConfig.networkExecutor();
                                if (networkExecutor != null) {
                                    builder.networkExecutor = networkExecutor;
                                    Executor transportExecutor = channelConfig.transportExecutor();
                                    if (transportExecutor != null) {
                                        builder.transportExecutor = transportExecutor;
                                        builder.setRecordNetworkMetricsToPrimes$ar$ds(channelConfig.recordNetworkMetricsToPrimes());
                                        builder.setMaxMessageSize$ar$ds(channelConfig.maxMessageSize());
                                        builder.setGrpcKeepAliveTimeMillis$ar$ds(channelConfig.grpcKeepAliveTimeMillis());
                                        builder.setGrpcKeepAliveTimeoutMillis$ar$ds(channelConfig.grpcKeepAliveTimeoutMillis());
                                        if (builder.set$0 == 15 && builder.applicationContext != null && builder.uri != null && builder.networkExecutor != null && builder.transportExecutor != null && builder.recordNetworkMetricsToPrimes != null) {
                                            try {
                                                obj = obj2;
                                            } catch (Throwable th) {
                                                th = th;
                                                obj = obj2;
                                                throw th;
                                            }
                                            try {
                                                TransportChannel transportChannel2 = new TransportChannel(channelConfig.transport$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(), new Transport$TransportConfig(builder.applicationContext, builder.uri, builder.networkExecutor, builder.transportExecutor, builder.recordNetworkMetricsToPrimes, builder.trafficStatsUid, builder.trafficStatsTag, builder.grpcIdleTimeoutMillis, builder.maxMessageSize, builder.grpcKeepAliveTimeMillis, builder.grpcKeepAliveTimeoutMillis), channelConfig.ioExecutor());
                                                transportChannelCachingChannel = this;
                                                channelRuntimeConfig = channelRuntimeConfig2;
                                                transportChannelCachingChannel.uriToChannel.put(channelRuntimeConfig, transportChannel2);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                throw th;
                                            }
                                        }
                                        StringBuilder sb = new StringBuilder();
                                        if (builder.applicationContext == null) {
                                            sb.append(" applicationContext");
                                        }
                                        if (builder.uri == null) {
                                            sb.append(" uri");
                                        }
                                        if (builder.networkExecutor == null) {
                                            sb.append(" networkExecutor");
                                        }
                                        if (builder.transportExecutor == null) {
                                            sb.append(" transportExecutor");
                                        }
                                        if (builder.recordNetworkMetricsToPrimes == null) {
                                            sb.append(" recordNetworkMetricsToPrimes");
                                        }
                                        if ((builder.set$0 & 1) == 0) {
                                            sb.append(" grpcIdleTimeoutMillis");
                                        }
                                        if ((builder.set$0 & 2) == 0) {
                                            sb.append(" maxMessageSize");
                                        }
                                        if ((builder.set$0 & 4) == 0) {
                                            sb.append(" grpcKeepAliveTimeMillis");
                                        }
                                        if ((builder.set$0 & 8) == 0) {
                                            sb.append(" grpcKeepAliveTimeoutMillis");
                                        }
                                        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
                                    }
                                    throw new NullPointerException("Null transportExecutor");
                                }
                                throw new NullPointerException("Null networkExecutor");
                            }
                            throw new NullPointerException("Null applicationContext");
                        }
                        channelRuntimeConfig = channelRuntimeConfig2;
                        obj = obj2;
                        transportChannel = (TransportChannel) transportChannelCachingChannel.uriToChannel.get(channelRuntimeConfig);
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }
        return transportChannel.newCall(methodDescriptor, callOptions);
    }
}
