package org.chromium.net;

import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NetworkChangeNotifierAutoDetect$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ NetworkChangeNotifierAutoDetect f$0;
    public /* synthetic */ NetworkChangeNotifierAutoDetect.ConnectivityManagerDelegate f$1;

    public /* synthetic */ NetworkChangeNotifierAutoDetect$$ExternalSyntheticLambda0(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect, NetworkChangeNotifierAutoDetect.ConnectivityManagerDelegate connectivityManagerDelegate) {
        this.f$0 = networkChangeNotifierAutoDetect;
        this.f$1 = connectivityManagerDelegate;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$setConnectivityManagerDelegateForTests$1(this.f$1);
    }
}
