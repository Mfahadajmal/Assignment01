package com.google.android.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IAuthManagerService extends IInterface {
    Bundle getTokenByAccount(Account account, String str, Bundle bundle);
}
