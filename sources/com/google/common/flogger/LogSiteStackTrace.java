package com.google.common.flogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogSiteStackTrace extends Exception {
    public LogSiteStackTrace(Throwable th, StackSize stackSize, StackTraceElement[] stackTraceElementArr) {
        super(stackSize.toString(), th);
        setStackTrace(stackTraceElementArr);
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        return this;
    }
}
