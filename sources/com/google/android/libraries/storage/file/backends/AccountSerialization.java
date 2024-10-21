package com.google.android.libraries.storage.file.backends;

import android.accounts.Account;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccountSerialization {
    public static final Account SHARED_ACCOUNT = new Account("shared", "mobstore");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSharedAccount(Account account) {
        return SHARED_ACCOUNT.equals(account);
    }
}
