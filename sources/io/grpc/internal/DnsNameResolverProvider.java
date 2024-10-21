package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageCaptioningLoadLogEvent;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DnsNameResolverProvider extends NameResolverProvider {
    private static final boolean IS_ANDROID = OnDeviceImageCaptioningLoadLogEvent.isAndroid(DnsNameResolverProvider.class.getClassLoader());

    @Override // io.grpc.NameResolver.Factory
    public final String getDefaultScheme() {
        return "dns";
    }

    @Override // io.grpc.NameResolverProvider
    public final Collection getProducedSocketAddressTypes() {
        return Collections.singleton(InetSocketAddress.class);
    }

    @Override // io.grpc.NameResolver.Factory
    public final NameResolver newNameResolver(URI uri, NameResolver.Args args) {
        if ("dns".equals(uri.getScheme())) {
            String path = uri.getPath();
            path.getClass();
            ContextDataProvider.checkArgument(path.startsWith("/"), "the path component (%s) of the target (%s) must start with '/'", path, uri);
            String substring = path.substring(1);
            uri.getAuthority();
            return new DnsNameResolver(substring, args, GrpcUtil.SHARED_CHANNEL_EXECUTOR, new Stopwatch(), IS_ANDROID);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.NameResolverProvider
    public final void isAvailable$ar$ds$f3439281_1() {
    }

    @Override // io.grpc.NameResolverProvider
    public final void priority$ar$ds() {
    }
}
