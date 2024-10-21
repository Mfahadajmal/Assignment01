package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DiagnosticCoroutineContextException extends RuntimeException {
    private final transient CoroutineContext context;

    public DiagnosticCoroutineContextException(CoroutineContext coroutineContext) {
        this.context = coroutineContext;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public final String getLocalizedMessage() {
        return this.context.toString();
    }
}
