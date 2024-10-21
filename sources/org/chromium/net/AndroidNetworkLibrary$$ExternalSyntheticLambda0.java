package org.chromium.net;

import android.net.wifi.WifiManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AndroidNetworkLibrary$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ WifiManager f$0;
    public /* synthetic */ boolean f$1;

    public /* synthetic */ AndroidNetworkLibrary$$ExternalSyntheticLambda0(WifiManager wifiManager, boolean z) {
        this.f$0 = wifiManager;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.setWifiEnabled(this.f$1);
    }
}
