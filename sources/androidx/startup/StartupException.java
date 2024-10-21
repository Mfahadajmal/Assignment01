package androidx.startup;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupException extends RuntimeException {
    public StartupException() {
        super("Context cannot be null");
    }

    public StartupException(Throwable th) {
        super(th);
    }
}
