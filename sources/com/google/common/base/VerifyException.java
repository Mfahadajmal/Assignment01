package com.google.common.base;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VerifyException extends RuntimeException {
    public VerifyException() {
    }

    public VerifyException(String str) {
        super(str);
    }

    public VerifyException(String str, Throwable th) {
        super(str, th);
    }

    public VerifyException(Throwable th) {
        super(th);
    }
}
