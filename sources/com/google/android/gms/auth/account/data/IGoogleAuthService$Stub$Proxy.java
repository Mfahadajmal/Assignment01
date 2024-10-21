package com.google.android.gms.auth.account.data;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IGoogleAuthService$Stub$Proxy extends BaseProxy implements IGoogleAuthService {
    public IGoogleAuthService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.data.IGoogleAuthService");
    }

    @Override // com.google.android.gms.auth.account.data.IGoogleAuthService
    public final void getTokenWithDetails(IGetTokenWithDetailsCallback iGetTokenWithDetailsCallback, Account account, String str, Bundle bundle) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGetTokenWithDetailsCallback);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, account);
        obtainAndWriteInterfaceToken.writeString("oauth2:https://www.googleapis.com/auth/supportcontent");
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
