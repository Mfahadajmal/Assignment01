package org.chromium.net;

import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NetworkChangeNotifierAutoDetect$$ExternalSyntheticLambda1 implements Runnable {
    public /* synthetic */ NetworkChangeNotifierAutoDetect f$0;
    public /* synthetic */ NetworkChangeNotifierAutoDetect.WifiManagerDelegate f$1;

    public /* synthetic */ NetworkChangeNotifierAutoDetect$$ExternalSyntheticLambda1(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect, NetworkChangeNotifierAutoDetect.WifiManagerDelegate wifiManagerDelegate) {
        this.f$0 = networkChangeNotifierAutoDetect;
        this.f$1 = wifiManagerDelegate;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$setWifiManagerDelegateForTests$2(this.f$1);
    }
}
