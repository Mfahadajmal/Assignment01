package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import io.grpc.InternalChannelz$ChannelTrace$Event;
import io.grpc.ProxiedSocketAddress;
import io.grpc.ProxyDetector;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProxyDetectorImpl implements ProxyDetector {
    private final ServiceConfigUtil authenticationProvider$ar$class_merging$ar$class_merging;
    private final InetSocketAddress overrideProxyAddress;
    private final Supplier proxySelector;
    public static final Logger log = Logger.getLogger(ProxyDetectorImpl.class.getName());
    private static final ServiceConfigUtil DEFAULT_AUTHENTICATOR$ar$class_merging$ar$class_merging = new ServiceConfigUtil();
    private static final Supplier DEFAULT_PROXY_SELECTOR = new AnonymousClass2(0);

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.ProxyDetectorImpl$2, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass2 implements Supplier {
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(int i) {
            this.switching_field = i;
        }

        @Override // com.google.common.base.Supplier
        public final /* synthetic */ Object get() {
            if (this.switching_field != 0) {
                return new Stopwatch();
            }
            return ProxySelector.getDefault();
        }
    }

    public ProxyDetectorImpl() {
        int i;
        Supplier supplier = DEFAULT_PROXY_SELECTOR;
        ServiceConfigUtil serviceConfigUtil = DEFAULT_AUTHENTICATOR$ar$class_merging$ar$class_merging;
        String str = System.getenv("GRPC_PROXY_EXP");
        supplier.getClass();
        this.proxySelector = supplier;
        serviceConfigUtil.getClass();
        this.authenticationProvider$ar$class_merging$ar$class_merging = serviceConfigUtil;
        if (str != null) {
            String[] split = str.split(":", 2);
            if (split.length > 1) {
                i = Integer.parseInt(split[1]);
            } else {
                i = 80;
            }
            log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl", "overrideProxy", "Detected GRPC_PROXY_EXP and will honor it, but this feature will be removed in a future release. Use the JVM flags \"-Dhttps.proxyHost=HOST -Dhttps.proxyPort=PORT\" to set the https proxy for this JVM.");
            this.overrideProxyAddress = new InetSocketAddress(split[0], i);
            return;
        }
        this.overrideProxyAddress = null;
    }

    private final ProxiedSocketAddress detectProxy(InetSocketAddress inetSocketAddress) {
        String str = null;
        try {
            try {
                URI uri = new URI("https", null, GrpcUtil.getHost(inetSocketAddress), inetSocketAddress.getPort(), null, null, null);
                ProxySelector proxySelector = (ProxySelector) this.proxySelector.get();
                if (proxySelector == null) {
                    log.logp(Level.FINE, "io.grpc.internal.ProxyDetectorImpl", "detectProxy", "proxy selector is null, so continuing without proxy lookup");
                    return null;
                }
                List<Proxy> select = proxySelector.select(uri);
                if (select.size() > 1) {
                    log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl", "detectProxy", "More than 1 proxy detected, gRPC will select the first one");
                }
                Proxy proxy = select.get(0);
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                InetSocketAddress inetSocketAddress2 = (InetSocketAddress) proxy.address();
                PasswordAuthentication requestPasswordAuthentication$ar$ds = ServiceConfigUtil.requestPasswordAuthentication$ar$ds(GrpcUtil.getHost(inetSocketAddress2), inetSocketAddress2.getAddress(), inetSocketAddress2.getPort());
                if (inetSocketAddress2.isUnresolved()) {
                    inetSocketAddress2 = new InetSocketAddress(InetAddress.getByName(inetSocketAddress2.getHostName()), inetSocketAddress2.getPort());
                }
                InternalChannelz$ChannelTrace$Event.Builder builder = new InternalChannelz$ChannelTrace$Event.Builder();
                builder.setTargetAddress$ar$ds(inetSocketAddress);
                builder.setProxyAddress$ar$ds(inetSocketAddress2);
                if (requestPasswordAuthentication$ar$ds == null) {
                    return builder.build();
                }
                builder.InternalChannelz$ChannelTrace$Event$Builder$ar$subchannelRef = requestPasswordAuthentication$ar$ds.getUserName();
                if (requestPasswordAuthentication$ar$ds.getPassword() != null) {
                    str = new String(requestPasswordAuthentication$ar$ds.getPassword());
                }
                builder.InternalChannelz$ChannelTrace$Event$Builder$ar$severity = str;
                return builder.build();
            } catch (URISyntaxException e) {
                log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl", "detectProxy", "Failed to construct URI for proxy lookup, proceeding without proxy", (Throwable) e);
                return null;
            }
        } catch (Throwable th) {
            log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl", "detectProxy", "Failed to get host for proxy lookup, proceeding without proxy", th);
            return null;
        }
    }

    @Override // io.grpc.ProxyDetector
    public final ProxiedSocketAddress proxyFor(SocketAddress socketAddress) {
        if (!(socketAddress instanceof InetSocketAddress)) {
            return null;
        }
        if (this.overrideProxyAddress != null) {
            InternalChannelz$ChannelTrace$Event.Builder builder = new InternalChannelz$ChannelTrace$Event.Builder();
            builder.setProxyAddress$ar$ds(this.overrideProxyAddress);
            builder.setTargetAddress$ar$ds((InetSocketAddress) socketAddress);
            return builder.build();
        }
        return detectProxy((InetSocketAddress) socketAddress);
    }
}
