package com.google.apps.tiktok.tracing;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public interface ErrorTrace extends Trace {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class MissingTraceException extends Exception {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TraceCauseCheckingDisabled extends MissingTraceException {
        @Override // java.lang.Throwable
        public final synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }
    }

    Trace createChildTrace(String str, SpanExtras spanExtras, boolean z);

    Exception getException();
}
