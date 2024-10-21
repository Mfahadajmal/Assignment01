package com.google.android.gms.common.api;

import android.accounts.Account;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Api$ApiOptions {
    public static final NoOptions NO_OPTIONS = new NoOptions();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface HasAccountOptions extends Api$ApiOptions {
        Account getAccount();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface HasGoogleSignInAccountOptions extends Api$ApiOptions {
        GoogleSignInAccount getGoogleSignInAccount();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NoOptions implements Api$ApiOptions {
    }
}
