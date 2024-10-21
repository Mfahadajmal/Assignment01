package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ISignInService$Stub$Proxy extends BaseProxy implements ISignInService {
    public ISignInService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    @Override // com.google.android.gms.signin.internal.ISignInService
    public final void signIn(SignInRequest signInRequest, ISignInCallbacks iSignInCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, signInRequest);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iSignInCallbacks);
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }
}
