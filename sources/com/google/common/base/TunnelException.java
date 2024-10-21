package com.google.common.base;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TunnelException extends RuntimeException {
    public TunnelException(Exception exc) {
        super("TunnelExceptions should always be unwrapped to deal with the checked exception underneath, this message should never be seen if TunnelException is used properly.", exc);
    }

    @Override // java.lang.Throwable
    public final synchronized Exception getCause() {
        Throwable cause;
        cause = super.getCause();
        cause.getClass();
        return (Exception) cause;
    }
}
