package okio;

import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class InputStreamSource implements Source {
    private final InputStream input;
    private final Timeout timeout;

    public InputStreamSource(InputStream inputStream, Timeout timeout) {
        this.input = inputStream;
        this.timeout = timeout;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.input.close();
    }

    @Override // okio.Source
    public final long read(Buffer buffer, long j) {
        String message;
        try {
            Timeout.throwIfReached$ar$ds();
            Segment writableSegment$third_party_java_src_okio_okio_jvm = buffer.writableSegment$third_party_java_src_okio_okio_jvm(1);
            int read = this.input.read(writableSegment$third_party_java_src_okio_okio_jvm.data, writableSegment$third_party_java_src_okio_okio_jvm.limit, (int) Math.min(j, 8192 - writableSegment$third_party_java_src_okio_okio_jvm.limit));
            if (read == -1) {
                if (writableSegment$third_party_java_src_okio_okio_jvm.pos == writableSegment$third_party_java_src_okio_okio_jvm.limit) {
                    buffer.head = writableSegment$third_party_java_src_okio_okio_jvm.pop();
                    SegmentPool.recycle(writableSegment$third_party_java_src_okio_okio_jvm);
                    return -1L;
                }
                return -1L;
            }
            writableSegment$third_party_java_src_okio_okio_jvm.limit += read;
            long j2 = read;
            buffer.size += j2;
            return j2;
        } catch (AssertionError e) {
            int i = Okio__JvmOkioKt.Okio__JvmOkioKt$ar$NoOp;
            if (e.getCause() != null && (message = e.getMessage()) != null && OnDeviceStainRemovalLogEvent.contains$default$ar$ds(message, "getsockname failed")) {
                throw new IOException(e);
            }
            throw e;
        }
    }

    public final String toString() {
        return "source(" + this.input + ")";
    }
}
