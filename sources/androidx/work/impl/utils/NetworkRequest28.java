package androidx.work.impl.utils;

import android.net.NetworkRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkRequest28 {
    public static final NetworkRequest28 INSTANCE = new NetworkRequest28();

    private NetworkRequest28() {
    }

    public static final NetworkRequest createNetworkRequest(int[] iArr, int[] iArr2) {
        iArr.getClass();
        iArr2.getClass();
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        for (int i : iArr) {
            builder.addCapability(i);
        }
        for (int i2 : iArr2) {
            builder.addTransportType(i2);
        }
        NetworkRequest build = builder.build();
        build.getClass();
        return build;
    }

    public final NetworkRequestCompat createNetworkRequestCompat$work_runtime_release(int[] iArr, int[] iArr2) {
        iArr.getClass();
        iArr2.getClass();
        return new NetworkRequestCompat(createNetworkRequest(iArr, iArr2));
    }

    public final boolean hasCapability$work_runtime_release(NetworkRequest networkRequest, int i) {
        boolean hasCapability;
        networkRequest.getClass();
        hasCapability = networkRequest.hasCapability(i);
        return hasCapability;
    }

    public final boolean hasTransport$work_runtime_release(NetworkRequest networkRequest, int i) {
        boolean hasTransport;
        networkRequest.getClass();
        hasTransport = networkRequest.hasTransport(i);
        return hasTransport;
    }
}
