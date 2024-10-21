package com.google.android.gms.common;

import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UserRecoverableException extends Exception {
    public final Intent intent;

    public UserRecoverableException(String str, Intent intent) {
        super(str);
        this.intent = intent;
    }
}
