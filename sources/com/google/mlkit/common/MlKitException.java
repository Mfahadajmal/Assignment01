package com.google.mlkit.common;

import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MlKitException extends Exception {
    public final int code;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MlKitException(String str, int i) {
        super(str);
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds$c11d1227_0(str, "Provided message must not be empty.");
        this.code = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MlKitException(String str, Throwable th) {
        super(str, th);
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds$c11d1227_0(str, "Provided message must not be empty.");
        this.code = 13;
    }
}
