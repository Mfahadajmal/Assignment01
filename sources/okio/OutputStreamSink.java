package okio;

import com.google.mlkit.logging.schema.ToxicityDetectionCreateEvent;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class OutputStreamSink implements Sink {
    private final OutputStream out;
    private final Timeout timeout;

    public OutputStreamSink(OutputStream outputStream, Timeout timeout) {
        this.out = outputStream;
        this.timeout = timeout;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.out.close();
    }

    @Override // okio.Sink, java.io.Flushable
    public final void flush() {
        this.out.flush();
    }

    public final String toString() {
        return "sink(" + this.out + ")";
    }

    @Override // okio.Sink
    public final void write(Buffer buffer, long j) {
        ToxicityDetectionCreateEvent.checkOffsetAndCount(buffer.size, 0L, j);
        while (j > 0) {
            Timeout.throwIfReached$ar$ds();
            Segment segment = buffer.head;
            segment.getClass();
            int min = (int) Math.min(j, segment.limit - segment.pos);
            this.out.write(segment.data, segment.pos, min);
            int i = segment.pos + min;
            segment.pos = i;
            long j2 = min;
            buffer.size -= j2;
            j -= j2;
            if (i == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }
}
