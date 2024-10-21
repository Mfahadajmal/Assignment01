package io.grpc.cronet;

import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.Attributes;
import io.grpc.ChannelLogger;
import io.grpc.ForwardingChannelBuilder2;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.OkHttpChannelBuilder;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.CronetEngine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetChannelBuilder extends ForwardingChannelBuilder2 {
    public final CronetEngine cronetEngine;
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    public int trafficStatsTag;
    public boolean trafficStatsTagSet;
    public int trafficStatsUid;
    public boolean trafficStatsUidSet;
    public final RemoteModelManager transportTracerFactory$ar$class_merging$ar$class_merging = TransportTracer.DEFAULT_FACTORY$ar$class_merging$ar$class_merging;
    public int maxMessageSize = 4194304;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetTransportFactory implements ClientTransportFactory {
        private final Executor executor;
        private final int maxMessageSize;
        private final StreamBuilderFactory streamFactory;
        private final TransportTracer transportTracer;
        private final boolean usingSharedScheduler = true;
        private final ScheduledExecutorService timeoutService = (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE);

        public CronetTransportFactory(StreamBuilderFactory streamBuilderFactory, Executor executor, ScheduledExecutorService scheduledExecutorService, int i, TransportTracer transportTracer) {
            this.maxMessageSize = i;
            this.streamFactory = streamBuilderFactory;
            executor.getClass();
            this.executor = executor;
            this.transportTracer = transportTracer;
        }

        @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            SharedResourceHolder.holder.releaseInternal$ar$ds(GrpcUtil.TIMER_SERVICE, this.timeoutService);
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public final ScheduledExecutorService getScheduledExecutorService() {
            return this.timeoutService;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public final Collection getSupportedSocketAddressTypes() {
            return Collections.singleton(InetSocketAddress.class);
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public final ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            String str = clientTransportOptions.authority;
            String str2 = clientTransportOptions.userAgent;
            Attributes attributes = clientTransportOptions.eagAttributes;
            Executor executor = this.executor;
            int i = this.maxMessageSize;
            return new CronetClientTransport(this.streamFactory, (InetSocketAddress) socketAddress, str, str2, attributes, executor, i, this.transportTracer);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StreamBuilderFactory {
        private final CronetEngine cronetEngine;
        private final int trafficStatsTag;
        private final boolean trafficStatsTagSet;
        private final int trafficStatsUid;
        private final boolean trafficStatsUidSet;

        public StreamBuilderFactory() {
        }

        public final BidirectionalStream.Builder newBidirectionalStreamBuilder(String str, BidirectionalStream.Callback callback, Executor executor) {
            boolean z = this.trafficStatsTagSet;
            BidirectionalStream.Builder newBidirectionalStreamBuilder = this.cronetEngine.newBidirectionalStreamBuilder(str, callback, executor);
            if (z) {
                newBidirectionalStreamBuilder.setTrafficStatsTag(this.trafficStatsTag);
            }
            if (this.trafficStatsUidSet) {
                newBidirectionalStreamBuilder.setTrafficStatsUid(this.trafficStatsUid);
            }
            return newBidirectionalStreamBuilder;
        }

        public StreamBuilderFactory(CronetEngine cronetEngine, boolean z, int i, boolean z2, int i2) {
            this();
            this.cronetEngine = cronetEngine;
            this.trafficStatsTagSet = z;
            this.trafficStatsTag = i;
            this.trafficStatsUidSet = z2;
            this.trafficStatsUid = i2;
        }
    }

    private CronetChannelBuilder(String str, int i, CronetEngine cronetEngine) {
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(InetSocketAddress.createUnresolved(str, i), GrpcUtil.authorityFromHostAndPort(str, i), new OkHttpChannelBuilder.OkHttpChannelTransportFactoryBuilder(this, 1));
        this.cronetEngine = cronetEngine;
    }

    public static CronetChannelBuilder forAddress(String str, int i, CronetEngine cronetEngine) {
        cronetEngine.getClass();
        return new CronetChannelBuilder(str, i, cronetEngine);
    }

    @Override // io.grpc.ForwardingChannelBuilder2
    public final ManagedChannelBuilder delegate() {
        return this.managedChannelImplBuilder;
    }
}
