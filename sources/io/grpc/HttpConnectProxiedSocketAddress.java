package io.grpc;

import _COROUTINE._BOUNDARY;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HttpConnectProxiedSocketAddress extends ProxiedSocketAddress {
    private static final long serialVersionUID = 0;
    public final String password;
    public final SocketAddress proxyAddress;
    public final InetSocketAddress targetAddress;
    public final String username;

    public HttpConnectProxiedSocketAddress(SocketAddress socketAddress, InetSocketAddress inetSocketAddress, String str, String str2) {
        socketAddress.getClass();
        inetSocketAddress.getClass();
        ContextDataProvider.checkState(!((InetSocketAddress) socketAddress).isUnresolved(), "The proxy address %s is not resolved", socketAddress);
        this.proxyAddress = socketAddress;
        this.targetAddress = inetSocketAddress;
        this.username = str;
        this.password = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof HttpConnectProxiedSocketAddress)) {
            return false;
        }
        HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress = (HttpConnectProxiedSocketAddress) obj;
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.proxyAddress, httpConnectProxiedSocketAddress.proxyAddress) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.targetAddress, httpConnectProxiedSocketAddress.targetAddress) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.username, httpConnectProxiedSocketAddress.username) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.password, httpConnectProxiedSocketAddress.password)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.proxyAddress, this.targetAddress, this.username, this.password});
    }

    public final String toString() {
        boolean z;
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("proxyAddr", this.proxyAddress);
        stringHelper.addHolder$ar$ds("targetAddr", this.targetAddress);
        stringHelper.addHolder$ar$ds("username", this.username);
        if (this.password != null) {
            z = true;
        } else {
            z = false;
        }
        return stringHelper.add("hasPassword", z).toString();
    }
}
