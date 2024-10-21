package com.google.android.gms.pseudonymous.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IPseudonymousIdService$Stub$Proxy extends BaseProxy implements IPseudonymousIdService {
    public IPseudonymousIdService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.pseudonymous.internal.IPseudonymousIdService");
    }

    @Override // com.google.android.gms.pseudonymous.internal.IPseudonymousIdService
    public final void getToken(IPseudonymousIdCallbacks iPseudonymousIdCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iPseudonymousIdCallbacks);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
