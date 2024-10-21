package com.google.android.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IAuthManagerService$Stub$Proxy extends BaseProxy implements IAuthManagerService {
    public IAuthManagerService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.auth.IAuthManagerService");
    }

    @Override // com.google.android.auth.IAuthManagerService
    public final Bundle getTokenByAccount(Account account, String str, Bundle bundle) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, account);
        obtainAndWriteInterfaceToken.writeString("oauth2:https://www.googleapis.com/auth/supportcontent");
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken);
        Bundle bundle2 = (Bundle) Codecs.createParcelable(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle2;
    }
}
