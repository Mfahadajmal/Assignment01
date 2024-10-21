package com.google.android.gms.auth.account.data;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGoogleAuthService extends IInterface {
    void getTokenWithDetails(IGetTokenWithDetailsCallback iGetTokenWithDetailsCallback, Account account, String str, Bundle bundle);
}
