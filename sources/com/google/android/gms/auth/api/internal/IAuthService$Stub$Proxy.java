package com.google.android.gms.auth.api.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IAuthService$Stub$Proxy extends BaseProxy implements IAuthService {
    public IAuthService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.internal.IAuthService");
    }

    @Override // com.google.android.gms.auth.api.internal.IAuthService
    public final void getSpatulaHeader(IAuthCallbacks iAuthCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iAuthCallbacks);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.auth.api.internal.IAuthService
    public final void performProxyRequest(IAuthCallbacks iAuthCallbacks, ProxyRequest proxyRequest) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iAuthCallbacks);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, proxyRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
