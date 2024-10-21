package org.chromium.net;

import org.chromium.net.ProxyChangeListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProxyChangeListener$$ExternalSyntheticLambda1 implements Runnable {
    public /* synthetic */ ProxyChangeListener f$0;
    public /* synthetic */ ProxyChangeListener.Delegate f$1;

    public /* synthetic */ ProxyChangeListener$$ExternalSyntheticLambda1(ProxyChangeListener proxyChangeListener, ProxyChangeListener.Delegate delegate) {
        this.f$0 = proxyChangeListener;
        this.f$1 = delegate;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$setDelegateForTesting$0(this.f$1);
    }
}
