package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IAccountAccessor$Stub$Proxy extends BaseProxy implements IAccountAccessor {
    public IAccountAccessor$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account getAccount() {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        Account account = (Account) Codecs.createParcelable(transactAndReadException, Account.CREATOR);
        transactAndReadException.recycle();
        return account;
    }
}
