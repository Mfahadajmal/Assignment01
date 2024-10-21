package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import io.grpc.Attributes;
import io.grpc.ChannelLogger;
import io.grpc.HttpConnectProxiedSocketAddress;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ClientTransportFactory extends Closeable {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ClientTransportOptions {
        public HttpConnectProxiedSocketAddress connectProxiedSocketAddr;
        public String userAgent;
        public String authority = "unknown-authority";
        public Attributes eagAttributes = Attributes.EMPTY;

        public final boolean equals(Object obj) {
            if (!(obj instanceof ClientTransportOptions)) {
                return false;
            }
            ClientTransportOptions clientTransportOptions = (ClientTransportOptions) obj;
            if (!this.authority.equals(clientTransportOptions.authority) || !this.eagAttributes.equals(clientTransportOptions.eagAttributes) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.userAgent, clientTransportOptions.userAgent) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.connectProxiedSocketAddr, clientTransportOptions.connectProxiedSocketAddr)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.authority, this.eagAttributes, this.userAgent, this.connectProxiedSocketAddr});
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    ScheduledExecutorService getScheduledExecutorService();

    Collection getSupportedSocketAddressTypes();

    ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger);
}
