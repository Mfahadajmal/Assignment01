package androidx.work.impl.utils;

import android.net.NetworkRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkRequest31 {
    public static final NetworkRequest31 INSTANCE = new NetworkRequest31();

    private NetworkRequest31() {
    }

    public final int[] capabilities(NetworkRequest networkRequest) {
        int[] capabilities;
        networkRequest.getClass();
        capabilities = networkRequest.getCapabilities();
        capabilities.getClass();
        return capabilities;
    }

    public final int[] transportTypes(NetworkRequest networkRequest) {
        int[] transportTypes;
        networkRequest.getClass();
        transportTypes = networkRequest.getTransportTypes();
        transportTypes.getClass();
        return transportTypes;
    }
}
