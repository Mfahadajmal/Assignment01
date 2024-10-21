package io.grpc;

import java.net.SocketAddress;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ProxyDetector {
    ProxiedSocketAddress proxyFor(SocketAddress socketAddress);
}
