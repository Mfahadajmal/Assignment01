package okio;

import java.io.InterruptedIOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Timeout {
    public static void throwIfReached$ar$ds() {
        if (!Thread.currentThread().isInterrupted()) {
        } else {
            throw new InterruptedIOException("interrupted");
        }
    }
}
