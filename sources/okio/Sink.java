package okio;

import java.io.Closeable;
import java.io.Flushable;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Sink extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    void write(Buffer buffer, long j);
}
