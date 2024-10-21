package com.google.android.gms.auth.api.internal;

import android.os.IInterface;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IAuthService extends IInterface {
    void getSpatulaHeader(IAuthCallbacks iAuthCallbacks);

    void performProxyRequest(IAuthCallbacks iAuthCallbacks, ProxyRequest proxyRequest);
}
