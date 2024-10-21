package com.google.android.gms.auth;

import android.content.Intent;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UserRecoverableAuthException extends GoogleAuthException {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Type {
        LEGACY,
        AUTH_INSTANTIATION,
        CALLER_INSTANTIATION
    }

    public UserRecoverableAuthException(String str, Intent intent) {
        this(str, Type.LEGACY);
    }

    public UserRecoverableAuthException(String str, Type type) {
        super(str);
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(type);
    }
}
